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

import quicktime.QTException;
import quicktime.QTSession;

/**
 * @author Mickael BARON
 */
public class QuickTimeSessionCheck {

    private Thread shutdownHook;

    private static QuickTimeSessionCheck instance;

    private QuickTimeSessionCheck() throws QTException {
	QTSession.open();
	// Create Shutdown Handler
	shutdownHook = new Thread() {
	    public void run() {
		// QTSession.close();
		QTSession.exitMovies();
	    }
	};
	Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    private static QuickTimeSessionCheck getInstance() throws QTException {
	if (instance == null)
	    instance = new QuickTimeSessionCheck();
	return instance;
    }

    public static void check() throws QTException {
	getInstance();
    }

    public static void main(String[] args) {
	try {
	    QuickTimeSessionCheck.check();
	    System.exit(0);
	} catch (QTException qte) {
	    qte.printStackTrace();
	}
    }
}