package kmade.kmade.UI.worldobject.user;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.DefaultListTableModel;
import kmade.kmade.UI.toolutilities.JListTable;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.UserAdaptator;

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
public class KMADEReadWriteUserObjectPanel extends JScrollPane implements LanguageFactory {

    private static final long serialVersionUID = -6911578310580158697L;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    private TitledBorder myTitledBorder;
    
	private final ImageIcon ERROR_USER_IMAGEICON;

	private final ImageIcon UNKNOWN_USER_IMAGEICON;
    
    class MyImageColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
 		
        private static final long serialVersionUID = -3778437589036879639L;
   		
        private int currentState = 0;
   		
        private ImageIcon currentImageIcon = null;
        
        private String currentPath = "";
        
        private Object currentEditor;
       
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel myLabel;
          
       		if (value != null && value instanceof Object[]) {
       		    Object[] ref = (Object[]) value;
       		    currentPath = (String) ref[0];
       		    currentState = (Integer) ref[2];

                if (currentState == 0) {
                    currentImageIcon = null;
                    if (currentPath.equals("")) {
                        currentImageIcon = UNKNOWN_USER_IMAGEICON;
                    } else {
                        if (ref[1] != null
                                && ((ImageIcon) ref[1]).getIconWidth() != -1) {
                            currentImageIcon = (ImageIcon) ref[1];
                        } else {
                            currentImageIcon = ERROR_USER_IMAGEICON;
                        }
                    }

                    myLabel = new JLabel(currentImageIcon, JLabel.CENTER);
                } else {
                    myLabel = new JLabel(currentPath, JLabel.CENTER);
                }
            } else {
                myLabel = new JLabel("");
            }
       		myLabel.setOpaque(true);
            if (isSelected) {
                myLabel.setBackground(table.getSelectionBackground());
                myLabel.setForeground(table.getSelectionForeground());
            } else {
                myLabel.setBackground(table.getBackground());
                myLabel.setForeground(table.getForeground());
            }
            return myLabel;
        }
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			currentEditor = value;
			if (value != null && value instanceof Object[]) {
                Object[] myCurrentValue = (Object[])value;
                JComboBox myCombo = new JComboBox();
				myCombo.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);

                if (((String)myCurrentValue[0]).equals("")) {
                    // Pas d'image
                    myCombo.addItem(KMADEConstant.USER_CHOOSE_IMAGE_MESSAGE);
                    myCombo.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            chooseImagePath(e);
                        }
                    });
                } else {
                		ImageIcon myIcon = null;
                		if (myCurrentValue[1] != null) {
                			myIcon = ((ImageIcon)myCurrentValue[1]);
                		} 
                	
                    if (myIcon == ERROR_USER_IMAGEICON) {
                        // Image erronée
                        myCombo.addItem(KMADEConstant.USER_DISPLAY_DEFAULT_IMAGE_MESSAGE);
                        myCombo.addItem(KMADEConstant.USER_DISPLAY_BAD_PATH_IMAGE_MESSAGE);
                        myCombo.addItem(KMADEConstant.USER_CLEAR_IMAGE_MESSAGE);
                        myCombo.addItem(KMADEConstant.USER_CHOOSE_IMAGE_MESSAGE);
                        myCombo.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JComboBox myCombo = (JComboBox)e.getSource();
                                if (myCombo.getSelectedIndex() == 0) {
                                	displayImage(e);
                                } else if (myCombo.getSelectedIndex() == 1) {
                                    displayText(e);
                                } else if (myCombo.getSelectedIndex() == 2) {
                                    clearImagePath(e);
                                } else if (myCombo.getSelectedIndex() == 3) {
                                    chooseImagePath(e);
                                }
                            }
                        });
                    } else {
                        // Image correcte
                        myCombo.addItem(KMADEConstant.USER_DISPLAY_IMAGE_MESSAGE);
                        myCombo.addItem(KMADEConstant.USER_DISPLAY_PATH_IMAGE_MESSAGE);
                        myCombo.addItem(KMADEConstant.USER_CLEAR_IMAGE_MESSAGE);
                        myCombo.addItem(KMADEConstant.USER_CHOOSE_IMAGE_MESSAGE);
                        myCombo.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JComboBox myCombo = (JComboBox)e.getSource();
                                if (myCombo.getSelectedIndex() == 0) {
                                    displayImage(e);
                                } else if (myCombo.getSelectedIndex() == 1) {
                                    displayText(e);
                                } else if (myCombo.getSelectedIndex() == 2) {
                                    clearImagePath(e);
                                } else if (myCombo.getSelectedIndex() == 3) {
                                    chooseImagePath(e);
                                }
                            }
                        });
                    }
                }
			    return myCombo;
			} else {
				MyImageColumn.this.cancelCellEditing();
				return null;
			}
		}
		
        private void displayImage(ActionEvent e) {
    		((Object[])currentEditor)[2] = 0;

        	MyImageColumn.this.stopCellEditing();
        }
        
        private void displayText(ActionEvent e) {
        		((Object[])currentEditor)[2] = 1;
            MyImageColumn.this.stopCellEditing();
        }
        
        private void clearImagePath(ActionEvent e) {
        		((Object[])currentEditor)[0] = "";
        		((Object[])currentEditor)[1] = UNKNOWN_USER_IMAGEICON;
        		((Object[])currentEditor)[2] = 0;
        		MyImageColumn.this.stopCellEditing();
        }
        
        private void chooseImagePath(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(KMADEReadWriteUserObjectPanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	((Object[])currentEditor)[0] = fc.getSelectedFile().getAbsolutePath();
            	ImageIcon myCurrent = null;
            	Image temp = KMADEToolUtilities.getImageThumbnail(fc.getSelectedFile().getAbsolutePath(),KMADEConstant.ROW_HEIGHT);
     			
    			if (temp == null) {
    				myCurrent = ERROR_USER_IMAGEICON;
    			} else {
    	   			myCurrent = new ImageIcon(temp);   				
    			}    	
            	            	
    			((Object[])currentEditor)[1] = myCurrent;
            	((Object[])currentEditor)[2] = 0;
            	MyImageColumn.this.stopCellEditing();
            } else {
                MyImageColumn.this.cancelCellEditing();
            }
        }
        
		public Object getCellEditorValue() {
			return currentEditor;
		}
    }
    
    public KMADEReadWriteUserObjectPanel() {
        String[] columnNames = { KMADEConstant.USER_NAME_TABLE, KMADEConstant.USER_STATUS_TABLE, KMADEConstant.USER_ROLE_TABLE, KMADEConstant.USER_PHOTO_TABLE};
        myTableListModel = new MyTableListModel(columnNames, KMADEConstant.USER_NEW_ROW_TABLE);
        myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
        myListTable.addAllKeyListener(new MyKeyListener());
        this.setViewportView(myListTable);
        this.setColumnHeaderView(myListTable.getPanelHeader());
        myListTable.setCellEditor(0, "String", null);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        myTitledBorder = new TitledBorder(null, KMADEConstant.USER_TITLE_NAME, TitledBorder.CENTER, TitledBorder.TOP); 
        this.setBorder(myTitledBorder);
               
        MyImageColumn toto = new MyImageColumn();
        myListTable.getMyTable().getColumnModel().getColumn(3).setCellRenderer(toto);
        myListTable.getMyTable().getColumnModel().getColumn(3).setCellEditor(toto);

        ERROR_USER_IMAGEICON = new ImageIcon(KMADEToolUtilities.getImageThumbnail(GraphicEditorAdaptator.class.getResource(KMADEConstant.USER_ERROR_IMAGE), KMADEConstant.ROW_HEIGHT));
        UNKNOWN_USER_IMAGEICON = new ImageIcon(KMADEToolUtilities.getImageThumbnail(GraphicEditorAdaptator.class.getResource(KMADEConstant.USER_UNKNOWN_IMAGE), KMADEConstant.ROW_HEIGHT));        
    }

    class MyTableListModel extends DefaultListTableModel {
        private static final long serialVersionUID = 7930031534603520066L;

        public MyTableListModel(String[] columnNames, String nameDefault) {
            super(columnNames, nameDefault);
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (aValue == null) 
                return;

            if (rowIndex + 1 == this.getRowCount()) {
                // Ajouter une nouvelle ligne.
                String newUserObject = UserAdaptator.addUser();
                String value = (String) aValue;
                value = UserAdaptator.setUserName(newUserObject, value);
                Object[] couple = {"",UNKNOWN_USER_IMAGEICON,0}; // Chemin, image et etat.
                Object[] tempo = {value, "", "", couple, newUserObject, false };
                myTableListModel.getListTableData().add(tempo);
                this.fireTableRowsInserted(rowIndex, rowIndex + 1);
                myTableListModel.fireListRowsInserted();
                myListTable.getListSelectionModel().setSelectionInterval(rowIndex, rowIndex);
            } else {
                Object[] tempValue = (Object[]) myTableListModel.getListTableData().get(rowIndex);
                String nameRow = (String) tempValue[0];
                String oidRow = (String) tempValue[4];

                switch (columnIndex) {
                case 0: {
                    String value = (String) aValue;
                    if (value.equals("") || nameRow.equals(value)) {
                        return;
                    }
                    aValue = UserAdaptator.setUserName(oidRow, value);
                    break;
                }
                case 1: {
                    UserAdaptator.setUserStatut(oidRow, (String) aValue);
                    break;
                }
                case 2: {
                    UserAdaptator.setUserRole(oidRow, (String) aValue);
                    break;
                }
                case 3: {
                    UserAdaptator.setUserImage(oidRow, (String)((Object[])aValue)[0]);
                	tempValue[columnIndex] = aValue;
                    return;
                }
                }
                tempValue[columnIndex] = (String) aValue;
            }
        }
    }

    public void removeAllUser() {
        myTableListModel.removeAllListTableData();
    }

    // Utilisé lors du chargement d'un fichier KMADe.
    public void addUser(String name, String statut, String role, String image, String oid) {
    		ImageIcon myCurrent;
    		if (image == null || image.equals("")) {
    			myCurrent = UNKNOWN_USER_IMAGEICON;
    		} else {
    			Image temp = KMADEToolUtilities.getImageThumbnail(image,KMADEConstant.ROW_HEIGHT);
     			
    			if (temp == null) {
    				myCurrent = ERROR_USER_IMAGEICON;
    			} else {
    	   			myCurrent = new ImageIcon(temp);   				
    			}    			
    		}
    		Object[] couple = {image == null ? "" : image,myCurrent,0};
    		Object[] tempo = { name, statut, role, couple, oid, false};
    		myTableListModel.getListTableData().add(tempo);
    		myTableListModel.fireTableRowsInserted(myTableListModel.getRowCount() - 1, myTableListModel.getRowCount() - 1);
    		myTableListModel.fireListRowsInserted();
    		myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount() - 1,myTableListModel.getRowCount() - 1);
    }

    public DefaultListTableModel getModel() {
    	return myTableListModel;
    }
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                int[] r = myListTable.getMyTable().getSelectedRows();
                boolean state = GraphicEditorAdaptator.removeSelectedItem(
                        "User", r, myTableListModel.getListTableData(),
                        GraphicEditorAdaptator.getMainFrame(),
                        KMADEConstant.USER_REMOVE_NAME_TITLE);

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
        String[] columnNames = { KMADEConstant.USER_NAME_TABLE, KMADEConstant.USER_STATUS_TABLE, KMADEConstant.USER_ROLE_TABLE };
        myTableListModel.setColumnNames(columnNames);
        myTableListModel.setNameDefault(KMADEConstant.USER_NEW_ROW_TABLE);
        myTitledBorder.setTitle(KMADEConstant.USER_TITLE_NAME);
    }
}
