package kmade.kmade.UI;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kmade.KMADeMain;
import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.coherence.KMADECoherenceDialog;
import kmade.kmade.UI.preferences.KMADEPreferencesDialog;
import kmade.kmade.UI.print.KMADEPrintingDialog;
import kmade.kmade.UI.search.KMADEFindReplaceDialog;
import kmade.kmade.UI.simulation.KMADESimulationDialog;
import kmade.kmade.UI.statistic.KMADEStatisticDialog;
import kmade.kmade.UI.taskmodel.KMADEPreviewWindow;
import kmade.kmade.UI.toolutilities.InDevelopmentGlassPanel;
import kmade.kmade.UI.toolutilities.KMADEHistoryMessagePanel;
import kmade.kmade.UI.toolutilities.KMADEInfoHTMLDialog;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.KMADeAdaptator;

/**
 * K-MADe : Kernel of Model for Activity Description environment Copyright (C)
 * 2006 INRIA - MErLIn Project
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * 
 * 
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 */
public class KMADEMainFrame extends JFrame implements LanguageFactory {
	private static final long serialVersionUID = 3538L;

	private static final KMADEProjectPanel PROJECT_PANEL = new KMADEProjectPanel();

	private KMADEProjectDialog projectDialog = new KMADEProjectDialog(this);

	public static float[] BLUR = { 0.10f, 0.10f, 0.10f, 0.10f, 0.30f, 0.10f,
			0.10f, 0.10f, 0.10f };

	public static ConvolveOp blurOp = new ConvolveOp(new Kernel(3, 3, BLUR));

	private KMADEHistoryMessagePanel outputOkMessage = new KMADEHistoryMessagePanel(
			"");

	private KMADEHistoryMessagePanel outputErrorMessage = new KMADEHistoryMessagePanel(
			"");

	private KMADEPrintingDialog printingDialog = new KMADEPrintingDialog(this);

	private KMADEToolAbout aboutDialog = new KMADEToolAbout(this);

	private KMADEInfoHTMLDialog infoDebugDialog = new KMADEInfoHTMLDialog();

	private KMADEInfoHTMLDialog infoKMADDialog = new KMADEInfoHTMLDialog();

	private KMADEInfoHTMLDialog infoKMADEDialog = new KMADEInfoHTMLDialog();

	private KMADEInfoHTMLDialog infoHistoryDialog = new KMADEInfoHTMLDialog();

	private KMADEToolToolBar APPLICATION_TOOL_BAR = new KMADEToolToolBar();

	private KMADEPreviewWindow previewDialog = new KMADEPreviewWindow(this);

	private KMADEClipBoardDialog clipboardDialog = new KMADEClipBoardDialog(
			this);

	private KMADEProgressBarPanel progressBarDialog = new KMADEProgressBarPanel();

	private KMADEPreferencesDialog preferencesDialog = new KMADEPreferencesDialog(
			this);

	private KMADESimulationDialog simulationDialog = new KMADESimulationDialog();

	private KMADEStatisticDialog statisticDialog = new KMADEStatisticDialog(
			this);

	private KMADECoherenceDialog coherenceDialog = new KMADECoherenceDialog(
			this);

	private KMADEEntityDialog entityDialog = new KMADEEntityDialog(this);

	private KMADEFindReplaceDialog findReplaceDialog = new KMADEFindReplaceDialog(
			this);

	private final KMADEStatusBar myStatusBar = new KMADEStatusBar();

	private JTabbedPane myOutputTabbedPane;

	private CardLayout myProjectCard = new CardLayout();

	private JPanel panelHaut = new JPanel();

	private JPanel panelEmpty = new JPanel();
	
	public static final ImageIcon LISI_LOGO = new ImageIcon(
			KMADEMainFrame.class.getResource(KMADEConstant.LISI_LOGO_IMAGE));
	
	public static final ImageIcon INRIA_LOGO = new ImageIcon(
			KMADEMainFrame.class.getResource(KMADEConstant.INRIA_LOGO_IMAGE));

	public static final ImageIcon MERLIN_LOGO = new ImageIcon(
			KMADEMainFrame.class.getResource(KMADEConstant.MERLIN_LOGO_IMAGE));

	protected JSplitPane jSplitPaneBH;

	protected int widthAvant;

	protected int heightAvant;

	protected int locationDividerEstAvant;

	protected int locationDividerSudAvant;

	public KMADEFindReplaceDialog getFindReplaceDialog() {
		return findReplaceDialog;
	}

	public void showEmptyPanel() {

		myProjectCard.show(panelHaut, "PanelEmpty");
		APPLICATION_TOOL_BAR.noProjectViewState();
		clipboardDialog.setVisible(false);
		previewDialog.setVisible(false);
	}

