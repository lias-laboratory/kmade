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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class Condition implements Entity {

    private static final long serialVersionUID = 5216681248489889199L;

    @Override
    public Element toXML2(Document doc) throws Exception {
	return null;
    }

    @Override
    public boolean oidIsAnyMissing(Element p) throws Exception {
	return false;
    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	return false;
    }

    @Override
    public void createObjectFromXMLElement(Element p) throws Exception {
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
    }

    @Override
    public String toSPF() {
	return null;
    }

    @Override
    public void setOid(Oid oid) {
    }

    @Override
    public Oid getOid() {
	return null;
    }

    @Override
    public String getName() {
	return null;
    }
}
