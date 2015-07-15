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
package fr.upensma.lias.kmade.kmad.parser.spf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public final class ParserSPF {
    private static final char[] lettre = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
	    'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	    'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
	    'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
	    'U', 'V', 'W', 'X', 'Y', 'Z' };

    private static final char[] nombre = { '0', '1', '2', '3', '4', '5', '6',
	    '7', '8', '9' };

    /**
     * Cette méthode permet de lire le contenu d'un fichier SPF
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public ArrayList<List<?>> readSPFFile(File file) throws Exception {
	FileReader in = new FileReader(file);
	BufferedReader lecture = new BufferedReader(in);

	String ligne = "";

	ArrayList<List<?>> arrayList = new ArrayList<List<?>>();

	while (ligne != null) {
	    if (ligne.length() > 0) {
		if (ligne.charAt(0) == '#') {
		    arrayList.add(extractEntity(ligne));
		}
	    }
	    ligne = lecture.readLine();
	}
	lecture.close();
	return arrayList;
    }

    /**
     * Cette méthode extrait les informations d'une ligne.
     * 
     * @param ligne
     * @return
     */
    private List<String[]> extractEntity(String ligne) throws Exception {
	List<String[]> list = new ArrayList<String[]>();

	list.clear();
	int pointeur = 0;

	// Formation de la String : Type, Valeur
	// en O oid = valeur
	// en 1 typeAttribut = valeur
	// en etc

	// Récupérer la valeur de l'oid de l'entity
	String[] nomOID = { extractOid(ligne) };
	list.add(nomOID);

	// Récupérer le nom de l'entity
	pointeur = ligne.indexOf('=') + 1;
	String[] nomENTITY = { extractWord(ligne.substring(pointeur)) };
	list.add(nomENTITY);

	// Analyser les valeurs
	pointeur = ligne.indexOf('(') + 1;

	while (ligne.charAt(pointeur) != ')') {
	    String[] valeur = extractAttributType(ligne.substring(pointeur));
	    if (valeur != null) {
		list.add(valeur);

		pointeur += valeur[1].length();
	    } else
		pointeur++;
	}
	return list;
    }

    private String[] extractAttributType(String s) throws Exception {
	if (s.charAt(0) == '(') {
	    String[] agrega = { "AGGREGA",
		    (s.substring(0, (s.indexOf(')') + 1))) };
	    return agrega;
	}

	if (s.charAt(0) == '$') {
	    String[] idnull = { "NULL", "$" };
	    return idnull;
	}

	if (s.charAt(0) == '#') {
	    String[] oid = { "OID", extractOid(s) };
	    return oid;
	}

	if (s.charAt(0) == '*') {
	    String[] derive = { "DERIVE", "*" };
	    return (derive);
	}

	if (s.charAt(0) == '.') {
	    // Deux cas : soit Boolean soit Ennumere
	    if (s.startsWith(".false.")) {
		String[] boolFalse = { "BOOLEAN", ".false." };
		return boolFalse;
	    }
	    if (s.startsWith(".true.")) {
		String[] boolTrue = { "BOOLEAN", ".true." };
		return boolTrue;
	    }
	    // R�cuperer la chaine repr�sentative de l'ennumere
	    String[] enumm = { "ENUM", extractEnum(s) };
	    return enumm;
	}
	if (numero(s.charAt(0))) {
	    String[] num = { "INT", extractInt(s) };
	    return num;
	}

	if (s.charAt(0) == '\'') {
	    String[] chaine = { "CHAINE", extractString(s) };
	    return chaine;
	}
	return null;
    }

    private String extractInt(String s) throws Exception {
	String num = "";
	int ptr = 0;
	while (numero(s.charAt(ptr))) {
	    num = num + s.charAt(ptr++);
	}
	return num;
    }

    private String extractEnum(String s) throws Exception {
	String enumm = "";
	if (s.charAt(0) != '.') {
	    KMADEHistoryMessageManager.printlnMessage("Erreur dans l'enum \n");
	    throw new Exception();
	}
	int ptr = 1;
	while (s.charAt(ptr) != '.') {
	    enumm = enumm + s.charAt(ptr++);
	}
	return "." + enumm + ".";
    }

    public String extractOid(String s) throws Exception {
	if (s.charAt(0) != '#') {
	    KMADEHistoryMessageManager.printlnMessage("Erreur dans l'OID +> "
		    + s);
	    throw new Exception();
	}
	String oid = "#";
	int ptr = 1;
	while ((ptr < s.length()) && (numero(s.charAt(ptr)))) {
	    oid = oid + s.charAt(ptr++);
	}
	return oid;
    }

    private String extractWord(String s) throws Exception {
	String chaine = "";
	int ptr = 0;
	while (lettre(s.charAt(ptr))) {
	    chaine = chaine + s.charAt(ptr++);
	}
	return chaine;
    }

    private String extractString(String s) throws Exception {
	String chaine = "";
	if (s.charAt(0) != '\'') {
	    KMADEHistoryMessageManager.printlnMessage("Erreur dans chaine \n");
	    throw new Exception();
	}
	int ptr = 1;
	boolean stop = false;
	while (!stop) {
	    if (s.charAt(ptr) == '\\') {
		chaine = chaine + s.charAt(ptr++);
		if (s.charAt(ptr) == '\'') {
		    chaine = chaine + s.charAt(ptr++);
		}
	    } else if (s.charAt(ptr) == '\'') {
		stop = true;
	    } else {
		chaine = chaine + s.charAt(ptr++);
	    }
	}

	return "'" + chaine + "'";
    }

    public boolean numero(char c) {
	for (int i = 0; i < nombre.length; i++) {
	    if (c == nombre[i])
		return true;
	}
	return false;
    }

    public boolean lettre(char c) {
	for (int i = 0; i < lettre.length; i++) {
	    if (c == lettre[i])
		return true;
	}
	return false;
    }
}