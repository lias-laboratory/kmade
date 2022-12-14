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

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JWindow;
import javax.swing.Timer;

import fr.upensma.lias.kmade.KMADeMain;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.KMADeAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEToolProjectSplashScreenShadow extends JWindow {
	private static final long serialVersionUID = 6619270146987312917L;

	private BufferedImage splash = null;

	private Timer timer;

	public KMADEToolProjectSplashScreenShadow() {
		try {
			BufferedImage image = ImageIO.read(
					KMADEToolProjectSplashScreenShadow.class.getResourceAsStream(KMADEConstant.SPLASH_SCREEN_IMAGE));
			createShadowPicture(image);
			this.setVisible(true);

			int time = 5000;
			if (KMADeMain.isDebug) {
				time = 0;
			}
			timer = new Timer(time, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					KMADEToolProjectSplashScreenShadow.this.setVisible(false);
					KMADeAdaptator.showAtFirst();
					timer.stop();
				}
			});
			timer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		if (splash != null) {
			g.drawImage(splash, 0, 0, null);
		}
	}

	private void createShadowPicture(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int extra = 14;

		setSize(new Dimension(width + extra, height + extra));
		setLocationRelativeTo(null);
		Rectangle windowRect = getBounds();

		splash = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) splash.getGraphics();

		try {
			Robot robot = new Robot(getGraphicsConfiguration().getDevice());
			BufferedImage capture = robot.createScreenCapture(
					new Rectangle(windowRect.x, windowRect.y, windowRect.width + extra, windowRect.height + extra));
			g2.drawImage(capture, null, 0, 0);
		} catch (AWTException e) {
		}

		BufferedImage shadow = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB);
		Graphics g = shadow.getGraphics();
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		g.fillRoundRect(3, 3, width, height, 10, 10);

		g2.drawImage(shadow, getBlurOp(7), 0, 0);
		g2.drawImage(image, 0, 0, this);
	}

	private ConvolveOp getBlurOp(int size) {
		float[] data = new float[size * size];
		float value = 1 / (float) (size * size);
		for (int i = 0; i < data.length; i++) {
			data[i] = value;
		}
		return new ConvolveOp(new Kernel(size, size, data));
	}
}