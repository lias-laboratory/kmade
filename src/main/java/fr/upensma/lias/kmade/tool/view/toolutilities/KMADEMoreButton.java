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
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEMoreButton extends JToggleButton implements ActionListener {

	private static final long serialVersionUID = 6534942062944585074L;

	public static final ImageIcon MORE = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.MORE_IMAGE));

	private JToolBar toolbar;

	public KMADEMoreButton(final JToolBar toolbar) {
		super(MORE);
		this.toolbar = toolbar;
		addActionListener(this);
		setFocusPainted(false);

		toolbar.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setVisible(!isVisible(toolbar.getComponent(toolbar.getComponentCount() - 1), null));
			}
		});
	}

	private boolean isVisible(Component comp, Rectangle rect) {
		if (rect == null)
			rect = toolbar.getVisibleRect();
		return comp.getLocation().x + comp.getWidth() <= rect.getWidth();
	}

	public void actionPerformed(ActionEvent e) {
		Component[] comp = toolbar.getComponents();
		Rectangle visibleRect = toolbar.getVisibleRect();
		for (int i = 0; i < comp.length; i++) {
			if (!isVisible(comp[i], visibleRect)) {
				JPopupMenu popup = new JPopupMenu();
				for (; i < comp.length; i++) {
					if (comp[i] instanceof AbstractButton) {
						AbstractButton button = (AbstractButton) comp[i];
						if (button.getAction() != null)
							popup.add(button.getAction());
					} else if (comp[i] instanceof JSeparator)
						popup.addSeparator();
				}

				popup.addPopupMenuListener(new PopupMenuListener() {
					public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
						setSelected(false);
					}

					public void popupMenuCanceled(PopupMenuEvent e) {
					}

					public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					}
				});
				popup.show(this, 0, getHeight());
			}
		}
	}
}
