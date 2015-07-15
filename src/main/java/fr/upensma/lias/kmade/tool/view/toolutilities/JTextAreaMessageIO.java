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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import javax.swing.JTextArea;

/**
 * @author Thomas LACHAUME
 */
public class JTextAreaMessageIO extends JTextArea implements MessageIO {

    private static final long serialVersionUID = -6043303314605144118L;

    @Override
    public void printlnMessage(String message) {
	message += "\n";
	this.append(message);
    }

    @Override
    public void printMessage(String message) {
	this.append(message);
    }

    @Override
    public void printlnError(String message) {
	message += "\n";
	this.append(message);
    }

    @Override
    public void setOutputMessage() {
	KMADEHistoryMessageManager.setOutputMessage(this);
    }

    @Override
    public void setErrputMessage() {
	KMADEHistoryMessageManager.setErrputMessage(this);
    }

    @Override
    public void printlnError(Throwable e) {
	this.append(e.getMessage());
    }
}
