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
package fr.upensma.lias.kmade.tool.view.taskproperties;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesEnhancedEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEEnhancedTaskLabel extends JComponent {

	private static final long serialVersionUID = 6872507322390601841L;

	private int position;

	private String name;

	private String numero;

	public static final int LEFT = 0;

	public static final int TOP = 1;

	public static final int RIGHT = 2;

	public static final int BOTTOM = 3;

	private Font font = KMADEConstant.fontPASSIF;

	private boolean selected = false;

	private boolean clicking = false;

	private static final Image up = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.UP_TASK_IMAGE))
			.getImage();

	private static final Image down = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.DOWN_TASK_IMAGE)).getImage();

	private static final Image left = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.LEFT_TASK_IMAGE)).getImage();

	private static final Image right = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.RIGHT_TASK_IMAGE)).getImage();

	private static final Image no_up = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.NO_UP_TASK_IMAGE)).getImage();

	private static final Image no_down = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.NO_DOWN_TASK_IMAGE)).getImage();

	private static final Image no_left = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.NO_LEFT_TASK_IMAGE)).getImage();

	private static final Image no_right = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.NO_RIGHT_TASK_IMAGE)).getImage();

	public KMADEEnhancedTaskLabel(int p) {
		this.position = p;
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if (selected && KMADEEnhancedTaskLabel.this.isEnabled()) {
					clicking = true;
					KMADEEnhancedTaskLabel.this.repaint();
				} else {
					clicking = false;
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (clicking) {
					TaskPropertiesEnhancedEditorAdaptator.switchToOtherTask(position);
					TaskPropertiesEnhancedEditorAdaptator.setSelectedTaskAttributes();
					clicking = false;
					KMADEEnhancedTaskLabel.this.repaint();
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (KMADEEnhancedTaskLabel.this.isEnabled()) {
					TaskPropertiesEnhancedEditorAdaptator.switchToOtherTask(position);
					TaskPropertiesEnhancedEditorAdaptator.startAnimation();
				}
			}

			public void mouseExited(MouseEvent e) {
				selected = false;
				KMADEEnhancedTaskLabel.this.repaint();
				TaskPropertiesEnhancedEditorAdaptator.stopAnimation();
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
				int dh = KMADEEnhancedTaskLabel.this.getBounds().height;
				int dw = KMADEEnhancedTaskLabel.this.getBounds().width;
				switch (position) {
				case 0: {
					if (e.getY() > ((dh / 2) - 16) && e.getY() < ((dh / 2) + 16)) {
						selected = true;
					} else {
						selected = false;
					}
					break;
				}
				case 1: {
					if (e.getX() > ((dw / 2) - 16) && e.getX() < ((dw / 2) + 16)) {
						selected = true;
					} else {
						selected = false;
					}
					break;
				}
				case 2: {
					if (e.getY() > ((dh / 2) - 16) && e.getY() < ((dh / 2) + 16)) {
						selected = true;
					} else {
						selected = false;
					}
					break;
				}
				case 3: {
					if (e.getX() > ((dw / 2) - 16) && e.getX() < ((dw / 2) + 16)) {
						selected = true;
					} else {
						selected = false;
					}
					break;
				}
				}
				KMADEEnhancedTaskLabel.this.repaint();
				e.consume();
			}
		});
	}

	public Dimension getPreferredSize() {
		return new Dimension(32, 32);
	}

	public void setTaskName(String pname) {
		this.name = pname;
	}

	public void setTaskNum(String pnum) {
		this.numero = pnum;
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int dh = this.getBounds().height;
		int dw = this.getBounds().width;
		FontMetrics fm = this.getFontMetrics(KMADEConstant.fontPASSIF);
		int heighText = fm.getAscent();
		String displayText = name;
		String displayNumero = numero;
		String titleName = KMADEConstant.TASK_NAME_TITLE + " : ";
		String titleNum = KMADEConstant.TASK_NUMBER_SHORT_NAME_TITLE + " : ";
		int widthText = SwingUtilities.computeStringWidth(fm, titleName + displayText);
		int widthNumero = SwingUtilities.computeStringWidth(fm, titleNum + displayNumero);
		g2.setFont(font);
		switch (position) {
		case 0: {
			g2.translate(0, dh / 2);
			if (this.clicking) {
				g2.setPaintMode();
				g2.setColor(UIManager.getColor("Button" + "." + "select"));
				g2.fillRect(0, -16, 32, 32);
			}
			if (this.isEnabled()) {
				g2.drawImage(left, 0, -16, null);
			} else {
				g2.drawImage(no_left, 0, -16, null);
			}
			g2.rotate(-(Math.PI / 2));
			if (dh / 2 < (widthText + 20)) {
				if (displayText.length() > 5) {
					displayText = displayText.substring(0, 5) + "...";
				} else {
					displayText = "...";
				}
			}
			if (dh / 2 < (widthNumero + 20)) {
				if (displayNumero.length() > 5) {
					displayNumero = displayNumero.substring(0, 5) + "...";
				} else {
					displayNumero = "...";
				}
			}
			g2.setColor(Color.black);
			g2.drawString(titleName + displayText, 20, 16);
			g2.drawString(titleNum + displayNumero, 20, heighText + 16);
			break;
		}
		case 1: {
			g2.translate(dw / 2, 0);
			if (this.clicking) {
				g2.setPaintMode();
				g2.setColor(UIManager.getColor("Button" + "." + "select"));
				g2.fillRect(-16, 0, 32, 32);
			}
			if (this.isEnabled()) {
				g2.drawImage(up, -16, 0, null);
			} else {
				g2.drawImage(no_up, -16, 0, null);
			}
			g2.rotate(0);
			if (dw / 2 < (widthText + 20)) {
				if (displayText.length() > 5) {
					displayText = displayText.substring(0, 5) + "...";
				} else {
					displayText = "...";
				}
			}
			if (dw / 2 < (widthNumero + 20)) {
				if (displayNumero.length() > 5) {
					displayNumero = displayNumero.substring(0, 5) + "...";
				} else {
					displayNumero = "...";
				}
			}
			g2.setColor(Color.black);
			g2.drawString(titleName + displayText, 20, 16);
			g2.drawString(titleNum + displayNumero, 20, heighText + 16);
			break;
		}
		case 2: {
			g2.translate(0, dh / 2);
			if (this.clicking) {
				g2.setPaintMode();
				g2.setColor(UIManager.getColor("Button" + "." + "select"));
				g2.fillRect(0, -16, 32, 32);
			}
			if (this.isEnabled()) {
				g2.drawImage(right, 0, -16, null);
			} else {
				g2.drawImage(no_right, 0, -16, null);
			}
			g2.rotate(Math.PI / 2);
			if (dh / 2 < (widthText + 20)) {
				if (displayText.length() > 5) {
					displayText = displayText.substring(0, 5) + "...";
				} else {
					displayText = "...";
				}
			}
			if (dh / 2 < (widthNumero + 20)) {
				if (displayNumero.length() > 5) {
					displayNumero = displayNumero.substring(0, 5) + "...";
				} else {
					displayNumero = "...";
				}
			}
			g2.setColor(Color.black);
			g2.drawString(titleName + displayText, 20, -16);
			g2.drawString(titleNum + displayNumero, 20, -16 + heighText);
			break;
		}
		case 3: {
			g2.drawLine(0, 31, this.getBounds().width, 31);
			g2.translate(dw / 2, 0);
			if (this.clicking) {
				g2.setPaintMode();
				g2.setColor(UIManager.getColor("Button" + "." + "select"));
				g2.fillRect(-16, 0, 32, 32);
			}
			if (this.isEnabled()) {
				g2.drawImage(down, -16, 0, null);
			} else {
				g2.drawImage(no_down, -16, 0, null);
			}
			g2.rotate(0);
			if (dw / 2 < (widthText + 20)) {
				if (displayText.length() > 5) {
					displayText = displayText.substring(0, 5) + "...";
				} else {
					displayText = "...";
				}
			}
			if (dw / 2 < (widthNumero + 20)) {
				if (displayNumero.length() > 5) {
					displayNumero = displayNumero.substring(0, 5) + "...";
				} else {
					displayNumero = "...";
				}
			}
			widthText = SwingUtilities.computeStringWidth(fm, titleName + displayText);
			g2.setColor(Color.black);
			g2.drawString(titleName + displayText, -20 - widthText, 16);
			g2.drawString(titleNum + displayNumero, -20 - widthText, 16 + heighText);
			break;
		}
		}
	}
}
