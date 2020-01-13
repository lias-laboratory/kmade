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
package fr.upensma.lias.kmade.tool.viewadaptator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public final class PrePostIterExpressionAdaptator {

	public static int origine = 0;

	public static int PRE_POST_ITER_STATE;

	public static void disabledFrame() {
		GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
		TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();
	}

	public static void editedFromMainFrame() {
		origine = 0;
	}

	public static void editedFromEnhancedFrame() {
		origine = 1;
	}

	public static void enabledFrame() {
		ReadConcreteObjectAdaptator.closeReadConcreteObjectDialog();
		GraphicEditorAdaptator.enabledMainFrameAfterEdition();
		TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
		if (origine == 0) {
			GraphicEditorAdaptator.requestFocus();
		} else {
			TaskPropertiesEnhancedEditorAdaptator.requestFocus();
		}
		GraphicEditorAdaptator.getPanelProp().getEditorPrePostIterDialog().setVisible(false);
		origine = 0;
	}

	public static void initExpression() {
		ReadAbstractObjectAdaptator.initReadAbstractObjectTable();
	}

	public static void setToPreCondition() {
		PRE_POST_ITER_STATE = 1;
		// iln'est pas necessaire d'initialis� la pr�condition � la r�ouverture
		// de la fen�tre (surtout que les combo box ne sont pas mis � jour dans
		// ce cas l�
		// PreconditionAdaptator.initPrecondition();
	}

	public static void setToEffetsDeBord() {
		PRE_POST_ITER_STATE = 2;
		// cf setToPreCondition
		// EffetsDeBordAdaptator.initEffetsDeBord();
		EffetsDeBordAdaptator.initHistory();
	}

	public static void setToIterationCondition() {
		// cf setToPreCondition
		PRE_POST_ITER_STATE = 3;
		// IterationAdaptator.initIteration();
	}

	public static void finishExpressionEdition() {
		if (PRE_POST_ITER_STATE == 2 && EffetsDeBordAdaptator.isAnyHistory()) {
			int value = JOptionPane.showConfirmDialog(
					GraphicEditorAdaptator.getPanelProp().getEditorPrePostIterDialog(),
					KMADEConstant.EXIT_WITHOUT_SAVE_MESSAGE, KMADEConstant.CONFIRMATION_DIALOG_MESSAGE,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
			if (value == JOptionPane.NO_OPTION) {
				return;
			} else {
				EffetsDeBordAdaptator.clearAllHistory();
			}
		}

		PrePostIterExpressionAdaptator.enabledFrame();
		switch (PRE_POST_ITER_STATE) {
		case 1: {
			PreconditionAdaptator.setPrecondition();
			break;
		}
		case 2: {
			EffetsDeBordAdaptator.setEffetsDeBord();
			break;
		}
		case 3: {
			IterationAdaptator.setIteration();
			break;
		}
		}
	}

	public static void setNewToken(String value) {
		switch (PRE_POST_ITER_STATE) {
		case 1:
			PreconditionAdaptator.setNewToken(value);
			break;
		case 2:
			EffetsDeBordAdaptator.setNewToken(value);
			break;
		case 3:
			IterationAdaptator.setNewToken(value);
			break;
		}
	}
}
