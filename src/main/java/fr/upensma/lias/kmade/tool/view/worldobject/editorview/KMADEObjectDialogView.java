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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphCell;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.ExpressDB;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AgregatStructure;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ListeAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.PileAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractAttribut;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressGroup;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.AbstractObjectPanel.DescriptionTableModel;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell.AbstractObjectCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ArrayCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ConcreteObjectCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ListCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SetCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SingletonCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.StackCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.ObjectDefaultGraphCell;
import fr.upensma.lias.kmade.tool.viewadaptator.ObjectDialogViewAdaptator;

/**
 * Class allowing the user to create and manage objects and groups in KMADe.
 * 
 * @author Joachim TROUVERIE
 */
public class KMADEObjectDialogView extends JDialog {

	private static final long serialVersionUID = -591945642399299721L;

	private JTabbedPane tabs;

	private KMADEObjectsToolBar toolBar;

	private JLabel saveMessage;

	private AbstractObjectPanel abstractObjectPanel;

	private ConcreteObjectPanel concreteObjectPanel;

	private ExpressDB bdd;

	// For the undo
	private ObjectDefaultGraphCell lastCreated, lastRemoved;

	private AttributAbstrait attRemoved;

	private ConcreteObjectCell objectRemoved;

	private GroupDefaultGraphCell groupRemoved;

