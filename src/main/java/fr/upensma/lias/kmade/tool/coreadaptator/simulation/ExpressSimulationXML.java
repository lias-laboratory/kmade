/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
 * 
 * KMADe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KMADe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
 **********************************************************************************/
package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fr.upensma.lias.kmade.KMADEToolConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.User;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public final class ExpressSimulationXML {

	private static void transformerXml(Document document, String fichier) {
		try {
			// Cr??ation de la source DOM
			Source source = new DOMSource(document);

			// Cr??ation du fichier de sortie
			Result resultat = new StreamResult(fichier);

			// Configuration du transformer
			TransformerFactory fabrique = TransformerFactory.newInstance();
			Transformer transformer = fabrique.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "file:KMADEScenrio.dtd");

			// Transformation
			transformer.transform(source, resultat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean saveScenario(String xmlName, ArrayList<TokenRecordScenarioSimulation> scenarioList,
			Task rootTask) {
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document document = constructeur.newDocument();

			document.setXmlVersion("1.0");
			document.setXmlStandalone(true);

			Element racine = ExpressSimulationXML.writeHeadXML(document, rootTask);
			for (TokenRecordScenarioSimulation current : scenarioList) {
				ExpressSimulationXML.writeBodyXML(document, racine, current);
			}
			document.appendChild(racine);
			transformerXml(document, xmlName);
			return true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static Element writeHeadXML(Document document, Task rootTask) {
		Element racine = document.createElement("scenario");
		racine.setAttribute("comment", "Scenario Test");
		racine.setAttribute("date", "Under Development");
		racine.setAttribute("name", "Under Development");
		racine.setAttribute("idTask", rootTask.getOid().get());
		racine.setAttribute("nameTask", rootTask.getName());
		return racine;
	}

	private static void writeBodyXML(Document document, Element racine, TokenRecordScenarioSimulation tss) {
		Element action = null;
		if (tss.isExecuterAction()) {
			action = ExpressSimulationXML.writeExecuteActionXML(document, tss);
		} else if (tss.isInterrompreAction()) {
			action = ExpressSimulationXML.writeInterruptActionXML(document, tss);
		} else if (tss.isAbandonnerAction()) {
			action = ExpressSimulationXML.writeCancelActionXML(document, tss);
		} else if (tss.isPasserAction()) {
			action = ExpressSimulationXML.writePassActionXML(document, tss);
		} else if (tss.isReprendreAction()) {
			action = ExpressSimulationXML.writeResumeActionXML(document, tss);
		}

		racine.appendChild(action);
	}

	private static Element writeResumeActionXML(Document document, TokenRecordScenarioSimulation tss) {
		Element executeAction = document.createElement("resume");
		executeAction.setAttribute("idTask", tss.getTask().getOid().get());
		executeAction.setAttribute("nameTask", tss.getTask().getName());
		return executeAction;
	}

	private static Element writeCancelActionXML(Document document, TokenRecordScenarioSimulation tss) {
		Element executeAction = document.createElement("cancel");
		executeAction.setAttribute("idTask", tss.getTask().getOid().get());
		executeAction.setAttribute("nameTask", tss.getTask().getName());
		return executeAction;
	}

	private static Element writePassActionXML(Document document, TokenRecordScenarioSimulation tss) {
		Element executeAction = document.createElement("pass");
		executeAction.setAttribute("idTask", tss.getTask().getOid().get());
		executeAction.setAttribute("nameTask", tss.getTask().getName());
		return executeAction;
	}

	private static Element writeInterruptActionXML(Document document, TokenRecordScenarioSimulation tss) {
		Element executeAction = document.createElement("interrupt");
		executeAction.setAttribute("idTask", tss.getTask().getOid().get());
		executeAction.setAttribute("nameTask", tss.getTask().getName());
		return executeAction;
	}

	private static Element writeExecuteActionXML(Document document, TokenRecordScenarioSimulation tss) {
		Element executeAction = document.createElement("execute");
		executeAction.setAttribute("idTask", tss.getTask().getOid().get());
		executeAction.setAttribute("nameTask", tss.getTask().getName());

		if (tss.getUser() != null) {
			ExpressSimulationXML.writeUserExecutingConstraint(document, tss.getUser(), executeAction);
		}
		ExpressSimulationXML.writeUserValue(document, tss.getUserValuePre(), "userPreValue", executeAction);
		ExpressSimulationXML.writeConcreteValue(document, tss.getConcretePre(), "userPreConcreteObject",
				"idUserConcreteObject", "nameUserConcreteObject", executeAction);
		ExpressSimulationXML.writeUserValue(document, tss.getUserValuePost(), "userPostValue", executeAction);
		ExpressSimulationXML.writeConcreteValue(document, tss.getConcretePost(), "userPostConcreteObject",
				"idUserConcreteObject", "nameUserConcreteObject", executeAction);
		ExpressSimulationXML.writeUserValue(document, tss.getUserValueIter(), "userIterValue", executeAction);
		ExpressSimulationXML.writeConcreteValue(document, tss.getConcreteIter(), "userIterConcreteObject",
				"idUserConcreteObject", "nameUserConcreteObject", executeAction);
		return executeAction;
	}

	private static void writeUserExecutingConstraint(Document document, User p, Element executeAction) {
		Element temp = document.createElement("userExecutingConstraint");
		temp.setAttribute("idUser", p.getOid().get());
		temp.setAttribute("nameUser", p.getName());
		executeAction.appendChild(temp);
	}

	private static void writeUserValue(Document document, ArrayList<String> tab, String userValueElement,
			Element executeAction) {
		for (String currentUserPre : tab) {
			Element temp = document.createElement(userValueElement);
			temp.setTextContent(currentUserPre.toString());
			executeAction.appendChild(temp);
		}
	}

	private static void writeConcreteValue(Document document, ArrayList<ObjetConcret> tab, String userConcreteElement,
			String idUserConcrete, String nameUserConcreteObject, Element executeAction) {
		for (ObjetConcret currentConcretePre : tab) {
			Element tempConcreteObject = document.createElement(userConcreteElement);
			Element tempIdConcreteObject = document.createElement(idUserConcrete);
			tempIdConcreteObject.setTextContent(currentConcretePre.getOid().get());
			tempConcreteObject.appendChild(tempIdConcreteObject);
			Element tempNameUserConcreteObject = document.createElement(nameUserConcreteObject);
			tempNameUserConcreteObject.setTextContent(currentConcretePre.getName());
			tempConcreteObject.appendChild(tempNameUserConcreteObject);
			executeAction.appendChild(tempConcreteObject);
		}
	}

	public static ReplayScenarioModel loadScenario(File scenarioFile) {
		try {
			// cr??ation d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			// cr??ation d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();

			EntityResolver toto = new EntityResolver() {
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					try {
						if (!systemId.startsWith("file:")) {
							return null;
						}
						String pathDtd = systemId.substring(5);
						if (!pathDtd.startsWith("/")) {
							pathDtd = "/" + pathDtd;
						}
						pathDtd = KMADEToolConstant.DTD_DIRECTORY_NAME + pathDtd;

						InputStream is = getClass().getResourceAsStream(pathDtd);
						if (null == is) {
							return null;
						}
						return new InputSource(is);
					} catch (Exception e) {
						return null;
					}
				}
			};

			constructeur.setEntityResolver(toto);
			// lecture du contenu d'un fichier XML avec DOM
			Document document = constructeur.parse(scenarioFile);
			Element node = document.getDocumentElement();
			Task rootTask = ExpressSimulationXML.readHeadXML(node);
			ArrayList<TokenReplayScenarioSimulation> ref = ExpressSimulationXML.readBodyXML(node);
			ReplayScenarioModel myReplayScenarioModel = new ReplayScenarioModel(ref, rootTask);
			return myReplayScenarioModel;
		} catch (ParserConfigurationException pce) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CONFIG_ERROR_DOM);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CALL_NEW_DOCUMENT_BUILDER);
		} catch (SAXException se) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.SIMULATION_PARSING_ERROR);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CALL_CONSTRUCTEUR_PARSE);
		} catch (IOException ioe) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.SIMULATION_IO_ERROR);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CALL_CONSTRUCTEUR_PARSE);
		}
		return new ReplayScenarioModel();
	}

	private static Task readHeadXML(Element node) {
		// String p = "";
		// p += node.getAttribute("coment");
		// p += " " + node.getAttribute("date");
		// p += " " + node.getAttribute("name");
		String idTask = node.getAttribute("idTask");
		Task myTask = ExpressTask.getTaskFromOid(idTask);
		return myTask;
	}

	private static ArrayList<TokenReplayScenarioSimulation> readBodyXML(Element racine) {
		ArrayList<TokenReplayScenarioSimulation> myReplayScenarioSimulation = new ArrayList<TokenReplayScenarioSimulation>();
		NodeList nodeList = racine.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Element.ELEMENT_NODE) {
				Element myElement = (Element) nodeList.item(i);
				String idTask = myElement.getAttribute("idTask");
				String nameTask = myElement.getAttribute("nameTask");

				// A partir de l'OID du sc??nario enregistr??, l'OID de la t??che
				// et sa r??f??rence
				Task myTask = ExpressTask.getTaskFromOid(idTask);

				TokenReplayScenarioSimulation myTokenSimulation;
				// Tache non trouv??.
				if (myTask == null) {
					// Extraction des noms qui ressemblent ?? celui donn??s dans
					// le sc??nario.
					ArrayList<Task> potentialTask = ExpressTask.getTasksFromName(nameTask);
					myTokenSimulation = new TokenReplayScenarioSimulation(potentialTask);
					// Tache trouv??e
				} else {
					myTokenSimulation = new TokenReplayScenarioSimulation(myTask);
				}

				if (myElement.getNodeName().equals("execute")) {
					myTokenSimulation.setAction(TokenSimulation.EXECUTER);
					NodeList userPreValue = ((Element) nodeList.item(i)).getElementsByTagName("userPreValue");
					myTokenSimulation.setUserValuePre(ExpressSimulationXML.readUserValue(userPreValue));
					NodeList userPostValue = ((Element) nodeList.item(i)).getElementsByTagName("userPostValue");
					myTokenSimulation.setUserValuePost(ExpressSimulationXML.readUserValue(userPostValue));
					NodeList userIterValue = ((Element) nodeList.item(i)).getElementsByTagName("userIterValue");
					myTokenSimulation.setUserValueIter(ExpressSimulationXML.readUserValue(userIterValue));

					// NodeList userPreConcreteObject =
					// ((Element)nodeList.item(i)).getElementsByTagName("userPreConcreteObject");
					// NodeList userPostConcreteObject =
					// ((Element)nodeList.item(i)).getElementsByTagName("userPostConcreteObject");
					// NodeList userIterConcreteObject =
					// ((Element)nodeList.item(i)).getElementsByTagName("userIterConcreteObject");
				} else if (myElement.getNodeName().equals("pass")) {
					myTokenSimulation.setAction(TokenSimulation.PASSER);
				} else if (myElement.getNodeName().equals("interrupt")) {
					myTokenSimulation.setAction(TokenSimulation.INTERROMPRE);
				} else if (myElement.getNodeName().equals("resume")) {
					myTokenSimulation.setAction(TokenSimulation.REPRENDRE);
				} else if (myElement.getNodeName().equals("cancel")) {
					myTokenSimulation.setAction(TokenSimulation.ABANDONNER);
				}

				myReplayScenarioSimulation.add(myTokenSimulation);
			}
		}
		return myReplayScenarioSimulation;
	}

	private static ArrayList<String> readUserValue(NodeList tab) {
		ArrayList<String> maValueString = new ArrayList<String>();
		for (int i = 0; i < tab.getLength(); i++) {
			maValueString.add(tab.item(i).getTextContent());
		}
		return maValueString;
	}

	// private static void readConcreteValue(NodeList tab) {
	// for (int i = 0 ; i < tab.getLength() ; i++) {
	//
	// }
	// }
}
