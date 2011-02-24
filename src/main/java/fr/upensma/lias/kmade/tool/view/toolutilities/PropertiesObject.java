package fr.upensma.lias.kmade.tool.view.toolutilities;

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
public class PropertiesObject {   
    private String name;

    private Object value;

    private Integer type;

    private boolean isEditable;

    private String[] comboValue;

    private String description;
    
    private JPropertiesEditorDialog refDialog;
    
    public PropertiesObject(String pname, String description, String pvalue, int ptype, boolean pIsEditable, String[] pComboValue, JPropertiesEditorDialog pDialog) {
        this.name = pname;
        this.value = pvalue;
        this.type = ptype;
        this.isEditable = pIsEditable;
        this.comboValue = pComboValue;
        this.refDialog = pDialog;
        this.description = description;
    }
    
    public PropertiesObject(String pname, String description, String pvalue, int ptype, boolean pIsEditable, String[] pComboValue) {
        this(pname,description,pvalue,ptype,pIsEditable, pComboValue, null);
    }
    
    public PropertiesObject(String pname, String description, String pvalue, int ptype, boolean pIsEditable) {
        this(pname,description,pvalue,ptype,pIsEditable, null);
    }
    
    /**
     * @return Returns the comboValue.
     */
    public String[] getComboValue() {
        return comboValue;
    }

    /**
     * @param comboValue
     *            The comboValue to set.
     */
    public void setComboValue(String[] comboValue) {
        this.comboValue = comboValue;
    }

    /**
     * @return Returns the isEditable.
     */
    public boolean isEditable() {
        return isEditable;
    }

    /**
     * @param isEditable
     *            The isEditable to set.
     */
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the type.
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return Returns the value.
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return Returns the refDialog.
     */
    public JPropertiesEditorDialog getRefDialog() {
        return refDialog;
    }

    /**
     * @param refDialog The refDialog to set.
     */
    public void setRefDialog(JPropertiesEditorDialog refDialog) {
        this.refDialog = refDialog;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
