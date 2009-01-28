package kmade.kmade.UI.taskmodel;

import java.awt.Color;
import java.awt.Graphics;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.JComponent;

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
public class KMADECorner extends JComponent implements Accessible {
	private static final long serialVersionUID = -5467864357385427128L;

	public void paintComponent(Graphics g) {
		g.setColor(new Color(165, 163, 151));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public AccessibleContext getAccessibleContext() {
		if (accessibleContext == null) {
			accessibleContext = new  AccessibleCorner();
		}
		return accessibleContext;
	}

	protected class AccessibleCorner extends AccessibleJComponent {
		private static final long serialVersionUID = 7606517429466071101L;
	}
}
