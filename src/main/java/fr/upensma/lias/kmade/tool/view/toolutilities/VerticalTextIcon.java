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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author Mickael BARON
 */
public class VerticalTextIcon implements Icon, SwingConstants {

    private Font font = UIManager.getFont("Label.font");

    private FontMetrics fm;

    private String text;

    private int width;

    private boolean clockWize;

    public VerticalTextIcon(String text, boolean clockWize, JPanel cible) {
	this.text = " " + text + " ";
	fm = cible.getFontMetrics(font);
	width = SwingUtilities.computeStringWidth(fm, this.text);
	this.clockWize = clockWize;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
	Graphics2D g2 = (Graphics2D) g;
	Font oldFont = g.getFont();
	Color oldColor = g.getColor();
	AffineTransform oldTransform = g2.getTransform();
	g.setFont(font);
	g.setColor(Color.black);
	if (clockWize) {
	    g2.translate(x + getIconWidth(), y);
	    g2.rotate(Math.PI / 2);
	} else {
	    g2.translate(x - 6, y + getIconHeight());
	    g2.rotate(-Math.PI / 2);
	}
	g.drawString(text, 0, fm.getLeading() + fm.getAscent());
	g.setFont(oldFont);
	g.setColor(oldColor);
	g2.setTransform(oldTransform);

    }

    public int getIconWidth() {
	return 0;
    }

    public int getIconHeight() {
	return width + 2;
    }
}
