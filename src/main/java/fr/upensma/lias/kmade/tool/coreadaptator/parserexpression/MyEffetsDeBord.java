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
package fr.upensma.lias.kmade.tool.coreadaptator.parserexpression;

import java.io.InputStream;
import java.io.Reader;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class MyEffetsDeBord extends EffetsDeBord {
    public MyEffetsDeBord(EffetsDeBordTokenManager tm) {
	super(tm);
    }

    public MyEffetsDeBord(InputStream stream, String encoding) {
	super(stream, encoding);
    }

    public MyEffetsDeBord(InputStream stream) {
	super(stream);
    }

    public MyEffetsDeBord(Reader stream) {
	super(stream);
    }

    public ParseException generateParseException() {
	Token errortok = token.next;
	int line = errortok.beginLine, column = errortok.beginColumn;
	String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
	return new ParseException(KMADEConstant.LINE_MESSAGE + " " + line
		+ KMADEConstant.COLUMN_MESSAGE + " " + column + ", "
		+ KMADEConstant.ENCOUNTERED_TOKEN_MESSAGE + "\"" + mess + "\"");
    }

}
