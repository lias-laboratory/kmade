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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.awt.Color;
import java.awt.Graphics;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.JComponent;

/**
 * @author Mickael BARON
 */
public class KMADECorner extends JComponent implements Accessible {

	private static final long serialVersionUID = -5467864357385427128L;

	public void paintComponent(Graphics g) {
		g.setColor(new Color(165, 163, 151));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public AccessibleContext getAccessibleContext() {
		if (accessibleContext == null) {
			accessibleContext = new AccessibleCorner();
		}
		return accessibleContext;
	}

	protected class AccessibleCorner extends AccessibleJComponent {
		private static final long serialVersionUID = 7606517429466071101L;
	}
}
