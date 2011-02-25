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
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.awt.Point;
import java.io.File;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.upensma.lias.kmade.KMADeMain;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEffetsDeBord;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIteration;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressPrecondition;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.parserkmad.ExpressKMADXML;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.KMADEStartDialog;
import fr.upensma.lias.kmade.tool.view.KMADEToolProjectSplashScreenShadow;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;

import quicktime.QTSession;

/**
 * @author Mickael BARON
 */
public final class KMADeAdaptator {

    static {
	GraphicEditorAdaptator.cleanAllInterface();
	PreferencesAdaptator.initComponent();
    }

    private static String fileName = "";

    private static boolean updateThread = true;

    public static void initLaunchKMADe() {
	if (PreferencesAdaptator.splashScreen() == true)
	    new KMADEToolProjectSplashScreenShadow();
	else
	    KMADeAdaptator.showAtFirst();

	ExpressTask.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    InterfaceExpressJava.setBddSetOn();
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		    EntityListAdaptator.startEntityList();
		    GraphicEditorAdaptator.setSelectedTaskInEnhancedEditor();
		}
	    }
	});
	ExpressPrecondition.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    InterfaceExpressJava.setBddSetOn();
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		    EntityListAdaptator.startEntityList();
		    GraphicEditorAdaptator.setSelectedTaskInEnhancedEditor();
		}
	    }
	});
	ExpressEffetsDeBord.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    InterfaceExpressJava.setBddSetOn();
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		    EntityListAdaptator.startEntityList();
		    GraphicEditorAdaptator.setSelectedTaskInEnhancedEditor();
		}
	    }
	});
	ExpressIteration.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    InterfaceExpressJava.setBddSetOn();
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		    EntityListAdaptator.startEntityList();
		    GraphicEditorAdaptator.setSelectedTaskInEnhancedEditor();
		}
	    }
	});
    }

    public static void debugSimulation() {
	// GraphicEditorAdaptator.getMainFrame().showEmptyPanel();
	KMADeAdaptator.showAtFirst();

	ExpressTask.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		}
	    }
	});
	ExpressPrecondition.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		}
	    }
	});
	ExpressEffetsDeBord.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		}
	    }
	});
	ExpressIteration.addObserver(new Observer() {
	    public void update(Observable o, Object argv) {
		if (updateThread) {
		    CoherenceAdaptator.checkTaskModel();
		    StatisticAdaptator.statisticTaskModel();
		}
	    }
	});

	File myFile = new File("bbbb.spf");
	if (myFile != null) {
	    try {
		GraphicEditorAdaptator.cleanAllInterface();
		// On nettoie le mod?le et la partie graphique.
		InterfaceExpressJava.clearCurrentProject();
		KMADeAdaptator.cleanAllAdaptateur();
		InterfaceExpressJava.readOIDFromFile(myFile);
	    } catch (Exception e) {
		System.out.println("erreur");
		return;
	    }
	    GraphicEditorAdaptator.getMainFrame().getProgressBarDialog()
		    .goReadSPF(false, "");
	    GraphicEditorAdaptator.enabledMainFrameAfterEdition();
	    GraphicEditorAdaptator.getMainFrame().getProgressBarDialog()
		    .setVisible(false);
	    openSimulationDialog();
	}
    }

    public static void openStatisticDialog() {
	StatisticAdaptator.openStatisticDialog();
    }

    public static void openSimulationDialog() {
	// Avant d'ouvrir le module simulation il faut v�rifier s'il n'existe
	// pas d'erreur
	// /!\ Probl�me la mise � jour des valeurs ne se fait que si la boite de
	// dialogue de coh�rence a �t� ouverte
	int erreur = CoherenceAdaptator.getErrorMessageCount();
	int warning = CoherenceAdaptator.getWarningMessageCount();
	if (erreur != 0) {
	    String message = KMADEConstant.COHERENCE_INTRO_ERROR + " ";
	    // Appara�tre une boite de dialogue de danger.
	    message += erreur + " ";
	    message += (erreur == 1) ? KMADEConstant.COHERENCE_ONE_ERROR
		    : KMADEConstant.COHERENCE_SOME_ERROR;
	    message += " " + KMADEConstant.COHERENCE_INTER;
	    message += " " + warning + " ";
	    message += (erreur == 1) ? KMADEConstant.COHERENCE_ONE_WARNING
		    : KMADEConstant.COHERENCE_SOME_WARNING;
	    String titre = KMADEConstant.COHERENCE_VERIFICATION_TITLE;
	    JOptionPane.showMessageDialog(
		    GraphicEditorAdaptator.getMainFrame(),
		    message,
		    titre,
		    JOptionPane.WARNING_MESSAGE,
		    new ImageIcon(GraphicEditorAdaptator.class
			    .getResource(KMADEConstant.INFO_DIALOG_IMAGE)));
	    KMADeAdaptator.openCoherenceDialog();
	    return;
	}
	GraphicEditorAdaptator.clearSelection();
	SimulationAdaptator.openSimulationDialog();
    }

    public static void noToExistProject() {
	GraphicEditorAdaptator.getMainFrame().showProjectPanel();
	GraphicEditorAdaptator.clearClipBoard();
    }

    public static void showAtFirst() {
	GraphicEditorAdaptator.getMainFrame().setEnabled(true);
	GraphicEditorAdaptator.getMainFrame().setVisible(true);
	GraphicEditorAdaptator.hideGrid();
	GraphicEditorAdaptator.hideRule();

	// Montrer l'IHM du d�but.
	KMADEStartDialog ref = new KMADEStartDialog();
	ref.showInfoAbout(KMADEConstant.UNDER_DEVELOPMENT_START_TITLE,
		KMADEConstant.UNDER_DEVELOPMENT_START_HTML);
    }

    public static void printCurrentTaskModel() {
	GraphicEditorAdaptator.printCurrentTaskModel();
    }

    private static boolean doSaveProjectBeforeClose(String message) {
	if (InterfaceExpressJava.isBddSet()) {
	    int value = KMADeAdaptator.askToSaveBeforeAction(message);
	    if (value == JOptionPane.YES_OPTION) {
		// On peut sauvegarder avant.
		KMADeAdaptator.saveProjectXML();
	    } else if (value == JOptionPane.NO_OPTION) {

	    } else if (value == JOptionPane.CANCEL_OPTION) {
		return false;
	    }
	}
	KMADeAdaptator.stopAllUpdateThread();
	InterfaceExpressJava.clearCurrentProject();
	KMADeAdaptator.cleanAllAdaptateur();
	GraphicEditorAdaptator.getMainFrame().disableSaveTitleName();
	fileName = "";
	GraphicEditorAdaptator.cleanAllInterface();
	return true;
    }

    public static void newProject() {
	if (KMADeAdaptator
		.doSaveProjectBeforeClose(KMADEConstant.WRITE_BEFORE_NEW_PROJECT_MESSAGE)) {
	    // Remise � zero de l'outil K-MADe
	    updateThread = true;
	    GraphicEditorAdaptator.initMainFrame();
	    GraphicEditorAdaptator.setDefaultZoom();
	    ProjectManagerAdaptator.createNewProject();
	}
    }

    public static void closeProject() {
	KMADeAdaptator
		.doSaveProjectBeforeClose(KMADEConstant.WRITE_BEFORE_CLOSE_PROJECT_MESSAGE);
    }

    public static void showPreferences() {
	GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
		.setVisible(true);
    }

    public static void hidePreferences() {
	GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
		.setVisible(false);
    }

    public static void loadProjectXML() {
	// Avant d'ouvrir demander si le projet courant doit �tre sauvegard�.
	if (InterfaceExpressJava.isBddSet()) {
	    int value = KMADeAdaptator
		    .askToSaveBeforeAction(KMADEConstant.WRITE_BEFORE_OPEN_PROJECT_MESSAGE);
	    if (value == JOptionPane.YES_OPTION) {
		// On peut sauvegarder avant
		KMADeAdaptator.saveProjectXML();
	    } else if (value == JOptionPane.CANCEL_OPTION) {
		// Pas de chargement on arr�te
		System.out
			.println(KMADEConstant.OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME);
		return;
	    }
	}

	File myCurrentFile = KMADEFileChooser.openKMADModelXMLFile();
	if (myCurrentFile != null) {
	    // Pre-loading : clean UI
	    KMADeAdaptator.stopAllUpdateThread();
	    GraphicEditorAdaptator.cleanAllInterface();
	    InterfaceExpressJava.clearCurrentProject();
	    KMADeAdaptator.cleanAllAdaptateur();
	    GraphicEditorAdaptator.initMainFrame();

	    // Loading
	    ExpressKMADXML.loadKMADModel(myCurrentFile);
	    GraphicEditorAdaptator.getMainFrame().getProgressBarDialog()
		    .readKMADModelFromXMLFile();
	}
	updateThread = true;
    }

    public static void enableCurrentFileName(String p) {
	fileName = p;
	GraphicEditorAdaptator.getMainFrame().enableSaveTitleName(fileName);
    }

    public static void disableCurrentFileName() {
	fileName = "";
	GraphicEditorAdaptator.getMainFrame().disableSaveTitleName();
    }

    public static void saveProjectXML() {
	if (fileName == null | fileName.equals("")) {
	    saveProjectXMLAs();
	} else {
	    File myCurrentFile = new File(fileName);
	    if (myCurrentFile != null) {
		ExpressKMADXML.saveKMADModel(myCurrentFile.getAbsolutePath());
		GraphicEditorAdaptator.getMainFrame().getProgressBarDialog()
			.writeKMADModelFromXMLFile();
	    }
	}
    }

    public static void saveProjectXMLAs() {
	String myCurrentFile = KMADEFileChooser.saveKMADModelXMLFile();
	if (myCurrentFile != null) {
	    ExpressKMADXML.saveKMADModel(myCurrentFile);
	    GraphicEditorAdaptator.getMainFrame().getProgressBarDialog()
		    .writeKMADModelFromXMLFile();
	}
    }

    /**
     * M�thode qui consiste � ouvrir un projet.
     */
    public static void openProject() {
	// Avant d'ouvrir demander si le projet courant doit �tre sauvegard�.
	if (InterfaceExpressJava.isBddSet()) {
	    int value = KMADeAdaptator
		    .askToSaveBeforeAction(KMADEConstant.WRITE_BEFORE_OPEN_PROJECT_MESSAGE);
	    if (value == JOptionPane.YES_OPTION) {
		// On peut sauvegarder avant.
		KMADeAdaptator.saveProjectXML();
	    } else if (value == JOptionPane.CANCEL_OPTION) {
		// Pas de chargement on arr�te.
		System.out
			.println(KMADEConstant.OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME);
		return;
	    }
	}

	File myCurrentFile = KMADEFileChooser.openSPFFile();
	if (myCurrentFile != null) {
	    String message;
	    boolean ioError = false;
	    try {
		KMADeAdaptator.stopAllUpdateThread();
		GraphicEditorAdaptator.cleanAllInterface();
		// On nettoie le mod�le et la partie graphique.
		InterfaceExpressJava.clearCurrentProject();
		KMADeAdaptator.cleanAllAdaptateur();

		InterfaceExpressJava.readOIDFromFile(myCurrentFile);
		message = KMADEConstant.OPEN_EXPRESS_OK_FILE
			+ myCurrentFile.getAbsolutePath();
		ioError = false;
		fileName = myCurrentFile.getAbsolutePath();
		// Activer le bouton Sauvegarde et changer le titre.
		GraphicEditorAdaptator.getMainFrame().enableSaveTitleName(
			fileName);
	    } catch (Exception e) {
		message = KMADEConstant.OPEN_EXPRESS_NO_OK_FILE
			+ e.getLocalizedMessage();
		ioError = true;
		fileName = "";
		GraphicEditorAdaptator.getMainFrame().disableSaveTitleName();
	    }
	    updateThread = true;
	    GraphicEditorAdaptator.getMainFrame().getProgressBarDialog()
		    .goReadSPF(ioError, message);
	}
    }

    /**
     * Fermetture de l'application. A v�rifier si un projet est en cours de le
     * sauvegarder.
     */
    public static void closeApplication() {
	// Avant d'ouvrir demander si le projet courant doit �tre sauvegard�.
	if (InterfaceExpressJava.isBddSet()) {
	    int value = KMADeAdaptator
		    .askToSaveBeforeAction(KMADEConstant.WRITE_BEFORE_EXIT_TOOL_MESSAGE);
	    if (value == JOptionPane.YES_OPTION) {
		// On peut sauvegarder avant.
		KMADeAdaptator.saveProjectXML();
	    } else if (value == JOptionPane.NO_OPTION) {

	    } else if (value == JOptionPane.CANCEL_OPTION) {
		return;
	    }
	} else {
	}
	if (KMADeMain.isAcceptedMedia()) {
	    QTSession.close();
	}
	System.exit(0);
    }

    public static void showAboutDialog() {
	GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
	TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();
	GraphicEditorAdaptator.getMainFrame().getAboutDialog().showAbout();
	GraphicEditorAdaptator.enabledMainFrameAfterEdition();
	TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
    }

    public static void showHistoryDialog() {
	GraphicEditorAdaptator
		.getMainFrame()
		.getInfoHistoryDialog()
		.showInfoAbout(KMADEConstant.HISTORY_TITLE_NAME,
			KMADEConstant.HISTORY_KMADE_HTML);
    }

    public static void showInfoDebugDialog() {
	GraphicEditorAdaptator
		.getMainFrame()
		.getInfoDebugDialog()
		.showInfoAbout(KMADEConstant.INFO_DEBUG_TITLE_NAME,
			KMADEConstant.DEBUG_KMADE_HTML);
    }

    public static void showHelpModelDialog() {
	GraphicEditorAdaptator
		.getMainFrame()
		.getInfoNMDADialog()
		.showInfoAbout(KMADEConstant.HELP_KMAD_TITLE_NAME,
			KMADEConstant.HELP_KMAD_HTML);
    }

    public static void showHelpToolInfoDebugDialog() {
	GraphicEditorAdaptator
		.getMainFrame()
		.getInfoHistoryDialog()
		.showInfoAbout(KMADEConstant.HELP_KMADE_TITLE_NAME,
			KMADEConstant.HELP_KMADE_HTML);
    }

    /**
     * Impl?mente le m?canisme de copier.
     */
    public static void copyAction() {
	GraphicEditorAdaptator.copyAction();
    }

    /**
     * Impl?mente le m?canisme de coller.
     */
    public static void pasteAction() {
	GraphicEditorAdaptator.pasteAction(null);
    }

    /**
     * Impl?mente le m?canisme de coller.
     */
    public static void pasteActionInPoint(Point pt) {
	GraphicEditorAdaptator.pasteAction(pt);
    }

    /**
	 * 
	 */
    public static void cutAction() {
	KMADeAdaptator.copyAction();
	KMADeAdaptator.deleteAction();
    }

    /**
     * Impl?mente le m?canisme de copier.
     */
    public static void deleteAction() {
	// Avant de supprimer une t?che ou un lien, v?rifions si nous sommes
	// bien dans l'?diteur
	// de t?ches.
	if (KMADEMainFrame.isTaskEditorVisible()) {
	    GraphicEditorAdaptator.removeTaskAndEdge();
	}
    }

    public static void showClipBoardOverView() {
	GraphicEditorAdaptator
		.getMainFrame()
		.getClipBoardDialog()
		.setVisible(
			!GraphicEditorAdaptator.getMainFrame()
				.getClipBoardDialog().isVisible());
    }

    public static void showOverviewWindow() {
	GraphicEditorAdaptator.showOverviewWindow();
    }

    private static void setToLocalisation(Locale myLocale) {
	if (!KMADeMain.isTheSameLocale(myLocale)) {
	    if (KMADeAdaptator
		    .doSaveProjectBeforeClose(KMADEConstant.WRITE_BEFORE_CHANGE_LOCALE_MESSAGE)) {
		KMADeMain.loadKMADEResourceBundle(myLocale);
		GraphicEditorAdaptator.notifLocalisationModification();
		TaskPropertiesEnhancedEditorAdaptator
			.notifLocalisationModification();
	    }
	} else {
	}
    }

    public static void setToFrenchLocalisation() {
	KMADeAdaptator.setToLocalisation(new Locale("fr"));
    }

    public static void setToEnglishLocalisation() {
	KMADeAdaptator.setToLocalisation(new Locale("en"));
    }

    public static void stopAllUpdateThread() {
	updateThread = false;
	CoherenceAdaptator.stopCheckTaskModel();
	StatisticAdaptator.stopStatisticTaskModel();
	EntityListAdaptator.stopEntityList();
    }

    public static void cleanAllAdaptateur() {
	AbstractObjectAdaptator.removeAllAbstractObject();
	IntervalAdaptator.removeAllInterval();
	EnumAdaptator.removeAllEnum();
	EnumElementAdaptator.removeAllEnumElement();
	IndividuAdaptator.removeAllIndividu();
	OrganisationAdaptator.removeAllOrganisation();
	MachineAdaptator.removeAllMachine();
	ParcMachinesAdaptator.removeAllParcMachines();
	EventAdaptator.removeAllEvent();
	LabelAdaptator.removeAllLabels();
	ConcreteObjectPanelAdaptator.removeAllConcreteObject();
	GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
    }

    public static void openCoherenceDialog() {
	CoherenceAdaptator.openCoherenceDialog();
    }

    public static int askToSaveBeforeAction(String message) {
	return JOptionPane.showConfirmDialog(
		GraphicEditorAdaptator.getMainFrame(),
		message,
		KMADEConstant.CONFIRMATION_DIALOG_MESSAGE,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		new ImageIcon(GraphicEditorAdaptator.class
			.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
    }
}
