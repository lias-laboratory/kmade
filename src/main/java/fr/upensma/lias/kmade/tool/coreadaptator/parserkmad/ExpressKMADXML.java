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
import org.w3c.dom.Node;
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
import fr.upensma.lias.kmade.kmad.schema.tache.Individu;
import fr.upensma.lias.kmade.kmad.schema.tache.Machine;
import fr.upensma.lias.kmade.kmad.schema.tache.Media;
import fr.upensma.lias.kmade.kmad.schema.tache.Organisation;
import fr.upensma.lias.kmade.kmad.schema.tache.ParcMachines;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.kmad.schema.tache.User;
import fr.upensma.lias.kmade.tool.KMADEConstant;
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

    private static File kmadeFile = null;

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

    public static void saveKMAD(String kmadeModelFile) {
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
		    System.out.println(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
			    + fileName);
		    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
			    + " : " + e.getMessage());
		} catch (KMADXMLParserException e) {
		    error = true;
		    System.out.println(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
			    + fileName);
		    System.out.println(e.getMessage());
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
		    System.out.println(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
			    + fileName);
		    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
			    + " : " + e.getMessage());
		} catch (KMADXMLParserException e) {
		    error = true;
		    System.out.println(KMADEConstant.WRITE_EXPRESS_ERROR_FILE
			    + fileName);
		    System.out.println(e.getMessage());
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
	    System.out.println(oid.get());
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
		    || ((Tache) object).getNumero().startsWith(
			    ExpressConstant.ROOT_TASK_NAME)) {
		myElement = ((Entity) object).toXML2(document);
		System.out.println(((Entity) object).toSPF());
	    }

	    if (myElement != null) {
		racine.appendChild(myElement);
	    }
	} catch (Exception e) {
	    throw new KMADXMLParserException(
		    KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : "
			    + object.toString() + "("
			    + ((Entity) object).getOid().get() + ")");
	}
    }

    // Partie pour le chargement du fichier XML
    
    //It's the same method to load entire project or only items
    public static void loadKMAD(File kmadeFile) {
	ExpressKMADXML.kmadeFile = kmadeFile;
	fileName = kmadeFile.getAbsolutePath();
    }

    public static void loadKMADProcess() {
	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		try {
		    // creation d'une fabrique de documents
		    DocumentBuilderFactory fabrique = DocumentBuilderFactory
			    .newInstance();
		    // creation d'un constructeur de documents
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
		    Document document = constructeur.parse(kmadeFile);
		    Element node = document.getDocumentElement();
		    ExpressKMADXML.readHeadXML(node);
		    ExpressKMADXML.readBodyXML(node);
		} catch (ParserConfigurationException pce) {
		    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
			    + fileName);
		    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
			    + " : " + pce.getMessage());
		} catch (SAXException se) {
		    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
			    + fileName);
		    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
			    + " : " + se.getMessage());
		} catch (IOException ioe) {
		    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
			    + fileName);
		    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE
			    + " : " + ioe.getMessage());
		} catch (KMADXMLParserException e) {
		    ExpressKMADXML.error = true;
		    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
			    + fileName);
		    System.out.println(e.getMessage());
		}
		return null;
	    }
	};
	worker.start();
    }

    private static void readHeadXML(Element node) {
	if (!node.hasAttribute("version")) {
	    System.out.println(KMADEConstant.NO_VERSION);
	    System.out.println(KMADEConstant.NEW_SAVE_NEW_FORMAT);
	} else {
	    if (!KMADEToolConstant.VERSION_VALUE.equals(node
		    .getAttribute("version"))) {
		// la version du fichier charger n'est pas la m�me que celle
		// de
		// l'outil
		System.out.println(KMADEConstant.NOT_SAME_VERSION);
		System.out.println(KMADEConstant.VERSION_USE
			+ KMADEToolConstant.VERSION_VALUE);
		System.out.println(KMADEConstant.VERSION_FILE
			+ node.getAttribute("version"));
		System.out.println(KMADEConstant.NEW_SAVE_NEW_FORMAT);

	    } else {
		// version identique
		System.out.println(KMADEConstant.VERSION_USE
			+ node.getAttribute("version"));
	    }
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

    /**
     * @author Joachim TROUVERIE 
     * Create the objects from the kxml file
     * @param racine
     * @throws KMADXMLParserException
     */
    private static void readBodyXML(Element racine)
	    throws KMADXMLParserException {
	NodeList nodeList = racine.getElementsByTagName("*");
	for (int i = 0; i < nodeList.getLength() && !ExpressKMADXML.canceled; i++) {
	    if (nodeList.item(i).getNodeType() == Element.ELEMENT_NODE
		    && ((Element) nodeList.item(i)).hasAttribute("idkmad")) {
		Element myElement = (Element) nodeList.item(i);
		String idTask = myElement.getAttribute("idkmad");
		// Previous version of the dtd
		if (idTask.charAt(0) == '#') {
		    Entity newInstance = createOidInstance(myElement);
		    try {
			if (newInstance.oidIsAnyMissing(myElement)) {
			    notYetCreated.add(myElement);
			} else {
			    newInstance.createObjectFromXMLElement(myElement);
			    InterfaceExpressJava.bdd.mettre(new Oid(idTask),
				    newInstance);
			    System.out.println(newInstance.toSPF());
			}
		    } catch (Exception e) {
			throw new KMADXMLParserException(
				KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE
					+ " : "
					+ myElement.getAttribute("classkmad")
					+ "("
					+ myElement.getAttribute("idkmad")
					+ ")");
		    }
		}
		// Actual version of the dtd
		else if (idTask.charAt(0) == 'K') {
		    Entity newInstance = createOidInstance(myElement);
		    try {
			if (newInstance.oidIsAnyMissing2(myElement)) {
			    notYetCreated.add(myElement);
			} else {
			    newInstance.createObjectFromXMLElement2(myElement);
			    InterfaceExpressJava.bdd.mettre(new Oid(idTask),
				    newInstance);
			    System.out.println(newInstance.toSPF());
			}
		    } catch (Exception e) {
			throw new KMADXMLParserException(
				KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE
					+ " : "
					+ myElement.getAttribute("classkmad")
					+ "("
					+ myElement.getAttribute("idkmad")
					+ ")");
		    }
		}

	    }
	}

	boolean noRemove = false;
	while (notYetCreated.size() != 0 && !noRemove
		&& !ExpressKMADXML.canceled) {
	    noRemove = true;
	    for (int i = notYetCreated.size() - 1; i >= 0;) {
		Element current = notYetCreated.get(i);
		String idTask = current.getAttribute("idkmad");
		Entity newInstance = createOidInstance(current);

		try {
		    // Previous version of the dtd
		    if (idTask.charAt(0) == '#') {
			if (newInstance.oidIsAnyMissing(current)) {

			} else {
			    newInstance.createObjectFromXMLElement(current);
			    InterfaceExpressJava.bdd.mettre(new Oid(idTask),
				    newInstance);
			    notYetCreated.remove(i);
			    noRemove = false;
			    System.out.println(newInstance.toSPF());
			    i--;
			}
		    }
		    // Actual version of the dtd
		    else if (idTask.charAt(0) == 'K') {
			if (newInstance.oidIsAnyMissing2(current)) {
			    if (i != 0) {
				notYetCreated.add(0, notYetCreated.get(i));
				notYetCreated.remove(i + 1);
				noRemove = false;
			    }
			} else {
			    newInstance.createObjectFromXMLElement2(current);
			    InterfaceExpressJava.bdd.mettre(new Oid(idTask),
				    newInstance);
			    notYetCreated.remove(i);
			    noRemove = false;
			    System.out.println(newInstance.toSPF());
			    i--;
			}
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
	    // Y a des entit�s en trop : explication un probl�me d'exception
	    // qui
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

	if (!ExpressKMADXML.canceled) {
	    InterfaceExpressJava.bdd.loadSPFFinished();

	    // This code part allows to add new features from XML DTD v1.0
	    ExpressKMADXML.addNewFeatures();
	    ExpressKMADXML.done = true;
	}

    }

    private static void addNewFeatures() {
	// Individu et organisation

	// le chargement n'a pas mis les individus au seins des organisations
	Object[] indObj = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Individu");
	Individu[] ind = new Individu[indObj.length];
	for (int i = 0; i < ind.length; i++) {
	    ind[i] = (Individu) indObj[i];
	}
	for (int i = 0; i < ind.length; i++) {
	    ArrayList<Organisation> org = ind[i].getMemberOf();
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
	    ArrayList<ParcMachines> parc = mach[i].getMemberOf();
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
	    Individu indi = new Individu(user[i].getName(),
		    user[i].getStatut(), user[i].getRole(), user[i].getImage(),
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
	    if (((Tache) taches[i]).getMedia() == null) {
		Oid oidMedia = InterfaceExpressJava.createEntity("tache",
			"Media");
		Media m = (Media) InterfaceExpressJava.prendre(oidMedia);
		((Tache) taches[i]).setMedia(m);
	    }
	}
    }
}
