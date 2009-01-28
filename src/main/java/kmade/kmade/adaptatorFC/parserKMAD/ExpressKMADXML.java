package kmade.kmade.adaptatorFC.parserKMAD;

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

import kmade.KMADEToolConstant;
import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.SwingWorker;
import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.KMADXMLParserException;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.Media;
import kmade.nmda.schema.tache.Tache;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
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
     * @param done The done to set.
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
     * @param canceled The canceled to set.
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
     * @param notDone The notDone to set.
     */
    public static void setBegining(boolean notDone) {
        ExpressKMADXML.begining = notDone;
    }
    
    /**
     * @param error The error to set.
     */
    public static void setError(boolean error) {
        ExpressKMADXML.error = error;
    }

    // * 
    // * Save XML File part
    // *
	private static void transformerXml(Document document, String fichier) {
        try {
            // Création de la source DOM
            Source source = new DOMSource(document);
    
            // Création du fichier de sortie
            Result resultat = new StreamResult(fichier);
    
            // Configuration du transformer
            TransformerFactory fabrique = TransformerFactory.newInstance();
            Transformer transformer = fabrique.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "file:KMADModel.dtd");
            
            // Transformation
            transformer.transform(source, resultat);
        }catch(Exception e){
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
					DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
					DocumentBuilder constructeur = fabrique.newDocumentBuilder();
					Document document = constructeur.newDocument();

					document.setXmlVersion("1.0");
					document.setXmlStandalone(true);

					Element racine = document.createElement("kmad-model");
					ExpressKMADXML.writeBodyXML(document, racine);
                    if (!ExpressKMADXML.isCanceled()) {
                        document.appendChild(racine);
                        transformerXml(document, fileName);
                        done = true;
                    }
				} catch (ParserConfigurationException e) {
                    error = true;
					System.out.println(KMADEConstant.WRITE_EXPRESS_ERROR_FILE + fileName);
					System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE 	+ " : " + e.getMessage());
				} catch (KMADXMLParserException e) {
                    error = true;
					System.out.println(KMADEConstant.WRITE_EXPRESS_ERROR_FILE + fileName);
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
    
	private static void writeBodyXML(Document document, Element racine) throws KMADXMLParserException {
	    Set set = InterfaceExpressJava.bdd.keySet();
        currentEntity = 0;
        for (Iterator i = set.iterator(); i.hasNext() && !ExpressKMADXML.canceled && !ExpressKMADXML.error;) {
            Oid oid = (Oid) i.next();
            Object o = InterfaceExpressJava.bdd.prendre(oid);
            try {
                Element myElement = ((Entity) o).toXML(document);
                System.out.println(((Entity)o).toSPF());
                currentEntity++;
                if (myElement != null) {
                    racine.appendChild(myElement);
                }
            } catch(Exception e) {
                throw new KMADXMLParserException(KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : " + o.toString() + "(" + oid.get()+ ")");
            }
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
                     // création d'une fabrique de documents
                     DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
                     // création d'un constructeur de documents
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
                     // Lecture du contenu d'un fichier XML avec DOM
                     Document document = constructeur.parse(kmadeModelFile);             
                     Element node = document.getDocumentElement();
                     ExpressKMADXML.readHeadXML(node);
                     ExpressKMADXML.readBodyXML(node);
                } catch(ParserConfigurationException pce){
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + fileName);
                    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE + " : " + pce.getMessage());
                } catch(SAXException se){
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + fileName);
                    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE + " : " + se.getMessage());
                } catch(IOException ioe){
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + fileName);
                    System.out.println(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE + " : " + ioe.getMessage());
                } catch(KMADXMLParserException e) {
                    ExpressKMADXML.error = true;
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + fileName);
                    System.out.println(e.getMessage());
                }
                return null;
            }};
            worker.start();
    }
    
    private static void readHeadXML(Element node) {
    }
    
    private static Entity createOidInstance(Element currentElement) throws KMADXMLParserException {
        String classe = KMADEToolConstant.PACKAGE_PATH_NAME + currentElement.getAttribute("classkmad");

        Class entity = null;
        try {
            entity = Class.forName(classe);
        } catch (ClassNotFoundException e) {
            throw new KMADXMLParserException(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : " + currentElement.getAttribute("classkmad"));
        }
        
        if (entity != null) {
            Entity newInstance = null;
            try {
                newInstance = (Entity)entity.newInstance();
            } catch (InstantiationException e) {
                throw new KMADXMLParserException(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : " + currentElement.getAttribute("classkmad"));
            } catch (IllegalAccessException e) {
                throw new KMADXMLParserException(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : " + currentElement.getAttribute("classkmad"));
            } catch (IllegalArgumentException e) {
                throw new KMADXMLParserException(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : " + currentElement.getAttribute("classkmad"));
            }
            return newInstance;
        }
        throw new KMADXMLParserException(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : " + currentElement.getAttribute("classkmad"));
    }
    
    private static void readBodyXML(Element racine) throws KMADXMLParserException {
        NodeList nodeList = racine.getChildNodes();
        
        for (int i = 0 ; i < nodeList.getLength() && !ExpressKMADXML.canceled; i++) {
            if (nodeList.item(i).getNodeType() == Element.ELEMENT_NODE) {
                Element myElement = (Element)nodeList.item(i);
                String idTask = myElement.getAttribute("idkmad");
                Entity newInstance = createOidInstance(myElement);
                try {
                    if (newInstance.oidIsAnyMissing(myElement)) {
                        notYetCreated.add(myElement);
                    } else {
                        newInstance.createObjectFromXMLElement(myElement);
                        InterfaceExpressJava.bdd.mettre(new Oid(idTask), newInstance);
                        System.out.println(newInstance.toSPF());
                    }
                } catch (Exception e) {
                    throw new KMADXMLParserException(KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : " + myElement.getAttribute("classkmad") + "(" + myElement.getAttribute("idkmad") + ")");                        
                }
            }
        }
     
        boolean noRemove = false;
        while(notYetCreated.size() != 0 && !noRemove && !ExpressKMADXML.canceled) {
            Iterator<Element> it = notYetCreated.iterator();
            noRemove = true;
            while(it.hasNext()) {
                Element current = it.next();
                String idTask = current.getAttribute("idkmad");
                Entity newInstance = createOidInstance(current);
                
                try {
                    if (newInstance.oidIsAnyMissing(current)) {
                        // Ne rien faire ...
                    } else {
                        newInstance.createObjectFromXMLElement(current);
                        InterfaceExpressJava.bdd.mettre(new Oid(idTask), newInstance);                        
                        it.remove();
                        noRemove = false;
                        System.out.println(newInstance.toSPF());
                    }
                } catch (Exception e) {                    
                    throw new KMADXMLParserException(KMADEConstant.ELEMENT_PARSE_PROBLEM_MESSAGE + " : " + current.getAttribute("classkmad") + "(" + current.getAttribute("idkmad")+ ")");
                }
            }
        }        
        
        if (noRemove) {
        	// Y a des entités en trop : explication un problème d'exception qui n'a pas permis de finaliser la création ...
        	while(notYetCreated.size() != 0) {
        		try {
        			notYetCreated.remove(0);
        		} catch (Exception e) {
                	throw new KMADXMLParserException(KMADEConstant.XML_PARSER_PROBLEM_MESSAGE + " : " + KMADEConstant.XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE);
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
    	// Media Part
        Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Tache");
        for (int i = 0 ; i < taches.length ; i++) {
        	if (((Tache)taches[i]).getMedia() == null) {
        		Oid oidMedia = InterfaceExpressJava.createEntity("tache", "Media");
        		Media m = (Media) InterfaceExpressJava.prendre(oidMedia);
        		((Tache)taches[i]).setMedia(m);
        	}
        }
    }
}
