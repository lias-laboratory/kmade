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

import fr.upensma.lias.kmade.tool.view.taskproperties.KMADEEditorPrePostIterDialog;

/**
 * Class to manage the view in the objects view dialog
 * @author Joachim TROUVERIE
 */
public final class ObjectDialogViewAdaptator {

    /**
     * Open the view
     */
    public static void openObjectViewDialog() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.setVisible(true);
    }

    /**
     * Close the view
     */
    public static void closeObjectViewDialog() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.setVisible(false);
    }

    /**
     * Update the objects
     */
    public static void updateObjectViewDialog() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView().update();
    }

    /**
     * Create a new abstract object
     */
    public static void createAbstractObject() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.createAbstractObject();
	updateTaskPropPanel();
    }

    /**
     * Remove the selected abstract object
     */
    public static void removeAbstractObject() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.removeAbstractObject();
	hideAbstractObjectTable();
	updateTaskPropPanel();
    }

    /**
     * Add an attribute to the selected abstract object
     */
    public static void addAttribute() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.addAttribute();
	updateTaskPropPanel();
    }

    /**
     * Remove the selected attribute
     */
    public static void removeAttribute() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.removeAttribute();
	updateTaskPropPanel();
    }

    /**
     * Create a new group
     * @param Type of new group
     */
    public static void createNewGroup(String instance) {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.createNewGroup(instance);
    }

    /**
     * Remove the selected concrete object or 
     * the selected group
     */
    public static void removeObject() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.removeObject();
	checkSaveStatus();
    }

    /**
     * Edit the selected concrete object
     * or the selected group and the concrete object contained in it
     */
    public static void editObject() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.editObject();
    }

    /**
     * Undo the last removing action or the last abstract object creation
     */
    public static void undo() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView().undo();
	updateTaskPropPanel();
    }

    /**
     * Set if the add attribute action is enabled
     * @param true or false
     */
    public static void setAddAttributeEnabled(boolean condition) {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		.getToolBar().setAddAttributeEnabled(condition);
    }
    
    /**
     * Set if the edit action is enabled
     * @param true or false
     */
    public static void setEditGroupEnabled(boolean condition) {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
	.getToolBar().setEditGroupEnabled(condition);
    }
    
    /**
     * Show the table to edit abstract object
     */
    public static void showAbstractObjectTable() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView().getAbstractObjectPanel().showTable();
	setAddAttributeEnabled(true);
    }
    
    /**
     * Hide the table to edit abstract object
     */
    public static void hideAbstractObjectTable() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView().getAbstractObjectPanel().hideTable();
	setAddAttributeEnabled(false);
    }
    
    /**
     * Enable the different action to manage concrete objects and groups
     */
    public static void setConcreteObjectEnabled() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
	.getToolBar().setConcreteObjectEnabled();
	hideAbstractObjectTable();
    }
    
    /**
     * Enable the different action to manage abstract objects
     */
    public static void setAbstractObjectEnabled() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView().getToolBar().setAbstractObjectEnabled();
    }
    
    /**
     * Send the last modifications managed in object dialog view to the precondition
     * postcondition and iteration dialog view
     */
    public static void updateTaskPropPanel() {
	if (KMADEEditorPrePostIterDialog.getPreconditionPanel().isVisible()
		|| KMADEEditorPrePostIterDialog.getIterationPanel().isVisible()
		|| KMADEEditorPrePostIterDialog.getPostonditionPanel()
			.isVisible()) {
	    ReadAbstractObjectAdaptator.initReadAbstractObjectTable();
	}
	AbstractObjectAdaptator.removeAllAbstractObject();
	AbstractObjectAdaptator.updateAbstractObjectView();
    }
    /**
     * Check if all the concrete objects are in a group to allow the user to save
     */
    public static void checkSaveStatus() {
	GraphicEditorAdaptator.getMainFrame().getObjectDialogView().checkSaveStatus();
    }
}
