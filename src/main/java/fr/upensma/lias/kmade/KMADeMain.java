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
package fr.upensma.lias.kmade;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import quicktime.QTException;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.QuickTimeSessionCheck;
import fr.upensma.lias.kmade.tool.viewadaptator.KMADeAdaptator;

/**
 * @author Mickael BARON
 * @author Thomas LACHAUME
 */
public class KMADeMain {

    private static boolean acceptedMedia = false;

    public static final boolean isDebug = false;

    public static ResourceBundle messages;

    public static boolean isAcceptedMedia() {
	return acceptedMedia;
    }

    public static void setAcceptedMedia(boolean e) {
	acceptedMedia = e;
    }

    public static void main(String[] args) throws Exception {
	// Gestion du language.

	String lang = KMADEConstant.FRENCH_LANGUAGE_INFO;
	// Création d'un properties qui ne doit servir que pour la selection de
	// la langue au début
	// sinon il faut utiliser celui de PreferencesAdaptator
	// l'ouverture de preferenceAdaptator ne peut pas �tre fait ici
	Properties prop = new Properties();
	// ouverture du fichier de config
	try {
	    FileInputStream in = new FileInputStream("config.ini");
	    prop.load(in);
	    in.close();
	    lang = (String) prop.get(KMADEConstant.LANGUAGE_NAME);
	} catch (IOException e) {

	}
	// récupération de la langue

	// Français en langue par défaut
	String temp = "fr";
	if (lang.equals(KMADEConstant.FRENCH_LANGUAGE_INFO)) {
	    temp = "fr";
	} else if (lang.equals(KMADEConstant.ENGLISH_LANGUAGE_INFO)) {
	    temp = "en";
	}
	/* Permet de mettre les boites de dialogue dans la bonne langue */
	if (temp.equals("fr")) {
	    JOptionPane.setDefaultLocale(Locale.FRENCH);
	} else {
	    JOptionPane.setDefaultLocale(Locale.ENGLISH);
	}
	if (!KMADeMain.loadKMADEResourceBundle(new Locale(temp))) {
	    KMADEHistoryMessageManager.printlnMessage("");
	    KMADEHistoryMessageManager
		    .printlnMessage("Failure for loading the " + temp
			    + " localization.");

	}

	// KMADeMain.initQuickTimeSession();

	PlasticLookAndFeel.set3DEnabled(true);
	PlasticLookAndFeel.setCurrentTheme(new ExperienceBlue());
	try {
	    UIManager.setLookAndFeel(new PlasticLookAndFeel());
	} catch (Exception e) {
	    KMADEHistoryMessageManager.printlnError("Can't set look & feel:"
		    + e);
	}

	JDialog.setDefaultLookAndFeelDecorated(true);
	JFrame.setDefaultLookAndFeelDecorated(true);

	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		KMADeAdaptator.initLaunchKMADe();
	    }
	});
    }

    public static void initQuickTimeSession() {
	try {
	    QuickTimeSessionCheck.check();
	    acceptedMedia = true;
	} catch (QTException qte) {
	    qte.printStackTrace();
	    acceptedMedia = false;
	}
    }

    public static boolean loadKMADEResourceBundle(Locale myLocale) {
	try {
	    KMADeMain.messages = ResourceBundle.getBundle(
		    KMADEToolConstant.BUNDLE_FILE, myLocale,
		    new ExtendedControl());
	} catch (MissingResourceException e) {
	    e.printStackTrace();
	    return false;
	}
	KMADEConstant.loadMessagesFromBundle();
	ExpressConstant.loadMessagesFromBundle();
	return true;
    }

    public static boolean isTheSameLocale(Locale myNextLocale) {
	if (myNextLocale.getDisplayName().equals(
		KMADeMain.messages.getLocale().getDisplayName())) {
	    return true;
	} else {
	    return false;
	}
    }
}