	/**
	 * Constructor for the window
	 * 
	 * @param owner
	 * @param name
	 * @param if    the window is modal
	 */
	public KMADEObjectDialogView(JFrame owner) {
		super(owner, KMADEConstant.OBJECTS_VISUALIZATION_WINDOW_NAME, false);
		this.getContentPane().setLayout(new BorderLayout());

		// For the undo
		lastCreated = null;
		lastRemoved = null;
		attRemoved = null;
		groupRemoved = null;

		this.bdd = InterfaceExpressJava.bdd;

		List<ObjetAbstrait> abstracts = new ArrayList<ObjetAbstrait>();
		Set<Oid> set = bdd.keySet();
		Iterator<Oid> i;
		for (i = set.iterator(); i.hasNext();) {
			Oid oid = (Oid) i.next();
			if (bdd.prendre(oid) instanceof ObjetAbstrait)
				abstracts.add((ObjetAbstrait) bdd.prendre(oid));
		}

		this.toolBar = new KMADEObjectsToolBar();
		this.toolBar.setAbstractObjectEnabled();
		this.getContentPane().add(toolBar, BorderLayout.PAGE_START);

		this.tabs = new JTabbedPane();
		this.tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		abstractObjectPanel = new AbstractObjectPanel(bdd);
		tabs.addTab(KMADEConstant.ABSTRACT_OBJECT_TITLE_NAME, this.abstractObjectPanel);

		concreteObjectPanel = new ConcreteObjectPanel(bdd);
		tabs.addTab(KMADEConstant.CONCRETE_OBJECT_TITLE_NAME, this.concreteObjectPanel);

		tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabs.getSelectedComponent().equals(concreteObjectPanel)) {
					ObjectDialogViewAdaptator.setConcreteObjectEnabled();
				} else
					ObjectDialogViewAdaptator.setAbstractObjectEnabled();
			}
		});

		this.getContentPane().add(tabs, BorderLayout.CENTER);

		this.saveMessage = new JLabel();
		checkSaveStatus();
		this.getContentPane().add(this.saveMessage, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(800, 700));
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ObjectDialogViewAdaptator.closeObjectViewDialog();
			}
		});

		this.setModal(true);
		this.setResizable(true);
		this.pack();
		KMADEToolUtilities.setCenteredInScreen(this);
	}

	/**
	 * @return the tool bar
	 */
	public KMADEObjectsToolBar getToolBar() {
		return this.toolBar;
	}

	/**
	 * @return the panel to manage the groups and the concrete objects
	 */
	public ConcreteObjectPanel getConcreteObjectPanel() {
		return this.concreteObjectPanel;
	}

	/**
	 * Method to get the panel to manage the abstract objects
	 * 
	 * @return the panel to manage the abstract objects
	 */
	public AbstractObjectPanel getAbstractObjectPanel() {
		return this.abstractObjectPanel;
	}

	// Actions
	/**
	 * Method to create a new abstract object
	 */
	public void createAbstractObject() {
		String oid = ExpressAbstractObject.createAbstractObject();
		ObjetAbstrait o = ExpressAbstractObject.getObjetAbstrait(oid);
		o.setName(KMADEConstant.NEW_OBJECT_NAME);
		// Set the position of the cell in abstract object panel
		int x = abstractObjectPanel.getGraph().getWidth() / 2;
		int y = abstractObjectPanel.getGraph().getHeight() / 2;

		CellView[] allViews = abstractObjectPanel.getGraph().getGraphLayoutCache().getAllViews();
		for (int i = allViews.length - 1; i >= 0; i--) {
			if (allViews[i].getBounds().contains(x, y)) {
				x += 10;
				y += 10;
			}
		}

		AbstractObjectCell cell = new AbstractObjectCell(o, x, y);
		abstractObjectPanel.getGraph().getGraphLayoutCache().insert(cell);

		// Set the position in concrete object panel
		JGraph concreteObjectPanelGraph = concreteObjectPanel.getConcreteObjectGraphCreation();
		x = 0;
		y = 0;
		allViews = concreteObjectPanelGraph.getGraphLayoutCache().getAllViews();
		for (int i = allViews.length - 1; i >= 0; i--) {
			if (allViews[i].getBounds().contains(x, y)) {
				y += allViews[i].getBounds().getHeight() + 10;
			}
		}
		AbstractObjectCell cell2 = new AbstractObjectCell(o, x, y);
		concreteObjectPanelGraph.getGraphLayoutCache().insert(cell2);

		// Select the last created
		abstractObjectPanel.getGraph().clearSelection();
		abstractObjectPanel.getGraph().setSelectionCell(cell);
		abstractObjectPanel.setSelected(cell);

		((DescriptionTableModel) abstractObjectPanel.getTable().getModel()).clearTable();
		((DescriptionTableModel) abstractObjectPanel.getTable().getModel())
				.writeDetails((ObjetAbstrait) cell.getObject());
		ObjectDialogViewAdaptator.showAbstractObjectTable();

		// For the undo
		lastCreated = cell;
		lastRemoved = null;
		attRemoved = null;
		groupRemoved = null;
		objectRemoved = null;
	}

	/**
	 * Method to remove the abstract object selected
	 */
	public void removeAbstractObject() {
		if (abstractObjectPanel.getSelected() != null) {

			// For the undo
			lastRemoved = abstractObjectPanel.getSelected();
			lastCreated = null;
			attRemoved = null;
			groupRemoved = null;
			objectRemoved = null;

			String message = "Etes vous s�r de vouloir supprimer " + abstractObjectPanel.getSelected().getName() + "\n";
			if (!(((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getInverseAttributsAbs().isEmpty())) {
				message += KMADEConstant.ABSTRACT_ATTRIBUT_REMOVE_MESSAGE_TITLE + " : ";
				for (AttributAbstrait a : ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject())
						.getInverseAttributsAbs()) {
					message += a.getName() + " ";
				}
				message += "\n";
			}
			if (!(((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getInverseGroupe().isEmpty())) {
				message += KMADEConstant.ABSTRACT_GROUP_REMOVE_MESSAGE_TITLE + " : ";
				for (Groupe g : ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getInverseGroupe()) {
					message += g.getName() + " ";
				}
				message += "\n";
			}
			if (!(((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getInverseObjConc().isEmpty())) {
				message += KMADEConstant.CONCRETE_OBJECT_REMOVE_MESSAGE_TITLE + " : ";
				for (ObjetConcret o : ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject())
						.getInverseObjConc()) {
					message += o.getName() + " ";
				}
				message += "\n";
			}

			int answer = JOptionPane.showConfirmDialog(KMADEObjectDialogView.this, message,
					KMADEConstant.ABSTRACT_OBJECT_REMOVE_MESSAGE_TITLE, JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				// Remove for the concrete object creation panel
				JGraph concreteObjectCreationGraph = concreteObjectPanel.getConcreteObjectGraphCreation();
				CellView[] allViews = concreteObjectCreationGraph.getGraphLayoutCache().getAllViews();
				for (int i = allViews.length - 1; i >= 0; i--) {
					if (((AbstractObjectCell) allViews[i].getCell()).getObject()
							.equals(abstractObjectPanel.getSelected().getObject())) {
						Object[] cells = { (AbstractObjectCell) allViews[i].getCell() };
						concreteObjectCreationGraph.getGraphLayoutCache().remove(cells);
					}
				}

				// Remove for the abstract object panel
				Object[] cells = { abstractObjectPanel.getSelected() };
				abstractObjectPanel.getGraph().getGraphLayoutCache().remove(cells);

				// Attributes
				for (AttributAbstrait a : ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject())
						.getInverseAttributsAbs()) {
					InterfaceExpressJava.remove(a.getOid());
					for (AttributConcret conc : a.getUtiliseParAttr()) {
						InterfaceExpressJava.remove(conc.getOid());
					}
				}

				// Concrete objects
				for (ObjetConcret o : ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject())
						.getInverseObjConc()) {
					InterfaceExpressJava.remove(o.getPoint().getOid());
					InterfaceExpressJava.remove(o.getOid());
					concreteObjectCreationGraph = concreteObjectPanel.getConcreteObjectGraph();
					allViews = concreteObjectCreationGraph.getGraphLayoutCache().getAllViews();
					for (int i = allViews.length - 1; i >= 0; i--) {
						if (allViews[i].getCell() instanceof ObjectDefaultGraphCell
								&& ((ObjectDefaultGraphCell) allViews[i].getCell()).getObject().equals(o)) {
							Object[] cell = { (ObjectDefaultGraphCell) allViews[i].getCell() };
							concreteObjectCreationGraph.getGraphLayoutCache().remove(cell);
						}
					}

				}

				// Groups
				for (Groupe g : ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getInverseGroupe()) {
					InterfaceExpressJava.remove(g.getPoint().getOid());
					InterfaceExpressJava.remove(g.getOid());
					concreteObjectCreationGraph = concreteObjectPanel.getConcreteObjectGraph();
					allViews = concreteObjectCreationGraph.getGraphLayoutCache().getAllViews();
					for (int i = allViews.length - 1; i >= 0; i--) {
						if (allViews[i].getCell() instanceof GroupDefaultGraphCell
								&& ((GroupDefaultGraphCell) allViews[i].getCell()).getObject().equals(g)) {
							Object[] object = { (GroupDefaultGraphCell) allViews[i].getCell() };
							concreteObjectCreationGraph.getGraphLayoutCache().remove(object);
						}
					}
				}

				InterfaceExpressJava.remove(((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getOid());

			}

		} else
			return;
	}

	/**
	 * Method to add a new abstract attribute for the selected abstract object
	 */
	public void addAttribute() {
		ObjetAbstrait o = (ObjetAbstrait) abstractObjectPanel.getSelected().getObject();
		String oidAttAbs = ExpressAbstractAttribut.createAbstractAttribut(o.getOid());
		AttributAbstrait newAtt = (AttributAbstrait) InterfaceExpressJava.bdd.prendre(new Oid(oidAttAbs));

		((DescriptionTableModel) abstractObjectPanel.getTable().getModel()).addObjectAttribute("",
				TypeStructure.getEnumereIntoLocaleTypeStructure(newAtt.getTypeStructure().getValue()), "");
		((DescriptionTableModel) abstractObjectPanel.getTable().getModel()).addNewRow("", "");
	}

	/**
	 * Method to remove an abstract attribute to the selected abstract object
	 */
	public void removeAttribute() {
		int[] indexes = abstractObjectPanel.getTable().getSelectedRows();
		int index = (indexes[0] - 4) / 3;
		attRemoved = ((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).getInverseAttributsAbs()
				.get(index);
		((ObjetAbstrait) abstractObjectPanel.getSelected().getObject()).removeInverseAttributsAbs(attRemoved);
		((DescriptionTableModel) abstractObjectPanel.getTable().getModel()).clearTable();
		((DescriptionTableModel) abstractObjectPanel.getTable().getModel())
				.writeDetails((ObjetAbstrait) abstractObjectPanel.getSelected().getObject());
		abstractObjectPanel.getGraph().getGraphLayoutCache().editCell(abstractObjectPanel.getSelected(),
				abstractObjectPanel.getSelected().getAttributes());

		// For the undo
		lastCreated = null;
		lastRemoved = null;
		groupRemoved = null;
		objectRemoved = null;

	}

	/**
	 * To undo the last removing action
	 */
	public void undo() {
		if (lastCreated != null) {
			Object[] cells = { lastCreated };
			abstractObjectPanel.getGraph().getGraphLayoutCache().remove(cells);
			lastCreated = null;

		} else if (attRemoved != null) {
			ObjetAbstrait o = (ObjetAbstrait) InterfaceExpressJava.prendre(attRemoved.getUtiliseParClasse().getOid());
			o.addInverseAttributsAbs(attRemoved);
			((DescriptionTableModel) abstractObjectPanel.getTable().getModel()).clearTable();
			((DescriptionTableModel) abstractObjectPanel.getTable().getModel())
					.writeDetails((ObjetAbstrait) abstractObjectPanel.getSelected().getObject());
			abstractObjectPanel.getGraph().getGraphLayoutCache().editCell(abstractObjectPanel.getSelected(),
					abstractObjectPanel.getSelected().getAttributes());
			attRemoved = null;

		} else if (lastRemoved != null) {
			InterfaceExpressJava.mettre(((ObjetAbstrait) lastRemoved.getObject()).getOid(),
					(ObjetAbstrait) lastRemoved.getObject());
			InterfaceExpressJava.mettre(((ObjetAbstrait) lastRemoved.getObject()).getPoint().getOid(),
					((ObjetAbstrait) lastRemoved.getObject()).getPoint());
			// Abstract attributes
			for (AttributAbstrait a : ((ObjetAbstrait) lastRemoved.getObject()).getInverseAttributsAbs()) {
				InterfaceExpressJava.mettre(a.getOid(), a);
				// Concrete attributes
				for (AttributConcret conc : a.getUtiliseParAttr()) {
					InterfaceExpressJava.mettre(conc.getOid(), conc);
				}
			}

			// Concrete object
			for (ObjetConcret o : ((ObjetAbstrait) lastRemoved.getObject()).getInverseObjConc()) {
				InterfaceExpressJava.mettre(o.getOid(), o);
				InterfaceExpressJava.mettre(o.getPoint().getOid(), o.getPoint());
				if (o.getAppartientGroupe() == null)
					concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache()
							.insert(new ConcreteObjectCell(o, o.getPoint()));

			}

			// Groups
			for (Groupe g : ((ObjetAbstrait) lastRemoved.getObject()).getInverseGroupe()) {
				InterfaceExpressJava.mettre(g.getOid(), g);
				InterfaceExpressJava.mettre(g.getPoint().getOid(), g.getPoint());
				GroupDefaultGraphCell group;
				if (g.getEnsemble() instanceof PileAg)
					group = new StackCell(g, g.getPoint());
				else if (g.getEnsemble() instanceof EnsembleAg)
					group = new SetCell(g, g.getPoint());
				else if (g.getEnsemble() instanceof ListeAg)
					group = new ListCell(g, g.getPoint());
				else if (g.getEnsemble() instanceof TableauAg)
					group = new ArrayCell(g, g.getPoint());
				else
					group = new SingletonCell(g, g.getPoint());

				for (ObjetConcret o : g.getEnsemble().getLstObjConcrets()) {
					group.addCellToGroup(new ConcreteObjectCell(o, o.getPoint()));
				}
				concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().insert(group);
			}
			abstractObjectPanel.getGraph().getGraphLayoutCache().insert(lastRemoved);
			// Set the position in concrete object panel
			JGraph concreteObjectPanelGraph = concreteObjectPanel.getConcreteObjectGraphCreation();
			int x = 0;
			int y = 0;
			CellView[] allViews = concreteObjectPanelGraph.getGraphLayoutCache().getAllViews();
			for (int i = allViews.length - 1; i >= 0; i--) {
				if (allViews[i].getBounds().contains(x, y)) {
					y += allViews[i].getBounds().getHeight() + 10;
				}
			}
			AbstractObjectCell cell2 = new AbstractObjectCell((ObjetAbstrait) lastRemoved.getObject(), x, y);
			concreteObjectPanelGraph.getGraphLayoutCache().insert(cell2);
			lastRemoved = null;
		} else if (objectRemoved != null) {
			InterfaceExpressJava.mettre(objectRemoved.getObject().getOid(), objectRemoved.getObject());
			for (AttributConcret a : objectRemoved.getObject().getInverseListAttribut()) {
				InterfaceExpressJava.mettre(a.getOid(), a);
			}
			concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().insert(objectRemoved);
			objectRemoved = null;
		} else if (groupRemoved != null) {
			InterfaceExpressJava.mettre(groupRemoved.getObject().getOid(), groupRemoved.getObject());
			for (ObjetConcret o : groupRemoved.getObject().getEnsemble().getLstObjConcrets()) {
				InterfaceExpressJava.mettre(o.getOid(), o);
				for (AttributConcret a : o.getInverseListAttribut()) {
					InterfaceExpressJava.mettre(a.getOid(), a);
				}
			}
			concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().insert(groupRemoved);
			groupRemoved = null;

		} else
			return;
	}

	/**
	 * Method to create a new group
	 * 
	 * @param Type of group
	 */
	public void createNewGroup(String AgregatName) {
		String oid = ExpressGroup.createGroup();
		Groupe g = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));
		g.setName(KMADEConstant.NEW_GROUP_NAME);
		GroupDefaultGraphCell cell;

		int y = concreteObjectPanel.getConcreteObjectGraph().getWidth() / 2;
		int x = concreteObjectPanel.getConcreteObjectGraph().getHeight() / 2;
		CellView[] allViews = concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().getAllViews();
		for (int i = allViews.length - 1; i >= 0; i--) {
			if (allViews[i].getBounds().contains(x, y)) {
				x += 10;
				y += 10;
			}
		}

		if (AgregatName.equals(ExpressConstant.STACK_NAME)) {
			ExpressGroup.removeOldAgregatAndCreateNewAgregat(g.getOid().get(),
					AgregatStructure.STACK_AGREGAT.getValue());
			cell = new StackCell(g, x, y);
		} else if (AgregatName.equals(ExpressConstant.SET_NAME)) {
			ExpressGroup.removeOldAgregatAndCreateNewAgregat(g.getOid().get(), AgregatStructure.SET_AGREGAT.getValue());
			cell = new SetCell(g, x, y);
		} else if (AgregatName.equals(ExpressConstant.LIST_NAME)) {
			ExpressGroup.removeOldAgregatAndCreateNewAgregat(g.getOid().get(),
					AgregatStructure.LIST_AGREGAT.getValue());
			cell = new ListCell(g, x, y);
		} else if (AgregatName.equals(ExpressConstant.ARRAY_NAME)) {
			ExpressGroup.removeOldAgregatAndCreateNewAgregat(g.getOid().get(),
					AgregatStructure.ARRAY_AGREGAT.getValue());
			cell = new ArrayCell(g, x, y);
		} else if (AgregatName.equals(ExpressConstant.SINGLETON_NAME)) {
			ExpressGroup.removeOldAgregatAndCreateNewAgregat(g.getOid().get(),
					AgregatStructure.SINGLETON_AGREGAT.getValue());
			cell = new SingletonCell(g, x, y);
		} else
			cell = (GroupDefaultGraphCell) new DefaultGraphCell();

		concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().insert(cell);
		cell.editCreation();
	}

	/**
	 * Method to edit a selected group or a selected concrete object
	 */
	public void editObject() {
		if (concreteObjectPanel.getConcreteObjectGraph().getSelectionCell() instanceof GroupDefaultGraphCell)
			((GroupDefaultGraphCell) concreteObjectPanel.getConcreteObjectGraph().getSelectionCell()).edit();
		else if ((concreteObjectPanel.getConcreteObjectGraph().getSelectionCell() instanceof ConcreteObjectCell))
			((ConcreteObjectCell) concreteObjectPanel.getConcreteObjectGraph().getSelectionCell())
					.edit(concreteObjectPanel.getConcreteObjectGraph());
		else
			return;
	}

	/**
	 * Method to remove a selected group or a selected concrete object
	 */
	public void removeObject() {
		if (concreteObjectPanel.getConcreteObjectGraph().getSelectionCell() instanceof GroupDefaultGraphCell) {
			groupRemoved = (GroupDefaultGraphCell) concreteObjectPanel.getConcreteObjectGraph().getSelectionCell();
			Object[] cells = { groupRemoved };
			concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().remove(cells);
			InterfaceExpressJava.remove(groupRemoved.getObject().getOid());
			for (ObjetConcret o : groupRemoved.getObject().getEnsemble().getLstObjConcrets()) {
				InterfaceExpressJava.remove(o.getOid());
				for (AttributConcret a : o.getInverseListAttribut()) {
					InterfaceExpressJava.remove(a.getOid());
				}
			}
			groupRemoved.getObject().getContientObj().removeInverseGroupe(groupRemoved.getObject());
			// For the undo
			objectRemoved = null;
		}

		else if (concreteObjectPanel.getConcreteObjectGraph().getSelectionCell() instanceof ConcreteObjectCell) {
			objectRemoved = (ConcreteObjectCell) concreteObjectPanel.getConcreteObjectGraph().getSelectionCell();
			Object[] cells = { objectRemoved };
			concreteObjectPanel.getConcreteObjectGraph().getGraphLayoutCache().remove(cells);
			InterfaceExpressJava.remove(objectRemoved.getObject().getOid());
			for (AttributConcret a : objectRemoved.getObject().getInverseListAttribut()) {
				InterfaceExpressJava.remove(a.getOid());
			}
			// For the undo
			groupRemoved = null;
		}

		attRemoved = null;
		lastCreated = null;
		lastCreated = null;

	}

	/**
	 * Method to update the views in case news objects would be created out of the
	 * interface
	 */
	public void update() {
		List<ObjetAbstrait> abstracts = new ArrayList<ObjetAbstrait>();
		Set<Oid> set = bdd.keySet();
		Iterator<Oid> i;
		for (i = set.iterator(); i.hasNext();) {
			Oid oid = (Oid) i.next();
			if (bdd.prendre(oid) instanceof ObjetAbstrait)
				abstracts.add((ObjetAbstrait) bdd.prendre(oid));
		}

		abstractObjectPanel.clearGraph();
		concreteObjectPanel.clearGraph();
		abstractObjectPanel.drawAbstractObjectCells(abstracts);
		concreteObjectPanel.drawConcreteObjectsCells(abstracts);
	}

	/**
	 * Method to check if the user can save the project or not
	 */
	public void checkSaveStatus() {
		saveMessage.setText(KMADEConstant.CAN_SAVE_PROJECT);
		List<ObjetConcret> concretes = new ArrayList<ObjetConcret>();
		Set<Oid> set = bdd.keySet();
		Iterator<Oid> i;
		for (i = set.iterator(); i.hasNext();) {
			Oid oid = (Oid) i.next();
			if (bdd.prendre(oid) instanceof ObjetConcret)
				concretes.add((ObjetConcret) bdd.prendre(oid));
		}

		for (ObjetConcret o : concretes) {
			if (o.getAppartientGroupe() == null)
				saveMessage
						.setText("<html><font color=\"red\">" + KMADEConstant.CAN_NOT_SAVE_PROJECT + "</font></html>");

		}
	}

}
