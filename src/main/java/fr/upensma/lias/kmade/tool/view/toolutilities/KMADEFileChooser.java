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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.awt.Window;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public final class KMADEFileChooser {

	private static File lastParentFile = null;

	public static String saveKMADModelSimpleFile() {
		final JFileChooser fc = new JFileChooser();
		File file = null;
		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.SimpleTxtSaveFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showSaveDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();

			if (file == null) {
				KMADEHistoryMessageManager.printlnMessage("kmc error");
				return null;
			} else {
				if (file.exists()) {
					int response = JOptionPane.showConfirmDialog(null, KMADEConstant.OVERWRITE_FILE_MESSAGE,
							KMADEConstant.SAVE_AS_MESSAGE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(KMADEFileChooser.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
					if (response == JOptionPane.CANCEL_OPTION)
						return null;
				}

				if (!file.getAbsolutePath().endsWith(".txt")) {
					file = new File(fc.getSelectedFile().getAbsolutePath() + ".txt");
				}
			}
		} else {
			KMADEHistoryMessageManager.printlnMessage("KMC Message");
			return null;
		}
		lastParentFile = file.getParentFile();
		return file.getAbsolutePath();
	}

	public static File openKMADModelXMLFile() {
		final JFileChooser fc = new JFileChooser();
		File file = null;
		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.KMADModelXMLOpenFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			if (file == null || file == lastParentFile) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_ERROR_FILE);
				return null;
			} else {
				if (!file.exists()) {
					KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_ERROR_FILE);
					return null;
				}
			}
		} else
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME);

		if (file != null)
			lastParentFile = file.getParentFile();
		return file;
	}

	public static String saveKMADModelXMLFile() {
		final JFileChooser fc = new JFileChooser();
		File file = null;
		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.KMADModelXMLSaveFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showSaveDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();

			if (file == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_ERROR_FILE);
				return null;
			} else {
				if (file.exists()) {
					int response = JOptionPane.showConfirmDialog(null, KMADEConstant.OVERWRITE_FILE_MESSAGE,
							KMADEConstant.SAVE_AS_MESSAGE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(KMADEFileChooser.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
					if (response == JOptionPane.CANCEL_OPTION)
						return null;
				}

				if (!file.getAbsolutePath().endsWith(KMADEConstant.KMADE_MODEL_EXTENSION_FILE)) {
					file = new File(fc.getSelectedFile().getAbsolutePath() + KMADEConstant.KMADE_MODEL_EXTENSION_FILE);
				}
			}
		} else {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.SAVE_CANCELLED_EXPRESS_FILECHOOSER_NAME);
			return null;
		}
		lastParentFile = file.getParentFile();
		return file.getAbsolutePath();
	}

	/**
	 * M??thode qui ouvre un JFileChooser pour le chargement d'un sc??nario
	 * 
	 * @return
	 */
	public static File openKMADScenarioFile() {
		final JFileChooser fc = new JFileChooser();
		File file = null;

		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.ScenarioOpenFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			if (file == null || file == lastParentFile) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_ERROR_FILE);
				return null;
			}
		} else
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_CANCELLED_SCENARIO_FILECHOOSER_NAME);
		lastParentFile = file.getParentFile();
		return file;
	}

	/**
	 * M??thode qui enregistre le mod??le de t??ches.
	 * 
	 * @return
	 */
	public static String saveKMADScenarioFile() {
		final JFileChooser fc = new JFileChooser();
		File file = null;

		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.ScenarioSaveFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showSaveDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();

			if (file == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_ERROR_FILE);
				return null;
			} else {
				if (file.exists()) {
					int response = JOptionPane.showConfirmDialog(null, KMADEConstant.OVERWRITE_FILE_MESSAGE,
							KMADEConstant.SAVE_AS_MESSAGE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(KMADEFileChooser.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
					if (response == JOptionPane.CANCEL_OPTION)
						return null;
				}

				if (!file.getAbsolutePath().endsWith(KMADEConstant.KMADE_SCENARIO_EXTENSION_FILE)) {
					file = new File(
							fc.getSelectedFile().getAbsolutePath() + KMADEConstant.KMADE_SCENARIO_EXTENSION_FILE);
				}
			}
		} else {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.SAVE_CANCELLED_SCENARIO_FILECHOOSER_NAME);
			return null;
		}
		lastParentFile = file.getParentFile();
		return file.getAbsolutePath();
	}

	/**
	 * M??thode qui ouvre un JFileChooser pour le chargement d'un fichier SPF.
	 * 
	 * @return
	 */
	public static File openSPFFile() {
		final JFileChooser fc = new JFileChooser();
		File file = null;

		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.SPFOpenFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			if (file == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_ERROR_FILE);
				return null;
			}
		} else
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME);
		lastParentFile = file.getParentFile();
		return file;
	}

	public static File saveTxtFile(Window searchWindow) {
		final JFileChooser fc = new JFileChooser();
		File file = null;

		fc.setCurrentDirectory(lastParentFile);
		fc.addChoosableFileFilter(new KMADEFileChooser.TXTSaveFilter());
		fc.setAcceptAllFileFilterUsed(false);

		int returnVal = fc.showSaveDialog(searchWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// V???rifie si le nom du fichier contient l'extension "txt".
			file = fc.getSelectedFile();

			if (file == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_TXT_FILE_ERROR);
				return null;
			} else {
				if (file.exists()) {
					/* int response = */
					JOptionPane.showConfirmDialog(fc, KMADEConstant.OVERWRITE_FILE_MESSAGE,
							KMADEConstant.SAVE_AS_MESSAGE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(KMADEFileChooser.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
				}

				if (!file.getAbsolutePath().endsWith(".txt")) {
					file = new File(fc.getSelectedFile().getAbsolutePath() + ".txt");
				}
			}
		} else {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.SAVE_CANCELLED_TEXT_FILECHOOSER_NAME);
		}
		lastParentFile = file.getParentFile();
		return file;
	}

	private static class KMADModelXMLOpenFilter extends FileFilter {
		public boolean accept(File f) {
			if (f != null) {
				if (f.isDirectory()) {
					return f.getName().equals("CVS") ? false : true;
				} else
					return (f.getName().endsWith(KMADEConstant.KMADE_MODEL_EXTENSION_FILE));
			}
			return false;
		}

		public String getDescription() {
			return KMADEConstant.EXTENSION_KMADE_FILTER_NAME;
		}
	}

	private static class KMADModelXMLSaveFilter extends FileFilter {
		public boolean accept(File f) {
			return true;
		}

		public String getDescription() {
			return KMADEConstant.EXTENSION_KMADE_FILTER_NAME;
		}
	}

	private static class ScenarioSaveFilter extends FileFilter {
		public boolean accept(File f) {
			return true;
		}

		public String getDescription() {
			return KMADEConstant.SCENARIO_FILTER_NAME;
		}
	}

	private static class ScenarioOpenFilter extends FileFilter {
		public boolean accept(File f) {
			if (f != null) {
				if (f.isDirectory()) {
					return f.getName().equals("CVS") ? false : true;
				} else
					return (f.getName().endsWith(KMADEConstant.KMADE_SCENARIO_EXTENSION_FILE));
			}
			return false;
		}

		public String getDescription() {
			return KMADEConstant.SCENARIO_FILTER_NAME;
		}
	}

	private static class SPFOpenFilter extends FileFilter {
		public boolean accept(File f) {
			if (f != null) {
				if (f.isDirectory()) {
					return f.getName().equals("CVS") ? false : true;
				} else
					return (f.getName().endsWith(".spf"));
			}
			return false;
		}

		public String getDescription() {
			return KMADEConstant.EXTENSION_EXPRESS_FILTER_NAME;
		}
	}

	private static class TXTSaveFilter extends FileFilter {
		public boolean accept(File f) {
			return true;
		}

		public String getDescription() {
			return KMADEConstant.EXTENSION_TEXT_FILTER_SEARCH_NAME;
		}
	}

	private static class SimpleTxtSaveFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			if (f != null) {
				if (f.isDirectory()) {
					return f.getName().equals("CVS") ? false : true;
				} else
					return (f.getName().endsWith(".txt"));
			}
			return false;

		}

		@Override
		public String getDescription() {
			return KMADEConstant.EXTENSION_TEXT_FILTER_SEARCH_NAME;
		}

	}
}
