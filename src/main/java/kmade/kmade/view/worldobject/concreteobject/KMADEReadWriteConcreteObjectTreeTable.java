package kmade.kmade.view.worldobject.concreteobject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.ConcreteObjectAdaptator;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.coreadaptator.parserExpression.RegularExpression;
import kmade.kmade.view.toolutilities.JListTreeTable;
import kmade.kmade.view.toolutilities.LanguageFactory;
import kmade.kmade.view.toolutilities.JTreeTable.DefaultTreeTableModel;
import kmade.kmade.view.toolutilities.JTreeTable.TreeTableModel;
import kmade.nmda.schema.metaobjet.AgregatStructure;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEReadWriteConcreteObjectTreeTable extends JScrollPane implements LanguageFactory {

	private static final long serialVersionUID = -6633815109319637067L;

	private JListTreeTable listTreeTable;

	private ConcreteObjectTreeTableModel refModel;

	public KMADEReadWriteConcreteObjectTreeTable() {
		listTreeTable = new JListTreeTable(new ConcreteObjectTreeTableModel(new ConcreteObjectNode(null)));      
		listTreeTable.addAllKeyListener(new MyKeyListener());
		listTreeTable.getTree().getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (listTreeTable.getTreeTable().getSelectionModel().isSelectionEmpty()) {
					ConcreteObjectAdaptator.setNoSelectedConcreteObject();
				} else {
					ArrayList<ObjetConcret> temp = getSelectionLastPathsConcreteObjectNode(); 

					if (temp.size() == 0) {
						ConcreteObjectAdaptator.setNoSelectedConcreteObject();
					} else if (temp.size() > 1) {
						ConcreteObjectAdaptator.setNoSelectedConcreteObject();
					} else if (temp.size() == 1) {
						ConcreteObjectAdaptator.setSelectedConcreteObject(temp.get(0));
					}
				}   
			}             
		});

		this.setViewportView(listTreeTable);
		this.setColumnHeaderView(listTreeTable.getPanelHeader());
		this.setOpaque(true);
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);

	}

	public ArrayList<ObjetConcret> getSelectionLastPathsConcreteObjectNode() {
		Object[] toto = listTreeTable.getTreeTable().getSelectionLastPaths();
		ArrayList<ObjetConcret> temp = new ArrayList<ObjetConcret>();

		for (int i = 0 ; i < toto.length ; i++) {
			if (toto[i] instanceof ConcreteObjectNode) {
				if (((ConcreteObjectNode)toto[i]).getEntity() instanceof ObjetConcret) {
					temp.add((ObjetConcret)(((ConcreteObjectNode)toto[i]).getEntity()));
				}
			}
		}
		return temp;
	}

	public void setConcreteObjectNameBorder(String name) {
		String afficher = "";
		if (name.equals("")) 
			afficher = "";
		else 
			afficher = " : " + name;
		this.setBorder(new TitledBorder(null, KMADEConstant.CONCRETE_OBJECT_TITLE_TABLE + afficher, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, KMADEConstant.fontPASSIF));
	}

	public void updateDataModel(ObjetAbstrait refObjetAbstrait) {
		refModel = new ConcreteObjectTreeTableModel(new ConcreteObjectNode(refObjetAbstrait));
		listTreeTable.getTreeTable().setTreeTableModel(refModel);
	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
				ArrayList<ObjetConcret> objetConcret = getSelectionLastPathsConcreteObjectNode();
				if (objetConcret.size() != 0) {
					ArrayList<Object[]> list = new ArrayList<Object[]>();
					int[] tabSelect = new int[objetConcret.size()];
					for (int i = 0; i < objetConcret.size(); i++) {
						Object[] temp = new Object[2];
						temp[0] = objetConcret.get(i).getOid().get();
						temp[1] = false;
						list.add(temp);
						tabSelect[i] = i;
					}
					boolean state = GraphicEditorAdaptator.removeSelectedItem(
							"ConcreteObject", tabSelect, list,
							GraphicEditorAdaptator.getMainFrame(),
							KMADEConstant.CONCRETE_OBJECT_REMOVE_MESSAGE_TITLE);

					if (state) {
						refModel.nodeStructureChanged((TreeNode)refModel.getRoot());
						listTreeTable.getTreeTable().getTableTreeModel().fireTableRowsDeleted(0,0);
					}
				}
			}
		}
	}

	class ConcreteObjectNewLineUserObject {
		public String toString() {
			return KMADEConstant.CONCRETE_NEW_OBJECT_TABLE;
		}
	}

	class ConcreteObjectNode extends DefaultMutableTreeNode {

		private static final long serialVersionUID = -4890496447789101870L;

		private Object myEntity;

		public ConcreteObjectNode(Object myEntity) {
			this.myEntity = myEntity;
		}

		public ConcreteObjectNode(Object myEntity, MutableTreeNode p) {
			this.myEntity = myEntity;
			this.setParent(p);
		}

		public ConcreteObjectNode searchEntityObject(Object entity) {
			if (entity == myEntity) {
				return this;
			}

			ConcreteObjectNode toto = null;
			if (children != null) {
				for (int i = 0 ; i < children.size(); i++) {
					toto = ((ConcreteObjectNode)children.get(i)).searchEntityObject(entity);
					if (toto != null) {
						return toto;
					}
				}
			}
			return toto;
		}

		public Object getEntity() {
			return myEntity;
		}

		public String toString() {
			if (myEntity == null) {
				return "Blabla";
			} else if (myEntity instanceof ObjetAbstrait) {
				return "Racine";
			} else if (myEntity instanceof ConcreteObjectNewLineUserObject) {
				return myEntity.toString();
			} else if (myEntity instanceof Groupe) {
				Groupe myGroupe = (Groupe)myEntity;
				String localeAgregat = AgregatStructure.getEnumereIntoLocaleAgregatStructure(myGroupe.getEnsemble().getAgregatStructure().getValue());                
				return ((Groupe)myEntity).getName() + " (" + localeAgregat + ")"; 
			} else if (myEntity instanceof ObjetConcret) {
				return ((ObjetConcret)myEntity).getName();
			}
			return "";
		}

		public boolean getAllowsChildren() {
			return !(myEntity instanceof ObjetConcret);
		}

		public boolean isLeaf() {
			return (myEntity instanceof ObjetConcret) || (myEntity instanceof ConcreteObjectNewLineUserObject);
		}


		@SuppressWarnings("unchecked")
		protected List<ConcreteObjectNode> getChildren() {
			if (children != null) {

				if (myEntity instanceof Groupe) {
					children = new Vector<ConcreteObjectNode>();
					Groupe ref = (Groupe)myEntity;
					for (ObjetConcret current : ref.getEnsemble().getLstObjConcrets()) {
						children.add(new ConcreteObjectNode(current,this));
					}
					children.add(new ConcreteObjectNode(new ConcreteObjectNewLineUserObject(),this));
					return children;
				}

				return children;
			}
			children = new Vector<ConcreteObjectNode>();
			if (myEntity instanceof ObjetAbstrait) {
				ObjetAbstrait ref = (ObjetAbstrait)myEntity;
				for (Groupe current : ref.getInverseGroupe()) {
					children.add(new ConcreteObjectNode(current,this));
				}
				return children;
			} else if (myEntity instanceof Groupe) {
				Groupe ref = (Groupe)myEntity;
				for (ObjetConcret current : ref.getEnsemble().getLstObjConcrets()) {
					children.add(new ConcreteObjectNode(current,this));
				}
				children.add(new ConcreteObjectNode(new ConcreteObjectNewLineUserObject(),this));
				return children;
			} else {
				return children;
			}
		}		
	}



	class ConcreteObjectTreeTableModel extends DefaultTreeTableModel {
		protected String[] cNames = { "Nom", "Désignation" };

		protected Class<?>[] cTypes = { TreeTableModel.class, String.class };

		public ConcreteObjectTreeTableModel(TreeNode root) {
			super(root);
		}

		public Object getChild(Object parent, int index) {
			return ((ConcreteObjectNode)parent).getChildren().get(index);
		}


		public int getChildCount(Object parent) {
			return((ConcreteObjectNode)parent).getChildren().size();
		}  

		public int getColumnCount() {
			return cNames.length;
		}

		public String getColumnName(int column) {
			return cNames[column];
		}

		public Class<?> getColumnClass(int column) {
			return cTypes[column];
		}

		/**
		 * Test si le nom est correct
		 * @param value
		 * @return
		 * une chaine vide ou null si le nom est incorrect
		 * le nom si le nom est correct
		 */
		private String nameTest(String value){
			String name =value;
			boolean needVerif = true;
			// un nom null annule l'�dition, boucle tant que le nom n'est pas correct  
			while (name != null && needVerif){
				// v�rification de l'expression r�guli�re avec affichage d'un popUp
				if( !RegularExpression.isGoodConcretObjectName(name)){
					name = (String) JOptionPane.showInputDialog(GraphicEditorAdaptator.getMainFrame(),KMADEConstant.BAD_CHARACTER_TEXT,
							KMADEConstant.BAD_CARACTER_TITLE,JOptionPane.YES_NO_OPTION,
							new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE))
					,null,name
					);
				}else{ // l'expression est ok
					if(ObjetConcret.isUniqueName(name)){
						// si le nom est unique, le nom est correct et possible
						needVerif = false;
					} else {
						name = (String)  JOptionPane.showInputDialog(GraphicEditorAdaptator.getMainFrame(),KMADEConstant.SAME_NAME_TEXT,
								KMADEConstant.SAME_NAME_TITLE,JOptionPane.YES_NO_OPTION,
								new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE))
						,null,ObjetConcret.propositionNom(name)
						);
					}		
				}
			}
			return name;
		}

		public void setValueAt(Object aValue, Object node, int column) {
			ConcreteObjectNode refNode = (ConcreteObjectNode)node;
			String value = (String)aValue;
			if (column == 1) {
				if (refNode.getEntity() instanceof ObjetConcret) {
					ConcreteObjectAdaptator.setConcreteObjectDescription((ObjetConcret)(refNode.getEntity()),(String)aValue);
				}                
			} else if (column == 0) {
				if (((String)aValue).equals("")) {
					return;
				}

				if (refNode.getEntity() instanceof ConcreteObjectNewLineUserObject) {
					//verif

					String name = nameTest(value);
					if(name == null || name.equals("")){
						return;	}
					// Ajouter

					ObjetConcret newConcreteObject = ConcreteObjectAdaptator.addNewConcreteObject((Groupe)((ConcreteObjectNode)refNode.getParent()).getEntity(),name);
					if (newConcreteObject == null) {
						return;
					}     

					this.nodeStructureChanged(refNode.getParent());                  
					listTreeTable.getTreeTable().getTableTreeModel().fireTableRowsInserted(0,0);
					listTreeTable.fireListRowsInserted();
					ConcreteObjectNode newNode = ((ConcreteObjectNode)this.root).searchEntityObject(newConcreteObject);
					int t = listTreeTable.getTree().getRowForPath(new TreePath(this.getPathToRoot(newNode)));
					listTreeTable.getTree().setSelectionPath(new TreePath(this.getPathToRoot(newNode)));
					listTreeTable.getTreeTable().getSelectionModel().setSelectionInterval(t,t);
				} else {
					// Modifier
					
					ObjetConcret temp = ((ObjetConcret)(refNode.getEntity()));
					if(value.equals(temp.getName())){
						return;	}
					String name = nameTest(value);
					value = name;
					if(name == null || name.equals("") || name.equals(temp.getName()) ){
						return;	}
					ConcreteObjectAdaptator.setConcreteObjectName((ObjetConcret)(refNode.getEntity()),value);
					this.nodeStructureChanged(refNode.getParent());
					listTreeTable.getTreeTable().getTableTreeModel().delayedFireTableDataChanged();

				}
			}
		}

		public Object getValueAt(Object node, int column) {
			ConcreteObjectNode refNode = (ConcreteObjectNode)node;
			String name = "";
			String designation = "";

			if (refNode.getEntity() instanceof ObjetAbstrait) {
				name = refNode.toString();
				int groupCount = ((ObjetAbstrait)refNode.getEntity()).getInverseGroupe().size();
				designation = groupCount != 0 ? Integer.toString(groupCount) : KMADEConstant.NO_CONCRETE_OBJECT_GROUPE_NAME;
				designation += " " + KMADEConstant.CONCRETE_OBJECT_GROUPE_NAME + (groupCount > 1 ? "s" : "");   
			} else if (refNode.getEntity() instanceof Groupe) {
				Groupe temp = (Groupe)refNode.getEntity();
				name = temp.getName();
				designation = temp.getDescription();
			} else if (refNode.getEntity() instanceof ObjetConcret) {
				ObjetConcret temp = (ObjetConcret)refNode.getEntity();
				name = temp.getName();
				designation = temp.getDescription();
			} else if (refNode.getEntity() instanceof ConcreteObjectNewLineUserObject) {
				name = "";
			}

			switch (column) {
			case 0:
				return name;
			case 1:
				return designation;
			}
			return null;
		}

		public boolean isCellEditable(Object node, int column) {
			switch (column) {
			case 0 : {
				return true;
			}
			case 1 : {
				Object myUserObject = ((ConcreteObjectNode)node).getEntity();
				if (myUserObject instanceof ObjetConcret) {
					return true;
				} else {
					return false;
				} 
			}
			}

			return getColumnClass(column) == TreeTableModel.class;
		}


		public boolean isCellTreeEditable(Object node) {
			ConcreteObjectNode temp = (ConcreteObjectNode)node;
			if (temp.getEntity() instanceof ObjetAbstrait) {
				return false;
			} else if (temp.getEntity() instanceof Groupe) {
				return false;
			} else if (temp.getEntity() instanceof ConcreteObjectNewLineUserObject) {
				return true;                    
			} else if (temp.getEntity() instanceof ObjetConcret) {
				return true;
			} 
			return false;
		}    
	}

	public void notifLocalisationModification() {
	}
}
