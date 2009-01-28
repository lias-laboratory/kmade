package kmade.kmade.UI.toolutilities;

import quicktime.QTException;
import quicktime.QTSession;

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