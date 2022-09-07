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
package fr.upensma.lias.kmade.tool.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class KMADEToolAnimationPanel extends JComponent implements ActionListener {
	private static final long serialVersionUID = 1398330497163690908L;

	private final static int numImages = 5;

	private Timer animator;

	private ImageIcon icon[] = new ImageIcon[numImages];

	private double x[] = new double[numImages];
	private double y[] = new double[numImages];

	private int xh[] = new int[numImages];
	private int yh[] = new int[numImages];

	double scale[] = new double[numImages];

	public void stopAnimator() {
		animator.stop();
	}

	public KMADEToolAnimationPanel() {
		setBackground(Color.white);
		icon[0] = KMADEMainFrame.INRIA_LOGO;
		icon[1] = KMADEMainFrame.MERLIN_LOGO;
		icon[2] = KMADEMainFrame.LISI_LOGO;
		icon[3] = new ImageIcon(this.getClass().getResource(KMADEConstant.BARON_IMAGE));
		icon[4] = new ImageIcon(this.getClass().getResource(KMADEConstant.SCAPIN_IMAGE));
		this.setPreferredSize(new Dimension(500, 200));
	}

	public void go() {
		animator = new Timer(22 + 22 + 22, this);
		animator.start();
	}

	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < numImages; i++) {
			if (x[i] > 3 * i) {
				nudge(i);
				squish(g, icon[i], xh[i], yh[i], scale[i]);
			} else {
				x[i] += .05;
				y[i] += .05;
			}
		}
	}

	Random rand = new Random();

	public void nudge(int i) {
		x[i] += (double) rand.nextInt(1000) / 8756;
		y[i] += (double) rand.nextInt(1000) / 5432;
		int tmpScale = (int) (Math.abs(Math.sin(x[i])) * 10);
		scale[i] = (double) tmpScale / 10;
		int nudgeX = (int) (((double) getWidth() / 2) * .8);
		int nudgeY = (int) (((double) getHeight() / 2) * .60);
		xh[i] = (int) (Math.sin(x[i]) * nudgeX) + nudgeX;
		yh[i] = (int) (Math.sin(y[i]) * nudgeY) + nudgeY;
	}

	public void squish(Graphics g, ImageIcon icon, int x, int y, double scale) {
		if (isVisible()) {
			g.drawImage(icon.getImage(), x, y, (int) (icon.getIconWidth() * scale),
					(int) (icon.getIconHeight() * scale), this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}