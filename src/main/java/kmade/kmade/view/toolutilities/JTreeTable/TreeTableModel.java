package kmade.kmade.view.toolutilities.JTreeTable;

import javax.swing.tree.TreeModel;

public interface TreeTableModel extends TreeModel {
    public Class getColumnClass(int column);
    public int getColumnCount();
    public String getColumnName(int column);
    public Object getValueAt(Object node, int column);
    public boolean isCellEditable(Object node, int column);
    public void setValueAt(Object value, Object node, int column);
    public boolean isCellTreeEditable(Object value);
}