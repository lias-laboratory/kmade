package kmade.kmade.UI.taskproperties.readworldobject;

import java.util.ArrayList;

import javax.swing.JScrollPane;

import kmade.kmade.UI.toolutilities.JTreeTable.AbstractTreeTableModel;
import kmade.kmade.UI.toolutilities.JTreeTable.JTreeTable;
import kmade.kmade.UI.toolutilities.JTreeTable.TreeTableModel;
import kmade.nmda.schema.metaobjet.AttributConcret;
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
public class KMADEReadConcreteObjectTable extends JScrollPane {

    private static final long serialVersionUID = 4870194953315242971L;

    private JTreeTable treeTable;

    public KMADEReadConcreteObjectTable() {
        treeTable = new JTreeTable(new ReadConcreteTreeTableModel(new NodeObject(new CurrentAbstractObjectDataBase("Root", null))));
        this.setViewportView(treeTable);
    }

    public void updateView(ArrayList<ObjetAbstrait> ref) {
        NodeObject myNode = new NodeObject("Root", ref);
        treeTable.setTreeTableModel(new ReadConcreteTreeTableModel(myNode));
    }
    
    static class CurrentAbstractObjectDataBase {
        private ArrayList<ObjetAbstrait> listObjetAbstrait;

        private String name;

        public CurrentAbstractObjectDataBase(String name,
                ArrayList<ObjetAbstrait> plistObjetAbstrait) {
            this.listObjetAbstrait = plistObjetAbstrait;
            this.name = name;
        }

        public ArrayList<ObjetAbstrait> getAbstractObjects() {
            return listObjetAbstrait;
        }
        
        public String getName() {
            return name;
        }
        
        public String toString() {
        		return name;
        }
    }

    class NodeObject {
        private Object myEntity;

        private Object[] children;

        public String toString() {
        		return myEntity == null ? "??" : myEntity.toString();
        }

        public Object getEntity() {
            return myEntity;
        }

        public NodeObject(String name, ArrayList<ObjetAbstrait> ref) {
            myEntity = new CurrentAbstractObjectDataBase(name, ref);
            
        }
        
        public NodeObject(Object pobject) {
            this.myEntity = pobject;
        }

        protected Object[] getChildren() {
            if (children != null) {
                return children;
            }

            ArrayList<?> listChildren = null;

            if (myEntity instanceof CurrentAbstractObjectDataBase) {
                listChildren = ((CurrentAbstractObjectDataBase) myEntity).getAbstractObjects();
            }

            if (myEntity instanceof ObjetAbstrait) {
                listChildren = ((ObjetAbstrait) myEntity).getInverseGroupe();
            }

            if (myEntity instanceof Groupe) {
                listChildren = ((Groupe) myEntity).getEnsemble().getLstObjConcrets();
            }
            
            if (myEntity instanceof ObjetConcret) {
                listChildren = ((ObjetConcret) myEntity).getInverseListAttribut();
            }
            
            if (listChildren != null) {
                children = new Object[listChildren.size()];
                for (int i = 0; i < listChildren.size(); i++) {
                    children[i] = new NodeObject(listChildren.get(i));
                }
            }

            return children;
        }
    }

    static class ReadConcreteTreeTableModel extends AbstractTreeTableModel implements TreeTableModel {
        protected String[] cNames = { "Nom", "Type", "Valeur" };

        protected Class<?>[] cTypes = { TreeTableModel.class, String.class, String.class };

        protected Object[] getChildren(Object node) {
            NodeObject fileNode = ((NodeObject) node);
            return fileNode.getChildren();
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

        public void setValueAt(Object p, Object node, int o) {
        	
        }
        
        public Object getValueAt(Object node, int column) {
            NodeObject current = (NodeObject) node;
            String name = "";
            String type = "";
            String valeur = "";
            if (current.getEntity() instanceof CurrentAbstractObjectDataBase) {
                CurrentAbstractObjectDataBase temp = (CurrentAbstractObjectDataBase)current.getEntity();
                name = temp.getName();
                type = "";
                valeur = temp.getAbstractObjects().size() != 0 ? temp.getAbstractObjects().size() + " objet(s) abstrait(s)" : "Aucun objet abstrait";                    
            } else if (current.getEntity() instanceof ObjetAbstrait) {
                ObjetAbstrait temp = (ObjetAbstrait)current.getEntity();
                name = temp.getName();
                type = "Objet Abstrait";                
                valeur = temp.getInverseGroupe().size() != 0 ? temp.getInverseGroupe().size() + " groupe(s)" : "Aucun groupe";
            } else if (current.getEntity() instanceof Groupe) {
            		Groupe temp = (Groupe)current.getEntity();
                name = temp.getName();
                type = "Groupe";
                valeur = temp.getEnsemble().getLstObjConcrets().size() != 0 ? temp.getEnsemble().getLstObjConcrets().size() + " objet(s) concret(s)" : "Aucun objet concret";
            } else if (current.getEntity() instanceof ObjetConcret) {
                ObjetConcret temp = (ObjetConcret)current.getEntity();
                name = temp.getName();
                type = "Objet Concret";
                valeur = temp.getInverseListAttribut().size() != 0 ? temp.getInverseListAttribut().size() + " attribut(s) concret(s)" : "Aucun attribut concret";                
            } else if (current.getEntity() instanceof AttributConcret) {               
                AttributConcret temp = (AttributConcret)current.getEntity();
                name = temp.getName();
                type = "Attribut Concret";
                if (temp.isAttributeNull()) {
                    valeur = "??";
                } else {
                    valeur = temp.getValue() == null ? "??" : temp.getValue().toString();
                }
            } else {                
                name = "???";
                type = "???";
                valeur = "???";
            }

            switch (column) {
                case 0:
                    return name;
                case 1:
                    return type;
                case 2:
                    return valeur;
            }
            return null;
        }

        public ReadConcreteTreeTableModel(Object root) {
            super(root);
        }

        public Object getChild(Object parent, int index) {
            return getChildren(parent)[index];
        }

        public int getChildCount(Object parent) {
            Object[] children = getChildren(parent);
            return (children == null) ? 0 : children.length;
        }
        
        public boolean isLeaf(Object node) {
        		if (((NodeObject) node).getEntity() instanceof AttributConcret) {
        			return true;
        		} else {
        			return getChildren(node) == null ? true : getChildren(node).length == 0;
        		}
        }

		public boolean isCellTreeEditable(Object value) {
			return false;		
		}
    }
}
