package fr.upensma.lias.kmade.tool.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserkmad.ExpressKMADTXT;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * KMADe trunk project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class KMADESimpleTreePanel extends JFrame {

	JTextArea textArea = new JTextArea();
	private JPanel myContentPane;
	private static final ImageIcon SAVE_HISTORY = new ImageIcon(
			GraphicEditorAdaptator.class
			.getResource(KMADEConstant.SAVE_HISTORY_IMAGE));

	public KMADESimpleTreePanel(){
		super("KMC TITLE");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeSimpleTreePanel();
			}
		});
		Dimension screen_dimension = Toolkit.getDefaultToolkit()
				.getScreenSize();
		//TODO dont understant why it is /4 and /2
		this.setLocation(
			(int) ((screen_dimension.getWidth() - this.getWidth()) / 4),
			(int) ((screen_dimension.getHeight() - this.getHeight()) / 4));
		// JPanel for constraint
		this.myContentPane = new JPanel();
		this.setContentPane(myContentPane);

		this.setSize(new Dimension(1000, 700));

		this.validate();
		this.repaint();
		myContentPane.setLayout(new BoxLayout(myContentPane,BoxLayout.LINE_AXIS));
		
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
				String myCurrentFile = KMADEFileChooser.saveKMADModelSimpleFile();
				if(myCurrentFile != null){
					ExpressKMADTXT.saveKMADSimpleModel(myCurrentFile);
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(selectButton);
		buttonPanel.add(copyButton);
		buttonPanel.add(saveButton);
		myContentPane.add(buttonPanel);
		JScrollPane scrollpane = new JScrollPane(textArea);
		myContentPane.add(scrollpane);
	}


	public void closeSimpleTreePanel(){
		this.setVisible(false);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

}
