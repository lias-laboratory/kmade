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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * @author Mickael BARON
 */
public class KMADEToolUtilities {
	public static void setCenteredInScreen(Window p_window) {
		Dimension screen_dimension = Toolkit.getDefaultToolkit().getScreenSize();
		p_window.setLocation((int) ((screen_dimension.getWidth() - p_window.getWidth()) / 2),
				(int) ((screen_dimension.getHeight() - p_window.getHeight()) / 2));
	}

	public static JLabel getLabelCenter(String p_texte) {
		JLabel nouveau = new JLabel(p_texte);
		nouveau.setAlignmentX(Component.CENTER_ALIGNMENT);
		return nouveau;
	}

	public static Image getImageThumbnail(String image, int requestedThumbSize) {
		try {
			BufferedImage imageBuffered = ImageIO.read(new File(image));
			if (imageBuffered.getType() == 0) {
				return imageBuffered.getScaledInstance(-1, requestedThumbSize, Image.SCALE_FAST);
			}
			return KMADEToolUtilities.createThumbnail(imageBuffered, requestedThumbSize);
		} catch (Exception e) {
			return null;
		}
	}

	public static Image getImageThumbnail(URL image, int requestedThumbSize) {
		try {
			BufferedImage imageBuffered = ImageIO.read(image);
			if (imageBuffered.getType() == 0) {
				return imageBuffered.getScaledInstance(-1, requestedThumbSize, Image.SCALE_FAST);
			}
			return KMADEToolUtilities.createThumbnail(imageBuffered, requestedThumbSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static BufferedImage createThumbnail(BufferedImage image, int requestedThumbSize) {
		float ratio = (float) image.getWidth() / (float) image.getHeight();
		int height = image.getHeight();
		boolean divide = requestedThumbSize < height;
		BufferedImage thumb = image;

		do {
			if (divide) {
				height /= 2;
				if (height < requestedThumbSize) {
					height = requestedThumbSize;
				}
			} else {
				height *= 2;
				if (height > requestedThumbSize) {
					height = requestedThumbSize;
				}
			}

			BufferedImage temp = new BufferedImage((int) (height * ratio), height, image.getType());
			Graphics2D g2 = temp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
			g2.dispose();

			thumb = temp;
		} while (height != requestedThumbSize);

		return thumb;
	}
}
