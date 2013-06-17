/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.coreadaptator.parserkmad;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.KMADXMLParserException;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Person;
import fr.upensma.lias.kmade.kmad.schema.tache.Machine;
import fr.upensma.lias.kmade.kmad.schema.tache.Media;
import fr.upensma.lias.kmade.kmad.schema.tache.Organization;
import fr.upensma.lias.kmade.kmad.schema.tache.ParkMachines;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.User;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public final class ExpressKMADXML {

	private static boolean done = false;

	private static boolean canceled = false;

	private static boolean error = false;

	private static boolean begining = true;

	private static int currentEntity = 0;

	private static ArrayList<Element> notYetCreated = new ArrayList<Element>();

	private static File kmadeModelFile = null;

	private static String fileName = "";

	public static String getCurrentFileName() {
		return fileName;
	}

	public static int getCurrentEntity() {
		return currentEntity;
	}

	public static void setCurrentEntity(int p) {
		currentEntity = p;
	}

	/**
	 * @return Returns the done.
	 */
	public static boolean isDone() {
		return done;
	}

	/**
	 * @param done
	 *            The done to set.
	 */
	public static void setDone(boolean done) {
		ExpressKMADXML.done = done;
	}

	/**
	 * @return Returns the canceled.
	 */
	public static boolean isCanceled() {
		return canceled;
	}

	/**
	 * @param canceled
	 *            The canceled to set.
	 */
	public static void setCanceled(boolean canceled) {
		ExpressKMADXML.canceled = canceled;
	}

	/**
	 * @return Returns the error.
	 */
	public static boolean isError() {
		return error;
	}

	/**
	 * @return Returns the notDone.
	 */
	public static boolean isBegining() {
		return begining;
	}

	/**
	 * @param notDone
	 *            The notDone to set.
	 */
	public static void setBegining(boolean notDone) {
		ExpressKMADXML.begining = notDone;
	}

	/**
	 * @param error
	 *            The error to set.
	 */
	public static void setError(boolean error) {
		ExpressKMADXML.error = error;
	}

	// *
	// * Save XML File part
	// *
	private static void transformerXml(Document document, String fichier) {
		try {
			// Cr�ation de la source DOM
			Source source = new DOMSource(document);

			// Cr�ation du fichier de sortie
			Result resultat = new StreamResult(fichier);

			// Configuration du transformer
			TransformerFactory fabrique = TransformerFactory.newInstance();
			Transformer transformer = fabrique.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
					"file:KMADModelJT.dtd");

			// Transformation
			transformer.transform(source, resultat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveKMADModel(String kmadeModelFile) {
		ExpressKMADXML.fileName = kmadeModelFile;
	}

	public static void saveKMADModelProcess() {
		SwingWorker worker = new SwingWorker() {
			public Object construct() {
				try {
					DocumentBuilderFactory fabrique = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder constructeur = fabrique
							.newDocumentBuilder();
					Document document = constructeur.newDocument();

					document.setXmlVersion("1.0");
					document.setXmlStandalone(true);

					Element racine = document.createElement("kmad-model");
					racine.setAttribute("version",
							KMADEToolConstant.VERSION_VALUE);
					ExpressKMADXML.writeBodyXML(document, racine);
					if (!ExpressKMADXML.isCanceled()) {
						document.appendChild(racine);
						transformerXml(document, fileName);
						done = true;
					}
				} catch (ParserConfigurationException e) {
					error = true;
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
							+ " : " + e.getMessage());
				} catch (KMADXMLParserException e) {
					error = true;
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(e.getMessage());
				}
				return null;
			}
		};
		worker.start();
	}

	public static void saveKMADItemsProcess() {
		SwingWorker worker = new SwingWorker() {
			public Object construct() {
				try {
					DocumentBuilderFactory fabrique = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder constructeur = fabrique
							.newDocumentBuilder();
					Document document = constructeur.newDocument();

					document.setXmlVersion("1.0");
					document.setXmlStandalone(true);

					Element racine = document.createElement("kmad-items");
					racine.setAttribute("version",
							KMADEToolConstant.VERSION_VALUE);
					ExpressKMADXML.writeBodyXMLForItems(document, racine);
					if (!ExpressKMADXML.isCanceled()) {
						document.appendChild(racine);
						transformerXml(document, fileName);
						done = true;
					}
				} catch (ParserConfigurationException e) {
					error = true;
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
							+ " : " + e.getMessage());
				} catch (KMADXMLParserException e) {
					error = true;
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(e.getMessage());
				}
				return null;
			}
		};
		worker.start();
	}

	public static int getEntitySize() {
		return InterfaceExpressJava.bdd.size();
	}


	/**
	 * @author Joachim TROUVERIE
	 * To create the xml body for the kmad Model the only classes
	 * concerned are the classes 
	 * @param document
	 * @param racine
	 * @throws KMADXMLParserException
	 *             
	 */
	private static void writeBodyXML(Document document, Element racine)
			throws KMADXMLParserException {
		currentEntity = 0;
		for (Iterator<Oid> i = InterfaceExpressJava.bdd.keySet().iterator(); i
				.hasNext();) {
			Oid oid = i.next();
			currentEntity++;
			Object o = InterfaceExpressJava.bdd.prendre(oid);
			String thisClassName = o.getClass().getName();
			//Verify if the class is a mother class
			for (int j = 0; j < ExpressConstant.MOTHER_CLASSES.length; j++) {
				if (thisClassName.contains(ExpressConstant.MOTHER_CLASSES[j])) {
					writeBodyXMLByObject(o, document, racine);
				}
			}
		}
	}
	/**
	 * To save only the objects in the xml file
	 * @author Joachim TROUVERIE
	 * @param document
	 * @param racine
	 * @throws KMADXMLParserException
	 */
	private static void writeBodyXMLForItems(Document document, Element racine)
			throws KMADXMLParserException {
		currentEntity = 0;
		for (Iterator<Oid> i = InterfaceExpressJava.bdd.keySet().iterator(); i
				.hasNext();) {
			Oid oid = i.next();
			currentEntity++;
			Object o = InterfaceExpressJava.bdd.prendre(oid);
			KMADEHistoryMessageManager.printlnMessage(oid.get());
			String thisClassName = o.getClass().getName();
			//Verify if the class is a mother class
			//and if the class is a part of the object package
			for (int j = 0; j < ExpressConstant.MOTHER_CLASSES.length; j++) {
				if (thisClassName.contains(ExpressConstant.MOTHER_CLASSES[j])
						&& thisClassName.contains(ExpressConstant.OBJECTS_PACKAGE)) {
					writeBodyXMLByObject(o, document, racine);
				}
			}
		}
	}

	/**
	 * Save elements in the xml by object
	 * @author Joachim TROUVERIE 
	 * @param object
	 * @param document
	 * @param racine
	 * @throws KMADXMLParserException
	 */
	private static void writeBodyXMLByObject(Object object, Document document,
			Element racine) throws KMADXMLParserException {
		String ClassName = object.getClass().getName();
		try {
			Element myElement = null;
			// if the element is not a task or if it is the root task
			if (!ClassName.contains(ExpressConstant.TASK_CLASS)
					|| ((Task) object).getNumber().startsWith(
							ExpressConstant.ROOT_TASK_NAME)) {
				myElement = ((Entity) object).toXML2(document);
				KMADEHistoryMessageManager.printlnMessage(((Entity) object).toSPF());
			}

			if (myElement != null) {
				racine.appendChild(myElement);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new KMADXMLParserException(
					KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : "
							+ object.toString() + "("
							+ ((Entity) object).getOid().get() + ")");
		}
	}


	// Partie pour le chargement du fichier XML
	public static void loadKMADModel(File kmadeModelFile) {
		ExpressKMADXML.kmadeModelFile = kmadeModelFile;
		fileName = kmadeModelFile.getAbsolutePath();
	}

	public static void loadKMADModelProcess() {
		SwingWorker worker = new SwingWorker() {
			public Object construct() {
				try {
					// cr�ation d'une fabrique de documents
					DocumentBuilderFactory fabrique = DocumentBuilderFactory
							.newInstance();
					// cr�ation d'un constructeur de documents
					DocumentBuilder constructeur = fabrique
							.newDocumentBuilder();

					EntityResolver toto = new EntityResolver() {
						public InputSource resolveEntity(String publicId,
								String systemId) throws SAXException,
								IOException {
							try {
								if (!systemId.startsWith("file:")) {
									return null;
								}
								String pathDtd = systemId.substring(5);
								if (!pathDtd.startsWith("/")) {
									pathDtd = "/" + pathDtd;
								}
								pathDtd = KMADEToolConstant.DTD_DIRECTORY_NAME
										+ pathDtd;

								InputStream is = getClass()
										.getResourceAsStream(pathDtd);
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
					// Lecture du contenu d'un fichier XML avec DOM
					Document document = constructeur.parse(kmadeModelFile);
					Element node = document.getDocumentElement();
					float version = ExpressKMADXML.readHeadXML(node);
					ExpressKMADXML.readBodyXML(node,version);
				} catch (ParserConfigurationException pce) {
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
							+ " : " + pce.getMessage());
				} catch (SAXException se) {
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
							+ " : " + se.getMessage());
				} catch (IOException ioe) {
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
							+ " : " + ioe.getMessage());
				} catch (KMADXMLParserException e) {
					ExpressKMADXML.error = true;
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
							+ fileName);
					KMADEHistoryMessageManager.printlnMessage(e.getMessage());
				}
				return null;
			}
		};
		worker.start();
	}

	/**
	 * @param node
	 * @return the version number  xx.yy.zz->xx,yyzz
	 */
	private static float readHeadXML(Element node) {
		if (!node.hasAttribute("version")) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.NO_VERSION);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.NEW_SAVE_NEW_FORMAT);
			KMADEHistoryMessageManager.printlnMessage(String.valueOf(Float.parseFloat(node.getAttribute("version"))));
			return 0;
		} else {
			if (!KMADEToolConstant.VERSION_VALUE.equals(node
					.getAttribute("version"))) {
				// la version du fichier charger n'est pas la m�me que celle de
				// l'outil
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.NOT_SAME_VERSION);
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.VERSION_USE
						+ KMADEToolConstant.VERSION_VALUE);
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.VERSION_FILE
						+ node.getAttribute("version"));
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.NEW_SAVE_NEW_FORMAT);

			} else {

				// version identique
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.VERSION_USE
						+ node.getAttribute("version"));
			}
			// n� f the version in float xx.yy.zz->xx,yyzz
			String vers = node.getAttribute("version");
			String versTab[] = vers.split("\\.");
			float version = 0;
			int div = 1;
			for(int i = 0; i < versTab.length; i++){
				div = (int) ( (Math.pow(10,i*2)));
				version = version + Float.parseFloat(versTab[i])/div;
			}
			return version;
		}
	}

	private static Entity createOidInstance(Element currentElement)
			throws KMADXMLParserException {
		String classe = KMADEToolConstant.PACKAGE_PATH_NAME
				+ currentElement.getAttribute("classkmad");

		Class<?> entity = null;
		try {
			entity = Class.forName(classe);
		} catch (ClassNotFoundException e) {

			if (classe.equals("kmade.nmda.schema.metaobjet.IntValue")) {
				try {
					entity = Class
							.forName("kmade.nmda.schema.metaobjet.NumberValue");
				} catch (ClassNotFoundException e1) {
					throw new KMADXMLParserException(
							ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE
							+ " : "
							+ currentElement.getAttribute("classkmad"));
				}
			} else {
				throw new KMADXMLParserException(
						ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : "
								+ currentElement.getAttribute("classkmad"));
			}
		}

		if (entity != null) {
			Entity newInstance = null;
			try {
				newInstance = (Entity) entity.newInstance();
			} catch (InstantiationException e) {
				throw new KMADXMLParserException(
						ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : "
								+ currentElement.getAttribute("classkmad"));
			} catch (IllegalAccessException e) {
				throw new KMADXMLParserException(
						ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : "
								+ currentElement.getAttribute("classkmad"));
			} catch (IllegalArgumentException e) {
				throw new KMADXMLParserException(
						ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : "
								+ currentElement.getAttribute("classkmad"));
			}
			return newInstance;
		}
		throw new KMADXMLParserException(
				ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : "
						+ currentElement.getAttribute("classkmad"));
	}

	private static void readBodyXML(Element racine, float version)
			throws KMADXMLParserException {
		NodeList nodeList = racine.getChildNodes();
		boolean noRemove = false;
		if(version <=1){
			for (int i = 0; i < nodeList.getLength() && !ExpressKMADXML.canceled; i++) {
				if (nodeList.item(i).getNodeType() == Element.ELEMENT_NODE) {

					Element myElement = (Element) nodeList.item(i);
					String idTask = myElement.getAttribute("idkmad");
					Entity newInstance = createOidInstance(myElement);
					try {
						if (newInstance.oidIsAnyMissing(myElement)) {
							notYetCreated.add(myElement);
						} else {
							newInstance.createObjectFromXMLElement(myElement);
							InterfaceExpressJava.bdd.mettre(new Oid(idTask),
									newInstance);
							KMADEHistoryMessageManager.printlnMessage(newInstance.toSPF());
						}
					} catch (Exception e) {
						throw new KMADXMLParserException(
								KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : "
										+ myElement.getAttribute("classkmad") + "("
										+ myElement.getAttribute("idkmad") + ")");
					}
				}
			}

			noRemove = false;
			while (notYetCreated.size() != 0 && !noRemove
					&& !ExpressKMADXML.canceled) {
				Iterator<Element> it = notYetCreated.iterator();
				noRemove = true;
				while (it.hasNext()) {
					Element current = it.next();
					String idTask = current.getAttribute("idkmad");
					Entity newInstance = createOidInstance(current);

					try {
						if (newInstance.oidIsAnyMissing(current)) {
							// Ne rien faire ...
						} else {
							newInstance.createObjectFromXMLElement(current);
							InterfaceExpressJava.bdd.mettre(new Oid(idTask),
									newInstance);
							it.remove();
							noRemove = false;
							KMADEHistoryMessageManager.printlnMessage(newInstance.toSPF());
						}
					} catch (Exception e) {
						throw new KMADXMLParserException(
								KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : "
										+ current.getAttribute("classkmad") + "("
										+ current.getAttribute("idkmad") + ")");
					}
				}
			}

			if (noRemove) {
				// Y a des entit�s en trop : explication un probl�me d'exception qui
				// n'a pas permis de finaliser la cr�ation ...
				while (notYetCreated.size() != 0) {
					try {
						notYetCreated.remove(0);
					} catch (Exception e) {
						throw new KMADXMLParserException(
								KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
								+ " : "
								+ KMADEConstant.XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE);
					}
				}
			}
		}else{//version >1
			//TODO Need the boolean for thread ...
			@SuppressWarnings("unused")
			boolean t = ExpressKMADXML.fromXML2(racine);
		}
		if (!ExpressKMADXML.canceled) {
			InterfaceExpressJava.bdd.loadSPFFinished();

			// This code part allows to add new features from XML DTD v1.0
			ExpressKMADXML.addNewFeatures();
			ExpressKMADXML.done = true;
		}
	}

	private static boolean fromXML2(Element racine) throws KMADXMLParserException{
		HashMap<Entity,Element> myEntities = new HashMap<Entity,Element>();
		// create entities
		fromXMLRecursive(racine,myEntities);
		// create object
		int previoussize = myEntities.size()+1;
		while(previoussize > 0  && previoussize > myEntities.size()){
			previoussize = myEntities.size();
			Set<Entity> mySet = myEntities.keySet();
			for (Entity newInstance : mySet) {
				Element myElement = myEntities.get(newInstance);
				String idTask = myElement.getAttribute("idkmad");
				boolean oidIsAnyMissing = true;
				try {
					// maybe useless with this version 
					if (newInstance.oidIsAnyMissing2(myElement)) {
						if(!notYetCreated.contains(myElement)){
							notYetCreated.add(myElement);
						}
					} else {
						oidIsAnyMissing = false;
					}
				}
				catch (Exception e){
					throw new KMADXMLParserException(
							KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : "
									+ myElement.getAttribute("classkmad") + "("
									+ myElement.getAttribute("idkmad") + ")");
				}
				if(!oidIsAnyMissing){

					try {
							newInstance.createObjectFromXMLElement2(myElement);
							InterfaceExpressJava.bdd.mettre(new Oid(idTask),newInstance);
							myEntities.remove(newInstance);
						break;
					} catch (Exception e) {
						//TODO lot of exception because code archi ...
					}
				}
			}

		}


		//some verification
		boolean noRemove = false;
		while (notYetCreated.size() != 0 && !noRemove
				&& !ExpressKMADXML.canceled) {
			Iterator<Element> it = notYetCreated.iterator();
			noRemove = true;
			while (it.hasNext()) {
				Element current = it.next();
				String idTask = current.getAttribute("idkmad");
				Entity newInstance = createOidInstance(current);

				try {
					if (newInstance.oidIsAnyMissing(current)) {
						// Ne rien faire ...
					} else {
						newInstance.createObjectFromXMLElement(current);
						InterfaceExpressJava.bdd.mettre(new Oid(idTask),
								newInstance);
						it.remove();
						noRemove = false;
						KMADEHistoryMessageManager.printlnMessage(newInstance.toSPF());
					}
				} catch (Exception e) {
					throw new KMADXMLParserException(
							KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : "
									+ current.getAttribute("classkmad") + "("
									+ current.getAttribute("idkmad") + ")");
				}
			}
		}

		if (noRemove) {
			// Y a des entit�s en trop : explication un probl�me d'exception qui
			// n'a pas permis de finaliser la cr�ation ...
			while (notYetCreated.size() != 0) {
				try {
					notYetCreated.remove(0);
				} catch (Exception e) {
					throw new KMADXMLParserException(
							KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
							+ " : "
							+ KMADEConstant.XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE);
				}
			}
		}

		return true;

	}

	private static void fromXMLRecursive(Element racine, HashMap<Entity,Element> myEntities) throws KMADXMLParserException{
		NodeList nodeList = racine.getChildNodes();
		for (int i = 0; i < nodeList.getLength() && !ExpressKMADXML.canceled; i++) {
			if(nodeList.item(i).getNodeType() == Element.ELEMENT_NODE && ((Element) nodeList.item(i)).hasAttribute("idkmad")){
				fromXMLRecursive((Element) nodeList.item(i),myEntities);
			}
			if (nodeList.item(i).getNodeType() == Element.ELEMENT_NODE  && ((Element) nodeList.item(i)).hasAttribute("idkmad")) {
				Element myElement = (Element) nodeList.item(i);
				String idTask = myElement.getAttribute("idkmad");
				Entity newInstance = createOidInstance(myElement);
				InterfaceExpressJava.bdd.mettre(new Oid(idTask),newInstance);
				myEntities.put(newInstance, myElement);
			}

		}

	}


	private static void addNewFeatures() {
		// Individu et organisation

		// le chargement n'a pas mis les individus au seins des organisations
		Object[] indObj = InterfaceExpressJava.prendreAllOidOfEntity("tache",
				"Individu");
		Person[] ind = new Person[indObj.length];
		for (int i = 0; i < ind.length; i++) {
			ind[i] = (Person) indObj[i];
		}
		for (int i = 0; i < ind.length; i++) {
			ArrayList<Organization> org = ind[i].getOrganisations();
			for (int j = 0; j < org.size(); j++) {
				org.get(j).addMember(ind[i]);
			}
		}
		// Machine et parc

		// le chargement n'a pas mis les machines dans les parcs
		Object[] machobj = InterfaceExpressJava.prendreAllOidOfEntity("tache",
				"Machine");
		Machine[] mach = new Machine[machobj.length];
		for (int i = 0; i < mach.length; i++) {
			mach[i] = (Machine) machobj[i];
		}
		for (int i = 0; i < mach.length; i++) {
			ArrayList<ParkMachines> parc = mach[i].getMemberOf();
			for (int j = 0; j < parc.size(); j++) {
				parc.get(j).addMember(mach[i]);
			}
		}

		// changement de tout les User(ancienne version) en Individu
		Object[] userObj = InterfaceExpressJava.prendreAllOidOfEntity("tache",
				"User");
		User[] user = new User[userObj.length];
		for (int i = 0; i < userObj.length; i++) {
			user[i] = (User) userObj[i];
		}
		for (int i = 0; i < userObj.length; i++) {
			// la version actuelle de KMADE ne permet l'utilisation d'User en
			// tant que tel, il faut que ce soit un Individu ou une organisation
			// On cr��r un Individu � partir de l'user
			Person indi = new Person(user[i].getName(),
					user[i].getStatus(), user[i].getRole(), user[i].getImage(),
					user[i].getOid());
			// on supprime l'User de la bdd
			InterfaceExpressJava.remove(user[i].getOid());
			// on met l'individu en pr�cisant l'oid
			InterfaceExpressJava.mettre(indi.getOid(), indi);

		}

		// Media Part
		Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity("tache",
				"Tache");
		for (int i = 0; i < taches.length; i++) {
			if (((Task) taches[i]).getMedia() == null) {
				Oid oidMedia = InterfaceExpressJava.createEntity("tache",
						"Media");
				Media m = (Media) InterfaceExpressJava.prendre(oidMedia);
				((Task) taches[i]).setMedia(m);
			}
		}
	}
}
