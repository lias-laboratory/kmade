package kmade.kmade.view.worldobject.label;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorFC.parserExpression.RegularExpression;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.LabelAdaptator;
import kmade.kmade.view.toolutilities.DefaultListTableModel;
import kmade.kmade.view.toolutilities.JListTable;
import kmade.kmade.view.toolutilities.LanguageFactory;
import kmade.nmda.schema.tache.Label;

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
public class KMADELabelPanel extends JScrollPane implements LanguageFactory {

	private static final long serialVersionUID = -2435972186172023555L;

	private JListTable myListTable;

    private MyTableListModel myTableListModel;

    private TitledBorder myTitledBorder;

    public KMADELabelPanel() {
        String[] columnNames = {KMADEConstant.LABEL_NUMERO_TITLE, KMADEConstant.LABEL_NAME_TITLE, KMADEConstant.LABEL_DESCRIPTION_TITLE, KMADEConstant.LABEL_COLOR_TITLE, KMADEConstant.LABEL_IS_VISIBLE_COLOR_TITLE, KMADEConstant.LABEL_IS_VISIBLE_TITLE};
        myTableListModel = new MyTableListModel(columnNames, KMADEConstant.LABEL_ADD_NEW_LABEL_TITLE);
        myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
        myListTable.addAllKeyListener(new MyKeyListener());
        this.setViewportView(myListTable);
        this.setColumnHeaderView(myListTable.getPanelHeader());
        myListTable.setCellEditor(0,"String",null);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        myTitledBorder = new TitledBorder(null, KMADEConstant.LABEL_EDITOR_TITLE, TitledBorder.CENTER, TitledBorder.TOP);
        this.setBorder(myTitledBorder);
        myListTable.getMyTable().setDefaultEditor(Color.class, new ColorEditor());
        myListTable.getMyTable().setDefaultRenderer(Color.class, new ColorRenderer(true));
        myListTable.getMyTable().setDefaultRenderer(Boolean.class, new BooleanRenderer());
        myListTable.setCellEditor(1,"SpecialString",null);
        myListTable.getMyTable().getColumnModel().getColumn(0).setMaxWidth(40);
    }
    
    public void removeAllLabels(){
        myTableListModel.removeAllListTableData();
    }
    
    public DefaultListTableModel getModel() {
        return myTableListModel;
    }
    
