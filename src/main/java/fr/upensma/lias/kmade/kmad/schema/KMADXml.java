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
package fr.upensma.lias.kmade.kmad.schema;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Mickael BARON
 */
public interface KMADXml {

	// obsolete Element toXML(Document doc) throws Exception;

	// New version of the dtd
	Element toXML2(Document doc) throws Exception;

	boolean oidIsAnyMissing(Element p) throws Exception;

	// New version of the dtd
	boolean oidIsAnyMissing2(Element p) throws Exception;

	// For compatibility with the old version of the dtd
	void createObjectFromXMLElement(Element p) throws Exception;

	// New version of the dtd
	void createObjectFromXMLElement2(Element p) throws Exception;
}
