/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.view.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEGraphCellEditor;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.SearchAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEFindReplaceDialog extends JDialog {

    private static final long serialVersionUID = -7002552898695964908L;

    private JRadioButton forwardButton;

    private JRadioButton backwardButton;

    private JRadioButton allButton;

    private JRadioButton taskSelectedButton;

    private JCheckBox caseSensitiveButton;

    private JCheckBox wholeWordButton;

    private JCheckBox regularExpressions;

    private JButton findButton;

    private JButton findReplaceButton;

    private JButton replaceButton;

    private JButton replaceAll;

    private JButton close;

    private JButton saveOccurence;

    private DefaultListModel myListModel;

    private JList myList;

    private CriteriaPanel find;

    private CriteriaPanel replace;

    public KMADEFindReplaceDialog(Frame owner) {
	super(owner, KMADEConstant.SEARCH_MESSAGE_TITLE, false);
	find = new CriteriaPanel(KMADEConstant.FIND_MESSAGE, false);
	find.getNameFind().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.find(find.getFindName(),
			find.getExecutantValue(), find.getDecompositionValue(),
			find.getInterruptibleIndex(), find.getOptionalIndex(),
			regularExpressions.isSelected(),
			caseSensitiveButton.isSelected(),
			wholeWordButton.isSelected(), allButton.isSelected());
	    }
	});

	replace = new CriteriaPanel(KMADEConstant.REPLACE_MESSAGE, true);
	replace.getNameFind().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.find(find.getFindName(),
			find.getExecutantValue(), find.getDecompositionValue(),
			find.getInterruptibleIndex(), find.getOptionalIndex(),
			regularExpressions.isSelected(),
			caseSensitiveButton.isSelected(),
			wholeWordButton.isSelected(), allButton.isSelected());
	    }
	});

	JPanel panelCriteria = new JPanel(new GridLayout(2, 1));
	panelCriteria.add(find);
	panelCriteria.add(replace);

	JPanel panelCenter = new JPanel();
	panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
	panelCenter.add(panelCriteria);

	JPanel panelAllOptions = new JPanel();
	panelAllOptions.setLayout(new BoxLayout(panelAllOptions,
		BoxLayout.Y_AXIS));
	JPanel panelAllUpOptions = new JPanel(new GridLayout(1, 2));

	// JPanel pour la direction
	JPanel panelDirection = new JPanel(new GridLayout(2, 1));
	panelDirection.setBorder(BorderFactory
		.createTitledBorder(KMADEConstant.DIRECTION_FIND_MESSAGE));
	forwardButton = new JRadioButton(
		KMADEConstant.FORWARD_DIRECTION_FIND_MESSAGE);
	forwardButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.setToforward();
	    }
	});
	forwardButton.setSelected(true);

	backwardButton = new JRadioButton(
		KMADEConstant.BACKWARD_DIRECTION_FIND_MESSAGE);
	backwardButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.setToBackward();
	    }
	});
	ButtonGroup myGroupWard = new ButtonGroup();
	myGroupWard.add(forwardButton);
	myGroupWard.add(backwardButton);
	panelDirection.add(forwardButton);
	panelDirection.add(backwardButton);

	// JPanel pour le scope
	JPanel panelScope = new JPanel(new GridLayout(2, 1));
	panelScope.setBorder(BorderFactory
		.createTitledBorder(KMADEConstant.SCOPE_FIND_MESSAGE));
	ButtonGroup myButtonGroup = new ButtonGroup();
	allButton = new JRadioButton(KMADEConstant.ALL_FIND_MESSAGE);
	allButton.setSelected(true);
	taskSelectedButton = new JRadioButton(
		KMADEConstant.SELECTION_FIND_MESSAGE);
	myButtonGroup.add(allButton);
	myButtonGroup.add(taskSelectedButton);
	panelScope.add(allButton);
	panelScope.add(taskSelectedButton);

	// JPanel pour les options
	JPanel panelOptions = new JPanel(new GridLayout(2, 2));
	panelOptions.setBorder(BorderFactory
		.createTitledBorder(KMADEConstant.OPTION_FIND_MESSAGE));
	caseSensitiveButton = new JCheckBox(
		KMADEConstant.CASE_SENSITIVE_OPTIONS_FIND_MESSAGE);
	wholeWordButton = new JCheckBox(
		KMADEConstant.WHOLE_TASK_NAME_OPTIONS_FIND_MESSAGE);
	regularExpressions = new JCheckBox(
		KMADEConstant.REGULAR_EXPRESSIONS_OPTIONS_FIND_MESSAGE);
	regularExpressions.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (regularExpressions.isSelected()) {
		    wholeWordButton.setEnabled(false);
		} else {
		    wholeWordButton.setEnabled(true);
		}
	    }
	});
	panelOptions.add(caseSensitiveButton);
	panelOptions.add(wholeWordButton);
	panelOptions.add(regularExpressions);

	panelAllUpOptions.add(panelDirection);
	panelAllUpOptions.add(panelScope);

	panelAllOptions.add(panelAllUpOptions);
	panelAllOptions.add(panelOptions);

	panelCenter.add(panelAllOptions);

	myListModel = new DefaultListModel();
	myList = new JList(myListModel);
	myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	myList.addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent e) {
		if (myList.getSelectedValue() instanceof Task) {
		    SearchAdaptator.selectTasksFromOccurence(
			    ((Task) (myList.getSelectedValue())),
			    myList.getSelectedIndex());
		}
	    }
	});
	myList.setCellRenderer(new MyListCellRenderer());
	JScrollPane myScrollPane = new JScrollPane(myList);
	myScrollPane.setBorder(BorderFactory
		.createTitledBorder(KMADEConstant.SEARCH_RESULT_FIND_MESSAGE));
	myScrollPane.setPreferredSize(new Dimension(100, 125));
	panelCenter.add(myScrollPane);

	// JPanel pour les actions
	JPanel panelActions = new JPanel(new GridLayout(3, 2, 10, 10));
	findButton = new JButton(KMADEConstant.FIND_ACTION_FIND_MESSAGE);
	findButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.find(find.getFindName(),
			find.getExecutantValue(), find.getDecompositionValue(),
			find.getInterruptibleIndex(), find.getOptionalIndex(),
			regularExpressions.isSelected(),
			caseSensitiveButton.isSelected(),
			wholeWordButton.isSelected(), allButton.isSelected());
	    }
	});

	saveOccurence = new JButton(
		KMADEConstant.SAVE_OCCURENCES_ACTION_FIND_MESSAGE);
	saveOccurence.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.resultatSearch(find.getFindName(),
			find.getExecutantValue(), find.getDecompositionValue(),
			find.getInterruptibleString(),
			find.getOptionalString(),
			regularExpressions.isSelected(),
			caseSensitiveButton.isSelected(),
			wholeWordButton.isSelected(), allButton.isSelected());
	    }
	});

	findReplaceButton = new JButton(
		KMADEConstant.REPLACE_NEXT_ACTION_FIND_MESSAGE);
	findReplaceButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.replaceAndNext(find.getFindName(),
			replace.isWholeNameChecked(), replace.getFindName(),
			replace.getExecutantValue(),
			replace.getDecompositionValue(),
			replace.getInterruptibleIndex(),
			replace.getOptionalIndex());
	    }
	});
	replaceButton = new JButton(KMADEConstant.REPLACE_ACTION_FIND_MESSAGE);
	replaceButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.replace(find.getFindName(),
			replace.isWholeNameChecked(), replace.getFindName(),
			replace.getExecutantValue(),
			replace.getDecompositionValue(),
			replace.getInterruptibleIndex(),
			replace.getOptionalIndex());
	    }
	});
	replaceAll = new JButton(KMADEConstant.REPLACE_ALL_ACTION_FIND_MESSAGE);
	panelActions.add(findButton);
	panelActions.add(findReplaceButton);
	panelActions.add(replaceButton);
	panelActions.add(replaceAll);
	panelActions.add(saveOccurence);
	JPanel panelActionsFlottant = new JPanel();
	panelActionsFlottant.add(panelActions);
	panelCenter.add(panelActionsFlottant);

	close = new JButton(KMADEConstant.GO_BACK_MESSAGE);
	close.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SearchAdaptator.closeFindReplaceDialog();
	    }
	});

	this.getContentPane().add(BorderLayout.CENTER, panelCenter);

	JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
	panelSouth.add(close);
	this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
	this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
	this.pack();
	this.setLocation(250, 250);
	KMADEToolUtilities.setCenteredInScreen(this);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		SearchAdaptator.closeFindReplaceDialog();
	    }
	});
    }

    public void clearElement() {
	myListModel.removeAllElements();
    }

    public void addElement(ArrayList<Task> p) {
	myListModel.removeAllElements();
	for (Task current : p) {
	    myListModel.addElement(current);
	}
    }

    public void setNextButtonName() {
	findReplaceButton
		.setText(KMADEConstant.REPLACE_NEXT_ACTION_FIND_MESSAGE);
    }

    public void setPreviousButtonName() {
	findReplaceButton
		.setText(KMADEConstant.REPLACE_PREVIOUS_ACTION_FIND_MESSAGE);
    }

    public void addOneElement(String p) {
	myListModel.removeAllElements();
	myListModel.addElement(p);
    }

    public void selectOccurenceAt(int p) {
	myList.setSelectedIndex(p);
    }

    public void selectNoOccurence() {
	myList.clearSelection();
    }

    public void enableReplaceButtons() {
	this.findReplaceButton.setEnabled(true);
	this.replaceButton.setEnabled(true);
	this.replaceAll.setEnabled(true);
	this.saveOccurence.setEnabled(true);
    }

    public void disableReplaceButtons() {
	this.findReplaceButton.setEnabled(false);
	this.replaceButton.setEnabled(false);
	this.replaceAll.setEnabled(false);
	this.saveOccurence.setEnabled(false);
    }

    static class MyListCellRenderer implements ListCellRenderer {
	public Component getListCellRendererComponent(JList list, Object value,
		int index, boolean isSelected, boolean cellHasFocus) {
	    String stringToDisplay = "";
	    JLabel myLabel = new JLabel();
	    if (value instanceof Task) {
		Task myTemp = (Task) value;
		stringToDisplay = (index + 1) + " : " + myTemp.getNumber()
			+ " - " + myTemp.getName();

		myLabel.setOpaque(true);

		if (isSelected) {
		    myLabel.setBackground(list.getSelectionBackground());
		    myLabel.setForeground(list.getSelectionForeground());
		} else {
		    myLabel.setBackground(list.getBackground());
		    myLabel.setForeground(list.getForeground());
		}
	    } else if (value instanceof String) {
		stringToDisplay = (String) value;
	    }

	    myLabel.setText(stringToDisplay);
	    return myLabel;
	}
    }

    class CriteriaPanel extends JPanel {

	private static final long serialVersionUID = -1290425752793293655L;

	private JComboBox myCategorieFind = new JComboBox();

	private JComboBox myFaculFind = new JComboBox();

	private JComboBox myInterFind = new JComboBox();

	private JTextField myNameFind = new JTextField();

	private JComboBox myDecompoFind = new JComboBox();

	private JCheckBox wholeName;

	private String[] executantName;

	private ImageIcon[] executantImage;

	public JTextField getNameFind() {
	    return myNameFind;
	}

	public boolean isWholeNameChecked() {
	    if (wholeName != null) {
		return this.wholeName.isSelected();
	    } else {
		return false;
	    }
	}

	public int getOptionalIndex() {
	    return myFaculFind.getSelectedIndex();
	}

	public String getOptionalString() {
	    return (String) myFaculFind.getSelectedItem();
	}

	public int getInterruptibleIndex() {
	    return myInterFind.getSelectedIndex();
	}

	public String getInterruptibleString() {
	    return (String) myInterFind.getSelectedItem();
	}

	public String getExecutantValue() {
	    return (String) myCategorieFind.getSelectedItem();
	}

	public String getFindName() {
	    return myNameFind.getText();
	}

	public String getDecompositionValue() {
	    return (String) myDecompoFind.getSelectedItem();
	}

	public CriteriaPanel(String border, boolean isToReplace) {
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	    // Le JPanel pour le nom
	    JPanel panelNameSearch = new JPanel(new BorderLayout(5, 0));
	    panelNameSearch.add(BorderLayout.WEST, new JLabel(
		    KMADEConstant.SEARCH_NAME_NAME + " "));
	    panelNameSearch.add(BorderLayout.CENTER, myNameFind);

	    if (isToReplace) {
		wholeName = new JCheckBox(
			KMADEConstant.WHOLE_TASK_NAME_OPTION_REPLACE_MESSAGE);
		panelNameSearch.add(BorderLayout.EAST, wholeName);
	    }

	    this.add(panelNameSearch);
	    this.add(Box.createRigidArea(new Dimension(0, 15)));

	    // Le JPanel pour l'executant et l'opérateur
	    executantName = Executor.getNameLocaleExecutant();
	    executantImage = new ImageIcon[executantName.length];
	    String[] executantTempImage = Executor.getImageLocaleExecutant();
	    for (int i = 0; i < executantName.length; i++) {
		executantImage[i] = new ImageIcon(
			KMADEGraphCellEditor.class
				.getResource(executantTempImage[i]));
	    }
	    JPanel panelExOp = new JPanel();
	    panelExOp.setLayout(new BoxLayout(panelExOp, BoxLayout.X_AXIS));
	    this.add(panelExOp);

	    JPanel panelExecutant = new JPanel(new BorderLayout(5, 0));
	    myCategorieFind = new JComboBox(executantName);
	    myCategorieFind.setRenderer(new ComboBoxRenderer());
	    myCategorieFind.addItem("*");
	    myCategorieFind.setSelectedIndex(executantName.length);
	    panelExecutant.add(BorderLayout.WEST, new JLabel(
		    KMADEConstant.EDITOR_EXECUTANT_NAME + " "));
	    panelExecutant.add(BorderLayout.CENTER, myCategorieFind);
	    panelExOp.add(panelExecutant);
	    panelExOp.add(Box.createRigidArea(new Dimension(5, 0)));

	    JPanel panelNameDecompo = new JPanel(new BorderLayout(5, 0));
	    panelNameDecompo.add(BorderLayout.WEST, new JLabel(
		    KMADEConstant.EDITOR_OPERATOR_NAME + " "));

	    myDecompoFind = new JComboBox(
		    Decomposition.getNameLocaleDecomposition());
	    myDecompoFind.addItem("*");
	    myDecompoFind.setSelectedIndex(Decomposition
		    .getNameLocaleDecomposition().length);

	    panelNameDecompo.add(BorderLayout.CENTER, myDecompoFind);

	    panelExOp.add(panelNameDecompo);
	    this.add(Box.createRigidArea(new Dimension(0, 15)));

	    // Le JPanel pour l'interruptibilié et l'optinalité
	    JPanel panelFacInt = new JPanel();
	    panelFacInt.setLayout(new BoxLayout(panelFacInt, BoxLayout.X_AXIS));

	    // Le JPanel pour l'optionalité
	    JPanel panelFac = new JPanel(new BorderLayout(5, 0));
	    String[] choix = { "*", KMADEConstant.YES_MESSAGE,
		    KMADEConstant.NO_MESSAGE };
	    myFaculFind = new JComboBox(choix);
	    panelFacInt.add(myFaculFind);
	    myFaculFind.setSelectedIndex(0);
	    panelFac.add(BorderLayout.WEST, new JLabel(
		    KMADEConstant.SEARCH_FAC_NAME + " "));
	    panelFac.add(BorderLayout.CENTER, myFaculFind);
	    panelFacInt.add(panelFac);
	    panelFacInt.add(Box.createRigidArea(new Dimension(5, 0)));

	    // Le JPanel pour l'interruptibilité
	    JPanel panelInt = new JPanel(new BorderLayout(5, 0));
	    myInterFind = new JComboBox(choix);
	    panelFacInt.add(myInterFind);
	    myInterFind.setSelectedIndex(0);
	    panelInt.add(BorderLayout.WEST, new JLabel(
		    KMADEConstant.SEARCH_INT_NAME + " "));
	    panelInt.add(BorderLayout.CENTER, myInterFind);
	    panelFacInt.add(panelInt);

	    this.add(panelFacInt);
	    this.setBorder(BorderFactory.createTitledBorder(border));
	}

	class ComboBoxRenderer extends JLabel implements ListCellRenderer {
	    private static final long serialVersionUID = -5771046098633593675L;

	    public ComboBoxRenderer() {
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	    }

	    public Component getListCellRendererComponent(JList list,
		    Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {
		String maValue = (String) value;

		this.setHorizontalAlignment(JLabel.LEFT);
		if (isSelected) {
		    setBackground(list.getSelectionBackground());
		    setForeground(list.getSelectionForeground());
		} else {
		    setBackground(list.getBackground());
		    setForeground(list.getForeground());
		}
		if (maValue != "*") {

		    int myInt = Executor.getLocaleExecutantAt(maValue);
		    ImageIcon icon = executantImage[myInt];
		    setIcon(icon);
		    setText(maValue);
		} else {
		    setIcon(null);
		    setText("  " + maValue);
		}
		return this;
	    }
	}
    }
}
