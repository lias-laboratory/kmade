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
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.io.File;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.upensma.lias.kmade.KMADeMain;
import fr.upensma.lias.kmade.kmad.schema.tache.Media;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADEEditorMultimediaDialog;

import quicktime.QTException;
import quicktime.app.view.MoviePlayer;
import quicktime.app.view.QTFactory;
import quicktime.app.view.QTJComponent;
import quicktime.io.OpenMovieFile;
import quicktime.io.QTFile;
import quicktime.std.StdQTConstants;
import quicktime.std.StdQTException;
import quicktime.std.clocks.ExtremesCallBack;
import quicktime.std.movies.Movie;
import quicktime.std.movies.TimeInfo;
import quicktime.util.QTUtils;

/**
 * @author Mickael BARON
 */
public final class MediaAdaptator {
    
    public static int origine = 0;

    private static Movie currentMovie;

    private static boolean isPlayingWithMark = false;

    private static boolean isPlaying = false;

    private static boolean isFinished = false;

    private static int duration = 0;

    private static int startMark = -1;

    private static int finishMark = -1;

    private static boolean mousePressed = false;

    private static boolean keyPressed = false;

    private static final ImageIcon START_MARK_SLIDER_IMAGE = new ImageIcon(
	    KMADEEditorMultimediaDialog.class
		    .getResource(KMADEConstant.START_MARK_SLIDER_IMAGE));

    private static final ImageIcon FINISH_MARK_SLIDER_IMAGE = new ImageIcon(
	    KMADEEditorMultimediaDialog.class
		    .getResource(KMADEConstant.FINISH_MARK_SLIDER_IMAGE));

    private static String mediaFileName = "";

    private static String mediaFilePath = "";

    private static boolean isExisting = false;

    private static Media ref;

    private static boolean error = false;

    public static void openMediaEditor(Object ref) {
	GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
	TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();

	MediaAdaptator.processInitPlayer();

	if (!KMADeMain.isAcceptedMedia()) {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .hideNoCodecMoviePanel();
	    return;
	}

	if (ref instanceof Media) {
	    Media myRef = (Media) ref;
	    MediaAdaptator.ref = myRef;
	    if (myRef.isExisting()) {
		mediaFileName = myRef.getFileName();
		mediaFilePath = myRef.getPath();
		startMark = myRef.getStartMark();
		finishMark = myRef.getFinishMark();
		isExisting = true;
		MediaAdaptator.openFromModel();
	    } else {
		isExisting = false;
	    }
	} else {
	}
    }

    public static void editedFromEnhancedFrame() {
	origine = 1;
    }

    public static void closeMediaEditor() {
	GraphicEditorAdaptator.enabledMainFrameAfterEdition();
	TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
	if (origine == 0) {
	    GraphicEditorAdaptator.requestFocus();
	} else {
	    TaskPropertiesEnhancedEditorAdaptator.requestFocus();
	}
	origine = 0;
	stopMovie();

	if (ref != null && !error) {
	    ref.setExisting(isExisting);
	    ref.setFileName(mediaFileName);
	    ref.setPath(mediaFilePath);
	    ref.setStartMark(startMark);
	    ref.setFinishMark(finishMark);
	}
    }

    public static void openMovie(QTFile qtf) {
	try {
	    mediaFileName = qtf.getName();
	    mediaFilePath = qtf.getParent();
	    if (mediaFilePath == null) {
		mediaFilePath = "";
	    }
	    OpenMovieFile openqtfile = OpenMovieFile.asRead(qtf);
	    currentMovie = Movie.fromFile(openqtfile);
	    MoviePlayer mp = new MoviePlayer(currentMovie);
	    QTJComponent qc = QTFactory.makeQTJComponent(mp);
	    duration = currentMovie.getDuration();
	    new MyQTCallback(currentMovie);
	    GraphicEditorAdaptator
		    .getPanelProp()
		    .getEditorMultimedia()
		    .openNewMovie(
			    qc,
			    duration,
			    getTimeWithFormat(duration
				    / currentMovie.getTimeScale()),
			    mediaFilePath + File.separator + mediaFileName,
			    qtf.length() / 1024);
	    if (startMark != -1) {
		MediaAdaptator.startMarkMovie();
	    }

	    if (finishMark != -1) {
		MediaAdaptator.finishMarkMovie();
	    }

	    isExisting = true;
	} catch (QTException e) {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .hideMoviePanel();
	} catch (Exception e) {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .hideMoviePanel();
	} finally {
	    QTUtils.reclaimMemory();
	}
    }

    private static void openFromModel() {
	File myFile = new File(mediaFilePath + File.separator + mediaFileName);
	if (!myFile.exists()) {
	    myFile = new File(mediaFileName);

	    if (!myFile.exists()) {
		error = true;
		return;
	    }
	}

	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.showMovieInformationPanel();
	QTFile f = new QTFile(myFile);
	MediaAdaptator.openMovie(f);
    }