    public void addLabel(String name, String description, Color myColor, boolean colorvisible,boolean visible, String oid) {
        Object[] tempo = {name, description, myColor, colorvisible, visible, oid, false};
        myTableListModel.getListTableData().add(tempo);
        myTableListModel.fireTableRowsInserted(myTableListModel.getRowCount() -1,myTableListModel.getRowCount() -1);
        myTableListModel.fireListRowsInserted();                  
        myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount() -1,myTableListModel.getRowCount() -1);
    } 
    
    class MyTableListModel extends DefaultListTableModel {
		private static final long serialVersionUID = -7303557045036013883L;
    	
		public MyTableListModel(String[] columnNames, String nameDefault) {
			super(columnNames, nameDefault);
		}
		
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return false;
            }
            
	        if (rowIndex + 1 == this.getRowCount()) {
	            if (columnIndex != 1) {
	                return false;
	            } else {
	                return true;        
	            }
	        } else {
	            return true;
	        }
	    }
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				if (rowIndex <= (this.getRowCount() - 2)) {
					return rowIndex;
				} else {
					return "";
				}
			}
			
			if (rowIndex == this.getRowCount() - 1) {
	            if (columnIndex == 1) {
	                return nameDefault;
	            } else {
	                return null;
	            }
	        } else {                
	            return maList.get(rowIndex)[columnIndex-1];
	        }    
		}		
		private String nameTest(String value){
			String name =value;
			boolean needVerif = true;
			// un nom null annule l'�dition, boucle tant que le nom n'est pas correct  
			while (name != null && needVerif){
				// v�rification de l'expression r�guli�re avec affichage d'un popUp
				if( !RegularExpression.isGoodLabelName(name)){
					name = (String) JOptionPane.showInputDialog(GraphicEditorAdaptator.getMainFrame(),KMADEConstant.BAD_CHARACTER_TEXT,
							KMADEConstant.BAD_CARACTER_TITLE,JOptionPane.YES_NO_OPTION,
							new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE))
					,null,name
					);
				}else{ // l'expression est ok
					if(Label.isUniqueName(name)){
						// si le nom est unique, le nom est correct et possible
						needVerif = false;
					} else {
						name = (String)  JOptionPane.showInputDialog(GraphicEditorAdaptator.getMainFrame(),KMADEConstant.SAME_NAME_TEXT,
								KMADEConstant.SAME_NAME_TITLE,JOptionPane.YES_NO_OPTION,
								new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE))
						,null,Label.propositionNom(name)
						);
					}		
				}
			}
			return name;
		}
		
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    		if (rowIndex + 1 == this.getRowCount()) {
				String value = (String) aValue;
				if (value.equals("")) {
					return;
				}
				
				//verification du nom
				String name = nameTest(value);
				if(name == null || name.equals("")){
					return;	}
				value = name;
				// Ajouter une nouvelle ligne.
				String newLabelObject = LabelAdaptator.addLabel();
				value = LabelAdaptator.setLabelName(newLabelObject, value);
				Object[] tempo = {value,"", Color.WHITE, true, true, newLabelObject, false };
				myTableListModel.getListTableData().add(tempo);
				this.fireTableRowsInserted(rowIndex, rowIndex + 1);
				myTableListModel.fireListRowsInserted();
				myListTable.getListSelectionModel().setSelectionInterval(rowIndex, rowIndex);
			} else {
				Object[] tempValue = (Object[]) myTableListModel.getListTableData().get(rowIndex);
				String oidRow = (String) tempValue[5];
				Object value = aValue;
				switch (columnIndex) {
				case 0: {
					return;
				}
				case 1: {
					 String nameRow = (String)tempValue[0];
                     if (nameRow.equals((String)aValue))
                         return;
                     String name = nameTest((String)value);
     				if(name == null || name.equals("")){
     					return;	}
     				value = LabelAdaptator.setLabelName(oidRow, name);
					break;
				}
				case 2: {
					LabelAdaptator.setLabelDescription(oidRow, (String) value);
					break;
				}
				case 3: {
                    LabelAdaptator.setLabelColor(oidRow, (Color)value);
					break;
				}
                case 4: {
                    LabelAdaptator.setLabelColorVisible(oidRow, (Boolean)value);
                    break;
                }
				case 5: {
                    LabelAdaptator.setLabelVisible(oidRow, (Boolean)value);
					break;
				}
				}
				tempValue[columnIndex-1] = value;
			}
		}  
	       
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Color.class;
            case 4:
                return Boolean.class;               
            case 5:
                return Boolean.class;
            default:
                return String.class;
            }
        }
    }
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                int[] r = myListTable.getMyTable().getSelectedRows();
                boolean state = GraphicEditorAdaptator.removeSelectedItem("Label", r, myTableListModel.getListTableData(),
                        GraphicEditorAdaptator.getMainFrame(),
                        KMADEConstant.LABEL_REMOVE_NAME_TITLE);

                if (state) {
                    myTableListModel.fireTableDataChanged();
                    myListTable.getListSelectionModel().setSelectionInterval(
                            myTableListModel.getRowCount() - 1,
                            myTableListModel.getRowCount() - 1);
                }                
            }
        }
    }
    
	public void notifLocalisationModification() {
		
	}
	
	class ColorEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

		private static final long serialVersionUID = 6655509581266780189L;

		private Color currentColor;

		private JButton button;

		private JColorChooser colorChooser;

		private JDialog dialog;

		protected static final String EDIT = "edit";

		public ColorEditor() {
			button = new JButton();
			button.setActionCommand(EDIT);
			button.addActionListener(this);
			button.setBorderPainted(false);
			colorChooser = new JColorChooser();
			dialog = JColorChooser.createDialog(button, "Pick a Color", true, colorChooser, this, null); 
		}

		public void actionPerformed(ActionEvent e) {
			if (EDIT.equals(e.getActionCommand())) {
				button.setBackground(currentColor);
				colorChooser.setColor(currentColor);
				dialog.setVisible(true);
				fireEditingStopped(); 

			} else { 
				currentColor = colorChooser.getColor();
			}
		}

		public Object getCellEditorValue() {
			return currentColor;
		}

		public Component getTableCellEditorComponent(JTable table,	Object value, boolean isSelected, int row, int column) {
			currentColor = (Color) value;
			return button;
		}
	}
    
    class BooleanRenderer extends JCheckBox implements TableCellRenderer, UIResource {

        private static final long serialVersionUID = 7871885791399629274L;

        public BooleanRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
            setBorderPainted(true);
        }   

        public Component getTableCellRendererComponent(JTable table, Object value,
                               boolean isSelected, boolean hasFocus, int row, int column) {
            if (row != myTableListModel.getRowCount() - 1) {
                if (isSelected) {
                    setForeground(table.getSelectionForeground());
                    super.setBackground(table.getSelectionBackground());
                }
                else {
                    setForeground(table.getForeground());
                    setBackground(table.getBackground());
                }
                setSelected((value != null && ((Boolean)value).booleanValue()));

                if (hasFocus) {
                    setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
                } else {
                    setBorder(new EmptyBorder(1, 1, 1, 1));
                }

                return this;
            } else {
                JLabel myLabel = new JLabel("");
                myLabel.setOpaque(true);
                if (isSelected) {
                    myLabel.setForeground(table.getSelectionForeground());
                    myLabel.setBackground(table.getSelectionBackground());
                }
                else {
                    myLabel.setForeground(table.getForeground());
                    myLabel.setBackground(table.getBackground());
                }
                return myLabel;
            }
        }
    }
	
	class ColorRenderer extends JLabel implements TableCellRenderer {
		private static final long serialVersionUID = 5866803288700034009L;

		Border unselectedBorder = null;

		Border selectedBorder = null;

		boolean isBordered = true;

		public ColorRenderer(boolean isBordered) {
			this.isBordered = isBordered;
			setOpaque(true); 
		}

		public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
			if (row != myTableListModel.getRowCount() - 1) {
    			Color newColor = (Color) color;
    			setBackground(newColor);
    			if (isBordered) {
    				if (isSelected) {
    					if (selectedBorder == null) {
    						selectedBorder = BorderFactory.createMatteBorder(2, 5,
    								2, 5, table.getSelectionBackground());
    					}
    					setBorder(selectedBorder);
    				} else {
    					if (unselectedBorder == null) {
    						unselectedBorder = BorderFactory.createMatteBorder(2,
    								5, 2, 5, table.getBackground());
    					}
    					setBorder(unselectedBorder);
    				}
    			}
    
    			setToolTipText("RGB value: " + newColor.getRed() + ", "
    					+ newColor.getGreen() + ", " + newColor.getBlue());
    			return this;
			} else {
                JLabel myLabel = new JLabel("");
                myLabel.setOpaque(true);
                if (isSelected) {
                    myLabel.setForeground(table.getSelectionForeground());
                    myLabel.setBackground(table.getSelectionBackground());
                }
                else {
                    myLabel.setForeground(table.getForeground());
                    myLabel.setBackground(table.getBackground());
                }
                return myLabel;
            }
		}
	}

}
