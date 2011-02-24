package kmade.kmade.view.taskproperties;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.MediaAdaptator;
import kmade.kmade.view.toolutilities.DefaultPropertiesTableModel;
import kmade.kmade.view.toolutilities.JPropertiesEditorDialog;
import kmade.kmade.view.toolutilities.KMADEToolUtilities;
import quicktime.app.view.QTJComponent;

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
public class KMADEEditorMultimediaDialog extends JPropertiesEditorDialog implements ActionListener {

	private static final long serialVersionUID = 6070559297867254874L;
	
    private CardLayout cardMovie;
    
    private CardLayout cardMovieInformation;
    
    private JPanel panelMovie;
    
    private JPanel panelInformation;
    
	private JSlider myslider;
	
	private JButton play;
	
	private JButton playMark;
    
	private JButton playStartMarkInit;
	
	private JButton playFinishMarkInit;

	private JButton finishMarkButton;

	private JButton startMarkButton;
	
	private JButton browse;
	
	private JButton removeVideo;
	
	private JLabel timeLabel;
	
	private JPanel panelCodec;
	
	private static final ImageIcon PLAY_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.PLAY_IMAGE));
	
	private static final ImageIcon PAUSE_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.PAUSE_IMAGE));
	
	private static final ImageIcon PLAY_MARK_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.PLAY_MARK_IMAGE));
	
	private static final ImageIcon PAUSE_MARK_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.PAUSE_MARK_IMAGE));
	
	private static final ImageIcon START_MARK_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.START_MARK_IMAGE));
	
	private static final ImageIcon FINISH_MARK_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.FINISH_MARK_IMAGE));
	
	private static final ImageIcon INIT_START_MARK_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.INIT_START_MARK_IMAGE));
	
	private static final ImageIcon INIT_FINISH_MARK_IMAGE = new ImageIcon(KMADEEditorMultimediaDialog.class.getResource(KMADEConstant.INIT_FINISH_MARK_IMAGE));

	private Timer timer;
	
	private JTextField mediaFileTextField;
	
	private JTextField fileSizeTextField;
	
	private JTextField mediaLengthTextField;
	
	private JTextField startMarkTextField;
	
	private JTextField finishMarkTextField;
	
	public KMADEEditorMultimediaDialog() {
        super();
        this.setModal(false);
		JPanel panelCenter = new JPanel(new BorderLayout());
		JPanel panelEast = new JPanel(new BorderLayout());
		panelEast.setPreferredSize(new Dimension(270,100));

		// Le JPanel qui affiche les informations du film
		panelInformation = new JPanel(new BorderLayout());
		panelInformation.setBorder(BorderFactory.createTitledBorder(KMADEConstant.INFORMATION_MEDIA_TITLE_NAME));
		cardMovieInformation = new CardLayout();
		panelInformation.setLayout(cardMovieInformation);
		JPanel panelInformationLabels = new JPanel(new GridLayout(5,1,0,10));
		panelInformationLabels.add(new JLabel(KMADEConstant.MEDIA_FILE_NAME + " : "));
		panelInformationLabels.add(new JLabel(KMADEConstant.MEDIA_FILE_LENGTH + " : "));
		panelInformationLabels.add(new JLabel(KMADEConstant.MEDIA_LENGTH + " : "));
		panelInformationLabels.add(new JLabel(KMADEConstant.MEDIA_START_TAG + " : "));
		panelInformationLabels.add(new JLabel(KMADEConstant.MEDIA_FINISH_TAG + " : "));
		
		JPanel panelUserInputLabels = new JPanel(new GridLayout(5,1,0,10)); 
		panelUserInputLabels.add(mediaFileTextField = new JTextField(""));
		panelUserInputLabels.add(fileSizeTextField = new JTextField(""));
		panelUserInputLabels.add(mediaLengthTextField = new JTextField(""));
		panelUserInputLabels.add(startMarkTextField = new JTextField(""));
		panelUserInputLabels.add(finishMarkTextField = new JTextField(""));
		
		JPanel panelTopInformation = new JPanel(new BorderLayout(5,0));
		panelTopInformation.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelTopInformation.add(BorderLayout.WEST, panelInformationLabels);
		panelTopInformation.add(BorderLayout.CENTER, panelUserInputLabels);
		JPanel panelTopInformationBis = new JPanel(new BorderLayout());
		panelTopInformationBis.add(BorderLayout.NORTH, panelTopInformation);
		panelInformation.add("INFORMATION",panelTopInformationBis);
		
		JPanel panelInformationEmpty = new JPanel(new BorderLayout());
		panelInformationEmpty.add(new JLabel(KMADEConstant.MEDIA_NO_INFORMATION_PANEL_MESSAGE,JLabel.CENTER));
		panelInformation.add("NO_INFORMATION",panelInformationEmpty);
		
		panelEast.add(BorderLayout.CENTER,panelInformation);		
		panelCenter.add(BorderLayout.EAST,panelEast);
		
		// Le JPanel qui gère le film et le contrôle
		JPanel panelMovieControl = new JPanel(new BorderLayout());
		panelMovie = new JPanel();
		panelMovie.setBorder(BorderFactory.createTitledBorder(KMADEConstant.MEDIA_TITLE_NAME));
		cardMovie = new CardLayout();
        panelMovie.setLayout(cardMovie);
        
        JPanel panelEmpty = new JPanel(new BorderLayout());
        panelEmpty.add(new JLabel(KMADEConstant.NO_MEDIA_FILE_MESSAGE,JLabel.CENTER));

        JPanel panelNoCodec = new JPanel(new BorderLayout());
        panelNoCodec.add(new JLabel(KMADEConstant.QUICK_TIME_NO_INSTALLED_ERROR_MESSAGE,JLabel.CENTER));
        
        panelCodec = new JPanel(new BorderLayout());

        panelMovie.add("EMPTY", panelEmpty);
        panelMovie.add("NO_CODEC", panelNoCodec);
        panelMovie.add("CODEC", panelCodec);
       
		panelMovieControl.add(BorderLayout.CENTER,panelMovie);
		JPanel panelControl = new JPanel(new BorderLayout());
		panelControl.setBorder(BorderFactory.createTitledBorder(KMADEConstant.CONTROLE_MEDIA_TITLE_NAME));
		panelMovieControl.add(BorderLayout.SOUTH, panelControl);
		
		myslider = new JSlider(0, 0, 0);
		myslider.setPaintLabels(true);		
		myslider.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				MediaAdaptator.disableMousePressed();
			}

			public void mousePressed(MouseEvent e) {
				MediaAdaptator.enableMousePressed();
			}
		});

		myslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MediaAdaptator.timeProgress(myslider.getValue());
			}
		});
		myslider.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				MediaAdaptator.disableKeyPressed();
			}

			public void keyPressed(KeyEvent e) {
				MediaAdaptator.enableKeyPressed();
			}
		});		
		
		JPanel panelSouthControl = new JPanel(new BorderLayout());
		panelSouthControl.add(BorderLayout.CENTER, myslider);
		panelControl.add(BorderLayout.CENTER, panelSouthControl);		
	
		browse = new JButton(KMADEConstant.BROWSE_DIRECTORY_OR_FILE_MESSAGE);
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MediaAdaptator.openFromBrowse();
            }
        });
		removeVideo = new JButton(KMADEConstant.REMOVE_MESSAGE);
		removeVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MediaAdaptator.removeCurrentMovie();
			}
		});
		
		JPanel panelEastControl = new JPanel(new GridLayout(2,1,10,10));
		panelEastControl.add(browse);
		panelEastControl.add(removeVideo);
		panelControl.add(BorderLayout.EAST, panelEastControl);
		
		JPanel panelActionControl = new JPanel();
		startMarkButton = new JButton(START_MARK_IMAGE);
		startMarkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				MediaAdaptator.startMarkMovieAction();
			}
		});

		play = new JButton(PLAY_IMAGE);
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				MediaAdaptator.playMovie();
			}
		});
		playMark = new JButton(PLAY_MARK_IMAGE);
		playMark.setEnabled(false);
		playMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				MediaAdaptator.playMarkMovie();
			}
		});
		
		finishMarkButton = new JButton(FINISH_MARK_IMAGE);
		finishMarkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				MediaAdaptator.finishMarkMovieAction();
			}
		});
		
		
		playStartMarkInit = new JButton(INIT_START_MARK_IMAGE);
		playStartMarkInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MediaAdaptator.initStartMarkMovie();
			}
		});
		
		panelActionControl.add(playStartMarkInit);
		panelActionControl.add(startMarkButton);
		JPanel playAndTime = new JPanel(new BorderLayout());
		timeLabel = new JLabel("0:00",JLabel.CENTER);
		playAndTime.add(BorderLayout.NORTH, timeLabel);
		JPanel panelPlay = new JPanel();
		panelPlay.add(play);
		panelPlay.add(playMark);
		playAndTime.add(panelPlay);
		panelActionControl.add(playAndTime);
		
		playFinishMarkInit = new JButton(INIT_FINISH_MARK_IMAGE);
		playFinishMarkInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MediaAdaptator.initFinishMarkMovie();
			}
		});

		panelActionControl.add(finishMarkButton);
		panelActionControl.add(playFinishMarkInit);
		panelSouthControl.add(BorderLayout.SOUTH, panelActionControl);		
		
		
		panelCenter.add(BorderLayout.CENTER,panelMovieControl);
        this.getContentPane().add(BorderLayout.CENTER,panelCenter);
        
        this.setPreferredSize(new Dimension(600, 400));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
	}
   
    
    protected void stopEditorDialog() { 
        MediaAdaptator.closeMediaEditor();
        this.stopTimer();
        super.stopEditorDialog();
    }
	
    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int prow) {
        this.setTitle(KMADEConstant.EDITOR_TEXT_TITLE_NAME + " : " + refModel.getValueAt(prow,0));

        MediaAdaptator.openMediaEditor(refModel.getValue(prow));
        super.showPropertiesEditor(refModel,prow);
	}

    public void openNewMovie(QTJComponent qc, int duration, String realDuration, String fileName, long length) {  
        JComponent jc = qc.asJComponent();
        panelCodec.removeAll();
        panelCodec.add(BorderLayout.CENTER,jc);
        myslider.setMaximum(duration);
        cardMovie.show(panelMovie,"CODEC");
        cardMovieInformation.show(panelInformation, "INFORMATION");    
		timer = new Timer(250, this);
		timer.start();
		this.setMediaFileTextField(fileName);
		this.setMediaLengthTextField(realDuration);
		this.setFileSizeTextField(Long.toString(length));
    	this.myslider.setEnabled(true);
	   	this.play.setEnabled(true);
    	this.playMark.setEnabled(false);
    	this.startMarkButton.setEnabled(true);
    	this.finishMarkButton.setEnabled(true);
    }   
    
    public void showMoviePanel() {
    	cardMovie.show(panelMovie, "CODEC");
    }
    
    public void hideNoCodecMoviePanel() {
    	cardMovie.show(panelMovie, "NO_CODEC");
    	this.browse.setEnabled(false);
    	this.removeVideo.setEnabled(false);
    }
    
    public void hideMoviePanel() {
        cardMovie.show(panelMovie,"EMPTY");
    }
    
    public void hideMovieInformationPanel() {
    	cardMovieInformation.show(panelInformation, "NO_INFORMATION");
    }
    
    public void showMovieInformationPanel() {
    	cardMovieInformation.show(panelInformation, "INFORMATION");	
    }
    
    public void stopTimer() {
    	if (timer != null) {
    		timer.stop();
    		timer = null;
    	}
    }
    
    public void initPlayerController() {
    	this.play.setEnabled(false);
    	this.playMark.setEnabled(false);
    	this.setDisablePlayMarkButton();
    	this.setPlayButtonImage();
    	this.setPlayMarkButtonImage();
    	this.timeLabel.setText("0:00");
    	this.setStartMarkTextField("");
    	this.setFinishMarkTextField("");
    	this.myslider.setValue(0);
    	this.myslider.setEnabled(false);
    	this.playStartMarkInit.setEnabled(false);
    	this.playFinishMarkInit.setEnabled(false);
    	this.startMarkButton.setEnabled(false);
    	this.finishMarkButton.setEnabled(false);
    }
    
    public void notifLocalisationModification() {
        super.notifLocalisationModification();        
    }
    
    public void setPlayMarkButtonImage() {
    	playMark.setIcon(PLAY_MARK_IMAGE);
    }
    
    public void setPauseMarkButtonImage() {
    	playMark.setIcon(PAUSE_MARK_IMAGE);
    }
    
    public void setEnablePlayMarkButton(Hashtable<Integer,JLabel> v) {
		myslider.setLabelTable(v);
    	myslider.setPaintLabels(true);
		playMark.setEnabled(true);
    }
    
    public void setDisablePlayMarkButton() {
		myslider.setPaintLabels(false);
		playMark.setEnabled(false);
    }
    
    public void setEnableStartMarkInit() {
    	this.playStartMarkInit.setEnabled(true);
    }
    
    public void setDisableStartMarkInit() {
    	this.playStartMarkInit.setEnabled(false);
    }
    
    public void setEnableFinishMarkInit() {
    	this.playFinishMarkInit.setEnabled(true);
    }
    
    public void setDisableFinishMarkInit() {
    	this.playFinishMarkInit.setEnabled(false);
    }
    
    public void setPlayButtonImage() {
    	this.play.setIcon(PLAY_IMAGE);
    }

    public void setPauseButtonImage() {
    	this.play.setIcon(PAUSE_IMAGE);
    }

    public void setTimeLabelContent(String p) {
    	timeLabel.setText(p);
    }
    
    public void setTimeSliderContent(int p) {
    	myslider.setValue(p);
    }
    
	public void actionPerformed(ActionEvent e) {
		MediaAdaptator.timerProgess();
	}


	public void setFileSizeTextField(String t) {
		this.fileSizeTextField.setText(t);
	}


	public void setFinishMarkTextField(String t) {
		this.finishMarkTextField.setText(t);
	}


	public void setMediaFileTextField(String t) {
		this.mediaFileTextField.setText(t);
	}


	public void setMediaLengthTextField(String t) {
		this.mediaLengthTextField.setText(t);
	}


	public void setStartMarkTextField(String t) {
		this.startMarkTextField.setText(t);
	}
}