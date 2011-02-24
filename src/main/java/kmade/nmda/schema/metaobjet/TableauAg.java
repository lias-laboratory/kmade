package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;

import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;

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
 * @author Thomas Lachaume and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class TableauAg extends Agregat{

	private static final long serialVersionUID = -1957369154765575357L;

	    public TableauAg() {
	        this.lstObjConcrets = new ArrayList<ObjetConcret>();
	        this.agregatStruct = AgregatStructure.ARRAY_AGREGAT;
	    }

	    public TableauAg(Oid oid) {
	        this.lstObjConcrets = new ArrayList<ObjetConcret>();
	        this.agregatStruct = AgregatStructure.ARRAY_AGREGAT;
	        this.oid = oid;
	    }

	    public TableauAg(ArrayList<ObjetConcret> lst, Oid oid) {
	        this.lstObjConcrets = lst;
	        this.oid = oid;
	        this.agregatStruct = AgregatStructure.ARRAY_AGREGAT;
	    }

	    public void setOid(Oid oid) {
	        this.oid = oid;
	    }

	    public boolean put(ObjetConcret i) {
	        this.lstObjConcrets.add(i);
	        return true;
	    }

	    public ObjetConcret get() {
	        try {
	            return this.lstObjConcrets.get(0);
	        } catch (java.lang.IndexOutOfBoundsException e) {
	            return null;
	        }
	    }

	    public String toSPF() {
	        String SPF = this.oid.get() + "=TableauAg();";
	        return SPF;
	    }
	    
	    public org.w3c.dom.Element toXML(Document doc) {
	        org.w3c.dom.Element racine = super.toXML(doc);
	        racine.setAttribute("classkmad", "metaobjet.TableauAg");
	        return racine;
	    }
	    
	    public String getName() {
			return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.ARRAY_AGREGAT.getValue());
		}    
	}
