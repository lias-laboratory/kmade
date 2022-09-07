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
package fr.upensma.lias.kmade.kmad.interfaceexpressjava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.upensma.lias.kmade.KMADEToolConstant;
import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.parser.spf.ParserSPF;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.expression.CurrentObject;
import fr.upensma.lias.kmade.kmad.schema.tache.CurrentEvents;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public class InterfaceExpressJava {

	private static boolean done = false;

	private static boolean canceled = false;

	private static boolean error = false;

	private static boolean begining = true;

	private static ArrayList<List<?>> dataListFromSPF;

	private static final ParserSPF PARSER_SPF = new ParserSPF();

	private static AbstractMessage gestionWarning = new WarningMessage();

	private static String historyMessage = "";

	private final static CurrentObject refCurrentObject = new CurrentObject();

	private final static CurrentEvents refCurrentEvents = new CurrentEvents();

	public static ExpressDB bdd = new ExpressDB();

	/* AG */
	public static ArrayDeque<ExpressDB> toUndo = new ArrayDeque<ExpressDB>();
	public static ArrayDeque<ExpressDB> toRedo = new ArrayDeque<ExpressDB>();

	private static final ClipBoardDB MY_CLIP_BOARD = new ClipBoardDB();

	public static CurrentObject getCurrentObject() {
		return refCurrentObject;
	}

	public static CurrentEvents getCurrentEvents() {
		return refCurrentEvents;
	}

	public static AbstractMessage getGestionWarning() {
		return gestionWarning;
	}

	/**
	 * Cette m�thode permet d'effacer le contenu complet de la BDD.
	 */
	public static void clearCurrentProject() {
		bdd.clear();
	}

	/**
	 * Cette methode permet d'effacer les objets de la bdd
	 */
	public static void clearCurrentItems() {
		for (Iterator<Oid> i = InterfaceExpressJava.bdd.keySet().iterator(); i.hasNext();) {
			Oid oid = i.next();
			String ClassName = InterfaceExpressJava.bdd.prendre(oid).getClass().getName();
			if (ClassName.contains(ExpressConstant.METAOBJECT_PACKAGE))
				bdd.remove(InterfaceExpressJava.bdd.prendre(oid));
		}
	}

	/**
	 * Cette m�thode permet de supprimer le contenu du presse-papier (Express).
	 */
	public static void clearClipBoard() {
		MY_CLIP_BOARD.clear();
	}

	/**
	 * Cette m�thode permet de savoir si la BDD est modifi�e ou pas.
	 * 
	 * @return
	 */
	public static boolean isBddSet() {
		return ExpressDB.isSet();
	}

	public static void setBddSetOn() {
		ExpressDB.setSetOn();
	}

	public static void setBddSetOff() {
		ExpressDB.setSetOff();
	}

	public static Oid getLastOid() {
		return bdd.getLastOid();
	}

	/**
	 * Cette m�thode permet de cr�er des entit�s dans la base utilis�e uniquement
	 * dans le presse-papier.
	 * 
	 * @param nameSchema
	 * @param typeEntity
	 * @return
	 */
	public static Object createEntityReferenceBackIntoClipBoard(String nameSchema, String typeEntity) {
		String classe = KMADEToolConstant.PACKAGE_PATH_NAME + nameSchema + "." + typeEntity;

		Class<?> entity = null;
		try {
			entity = Class.forName(classe);
		} catch (ClassNotFoundException e) {
			KMADEHistoryMessageManager.printlnError(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + " : " + classe);
			KMADEHistoryMessageManager.printlnError(e.getMessage());
		}

		Object object = null;
		try {
			object = entity.newInstance();
		} catch (InstantiationException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalAccessException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalArgumentException e) {
			KMADEHistoryMessageManager.printlnError(e);
		}

		Oid oid = MY_CLIP_BOARD.put(object);
		InterfaceExpressJava.modifyOid(object, oid);
		return object;
	}

	/**
	 * Cette m�thode permet de retourner toutes les r�f�rences d'un entit�.
	 * 
	 * @param nameSchema
	 * @param typeEntity
	 * @return
	 */
	public static Object[] getAllReferencesOfEntityFromClipBoard(String nameSchema, String typeEntity) {
		Collection<?> set = MY_CLIP_BOARD.values();
		Set<Object> setReturn = new HashSet<Object>();
		String classe = KMADEToolConstant.PACKAGE_PATH_NAME + nameSchema + "." + typeEntity;

		Class<?> entity = null;
		try {
			entity = Class.forName(classe);
		} catch (ClassNotFoundException e) {
			System.err.print(ExpressConstant.ENTITY_NO_EXIST_PROBLEM_MESSAGE + " : " + classe);
			KMADEHistoryMessageManager.printlnError(e);
		}

		Iterator<?> i;
		for (i = set.iterator(); i.hasNext();) {
			Object o = (Object) i.next();
			String classO = o.getClass().toString();
			String classEntity = entity.toString();
			if (classO.equals(classEntity)) {
				setReturn.add(o);
			}
		}
		return setReturn.toArray();
	}

	/**
	 * Cette m�thode cr�e une r�f�rence de la classe nameSchema.typeEntity et la
	 * retourne
	 * 
	 * @param nameSchema
	 * @param typeEntity
	 * @return
	 */
	public static Object createEntityReferenceBack(String nameSchema, String typeEntity) {
		String classe = KMADEToolConstant.PACKAGE_PATH_NAME + nameSchema + "." + typeEntity;

		Class<?> entity = null;
		try {
			entity = Class.forName(classe);
		} catch (ClassNotFoundException e) {
			KMADEHistoryMessageManager.printlnError(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + classe);
			KMADEHistoryMessageManager.printlnError(e.getMessage());
		}

		Object object = null;
		try {
			object = entity.newInstance();
		} catch (InstantiationException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalAccessException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalArgumentException e) {
			KMADEHistoryMessageManager.printlnError(e);
		}

		Oid oid = bdd.put(object);
		InterfaceExpressJava.modifyOid(object, oid);
		return object;
	}

	/**
	 * Cette méthode crée une référence de la classe nameSchema.typeEntity et
	 * retourne son OID
	 * 
	 * @param nameSchema
	 * @param typeEntity
	 * @return
	 */
	public static Oid createEntity(String nameSchema, String typeEntity) {
		// Creer l'objet puis assigner les valeurs suivants les attributs
		String classe = KMADEToolConstant.PACKAGE_PATH_NAME + nameSchema + "." + typeEntity;

		Class<?> entity = null;
		try {
			entity = Class.forName(classe);
		} catch (ClassNotFoundException e) {
			KMADEHistoryMessageManager.printlnError(ExpressConstant.CLASS_LOADER_PROBLEM_MESSAGE + classe);
			KMADEHistoryMessageManager.printlnError(e);
		}

		Object object = null;
		try {
			object = entity.newInstance();
		} catch (InstantiationException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalAccessException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalArgumentException e) {
			KMADEHistoryMessageManager.printlnError(e);
		}

		Oid oid = bdd.put(object);
		InterfaceExpressJava.modifyOid(object, oid);
		return oid;
	}

	private static void modifyOid(Object object, Object valeur) {
		Field widthField;
		Class<?> entity = object.getClass();
		try {
			widthField = entity.getField("oid");
			widthField.set(object, (Oid) valeur);
		} catch (NoSuchFieldException e) {
			KMADEHistoryMessageManager.printlnError(e);
		} catch (IllegalAccessException e) {
			KMADEHistoryMessageManager.printlnError(e);
		}
	}

	public static Oid put(Object o) {
		Oid oid = bdd.put(o);
		modifyOid(o, oid);
		return oid;
	}

	/**
	 * Cette m�thode permet la suppression d'un objet.
	 * 
	 * @param oid
	 */
	public static void remove(Oid oid) {
		bdd.remove(oid);
	}

	public static Object prendre(Oid key) {
		return bdd.prendre(key);
	}

	/**
	 * Cette m�thode permet d'extraire toutes les r�f�rences d'une entit� donn�e.
	 * 
	 * @param nameSchema
	 * @param typeEntity
	 * @return
	 */
	public static Object[] prendreAllOidOfEntity(String nameSchema, String typeEntity) {
		Collection<?> set = bdd.values();
		Set<Object> setReturn = new HashSet<Object>();
		String classe = KMADEToolConstant.PACKAGE_PATH_NAME + nameSchema + "." + typeEntity;

		Class<?> entity = null;
		try {
			entity = Class.forName(classe);
		} catch (ClassNotFoundException e) {
			System.err.print(ExpressConstant.ENTITY_NO_EXIST_PROBLEM_MESSAGE + " : " + classe);
			KMADEHistoryMessageManager.printlnError(e);
		}

		Iterator<?> i;
		for (i = set.iterator(); i.hasNext();) {
			Object o = (Object) i.next();
			String classO = o.getClass().toString();
			String classEntity = entity.toString();
			if (classO.equals(classEntity)) {
				setReturn.add(o);
			}
		}
		return setReturn.toArray();
	}

	public static void out() {
		Set<Oid> set = bdd.keySet();
		Iterator<Oid> i;
		for (i = set.iterator(); i.hasNext();) {
			Oid oid = i.next();
			Object o = bdd.prendre(oid);
			KMADEHistoryMessageManager.printlnMessage(((Entity) o).toSPF());
		}
	}

	/**
	 * Cette méthode enregistre les données dans un fichier texte.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void write(File file) throws IOException {
		FileWriter out = new FileWriter(file);

		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);

		// Ecriture d'un en-tête
		out.write("// SPF K-MADe Version : 0.1");
		out.write("\n");
		out.write("// INRIA Rocquencourt / MErLIn Project");
		out.write("\n");
		out.write("// Contacts : baron@ensma.fr and dominique.scapin@inria.fr");
		out.write("\n");
		out.write("// Date : " + "[" + calendar.get(Calendar.HOUR) + "-" + calendar.get(Calendar.MINUTE) + "], "
				+ calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH));
		out.write("\n");
		out.write("DATA;");
		out.write("\n");

		// Ecriture des donn�es
		Set<Oid> set = bdd.keySet();
		Iterator<Oid> i;
		for (i = set.iterator(); i.hasNext();) {
			Oid oid = i.next();
			Object o = bdd.prendre(oid);
			out.write(((Entity) o).toSPF());
			// Pour chaque OID on saute une ligne.
			out.write("\n");
		}
		out.close();
	}

	/**
	 * Cette méthode doit être appelée avant readOIDFromList et permet de savoir si
	 * d'une part le fichier a été bien lu (l'exeception) et d'autre part si la
	 * liste qui contient tous les OID n'est pas nul (= optimisation).
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static void readOIDFromFile(File file) throws Exception {
		// Introspection du fichier SPF suivant une grammaire donnée.
		dataListFromSPF = PARSER_SPF.readSPFFile(file);
	}

	/**
	 * Cette méthode permet de lancer un processus d'extraction d'information à
	 * partir de la liste d'OID.
	 */
	public static void readOIDFromSPFFile() {
		SwingWorker worker = new SwingWorker() {
			public Object construct() {
				while (dataListFromSPF.size() != 0 && !InterfaceExpressJava.canceled && !InterfaceExpressJava.error) {
					List<?> list2 = dataListFromSPF.remove(0);
					Iterator<?> j = list2.iterator();
					String[] oid = (String[]) j.next();
					String[] nameEntity = (String[]) j.next();

					// Extraction du nom de l'entite : condition, tache,
					// metaobject, project
					Class<?> entity = null;
					try {
						entity = InterfaceExpressJava.getClassFromName(nameEntity[0]);
					} catch (Exception e) {
						InterfaceExpressJava.error = true;
						return null;
					}

					// Taille de la liste des arguments
					int tailleArgs = list2.size();
					Object[] lesArgs = null;
					try {
						lesArgs = fournirListObjetParametre(tailleArgs, j);
					} catch (Exception e) {
						InterfaceExpressJava.error = true;
						return null;
					}
					if (lesArgs == null) {
						dataListFromSPF.add(list2);
					} else {
						// Ajouter le numero de l'oid à la fin des arguments
						lesArgs[lesArgs.length - 1] = new Oid(oid[0]);

						Object object = null;
						Class<?>[] tabClassArgs = new Class[lesArgs.length];
						for (int i = 0; i < lesArgs.length; i++) {
							if (lesArgs[i] != null) {
								tabClassArgs[i] = lesArgs[i].getClass();
							} else {
								tabClassArgs[i] = Object.class;
							}
						}

						Constructor<?> constructeur = getConstructorMaxArgs(entity, lesArgs.length);

						if (constructeur == null) {
							InterfaceExpressJava.error = true;
							return null;
						}

						try {
							object = constructeur.newInstance(lesArgs);
						} catch (Exception e) {
							InterfaceExpressJava.error = true;
							return null;
						}

						bdd.mettre(new Oid(oid[0]), object);
					}
				}

				if (!InterfaceExpressJava.canceled && !InterfaceExpressJava.error) {
					bdd.loadSPFFinished();
					InterfaceExpressJava.out();
					InterfaceExpressJava.done = true;
				}
				return null;
			}
		};
		worker.start();
	}

	public static void mettre(Oid oid, Entity entity) {
		bdd.put(oid, entity);
	}

	private static ArrayList<?> recupererLst(String s) throws Exception {
		java.util.ArrayList<Object> vector = new java.util.ArrayList<Object>();
		String sub = s.substring(1, s.length() - 1);
		if (sub.length() == 0)
			return vector;
		int index = sub.indexOf('#');
		while (index != -1) {
			sub = sub.substring(index);
			String oid = PARSER_SPF.extractOid(sub);
			Object o = bdd.prendre(new Oid(oid));
			if (o == null)
				return null;
			vector.add(o);
			sub = sub.substring(1);
			index = sub.indexOf('#');
		}
		return vector;
	}

	/**
	 * Retourne la classe associ�e � la chaine de caract�res.
	 * 
	 * @param nameEntity
	 * @return
	 */
	private static Class<?> getClassFromName(String nameEntity) throws Exception {
		Class<?> entity = null;
		try {
			entity = Class.forName(KMADEToolConstant.PACKAGE_PATH_NAME + "condition." + nameEntity);
		} catch (ClassNotFoundException c) {
			try {
				entity = Class
						.forName(KMADEToolConstant.PACKAGE_PATH_NAME + ExpressConstant.CORE_PACKAGE + "." + nameEntity);
			} catch (ClassNotFoundException t) {
				try {
					entity = Class.forName(KMADEToolConstant.PACKAGE_PATH_NAME + "metaobjet." + nameEntity);
				} catch (ClassNotFoundException m) {
					try {
						entity = Class.forName(KMADEToolConstant.PACKAGE_PATH_NAME + "project." + nameEntity);
					} catch (ClassNotFoundException s) {
						throw new Exception();
					}
				}
			}
		}
		return entity;
	}

	private static Object[] fournirListObjetParametre(int tailleArgs, java.util.Iterator<?> j) throws Exception {
		Object[] lesArgs = new Object[tailleArgs - 1]; // supprimer le nom
		// entity
		int compteur = 0;
		// indice de remplissage des attributs
		// lire la liste des attributs
		// l'analyse est fonction de la liste donn�e par le parser
		// ***** je considere que les oids d'attributs existent
		// ***** deja en tant que entit�e
		while (j.hasNext()) {
			String[] ch = (String[]) j.next();
			if (ch[0].equalsIgnoreCase("INT")) {
				lesArgs[compteur] = Integer.valueOf(Integer.parseInt(ch[1]));
			} else if (ch[0].equalsIgnoreCase("CHAINE")) {
				String temp = new String(ch[1].substring(1, (ch[1].length() - 1)));
				temp = temp.replaceAll("\\\\'", "'");
				lesArgs[compteur] = temp;
			} else if (ch[0].equalsIgnoreCase("OID")) {
				lesArgs[compteur] = bdd.prendre(new Oid(ch[1]));
				if (lesArgs[compteur] == null)
					return null;
			} else if (ch[0].equalsIgnoreCase("ENUM")) {
				lesArgs[compteur] = new String(ch[1].substring(1, (ch[1].length() - 1)));
			} else if (ch[0].equalsIgnoreCase("BOOLEAN")) {
				lesArgs[compteur] = Boolean.valueOf(ch[1].substring(1, (ch[1].length() - 1)));
			} else if (ch[0].equalsIgnoreCase("AGGREGA")) {
				lesArgs[compteur] = recupererLst(ch[1]);
				if (lesArgs[compteur] == null)
					return null;
			} else if (ch[0].equalsIgnoreCase("NULL")) {
				lesArgs[compteur] = null;
			} else {
				KMADEHistoryMessageManager.printlnError("Type inconnu :" + ch[0]);
				System.exit(0);
			}
			compteur++;
		}
		return lesArgs;
	}

	/** Utilisé pour choisr le constructeur avec le bon nombre d'arguments */
	public static Constructor<?> getConstructorMaxArgs(Class<?> c, int size) {
		Constructor<?>[] cons = c.getConstructors();
		Constructor<?> keeped = null;
		for (int i = 0; i < cons.length; i++) {
			int taille = cons[i].getParameterTypes().length;
			if (taille == size) {
				keeped = cons[i];
				break;
			}
		}
		return keeped;
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
		InterfaceExpressJava.done = done;
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
		InterfaceExpressJava.canceled = canceled;
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
		InterfaceExpressJava.begining = notDone;
	}

	/**
	 * @param error The error to set.
	 */
	public static void setError(boolean error) {
		InterfaceExpressJava.error = error;
	}

	/**
	 * @return Returns the historyMessage.
	 */
	public static String getHistoryMessage() {
		if (InterfaceExpressJava.historyMessage.equals("")) {
			return "Pas de valeur";
		} else {
			return historyMessage;
		}
	}

	public static void clearHistoryMessage() {
		InterfaceExpressJava.historyMessage = "";
	}

	public static void appendHistoryMessage(String value) {
		if (InterfaceExpressJava.historyMessage.equals("")) {
			Calendar calendar = new GregorianCalendar();
			Date trialTime = new Date();
			calendar.setTime(trialTime);

			InterfaceExpressJava.historyMessage = "[" + calendar.get(Calendar.HOUR_OF_DAY) + ","
					+ calendar.get(Calendar.MINUTE) + "] " + "Avant : " + value;
		} else {
			InterfaceExpressJava.historyMessage += " " + value;
		}
	}
}
