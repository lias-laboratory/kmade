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

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * @author Mickael BARON
 */
public class JTextAreaOutputStream extends OutputStream {

	private JTextArea m_text_area = null;

	/**
	 * Method JTextAreaOutputStream.
	 * 
	 * @param p_text_area le JTextArea qui recevra les caract�res.
	 */
	public JTextAreaOutputStream(JTextArea p_text_area) {
		m_text_area = p_text_area;
	}

	/**
	 * �crit un caract�re dans le JTextArea. Si le caract�re est un retour chariot,
	 * scrolling.
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	public void write(int b) throws IOException {
		byte[] bytes = new byte[1];
		bytes[0] = (byte) b;
		// String new_text = new String(bytes);
		String new_text = String.valueOf((char) b);
		m_text_area.append(new_text);

		if (new_text.indexOf('\n') > -1) {
			try {
				m_text_area.scrollRectToVisible(m_text_area.modelToView(m_text_area.getDocument().getLength()));
			} catch (javax.swing.text.BadLocationException err) {
				err.printStackTrace();
			}
		}
	}

	/**
	 * Ecrit un tableau de bytes dans le JTextArea. Scrolling du JTextArea à la fin
	 * du texte ajouté.
	 * 
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
