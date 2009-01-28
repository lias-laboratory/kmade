package kmade;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.QuickTimeSessionCheck;
import kmade.kmade.adaptatorUI.KMADeAdaptator;
import kmade.nmda.ExpressConstant;
import quicktime.QTException;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;

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
		boolean isError = false;
        if (args.length > 0) {
        	String temp = args[0].toLowerCase();
            if (!temp.equals("fr") && !temp.equals("en")) {
                System.out.println("");     
                System.out.println("Supported languages by K-MADe");
                System.out.println(" fr : French");
                System.out.println(" en : English");
                System.exit(0);                 
            }
            if (!KMADeMain.loadKMADEResourceBundle(new Locale(temp))) {
                System.out.println("");
                System.out.println("Failure for loading the " + temp + " localization.");
                isError = true;
            }
        } else {
            if (!KMADeMain.loadKMADEResourceBundle()) {
                System.out.println("");
                System.out.println("Failure for loading the " + Locale.getDefault().getLanguage() + " localization.");
                isError = true;
            }
        }
        
        if (isError) {
            System.out.println("");
            System.out.println("Loading the " + Locale.ENGLISH.getLanguage() + " localization.");
            if (!KMADeMain.loadKMADEResourceBundle(Locale.ENGLISH)) {
                System.out.println("");
                System.out.println("Failure for loading the " + Locale.ENGLISH + " localization. Contact K-MADe team");
            	System.exit(0);
            }
        }

//        KMADeMain.initQuickTimeSession();
        
        PlasticLookAndFeel.set3DEnabled(true);
        PlasticLookAndFeel.setCurrentTheme(new ExperienceBlue());
        try {
            UIManager.setLookAndFeel(new PlasticLookAndFeel());
        } catch (Exception e) {
            System.err.println("Can't set look & feel:" + e);
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

	private static boolean loadKMADEResourceBundle() {
		if (Locale.getDefault().getLanguage().equals(Locale.FRENCH.getLanguage()) || Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
			return KMADeMain.loadKMADEResourceBundle(Locale.getDefault());
		}
		return false;
	}

	public static boolean loadKMADEResourceBundle(Locale myLocale) {
	    try {            
	        KMADeMain.messages = ResourceBundle.getBundle(KMADEToolConstant.BUNDLE_FILE, myLocale);
	    } catch (MissingResourceException e) {
	        return false;
	    }
	    KMADEConstant.loadMessagesFromBundle();
	    ExpressConstant.loadMessagesFromBundle();
	    return true;
	}

	public static boolean isTheSameLocale(Locale myNextLocale) {
	    if (myNextLocale.getDisplayName().equals(KMADeMain.messages.getLocale().getDisplayName())) {
	        return true;
	    } else {
	        return false;
	    }
	}
}