    public static boolean isExistingMediaFile(Media p) {
	File myFile = new File(p.getPath() + File.separator + p.getFileName());
	if (!myFile.exists()) {
	    myFile = new File(p.getFileName());

	    if (!myFile.exists()) {
		return false;
	    }
	}
	return true;
    }

    public static void openFromBrowse() {
	try {
	    MediaAdaptator.stopMovie();
	    QTFile qtf = QTFile
		    .standardGetFilePreview(QTFile.kStandardQTFileTypes);
	    MediaAdaptator.openMovie(qtf);
	} catch (QTException e) {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .hideMoviePanel();
	} finally {
	    QTUtils.reclaimMemory();
	}
    }

    public static void timeProgress(int slider) {
	if (mousePressed || keyPressed) {
	    try {
		currentMovie.setTimeValue(slider);
	    } catch (StdQTException exe) {
		exe.printStackTrace();
	    }
	}
    }

    public static void enableMousePressed() {
	if (currentMovie == null) {
	    return;
	}
	MediaAdaptator.mousePressed = true;

	try {
	    currentMovie.setActiveSegment(new TimeInfo(0, duration));
	    if (isPlayingWithMark) {
		isPlaying = true;
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayMarkButtonImage();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPauseButtonImage();
		isPlayingWithMark = false;
	    }
	} catch (StdQTException exe) {
	    exe.printStackTrace();
	}
    }

    public static void disableMousePressed() {
	MediaAdaptator.mousePressed = false;
    }

    public static void disableKeyPressed() {
	MediaAdaptator.keyPressed = false;
    }

    public static void enableKeyPressed() {
	if (currentMovie == null) {
	    return;
	}

	MediaAdaptator.keyPressed = true;
	try {
	    currentMovie.setActiveSegment(new TimeInfo(0, duration));
	    if (isPlayingWithMark) {
		isPlaying = true;
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayMarkButtonImage();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPauseButtonImage();
		isPlayingWithMark = false;
	    }
	} catch (StdQTException exe) {
	    exe.printStackTrace();
	}
    }

    public static void stopMovie() {
	if (currentMovie != null) {
	    try {
		currentMovie.stop();
	    } catch (StdQTException exe) {
		exe.printStackTrace();
	    }
	}
    }

    public static void playMovie() {
	try {
	    currentMovie.setActiveSegment(new TimeInfo(0, duration));
	    if (isPlayingWithMark) {
		isPlayingWithMark = false;
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayMarkButtonImage();
	    }
	    if (isPlaying) {
		currentMovie.stop();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayButtonImage();
		isPlaying = false;
	    } else {
		if (isFinished) {
		    currentMovie.goToBeginning();
		    isFinished = false;
		}
		currentMovie.start();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPauseButtonImage();
		isPlaying = true;
	    }
	} catch (StdQTException exe) {
	    exe.printStackTrace();
	}
    }

    public static void playMarkMovie() {
	try {
	    int start = 0;
	    int finish = duration;

	    if (startMark != -1) {
		start = startMark;
	    }

	    if (finishMark != -1) {
		finish = finishMark;
	    }

	    currentMovie.setActiveSegment(new TimeInfo(start, finish - start));

	    if (isPlaying) {
		isPlaying = false;
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayButtonImage();
	    }
	    if (isPlayingWithMark) {
		currentMovie.stop();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayMarkButtonImage();
		isPlayingWithMark = false;
	    } else {
		if (isFinished) {
		    currentMovie.goToBeginning();
		    isFinished = false;
		}
		currentMovie.start();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPauseMarkButtonImage();
		isPlayingWithMark = true;
	    }
	} catch (StdQTException exe) {
	    exe.printStackTrace();
	}
    }

    public static void startMarkMovieAction() {
	startMark = MediaAdaptator.getCurrentTime();
	MediaAdaptator.startMarkMovie();
    }

    private static void startMarkMovie() {
	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
	labelTable.put(Integer.valueOf(startMark), new JLabel(
		START_MARK_SLIDER_IMAGE));

	if (finishMark != -1) {
	    if (startMark >= finishMark) {
		finishMark = -1;
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setFinishMarkTextField("");
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setDisableFinishMarkInit();
	    } else {
		labelTable.put(Integer.valueOf(finishMark), new JLabel(
			FINISH_MARK_SLIDER_IMAGE));
	    }
	}

	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setEnableStartMarkInit();
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setEnablePlayMarkButton(labelTable);
	GraphicEditorAdaptator
		.getPanelProp()
		.getEditorMultimedia()
		.setStartMarkTextField(
			getTimeWithFormat((getRealCurrentTime(startMark))));
    }

