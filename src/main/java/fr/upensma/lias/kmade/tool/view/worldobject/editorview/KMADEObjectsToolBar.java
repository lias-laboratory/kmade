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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.ObjectDialogViewAdaptator;

/**
 * Class for the tool bar representation of the different action that could be made on objects.
 * 
 * @author Joachim TROUVERIE
 */
public class KMADEObjectsToolBar extends JToolBar {

    private static final long serialVersionUID = -3774159363154430564L;

    public AbstractAction newAbstractObject, removeAbstractObject,
	    addAttribute, removeAttribute, cancel, newStack, newSet, newList,
	    newUnique, newArray, editConcrete, deleteConcrete;

    public static final ImageIcon ADD_ABSTRACT_OBJECT = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.ADD_OBJECT_IMAGE));

    public static final ImageIcon REMOVE_ABSTRACT_OBJECT = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.REMOVE_OBJECT_IMAGE));

    public static final ImageIcon REMOVE_ATTRIBUTE = new ImageIcon(
	    KMADEObjectsToolBar.class.getResource(KMADEConstant.DELETE_IMAGE));

    public static final ImageIcon ADD_ATTRIBUTE = new ImageIcon(
	    KMADEObjectsToolBar.class.getResource(KMADEConstant.ADD_IMAGE));

    public static final ImageIcon CANCEL = new ImageIcon(
	    KMADEObjectsToolBar.class.getResource(KMADEConstant.CANCEL_IMAGE));

    public static final ImageIcon NEW_STACK = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.GROUP_STACK_IMAGE));

    public static final ImageIcon NEW_SET = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.GROUP_SET_IMAGE));

    public static final ImageIcon NEW_LIST = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.GROUP_LIST_IMAGE));

    public static final ImageIcon NEW_UNIQUE = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.GROUP_SINGLETON_IMAGE));

    public static final ImageIcon NEW_ARRAY = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.GROUP_ARRAY_IMAGE));

    public static final ImageIcon EDIT_GROUP_IMAGE = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.EDIT_GROUP_IMAGE));

    public static final ImageIcon DELETE_GROUP_IMAGE = new ImageIcon(
	    KMADEObjectsToolBar.class
		    .getResource(KMADEConstant.DELETE_GROUP_IMAGE));
    

    public KMADEObjectsToolBar() {
	super();
	this.setPreferredSize(new Dimension(460, 43));
	this.setFloatable(false);

	// ABSTRACT OBJECT
	// New abstract Object
	this.newAbstractObject = new AbstractAction(
		KMADEConstant.NEW_ABSTRACT_OBJECT_TEXT,
		KMADEObjectsToolBar.ADD_ABSTRACT_OBJECT) {

	    private static final long serialVersionUID = 7569464133407669095L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.createAbstractObject();
	    }
	};
	this.newAbstractObject.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.NEW_ABSTRACT_OBJECT_TEXT);
	this.newAbstractObject.setEnabled(false);
	this.add(newAbstractObject);

	// Remove Abstract Object
	this.removeAbstractObject = new AbstractAction(
		KMADEConstant.REMOVE_ABSTRACT_OBJECT_TEXT,
		KMADEObjectsToolBar.REMOVE_ABSTRACT_OBJECT) {

	    private static final long serialVersionUID = -3036488438087990523L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.removeAbstractObject();
	    }
	};
	this.removeAbstractObject.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.REMOVE_ABSTRACT_OBJECT_TEXT);
	this.removeAbstractObject.setEnabled(false);
	this.add(removeAbstractObject);

	this.addSeparator();

	// Add an attribute
	this.addAttribute = new AbstractAction(KMADEConstant.ADD_ATTRIBUTE,
		KMADEObjectsToolBar.ADD_ATTRIBUTE) {

	    private static final long serialVersionUID = -7918217042319157313L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.addAttribute();
	    }
	};
	this.addAttribute.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ADD_ATTRIBUTE);
	this.addAttribute.setEnabled(false);
	this.add(addAttribute);

	// Remove the attribute
	this.removeAttribute = new AbstractAction(KMADEConstant.ADD_ATTRIBUTE,
		KMADEObjectsToolBar.REMOVE_ATTRIBUTE) {

	    private static final long serialVersionUID = -3031902031571442538L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.removeAttribute();
	    }
	};
	this.removeAttribute.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.REMOVE_ATTRIBUTE);
	this.removeAttribute.setEnabled(false);
	this.add(removeAttribute);

	this.addSeparator();

	// CONCRETE OBJECT
	// New stack
	this.newStack = new AbstractAction(
		KMADEConstant.NEW_STACK_ACTION_MESSAGE,
		KMADEObjectsToolBar.NEW_STACK) {

	    private static final long serialVersionUID = -1502451878251306472L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.createNewGroup(ExpressConstant.STACK_NAME);
	    }
	};
	this.newStack.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.NEW_STACK_ACTION_MESSAGE);
	this.newStack.setEnabled(false);
	this.add(newStack);

	// New set
	this.newSet = new AbstractAction(KMADEConstant.NEW_SET_ACTION_MESSAGE,
		KMADEObjectsToolBar.NEW_SET) {

	    private static final long serialVersionUID = -6908873830714517270L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.createNewGroup(ExpressConstant.SET_NAME);
	    }
	};
	this.newSet.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.NEW_SET_ACTION_MESSAGE);
	this.newSet.setEnabled(false);
	this.add(newSet);

	// New array
	this.newArray = new AbstractAction(
		KMADEConstant.NEW_ARRAY_ACTION_MESSAGE,
		KMADEObjectsToolBar.NEW_ARRAY) {

	    private static final long serialVersionUID = -2571976202894542778L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.createNewGroup(ExpressConstant.ARRAY_NAME);
	    }
	};
	this.newArray.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.NEW_ARRAY_ACTION_MESSAGE);
	this.newArray.setEnabled(false);
	this.add(newArray);

	// New list
	this.newList = new AbstractAction(
		KMADEConstant.NEW_LIST_ACTION_MESSAGE,
		KMADEObjectsToolBar.NEW_LIST) {

	    private static final long serialVersionUID = 9085057103468420580L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.createNewGroup(ExpressConstant.LIST_NAME);
	    }
	};
	this.newList.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.NEW_LIST_ACTION_MESSAGE);
	this.newList.setEnabled(false);
	this.add(newList);

	// New singleton
	this.newUnique = new AbstractAction(
		KMADEConstant.NEW_SINGLETON_ACTION_MESSAGE,
		KMADEObjectsToolBar.NEW_UNIQUE) {

	    private static final long serialVersionUID = 1974601767047187441L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.createNewGroup(ExpressConstant.SINGLETON_NAME);
	    }
	};
	this.newUnique.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.NEW_SINGLETON_ACTION_MESSAGE);
	this.newUnique.setEnabled(false);
	this.add(newUnique);

	this.addSeparator();

	// Edit Group and concrete object
	this.editConcrete = new AbstractAction(
		KMADEConstant.EDIT_GROUP_AND_CONCRETE_ACTION_MESSAGE,
		KMADEObjectsToolBar.EDIT_GROUP_IMAGE) {

	    private static final long serialVersionUID = 1974601767047187441L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.editObject();
	    }
	};
	this.editConcrete.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.EDIT_GROUP_AND_CONCRETE_ACTION_MESSAGE);
	this.editConcrete.setEnabled(false);
	this.add(editConcrete);

	this.deleteConcrete = new AbstractAction(
		KMADEConstant.DELETE_GROUP_AND_CONCRETE_ACTION_MESSAGE,
		KMADEObjectsToolBar.DELETE_GROUP_IMAGE) {

	    private static final long serialVersionUID = -8068881566266996286L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.removeObject();
	    }
	};
	this.deleteConcrete.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.DELETE_GROUP_AND_CONCRETE_ACTION_MESSAGE);
	this.deleteConcrete.setEnabled(false);
	this.add(deleteConcrete);

	this.addSeparator();

	// Cancel
	this.cancel = new AbstractAction(KMADEConstant.CANCEL_MESSAGE,
		KMADEObjectsToolBar.CANCEL) {

	    private static final long serialVersionUID = -5211270054395649529L;

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ObjectDialogViewAdaptator.undo();
	    }
	};
	this.cancel.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.CANCEL_MESSAGE);
	this.cancel.setEnabled(false);
	this.add(cancel);

    }

    public void setAbstractObjectEnabled() {
	this.addAttribute.setEnabled(false);
	this.removeAttribute.setEnabled(false);
	this.removeAbstractObject.setEnabled(true);
	this.newAbstractObject.setEnabled(true);
	this.cancel.setEnabled(true);
	//
	this.newArray.setEnabled(false);
	this.newList.setEnabled(false);
	this.newSet.setEnabled(false);
	this.newStack.setEnabled(false);
	this.newUnique.setEnabled(false);
	this.editConcrete.setEnabled(false);
	this.deleteConcrete.setEnabled(false);
    }

    public void setConcreteObjectEnabled() {
	this.addAttribute.setEnabled(false);
	this.removeAbstractObject.setEnabled(false);
	this.removeAttribute.setEnabled(false);
	this.newAbstractObject.setEnabled(false);
	//
	this.newArray.setEnabled(true);
	this.newList.setEnabled(true);
	this.newSet.setEnabled(true);
	this.newStack.setEnabled(true);
	this.newUnique.setEnabled(true);
	this.cancel.setEnabled(true);
	this.editConcrete.setEnabled(false);
	this.deleteConcrete.setEnabled(false);
    }

    public void setAddAttributeEnabled(boolean condition) {
	this.addAttribute.setEnabled(condition);
	this.removeAttribute.setEnabled(condition);
    }

    public void setEditGroupEnabled(boolean condition) {
	this.editConcrete.setEnabled(condition);
	this.deleteConcrete.setEnabled(condition);
    }

}