	public void showProjectPanel() {
		myProjectCard.show(panelHaut, "PanelProject");
		APPLICATION_TOOL_BAR.existProjectViewState();
	}

	public void enableSaveTitleName(String p) {
		this.setTitle(KMADEConstant.FENETRE_PRINCIPALE_TITLE_NAME + " - " + p);
		APPLICATION_TOOL_BAR.fileInEditingState();
	}

	public void disableSaveTitleName() {
		this.setTitle(KMADEConstant.FENETRE_PRINCIPALE_TITLE_NAME);
		APPLICATION_TOOL_BAR.noFileInEditingState();
	}

	
	public KMADEMainFrame() {
		super(KMADEConstant.FENETRE_PRINCIPALE_TITLE_NAME);
		

		UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 1, 0));
		this.setIconImage(new ImageIcon(KMADEMainFrame.class
				.getResource(KMADEConstant.ICON_LOGO_IMAGE)).getImage());
		previewDialog.setOverviewGraph(PROJECT_PANEL.getTaskModelPanel()
				.getJGraph());

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(APPLICATION_TOOL_BAR, BorderLayout.NORTH);
		this.setJMenuBar(APPLICATION_TOOL_BAR.getMenuBar());

		// Panel Vide
		panelEmpty = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Dimension size = getSize();

				int w = size.width;
				int h = size.height;

				int gapw = 20;
				int gaph = 20;
				g.drawImage(LISI_LOGO.getImage(), w - (100 + gapw), h - 73
						- gaph, 100, 90, this);// 50,100
				g.drawImage(INRIA_LOGO.getImage(),   gapw, h - 22
						- 73 - gaph - gaph, 100, 22, this);// 50,100
				g.drawImage(MERLIN_LOGO.getImage(),   gapw, h - 73
						- gaph, 100, 73, this);// 90,100
			}

			private static final long serialVersionUID = 0L;

		};
		panelEmpty.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;

		JPanel panelProject = new JPanel() {

			private static final long serialVersionUID = -5770040028117937897L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				Font font = g.getFont();

				Dimension size = getSize();
				int h = size.height;
				int w = size.width;
				int arc = 0;

				if (size.width > 300) {
					font = font.deriveFont(Font.PLAIN, 48);
					arc = 20;
				} else if (size.width > 150) {
					font = font.deriveFont(Font.PLAIN, 24);
					arc = 10;
				} else {
					font = font.deriveFont(Font.PLAIN, 12);
					arc = 3;
				}

				int preferredWidth = size.width;
				int preferredHeight = size.height;

				w = Math.min(preferredWidth, w);
				h = Math.min(preferredHeight, h);

				// Create the path that runs through the rounded rectangle
				float h2 = h / 2;
				float h4 = h / 4;

				GeneralPath path = new GeneralPath();
				path.moveTo(0, h);
				path.curveTo(0, h - h4, h4, h2, h2, h2);
				path.lineTo(w - h2, h2);
				path.curveTo(w - h4, h2, w, h4, w, 0);
				path.lineTo(w, h);

				// Create a buffered image to paint the rounded rectangle
				BufferedImage vBuffer = new BufferedImage(preferredWidth,
						preferredHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D bg2d = vBuffer.createGraphics();
				bg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				// Paint the background
				RoundRectangle2D fillArea = new RoundRectangle2D.Double(0.0D,
						0.0D, w, h, arc, arc);
				bg2d.setClip(fillArea);

				Area area = new Area(fillArea);
				area.subtract(new Area(path));

				Color vStartColor = new Color(10, 0, 40);
				Color vEndColor = new Color(175, 165, 225);

				Paint p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h,
						vEndColor);
				bg2d.setPaint(p);

				bg2d.fill(area);

				vStartColor = new Color(5, 0, 50);
				vEndColor = new Color(105, 100, 155);

				p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h,
						vEndColor);
				bg2d.setPaint(p);

				bg2d.fill(path);

				// Blur the background
				vBuffer = blurOp.filter(vBuffer, null);

				// Figure out where to place the background and the text
				int insetX = (size.width - w) / 2;
				int insetY = (size.height - h) / 2;

				g2d.translate(insetX, insetY);

				Composite composite = g2d.getComposite();
				g2d.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, .9f));
				Shape clip = g2d.getClip();
				g2d.setClip(fillArea);
				g2d.drawImage(vBuffer, 0, 0, null);
				g2d.setClip(clip);
				g2d.setComposite(composite);

				// Paint a border around the background since it was clipped and
				// the
				// edges
				// weren't anti-aliased
				Color vWrapColor = new Color(175, 165, 225);
				g2d.setColor(vWrapColor);
				g2d.drawRoundRect(0, 0, w, h, arc, arc);

				g2d.translate(-insetX, -insetY);
			}

		};
		panelProject.setLayout(new BorderLayout(10, 30));
		panelProject.setOpaque(false);
		panelProject.setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
		JPanel panelInfo = new JPanel(new BorderLayout(0, 10));
		panelInfo.setOpaque(false);
		JLabel title = new JLabel("KMADe", JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(Font.BOLD, 40));
		title.setForeground(Color.WHITE.brighter());
		panelInfo.add(BorderLayout.CENTER, title);
		panelInfo.add(BorderLayout.NORTH, new JLabel(
				new ImageIcon(KMADEMainFrame.class
						.getResource(KMADEConstant.ICON_LOGO_IMAGE)),
				JLabel.CENTER));
		panelProject.add(BorderLayout.NORTH, panelInfo);

		JPanel panelProjectAction = new JPanel(new GridLayout(1, 2, 40, 5));
		panelProjectAction.setOpaque(false);
		JPanel panelNew = new JPanel(new BorderLayout(2, 5));
		panelNew.setOpaque(false);
		JButton myNewProject = new JButton();
		myNewProject.setOpaque(false);
		myNewProject.setBorder(null);
		myNewProject.setAction(APPLICATION_TOOL_BAR.getProjectAction());
		myNewProject.setIcon(new ImageIcon(KMADEMainFrame.class
				.getResource(KMADEConstant.NEW_PROJECT_BIG_IMAGE)));
		myNewProject.setText("");
		panelNew.add(BorderLayout.CENTER, myNewProject);
		JLabel newProjectLabel = new JLabel(APPLICATION_TOOL_BAR
				.getProjectAction().getValue(AbstractAction.NAME).toString(),
				JLabel.CENTER);
		newProjectLabel.setForeground(Color.WHITE);
		panelNew.add(BorderLayout.SOUTH, newProjectLabel);

		JPanel panelOpen = new JPanel(new BorderLayout(2, 5));
		panelOpen.setOpaque(false);
		JButton myOpenProject = new JButton();
		myOpenProject.setOpaque(false);
		myOpenProject.setBorder(null);
		myOpenProject.setAction(APPLICATION_TOOL_BAR.getLoadAction());
		myOpenProject.setIcon(new ImageIcon(KMADEMainFrame.class
				.getResource(KMADEConstant.OPEN_PROJECT_BIG_IMAGE)));
		myOpenProject.setText("");

		panelOpen.add(BorderLayout.CENTER, myOpenProject);
		JLabel openLabel = new JLabel(APPLICATION_TOOL_BAR.getLoadAction()
				.getValue(AbstractAction.NAME).toString(), JLabel.CENTER);
		openLabel.setForeground(Color.WHITE);
		panelOpen.add(BorderLayout.SOUTH, openLabel);

		panelProjectAction.add(panelNew);
		panelProjectAction.add(panelOpen);
		panelProject.add(BorderLayout.CENTER, panelProjectAction);

		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		panelEmpty.add(panelProject, c);

		panelHaut = new JPanel(myProjectCard);
		panelHaut.add(PROJECT_PANEL, "PanelProject");
		panelHaut.add(panelEmpty, "PanelEmpty");

		JPanel panelSouth = new JPanel(new BorderLayout());

		myOutputTabbedPane = new JTabbedPane();
		myOutputTabbedPane.add(KMADEConstant.MESSAGES_MESSAGE, outputOkMessage);
		myOutputTabbedPane.add(KMADEConstant.ERREURS_MESSAGE,
				outputErrorMessage);

		myOutputTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (myOutputTabbedPane.getSelectedIndex() == 0) {

				} else {

				}
				myOutputTabbedPane.repaint();
			}
		});
		panelSouth.add(BorderLayout.CENTER, myOutputTabbedPane);
		panelSouth.add(BorderLayout.SOUTH, myStatusBar);

		jSplitPaneBH = new JSplitPane();
		jSplitPaneBH.setTopComponent(panelHaut);
		jSplitPaneBH.setOneTouchExpandable(true);
		jSplitPaneBH.setBottomComponent(panelSouth);
		jSplitPaneBH.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPaneBH.setResizeWeight(1);
		this.getContentPane().add(jSplitPaneBH, BorderLayout.CENTER);

		// Redirection du flux des messages.
		this.activeMessageStream();

		this.pack();
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setSize(new Dimension(tailleEcran));

		// this.setSize(440, 400);

		widthAvant = this.getWidth();
		heightAvant = this.getHeight();
		KMADEToolUtilities.setCenteredInScreen(this);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(true);
		// //////////rajout�/////////////////////
		PROJECT_PANEL.getTaskDescriptionPanel().getJSplitPaneV()
				.setDividerLocation(this.getWidth());
		jSplitPaneBH.setDividerLocation(this.getHeight());

		locationDividerEstAvant = PROJECT_PANEL.getTaskDescriptionPanel()
				.getJSplitPaneV().getDividerLocation();
		locationDividerSudAvant = jSplitPaneBH.getDividerLocation();

		// //////////////////////////////////////
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				KMADeAdaptator.closeApplication();
			}
		});
		previewDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().getEditorsToolBar()
						.setSelectedOverviewWindowToggleButton(false);
			}
		});

		this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
	}

	public static boolean isTaskEditorVisible() {
		return PROJECT_PANEL.isTaskEditorVisible();
	}

	public void setDefaultMessagesTitle() {
		myOutputTabbedPane.setTitleAt(0, KMADEConstant.MESSAGES_MESSAGE);
	}

	public void setDefaultErrorsTitle() {
		myOutputTabbedPane.setTitleAt(1, KMADEConstant.ERREURS_MESSAGE);
	}

	public void setMessagesTitle() {
		if (myOutputTabbedPane.getSelectedIndex() == 1) {
			myOutputTabbedPane.setTitleAt(0, KMADEConstant.MESSAGES_MESSAGE
					+ "(!)");
		}
	}

	public void setErreursTitle() {
		if (myOutputTabbedPane.getSelectedIndex() == 0) {
			myOutputTabbedPane.setTitleAt(1, KMADEConstant.ERREURS_MESSAGE
					+ "(!)");
		}
	}

	public static KMADEProjectPanel getProjectPanel() {
		return PROJECT_PANEL;
	}

	public KMADEPrintingDialog getPrintingFrame() {
		return printingDialog;
	}

	/**
	 * @return Returns the aPPLICATION_TOOL_BAR.
	 */
	public KMADEToolToolBar getApplicationToolBar() {
		return APPLICATION_TOOL_BAR;
	}

	public KMADEToolAbout getAboutDialog() {
		return aboutDialog;
	}

	public KMADEInfoHTMLDialog getInfoDebugDialog() {
		return infoDebugDialog;
	}

	public KMADEInfoHTMLDialog getInfoNMDADialog() {
		return infoKMADDialog;
	}

	public KMADEInfoHTMLDialog getInfoKMADEDialog() {
		return infoKMADEDialog;
	}

	public KMADEInfoHTMLDialog getInfoHistoryDialog() {
		return infoHistoryDialog;
	}

	/**
	 * @return Returns the pREVIEW_WINDOW.
	 */
	public KMADEPreviewWindow getPreviewWindow() {
		return previewDialog;
	}

	/**
	 * @return Returns the myOutputTabbedPane.
	 */
	public JTabbedPane getMyOutputTabbedPane() {
		return myOutputTabbedPane;
	}

	/**
	 * @return Returns the cLIPBOARD_DIALOG.
	 */
	public KMADEClipBoardDialog getClipBoardDialog() {
		return clipboardDialog;
	}

	/**
	 * @return Returns the pROGRESS_BAR_DIALOG.
	 */
	public KMADEProgressBarPanel getProgressBarDialog() {
		return progressBarDialog;
	}

	public void activeMessageStream() {
		this.outputOkMessage.setOutputMessage();
		if (!KMADeMain.isDebug) {
			this.outputErrorMessage.setErrputMessage();
		}
	}

	/**
	 * @return Returns the pROJECT_DIALOG.
	 */
	public KMADEProjectDialog getProjectDialog() {
		return projectDialog;
	}

	/**
	 * @return Returns the tOOL_TIP_DIALOG.
	 */
	public KMADEPreferencesDialog getPreferencesDialog() {
		return preferencesDialog;
	}

	public KMADESimulationDialog getSimulationDialog() {
		return simulationDialog;
	}

	public KMADEStatisticDialog getStatisticDialog() {
		return statisticDialog;
	}

	public KMADECoherenceDialog getCoherenceDialog() {
		return coherenceDialog;
	}

	public KMADEEntityDialog getEntityDialog() {
		return entityDialog;
	}

	public void notifLocalisationModification() {
		// NMDAMainFrame
		this.setTitle(KMADEConstant.FENETRE_PRINCIPALE_TITLE_NAME);
		myOutputTabbedPane.setTitleAt(0, KMADEConstant.MESSAGES_MESSAGE);
		myOutputTabbedPane.setTitleAt(1, KMADEConstant.ERREURS_MESSAGE);
		// Les sous-objets
		PROJECT_PANEL.notifLocalisationModification();
		projectDialog.notifLocalisationModification();
		printingDialog.notifLocalisationModification();
		aboutDialog.notifLocalisationModification();
		infoDebugDialog.notifLocalisationModification();
		APPLICATION_TOOL_BAR.notifLocalisationModification();
		previewDialog.notifLocalisationModification();
		clipboardDialog.notifLocalisationModification();
		progressBarDialog.notifLocalisationModification();
		preferencesDialog.notifLocalisationModification();
		myStatusBar.notifLocalisationModification();
	}
}
