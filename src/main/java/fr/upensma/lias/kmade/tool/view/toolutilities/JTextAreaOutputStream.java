package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class JTextAreaOutputStream extends OutputStream {
	private JTextArea m_text_area = null;

	/**
     * Method JTextAreaOutputStream.
     * @param p_text_area le JTextArea qui recevra les caract�res.
     */
	public JTextAreaOutputStream(JTextArea p_text_area) {
		m_text_area = p_text_area;
	}

    /**
     * �crit un caract�re dans le JTextArea.
     * Si le caract�re est un retour chariot, scrolling.
     * @see java.io.OutputStream#write(int)
     */
	public void write(int b) throws IOException {
		byte[] bytes = new byte[1];
		bytes[0] = (byte)b;
		String new_text = new String(bytes);
		m_text_area.append(new_text);
		if (new_text.indexOf('\n') > -1) {
			try {
				m_text_area.scrollRectToVisible(m_text_area.modelToView(
                        m_text_area.getDocument().getLength()));
			} catch (javax.swing.text.BadLocationException err) {
				err.printStackTrace();
			}
		}
	}

	/**
	 * �crit un tableau de bytes dans le JTextArea.
	 * Scrolling du JTextArea � la fin du texte ajout�.
	 * @see java.io.OutputStream#write(byte[])
	 */
	public final void write(byte[] arg0) throws IOException {
		String txt = new String(arg0);
		m_text_area.append(txt);
		try {
			m_text_area.scrollRectToVisible(m_text_area.modelToView(m_text_area.getDocument().getLength()));
		} catch (javax.swing.text.BadLocationException err) {
			err.printStackTrace();
		}
	}
}