    public static void initStartMarkMovie() {
	startMark = -1;
	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
	if (finishMark != -1) {
	    labelTable.put(Integer.valueOf(finishMark), new JLabel(
		    FINISH_MARK_SLIDER_IMAGE));
	}
	if (labelTable.size() == 0) {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .setDisablePlayMarkButton();
	} else {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .setEnablePlayMarkButton(labelTable);
	}
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setStartMarkTextField("");
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setDisableStartMarkInit();
    }

    public static void initFinishMarkMovie() {
	finishMark = -1;
	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
	if (startMark != -1) {
	    labelTable.put(Integer.valueOf(startMark), new JLabel(
		    START_MARK_SLIDER_IMAGE));
	}

	if (isPlayingWithMark) {
	    try {
		currentMovie.setActiveSegment(new TimeInfo(0, duration));
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayMarkButtonImage();
		isPlayingWithMark = false;
	    } catch (StdQTException exe) {
		exe.printStackTrace();
	    }
	}

	if (labelTable.size() == 0) {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .setDisablePlayMarkButton();
	} else {
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .setEnablePlayMarkButton(labelTable);
	}
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setFinishMarkTextField("");
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setDisableFinishMarkInit();
    }

    public static void finishMarkMovieAction() {
	finishMark = MediaAdaptator.getCurrentTime();
	MediaAdaptator.finishMarkMovie();
    }

    private static void finishMarkMovie() {
	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
	labelTable.put(Integer.valueOf(finishMark), new JLabel(
		FINISH_MARK_SLIDER_IMAGE));

	if (startMark != -1) {
	    if (startMark != -1) {
		if (startMark >= finishMark) {
		    startMark = -1;
		    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			    .setStartMarkTextField("");
		    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			    .setDisableStartMarkInit();
		} else {
		    labelTable.put(Integer.valueOf(startMark), new JLabel(
			    START_MARK_SLIDER_IMAGE));
		}
	    }
	}

	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setEnableFinishMarkInit();
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.setEnablePlayMarkButton(labelTable);
	GraphicEditorAdaptator
		.getPanelProp()
		.getEditorMultimedia()
		.setFinishMarkTextField(
			getTimeWithFormat((getRealCurrentTime(finishMark))));
    }

    private static int getCurrentTime() {
	if (currentMovie != null) {
	    try {
		int totalSeconds = currentMovie.getTime();
		return totalSeconds;
	    } catch (QTException qte) {
		qte.printStackTrace();
	    }
	}
	return -1;
    }

    private static int getRealCurrentTime(int current) {
	try {
	    return current / currentMovie.getTimeScale();
	} catch (QTException qte) {
	    qte.printStackTrace();
	}
	return -1;
    }

    private static class MyQTCallback extends ExtremesCallBack {
	public MyQTCallback(Movie m) throws QTException {
	    super(m.getTimeBase(), StdQTConstants.triggerAtStop);
	    callMeWhen();
	}

	public void execute() {
	    if (rateWhenCalled == 0.0) {

	    } else if (rateWhenCalled == 1.0) {
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayButtonImage();
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setPlayMarkButtonImage();
		isPlaying = false;
		isPlayingWithMark = false;
		try {
		    currentMovie.stop();
		    isFinished = true;
		} catch (StdQTException exe) {
		    exe.printStackTrace();
		}
	    }
	    try {
		callMeWhen();
	    } catch (QTException qte) {
		qte.printStackTrace();
	    }
	}
    }

    public static void timerProgess() {
	if (currentMovie == null) {
	    return;
	}
	try {
	    int totalSeconds = currentMovie.getTime()
		    / currentMovie.getTimeScale();
	    GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		    .setTimeLabelContent(getTimeWithFormat(totalSeconds));
	    if (!(mousePressed && keyPressed)) {
		GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
			.setTimeSliderContent(currentMovie.getTime());
	    }
	} catch (QTException qte) {
	    qte.printStackTrace();
	}
    }

    private static String getTimeWithFormat(int totalSeconds) {
	int seconds = totalSeconds % 60;
	int minutes = totalSeconds / 60;
	String secString = (seconds > 9) ? Integer.toString(seconds)
		: ("0" + Integer.toString(seconds));
	String minString = Integer.toString(minutes);
	return (minString + ":" + secString);
    }

    public static void removeCurrentMovie() {
	if (currentMovie == null) {
	    return;
	}
	try {
	    currentMovie.stop();
	} catch (QTException qte) {
	    qte.printStackTrace();
	}
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia().stopTimer();
	isExisting = false;
	MediaAdaptator.processInitPlayer();
    }

    private static void processInitPlayer() {
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.initPlayerController();
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.hideMoviePanel();
	GraphicEditorAdaptator.getPanelProp().getEditorMultimedia()
		.hideMovieInformationPanel();
	isPlayingWithMark = false;
	isPlaying = false;
	isFinished = false;
	duration = 0;
	startMark = -1;
	finishMark = -1;
	mousePressed = false;
	keyPressed = false;
	error = false;
    }
}