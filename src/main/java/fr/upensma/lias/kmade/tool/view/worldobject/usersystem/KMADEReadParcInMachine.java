package fr.upensma.lias.kmade.tool.view.worldobject.usersystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEJTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;


public class KMADEReadParcInMachine extends JScrollPane implements LanguageFactory {

	private static final long serialVersionUID = 3255024943462709826L;

	private KMADEJTable table;

	private myModel modele;

	public KMADEReadParcInMachine(){
		modele = new myModel();
		table = new KMADEJTable(modele);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setViewportView(table);
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE); 
		table.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent ke) { 
				// on enleve l'individu des organisations selectionn�es
				if (ke.getKeyChar() == KeyEvent.VK_DELETE || ke.getKeyChar() == KeyEvent.VK_ENTER) {
					int r[] = ((JTable)ke.getSource()).getSelectedRows();
					for(int i =0;i<r.length;i++){
						String oidOrga = (String) modele.getValueAt(r[i],4);
						KMADEMainFrame.getProjectPanel().getMachinePanel().removeParcMachinesToActiveMachine(oidOrga);
					}
					KMADEMainFrame.getProjectPanel().getMachinePanel().refreshActiveMachine();

				}

			}

			public void keyReleased(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {

			}
		});
		table.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
			      { 
					int r = ((JTable)e.getSource()).getSelectedRow();
					String oidOrga = (String) modele.getValueAt(r,4);
					KMADEMainFrame.getProjectPanel().getMachinePanel().removeParcMachinesToActiveMachine(oidOrga);
					KMADEMainFrame.getProjectPanel().getMachinePanel().refreshActiveMachine();
			      } 
			}

			public void mousePressed(MouseEvent e) {							
			}

			public void mouseReleased(MouseEvent e) {							
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
			
		});
	}

	
	public void setData(Object[][] tab) {		
		modele.setData(tab);
		modele.fireTableDataChanged();        
	}

	static class myModel extends AbstractTableModel {
		static final long serialVersionUID = 132585986L;

		private Object[][] data = new Object[0][];

		public int getColumnCount() {
			return 4;
		}

		public int getRowCount() {
			return (data == null ? 0 : data.length);
		}

		public Object getValueAt(int param, int param1) {
			return data[param][param1];
		}

		public String getColumnName(int i) {
			switch (i) {
			case 0 : return KMADEConstant.PARCMACHINES_NAME_TABLE;
			case 1 : return KMADEConstant.PARCMACHINES_DESCRIPTION_TABLE;
			case 2 : return KMADEConstant.PARCMACHINES_IMAGE_TABLE;
			case 3 : return KMADEConstant.PARCMACHINES_MACHINE_TABLE;
			default : return "";
			}
		}

		public void setData(Object[][] data) {
			this.data = data;
		}
	}



	class MyTableListModel extends DefaultListTableModel {

		private static final long serialVersionUID = 8773587010073998732L;

		public MyTableListModel(String[] columnNames, String nameDefault) {
			super(columnNames, nameDefault);
		}
		public boolean isCellEditable(int iRowIndex, int iColumnIndex)
		{
			return false;
		}
	}


	public void notifLocalisationModification() {
	}

	public void clearSelection() {
		table.getSelectionModel().clearSelection();		
	}

	public void setParcMachinesNameBorder(String name) {
		this.setBorder(new TitledBorder(null,KMADEConstant.MACHINE_OTHERS +" "+ KMADEConstant.MACHINE_PARCS_TABLE + " : " + name,
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

	}

	public void removeAllParc() {
		table.removeAll();
		modele.setData(new Object[0][]);
	}

	public void updateDataModel(Object[][] o) {
		this.removeAllParc();
		//les organisations {oid, name, descritpion, image path,nom des machines}
		Object[][] temp = new Object[o.length][5];
		for (int i = 0; i < o.length; i++) {

			// nom
			temp[i][0] = (String) o[i][1];
			//descritpion
			temp[i][1] = (String) o[i][2];
			//image path
			temp[i][2] = (String) o[i][3];
			//nom des membres
			temp[i][3] = (String) o[i][4];
			// oid
			temp[i][4] = (String) o[i][0];
		}
		modele.setData(temp);
		modele.fireTableDataChanged();

	}


}
