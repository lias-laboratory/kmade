package fr.upensma.lias.kmade.tool.view.prototask;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.coreadaptator.parserkmad.ExpressKMADTXT;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;

public class KMADEProtoTaskSaveScenarioPanel extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2331971899758022471L;

	private JTextArea textArea = new JTextArea();

	private JPanel myContentPane = new JPanel();

	public KMADEProtoTaskSaveScenarioPanel() {
		super();
		this.setClosable(true);
		this.setMaximizable(true);
		this.add(myContentPane);
		myContentPane.validate();
		myContentPane.repaint();

		textArea.append("KMC pas d'arbre");
		JButton selectButton = new JButton("KMC Séléctionner");
		selectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getTextArea().selectAll();
				getTextArea().requestFocus();

			}
		});
		JButton copyButton = new JButton("KMC Copier");
		copyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getTextArea().selectAll();
				getTextArea().requestFocus();
				getTextArea().copy();

			}
		});

		JButton saveButton = new JButton("KMC Enregistrer");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String myCurrentFile = KMADEFileChooser
						.saveKMADModelSimpleFile();
				if (myCurrentFile != null) {
					ExpressKMADTXT.saveKMADSimpleModel(myCurrentFile);
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(selectButton);
		buttonPanel.add(copyButton);
		buttonPanel.add(saveButton);
		JScrollPane scrollpane = new JScrollPane(textArea);
	}

	public void closeSimpleTreePanel() {
		this.setVisible(false);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
