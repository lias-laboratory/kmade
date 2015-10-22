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
package fr.upensma.lias.kmade.tool;

import java.awt.Color;
import java.awt.Font;

import fr.upensma.lias.kmade.KMADEToolConstant;
import fr.upensma.lias.kmade.KMADeMain;

/**
 * @author Mickael BARON
 */
public class KMADEConstant {
    // Couleurs

    // Couleur pour la s�lection dans les JTable
    public static final Color ACTIVE_SELECTION = new Color(186, 219, 243); // Color.blue;

    public static Color ACTIVE_SELECTION_FONT_COLOR = Color.black;

    // Couleur pour les Containers
    public static final Color ACTIVE_PANE = Color.white;

    // Couleur pour le nom d'une t�che
    public static Color TASK_NAME_COLOR = Color.black;

    // Couleur pour le nom de l'op�ration
    public static Color TASK_OPERATOR_COLOR = Color.black;

    // Fontes

    public static final Font fontACTIF = new Font("Tahoma", Font.BOLD, 12);

    public static final Font fontPASSIF = new Font("Tahoma", Font.PLAIN, 10);

    public static final Font TASK_NUM_FONT = new Font("arial", Font.BOLD, 10);

    public static Font TASK_NAME_FONT = new Font("arial", Font.BOLD, 15);

    public static Font TASK_OPERATOR_FONT = new Font("arial", Font.BOLD, 10);

    public static Font TITLE_PROTO_TASK_FONT = new Font("arial", Font.BOLD, 20);

    public static Font BORDER_PROTO_TASK_FONT = new Font("arial", Font.BOLD, 13);

    public static Font TEXT_PROTO_TASK_FONT = new Font("arial", Font.PLAIN, 14);

    public static final int ROW_HEIGHT = 35;

    // Task model appearance

    // Hauteur des niveaux
    public static int LEVEL_DISTANCE = 130;

    // Distance entre les t�ches
    public static int TASK_DISTANCE = 60;

    // Couleur de la s�lection d'une t�che
    public static Color TASK_SELECTION_COLOR = Color.GREEN;

    // Orthogonal Edges
    public static boolean ORTHOGONAL_EDGES = true;

    // Pas de zoom
    public static double WHEEL_ZOOM = 1;

    // Extension

    // KMADe Project
    public static final String KMADE_MODEL_EXTENSION_FILE = ".kxml";

    // KMADe Scenario Extension
    public static final String KMADE_SCENARIO_EXTENSION_FILE = ".ksxml";

    /**
     * Modifie la police du nom de la tache
     * 
     * @param font
     * @param style
     * @param size
     * @param fontColor
     */
    public static void setTaskNameFont(String font, int style, int size,
	    Color fontColor) {
	TASK_NAME_FONT = new Font(font, style, size);
	TASK_NAME_COLOR = fontColor;
    }

    /**
     * Modifie la police de l'op�rateur de la tache
     * 
     * @param font
     * @param style
     * @param size
     * @param fontColor
     */
    public static void setTaskOperatorFont(String font, int style, int size,
	    Color fontColor) {
	TASK_OPERATOR_FONT = new Font(font, style, size);
	TASK_OPERATOR_COLOR = fontColor;
    }

    // Les mots affich�s sur une t�che dans l'espace de t�che
    public static String VERTEX_NUM = "N:";
    public static String VERTEX_PRECONDITION = "PRE";
    public static String VERTEX_ITERATION = "ITER";
    public static String VERTEX_OPT = "OPT";
    public static String VERTEX_INTERRUPTIBLE = "INTER";
    public static String VERTEX_ACTOR = "ACTEUR";
    public static String VERTEX_EFFETSDEBORD = "ACTION";
    public static String VERTEX_DESCRIPTION = "DESC";
    public static String VERTEX_ITER_LETTER = "I:";
    public static String VERTEX_STATE_LETTER = "S:";
    public static String VERTEX_EVENT_IN_LETTER = "IN:";
    public static String VERTEX_EVENT_OUT_LETTER = "OUT:";
    //
    // Les constantes li�es aux titres et autres chaines de caract�res.
    //
    public static String FENETRE_PRINCIPALE_TITLE_NAME = "KMADe (Kernel of Model for Activity Description environment)";
    public static String ABOUT_TITLE_NAME = "A Propos de "
	    + KMADEToolConstant.TOOL_NAME;
    public static String TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME = "Espace T�ches";
    public static String ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME = "Objets Abstraits";
    public static String USER_TABBEDPANE_TITLE_NAME = "Acteurs potentiels";
    public static String INDIVIDU_TABBEDPANE_TITLE_NAME = "Individus";
    public static String ORGANIZATION_TABBEDPANE_TITLE_NAME = "Organisations";
    public static String MACHINE_TABBEDPANE_TITLE_NAME = "Machines";
    public static String PARCMACHINES_TABBEDPANE_TITLE_NAME = "Parc de machines";
    public static String EVENT_TABBEDPANE_TITLE_NAME = "Evénements";
    public static String CONDITION_TABBEDPANE_TITLE_MESSAGE = "Condition";
    public static String CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME = "Objets Concrets";
    public static String PREVIEW_WINDOW_TITLE_NAME = "Fen�tre d'aper�u";
    public static String INFO_DEBUG_TITLE_NAME = "Informations de d�veloppement";
    public static String HELP_KMADE_TITLE_NAME = "Aide sur l'outil "
	    + KMADEToolConstant.TOOL_NAME;
    public static String HELP_KMAD_TITLE_NAME = "Aide sur le mod�le K-MAD";
    public static String HISTORY_TITLE_NAME = "Historique de l'outil KMADe";
    public static String ABSTRACT_OBJECT_TITLE_NAME = "Editeur Objets Abstraits";
    public static String CONCRETE_OBJECT_TITLE_NAME = "Editeur Objets Concrets";
    public static String USER_TITLE_NAME = "Editeur Utilisateurs";
    public static String INDIVIDU_TITLE_NAME = "Editeur Individu";
    public static String ORGANIZATION_TITLE_NAME = "Editeur Organisation";
    public static String MACHINE_TITLE_NAME = "Editeur Machine";
    public static String PARCMACHINES_TITLE_NAME = "Editeur Parc de machines";
    public static String EVENT_TITLE_NAME = "Editeur Ev�nements";
    public static String TASK_MODEL_TITLE_NAME = "Arbre de T�ches";
    public static String PREFERENCES_TITLE_NAME = "Pr�f�rences";
    public static String COMPLETE_EDITOR_TITLE_NAME = "Editeur complet de t�ches";
    public static String CLIPBOARD_TITLE_NAME = "Aper�u du Presse-Papier";
    public static String LOAD_MONITOR_TITLE_NAME = "Moniteur de chargement de projet K-MAD";
    public static String SAVE_MONITOR_TITLE_NAME = "Moniteur d'enregistrement de projet K-MAD";
    public static String EDITOR_TEXT_TITLE_NAME = "Editeur du contenu de l'attribut";
    public static String EVENT_TASK_LINKED_TITLE_NAME = "Liaison Tache/Ev�nement";
    public static String ACTOR_CREATE_TITLE_NAME = "Cr�ation des acteurs";
    public static String ACTORSYSTEM_CREATE_TITLE_NAME = "Cr�ation des acteurs syst�mes";
    public static String PRINT_PREVIEW_WINDOW_TITLE_NAME = "Aper�u d'impression";
    public static String PROJECT_DIALOG_TITLE_NAME = "Gestion des interviews";

    // Les affichages des boites de dialogue d'erreur de nom

    public static String BAD_CHARACTER_TEXT = "Erreur d'�criture dans le nom";
    public static String BAD_CARACTER_TITLE = "Nom incorrect";
    public static String SAME_NAME_TEXT = "Nom Identique";
    public static String SAME_NAME_TITLE = "Le nom est d�j� utilis�, renommer en :";
    // Abstract Object
    public static String BACK_TO_EDITOR = "Retour vers l'�diteur de";
    public static String ABSTRACT_OBJECT_NAME_TABLE = "Nom";
    public static String ABSTRACT_OBJECT_OBSERVATION_TABLE = "Observation";
    public static String ABSTRACT_OBJECT_NEW_OBJECT_TABLE = "Ajouter un objet abstrait ...";
    public static String ABSTRACT_OBJECT_TITLE_TABLE = "Objet Abstrait";
    public static String ABSTRACT_OBJECT_REMOVE_MESSAGE_TITLE = "Suppression d'objets abstraits";
    public static String ABSTRACT_OBJECT_LIST_MESSAGE_TITLE = "Liste Objets Abstraits";

    // Groupe Abstrait
    public static String ABSTRACT_GROUP_NAME_TABLE = "Nom";
    public static String ABSTRACT_GROUP_DESCRIPTION_TABLE = "Description";
    public static String ABSTRACT_GROUP_SET_TYPE_TABLE = "Type d'ensemble";
    public static String ABSTRACT_GROUP_NEW_OBJECT_TABLE = "Ajouter un groupe ...";
    public static String ABSTRACT_GROUP_TITLE_TABLE = "Groupe de";
    public static String ABSTRACT_GROUP_REMOVE_MESSAGE_TITLE = "Suppression de groupes";

    // Attribut Abstrait
    public static String ABSTRACT_ATTRIBUT_NAME_TABLE = "Nom";
    public static String ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE = "Description";
    public static String ABSTRACT_ATTRIBUT_TYPE_TABLE = "Type";
    public static String ABSTRACT_ATTRIBUT_TYPE_NAME_TABLE = "Nom du type";
    public static String ABSTRACT_ATTRIBUT_NEW_OBJECT_TABLE = "Ajouter un attribut abstrait ...";
    public static String ABSTRACT_ATTRIBUT_TITLE_TABLE = "Attribut de";
    public static String ABSTRACT_ATTRIBUT_ENUMERATE_EDIT_TABLE = "Editer �num�r� ...";
    public static String ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE = "Editer intervalle ...";
    public static String ABSTRACT_ATTRIBUT_REMOVE_MESSAGE_TITLE = "Suppression d'attributs abstraits";

    // Enumeration
    public static String ENUMERATION_NAME_TABLE = "Nom �num�r�";
    public static String ENUMERATION_DESCRIPTION_TABLE = "Description";
    public static String ENUMERATION_NEW_OBJECT_TABLE = "Ajouter un �num�r� ...";
    public static String ENUMERATION_TITLE_TABLE = "Enum�ration";
    public static String ENUMERATION_REMOVE_MESSAGE_TITLE = "Suppression d'�num�r�s";

    // Element
    public static String ELEMENT_NAME_TABLE = "Nom";
    public static String ELEMENT_NEW_OBJECT_TABLE = "Ajouter un �l�ment ...";
    public static String ELEMENT_REMOVE_MESSAGE_TITLE = "Suppression d'�l�ments d'un �num�r�";

    // Intervalle
    public static String INTERVALLE_TITLE_TABLE = "Intervalle";
    public static String INTERVALLE_NAME_TABLE = "Nom";
    public static String INTERVALLE_DESCRIPTION_TABLE = "Description";
    public static String INTERVALLE_MIN_VALUE_TABLE = "Valeur min";
    public static String INTERVALLE_MAX_VALUE_TABLE = "Valeur max";
    public static String INTERVALLE_NEW_OBJECT_TABLE = "Ajouter un intervalle";
    public static String INTERVALLE_REMOVE_MESSAGE_TITLE = "Suppression d'intervalles";

    // Objet Concret
    public static String CONCRETE_ABSTRACT_OBJECT_TITLE_TABLE = "Objet Abstrait";
    public static String CONCRETE_OBJECT_TITLE_TABLE = "Objet Concret";
    public static String CONCRETE_OBJECT_NAME_TABLE = "Nom";
    public static String CONCRETE_OBJECT_OBSERVATION_TABLE = "Observation";
    public static String CONCRETE_OBJECT_GROUPE_TABLE = "Groupe";
    public static String CONCRETE_OBJECT_GROUPE_NAME = "groupe";
    public static String NO_CONCRETE_OBJECT_GROUPE_NAME = "Aucun";
    public static String CONCRETE_NEW_OBJECT_TABLE = "Ajouter un objet concret ...";
    public static String CONCRETE_NONEW_ROW_TABLE = "Ajout impossible car pas de groupe disponible";
    public static String CONCRETE_OBJECT_REMOVE_MESSAGE_TITLE = "Suppression d'objets concrets";

    // Attribut Concret
    public static String CONCRETE_ATTRIBUT_NAME_TABLE = "Nom";
    public static String CONCRETE_ATTRIBUT_VALUE_TABLE = "Valeur";
    public static String CONCRETE_ATTRIBUT_NOINIT_TABLE = "Attribut non initialis�";
    public static String CONCRETE_ATTRIBUT_TITLE_TABLE = "Attribut concret de";

    // Evenement
    public static String EVENT_NAME_TABLE = "Nom";
    public static String EVENT_DESCRIPTION_TABLE = "Description";
    public static String EVENT_NEW_ROW_TABLE = "Ajouter un �v�nement ...";
    public static String EVENT_NONEW_ROW_TABLE = "Ajout impossible car pas d'�v�nement disponible";
    public static String EVENT_REMOVE_INTO_TASK = "Suppression d'�v�nements d'une t�che";
    public static String EVENT_REMOVE_NAME_TITLE = "Suppression d'�v�nements";
    public static String EVENT_NO_ELEMENT_INTO_TASK = "Pas d'�v�nement associ�";

    // Utilisateur
    public static String USER_NAME_TABLE = "Nom";
    public static String USER_STATUS_TABLE = "Statut";
    public static String USER_ROLE_TABLE = "R�le";
    public static String USER_PHOTO_TABLE = "Photo";
    public static String USER_MEMBERS = "Membre(s)";
    public static String USER_NEW_ROW_TABLE = "Ajouter un utilisateur ...";
    public static String USER_REMOVE_NAME_TITLE = "Suppression d'utilisateurs";
    public static String USER_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String USER_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String USER_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String USER_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image D�faut";
    public static String USER_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erron�";
    public static String USER_CLEAR_IMAGE_MESSAGE = "Effacer Image";

    // Individu

    public static String INDIVIDU_NAME_TABLE = "Nom";
    public static String INDIVIDU_STATUS_TABLE = "Statut";
    public static String INDIVIDU_ROLE_TABLE = "R�le";
    public static String INDIVIDU_PHOTO_TABLE = "Photo";
    public static String INDIVIDU_NEW_ROW_TABLE = "Ajouter un individu";
    public static String INDIVIDU_REMOVE_NAME_TITLE = "Suppression d'individus";
    public static String INDIVIDU_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String INDIVIDU_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String INDIVIDU_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String INDIVIDU_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image D�faut";
    public static String INDIVIDU_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erron�";
    public static String INDIVIDU_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String INDIVIDU_ORGANIZATION_TABLE = "Organisation";
    public static String INDIVIDU_OTHERS = "Autres";
    // ORGANIZATION

    public static String ORGANIZATION_NAME_TABLE = "Nom";
    public static String ORGANIZATION_STATUS_TABLE = "Statut";
    public static String ORGANIZATION_ROLE_TABLE = "R�le";
    public static String ORGANIZATION_PHOTO_TABLE = "Logo";
    public static String ORGANIZATION_NEW_ROW_TABLE = "Ajouter un organisation";
    public static String ORGANIZATION_REMOVE_NAME_TITLE = "Suppression d'organisation";
    public static String ORGANIZATION_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String ORGANIZATION_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String ORGANIZATION_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String ORGANIZATION_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image D�faut";
    public static String ORGANIZATION_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erron�";
    public static String ORGANIZATION_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String ORGANIZATION_MEMBERS_TABLE = "Membres";
    public static String ORGANIZATION_OTHERS = "Autres";

    // Machine

    public static String MACHINE_NAME_TABLE = "Nom";
    public static String MACHINE_DESCRIPTION_TABLE = "Description";
    public static String MACHINE_ISCOMPUTER_TABLE = "est informatis�";
    public static String MACHINE_IMAGE_TABLE = "image";
    public static String MACHINE_NEW_ROW_TABLE = "Ajouter un machine";
    public static String MACHINE_REMOVE_NAME_TITLE = "Suppression de machines";
    public static String MACHINE_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String MACHINE_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String MACHINE_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String MACHINE_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image D�faut";
    public static String MACHINE_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erron�";
    public static String MACHINE_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String MACHINE_PARCS_TABLE = "Parcs de machines";
    public static String MACHINE_OTHERS = "Autres";

    // ParcMachines

    public static String PARCMACHINES_NAME_TABLE = "Nom";
    public static String PARCMACHINES_DESCRIPTION_TABLE = "Description";
    public static String PARCMACHINES_IMAGE_TABLE = "image";
    public static String PARCMACHINES_NEW_ROW_TABLE = "Ajouter un parc de machines";
    public static String PARCMACHINES_REMOVE_NAME_TITLE = "Suppression de parcs de machine";
    public static String PARCMACHINES_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String PARCMACHINES_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String PARCMACHINES_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String PARCMACHINES_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image D�faut";
    public static String PARCMACHINES_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erron�";
    public static String PARCMACHINES_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String PARCMACHINES_MACHINE_TABLE = "Machines";
    public static String PARCMACHINES_OTHERS = "Autres";

    // Label
    public static String LABEL_TITLE_MESSAGE = "Libell�";
    public static String LABEL_REMOVE_NAME_TITLE = "Suppression de libell�s";
    public static String LABEL_NUMERO_TITLE = "Num�ro";
    public static String LABEL_NAME_TITLE = "Nom";
    public static String LABEL_DESCRIPTION_TITLE = "Description";
    public static String LABEL_COLOR_TITLE = "Couleur";
    public static String LABEL_IS_VISIBLE_COLOR_TITLE = "Couleur Visible";
    public static String LABEL_IS_VISIBLE_TITLE = "Visible";
    public static String LABEL_ADD_NEW_LABEL_TITLE = "Ajouter un libell� ...";
    public static String LABEL_EDITOR_TITLE = "Editeur Libell�";

    // Acteur
    public static String ACTOR_NAME_TABLE = "Nom";
    public static String ACTOR_EXPERIENCE_TABLE = "Exp�rience";
    public static String ACTOR_COMPETENCE_TABLE = "Comp�tence";
    public static String ACTOR_NEW_ROW_TABLE = "Ajouter un acteur ...";
    public static String ACTOR_NONEW_ROW_TABLE = "Ajout impossible d'acteur car pas d'utilisateur disponible";
    public static String ACTOR_NO_ELEMENT_INTO_TASK = "Pas d'acteur associ�";
    public static String ACTOR_REMOVE_NAME_TITLE = "Suppression d'acteurs";
    public static String ACTOR_REMOVE_ALL_NAME_TITLE = "Suppression de l'ensemble des acteurs";

    // Acteur Systeme

    public static String ACTORSYSTEM_NAME_TABLE;
    public static String ACTORSYSTEM_EXPERIENCE_TABLE;
    public static String ACTORSYSTEM_COMPETENCE_TABLE;
    public static String ACTORSYSTEM_NEW_ROW_TABLE;
    public static String ACTORSYSTEM_NONEW_ROW_TABLE;
    public static String ACTORSYSTEM_NO_ELEMENT_INTO_TASK;
    public static String ACTORSYSTEM_REMOVE_NAME_TITLE;
    public static String ACTORSYSTEM_REMOVE_ALL_NAME_TITLE;

    // Propri�t�s des t�ches
    public static String NO_SELECTED_TASK_MESSAGE = "Pas de t�che S�lectionn�e";
    public static String PROPERTIES_MESSAGE = "Caract�ristiques";
    public static String GENERAL_TABBEDPANE_TITLE_NAME = "G�n�ral";
    public static String NO_VALUE_LEGEND_MESSAGE = "pas de valeur";
    public static String NO_CARACTERISTIC_SELECTED_MESSAGE = "Caract�ristique non s�lectionn�e";
    public static String ERROR_CARACTERISTIC_MESSAGE = "Erreur";

    // Informations G�n�rales des propri�t�s d'une t�ches
    public static String TASK_ATTRIBUT_NAME = "T�che";
    public static String GENERAL_INFORMATION_PANEL_TITLE_NAME = "G�n�rales";
    public static String GENERAL_INFORMATION_PANEL_LEGEND_TITLE_NAME = "Les caract�ristiques de type g�n�rales sont les premi�res renseign�es et sont pr�vues pour �tre assimil�es par l'ensemble des intervenants de l'analyse d'une activit�. Ces caract�ristiques sont informelles et ne font pas r�f�rences � des objets de l'�tat du monde";
    public static String TASK_NUMBER_NAME_TITLE = "Num�ro";
    public static String TASK_NUMBER_SHORT_NAME_TITLE = "Num";
    public static String TASK_NUMBER_NAME_LEGEND_TITLE = "Num�ro";
    public static String NO_MOTHER_TASK_NAME_MESSAGE = "Pas de t�che m�re";
    public static String SUB_TASK_PARENT_NAME_TITLE = "Sous-t�che";
    public static String SUB_TASK_PARENT_NAME_LEGEND_TITLE = "Sous-t�che";
    public static String TASK_NAME_TITLE = "Nom";
    public static String TASK_NAME_LEGEND_TITLE = "Nom";
    public static String TASK_PURPOSE_TITLE = "But";
    public static String TASK_PURPOSE_LEGEND_TITLE = "But";
    public static String TASK_MULTIMEDIA_PURPOSE_TITLE = "Multim�dia";
    public static String TASK_MULTIMEDIA_PURPOSE_LEGEND_TITLE = "Multim�dia";
    public static String TASK_MULTIMEDIA_ERROR_FILE_MESSAGE = "Probl�me : m�dia introuvable";
    public static String TASK_LABEL_TITLE = "Libell�";
    public static String TASK_LABEL_LEGEND_TITLE = "Libell�";
    public static String TASK_OBJECTS_TITLE = "Objets";
    public static String TASK_OBJECTS_LEGEND_TITLE = "Objets";
    public static String TASK_FEEDBACK_TITLE = "Affichage";
    public static String TASK_FEEDBACK_LEGEND_TITLE = "Affichage";
    public static String TASK_DURATION_TITLE = "Dur�e";
    public static String TASK_DURATION_LEGEND_TITLE = "Dur�e";
    public static String TASK_OBSERVATION_TITLE = "Observation";
    public static String TASK_OBSERVATION_LEGEND_TITLE = "Observation";
    public static String EXECUTING_NAME_TITLE = "Ex�cutant";
    public static String EXECUTING_NAME_LEGEND_TITLE = "Ex�cutant";
    public static String MODALITY_NAME_TITLE = "Modalit�";
    public static String MODALITY_NAME_LEGEND_TITLE = "Modalit�";
    public static String FREQUENCY_NAME_TITLE = "Fr�quence";
    public static String FREQUENCY_NAME_LEGEND_TITLE = "Fr�quence";
    public static String FREQUENCY_VALUE_NAME_TITLE = "Valeur Fr�quence";
    public static String FREQUENCY_VALUE_NAME_LEGEND_TITLE = "Valeur Fr�quence";
    public static String IMPORTANT_NAME_TITLE = "Importance";
    public static String IMPORTANT_NAME_LEGEND_TITLE = "Importance";
    public static String SENSORI_MOTRICE_NAME_TITLE = "Sensori-motrice";
    public static String COGNITIVE_NAME_TITLE = "Cognitive";

    // Ordonnancement d'une t�che
    public static String SCHEDULE_PANEL_TITLE_NAME = "Ordonnancement";
    public static String NECESSITE_LABEL_NAME = "N�cessit�";
    public static String NECESSITE_LABEL_LEGEND_NAME = "N�cessit�";
    public static String INTERRUPTIBLE_LABEL_NAME = "Interruptible";
    public static String INTERRUPTIBLE_LABEL_LEGEND_NAME = "Interruptible";
    public static String DECLENCHEMENT_LABEL_NAME = "D�clenchement";
    public static String DECLENCHEMENT_LABEL_LEGEND_NAME = "D�clenchement";
    public static String UTILISATEUR_LABEL_NAME = "Acteur(s)";
    public static String UTILISATEUR_LABEL_LEGEND_NAME = "Utilisateurs";
    public static String SCHEDULE_LABEL_NAME = "D�composition";
    public static String SCHEDULE_LABEL_LEGEND_NAME = "D�composition";
    public static String OPTIONAL_NECESSITE_VALUE = "Optionnelle";
    public static String NO_OPTIONAL_NECESSITE_VALUE = "Obligatoire";
    public static String INTERRUPTIBLE_VALUE = "Interruptible";
    public static String NO_INTERRUPTIBLE_VALUE = "Non interruptible";
    public static String PRECONDITION_LABEL_VALUE = "Pr�condition";
    public static String PRECONDITION_LABEL_LEGEND_VALUE = "Pr�condition";
    public static String ITERATION_LABEL_VALUE = "It�ration";
    public static String ITERATION_LABEL_LEGEND_VALUE = "It�ration";
    public static String MATERIEL_LABEL_NAME = "Acteur(s) Syst�me(s)";
    public static String MATERIEL_LABEL_LEGEND = "Materiel";

    // Effets d'une t�che
    public static String POSTCONDITION_PANEL_TITLE_NAME = "Actions";
    public static String EFFETSDEBORD_LABEL_VALUE = "EffetsDeBord";
    public static String EFFETSDEBORD_LABEL_LEGEND_VALUE = "EffetsDeBord";
    public static String EVENT_NAME_TITLE;
    public static String EVENT_NAME_LEGEND_TITLE = "Ev�nement";

    // Pour l'outil li� au multim�dia
    public static String MEDIA_FILE_NAME = "Fichier m�dia";
    public static String MEDIA_FILE_LENGTH = "Taille m�dia (ko)";
    public static String MEDIA_LENGTH = "Dur�e m�dia (m:s)";
    public static String MEDIA_START_TAG = "Rep�re d�part (m:s)";
    public static String MEDIA_FINISH_TAG = "Rep�re fin (m:s)";
    public static String MEDIA_NO_INFORMATION_PANEL_MESSAGE = "Aucune Information";
    public static String INFORMATION_MEDIA_TITLE_NAME = "Informations";
    public static String MEDIA_TITLE_NAME = "M�dia";
    public static String NO_MEDIA_FILE_MESSAGE = "Aucun fichier m�dia";
    public static String QUICK_TIME_NO_INSTALLED_ERROR_MESSAGE = "Pas de prise en compte vid�o : Installer QuickTime";
    public static String CONTROLE_MEDIA_TITLE_NAME = "Contr�le";

    // Editeur complet d'une t�che
    public static String EXECUTION_CONDITION_TITLE_NAME = "Conditions d'�x�cution";
    public static String OBJECT_LIST_TITLE_NAME = "Liste des objets";
    public static String POSTCONDITION_TITLE_NAME = "Postconditions";
    public static String EFFETSDEBORD_TITLE_NAME = "Effets de bord";
    public static String NO_MOTHER_TASK_MESSAGE = "Aucune t�che m�re";
    public static String NO_LEFT_TASK_MESSAGE = "Aucune t�che � gauche";
    public static String NO_RIGHT_TASK_MESSAGE = "Aucune t�che � droite";
    public static String NO_SUB_TASK_MESSAGE = "Aucune sous-t�che";
    public static String NO_NUMERO_TASK = "Pas de num�ro";

    // Caract�ristique d'un projet
    public static String INTERVIEWED_NAME_TABLE = "Interview�";
    public static String PLACE_INFORMATION_TABLE = "Coordonn�es";
    public static String STATUT_TABLE = "Statut";
    public static String SENIORITY_TABLE = "Anciennet�";
    public static String DATE_TABLE = "Date";
    public static String INTERVIEW_TYPE_TABLE = "Type de recueil";
    public static String SEARCHED_INFORMATIONS_TABLE = "Informations";
    public static String INTERVIEW_NAME_TABLE = "Interviewer";
    public static String INTERVIEW_NEW_OBJECT_TABLE = "Nouvel interview";
    public static String GENERAL_DESCRIPTION_PROJECT_DIALOG_TITLE = "Description g�n�rale";
    public static String COMPANY_LABEL_NAME = "Entreprise";
    public static String SITE_LABEL_NAME = "Site";
    public static String POSTE_TYPE_LABEL_NAME = "Type de Poste";
    public static String DATE_STUDY = "Date du cas �tudi�";
    public static String OTHER_RESOURCES = "Autres Resources";
    public static String INTERVIEWED_DESCRIPTION_PROJECT_DIALOG_TITLE = "Description des interview�s";
    public static String JUSTIFICATION_PROJECT_DIALOG_TITLE = "Justification de l'�tude de cas (en quoi ce cas refl�te un probl�me de charge)";
    public static String REMOVE_INTERVIEW_MESSAGE_TITLE = "Suppression d'interviews";
    public static String ASK_REMOVE_INTERVIEW_MESSAGE_TITLE = "Souhaitez-vous supprimer l'interview de";

    // Editeur des attributs d'une t�che
    public static String EDITOR_MESSAGE_TITLE = "Editeur de caract�ristiques";
    public static String EDITOR_OPERATOR_NAME = "D�composition";
    public static String EDITOR_NAME_NAME = "Nom";
    public static String EDITOR_EXECUTANT_NAME = "Ex�cutant";

    // Gestion du projet
    public static String EXTENSION_EXPRESS_FILTER_NAME = "Fichier Express (*.spf)";
    public static String EXTENSION_KMADE_FILTER_NAME = "Fichier K-MAD (*.kxml)";
    public static String SET_EXISTING_PROJECT_LONG_MESSAGE = "Modification des propri�t�s du projet";
    public static String SET_EXISTING_PROJECT_SHORT_MESSAGE = "Propri�t�s";
    public static String STATE_LOAD_TITLE_NAME = "Etat";
    public static String ELEMENT_PARSE_PROBLEM_MESSAGE = "Probl�me avec l'�l�ment";
    public static String XML_PARSER_PROBLEM_MESSAGE = "Probl�me avec le ParserXML";
    public static String XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE = "entit�s manquantes dans le fichier XML";

    // Gestion des objets
    public static String OPEN_OBJECT_ACTION_MESSAGE = "Charger des objets";
    public static String SAVE_OBJECT_ACTION_MESSAGE = "Enregistrer les objets";
    public static String SAVE_OBJECT_AS_ACTION_MESSAGE = "Enregistrer les objets sous ...";
    public static String WRITE_BEFORE_OPEN_OBJECTS_MESSAGE = "Enregistrer les objets avant l'ouverture d'objets existant";

    // Gestion du chargement
    public static String OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME = "Chargement d'un projet MDA annul�";
    public static String OPEN_ERROR_FILE = "Erreur d'ouverture";
    public static String OPEN_EXPRESS_OK_FILE = "Chargement r�ussi : ";
    public static String OPEN_EXPRESS_NO_OK_FILE = "Chargement �chou� : ";
    public static String EXPRESS_OBJECTS_TITLE_MESSAGE = "Objets de l'utilisateur";
    public static String GRAPHICAL_OBJECTS_MESSAGE = "Objets graphiques";
    public static String CREATE_GRAPHICAL_TASKS_MESSAGE = "Cr�ation des t�ches graphiques";
    public static String CREATE_GRAPHICAL_EDGES_MESSAGE = "Cr�ation des liens";
    public static String LOAD_PROJECT_FINISHED_MESSAGE = "Chargement termin�";
    public static String LOAD_CONSOLE_TITLE_NAME = "Console de chargement";
    public static String IMPLICIT_STOP_LOAD_SPF_FILE = "Chargement stopp� : Probl�me dans la cr�ation des objets de l'utilisateur";
    public static String EXPLICIT_STOP_LOAD_SPF_FILE_DURING_OBJECT = "Chargement annul� : Suppression des objets Express";
    public static String EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT = "Chargement annul� : Suppression des objets Express et Graphiques";

    // Gestion de la sauvegarde
    public static String SAVE_CANCELLED_EXPRESS_FILECHOOSER_NAME = "Enregistrement du projet K-MAD courant annul�";
    public static String WRITE_EXPRESS_ERROR_FILE = "Erreur d'enregistrement du projet K-MAD : ";
    public static String WRITE_ERROR_FILE = "Erreur d'enregistrement d'un projet K-MAD";
    public static String WRITE_EXPRESS_OK_FILE = "Enregistrement du projet K-MAD r�ussi : ";
    public static String WRITE_EXPRESS_NO_OK_FILE = "Enregistrement du projet K-MAD �chou� : ";
    public static String WRITE_BEFORE_OPEN_PROJECT_MESSAGE = "Enregistrer le projet courant avant l'ouverture d'un projet existant";
    public static String WRITE_BEFORE_EXIT_TOOL_MESSAGE = "Enregistrer le projet courant avant de quitter";
    public static String WRITE_BEFORE_NEW_PROJECT_MESSAGE = "Enregistrer le projet courant \n avant de cr�er un nouveau projet";
    public static String WRITE_BEFORE_CLOSE_PROJECT_MESSAGE = "Enregistrer le projet courant \n avant de le fermer";
    public static String WRITE_BEFORE_CHANGE_LOCALE_MESSAGE = "Enregistrer le projet avant \n de changer la localisation";
    public static String SAVE_CONSOLE_TITLE_NAME = "Console d'enregistrement";
    public static String SAVE_PROJECT_FINISHED_MESSAGE = "Enregistrement termin�";
    public static String IMPLICIT_STOP_SAVE_FILE_MESSAGE = "Enregistrement stopp� : Probl�me dans l'enregistrement des objets de l'utilisateur";
    public static String EXPLICIT_STOP_SAVE_FILE_MESSAGE = "Enregistrement annul� : Suppression des objets K-MAD";

    // Outil pour lister les entit�s
    public static String ENTITY_LIST_DIALOG_TITLE = "Outil d'introspection de l'�tat du monde";
    public static String FORCE_ENTITY_LIST_ACTION_MESSAGE = "Forcer Affichage";

    // Pr�f�rences de l'outil
    public static String GENERAL_TABBED_NAME = "G�n�ral";
    public static String TASK_MODEL_EDITOR_TABBED_NAME = "T�ches";
    public static String TASK_MODEL_EDITOR_TABBED_LONG_NAME = "Editeur de T�ches";
    public static String TREE_EDITOR_TABBED_NAME = "Arbres";
    public static String TREE_EDITOR_TABBED_LONG_NAME = "Editeur d'Arbres";

    public static String GENERAL_PANEL_CONFIGURATION_PANEL_NAME = "Configuration";
    public static String GENERAL_PANEL_AUTOMATIC_SAVE_LABEL = "Sauvegarde automatique :";
    public static String GENERAL_PANEL_BUG_MESSAGE_LABEL = "Autoriser envoi de rapport de bug";
    public static String GENERAL_PANEL_GARBAGE_COLLECTOR_LABEL = "Forcer le garbage collector :";
    public static String GENERAL_PANEL_SPLASH_SREEN_LABEL = "Affichage Splash Screen";
    public static String GENERAL_PANEL_LANGUAGE_PANEL_NAME = "Langue";
    public static String GENERAL_PANEL_LANGUAGE_LABEL = "Choix de la langue : ";
    public static String GENERAL_PANEL_KEYS_PANEL_NAME = "Raccourcis";
    public static String GENERAL_PANEL_ZOOM_LABEL = "Pas de zoom : ";

    public static String TASK_PANEL_NAME_PANEL_NAME = "Nom";
    public static String TASK_PANEL_NAME_FONT_NAME_LABEL = "Police : ";
    public static String TASK_PANEL_NAME_FONT_SIZE_LABEL = "Taille : ";
    public static String TASK_PANEL_NAME_FONT_STYLE_LABEL = "Style : ";
    public static String TASK_PANEL_NAME_FONT_COLOR_LABEL = "Couleur : ";
    public static String TASK_PANEL_NAME_BACKGROUND_COLOR_LABEL = "Couleur d'arri�re plan : ";
    public static String TASK_PANEL_DECOMPOSITION_PANEL_NAME = "D�composition";

    public static String TASK_PANEL_MODEL_PANEL_NAME = "Style Pr�d�fini";
    public static String TASK_PANEL_MODEL_CHOICE_LABEL = "Choix :";
    public static String TASK_PANEL_MODEL_SAVE_BUTTON_LABEL = "Sauvegarde";
    public static String TASK_PANEL_MODEL_LOAD_BUTTON_LABEL = "Chargement";
    public static String TASK_PANEL_OPTIONS_PANEL_NAME = "Options";
    public static String TASK_PANEL_OPTIONS_TRIGGER_EVENT_LABEL = "Indiquer les t�ches d�clencheuses d'�v�nements";
    public static String TASK_PANEL_OPTIONS_ACTOR_TASK_LABEL = "Indiquer l'acteur de la t�che";
    public static String TASK_PANEL_PREVIEW_PANEL_NAME = "Aper�u";

    public static String TASK_TREE_PANEL_LAYOUT_PANEL_NAME = "Agencement";
    public static String TASK_TREE_PANEL_LAYOUT_ORIENTATION_LABEL = "Orientation de l'arbre";
    public static String TASK_TREE_PANEL_LAYOUT_DISTANCE_LABEL = "Distance entre les noeuds";
    public static String TASK_TREE_PANEL_LAYOUT_SELECTION_COLOR_LABEL = "Couleur de s�lection d'une t�che";
    public static String TASK_TREE_PANEL_LAYOUT_LEVEL_DISTANCE_LABEL = "Hauteur des liens";
    public static String TASK_TREE_PANEL_OPTIONS_PANEL_NAME = "Options";
    public static String TASK_TREE_PANEL_OPTIONS_ORTHOGONAL_EDGE_LABEL = "Activer l'orthogonalit� des liens";

    // temps (en millisecondes) d'attente avec l'affichage du curseur de grab
    public static long TEMPORISATION_GRAB = 1000;

    // distance (en pixel) servant pour afficher le trait "aimant"
    public static int DISTANCE_AIMANT = 20;

    // Cl�s des properties
    public static final String LEVEL_DISTANCE_NAME = "levelDistance";
    public static final String TASK_MODEL_ORIENTATION_NAME = "taskModelOrientation";
    public static final String TASK_DISTANCE_NAME = "taskDistanceName";
    public static final String TASK_SELECTION_COLOR_NAME = "taskSelectionColor";
    public static final String ORTHOGONAL_EDGE_NAME = "isOrthogonalEdge";
    public static final String TASK_FONT_NAME = "taskFont";
    public static final String TASK_FONT_SIZE_NAME = "taskFontSize";
    public static final String TASK_FONT_STYLE_NAME = "taskFontStyle";
    public static final String TASK_FONT_COLOR_NAME = "taskFontColor";
    public static final String TASK_BACKGROUND_COLOR_NAME = "taskBackgroundColor";
    public static final String TASK_SKIN_STYLE_NAME = "taskSkinStyle";
    public static final String DECOMPOSITION_FONT_NAME = "decompositionFont";
    public static final String DECOMPOSITION_FONT_SIZE_NAME = "decompositionFontSize";
    public static final String DECOMPOSITION_FONT_STYLE_NAME = "decompositionFontStyle";
    public static final String DECOMPOSITION_FONT_COLOR_NAME = "decompositionFontColor";
    public static final String TRIGGER_EVENT_NAME = "isTriggerEventVisible";
    public static final String ACTOR_TASK_NAME = "isActorTaskVisible";
    public static final String AUTOMATIC_SAVE_NAME = "isAutomaticSave";
    public static final String TIME_SAVE_PERIOD_NAME = "timeSavePeriod";
    public static final String SPLASH_SCREEN_NAME = "isSplashScreenVisible";
    public static final String BUG_MESSAGE_NAME = "isBugMessageEnabled";
    public static final String LANGUAGE_NAME = "language";
    public static final String WHEEL_ZOOM_NAME = "wheelZoom";

    // Noms de couleurs
    public static String COLOR_NO_COLOR = "Aucune";
    public static String COLOR_BLUE = "Bleu";
    public static String COLOR_GREEN = "Vert";
    public static String COLOR_RED = "Rouge";
    public static String COLOR_BLACK = "Noir";
    public static String COLOR_YELLOW = "Jaune";
    public static String COLOR_WHITE = "Blanc";

    // Noms de polices
    public static String ARIAL_FONT = "Arial";
    public static String TIMES_FONT = "Times New Roman";
    public static String TAHOMA_FONT = "Tahoma";

    // Noms de styles de police
    public static String STYLE_PLAIN = "Normal";
    public static String STYLE_BOLD = "Gras";
    public static String STYLE_ITALIC = "Italique";
    public static String STYLE_BOLD_ITALIC = "Gras+Italique";

    // Noms de langues
    public static String FRENCH_LANGUAGE = "French";
    public static String ENGLISH_LANGUAGE = "English";

    // Noms des langues dans leur langue
    public static String FRENCH_LANGUAGE_INFO = "Fran�ais";
    public static String ENGLISH_LANGUAGE_INFO = "English";

    // Messages commumns
    public static String YES_MESSAGE = "Oui";
    public static String NO_MESSAGE = "Non";
    public static String ALL_MESSAGE = "Tout";
    public static String VALID_MESSAGE = "Valider";
    public static String APPLY_MESSAGE = "Appliquer";
    public static String CANCEL_MESSAGE = "Annuler";
    public static String SAVE_AS_MESSAGE = "Enregistrer sous ...";
    public static String OVERWRITE_FILE_MESSAGE = "Remplacer fichier existant ?";
    public static String GO_BACK_MESSAGE = "Fermer";
    public static String BROWSE_DIRECTORY_OR_FILE_MESSAGE = "Parcourir";
    public static String REMOVE_MESSAGE = "Supprimer";
    public static String CONFIRMATION_DIALOG_MESSAGE = "Message de confirmation";

    // Action des ToolBars
    public static String FILE_MENU_MESSAGE = "Fichier";
    public static String EDITING_MENU_MESSAGE = "Edition";
    public static String VIEW_MENU_MESSAGE = "Visualisation";
    public static String OBJECT_MENU_MESSAGE = "Objets";
    public static String PROJECT_MENU_MESSAGE = "Projet";
    public static String HELP_MENU_MESSAGE = "Aide";
    public static String TOOLS_MENU_MESSAGE = "Outils";

    public static String NEW_PROJECT_ACTION_MESSAGE = "Nouveau projet";
    public static String OPEN_PROJECT_ACTION_MESSAGE = "Ouvrir un fichier projet ou objets";
    public static String SAVE_PROJECT_ACTION_MESSAGE = "Enregistrer projet";
    public static String SAVE_PROJECT_AS_ACTION_MESSAGE = "Enregistrer projet sous ...";
    public static String CLOSE_PROJECT_ACTION_MESSAGE = "Fermer projet";
    public static String PRINT_PROJECT_ACTION_MESSAGE = "Imprimer projet";
    public static String EXIT_ACTION_MESSAGE = "Quitter";
    public static String HIDE_GRID_ACTION_MESSAGE = "Cacher grille";
    public static String SHOW_GRID_ACTION_MESSAGE = "Montrer grille";
    public static String CHOICE_GRID_SIZE_MESSAGE = "Choisir taille de la grille";
    public static String HIDE_RULE_ACTION_MESSAGE = "Cacher r�gle";
    public static String SHOW_RULE_ACTION_MESSAGE = "Montrer r�gle";
    public static String PREFERENCE_ACTION_MESSAGE = "Pr�f�rences...";
    public static String DEBUG_INFO_ACTION_MESSAGE = "Info de d�veloppement";
    public static String ABOUT_ACTION_MESSAGE = "A Propos ...";
    public static String HELP_MODEL_ACTION_MESSAGE = "Aide sur K-MAD";
    public static String HELP_TOOL_ACTION_MESSAGE = "Aide sur KMADe";
    public static String HISTORY_ACTION_MESSAGE = "Historique de KMADe";
    public static String UNDO_ACTION_MESSAGE = "Annuler derni�re action";
    public static String REDO_ACTION_MESSAGE = "R�tablir derni�re action";
    public static String CUT_ACTION_MESSAGE = "Couper";
    public static String COPY_ACTION_MESSAGE = "Copier";
    public static String PASTE_ACTION_MESSAGE = "Coller";
    public static String SHOW_CLIPBOARD_MESSAGE = "Afficher fen�tre presse-papier";
    public static String SEARCH_ACTION_MESSAGE = "Recherche avanc�e ...";
    public static String SIMULATION_ACTION_MESSAGE = "Simulation";
    public static String INTERROGATION_ACTION_MESSAGE = "Statistiques";
    public static String CHECK_COHERENCE_ACTION_MESSAGE = "Coh�rence";
    public static String ENTITY_LIST_ACTION_MESSAGE = "Liste des entit�s de l'�tat du monde";
    public static String VERSION_MESSAGE = "Version";
    public static String MESSAGES_MESSAGE = "Messages";
    public static String ERREURS_MESSAGE = "Erreurs";
    public static String FIND_REPLACE_ACTION_MESSAGE = "Chercher/Remplacer";

    public static String GRID_SIZE_ACTION_MESSAGE = "Taille de la grille (compris entre 1 et 40)";
    public static String INPUT_GRID_SIZE_MESSAGE = "Choix de la taille de la grille";
    public static String INPUT_GRID_SIZE_ERROR_MESSAGE = "Valeur de la grille non conforme";

    public static String NEW_UNKNOWN_TASK_ACTION_MESSAGE = "Nouvelle t�che inconnue";
    public static String NEW_ABSTRACT_TASK_ACTION_MESSAGE = "Nouvelle t�che abstraite";
    public static String NEW_INTERACTION_TASK_ACTION_MESSAGE = "Nouvelle t�che interaction";
    public static String NEW_FEEDBACK_TASK_ACTION_MESSAGE = "Nouvelle t�che syst�me";
    public static String NEW_USER_TASK_ACTION_MESSAGE = "Nouvelle t�che utilisateur";
    public static String EDIT_TASK_ACTION_MESSAGE = "Edition de la t�che";
    public static String COMPLETE_EDIT_TASK_ACTION_MESSAGE = "Edition compl�te de la t�che";

    public static String COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU = "Edition compl�te";

    public static String UNKNOWN_TASK_MESSAGE = "Inconnue";
    public static String ABSTRACT_TASK_MESSAGE = "Abstraite";
    public static String INTERACTION_TASK_MESSAGE = "Interaction";
    public static String FEEDBACK_TASK_MESSAGE = "Systeme";
    public static String USER_TASK_MESSAGE = "Utilisateur";

    public static String DELETE_CELL_ACTION_MESSAGE = "Supprimer �l�ment(s) du mod�le";

    public static String DELETE_CELL_ACTION_MESSAGE_PIE_MENU = "Supprimer";

    public static String PREVIEW_WINDOW_ACTION_MESSAGE = "Afficher fen�tre aper�ue";
    public static String ZOOM_IN_ACTION_MESSAGE = "Agrandissement du mod�le de t�ches (+)";
    public static String ZOOM_OUT_ACTION_MESSAGE = "D�grossissement du mod�le de t�ches (-)";
    public static String ZOOM_DEFAULT_ACTION_MESSAGE = "Taille du mod�le de t�ches par d�faut";
    public static String ZOOM_GLOBAL_ACTION_MESSAGE = "Vue globale du mod�le de t�ches";
    public static String MAGNET_OPTION_ACTION_MESSAGE = "Activer/D�sactiver l'aimant";
    public static String JUSTIFY_OPTION_ACTION_MESSAGE = "Respatialisation d'une t�che";
    public static String SAME_EXECUTANT_MESSAGE = "Uniformisation des sous ex�cutants";
    public static String VISIBLE_LABEL_ACTION_MESSAGE = "Activer/D�sactiver l'affichage des libell�s";
    public static String COLOR_LABEL_ACTION_MESSAGE = "Activer/D�sactiver la couleur des libell�s";
    /*** Boite de dialogue lancement simulation ***/
    public static String OPEN_SIMULATION_MESSAGE = "Lancement de la boite de dialogue de simulation";

    public static String TREE_TASK_PRINT_TITLE_MESSAGE = "Arbre de t�ches";
    public static String USER_CARDS_PRINT_TITLE_MESSAGE = "Fiches utilisateur";
    public static String OBJECTS_PRINT_TITLE_MESSAGE = "Objets du monde";
    public static String PRINT_ACTION_MESSAGE = "Impression";
    public static String LAYOUT_PRINT_ACTION_MESSAGE = "Mise en page";
    public static String PRINT_PREVIEW_ACTION_MESSAGE = "Aper�u avant impression";
    public static String PDF_EXPORT_ACTION_MESSAGE = "Exporter en PDF";
    public static String EPS_EXPORT_ACTION_MESSAGE = "Exporter en EPS";
    public static String PORTRAIT_ACTION_MESSAGE = "Portrait";
    public static String LANDSCAPE_ACTION_MESSAGE = "Paysage";
    public static String SCALE_ACTION_MESSAGE = "Echelle";
    public static String INPUT_SCALE_ACTION_MESSAGE = "Choix de la valeur de l'�chelle";
    public static String PREVIEW_ZOOM_ACTION_MESSAGE = "Agrandissement/Grossissement de l'aper�u";
    public static String SAVE_HISTORIC_ACTION_MESSAGE = "Sauver historique";
    public static String CLEAR_HISTORIC_ACTION_MESSAGE = "Effacer historique";

    // Messages li�s aux expressions
    public static String PRINT_SCALE_ADAPT_MESSAGE = "Adapter au mod�le";
    public static String PRINT_SCALE_PERSONALIZE_MESSAGE = "Personnaliser...";
    public static String RESET_ACTION_MESSAGE = "Reset";
    public static String CONCRETE_OBJECTS_LIST_MESSAGE = "Liste Objets";
    public static String EDITOR_PRE_TITLE_NAME = "Editeur Precondition";
    public static String EDITOR_POST_TITLE_NAME = "Editeur EffetsDeBord";
    public static String EDITOR_ITER_TITLE_NAME = "Editeur Iteration";
    public static String CAUSE_MESSAGE = "  CAUSE";
    public static String VALUE_MESSAGE = "  RESULTAT";
    public static String CHECK_ACTION_MESSAGE = "V�rification de l'expression";
    public static String EVALUATE_ACTION_MESSAGE = "Evaluation de l'expression";
    public static String STRING_TO_INTEGER = "Erreur : impossible de transformer le texte en entier";
    public static String STRING_TO_NUMBER = "Erreur : impossible de transformer le texte en nombre";
    public static String NO_EXPRESSION_MESSAGE = "Aucune expression";
    public static String NO_FIRST_EXPRESSION_MESSAGE = "Expression non bool�enne ne peut �tre utilis�e toute seule";
    public static String MISSING_USER_VALUE_MESSAGE = "Valeur(s) utilisateur manquante(s)";
    public static String PARSER_PROBLEM_MESSAGE = "Probl�me dans le module d'analyse grammaticale";
    public static String LEXICAL_ERROR_MESSAGE = "Erreur lexicale";
    public static String SYNTAXICAL_ERROR_MESSAGE = "Erreur syntaxique";
    public static String SEMANTICAL_ERROR_MESSAGE = "Erreur s�mantique";
    public static String LINE_MESSAGE = "ligne";
    public static String COLUMN_MESSAGE = "colonne";
    public static String ENCOUNTERED_CHAR_MESSAGE = "caract�re rencontr�";
    public static String ENCOUNTERED_TOKEN_MESSAGE = "texte rencontr�";

    public static String CHECK_ALL_PRECONDITIONS = "V�rification des pr�conditions ...";
    public static String PRECONDITION_OK_MESSAGE = "pr�condition correcte";
    public static String PRECONDITION_NO_OK_MESSAGE = "pr�condition non correcte";
    public static String PRECONDITION_EVAL_OK_MESSAGE = "pr�condition �valu�e";
    public static String PRECONDITION_EVAL_NO_OK_MESSAGE = "pr�condition non �valu�e";
    public static String PRECONDITION_OF_TASK_OK_MESSAGE = "Pr�condition correcte pour la t�che";
    public static String PRECONDITION_OF_TASK_NO_OK_MESSAGE = "Pr�condition incorrecte pour la t�che";
    public static String PRECONDITION_ERROR_MESSAGE = "Erreur sur la pr�condition";
    public static String NOT_YET_CHECKED_PRECONDITION_MESSAGE = "Pr�condition non v�rifi�e";
    public static String PRECONDITION_CHECKED_AND_BUILT_MESSAGE = "V�rification et cr�ation des pr�conditions termin�es";
    public static String PRECONDITION_PROGRESSBAR_MESSAGE = "V�rification et construction des pr�conditions";

    public static String EXIT_WITHOUT_SAVE_MESSAGE = "Quitter en modifiant l'�tat du mod�le. L'historique des modifications sera initialis�";
    public static String CHECK_ALL_EFFETSDEBORDS = "V�rification des effetsdebords ...";
    public static String EFFETSDEBORD_OK_MESSAGE = "effetsdebord correcte";
    public static String EFFETSDEBORD_NO_OK_MESSAGE = "effetsdebord non correcte";
    public static String EFFETSDEBORD_EVAL_OK_MESSAGE = "effetsdebord �valu�e et mod�le K-MAD modifi�";
    public static String EFFETSDEBORD_EVAL_NO_OK_MESSAGE = "effetsdebord non �valu�e";
    public static String EFFETSDEBORD_OF_TASK_OK_MESSAGE = "EffetsDeBord correcte pour la t�che";
    public static String EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE = "EffetsDeBord incorrecte pour la t�che";
    public static String EFFETSDEBORD_ERROR_MESSAGE = "Erreur sur la effetsdebord";
    public static String NOT_YET_CHECKED_EFFETSDEBORD_MESSAGE = "EffetsDeBord non v�rifi�e";
    public static String EFFETSDEBORD_CHECKED_AND_BUILT_MESSAGE = "V�rification et cr�ation des effetsdebords termin�es";
    public static String EFFETSDEBORD_PROGRESSBAR_MESSAGE = "V�rification et construction des effetsdebords";
    public static String EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE = "Objet Concret";
    public static String EFFETSDEBORD_HISTORY_TITLE_MESSAGE = "Historique de K-MAD";
    public static String EFFETSDEBORD_LOAD_STATE_MESSAGE = "Charger";
    public static String NO_SELECTED_HISTORIC_MESSAGE = "Historique non s�lectionn�";
    public static String HISTORIC_LOADED_MESSAGE = "Historique charg�e";
    public static String HISTORIC_NO_LOADED_MESSAGE = "Historique non charg�e";

    public static String CHECK_ALL_ITERATIONS = "V�rification des it�rations ...";
    public static String ITERATION_OK_MESSAGE = "it�ration correcte";
    public static String ITERATION_NO_OK_MESSAGE = "it�ration non correcte";
    public static String ITERATION_EVAL_OK_MESSAGE = "it�ration �valu�e";
    public static String ITERATION_EVAL_NO_OK_MESSAGE = "it�ration non �valu�e";
    public static String NOT_YET_CHECKED_ITERATION_MESSAGE = "It�ration non v�rifi�e";
    public static String STOP_LOOP_ITERATION_MESSAGE = "It�ration termin�e";
    public static String CONTINUE_LOOP_ITERATION_MESSAGE = "It�ration en cours";
    public static String ITERATION_MESSAGE = "ITERATION";
    public static String ITERATION_ERROR_MESSAGE = "Erreur sur l'it�ration";
    public static String ITERATION_OF_TASK_OK_MESSAGE = "It�ration correcte pour la t�che";
    public static String ITERATION_OF_TASK_NO_OK_MESSAGE = "It�ration incorrecte pour la t�che";
    public static String ITERATION_CHECKED_AND_BUILT_MESSAGE = "V�rification et cr�ation des it�rations termin�es";
    public static String ITERATION_PROGRESSBAR_MESSAGE = "V�rification et construction des it�rations";

    // Calculatrice pour les expressions
    public static String CLEAR_EXPRESSION_MESSAGE = "Effacer";
    public static String CHECK_VALIDATE_MESSAGE = "V�rifier";
    public static String EVALUATE_FORCE1_MESSAGE = "Evaluer";
    public static String PRECONDITION_EDITION_MESSAGE = "Edition de la pr�condition";
    public static String EFFETSDEBORD_EDITION_MESSAGE = "Edition de la effetsdebord";
    public static String ITERATION_EDITION_MESSAGE = "Edition de l'it�ration";
    public static String PRECONDITION_TEXTUEL_EDITION_MESSAGE = "Edition textuelle de la pr�condition";
    public static String EFFETSDEBORD_TEXTUEL_EDITION_MESSAGE = "Edition textuelle de la effetsdebord";
    public static String ITERATION_TEXTUEL_EDITION_MESSAGE = "Edition textuelle de l'it�ration";
    public static String LITTERAUX_TITLE_MESSAGE = "Lit�raux";
    public static String OPERATORS_TITLE_MESSAGE = "Op�rateurs";
    public static String FUNCTIONS_TITLE_MESSAGE = "Fonctions";
    public static String ITERATORS_TITLE_MESSAGE = "It�rateurs";
    public static String LOOP_VARIANT_MESSAGE = "Variant Boucle";

    // L'outil de recherche
    public static String WRITE_TXT_FILE_OK = "Enregistrement du fichier texte r�ussi : ";
    public static String WRITE_TXT_FILE_ERROR = "Erreur d'enregistrement du fichier texte";
    public static String SAVE_CANCELLED_TEXT_FILECHOOSER_NAME = "Enregistrement du fichier texte annul�";
    public static String SEARCH_NAME_NAME = "Nom de la T�che";
    public static String WHOLE_TASK_NAME_OPTION_REPLACE_MESSAGE = "Nom Complet";
    public static String SEARCH_FAC_NAME = "T�che optionnelle";
    public static String SEARCH_INT_NAME = "T�che interruptible";
    public static String EXTENSION_TEXT_FILTER_SEARCH_NAME = "Fichier texte (*.txt)";
    public static String SEARCH_MESSAGE_TITLE = "Outil de recherche ...";
    public static String FIND_MESSAGE = "Rechercher";
    public static String REPLACE_MESSAGE = "Remplacer avec";
    public static String DIRECTION_FIND_MESSAGE = "Direction";
    public static String FORWARD_DIRECTION_FIND_MESSAGE = "En Avant";
    public static String BACKWARD_DIRECTION_FIND_MESSAGE = "En Arri�re";
    public static String SCOPE_FIND_MESSAGE = "Etendu des t�ches";
    public static String ALL_FIND_MESSAGE = "Toutes";
    public static String SELECTION_FIND_MESSAGE = "Selection";
    public static String OPTION_FIND_MESSAGE = "Options";
    public static String CASE_SENSITIVE_OPTIONS_FIND_MESSAGE = "Sensible � la case";
    public static String WHOLE_TASK_NAME_OPTIONS_FIND_MESSAGE = "Nom de t�che complet";
    public static String REGULAR_EXPRESSIONS_OPTIONS_FIND_MESSAGE = "Expressions r�guli�res";
    public static String SEARCH_RESULT_FIND_MESSAGE = "R�sultat Recherche";
    public static String FIND_ACTION_FIND_MESSAGE = "Chercher";
    public static String SAVE_OCCURENCES_ACTION_FIND_MESSAGE = "Enregistrer Occurences";
    public static String REPLACE_NEXT_ACTION_FIND_MESSAGE = "Remplacer/Suivant";
    public static String REPLACE_PREVIOUS_ACTION_FIND_MESSAGE = "Remplacer/Pr�c�dent";
    public static String REPLACE_ACTION_FIND_MESSAGE = "Remplacer";
    public static String REPLACE_ALL_ACTION_FIND_MESSAGE = "Remplacer tout";
    public static String NO_TASK_AVAILABLE_FOR_SEARCHING_TOOL_MESSAGE = "Aucune t�che disponible pour la recherche";
    public static String TASK_NOT_FOUNDED_ACCORDING_FIND_CRITERIAS = "T�che non trouv�e";
    public static String COUNT_TASKS_FOUNDED_MESSAGE = "Nombre de t�ches";

    // Simulation de mani�re g�n�rale
    public static String SIMULATION_TITLE_MESSAGE = "Simulation";
    public static String ERROR_ITERATION_VIEW_MESSAGE = "Erreur";
    public static String PREDICAT_ITERATION_VIEW_MESSAGE = "Pr�dicat";
    public static String USER_VALUE_ITERATION_VIEW_MESSAGE = "Val. Utilisateur";
    public static String SCENARIO_FILTER_NAME = "Fichier de sc�nario" + " ("
	    + KMADE_SCENARIO_EXTENSION_FILE + ")";
    public static String ACTIONS_SIMULATION_MESSAGE = "Actions";
    public static String EDIT_USER_VALUES_SIMULATION_MESSAGE = "Editer Valeurs Utilisateur";
    public static String RECORD_SCENARIO_MESSAGE = "Enregistrer sc�nario";
    public static String OPEN_CANCELLED_SCENARIO_FILECHOOSER_NAME = "Chargement d'un sc�nario annul�";
    public static String SAVE_CANCELLED_SCENARIO_FILECHOOSER_NAME = "Enregistrement d'un sc�nario annul�";
    public static String SUCCEEDED_SAVE_SCENARIO_MESSAGE = "Enregistrement du sc�nario r�ussi";
    public static String NO_SUCCEEDED_SAVE_SCENARIO_MESSAGE = "Enregistrement du sc�nario �chou�";
    public static String AUTHORIZED_EXECUTER_USER_MESSAGE = "Autoris�e";
    public static String NO_AUTHORIZED_EXECUTER_USER_MESSAGE = "Non Autoris�e";
    public static String DISABLED_CONSTRAINT_MESSAGE = "D�sactiv�e";
    public static String EVENT_TITLE_MESSAGE = "Ev�nements";
    public static String NO_CONCRETE_OBJECT = "Aucun objet concret courant";
    public static String NO_FIRING_EVENT_MESSAGE = "Aucun Ev�nement Provoqu�";
    public static String NO_FIRING_EVENT_TINY_MESSAGE = "Aucun Ev�nement";
    public static String FIRABLE_EVENT_STATE_MESSAGE = "D�clenchable";
    public static String NO_FIRABLE_EVENT_STATE_MESSAGE = "Non D�clenchable";
    public static String NO_SELECTED_ACTION_FOR_CONSTRAINTS_MESSAGE = "Aucune action \"ex�cuter\" s�lectionn�e";
    public static String NO_SELECTED_SCENARIO_ACTION_FOR_CONSTRAINTS_MESSAGE = "Aucune action \"ex�cuter\" du sc�nario s�lectionn�e";
    public static String CONSTRAINTS_TITLE_MESSAGE = "Contraintes";
    public static String SOURCE_TASK_EVENT_MESSAGE = "T�che";
    public static String NO_USER_MESSAGE = "Aucun Utilisateur";
    public static String EXECUTING_USER_TITLE_MESSAGE = "Utilisateur";
    public static String CONSTRAINTS_NAME_COLUMN_NAME = "Nom";
    public static String CONSTRAINTS_VALEUR_COLUMN_NAME = "Valeur";
    public static String CONSTRAINTS_STATE_COLUMN_NAME = "Etat";
    public static String PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE = "Respect�e";
    public static String PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE = "Non Respect�e";
    public static String PRECONDITION_CONSTRAINTS_ERROR_STATE_MESSAGE = "Erreur";
    public static String PRECONDITION_CONSTRAINTS_NEED_VALUES_STATE_MESSAGE = "Besoin Valeur(s)";
    public static String EFFETSDEBORD_EXECUTED_STATE_MESSAGE = "Ex�cut�e";
    public static String EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE = "Non Ex�cut�e";
    public static String USER_VALUES_TITLE = "Editeur des valeurs utilisateurs";
    public static String PRECONDITION_USER_EDITION_MESSAGE = "Valeurs utilisateur de la pr�condition";
    public static String EFFETSDEBORD_USER_EDITION_MESSAGE = "Valeurs utilisateur de la effetsdebord";
    public static String ITERATION_USER_EDITION_MESSAGE = "Valeurs utilisateur de l'it�ration";
    public static String TRIGGER_VALID_TASK_MESSAGE = "D�clencher";
    public static String NO_TRIGGER_CANCEL_TASK_MESSAGE = "Annuler D�clenchement";
    public static String EXECUTE_TASK_TRAITEMENT_MESSAGE = "Traitement de la t�che";
    public static String EXECUTION_CONSTRAINT_MESSAGE = "Contraintes d'ex�cutation";
    public static String USER_EXECUTION_CONSTRAINT_MESSAGE = "Utilisateur";
    public static String CAN_EXECUTE_TASK_MESSAGE = "peut ex�cuter la t�che";
    public static String CAN_NOT_EXECUTE_TASK_MESSAGE = "ne peut pas ex�cuter la t�che";
    public static String EVENT_TRIGGER_CONSTRAINT_MESSAGE = "Ev�nement";
    public static String NO_TRIGGER_EVENT_CONSTRAINT_MESSAGE = "Aucun d'�v�nement d�clencheur";
    public static String CAN_TRIGGER_TASK_MESSAGE = "d�clenche la t�che";
    public static String CAN_NOT_TRIGGER_CONSTRAINT_MESSAGE = "non g�n�r� et ne peut d�clencher la t�che";
    public static String PRECONDITION_CONSTRAINT_MESSAGE = "Pr�condition";
    public static String ACTION_CONSTRAINT_MESSAGE = "Actions";
    public static String GENERATE_EVENTS_CONSTRAINT_MESSAGE = "G�n�ration Ev�nements";
    public static String NO_GENERATED_EVENTS_CONSTRAINT_MESSAGE = "Pas d'�v�nement g�n�r�";
    public static String EFFETSDEBORD_CONSTRAINT_MESSAGE = "EffetsDeBord";
    public static String USER_VALUES_MISSING_OR_WRONG_MESSAGE = "Valeur(s) utilisateur manquante(s) ou erron�e(s)";
    public static String CONCRETE_OBJECT_NO_SELECTED_MESSAGE = "Objet(s) concret(s) non s�lectionn�(s)";
    public static String USER_VALUES_PROBLEM_MESSAGE = "Probl�me Valeurs Utilisateur";
    public static String FINISHED_SCENARIO_PROBLEM_MESSAGE = "Sc�nario Termin�e";
    public static String FINISHED_SIMULATION_PROBLEM_MESSAGE = "Simulation Termin�e";
    public static String FINISHED_SCENARIO_AND_SIMULATION_PROBLEM_MESSAGE = "Simulation et sc�nario Termin�s";
    public static String SELECT_TASK_TO_REPLAY_MESSAGE = "Veuillez selectionner une tache";
    public static String ITERATION_FINISH_NO_ACTION = "L'it�ration est fini, aucun effet de bord n'a lieu";
    // Les actions sur les t�ches pour lors de la simulation
    public static String EXECUTE_ACTION_SIMULATION_MESSAGE = "Ex�cuter";
    public static String PASS_ACTION_SIMULATION_MESSAGE = "Passer";
    public static String SUSPEND_ACTION_SIMULATION_MESSAGE = "Interrompre";
    public static String RESUME_ACTION_SIMULATION_MESSAGE = "Reprendre";
    public static String NO_RESUME_ACTION_SIMULATION_MESSAGE = "Abandonner";

    // L'�tat des t�ches pour lors de la simulation.
    public static String ACTIVE_STATE_TASK_SIMULATION_MESSAGE = "Active";
    public static String ACTIVE_ITERATION_STATE_TASK_SIMULATION_MESSAGE = "ActiveIte";
    public static String FINISHED_STATE_TASK_SIMULATION_MESSAGE = "Termin�e";
    public static String PASSIVE_STATE_TASK_SIMULATION_MESSAGE = "Passive";
    public static String NO_ACCESSIBLE_STATE_TASK_SIMULATION_MESSAGE = "Inactive";
    public static String PASSED_STATE_TASK_SIMULATION_MESSAGE = "Pass�e";
    public static String SUSPENDED_STATE_TASK_SIMULATION_MESSAGE = "Suspendue";
    public static String NO_RESUMED_STATE_TASK_SIMULATION_MESSAGE = "Abandonn�e";

    // Pour l'enregistrement de la simulation
    public static String RECORD_TITLE_MESSAGE = "Enregistrement";
    public static String RECORD_BUILDING_SCENARION_MESSAGE = "Sc�nario Courant";
    public static String RECORD_AVAILABLE_ACTIONABLE_MESSAGE = "Actions Disponibles";
    public static String FINISHED_RECORD_SIMULATION_MESSAGE = "Simulation du mod�le de t�ches K-MAD termin�e : sc�nario disponible ...";
    public static String NO_SELECTED_TASK_BEFORE_RECORD_SIMULATION_MESSAGE = "Veuillez selectionner une t�che";

    // Pour le rejeu de la simulation
    public static String REPLAY_TITLE_MESSAGE = "Rejeu";
    public static String REPLAY_SLOW_TEMPO = "Lent";
    public static String REPLAY_MODERATE_TEMPO = "Mod�r�";
    public static String REPLAY_SPEED_TEMPO = "Rapide";
    public static String SCENARIO_REPLAY_MESSAGE = "Sc�nario � Rejouer";
    public static String REPLAY_OPTIONS_MESSAGE = "Options de rejeu";
    public static String REPLAY_KEEP_USER_VALUES_MESSAGE = "Conserver valeurs";
    public static String REPLAY_TEMPO_MESSAGE = "Tempo";
    public static String NO_ROOT_TASK_PROBLEM_MESSAGE = "Pas de t�che racine";
    public static String NO_FOUNDED_TASK_TO_REPLAY_PROBLEM_MESSAGE = "T�che non trouv�e";
    public static String CAN_NOT_EXECUTE_TASK_TO_REPLAY_PROBLEM_MESSAGE = "Ne peut ex�cuter cette t�che tout de suite";

    public static String CONFIG_ERROR_DOM = "Erreur de configuration du parseur DOM";
    public static String CALL_NEW_DOCUMENT_BUILDER = "lors de l'appel � fabrique.newDocumentBuilder()";
    public static String SIMULATION_PARSING_ERROR = "Erreur lors du parsing du document";
    public static String CALL_CONSTRUCTEUR_PARSE = "lors de l'appel � construteur.parse(xml)";
    public static String SIMULATION_IO_ERROR = "Erreur d'entr�e/sortie";

    // Pour la boite "A Propos de ..."
    public static String PROJECT_LEADER_MESSAGE = "Responsable du Projet";
    public static String DEVELOPMENT_QUERING_TOOL_MESSAGE = "D�veloppement K-MADe et Interrogation K-MAD";
    public static String KMAD_SPECIFY_MESSAGE = "Sp�cification du mod�le K-MAD";
    public static String KMAD_ADAPTATOR_MESSAGE = "Adaptateur du mod�le K-MAD";
    public static String JAVA_GRAPHIC_LIBRARIES_MESSAGE = "Biblioth�ques Utilis�es";
    public static String KMADE_TEAM_MESSAGE = "Participants";
    public static String JAVA_LIBRARIES_MESSAGE = "Biblioth�ques";

    // Event

    public static String EVENT_TRIGGER = "Evénement déclancheur";
    public static String EVENT_ENABLE = "Evénement disponible";

    // Coh�rence du mod�le
    public static String COHERENCE_INTRO_ERROR = "L'outil de coh�rence a d�tect� ";
    public static String COHERENCE_ONE_ERROR = " erreur";
    public static String COHERENCE_SOME_ERROR = " erreurs";
    public static String COHERENCE_INTER = " et ";
    public static String COHERENCE_ONE_WARNING = " warning";
    public static String COHERENCE_SOME_WARNING = " warnings";
    public static String COHERENCE_VERIFICATION_TITLE = "Simulation refus�e ...";

    public static String NO_ALONE_MESSAGE_PROBLEM = "Pas de t�che seule";
    public static String PRECONDITION_EXPRESSION_MESSAGE_PROBLEM = "Probl�me dans l'expression de la pr�condition";
    public static String EFFETSDEBORD_EXPRESSION_MESSAGE_PROBLEM = "Probl�me dans l'expression de la effetsdebord";
    public static String ITERATION_EXPRESSION_MESSAGE_PROBLEM = "Probl�me dans l'expression de l'it�ration";
    public static String NO_ONLY_ONE_SUBTASK_MESSAGE_PROBLEM = "Pas de sous t�che unique";
    public static String TASK_HAS_NO_NAME_MESSAGE_PROBLEM = "Entrez un nom pour la tache";
    public static String INVALID_DECOMPOSITION_MESSAGE_PROBLEM = "Decomposition invalide";
    public static String NO_DECOMPOSITION_SPECIFIED_MESSAGE_PROBLEM = "D�composition non pr�cis�e";
    public static String ELEMENTARY_DECOMPOSITION_FOR_LEAF_TASK = "D�composition doit �tre �l�mentaire pour une t�che feuille";
    public static String NO_EXECUTANT_SPECIFIED_MESSAGE_WARNING = "Ex�cutant non d�fini";
    public static String SUBTASKS_EXECUTANT_DIFFERENT_MESSAGE_WARNING = "Ex�cutant diff�rent";
    public static String HIERARCHY_TYPE_MESSAGE = "Hi�rarchie";
    public static String EXPRESSION_TYPE_MESSAGE = "Expression";
    public static String TASKS_SPACE_LOCATION_MESSAGE = "Espace de t�ches";
    public static String PRECONDITION_LOCATION_MESSAGE = "Pr�condition";
    public static String EFFETSDEBORD_LOCATION_MESSAGE = "Action";
    public static String ITERATION_LOCATION_MESSAGE = "It�ration";
    public static String COHERENCE_TITLE_MESSAGE = "Coh�rence";
    public static String COHERENCE_CHECK_MESSAGE = "Forcer V�rification";
    public static String ERRORS_TITLE_MESSAGE = "Erreurs et Avertissements";
    public static String MESSAGE_ERROR_COLUMN_NAME = "Message";
    public static String TASK_NAME_COLUMN_NAME = "t�che";
    public static String ERROR_TYPE_COLUMN_NAME = "Type";
    public static String LOCATION_COLUMN_NAME = "Localisation";

    // Statistique du mod�le
    public static String COUNT_TASK_INTO_TASK_MODELS_MESSAGE = "Nombre de t�ches dans l'espace de t�ches";
    public static String COUNT_USER_TASK_MESSAGE = "Nombre d'ex�cutant de type utilisateur";
    public static String COUNT_UNKNOWN_TASK_MESSAGE = "Nombre d'ex�cutant de type inconnu";
    public static String COUNT_SYSTEM_TASK_MESSAGE = "Nombre d'ex�cutant de type syst�me";
    public static String COUNT_ABSTRACT_TASK_MESSAGE = "Nombre d'ex�cutant de type abstrait";
    public static String COUNT_INTERACT_TASK_MESSAGE = "Nombre d'ex�cutant de type interaction";
    public static String COUNT_UNKNOWN_DEC_TASK_MESSAGE = "Nombre de d�composition de type inconnu";
    public static String COUNT_ENABLING_DEC_TASK_MESSAGE = "Nombre de d�composition de type s�quenciel";
    public static String COUNT_CHOICE_DEC_TASK_MESSAGE = "Nombre de d�composition de type alternatif";
    public static String COUNT_CONCURRENT_DEC_TASK_MESSAGE = "Nombre de d�composition de type parall�le";
    public static String COUNT_ELEMENTARY_DEC_TASK_MESSAGE = "Nombre de d�composition de type �l�mentaire";
    public static String COUNT_NO_ORDER_DEC_TASK_MESSAGE = "Nombre de d�composition de type pas d'ordre";
    public static String STATISTIC_TITLE = "Outil de Statistiques";
    public static String TYPE_STATISTIC_MESSAGE = "Type";
    public static String RESULT_STATISTIC_MESSAGE = "Resultat";
    public static String REFRESH_STATISTIC_MESSAGE = "Mise � jour";

    //
    // Les constantes li�es aux images
    //

    public static final String PROGRESS_ICON_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "progress16x16.png";

    public static final String ACCEPT_ICON_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "accept.png";
    public static final String SPLASH_SCREEN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "splashscreen2.png";
    public static final String ICON_LOGO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "ilogo.png";
    public static final String INSERT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "insert.gif";
    public static final String SHOW_RULE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "ruler.gif";
    public static final String SHOW_GRID_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "grid.gif";
    public static final String HIDE_RULE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "noruler.gif";
    public static final String HIDE_GRID_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "nogrid.gif";
    public static final String CHOICE_GRID_SIZE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "askgrid.gif";
    public static final String OVERVIEW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "overview.gif";

    public static final String ASK_DIALOG_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "interrogation.png";
    public static final String INFO_DIALOG_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "information.png";

    public static final String ABSTRACT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "abstract.png";
    public static final String USER_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "user.png";
    public static final String INTERACTIF_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "interaction.png";
    public static final String FEEDBACK_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "feedback.png";
    public static final String UNKNOWN_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "unknown.png";

    public static final String ABSTRACT_TASK_48_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "abstract48.png";
    public static final String USER_TASK_48_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "user48.png";
    public static final String INTERACTIF_TASK_48_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "interaction48.png";
    public static final String FEEDBACK_TASK_48_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "feedback48.png";
    public static final String UNKNOWN_TASK_48_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "unknown48.png";

    public static final String ABSTRACT_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "abstract16.png";
    public static final String USER_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "user16.png";
    public static final String INTERACTIF_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "interaction16.png";
    public static final String FEEDBACK_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "feedback16.png";
    public static final String UNKNOWN_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "unknown16.png";

    public static final String OPTIONAL_ABSTRACT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "oabstract.png";
    public static final String OPTIONAL_USER_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "ouser.png";
    public static final String OPTIONAL_INTERACTIF_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "ointeraction.png";
    public static final String OPTIONAL_UNKNOWN_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "ounknown.png";

    public static final String INTERRUPTIBLE_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "interruptible.png";

    public static final String NEW_PROJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "newproject.gif";
    public static final String OPEN_PROJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "open.gif";
    public static final String NEW_PROJECT_BIG_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "filenew.png";
    public static final String OPEN_PROJECT_BIG_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "folder.png";
    public static final String CLOSE_PROJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "close.gif";
    public static final String SAVE_PROJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "save.gif";
    public static final String ZOOM_IN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "zoomin.gif";
    public static final String ZOOM_OUT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "zoomout.gif";
    public static final String ZOOM_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "zoom.gif";
    public static final String ZOOM_GLOBAL_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "zoomglobal.png";
    public static final String PRINT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "print.gif";
    public static final String PRINT_PREVIEW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "printpreview.gif";
    public static final String LANDSCAPE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "landscape.png";
    public static final String PORTRAIT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "portrait.png";
    public static final String PRINT_LAYOUT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "printlayout.gif";
    public static final String COPY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "copy.gif";
    public static final String CUT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "cut.gif";
    public static final String DELETE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "delete.gif";

    public static final String PASTE_IMAGE_32 = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "paste_32.gif";

    public static final String PASTE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "paste.gif";
    public static final String UNDO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "undo.gif";
    public static final String REDO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "redo.gif";
    public static final String MORE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "more.gif";
    public static final String OPTIONS_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "options.png";
    public static final String MAGNETISM_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "magn.png";
    public static final String JUSTIFY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "justify.png";
    public static final String UNIFORM_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uniform.png";
    public static final String CLIPBOARD_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "clipboard.png";
    public static final String PROJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "project.png";
    public static final String ENTITIES_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "entities.png";
    public static final String LOCK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "lock.png";
    public static final String UNLOCK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "unlock.png";
    public static final String LABEL_VISIBLE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "visiblelabel.png";
    public static final String LABEL_COLOR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "colorlabel.png";
    /** IMAGE Evenement des Taches **/
    public static final String EVENEMENT_IN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "evenement_in.png";
    public static final String EVENEMENT_OUT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "evenement_out.png";
    public static final String EVENEMENT_IN_IMAGE_DISABLE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "evenement_in_disable.png";
    public static final String EVENEMENT_OUT_IMAGE_DISABLE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "evenement_out_disable.png";

    public static final String INRIA_LOGO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "inria.gif";
    public static final String MERLIN_LOGO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "logoMerlin.gif";
    public static final String LIAS_LOGO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "lias.png";
    public static final String BARON_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "baron.gif";
    public static final String SCAPIN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "scapin.gif";

    public static final String UP_ARROW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uparrow.png";
    public static final String DOWN_ARROW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "downarrow.png";
    public static final String LEFT_ARROW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "leftarrow.png";
    public static final String RIGHT_ARROW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "rightarrow.png";

    public static final String UP_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uptask.png";
    public static final String DOWN_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "downtask.png";
    public static final String LEFT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "lefttask.png";
    public static final String RIGHT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "righttask.png";
    public static final String NO_UP_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "nouptask.png";
    public static final String NO_DOWN_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "nodowntask.png";
    public static final String NO_LEFT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "nolefttask.png";
    public static final String NO_RIGHT_TASK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "norighttask.png";

    public static final String CLEAR_HISTORY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "clearhistory.png";
    public static final String SAVE_HISTORY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "savehistory.png";
    public static final String USER_UNKNOWN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uuser.png";
    public static final String USER_ERROR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "euser.png";

    public static final String INDIVIDU_ERROR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "euser.png";
    public static final String INDIVIDU_UNKNOWN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uuser.png";

    public static final String ORGANIZATION_ERROR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "euser.png";
    public static final String ORGANIZATION_UNKNOWN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uuser.png";

    public static final String MACHINE_ERROR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "euser.png";
    public static final String MACHINE_UNKNOWN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uuser.png";

    public static final String PARCMACHINES_ERROR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "euser.png";
    public static final String PARCMACHINES_UNKNOWN_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "uuser.png";

    public static final String WARNING_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "warning.gif";
    public static final String ERROR_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "error.gif";

    public static final String ONE_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "1.gif";
    public static final String TWO_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "2.gif";
    public static final String THREE_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "3.gif";
    public static final String FOUR_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "4.gif";
    public static final String FIVE_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "5.gif";
    public static final String SIX_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "6.gif";
    public static final String SEVEN_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "7.gif";
    public static final String HEIGH_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "8.gif";
    public static final String NINE_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "9.gif";
    public static final String TEN_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "10.gif";
    public static final String ELEVEN_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "11.gif";
    public static final String TWELVE_STEP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "12.gif";

    public static final String PLAYER_PLAY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "playerplay.png";
    public static final String PLAYER_STOP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "playerstop.png";
    public static final String PLAYER_PAUSE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "playerpause.png";
    public static final String PLAYER_REW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "playerrew.png";
    public static final String PLAYER_FWD_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "playerfwd.png";
    public static final String SAVEAS_SCENARIO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "saveasscenario.png";
    public static final String GO_SIMULATION_SCENARIO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "gosimulation.png";
    public static final String PLAYER_ACTION_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "playeraction.png";

    public static final String PLAY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "player_play.png";
    public static final String PAUSE_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "player_pause.png";
    public static final String PLAY_MARK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "player_markplay.png";
    public static final String PAUSE_MARK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "player_markpause.png";
    public static final String START_MARK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "start.png";
    public static final String FINISH_MARK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "finish.png";
    public static final String START_MARK_SLIDER_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "startslider.png";
    public static final String FINISH_MARK_SLIDER_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "finishslider.png";
    public static final String INIT_START_MARK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "initstartslider.png";
    public static final String INIT_FINISH_MARK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "initfinishslider.png";

    public static final String CURSEUR_IMAGE_GRAB = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "hmove.gif";

    //
    // Les constantes li�es aux templates de Velocity
    //
    public static final String DEFAULT_CARD_VELOCITY_HTML = KMADEToolConstant.VELOCITY_TEMPLATE_DIRECTORY_NAME
	    + "default.vm";
    public static final String BASIC_CARD_VELOCITY_HTML = KMADEToolConstant.VELOCITY_TEMPLATE_DIRECTORY_NAME
	    + "basic.vm";

    //
    // Les constantes li�es aux autres resources
    //
    public static String DEBUG_KMADE_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
	    + "kmadedebug_fr.html";
    public static String HELP_KMADE_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
	    + "kmadehelp_fr.html";
    public static String HELP_KMAD_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
	    + "kmadhelp_fr.html";
    public static String HISTORY_KMADE_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
	    + "kmadehistory_fr.html";
    public static String UNDER_DEVELOPMENT_START_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
	    + "kmadeudstart_fr.html";
    public static String UNDER_DEVELOPMENT_START_TITLE = "KMADe is under development ...";

    // Les erreurs d'ouverture KXML
    public static String NO_VERSION = "Aucune version pr�cis�e version n'est pr�cis�e dans le fichier charg�";
    public static String NO_COMPATIBLE_ITEMS_DOCUMENT = "Ce fichier ne corresponf pas a un fichier d'objets KMADe";
    public static String NEW_SAVE_NEW_FORMAT = "Attention toute nouvelle sauvegarde se fera au nouveau format";
    public static String VERSION_USE = "Vous utilisez la version : ";
    public static String VERSION_FILE = "Vous chargez un fichier avec la version : ";
    public static String NOT_SAME_VERSION = "La version que vous essayez de charger n'est pas la m�me que celle de l'outil";

    /**
     * @author Joachim TROUVERIE
     */
    // Fenetre objets

    public static Color ACTIVE_OBJECT = new Color(253, 217, 38);
    public static Color INACTIVE_OBJECT = Color.LIGHT_GRAY;
    public static String OBJECTS_VISUALIZATION_WINDOW_NAME = "Objets KMADe";
    public static String OPEN_ITEMS_FILE = "Charger un fichier d'objets";
    public static String CAN_SAVE_PROJECT = "Vous pouvez sauver le projet ou les objets en cours";
    public static String CAN_NOT_SAVE_PROJECT = "Vous ne pouvez pas sauver le projet ou les objets en cours";

    // Abstract objects

    public static String EMPTY_CELL_NAME = "VIDE";
    public static String NEW_ABSTRACT_OBJECT_TEXT = "Creer un nouvel objet abstrait";
    public static String REMOVE_ABSTRACT_OBJECT_TEXT = "Supprimer l'objet abstrait s�lectionn�";
    public static String NEW_OBJECT_NAME = "Nouvel objet";
    public static String NO_SELECTED_OBJECT = "Pas d'objet s�lectionn�";
    public static String GENERALITY = "G�n�ralit�s";
    public static String ATTRIBUTES_NAME = "Attributs";
    public static String ADD_ATTRIBUTE = "Ajouter un nouvel attribut";
    public static String REMOVE_ATTRIBUTE = "Supprimer un attribut";

    // Concrete objects
    public static String EDIT_CONCRETE_OBJECT_ACTION_MESSAGE = "Editer l'objet concret ";
    public static String EDIT_OBJECTS_ACTION_MESSAGE = "Ouvrir la fen�tre d'�dition des objets";
    public static String OBJECT_INSERTION_ERROR = "Le groupe n'est pas concordant avec l'objet abstrait duquel est instanci� l'objet";
    public static String NEW_GROUP_NAME = "Nouveau groupe";
    public static String NEW_STACK_ACTION_MESSAGE = "Ajouter une nouvelle pile";
    public static String NEW_SET_ACTION_MESSAGE = "Ajouter un nouvel ensemble";
    public static String NEW_ARRAY_ACTION_MESSAGE = "Ajouter un nouveau tableau";
    public static String NEW_LIST_ACTION_MESSAGE = "Ajouter une nouvelle liste";
    public static String NEW_SINGLETON_ACTION_MESSAGE = "Ajouter un nouveau singleton";
    public static String EDIT_GROUP_ACTION_MESSAGE = "Editer le groupe";
    public static String EDIT_GROUP_AND_CONCRETE_ACTION_MESSAGE = "Editer le groupe ou l'objet concret";
    public static String DELETE_GROUP_AND_CONCRETE_ACTION_MESSAGE = "Supprimer le groupe ou l'objet concret";

    // Images
    public static String OBJECT_MANAGEMENT = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "manage_objects_16x16.png";
    public static String GROUP_LIST_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "list_16x16.png";
    public static String GROUP_STACK_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "stack_16x16.png";
    public static String GROUP_SET_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "set_16x16.png";
    public static String GROUP_ARRAY_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "array_16x16.png";
    public static String GROUP_SINGLETON_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "singleton_16x16.png";
    public static String ADD_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "add_16x16.png";
    public static String ADD_OBJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "add_object_16x16.png";
    public static String REMOVE_OBJECT_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "remove_object_16x16.png";
    public static String CANCEL_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "cancel_16x16.png";
    public static String EDIT_GROUP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "edit_16x16.png";
    public static String DELETE_GROUP_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "delete_group_16x16.png";
    public static String HELP_OBJECT_VIEW_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "help_16x16.png";

    // outil de prototypage
    public static String PROTOTYPING_TOOL_TITLE_NAME = "ProtoTask";
    public static String PROTOTYPING_TOOL_RESET = "Recommencer";
    public static String PROTOTYPING_TOOL_CURRENT_TASK = "Tâche en cours";
    public static String PROTOTYPING_TOOL_NAME_TITLE = "Nom";
    public static String PROTOTYPING_TOOL_NAME_TOOLTIP = "Le nom de la tâche";
    public static String PROTOTYPING_TOOL_EXECUTANT_ABS = "La tâche se décompose en plusieurs sous tâches";
    public static String PROTOTYPING_TOOL_EXECUTANT_INCONNU = "La tâche est réalisée par \"inconnu\"";
    public static String PROTOTYPING_TOOL_EXECUTANT_INT = "La tâche est réalisée par l'utilisateur sur le système";
    public static String PROTOTYPING_TOOL_EXECUTANT_SYS = "La tâche est réalisée par le système";
    public static String PROTOTYPING_TOOL_EXECUTANT_USER = "La tâche est réalisée par l'utilisateur, indépendamment du système";
    public static String PROTOTYPING_TOOL_EXECUTANT_TOOLTIP = "L'exécutant de la tâche";
    public static String PROTOTYPING_TOOL_DECOMPOSITION_TITLE = "Décomposition de la tâche";
    public static String PROTOTYPING_TOOL_PRECONDITION_TITLE = "Prérequis d'éxécution";
    public static String PROTOTYPING_TOOL_PRECONDITION_TOOLTIP = "Condition pour l'éxécution de la tâche";
    public static String PROTOTYPING_TOOL_ITERATION_TITLE = "Prérequis de répétition";
    public static String PROTOTYPING_TOOL_ITERATION_TOOLTIP = "Condition pour la répétition de la tâche";
    public static String PROTOTYPING_TOOL_DESCRIPTION_TITLE = "Description de la tâche";
    public static String PROTOTYPING_TOOL_DESCRIPTION_TOOLTIP = "Description textuelle de la tâche";
    public static String PROTOTYPING_TOOL_SUBTASK_DECOMP = "(...)";
    public static String PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP1 = "Cette sous-tâche se décompose en";
    public static String PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP2 = "tâche(s)";
    public static String PROTOTYPING_TOOL_SUBTASK_TERMINAL_TOOLTIP = "Cette sous-tâche ne se décompose pas";
    public static String PROTOTYPING_TOOL_SUBTASK_DESCRIPTION_TOOLTIP = "Description";
    public static String PROTOTYPING_TOOL_SUBTASK_PRECONDITION = "Prérequis";
    public static String PROTOTYPING_TOOL_CANCEL_BUTTON = "Annuler";
    public static String PROTOTYPING_TOOL_END_BUTTON = "Terminer";
    public static String PROTOTYPING_TOOL_VALIDATE_END_BUTTON = "Valider et Terminer";
    public static String PROTOTYPING_TOOL_CONTROL_TITLE = "Contrôle";
    public static String PROTOTYPING_TOOL_ITERATION_BUTTON = "Faire de nouveau";
    public static String PROTOTYPING_TOOL_MENU_TITLE = "ProtoTask";
    public static String PROTOTYPING_TOOL_MENU_TOOLTIP = "ProtoTask";
    public static String PROTOTYPING_TOOL_PRECONDITION_TEXT_PANE = "Prérequis";
    public static String PROTOTYPING_TOOL_PRECONDITION_TITLE_PANE = "Vérification du prérequis";
    public static String PROTOTYPING_TOOL_PASSIVE_INACTIVE_ERROR = "Erreur : la tâche en cours n'a pas le bon état";
    public static String PROTOTYPING_TOOL_END_ERROR = "Impossible de terminer la tâche";
    public static String PROTOTYPING_TOOL_TRUE = "Vrai";
    public static String PROTOTYPING_TOOL_FALSE = "Faux";
    public static String PROTOTYPING_TOOL_INDETERMINATE = "Indeterminée";
    public static String PROTOTYPING_TOOL_HISTORY = "Historique";
    public static String PROTOTYPING_TOOL_HISTORY_DESCRIPTION = "Description";
    public static String PROTOTYPING_TOOL_DURATION = "Durée";

    public static String PRECONDITION_TAB_FORMAL = "Précondition formelle";
    public static String PRECONDITION_TAB_PROTOTASK = "Précondition ProtoTask";
    public static String PROTOTASK_CONDITION_DESCRIPTION = "Description";
    public static String PROTOTASK_CONDITION_DEFAULT_VALUE = "valeur par default";
    public static String CONDITION_WORLD_TITLE = "condition disponible";
    public static String CONDITION_ADD_MESSAGE = "Ajouter une condition";
    public static String CONDITION_REMOVE_NAME_TITLE = "Supprimer la condition";

    public static String PROTOTASK_CONDITION_MENU = "Condition(s)";
    public static String PROTOTASK_CONDITION_MENU_ITEM1 = "Afficher";
    public static String PROTOTASK_HISTORIQUE_MENU = "Historique";
    public static String PROTOTASK_HISTORIQUE_MENU_ITEM1 = "Sauvegarder";
    public static String PROTOTASK_HISTORIQUE_MENU_ITEM2 = "Afficher";
    public static String PROTOTASK_CONDITION_FRAME_TITLE = "Condition(s)";
    public static String PROTOTASK_HISTORIQUE_FRAME_TITLE = "Historique";
    public static String PROTOTASK_END_TITLE = "Scenario terminé";
    public static String PROTOTASK_END_LABEL = "Scenario terminé !";
    public static String PROTOTASK_CONDITION_LABEL = "Condition(s):";
    public static String PROTOTASK_TASK_LABEL = "Tâche(s):";
    public static String PROTOTASK_REPEAT_BUTTON = "Refaire";
    public static String PROTOTASK_VALIDATE_BUTTON = "Valider";
    public static String PROTOTASK_TOOL_MENU_TITLE = "ProtoTask (V2 Beta)";
    public static String PROTOTASK_TOOL_MENU_TOOLTIP = "ProtoTask (V2 Beta)";

    public static void loadMessagesFromBundle() {
	DEBUG_KMADE_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
		+ KMADeMain.messages.getString("DEBUG_KMADE_HTML");
	HELP_KMADE_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
		+ KMADeMain.messages.getString("HELP_KMADE_HTML");
	HELP_KMAD_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
		+ KMADeMain.messages.getString("HELP_KMAD_HTML");
	HISTORY_KMADE_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
		+ KMADeMain.messages.getString("HISTORY_KMADE_HTML");
	UNDER_DEVELOPMENT_START_HTML = KMADEToolConstant.HTML_DIRECTORY_NAME
		+ KMADeMain.messages.getString("UNDER_DEVELOPMENT_START_HTML");
	UNDER_DEVELOPMENT_START_TITLE = KMADeMain.messages
		.getString("UNDER_DEVELOPMENT_START_TITLE");

	VERTEX_NUM = KMADeMain.messages.getString("VERTEX_NUM");
	VERTEX_PRECONDITION = KMADeMain.messages
		.getString("VERTEX_PRECONDITION");
	VERTEX_ITERATION = KMADeMain.messages.getString("VERTEX_ITERATION");
	VERTEX_OPT = KMADeMain.messages.getString("VERTEX_OPT");
	VERTEX_INTERRUPTIBLE = KMADeMain.messages
		.getString("VERTEX_INTERRUPTIBLE");
	VERTEX_ACTOR = KMADeMain.messages.getString("VERTEX_ACTOR");
	VERTEX_EFFETSDEBORD = KMADeMain.messages
		.getString("VERTEX_EFFETSDEBORD");
	VERTEX_DESCRIPTION = KMADeMain.messages.getString("VERTEX_DESCRIPTION");
	VERTEX_ITER_LETTER = KMADeMain.messages.getString("VERTEX_ITER_LETTER");
	VERTEX_STATE_LETTER = KMADeMain.messages
		.getString("VERTEX_STATE_LETTER");
	VERTEX_EVENT_IN_LETTER = KMADeMain.messages
		.getString("VERTEX_EVENT_IN_LETTER");
	VERTEX_EVENT_OUT_LETTER = KMADeMain.messages
		.getString("VERTEX_EVENT_OUT_LETTER");

	FENETRE_PRINCIPALE_TITLE_NAME = KMADeMain.messages
		.getString("FENETRE_PRINCIPALE_TITLE_NAME");
	ABOUT_TITLE_NAME = KMADeMain.messages.getString("ABOUT_TITLE_NAME")
		+ " " + KMADEToolConstant.TOOL_NAME;
	TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME");
	ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME");
	USER_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("USER_TABBEDPANE_TITLE_NAME");
	INDIVIDU_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("INDIVIDU_TABBEDPANE_TITLE_NAME");
	ORGANIZATION_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("ORGANIZATION_TABBEDPANE_TITLE_NAME");
	MACHINE_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("MACHINE_TABBEDPANE_TITLE_NAME");
	PARCMACHINES_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("PARCMACHINES_TABBEDPANE_TITLE_NAME");
	CONDITION_TABBEDPANE_TITLE_MESSAGE = KMADeMain.messages
		.getString("CONDITION_TABBEDPANE_TITLE_MESSAGE");

	EVENT_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("EVENT_TABBEDPANE_TITLE_NAME");
	CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME");
	PREVIEW_WINDOW_TITLE_NAME = KMADeMain.messages
		.getString("PREVIEW_WINDOW_TITLE_NAME");
	INFO_DEBUG_TITLE_NAME = KMADeMain.messages
		.getString("INFO_DEBUG_TITLE_NAME");
	HELP_KMADE_TITLE_NAME = KMADeMain.messages
		.getString("HELP_KMADE_TITLE_NAME");
	HELP_KMAD_TITLE_NAME = KMADeMain.messages
		.getString("HELP_KMAD_TITLE_NAME");
	HISTORY_TITLE_NAME = KMADeMain.messages.getString("HISTORY_TITLE_NAME");
	ABSTRACT_OBJECT_TITLE_NAME = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_TITLE_NAME");
	CONCRETE_OBJECT_TITLE_NAME = KMADeMain.messages
		.getString("CONCRETE_OBJECT_TITLE_NAME");
	USER_TITLE_NAME = KMADeMain.messages.getString("USER_TITLE_NAME");
	INDIVIDU_TITLE_NAME = KMADeMain.messages
		.getString("INDIVIDU_TITLE_NAME");
	ORGANIZATION_TITLE_NAME = KMADeMain.messages
		.getString("ORGANIZATION_TITLE_NAME");
	MACHINE_TITLE_NAME = KMADeMain.messages.getString("MACHINE_TITLE_NAME");
	PARCMACHINES_TITLE_NAME = KMADeMain.messages
		.getString("PARCMACHINES_TITLE_NAME");

	EVENT_TITLE_NAME = KMADeMain.messages.getString("EVENT_TITLE_NAME");
	TASK_MODEL_TITLE_NAME = KMADeMain.messages
		.getString("TASK_MODEL_TITLE_NAME");
	PREFERENCES_TITLE_NAME = KMADeMain.messages
		.getString("PREFERENCES_TITLE_NAME");
	COMPLETE_EDITOR_TITLE_NAME = KMADeMain.messages
		.getString("COMPLETE_EDITOR_TITLE_NAME");
	CLIPBOARD_TITLE_NAME = KMADeMain.messages
		.getString("CLIPBOARD_TITLE_NAME");
	LOAD_MONITOR_TITLE_NAME = KMADeMain.messages
		.getString("LOAD_MONITOR_TITLE_NAME");
	SAVE_MONITOR_TITLE_NAME = KMADeMain.messages
		.getString("SAVE_MONITOR_TITLE_NAME");
	EDITOR_TEXT_TITLE_NAME = KMADeMain.messages
		.getString("EDITOR_TEXT_TITLE_NAME");
	EVENT_TASK_LINKED_TITLE_NAME = KMADeMain.messages
		.getString("EVENT_TASK_LINKED_TITLE_NAME");
	ACTOR_CREATE_TITLE_NAME = KMADeMain.messages
		.getString("ACTOR_CREATE_TITLE_NAME");
	ACTORSYSTEM_CREATE_TITLE_NAME = KMADeMain.messages
		.getString("ACTORSYSTEM_CREATE_TITLE_NAME");
	PRINT_PREVIEW_WINDOW_TITLE_NAME = KMADeMain.messages
		.getString("PRINT_PREVIEW_WINDOW_TITLE_NAME");
	PROJECT_DIALOG_TITLE_NAME = KMADeMain.messages
		.getString("PROJECT_DIALOG_TITLE_NAME");

	BAD_CHARACTER_TEXT = KMADeMain.messages.getString("BAD_CHARACTER_TEXT");
	BAD_CARACTER_TITLE = KMADeMain.messages.getString("BAD_CARACTER_TITLE");
	SAME_NAME_TEXT = KMADeMain.messages.getString("SAME_NAME_TEXT");
	SAME_NAME_TITLE = KMADeMain.messages.getString("SAME_NAME_TITLE");

	// Abstract Object
	BACK_TO_EDITOR = KMADeMain.messages.getString("BACK_TO_EDITOR");
	ABSTRACT_OBJECT_NAME_TABLE = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_NAME_TABLE");
	ABSTRACT_OBJECT_OBSERVATION_TABLE = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_OBSERVATION_TABLE");
	ABSTRACT_OBJECT_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_NEW_OBJECT_TABLE");
	ABSTRACT_OBJECT_TITLE_TABLE = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_TITLE_TABLE");
	ABSTRACT_OBJECT_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_REMOVE_MESSAGE_TITLE");
	ABSTRACT_OBJECT_LIST_MESSAGE_TITLE = KMADeMain.messages
		.getString("ABSTRACT_OBJECT_LIST_MESSAGE_TITLE");

	// Groupe Abstrait
	ABSTRACT_GROUP_NAME_TABLE = KMADeMain.messages
		.getString("ABSTRACT_GROUP_NAME_TABLE");
	ABSTRACT_GROUP_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("ABSTRACT_GROUP_DESCRIPTION_TABLE");
	ABSTRACT_GROUP_SET_TYPE_TABLE = KMADeMain.messages
		.getString("ABSTRACT_GROUP_SET_TYPE_TABLE");
	ABSTRACT_GROUP_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("ABSTRACT_GROUP_NEW_OBJECT_TABLE");
	ABSTRACT_GROUP_TITLE_TABLE = KMADeMain.messages
		.getString("ABSTRACT_GROUP_TITLE_TABLE");
	ABSTRACT_GROUP_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("ABSTRACT_GROUP_REMOVE_MESSAGE_TITLE");

	// Attribut Abstrait
	ABSTRACT_ATTRIBUT_NAME_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_NAME_TABLE");
	ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE");
	ABSTRACT_ATTRIBUT_TYPE_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_TYPE_TABLE");
	ABSTRACT_ATTRIBUT_TYPE_NAME_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_TYPE_NAME_TABLE");
	ABSTRACT_ATTRIBUT_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_NEW_OBJECT_TABLE");
	ABSTRACT_ATTRIBUT_TITLE_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_TITLE_TABLE");
	ABSTRACT_ATTRIBUT_ENUMERATE_EDIT_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_ENUMERATE_EDIT_TABLE");
	ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE");
	ABSTRACT_ATTRIBUT_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE");

	// Enumeration
	ENUMERATION_NAME_TABLE = KMADeMain.messages
		.getString("ENUMERATION_NAME_TABLE");
	ENUMERATION_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("ENUMERATION_DESCRIPTION_TABLE");
	ENUMERATION_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("ENUMERATION_NEW_OBJECT_TABLE");
	ENUMERATION_TITLE_TABLE = KMADeMain.messages
		.getString("ENUMERATION_TITLE_TABLE");
	ENUMERATION_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("ENUMERATION_REMOVE_MESSAGE_TITLE");

	// Element
	ELEMENT_NAME_TABLE = KMADeMain.messages.getString("ELEMENT_NAME_TABLE");
	ELEMENT_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("ELEMENT_NEW_OBJECT_TABLE");
	ELEMENT_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("ELEMENT_REMOVE_MESSAGE_TITLE");

	// Intervalle
	INTERVALLE_TITLE_TABLE = KMADeMain.messages
		.getString("INTERVALLE_TITLE_TABLE");
	INTERVALLE_NAME_TABLE = KMADeMain.messages
		.getString("INTERVALLE_NAME_TABLE");
	INTERVALLE_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("INTERVALLE_DESCRIPTION_TABLE");
	INTERVALLE_MIN_VALUE_TABLE = KMADeMain.messages
		.getString("INTERVALLE_MIN_VALUE_TABLE");
	INTERVALLE_MAX_VALUE_TABLE = KMADeMain.messages
		.getString("INTERVALLE_MAX_VALUE_TABLE");
	INTERVALLE_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("INTERVALLE_NEW_OBJECT_TABLE");
	INTERVALLE_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("INTERVALLE_REMOVE_MESSAGE_TITLE");

	// Objet Concret
	CONCRETE_ABSTRACT_OBJECT_TITLE_TABLE = KMADeMain.messages
		.getString("CONCRETE_ABSTRACT_OBJECT_TITLE_TABLE");
	CONCRETE_OBJECT_TITLE_TABLE = KMADeMain.messages
		.getString("CONCRETE_OBJECT_TITLE_TABLE");
	CONCRETE_OBJECT_NAME_TABLE = KMADeMain.messages
		.getString("CONCRETE_OBJECT_NAME_TABLE");
	CONCRETE_OBJECT_OBSERVATION_TABLE = KMADeMain.messages
		.getString("CONCRETE_OBJECT_OBSERVATION_TABLE");
	CONCRETE_OBJECT_GROUPE_TABLE = KMADeMain.messages
		.getString("CONCRETE_OBJECT_GROUPE_TABLE");
	CONCRETE_OBJECT_GROUPE_NAME = KMADeMain.messages
		.getString("CONCRETE_OBJECT_GROUPE_NAME");
	NO_CONCRETE_OBJECT_GROUPE_NAME = KMADeMain.messages
		.getString("NO_CONCRETE_OBJECT_GROUPE_NAME");
	CONCRETE_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("CONCRETE_NEW_OBJECT_TABLE");
	CONCRETE_NONEW_ROW_TABLE = KMADeMain.messages
		.getString("CONCRETE_NONEW_ROW_TABLE");
	CONCRETE_OBJECT_REMOVE_MESSAGE_TITLE = KMADeMain.messages
		.getString("CONCRETE_OBJECT_REMOVE_MESSAGE_TITLE");

	// Attribut Concret
	CONCRETE_ATTRIBUT_NAME_TABLE = KMADeMain.messages
		.getString("CONCRETE_ATTRIBUT_NAME_TABLE");
	CONCRETE_ATTRIBUT_VALUE_TABLE = KMADeMain.messages
		.getString("CONCRETE_ATTRIBUT_VALUE_TABLE");
	CONCRETE_ATTRIBUT_NOINIT_TABLE = KMADeMain.messages
		.getString("CONCRETE_ATTRIBUT_NOINIT_TABLE");
	CONCRETE_ATTRIBUT_TITLE_TABLE = KMADeMain.messages
		.getString("CONCRETE_ATTRIBUT_TITLE_TABLE");

	// Evenement
	EVENT_NAME_TABLE = KMADeMain.messages.getString("EVENT_NAME_TABLE");
	EVENT_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("EVENT_DESCRIPTION_TABLE");
	EVENT_NEW_ROW_TABLE = KMADeMain.messages
		.getString("EVENT_NEW_ROW_TABLE");
	EVENT_NONEW_ROW_TABLE = KMADeMain.messages
		.getString("EVENT_NONEW_ROW_TABLE");
	EVENT_REMOVE_INTO_TASK = KMADeMain.messages
		.getString("EVENT_REMOVE_INTO_TASK");
	EVENT_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("EVENT_REMOVE_NAME_TITLE");
	EVENT_NO_ELEMENT_INTO_TASK = KMADeMain.messages
		.getString("EVENT_NO_ELEMENT_INTO_TASK");

	// Utilisateur
	USER_NAME_TABLE = KMADeMain.messages.getString("USER_NAME_TABLE");
	USER_STATUS_TABLE = KMADeMain.messages.getString("USER_STATUS_TABLE");
	USER_ROLE_TABLE = KMADeMain.messages.getString("USER_ROLE_TABLE");
	USER_PHOTO_TABLE = KMADeMain.messages.getString("USER_PHOTO_TABLE");
	USER_MEMBERS = KMADeMain.messages.getString("USER_MEMBERS");
	USER_NEW_ROW_TABLE = KMADeMain.messages.getString("USER_NEW_ROW_TABLE");
	USER_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("USER_REMOVE_NAME_TITLE");
	USER_CHOOSE_IMAGE_MESSAGE = KMADeMain.messages
		.getString("USER_CHOOSE_IMAGE_MESSAGE");
	USER_DISPLAY_IMAGE_MESSAGE = KMADeMain.messages
		.getString("USER_DISPLAY_IMAGE_MESSAGE");
	USER_DISPLAY_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("USER_DISPLAY_PATH_IMAGE_MESSAGE");
	USER_DISPLAY_DEFAULT_IMAGE_MESSAGE = KMADeMain.messages
		.getString("USER_DISPLAY_DEFAULT_IMAGE_MESSAGE");
	USER_DISPLAY_BAD_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("USER_DISPLAY_BAD_PATH_IMAGE_MESSAGE");
	USER_CLEAR_IMAGE_MESSAGE = KMADeMain.messages
		.getString("USER_CLEAR_IMAGE_MESSAGE");

	// Individu
	INDIVIDU_NAME_TABLE = KMADeMain.messages
		.getString("INDIVIDU_NAME_TABLE");
	INDIVIDU_STATUS_TABLE = KMADeMain.messages
		.getString("INDIVIDU_STATUS_TABLE");
	INDIVIDU_ROLE_TABLE = KMADeMain.messages
		.getString("INDIVIDU_ROLE_TABLE");
	INDIVIDU_PHOTO_TABLE = KMADeMain.messages
		.getString("INDIVIDU_PHOTO_TABLE");
	INDIVIDU_NEW_ROW_TABLE = KMADeMain.messages
		.getString("INDIVIDU_NEW_ROW_TABLE");
	INDIVIDU_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("INDIVIDU_REMOVE_NAME_TITLE");
	INDIVIDU_CHOOSE_IMAGE_MESSAGE = KMADeMain.messages
		.getString("INDIVIDU_CHOOSE_IMAGE_MESSAGE");
	INDIVIDU_DISPLAY_IMAGE_MESSAGE = KMADeMain.messages
		.getString("INDIVIDU_DISPLAY_IMAGE_MESSAGE");
	INDIVIDU_DISPLAY_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("INDIVIDU_DISPLAY_PATH_IMAGE_MESSAGE");
	INDIVIDU_DISPLAY_DEFAULT_IMAGE_MESSAGE = KMADeMain.messages
		.getString("INDIVIDU_DISPLAY_DEFAULT_IMAGE_MESSAGE");
	INDIVIDU_DISPLAY_BAD_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("INDIVIDU_DISPLAY_BAD_PATH_IMAGE_MESSAGE");
	INDIVIDU_CLEAR_IMAGE_MESSAGE = KMADeMain.messages
		.getString("INDIVIDU_CLEAR_IMAGE_MESSAGE");
	INDIVIDU_ORGANIZATION_TABLE = KMADeMain.messages
		.getString("INDIVIDU_ORGANIZATION_TABLE");
	INDIVIDU_OTHERS = KMADeMain.messages.getString("INDIVIDU_OTHERS");
	// Organisation

	ORGANIZATION_NAME_TABLE = KMADeMain.messages
		.getString("ORGANIZATION_NAME_TABLE");
	ORGANIZATION_STATUS_TABLE = KMADeMain.messages
		.getString("ORGANIZATION_STATUS_TABLE");
	ORGANIZATION_ROLE_TABLE = KMADeMain.messages
		.getString("ORGANIZATION_ROLE_TABLE");
	ORGANIZATION_PHOTO_TABLE = KMADeMain.messages
		.getString("ORGANIZATION_PHOTO_TABLE");
	ORGANIZATION_NEW_ROW_TABLE = KMADeMain.messages
		.getString("ORGANIZATION_NEW_ROW_TABLE");
	ORGANIZATION_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("ORGANIZATION_REMOVE_NAME_TITLE");
	ORGANIZATION_CHOOSE_IMAGE_MESSAGE = KMADeMain.messages
		.getString("ORGANIZATION_CHOOSE_IMAGE_MESSAGE");
	ORGANIZATION_DISPLAY_IMAGE_MESSAGE = KMADeMain.messages
		.getString("ORGANIZATION_DISPLAY_IMAGE_MESSAGE");
	ORGANIZATION_DISPLAY_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("ORGANIZATION_DISPLAY_PATH_IMAGE_MESSAGE");
	ORGANIZATION_DISPLAY_DEFAULT_IMAGE_MESSAGE = KMADeMain.messages
		.getString("ORGANIZATION_DISPLAY_DEFAULT_IMAGE_MESSAGE");
	ORGANIZATION_DISPLAY_BAD_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("ORGANIZATION_DISPLAY_BAD_PATH_IMAGE_MESSAGE");
	ORGANIZATION_CLEAR_IMAGE_MESSAGE = KMADeMain.messages
		.getString("ORGANIZATION_CLEAR_IMAGE_MESSAGE");
	ORGANIZATION_MEMBERS_TABLE = KMADeMain.messages
		.getString("ORGANIZATION_MEMBERS_TABLE");
	ORGANIZATION_OTHERS = KMADeMain.messages
		.getString("ORGANIZATION_OTHERS");

	// Machine

	MACHINE_NAME_TABLE = KMADeMain.messages.getString("MACHINE_NAME_TABLE");
	MACHINE_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("MACHINE_DESCRIPTION_TABLE");
	MACHINE_ISCOMPUTER_TABLE = KMADeMain.messages
		.getString("MACHINE_ISCOMPUTER_TABLE");
	MACHINE_IMAGE_TABLE = KMADeMain.messages
		.getString("MACHINE_IMAGE_TABLE");
	MACHINE_NEW_ROW_TABLE = KMADeMain.messages
		.getString("MACHINE_NEW_ROW_TABLE");
	MACHINE_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("MACHINE_REMOVE_NAME_TITLE");
	MACHINE_CHOOSE_IMAGE_MESSAGE = KMADeMain.messages
		.getString("MACHINE_CHOOSE_IMAGE_MESSAGE");
	MACHINE_DISPLAY_IMAGE_MESSAGE = KMADeMain.messages
		.getString("MACHINE_DISPLAY_IMAGE_MESSAGE");
	MACHINE_DISPLAY_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("MACHINE_DISPLAY_PATH_IMAGE_MESSAGE");
	MACHINE_DISPLAY_DEFAULT_IMAGE_MESSAGE = KMADeMain.messages
		.getString("MACHINE_DISPLAY_DEFAULT_IMAGE_MESSAGE");
	MACHINE_DISPLAY_BAD_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("MACHINE_DISPLAY_BAD_PATH_IMAGE_MESSAGE");
	MACHINE_CLEAR_IMAGE_MESSAGE = KMADeMain.messages
		.getString("MACHINE_CLEAR_IMAGE_MESSAGE");
	MACHINE_PARCS_TABLE = KMADeMain.messages
		.getString("MACHINE_PARCS_TABLE");
	MACHINE_OTHERS = KMADeMain.messages.getString("MACHINE_OTHERS");

	// ParcMachines

	PARCMACHINES_NAME_TABLE = KMADeMain.messages
		.getString("PARCMACHINES_NAME_TABLE");
	PARCMACHINES_DESCRIPTION_TABLE = KMADeMain.messages
		.getString("PARCMACHINES_DESCRIPTION_TABLE");
	PARCMACHINES_IMAGE_TABLE = KMADeMain.messages
		.getString("PARCMACHINES_IMAGE_TABLE");
	PARCMACHINES_NEW_ROW_TABLE = KMADeMain.messages
		.getString("PARCMACHINES_NEW_ROW_TABLE");
	PARCMACHINES_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("PARCMACHINES_REMOVE_NAME_TITLE");
	PARCMACHINES_CHOOSE_IMAGE_MESSAGE = KMADeMain.messages
		.getString("PARCMACHINES_CHOOSE_IMAGE_MESSAGE");
	PARCMACHINES_DISPLAY_IMAGE_MESSAGE = KMADeMain.messages
		.getString("PARCMACHINES_DISPLAY_IMAGE_MESSAGE");
	PARCMACHINES_DISPLAY_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("PARCMACHINES_DISPLAY_PATH_IMAGE_MESSAGE");
	PARCMACHINES_DISPLAY_DEFAULT_IMAGE_MESSAGE = KMADeMain.messages
		.getString("PARCMACHINES_DISPLAY_DEFAULT_IMAGE_MESSAGE");
	PARCMACHINES_DISPLAY_BAD_PATH_IMAGE_MESSAGE = KMADeMain.messages
		.getString("PARCMACHINES_DISPLAY_BAD_PATH_IMAGE_MESSAGE");
	PARCMACHINES_CLEAR_IMAGE_MESSAGE = KMADeMain.messages
		.getString("PARCMACHINES_CLEAR_IMAGE_MESSAGE");
	PARCMACHINES_MACHINE_TABLE = KMADeMain.messages
		.getString("PARCMACHINES_MACHINE_TABLE");
	PARCMACHINES_OTHERS = KMADeMain.messages
		.getString("PARCMACHINES_OTHERS");

	// Label
	LABEL_TITLE_MESSAGE = KMADeMain.messages
		.getString("LABEL_TITLE_MESSAGE");
	LABEL_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("LABEL_REMOVE_NAME_TITLE");
	LABEL_NUMERO_TITLE = KMADeMain.messages.getString("LABEL_NUMERO_TITLE");
	LABEL_NAME_TITLE = KMADeMain.messages.getString("LABEL_NAME_TITLE");
	LABEL_DESCRIPTION_TITLE = KMADeMain.messages
		.getString("LABEL_DESCRIPTION_TITLE");
	LABEL_COLOR_TITLE = KMADeMain.messages.getString("LABEL_COLOR_TITLE");
	LABEL_IS_VISIBLE_COLOR_TITLE = KMADeMain.messages
		.getString("LABEL_IS_VISIBLE_COLOR_TITLE");
	LABEL_IS_VISIBLE_TITLE = KMADeMain.messages
		.getString("LABEL_IS_VISIBLE_TITLE");
	LABEL_ADD_NEW_LABEL_TITLE = KMADeMain.messages
		.getString("LABEL_ADD_NEW_LABEL_TITLE");
	LABEL_EDITOR_TITLE = KMADeMain.messages.getString("LABEL_EDITOR_TITLE");

	// Acteur
	ACTOR_NAME_TABLE = KMADeMain.messages.getString("ACTOR_NAME_TABLE");
	ACTOR_EXPERIENCE_TABLE = KMADeMain.messages
		.getString("ACTOR_EXPERIENCE_TABLE");
	ACTOR_COMPETENCE_TABLE = KMADeMain.messages
		.getString("ACTOR_COMPETENCE_TABLE");
	ACTOR_NEW_ROW_TABLE = KMADeMain.messages
		.getString("ACTOR_NEW_ROW_TABLE");
	ACTOR_NONEW_ROW_TABLE = KMADeMain.messages
		.getString("ACTOR_NONEW_ROW_TABLE");
	ACTOR_NO_ELEMENT_INTO_TASK = KMADeMain.messages
		.getString("ACTOR_NO_ELEMENT_INTO_TASK");
	ACTOR_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("ACTOR_REMOVE_NAME_TITLE");
	ACTOR_REMOVE_ALL_NAME_TITLE = KMADeMain.messages
		.getString("ACTOR_REMOVE_ALL_NAME_TITLE");

	// Acteur Systeme
	ACTORSYSTEM_NAME_TABLE = KMADeMain.messages
		.getString("ACTORSYSTEM_NAME_TABLE");
	ACTORSYSTEM_EXPERIENCE_TABLE = KMADeMain.messages
		.getString("ACTORSYSTEM_EXPERIENCE_TABLE");
	ACTORSYSTEM_COMPETENCE_TABLE = KMADeMain.messages
		.getString("ACTORSYSTEM_COMPETENCE_TABLE");
	ACTORSYSTEM_NEW_ROW_TABLE = KMADeMain.messages
		.getString("ACTORSYSTEM_NEW_ROW_TABLE");
	ACTORSYSTEM_NONEW_ROW_TABLE = KMADeMain.messages
		.getString("ACTORSYSTEM_NONEW_ROW_TABLE");
	ACTORSYSTEM_NO_ELEMENT_INTO_TASK = KMADeMain.messages
		.getString("ACTORSYSTEM_NO_ELEMENT_INTO_TASK");
	ACTORSYSTEM_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("ACTORSYSTEM_REMOVE_NAME_TITLE");
	ACTORSYSTEM_REMOVE_ALL_NAME_TITLE = KMADeMain.messages
		.getString("ACTORSYSTEM_REMOVE_ALL_NAME_TITLE");

	// Propri�t�s des t�ches
	NO_SELECTED_TASK_MESSAGE = KMADeMain.messages
		.getString("NO_SELECTED_TASK_MESSAGE");
	PROPERTIES_MESSAGE = KMADeMain.messages.getString("PROPERTIES_MESSAGE");
	GENERAL_TABBEDPANE_TITLE_NAME = KMADeMain.messages
		.getString("GENERAL_TABBEDPANE_TITLE_NAME");
	NO_VALUE_LEGEND_MESSAGE = KMADeMain.messages
		.getString("NO_VALUE_LEGEND_MESSAGE");
	NO_CARACTERISTIC_SELECTED_MESSAGE = KMADeMain.messages
		.getString("NO_CARACTERISTIC_SELECTED_MESSAGE");
	ERROR_CARACTERISTIC_MESSAGE = KMADeMain.messages
		.getString("ERROR_CARACTERISTIC_MESSAGE");

	// Informations G�n�rales des propri�t�s d'une t�ches
	TASK_ATTRIBUT_NAME = KMADeMain.messages.getString("TASK_ATTRIBUT_NAME");
	GENERAL_INFORMATION_PANEL_TITLE_NAME = KMADeMain.messages
		.getString("GENERAL_INFORMATION_PANEL_TITLE_NAME");
	GENERAL_INFORMATION_PANEL_LEGEND_TITLE_NAME = KMADeMain.messages
		.getString("GENERAL_INFORMATION_PANEL_LEGEND_TITLE_NAME");
	TASK_NUMBER_NAME_TITLE = KMADeMain.messages
		.getString("TASK_NUMBER_NAME_TITLE");
	TASK_NUMBER_SHORT_NAME_TITLE = KMADeMain.messages
		.getString("TASK_NUMBER_SHORT_NAME_TITLE");
	TASK_NUMBER_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_NUMBER_NAME_LEGEND_TITLE");
	NO_MOTHER_TASK_NAME_MESSAGE = KMADeMain.messages
		.getString("NO_MOTHER_TASK_NAME_MESSAGE");
	SUB_TASK_PARENT_NAME_TITLE = KMADeMain.messages
		.getString("SUB_TASK_PARENT_NAME_TITLE");
	SUB_TASK_PARENT_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("SUB_TASK_PARENT_NAME_LEGEND_TITLE");
	TASK_NAME_TITLE = KMADeMain.messages.getString("TASK_NAME_TITLE");
	TASK_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_NAME_LEGEND_TITLE");
	TASK_PURPOSE_TITLE = KMADeMain.messages.getString("TASK_PURPOSE_TITLE");
	TASK_PURPOSE_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_PURPOSE_LEGEND_TITLE");
	TASK_MULTIMEDIA_PURPOSE_TITLE = KMADeMain.messages
		.getString("TASK_MULTIMEDIA_PURPOSE_TITLE");
	TASK_MULTIMEDIA_PURPOSE_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_MULTIMEDIA_PURPOSE_LEGEND_TITLE");
	TASK_MULTIMEDIA_ERROR_FILE_MESSAGE = KMADeMain.messages
		.getString("TASK_MULTIMEDIA_ERROR_FILE_MESSAGE");
	TASK_LABEL_TITLE = KMADeMain.messages.getString("TASK_LABEL_TITLE");
	TASK_LABEL_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_LABEL_LEGEND_TITLE");
	TASK_OBJECTS_TITLE = KMADeMain.messages.getString("TASK_OBJECTS_TITLE");
	TASK_OBJECTS_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_OBJECTS_LEGEND_TITLE");
	TASK_FEEDBACK_TITLE = KMADeMain.messages
		.getString("TASK_FEEDBACK_TITLE");
	TASK_FEEDBACK_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_FEEDBACK_LEGEND_TITLE");
	TASK_DURATION_TITLE = KMADeMain.messages
		.getString("TASK_DURATION_TITLE");
	TASK_DURATION_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_DURATION_LEGEND_TITLE");
	TASK_OBSERVATION_TITLE = KMADeMain.messages
		.getString("TASK_OBSERVATION_TITLE");
	TASK_OBSERVATION_LEGEND_TITLE = KMADeMain.messages
		.getString("TASK_OBSERVATION_LEGEND_TITLE");
	EXECUTING_NAME_TITLE = KMADeMain.messages
		.getString("EXECUTING_NAME_TITLE");
	EXECUTING_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("EXECUTING_NAME_LEGEND_TITLE");
	MODALITY_NAME_TITLE = KMADeMain.messages
		.getString("MODALITY_NAME_TITLE");
	MODALITY_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("MODALITY_NAME_LEGEND_TITLE");
	FREQUENCY_NAME_TITLE = KMADeMain.messages
		.getString("FREQUENCY_NAME_TITLE");
	FREQUENCY_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("FREQUENCY_NAME_LEGEND_TITLE");
	FREQUENCY_VALUE_NAME_TITLE = KMADeMain.messages
		.getString("FREQUENCY_VALUE_NAME_TITLE");
	FREQUENCY_VALUE_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("FREQUENCY_VALUE_NAME_LEGEND_TITLE");
	IMPORTANT_NAME_TITLE = KMADeMain.messages
		.getString("IMPORTANT_NAME_TITLE");
	IMPORTANT_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("IMPORTANT_NAME_LEGEND_TITLE");
	SENSORI_MOTRICE_NAME_TITLE = KMADeMain.messages
		.getString("SENSORI_MOTRICE_NAME_TITLE");
	COGNITIVE_NAME_TITLE = KMADeMain.messages
		.getString("COGNITIVE_NAME_TITLE");
	SCHEDULE_PANEL_TITLE_NAME = KMADeMain.messages
		.getString("SCHEDULE_PANEL_TITLE_NAME");

	// Ordonnancement d'une t�che
	NECESSITE_LABEL_NAME = KMADeMain.messages
		.getString("NECESSITE_LABEL_NAME");
	NECESSITE_LABEL_LEGEND_NAME = KMADeMain.messages
		.getString("NECESSITE_LABEL_LEGEND_NAME");
	INTERRUPTIBLE_LABEL_NAME = KMADeMain.messages
		.getString("INTERRUPTIBLE_LABEL_NAME");
	INTERRUPTIBLE_LABEL_LEGEND_NAME = KMADeMain.messages
		.getString("INTERRUPTIBLE_LABEL_LEGEND_NAME");
	DECLENCHEMENT_LABEL_NAME = KMADeMain.messages
		.getString("DECLENCHEMENT_LABEL_NAME");
	DECLENCHEMENT_LABEL_LEGEND_NAME = KMADeMain.messages
		.getString("DECLENCHEMENT_LABEL_LEGEND_NAME");
	UTILISATEUR_LABEL_NAME = KMADeMain.messages
		.getString("UTILISATEUR_LABEL_NAME");
	UTILISATEUR_LABEL_LEGEND_NAME = KMADeMain.messages
		.getString("UTILISATEUR_LABEL_LEGEND_NAME");
	MATERIEL_LABEL_NAME = KMADeMain.messages
		.getString("MATERIEL_LABEL_NAME");
	MATERIEL_LABEL_LEGEND = KMADeMain.messages
		.getString("MATERIEL_LABEL_LEGEND");
	SCHEDULE_LABEL_NAME = KMADeMain.messages
		.getString("SCHEDULE_LABEL_NAME");
	SCHEDULE_LABEL_LEGEND_NAME = KMADeMain.messages
		.getString("SCHEDULE_LABEL_LEGEND_NAME");
	OPTIONAL_NECESSITE_VALUE = KMADeMain.messages
		.getString("OPTIONAL_NECESSITE_VALUE");
	NO_OPTIONAL_NECESSITE_VALUE = KMADeMain.messages
		.getString("NO_OPTIONAL_NECESSITE_VALUE");
	INTERRUPTIBLE_VALUE = KMADeMain.messages
		.getString("INTERRUPTIBLE_VALUE");
	NO_INTERRUPTIBLE_VALUE = KMADeMain.messages
		.getString("NO_INTERRUPTIBLE_VALUE");
	PRECONDITION_LABEL_VALUE = KMADeMain.messages
		.getString("PRECONDITION_LABEL_VALUE");
	PRECONDITION_LABEL_LEGEND_VALUE = KMADeMain.messages
		.getString("PRECONDITION_LABEL_LEGEND_VALUE");
	ITERATION_LABEL_VALUE = KMADeMain.messages
		.getString("ITERATION_LABEL_VALUE");
	ITERATION_LABEL_LEGEND_VALUE = KMADeMain.messages
		.getString("ITERATION_LABEL_LEGEND_VALUE");

	// Effets d'une t�che
	POSTCONDITION_PANEL_TITLE_NAME = KMADeMain.messages
		.getString("POSTCONDITION_PANEL_TITLE_NAME");
	EFFETSDEBORD_LABEL_VALUE = KMADeMain.messages
		.getString("EFFETSDEBORD_LABEL_VALUE");
	EFFETSDEBORD_LABEL_LEGEND_VALUE = KMADeMain.messages
		.getString("EFFETSDEBORD_LABEL_LEGEND_VALUE");
	EVENT_NAME_TITLE = KMADeMain.messages.getString("EVENT_NAME_TITLE");
	EVENT_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("EVENT_NAME_LEGEND_TITLE");

	// Pour l'outil li� au multim�dia
	MEDIA_FILE_NAME = KMADeMain.messages.getString("MEDIA_FILE_NAME");
	MEDIA_FILE_LENGTH = KMADeMain.messages.getString("MEDIA_FILE_LENGTH");
	MEDIA_LENGTH = KMADeMain.messages.getString("MEDIA_LENGTH");
	MEDIA_START_TAG = KMADeMain.messages.getString("MEDIA_START_TAG");
	MEDIA_FINISH_TAG = KMADeMain.messages.getString("MEDIA_FINISH_TAG");
	MEDIA_NO_INFORMATION_PANEL_MESSAGE = KMADeMain.messages
		.getString("MEDIA_NO_INFORMATION_PANEL_MESSAGE");
	INFORMATION_MEDIA_TITLE_NAME = KMADeMain.messages
		.getString("INFORMATION_MEDIA_TITLE_NAME");
	MEDIA_TITLE_NAME = KMADeMain.messages.getString("MEDIA_TITLE_NAME");
	NO_MEDIA_FILE_MESSAGE = KMADeMain.messages
		.getString("NO_MEDIA_FILE_MESSAGE");
	QUICK_TIME_NO_INSTALLED_ERROR_MESSAGE = KMADeMain.messages
		.getString("QUICK_TIME_NO_INSTALLED_ERROR_MESSAGE");
	CONTROLE_MEDIA_TITLE_NAME = KMADeMain.messages
		.getString("CONTROLE_MEDIA_TITLE_NAME");

	// Editeur complet d'une t�che
	EXECUTION_CONDITION_TITLE_NAME = KMADeMain.messages
		.getString("EXECUTION_CONDITION_TITLE_NAME");
	OBJECT_LIST_TITLE_NAME = KMADeMain.messages
		.getString("OBJECT_LIST_TITLE_NAME");
	POSTCONDITION_TITLE_NAME = KMADeMain.messages
		.getString("POSTCONDITION_TITLE_NAME");
	EFFETSDEBORD_TITLE_NAME = KMADeMain.messages
		.getString("EFFETSDEBORD_TITLE_NAME");
	NO_MOTHER_TASK_MESSAGE = KMADeMain.messages
		.getString("NO_MOTHER_TASK_MESSAGE");
	NO_LEFT_TASK_MESSAGE = KMADeMain.messages
		.getString("NO_LEFT_TASK_MESSAGE");
	NO_RIGHT_TASK_MESSAGE = KMADeMain.messages
		.getString("NO_RIGHT_TASK_MESSAGE");
	NO_SUB_TASK_MESSAGE = KMADeMain.messages
		.getString("NO_SUB_TASK_MESSAGE");
	NO_NUMERO_TASK = KMADeMain.messages.getString("NO_NUMERO_TASK");

	// Caract�ristique d'un projet
	INTERVIEWED_NAME_TABLE = KMADeMain.messages
		.getString("INTERVIEWED_NAME_TABLE");
	PLACE_INFORMATION_TABLE = KMADeMain.messages
		.getString("PLACE_INFORMATION_TABLE");
	STATUT_TABLE = KMADeMain.messages.getString("STATUT_TABLE");
	SENIORITY_TABLE = KMADeMain.messages.getString("SENIORITY_TABLE");
	DATE_TABLE = KMADeMain.messages.getString("DATE_TABLE");
	INTERVIEW_TYPE_TABLE = KMADeMain.messages
		.getString("INTERVIEW_TYPE_TABLE");
	SEARCHED_INFORMATIONS_TABLE = KMADeMain.messages
		.getString("SEARCHED_INFORMATIONS_TABLE");
	INTERVIEW_NAME_TABLE = KMADeMain.messages
		.getString("INTERVIEW_NAME_TABLE");
	INTERVIEW_NEW_OBJECT_TABLE = KMADeMain.messages
		.getString("INTERVIEW_NEW_OBJECT_TABLE");
	GENERAL_DESCRIPTION_PROJECT_DIALOG_TITLE = KMADeMain.messages
		.getString("GENERAL_DESCRIPTION_PROJECT_DIALOG_TITLE");
	COMPANY_LABEL_NAME = KMADeMain.messages.getString("COMPANY_LABEL_NAME");
	SITE_LABEL_NAME = KMADeMain.messages.getString("SITE_LABEL_NAME");
	POSTE_TYPE_LABEL_NAME = KMADeMain.messages
		.getString("POSTE_TYPE_LABEL_NAME");
	DATE_STUDY = KMADeMain.messages.getString("DATE_STUDY");
	OTHER_RESOURCES = KMADeMain.messages.getString("OTHER_RESOURCES");
	INTERVIEWED_DESCRIPTION_PROJECT_DIALOG_TITLE = KMADeMain.messages
		.getString("INTERVIEWED_DESCRIPTION_PROJECT_DIALOG_TITLE");
	JUSTIFICATION_PROJECT_DIALOG_TITLE = KMADeMain.messages
		.getString("JUSTIFICATION_PROJECT_DIALOG_TITLE");
	REMOVE_INTERVIEW_MESSAGE_TITLE = KMADeMain.messages
		.getString("REMOVE_INTERVIEW_MESSAGE_TITLE");
	ASK_REMOVE_INTERVIEW_MESSAGE_TITLE = KMADeMain.messages
		.getString("ASK_REMOVE_INTERVIEW_MESSAGE_TITLE");

	// Editeur des attributs d'une tt�cche
	EDITOR_MESSAGE_TITLE = KMADeMain.messages
		.getString("EDITOR_MESSAGE_TITLE");
	EDITOR_OPERATOR_NAME = KMADeMain.messages
		.getString("EDITOR_OPERATOR_NAME");
	EDITOR_NAME_NAME = KMADeMain.messages.getString("EDITOR_NAME_NAME");
	EDITOR_EXECUTANT_NAME = KMADeMain.messages
		.getString("EDITOR_EXECUTANT_NAME");

	// Gestion du projet
	EXTENSION_EXPRESS_FILTER_NAME = KMADeMain.messages
		.getString("EXTENSION_EXPRESS_FILTER_NAME");
	EXTENSION_KMADE_FILTER_NAME = KMADeMain.messages
		.getString("EXTENSION_KMADE_FILTER_NAME");
	SET_EXISTING_PROJECT_LONG_MESSAGE = KMADeMain.messages
		.getString("SET_EXISTING_PROJECT_LONG_MESSAGE");
	SET_EXISTING_PROJECT_SHORT_MESSAGE = KMADeMain.messages
		.getString("SET_EXISTING_PROJECT_SHORT_MESSAGE");
	STATE_LOAD_TITLE_NAME = KMADeMain.messages
		.getString("STATE_LOAD_TITLE_NAME");
	ELEMENT_PARSE_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("ELEMENT_PARSE_PROBLEM_MESSAGE");
	XML_PARSER_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("XML_PARSER_PROBLEM_MESSAGE");
	XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE");

	// Gestion du chargement
	OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME = KMADeMain.messages
		.getString("OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME");
	OPEN_ERROR_FILE = KMADeMain.messages.getString("OPEN_ERROR_FILE");
	OPEN_EXPRESS_OK_FILE = KMADeMain.messages
		.getString("OPEN_EXPRESS_OK_FILE");
	OPEN_EXPRESS_NO_OK_FILE = KMADeMain.messages
		.getString("OPEN_EXPRESS_NO_OK_FILE");
	EXPRESS_OBJECTS_TITLE_MESSAGE = KMADeMain.messages
		.getString("EXPRESS_OBJECTS_TITLE_MESSAGE");
	GRAPHICAL_OBJECTS_MESSAGE = KMADeMain.messages
		.getString("GRAPHICAL_OBJECTS_MESSAGE");
	CREATE_GRAPHICAL_TASKS_MESSAGE = KMADeMain.messages
		.getString("CREATE_GRAPHICAL_TASKS_MESSAGE");
	CREATE_GRAPHICAL_EDGES_MESSAGE = KMADeMain.messages
		.getString("CREATE_GRAPHICAL_EDGES_MESSAGE");
	LOAD_PROJECT_FINISHED_MESSAGE = KMADeMain.messages
		.getString("LOAD_PROJECT_FINISHED_MESSAGE");
	LOAD_CONSOLE_TITLE_NAME = KMADeMain.messages
		.getString("LOAD_CONSOLE_TITLE_NAME");
	IMPLICIT_STOP_LOAD_SPF_FILE = KMADeMain.messages
		.getString("IMPLICIT_STOP_LOAD_SPF_FILE");
	EXPLICIT_STOP_LOAD_SPF_FILE_DURING_OBJECT = KMADeMain.messages
		.getString("EXPLICIT_STOP_LOAD_SPF_FILE_DURING_OBJECT");
	EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT = KMADeMain.messages
		.getString("EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT");

	// Gestion de la sauvegarde
	SAVE_CANCELLED_EXPRESS_FILECHOOSER_NAME = KMADeMain.messages
		.getString("SAVE_CANCELLED_EXPRESS_FILECHOOSER_NAME");
	WRITE_EXPRESS_ERROR_FILE = KMADeMain.messages
		.getString("WRITE_EXPRESS_ERROR_FILE");
	WRITE_ERROR_FILE = KMADeMain.messages.getString("WRITE_ERROR_FILE");
	WRITE_EXPRESS_OK_FILE = KMADeMain.messages
		.getString("WRITE_EXPRESS_OK_FILE");
	WRITE_EXPRESS_NO_OK_FILE = KMADeMain.messages
		.getString("WRITE_EXPRESS_NO_OK_FILE");
	WRITE_BEFORE_OPEN_PROJECT_MESSAGE = KMADeMain.messages
		.getString("WRITE_BEFORE_OPEN_PROJECT_MESSAGE");
	WRITE_BEFORE_EXIT_TOOL_MESSAGE = KMADeMain.messages
		.getString("WRITE_BEFORE_EXIT_TOOL_MESSAGE");
	WRITE_BEFORE_NEW_PROJECT_MESSAGE = KMADeMain.messages
		.getString("WRITE_BEFORE_NEW_PROJECT_MESSAGE");
	WRITE_BEFORE_CLOSE_PROJECT_MESSAGE = KMADeMain.messages
		.getString("WRITE_BEFORE_CLOSE_PROJECT_MESSAGE");
	WRITE_BEFORE_CHANGE_LOCALE_MESSAGE = KMADeMain.messages
		.getString("WRITE_BEFORE_CHANGE_LOCALE_MESSAGE");
	SAVE_CONSOLE_TITLE_NAME = KMADeMain.messages
		.getString("SAVE_CONSOLE_TITLE_NAME");
	SAVE_PROJECT_FINISHED_MESSAGE = KMADeMain.messages
		.getString("SAVE_PROJECT_FINISHED_MESSAGE");
	IMPLICIT_STOP_SAVE_FILE_MESSAGE = KMADeMain.messages
		.getString("IMPLICIT_STOP_SAVE_FILE_MESSAGE");
	EXPLICIT_STOP_SAVE_FILE_MESSAGE = KMADeMain.messages
		.getString("EXPLICIT_STOP_SAVE_FILE_MESSAGE");

	// Outil pour lister les entit�s
	ENTITY_LIST_DIALOG_TITLE = KMADeMain.messages
		.getString("ENTITY_LIST_DIALOG_TITLE");
	FORCE_ENTITY_LIST_ACTION_MESSAGE = KMADeMain.messages
		.getString("FORCE_ENTITY_LIST_ACTION_MESSAGE");

	// Pr�f�rences de l'outil
	GENERAL_TABBED_NAME = KMADeMain.messages
		.getString("GENERAL_TABBED_NAME");
	TASK_MODEL_EDITOR_TABBED_NAME = KMADeMain.messages
		.getString("TASK_MODEL_EDITOR_TABBED_NAME");
	TASK_MODEL_EDITOR_TABBED_LONG_NAME = KMADeMain.messages
		.getString("TASK_MODEL_EDITOR_TABBED_LONG_NAME");
	TREE_EDITOR_TABBED_NAME = KMADeMain.messages
		.getString("TREE_EDITOR_TABBED_NAME");
	TREE_EDITOR_TABBED_LONG_NAME = KMADeMain.messages
		.getString("TREE_EDITOR_TABBED_LONG_NAME");

	GENERAL_PANEL_CONFIGURATION_PANEL_NAME = KMADeMain.messages
		.getString("GENERAL_PANEL_CONFIGURATION_PANEL_NAME");
	GENERAL_PANEL_AUTOMATIC_SAVE_LABEL = KMADeMain.messages
		.getString("GENERAL_PANEL_AUTOMATIC_SAVE_LABEL");
	GENERAL_PANEL_BUG_MESSAGE_LABEL = KMADeMain.messages
		.getString("GENERAL_PANEL_BUG_MESSAGE_LABEL");
	GENERAL_PANEL_GARBAGE_COLLECTOR_LABEL = KMADeMain.messages
		.getString("GENERAL_PANEL_GARBAGE_COLLECTOR_LABEL");
	GENERAL_PANEL_SPLASH_SREEN_LABEL = KMADeMain.messages
		.getString("GENERAL_PANEL_SPLASH_SREEN_LABEL");
	GENERAL_PANEL_LANGUAGE_PANEL_NAME = KMADeMain.messages
		.getString("GENERAL_PANEL_LANGUAGE_PANEL_NAME");
	GENERAL_PANEL_LANGUAGE_LABEL = KMADeMain.messages
		.getString("GENERAL_PANEL_LANGUAGE_LABEL");
	GENERAL_PANEL_KEYS_PANEL_NAME = KMADeMain.messages
		.getString("GENERAL_PANEL_KEYS_PANEL_NAME");
	GENERAL_PANEL_ZOOM_LABEL = KMADeMain.messages
		.getString("GENERAL_PANEL_ZOOM_LABEL");

	TASK_PANEL_NAME_PANEL_NAME = KMADeMain.messages
		.getString("TASK_PANEL_NAME_PANEL_NAME");
	TASK_PANEL_NAME_FONT_NAME_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_NAME_FONT_NAME_LABEL");
	TASK_PANEL_NAME_FONT_SIZE_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_NAME_FONT_SIZE_LABEL");
	TASK_PANEL_NAME_FONT_STYLE_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_NAME_FONT_STYLE_LABEL");
	TASK_PANEL_NAME_FONT_COLOR_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_NAME_FONT_COLOR_LABEL");
	TASK_PANEL_NAME_BACKGROUND_COLOR_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_NAME_BACKGROUND_COLOR_LABEL");
	TASK_PANEL_DECOMPOSITION_PANEL_NAME = KMADeMain.messages
		.getString("TASK_PANEL_DECOMPOSITION_PANEL_NAME");

	TASK_PANEL_MODEL_PANEL_NAME = KMADeMain.messages
		.getString("TASK_PANEL_MODEL_PANEL_NAME");
	TASK_PANEL_MODEL_CHOICE_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_MODEL_CHOICE_LABEL");
	TASK_PANEL_MODEL_SAVE_BUTTON_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_MODEL_SAVE_BUTTON_LABEL");
	TASK_PANEL_MODEL_LOAD_BUTTON_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_MODEL_LOAD_BUTTON_LABEL");
	TASK_PANEL_OPTIONS_PANEL_NAME = KMADeMain.messages
		.getString("TASK_PANEL_OPTIONS_PANEL_NAME");
	TASK_PANEL_OPTIONS_TRIGGER_EVENT_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_OPTIONS_TRIGGER_EVENT_LABEL");
	TASK_PANEL_OPTIONS_ACTOR_TASK_LABEL = KMADeMain.messages
		.getString("TASK_PANEL_OPTIONS_ACTOR_TASK_LABEL");
	TASK_PANEL_PREVIEW_PANEL_NAME = KMADeMain.messages
		.getString("TASK_PANEL_PREVIEW_PANEL_NAME");

	TASK_TREE_PANEL_LAYOUT_PANEL_NAME = KMADeMain.messages
		.getString("TASK_TREE_PANEL_LAYOUT_PANEL_NAME");
	TASK_TREE_PANEL_LAYOUT_ORIENTATION_LABEL = KMADeMain.messages
		.getString("TASK_TREE_PANEL_LAYOUT_ORIENTATION_LABEL");
	TASK_TREE_PANEL_LAYOUT_DISTANCE_LABEL = KMADeMain.messages
		.getString("TASK_TREE_PANEL_LAYOUT_DISTANCE_LABEL");
	TASK_TREE_PANEL_LAYOUT_SELECTION_COLOR_LABEL = KMADeMain.messages
		.getString("TASK_TREE_PANEL_LAYOUT_SELECTION_COLOR_LABEL");
	TASK_TREE_PANEL_LAYOUT_LEVEL_DISTANCE_LABEL = KMADeMain.messages
		.getString("TASK_TREE_PANEL_LAYOUT_LEVEL_DISTANCE_LABEL");
	TASK_TREE_PANEL_OPTIONS_PANEL_NAME = KMADeMain.messages
		.getString("TASK_TREE_PANEL_OPTIONS_PANEL_NAME");
	TASK_TREE_PANEL_OPTIONS_ORTHOGONAL_EDGE_LABEL = KMADeMain.messages
		.getString("TASK_TREE_PANEL_OPTIONS_ORTHOGONAL_EDGE_LABEL");

	// Noms de couleurs
	COLOR_NO_COLOR = KMADeMain.messages.getString("COLOR_NO_COLOR");
	COLOR_BLUE = KMADeMain.messages.getString("COLOR_BLUE");
	COLOR_GREEN = KMADeMain.messages.getString("COLOR_GREEN");
	COLOR_RED = KMADeMain.messages.getString("COLOR_RED");
	COLOR_BLACK = KMADeMain.messages.getString("COLOR_BLACK");
	COLOR_YELLOW = KMADeMain.messages.getString("COLOR_YELLOW");
	COLOR_WHITE = KMADeMain.messages.getString("COLOR_WHITE");

	// Noms de polices
	ARIAL_FONT = KMADeMain.messages.getString("ARIAL_FONT");
	TIMES_FONT = KMADeMain.messages.getString("TIMES_FONT");
	TAHOMA_FONT = KMADeMain.messages.getString("TAHOMA_FONT");

	// Noms de styles de police
	STYLE_PLAIN = KMADeMain.messages.getString("STYLE_PLAIN");
	STYLE_BOLD = KMADeMain.messages.getString("STYLE_BOLD");
	STYLE_ITALIC = KMADeMain.messages.getString("STYLE_ITALIC");
	STYLE_BOLD_ITALIC = KMADeMain.messages.getString("STYLE_BOLD_ITALIC");

	// Noms de langues
	FRENCH_LANGUAGE = KMADeMain.messages.getString("FRENCH_LANGUAGE");
	ENGLISH_LANGUAGE = KMADeMain.messages.getString("ENGLISH_LANGUAGE");

	FRENCH_LANGUAGE_INFO = KMADeMain.messages
		.getString("FRENCH_LANGUAGE_INFO");
	ENGLISH_LANGUAGE_INFO = KMADeMain.messages
		.getString("ENGLISH_LANGUAGE_INFO");

	// Messages commumns
	YES_MESSAGE = KMADeMain.messages.getString("YES_MESSAGE");
	NO_MESSAGE = KMADeMain.messages.getString("NO_MESSAGE");
	ALL_MESSAGE = KMADeMain.messages.getString("ALL_MESSAGE");
	VALID_MESSAGE = KMADeMain.messages.getString("VALID_MESSAGE");
	APPLY_MESSAGE = KMADeMain.messages.getString("APPLY_MESSAGE");
	CANCEL_MESSAGE = KMADeMain.messages.getString("CANCEL_MESSAGE");
	SAVE_AS_MESSAGE = KMADeMain.messages.getString("SAVE_AS_MESSAGE");
	OVERWRITE_FILE_MESSAGE = KMADeMain.messages
		.getString("OVERWRITE_FILE_MESSAGE");
	GO_BACK_MESSAGE = KMADeMain.messages.getString("GO_BACK_MESSAGE");
	CONFIRMATION_DIALOG_MESSAGE = KMADeMain.messages
		.getString("CONFIRMATION_DIALOG_MESSAGE");
	BROWSE_DIRECTORY_OR_FILE_MESSAGE = KMADeMain.messages
		.getString("BROWSE_DIRECTORY_OR_FILE_MESSAGE");
	REMOVE_MESSAGE = KMADeMain.messages.getString("REMOVE_MESSAGE");

	// Action des ToolBars
	FILE_MENU_MESSAGE = KMADeMain.messages.getString("FILE_MENU_MESSAGE");
	EDITING_MENU_MESSAGE = KMADeMain.messages
		.getString("EDITING_MENU_MESSAGE");
	VIEW_MENU_MESSAGE = KMADeMain.messages.getString("VIEW_MENU_MESSAGE");
	PROJECT_MENU_MESSAGE = KMADeMain.messages
		.getString("PROJECT_MENU_MESSAGE");
	HELP_MENU_MESSAGE = KMADeMain.messages.getString("HELP_MENU_MESSAGE");
	TOOLS_MENU_MESSAGE = KMADeMain.messages.getString("TOOLS_MENU_MESSAGE");

	NEW_PROJECT_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_PROJECT_ACTION_MESSAGE");
	OPEN_PROJECT_ACTION_MESSAGE = KMADeMain.messages
		.getString("OPEN_PROJECT_ACTION_MESSAGE");
	SAVE_PROJECT_ACTION_MESSAGE = KMADeMain.messages
		.getString("SAVE_PROJECT_ACTION_MESSAGE");
	SAVE_PROJECT_AS_ACTION_MESSAGE = KMADeMain.messages
		.getString("SAVE_PROJECT_AS_ACTION_MESSAGE");
	CLOSE_PROJECT_ACTION_MESSAGE = KMADeMain.messages
		.getString("CLOSE_PROJECT_ACTION_MESSAGE");
	PRINT_PROJECT_ACTION_MESSAGE = KMADeMain.messages
		.getString("PRINT_PROJECT_ACTION_MESSAGE");
	EXIT_ACTION_MESSAGE = KMADeMain.messages
		.getString("EXIT_ACTION_MESSAGE");
	HIDE_GRID_ACTION_MESSAGE = KMADeMain.messages
		.getString("HIDE_GRID_ACTION_MESSAGE");
	SHOW_GRID_ACTION_MESSAGE = KMADeMain.messages
		.getString("SHOW_GRID_ACTION_MESSAGE");
	CHOICE_GRID_SIZE_MESSAGE = KMADeMain.messages
		.getString("CHOICE_GRID_SIZE_MESSAGE");
	HIDE_RULE_ACTION_MESSAGE = KMADeMain.messages
		.getString("HIDE_RULE_ACTION_MESSAGE");
	SHOW_RULE_ACTION_MESSAGE = KMADeMain.messages
		.getString("SHOW_RULE_ACTION_MESSAGE");
	PREFERENCE_ACTION_MESSAGE = KMADeMain.messages
		.getString("PREFERENCE_ACTION_MESSAGE");
	DEBUG_INFO_ACTION_MESSAGE = KMADeMain.messages
		.getString("DEBUG_INFO_ACTION_MESSAGE");
	ABOUT_ACTION_MESSAGE = KMADeMain.messages
		.getString("ABOUT_ACTION_MESSAGE");
	HELP_MODEL_ACTION_MESSAGE = KMADeMain.messages
		.getString("HELP_MODEL_ACTION_MESSAGE");
	HELP_TOOL_ACTION_MESSAGE = KMADeMain.messages
		.getString("HELP_TOOL_ACTION_MESSAGE");
	HISTORY_ACTION_MESSAGE = KMADeMain.messages
		.getString("HISTORY_ACTION_MESSAGE");
	UNDO_ACTION_MESSAGE = KMADeMain.messages
		.getString("HELP_TOOL_ACTION_MESSAGE");
	REDO_ACTION_MESSAGE = KMADeMain.messages
		.getString("REDO_ACTION_MESSAGE");
	CUT_ACTION_MESSAGE = KMADeMain.messages.getString("CUT_ACTION_MESSAGE");
	COPY_ACTION_MESSAGE = KMADeMain.messages
		.getString("COPY_ACTION_MESSAGE");
	PASTE_ACTION_MESSAGE = KMADeMain.messages
		.getString("PASTE_ACTION_MESSAGE");
	SHOW_CLIPBOARD_MESSAGE = KMADeMain.messages
		.getString("SHOW_CLIPBOARD_MESSAGE");
	SEARCH_ACTION_MESSAGE = KMADeMain.messages
		.getString("SEARCH_ACTION_MESSAGE");
	SIMULATION_ACTION_MESSAGE = KMADeMain.messages
		.getString("SIMULATION_ACTION_MESSAGE");
	INTERROGATION_ACTION_MESSAGE = KMADeMain.messages
		.getString("INTERROGATION_ACTION_MESSAGE");
	CHECK_COHERENCE_ACTION_MESSAGE = KMADeMain.messages
		.getString("CHECK_COHERENCE_ACTION_MESSAGE");
	ENTITY_LIST_ACTION_MESSAGE = KMADeMain.messages
		.getString("ENTITY_LIST_ACTION_MESSAGE");
	VERSION_MESSAGE = KMADeMain.messages.getString("VERSION_MESSAGE");
	MESSAGES_MESSAGE = KMADeMain.messages.getString("MESSAGES_MESSAGE");
	ERREURS_MESSAGE = KMADeMain.messages.getString("ERREURS_MESSAGE");
	FIND_REPLACE_ACTION_MESSAGE = KMADeMain.messages
		.getString("FIND_REPLACE_ACTION_MESSAGE");

	GRID_SIZE_ACTION_MESSAGE = KMADeMain.messages
		.getString("GRID_SIZE_ACTION_MESSAGE");
	INPUT_GRID_SIZE_MESSAGE = KMADeMain.messages
		.getString("INPUT_GRID_SIZE_MESSAGE");
	INPUT_GRID_SIZE_ERROR_MESSAGE = KMADeMain.messages
		.getString("INPUT_GRID_SIZE_ERROR_MESSAGE");

	NEW_UNKNOWN_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_UNKNOWN_TASK_ACTION_MESSAGE");
	NEW_ABSTRACT_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_ABSTRACT_TASK_ACTION_MESSAGE");
	NEW_INTERACTION_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_INTERACTION_TASK_ACTION_MESSAGE");
	NEW_FEEDBACK_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_FEEDBACK_TASK_ACTION_MESSAGE");
	NEW_USER_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_USER_TASK_ACTION_MESSAGE");
	EDIT_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("EDIT_TASK_ACTION_MESSAGE");
	COMPLETE_EDIT_TASK_ACTION_MESSAGE = KMADeMain.messages
		.getString("COMPLETE_EDIT_TASK_ACTION_MESSAGE");

	DELETE_CELL_ACTION_MESSAGE = KMADeMain.messages
		.getString("DELETE_CELL_ACTION_MESSAGE");
	PREVIEW_WINDOW_ACTION_MESSAGE = KMADeMain.messages
		.getString("PREVIEW_WINDOW_ACTION_MESSAGE");
	ZOOM_IN_ACTION_MESSAGE = KMADeMain.messages
		.getString("ZOOM_IN_ACTION_MESSAGE");
	ZOOM_OUT_ACTION_MESSAGE = KMADeMain.messages
		.getString("ZOOM_OUT_ACTION_MESSAGE");
	ZOOM_DEFAULT_ACTION_MESSAGE = KMADeMain.messages
		.getString("ZOOM_DEFAULT_ACTION_MESSAGE");
	ZOOM_GLOBAL_ACTION_MESSAGE = KMADeMain.messages
		.getString("ZOOM_GLOBAL_ACTION_MESSAGE");

	MAGNET_OPTION_ACTION_MESSAGE = KMADeMain.messages
		.getString("MAGNET_OPTION_ACTION_MESSAGE");
	JUSTIFY_OPTION_ACTION_MESSAGE = KMADeMain.messages
		.getString("JUSTIFY_OPTION_ACTION_MESSAGE");
	SAME_EXECUTANT_MESSAGE = KMADeMain.messages
		.getString("SAME_EXECUTANT_MESSAGE");
	VISIBLE_LABEL_ACTION_MESSAGE = KMADeMain.messages
		.getString("VISIBLE_LABEL_ACTION_MESSAGE");
	COLOR_LABEL_ACTION_MESSAGE = KMADeMain.messages
		.getString("COLOR_LABEL_ACTION_MESSAGE");

	TREE_TASK_PRINT_TITLE_MESSAGE = KMADeMain.messages
		.getString("TREE_TASK_PRINT_TITLE_MESSAGE");
	USER_CARDS_PRINT_TITLE_MESSAGE = KMADeMain.messages
		.getString("USER_CARDS_PRINT_TITLE_MESSAGE");
	OBJECTS_PRINT_TITLE_MESSAGE = KMADeMain.messages
		.getString("OBJECTS_PRINT_TITLE_MESSAGE");
	PRINT_ACTION_MESSAGE = KMADeMain.messages
		.getString("PRINT_ACTION_MESSAGE");
	LAYOUT_PRINT_ACTION_MESSAGE = KMADeMain.messages
		.getString("LAYOUT_PRINT_ACTION_MESSAGE");
	PRINT_PREVIEW_ACTION_MESSAGE = KMADeMain.messages
		.getString("PRINT_PREVIEW_ACTION_MESSAGE");
	PDF_EXPORT_ACTION_MESSAGE = KMADeMain.messages
		.getString("PDF_EXPORT_ACTION_MESSAGE");
	EPS_EXPORT_ACTION_MESSAGE = KMADeMain.messages
		.getString("EPS_EXPORT_ACTION_MESSAGE");
	PORTRAIT_ACTION_MESSAGE = KMADeMain.messages
		.getString("PORTRAIT_ACTION_MESSAGE");
	LANDSCAPE_ACTION_MESSAGE = KMADeMain.messages
		.getString("LANDSCAPE_ACTION_MESSAGE");
	SCALE_ACTION_MESSAGE = KMADeMain.messages
		.getString("SCALE_ACTION_MESSAGE");
	INPUT_SCALE_ACTION_MESSAGE = KMADeMain.messages
		.getString("INPUT_SCALE_ACTION_MESSAGE");
	PREVIEW_ZOOM_ACTION_MESSAGE = KMADeMain.messages
		.getString("PREVIEW_ZOOM_ACTION_MESSAGE");
	SAVE_HISTORIC_ACTION_MESSAGE = KMADeMain.messages
		.getString("SAVE_HISTORIC_ACTION_MESSAGE");
	CLEAR_HISTORIC_ACTION_MESSAGE = KMADeMain.messages
		.getString("CLEAR_HISTORIC_ACTION_MESSAGE");

	// Messages li�s aux expressions
	PRINT_SCALE_ADAPT_MESSAGE = KMADeMain.messages
		.getString("PRINT_SCALE_ADAPT_MESSAGE");
	PRINT_SCALE_PERSONALIZE_MESSAGE = KMADeMain.messages
		.getString("PRINT_SCALE_PERSONALIZE_MESSAGE");
	RESET_ACTION_MESSAGE = KMADeMain.messages
		.getString("RESET_ACTION_MESSAGE");
	CONCRETE_OBJECTS_LIST_MESSAGE = KMADeMain.messages
		.getString("CONCRETE_OBJECTS_LIST_MESSAGE");
	EDITOR_PRE_TITLE_NAME = KMADeMain.messages
		.getString("EDITOR_PRE_TITLE_NAME");
	EDITOR_POST_TITLE_NAME = KMADeMain.messages
		.getString("EDITOR_POST_TITLE_NAME");
	EDITOR_ITER_TITLE_NAME = KMADeMain.messages
		.getString("EDITOR_ITER_TITLE_NAME");
	CAUSE_MESSAGE = KMADeMain.messages.getString("CAUSE_MESSAGE");
	VALUE_MESSAGE = KMADeMain.messages.getString("VALUE_MESSAGE");
	CHECK_ACTION_MESSAGE = KMADeMain.messages
		.getString("CHECK_ACTION_MESSAGE");
	EVALUATE_ACTION_MESSAGE = KMADeMain.messages
		.getString("EVALUATE_ACTION_MESSAGE");
	STRING_TO_INTEGER = KMADeMain.messages.getString("STRING_TO_INTEGER");
	STRING_TO_NUMBER = KMADeMain.messages.getString("STRING_TO_NUMBER");
	NO_EXPRESSION_MESSAGE = KMADeMain.messages
		.getString("NO_EXPRESSION_MESSAGE");
	NO_FIRST_EXPRESSION_MESSAGE = KMADeMain.messages
		.getString("NO_FIRST_EXPRESSION_MESSAGE");
	MISSING_USER_VALUE_MESSAGE = KMADeMain.messages
		.getString("MISSING_USER_VALUE_MESSAGE");
	PARSER_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("PARSER_PROBLEM_MESSAGE");
	LEXICAL_ERROR_MESSAGE = KMADeMain.messages
		.getString("LEXICAL_ERROR_MESSAGE");
	SYNTAXICAL_ERROR_MESSAGE = KMADeMain.messages
		.getString("SYNTAXICAL_ERROR_MESSAGE");
	SEMANTICAL_ERROR_MESSAGE = KMADeMain.messages
		.getString("SEMANTICAL_ERROR_MESSAGE");
	LINE_MESSAGE = KMADeMain.messages.getString("LINE_MESSAGE");
	COLUMN_MESSAGE = KMADeMain.messages.getString("COLUMN_MESSAGE");
	ENCOUNTERED_CHAR_MESSAGE = KMADeMain.messages
		.getString("ENCOUNTERED_CHAR_MESSAGE");
	ENCOUNTERED_TOKEN_MESSAGE = KMADeMain.messages
		.getString("ENCOUNTERED_TOKEN_MESSAGE");

	CHECK_ALL_PRECONDITIONS = KMADeMain.messages
		.getString("CHECK_ALL_PRECONDITIONS");
	PRECONDITION_OK_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_OK_MESSAGE");
	PRECONDITION_NO_OK_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_NO_OK_MESSAGE");
	PRECONDITION_EVAL_OK_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_EVAL_OK_MESSAGE");
	PRECONDITION_EVAL_NO_OK_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_EVAL_NO_OK_MESSAGE");
	PRECONDITION_OF_TASK_OK_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_OF_TASK_OK_MESSAGE");
	PRECONDITION_OF_TASK_NO_OK_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_OF_TASK_NO_OK_MESSAGE");
	PRECONDITION_ERROR_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_ERROR_MESSAGE");
	NOT_YET_CHECKED_PRECONDITION_MESSAGE = KMADeMain.messages
		.getString("NOT_YET_CHECKED_PRECONDITION_MESSAGE");
	PRECONDITION_CHECKED_AND_BUILT_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_CHECKED_AND_BUILT_MESSAGE");
	PRECONDITION_PROGRESSBAR_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_PROGRESSBAR_MESSAGE");

	EXIT_WITHOUT_SAVE_MESSAGE = KMADeMain.messages
		.getString("EXIT_WITHOUT_SAVE_MESSAGE");
	CHECK_ALL_EFFETSDEBORDS = KMADeMain.messages
		.getString("CHECK_ALL_EFFETSDEBORDS");
	EFFETSDEBORD_OK_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_OK_MESSAGE");
	EFFETSDEBORD_NO_OK_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_NO_OK_MESSAGE");
	EFFETSDEBORD_EVAL_OK_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_EVAL_OK_MESSAGE");
	EFFETSDEBORD_EVAL_NO_OK_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_EVAL_NO_OK_MESSAGE");
	EFFETSDEBORD_OF_TASK_OK_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_OF_TASK_OK_MESSAGE");
	EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE");
	EFFETSDEBORD_ERROR_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_ERROR_MESSAGE");
	NOT_YET_CHECKED_EFFETSDEBORD_MESSAGE = KMADeMain.messages
		.getString("NOT_YET_CHECKED_EFFETSDEBORD_MESSAGE");
	EFFETSDEBORD_CHECKED_AND_BUILT_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_CHECKED_AND_BUILT_MESSAGE");
	EFFETSDEBORD_PROGRESSBAR_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_PROGRESSBAR_MESSAGE");
	EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE");
	EFFETSDEBORD_HISTORY_TITLE_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_HISTORY_TITLE_MESSAGE");
	EFFETSDEBORD_LOAD_STATE_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_LOAD_STATE_MESSAGE");
	NO_SELECTED_HISTORIC_MESSAGE = KMADeMain.messages
		.getString("NO_SELECTED_HISTORIC_MESSAGE");
	HISTORIC_LOADED_MESSAGE = KMADeMain.messages
		.getString("HISTORIC_LOADED_MESSAGE");
	HISTORIC_NO_LOADED_MESSAGE = KMADeMain.messages
		.getString("HISTORIC_NO_LOADED_MESSAGE");

	CHECK_ALL_ITERATIONS = KMADeMain.messages
		.getString("CHECK_ALL_ITERATIONS");
	ITERATION_OK_MESSAGE = KMADeMain.messages
		.getString("ITERATION_OK_MESSAGE");
	ITERATION_NO_OK_MESSAGE = KMADeMain.messages
		.getString("ITERATION_NO_OK_MESSAGE");
	ITERATION_EVAL_OK_MESSAGE = KMADeMain.messages
		.getString("ITERATION_EVAL_OK_MESSAGE");
	ITERATION_EVAL_NO_OK_MESSAGE = KMADeMain.messages
		.getString("ITERATION_EVAL_NO_OK_MESSAGE");
	NOT_YET_CHECKED_ITERATION_MESSAGE = KMADeMain.messages
		.getString("NOT_YET_CHECKED_ITERATION_MESSAGE");
	STOP_LOOP_ITERATION_MESSAGE = KMADeMain.messages
		.getString("STOP_LOOP_ITERATION_MESSAGE");
	CONTINUE_LOOP_ITERATION_MESSAGE = KMADeMain.messages
		.getString("CONTINUE_LOOP_ITERATION_MESSAGE");
	ITERATION_MESSAGE = KMADeMain.messages.getString("ITERATION_MESSAGE");
	ITERATION_ERROR_MESSAGE = KMADeMain.messages
		.getString("ITERATION_ERROR_MESSAGE");
	ITERATION_OF_TASK_OK_MESSAGE = KMADeMain.messages
		.getString("ITERATION_OF_TASK_OK_MESSAGE");
	ITERATION_OF_TASK_NO_OK_MESSAGE = KMADeMain.messages
		.getString("ITERATION_OF_TASK_NO_OK_MESSAGE");
	ITERATION_CHECKED_AND_BUILT_MESSAGE = KMADeMain.messages
		.getString("ITERATION_CHECKED_AND_BUILT_MESSAGE");
	ITERATION_PROGRESSBAR_MESSAGE = KMADeMain.messages
		.getString("ITERATION_PROGRESSBAR_MESSAGE");

	// Calculatrice pour les expressions
	CLEAR_EXPRESSION_MESSAGE = KMADeMain.messages
		.getString("CLEAR_EXPRESSION_MESSAGE");
	CHECK_VALIDATE_MESSAGE = KMADeMain.messages
		.getString("CHECK_VALIDATE_MESSAGE");
	EVALUATE_FORCE1_MESSAGE = KMADeMain.messages
		.getString("EVALUATE_FORCE1_MESSAGE");
	PRECONDITION_EDITION_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_EDITION_MESSAGE");
	EFFETSDEBORD_EDITION_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_EDITION_MESSAGE");
	ITERATION_EDITION_MESSAGE = KMADeMain.messages
		.getString("ITERATION_EDITION_MESSAGE");
	PRECONDITION_TEXTUEL_EDITION_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_TEXTUEL_EDITION_MESSAGE");
	EFFETSDEBORD_TEXTUEL_EDITION_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_TEXTUEL_EDITION_MESSAGE");
	ITERATION_TEXTUEL_EDITION_MESSAGE = KMADeMain.messages
		.getString("ITERATION_TEXTUEL_EDITION_MESSAGE");
	LITTERAUX_TITLE_MESSAGE = KMADeMain.messages
		.getString("LITTERAUX_TITLE_MESSAGE");
	OPERATORS_TITLE_MESSAGE = KMADeMain.messages
		.getString("OPERATORS_TITLE_MESSAGE");
	FUNCTIONS_TITLE_MESSAGE = KMADeMain.messages
		.getString("FUNCTIONS_TITLE_MESSAGE");
	ITERATORS_TITLE_MESSAGE = KMADeMain.messages
		.getString("ITERATORS_TITLE_MESSAGE");
	LOOP_VARIANT_MESSAGE = KMADeMain.messages
		.getString("LOOP_VARIANT_MESSAGE");

	// L'outil de recherche
	WRITE_TXT_FILE_OK = KMADeMain.messages.getString("WRITE_TXT_FILE_OK");
	WRITE_TXT_FILE_ERROR = KMADeMain.messages
		.getString("WRITE_TXT_FILE_ERROR");
	SAVE_CANCELLED_TEXT_FILECHOOSER_NAME = KMADeMain.messages
		.getString("SAVE_CANCELLED_TEXT_FILECHOOSER_NAME");
	SEARCH_NAME_NAME = KMADeMain.messages.getString("SEARCH_NAME_NAME");
	WHOLE_TASK_NAME_OPTION_REPLACE_MESSAGE = KMADeMain.messages
		.getString("WHOLE_TASK_NAME_OPTION_REPLACE_MESSAGE");
	SEARCH_FAC_NAME = KMADeMain.messages.getString("SEARCH_FAC_NAME");
	SEARCH_INT_NAME = KMADeMain.messages.getString("SEARCH_INT_NAME");
	EXTENSION_TEXT_FILTER_SEARCH_NAME = KMADeMain.messages
		.getString("EXTENSION_TEXT_FILTER_SEARCH_NAME");
	SEARCH_MESSAGE_TITLE = KMADeMain.messages
		.getString("SEARCH_MESSAGE_TITLE");
	FIND_MESSAGE = KMADeMain.messages.getString("FIND_MESSAGE");
	REPLACE_MESSAGE = KMADeMain.messages.getString("REPLACE_MESSAGE");
	DIRECTION_FIND_MESSAGE = KMADeMain.messages
		.getString("DIRECTION_FIND_MESSAGE");
	FORWARD_DIRECTION_FIND_MESSAGE = KMADeMain.messages
		.getString("FORWARD_DIRECTION_FIND_MESSAGE");
	BACKWARD_DIRECTION_FIND_MESSAGE = KMADeMain.messages
		.getString("BACKWARD_DIRECTION_FIND_MESSAGE");
	SCOPE_FIND_MESSAGE = KMADeMain.messages.getString("SCOPE_FIND_MESSAGE");
	ALL_FIND_MESSAGE = KMADeMain.messages.getString("ALL_FIND_MESSAGE");
	SELECTION_FIND_MESSAGE = KMADeMain.messages
		.getString("SELECTION_FIND_MESSAGE");
	OPTION_FIND_MESSAGE = KMADeMain.messages
		.getString("OPTION_FIND_MESSAGE");
	CASE_SENSITIVE_OPTIONS_FIND_MESSAGE = KMADeMain.messages
		.getString("CASE_SENSITIVE_OPTIONS_FIND_MESSAGE");
	WHOLE_TASK_NAME_OPTIONS_FIND_MESSAGE = KMADeMain.messages
		.getString("WHOLE_TASK_NAME_OPTIONS_FIND_MESSAGE");
	REGULAR_EXPRESSIONS_OPTIONS_FIND_MESSAGE = KMADeMain.messages
		.getString("REGULAR_EXPRESSIONS_OPTIONS_FIND_MESSAGE");
	SEARCH_RESULT_FIND_MESSAGE = KMADeMain.messages
		.getString("SEARCH_RESULT_FIND_MESSAGE");
	FIND_ACTION_FIND_MESSAGE = KMADeMain.messages
		.getString("FIND_ACTION_FIND_MESSAGE");
	SAVE_OCCURENCES_ACTION_FIND_MESSAGE = KMADeMain.messages
		.getString("SAVE_OCCURENCES_ACTION_FIND_MESSAGE");
	REPLACE_NEXT_ACTION_FIND_MESSAGE = KMADeMain.messages
		.getString("REPLACE_NEXT_ACTION_FIND_MESSAGE");
	REPLACE_PREVIOUS_ACTION_FIND_MESSAGE = KMADeMain.messages
		.getString("REPLACE_PREVIOUS_ACTION_FIND_MESSAGE");
	REPLACE_ACTION_FIND_MESSAGE = KMADeMain.messages
		.getString("REPLACE_ACTION_FIND_MESSAGE");
	REPLACE_ALL_ACTION_FIND_MESSAGE = KMADeMain.messages
		.getString("REPLACE_ALL_ACTION_FIND_MESSAGE");
	TASK_NOT_FOUNDED_ACCORDING_FIND_CRITERIAS = KMADeMain.messages
		.getString("TASK_NOT_FOUNDED_ACCORDING_FIND_CRITERIAS");
	COUNT_TASKS_FOUNDED_MESSAGE = KMADeMain.messages
		.getString("COUNT_TASKS_FOUNDED_MESSAGE");

	// Simulation de mani�re g�n�rale

	SIMULATION_TITLE_MESSAGE = KMADeMain.messages
		.getString("SIMULATION_TITLE_MESSAGE");
	ERROR_ITERATION_VIEW_MESSAGE = KMADeMain.messages
		.getString("ERROR_ITERATION_VIEW_MESSAGE");
	PREDICAT_ITERATION_VIEW_MESSAGE = KMADeMain.messages
		.getString("PREDICAT_ITERATION_VIEW_MESSAGE");
	USER_VALUE_ITERATION_VIEW_MESSAGE = KMADeMain.messages
		.getString("USER_VALUE_ITERATION_VIEW_MESSAGE");
	SCENARIO_FILTER_NAME = KMADeMain.messages
		.getString("SCENARIO_FILTER_NAME");
	ACTIONS_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("ACTIONS_SIMULATION_MESSAGE");
	EDIT_USER_VALUES_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("EDIT_USER_VALUES_SIMULATION_MESSAGE");
	RECORD_SCENARIO_MESSAGE = KMADeMain.messages
		.getString("RECORD_SCENARIO_MESSAGE");
	OPEN_CANCELLED_SCENARIO_FILECHOOSER_NAME = KMADeMain.messages
		.getString("OPEN_CANCELLED_SCENARIO_FILECHOOSER_NAME");
	SAVE_CANCELLED_SCENARIO_FILECHOOSER_NAME = KMADeMain.messages
		.getString("SAVE_CANCELLED_SCENARIO_FILECHOOSER_NAME");
	SUCCEEDED_SAVE_SCENARIO_MESSAGE = KMADeMain.messages
		.getString("SUCCEEDED_SAVE_SCENARIO_MESSAGE");
	NO_SUCCEEDED_SAVE_SCENARIO_MESSAGE = KMADeMain.messages
		.getString("NO_SUCCEEDED_SAVE_SCENARIO_MESSAGE");
	AUTHORIZED_EXECUTER_USER_MESSAGE = KMADeMain.messages
		.getString("AUTHORIZED_EXECUTER_USER_MESSAGE");
	NO_AUTHORIZED_EXECUTER_USER_MESSAGE = KMADeMain.messages
		.getString("NO_AUTHORIZED_EXECUTER_USER_MESSAGE");
	DISABLED_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("DISABLED_CONSTRAINT_MESSAGE");
	EVENT_TITLE_MESSAGE = KMADeMain.messages
		.getString("EVENT_TITLE_MESSAGE");
	NO_CONCRETE_OBJECT = KMADeMain.messages.getString("NO_CONCRETE_OBJECT");
	NO_FIRING_EVENT_MESSAGE = KMADeMain.messages
		.getString("NO_FIRING_EVENT_MESSAGE");
	NO_FIRING_EVENT_TINY_MESSAGE = KMADeMain.messages
		.getString("NO_FIRING_EVENT_TINY_MESSAGE");
	FIRABLE_EVENT_STATE_MESSAGE = KMADeMain.messages
		.getString("FIRABLE_EVENT_STATE_MESSAGE");
	NO_FIRABLE_EVENT_STATE_MESSAGE = KMADeMain.messages
		.getString("NO_FIRABLE_EVENT_STATE_MESSAGE");
	NO_SELECTED_ACTION_FOR_CONSTRAINTS_MESSAGE = KMADeMain.messages
		.getString("NO_SELECTED_ACTION_FOR_CONSTRAINTS_MESSAGE");
	NO_SELECTED_SCENARIO_ACTION_FOR_CONSTRAINTS_MESSAGE = KMADeMain.messages
		.getString("NO_SELECTED_SCENARIO_ACTION_FOR_CONSTRAINTS_MESSAGE");
	CONSTRAINTS_TITLE_MESSAGE = KMADeMain.messages
		.getString("CONSTRAINTS_TITLE_MESSAGE");
	SOURCE_TASK_EVENT_MESSAGE = KMADeMain.messages
		.getString("SOURCE_TASK_EVENT_MESSAGE");
	NO_USER_MESSAGE = KMADeMain.messages.getString("NO_USER_MESSAGE");
	EXECUTING_USER_TITLE_MESSAGE = KMADeMain.messages
		.getString("EXECUTING_USER_TITLE_MESSAGE");
	CONSTRAINTS_NAME_COLUMN_NAME = KMADeMain.messages
		.getString("CONSTRAINTS_NAME_COLUMN_NAME");
	CONSTRAINTS_VALEUR_COLUMN_NAME = KMADeMain.messages
		.getString("CONSTRAINTS_VALEUR_COLUMN_NAME");
	CONSTRAINTS_STATE_COLUMN_NAME = KMADeMain.messages
		.getString("CONSTRAINTS_STATE_COLUMN_NAME");
	PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE");
	PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE");
	PRECONDITION_CONSTRAINTS_ERROR_STATE_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_CONSTRAINTS_ERROR_STATE_MESSAGE");
	PRECONDITION_CONSTRAINTS_NEED_VALUES_STATE_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_CONSTRAINTS_NEED_VALUES_STATE_MESSAGE");
	EFFETSDEBORD_EXECUTED_STATE_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_EXECUTED_STATE_MESSAGE");
	EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE");
	USER_VALUES_TITLE = KMADeMain.messages.getString("USER_VALUES_TITLE");
	PRECONDITION_USER_EDITION_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_USER_EDITION_MESSAGE");
	EFFETSDEBORD_USER_EDITION_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_USER_EDITION_MESSAGE");
	ITERATION_USER_EDITION_MESSAGE = KMADeMain.messages
		.getString("ITERATION_USER_EDITION_MESSAGE");
	TRIGGER_VALID_TASK_MESSAGE = KMADeMain.messages
		.getString("TRIGGER_VALID_TASK_MESSAGE");
	NO_TRIGGER_CANCEL_TASK_MESSAGE = KMADeMain.messages
		.getString("NO_TRIGGER_CANCEL_TASK_MESSAGE");
	EXECUTE_TASK_TRAITEMENT_MESSAGE = KMADeMain.messages
		.getString("EXECUTE_TASK_TRAITEMENT_MESSAGE");
	EXECUTION_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("EXECUTION_CONSTRAINT_MESSAGE");
	USER_EXECUTION_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("USER_EXECUTION_CONSTRAINT_MESSAGE");
	CAN_EXECUTE_TASK_MESSAGE = KMADeMain.messages
		.getString("CAN_EXECUTE_TASK_MESSAGE");
	CAN_NOT_EXECUTE_TASK_MESSAGE = KMADeMain.messages
		.getString("CAN_NOT_EXECUTE_TASK_MESSAGE");
	EVENT_TRIGGER_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("EVENT_TRIGGER_CONSTRAINT_MESSAGE");
	NO_TRIGGER_EVENT_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("NO_TRIGGER_EVENT_CONSTRAINT_MESSAGE");
	CAN_TRIGGER_TASK_MESSAGE = KMADeMain.messages
		.getString("CAN_TRIGGER_TASK_MESSAGE");
	CAN_NOT_TRIGGER_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("CAN_NOT_TRIGGER_CONSTRAINT_MESSAGE");
	PRECONDITION_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_CONSTRAINT_MESSAGE");
	ACTION_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("ACTION_CONSTRAINT_MESSAGE");
	GENERATE_EVENTS_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("GENERATE_EVENTS_CONSTRAINT_MESSAGE");
	NO_GENERATED_EVENTS_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("NO_GENERATED_EVENTS_CONSTRAINT_MESSAGE");
	EFFETSDEBORD_CONSTRAINT_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_CONSTRAINT_MESSAGE");
	USER_VALUES_MISSING_OR_WRONG_MESSAGE = KMADeMain.messages
		.getString("USER_VALUES_MISSING_OR_WRONG_MESSAGE");
	CONCRETE_OBJECT_NO_SELECTED_MESSAGE = KMADeMain.messages
		.getString("CONCRETE_OBJECT_NO_SELECTED_MESSAGE");
	USER_VALUES_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("USER_VALUES_PROBLEM_MESSAGE");
	FINISHED_SCENARIO_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("FINISHED_SCENARIO_PROBLEM_MESSAGE");
	FINISHED_SIMULATION_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("FINISHED_SIMULATION_PROBLEM_MESSAGE");
	FINISHED_SCENARIO_AND_SIMULATION_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("FINISHED_SCENARIO_AND_SIMULATION_PROBLEM_MESSAGE");
	SELECT_TASK_TO_REPLAY_MESSAGE = KMADeMain.messages
		.getString("SELECT_TASK_TO_REPLAY_MESSAGE");
	ITERATION_FINISH_NO_ACTION = KMADeMain.messages
		.getString("ITERATION_FINISH_NO_ACTION");
	// Les actions sur les t�ches pour lors de la simulation
	EXECUTE_ACTION_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("EXECUTE_ACTION_SIMULATION_MESSAGE");
	PASS_ACTION_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("PASS_ACTION_SIMULATION_MESSAGE");
	SUSPEND_ACTION_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("SUSPEND_ACTION_SIMULATION_MESSAGE");
	RESUME_ACTION_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("RESUME_ACTION_SIMULATION_MESSAGE");
	NO_RESUME_ACTION_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("NO_RESUME_ACTION_SIMULATION_MESSAGE");

	// L'�tat des t�ches pour lors de la simulation.
	ACTIVE_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("ACTIVE_STATE_TASK_SIMULATION_MESSAGE");
	ACTIVE_ITERATION_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("ACTIVE_ITERATION_STATE_TASK_SIMULATION_MESSAGE");
	FINISHED_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("FINISHED_STATE_TASK_SIMULATION_MESSAGE");
	PASSIVE_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("PASSIVE_STATE_TASK_SIMULATION_MESSAGE");
	NO_ACCESSIBLE_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("NO_ACCESSIBLE_STATE_TASK_SIMULATION_MESSAGE");
	PASSED_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("PASSED_STATE_TASK_SIMULATION_MESSAGE");
	SUSPENDED_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("SUSPENDED_STATE_TASK_SIMULATION_MESSAGE");
	NO_RESUMED_STATE_TASK_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("NO_RESUMED_STATE_TASK_SIMULATION_MESSAGE");

	// Pour l'enregistrement de la simulation
	RECORD_TITLE_MESSAGE = KMADeMain.messages
		.getString("RECORD_TITLE_MESSAGE");
	RECORD_BUILDING_SCENARION_MESSAGE = KMADeMain.messages
		.getString("RECORD_BUILDING_SCENARION_MESSAGE");
	RECORD_AVAILABLE_ACTIONABLE_MESSAGE = KMADeMain.messages
		.getString("RECORD_AVAILABLE_ACTIONABLE_MESSAGE");
	FINISHED_RECORD_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("FINISHED_RECORD_SIMULATION_MESSAGE");
	NO_SELECTED_TASK_BEFORE_RECORD_SIMULATION_MESSAGE = KMADeMain.messages
		.getString("NO_SELECTED_TASK_BEFORE_RECORD_SIMULATION_MESSAGE");

	// Pour le rejeu de la simulation
	REPLAY_TITLE_MESSAGE = KMADeMain.messages
		.getString("REPLAY_TITLE_MESSAGE");
	REPLAY_SLOW_TEMPO = KMADeMain.messages.getString("REPLAY_SLOW_TEMPO");
	REPLAY_MODERATE_TEMPO = KMADeMain.messages
		.getString("REPLAY_MODERATE_TEMPO");
	REPLAY_SPEED_TEMPO = KMADeMain.messages.getString("REPLAY_SPEED_TEMPO");
	SCENARIO_REPLAY_MESSAGE = KMADeMain.messages
		.getString("SCENARIO_REPLAY_MESSAGE");
	REPLAY_OPTIONS_MESSAGE = KMADeMain.messages
		.getString("REPLAY_OPTIONS_MESSAGE");
	REPLAY_KEEP_USER_VALUES_MESSAGE = KMADeMain.messages
		.getString("REPLAY_KEEP_USER_VALUES_MESSAGE");
	REPLAY_TEMPO_MESSAGE = KMADeMain.messages
		.getString("REPLAY_TEMPO_MESSAGE");
	NO_ROOT_TASK_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("NO_ROOT_TASK_PROBLEM_MESSAGE");
	NO_FOUNDED_TASK_TO_REPLAY_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("NO_FOUNDED_TASK_TO_REPLAY_PROBLEM_MESSAGE");
	CAN_NOT_EXECUTE_TASK_TO_REPLAY_PROBLEM_MESSAGE = KMADeMain.messages
		.getString("CAN_NOT_EXECUTE_TASK_TO_REPLAY_PROBLEM_MESSAGE");

	CONFIG_ERROR_DOM = KMADeMain.messages.getString("CONFIG_ERROR_DOM");
	CALL_NEW_DOCUMENT_BUILDER = KMADeMain.messages
		.getString("CALL_NEW_DOCUMENT_BUILDER");
	SIMULATION_PARSING_ERROR = KMADeMain.messages
		.getString("SIMULATION_PARSING_ERROR");
	CALL_CONSTRUCTEUR_PARSE = KMADeMain.messages
		.getString("CALL_CONSTRUCTEUR_PARSE");
	SIMULATION_IO_ERROR = KMADeMain.messages
		.getString("SIMULATION_IO_ERROR");

	// Pour la boite A Propos de ...
	PROJECT_LEADER_MESSAGE = KMADeMain.messages
		.getString("PROJECT_LEADER_MESSAGE");
	DEVELOPMENT_QUERING_TOOL_MESSAGE = KMADeMain.messages
		.getString("DEVELOPMENT_QUERING_TOOL_MESSAGE");
	KMAD_SPECIFY_MESSAGE = KMADeMain.messages
		.getString("KMAD_SPECIFY_MESSAGE");
	KMAD_ADAPTATOR_MESSAGE = KMADeMain.messages
		.getString("KMAD_ADAPTATOR_MESSAGE");
	JAVA_GRAPHIC_LIBRARIES_MESSAGE = KMADeMain.messages
		.getString("JAVA_GRAPHIC_LIBRARIES_MESSAGE");
	KMADE_TEAM_MESSAGE = KMADeMain.messages.getString("KMADE_TEAM_MESSAGE");
	JAVA_LIBRARIES_MESSAGE = KMADeMain.messages
		.getString("JAVA_LIBRARIES_MESSAGE");

	// Event
	EVENT_TRIGGER = KMADeMain.messages.getString("EVENT_TRIGGER");
	EVENT_ENABLE = KMADeMain.messages.getString("EVENT_ENABLE");

	// Coh�rence du mod�le
	COHERENCE_INTRO_ERROR = KMADeMain.messages
		.getString("COHERENCE_INTRO_ERROR");
	COHERENCE_ONE_ERROR = KMADeMain.messages
		.getString("COHERENCE_ONE_ERROR");
	COHERENCE_SOME_ERROR = KMADeMain.messages
		.getString("COHERENCE_SOME_ERROR");
	COHERENCE_INTER = KMADeMain.messages.getString("COHERENCE_INTER");
	COHERENCE_ONE_WARNING = KMADeMain.messages
		.getString("COHERENCE_ONE_WARNING");
	COHERENCE_SOME_WARNING = KMADeMain.messages
		.getString("COHERENCE_SOME_WARNING");
	COHERENCE_VERIFICATION_TITLE = KMADeMain.messages
		.getString("COHERENCE_VERIFICATION_TITLE");

	NO_ALONE_MESSAGE_PROBLEM = KMADeMain.messages
		.getString("NO_ALONE_MESSAGE_PROBLEM");
	PRECONDITION_EXPRESSION_MESSAGE_PROBLEM = KMADeMain.messages
		.getString("PRECONDITION_EXPRESSION_MESSAGE_PROBLEM");
	EFFETSDEBORD_EXPRESSION_MESSAGE_PROBLEM = KMADeMain.messages
		.getString("EFFETSDEBORD_EXPRESSION_MESSAGE_PROBLEM");
	ITERATION_EXPRESSION_MESSAGE_PROBLEM = KMADeMain.messages
		.getString("ITERATION_EXPRESSION_MESSAGE_PROBLEM");
	NO_ONLY_ONE_SUBTASK_MESSAGE_PROBLEM = KMADeMain.messages
		.getString("NO_ONLY_ONE_SUBTASK_MESSAGE_PROBLEM");
	NO_DECOMPOSITION_SPECIFIED_MESSAGE_PROBLEM = KMADeMain.messages
		.getString("NO_DECOMPOSITION_SPECIFIED_MESSAGE_PROBLEM");
	ELEMENTARY_DECOMPOSITION_FOR_LEAF_TASK = KMADeMain.messages
		.getString("ELEMENTARY_DECOMPOSITION_FOR_LEAF_TASK");
	NO_EXECUTANT_SPECIFIED_MESSAGE_WARNING = KMADeMain.messages
		.getString("NO_EXECUTANT_SPECIFIED_MESSAGE_WARNING");
	SUBTASKS_EXECUTANT_DIFFERENT_MESSAGE_WARNING = KMADeMain.messages
		.getString("SUBTASKS_EXECUTANT_DIFFERENT_MESSAGE_WARNING");
	HIERARCHY_TYPE_MESSAGE = KMADeMain.messages
		.getString("HIERARCHY_TYPE_MESSAGE");
	EXPRESSION_TYPE_MESSAGE = KMADeMain.messages
		.getString("EXPRESSION_TYPE_MESSAGE");
	TASKS_SPACE_LOCATION_MESSAGE = KMADeMain.messages
		.getString("TASKS_SPACE_LOCATION_MESSAGE");
	PRECONDITION_LOCATION_MESSAGE = KMADeMain.messages
		.getString("PRECONDITION_LOCATION_MESSAGE");
	EFFETSDEBORD_LOCATION_MESSAGE = KMADeMain.messages
		.getString("EFFETSDEBORD_LOCATION_MESSAGE");
	ITERATION_LOCATION_MESSAGE = KMADeMain.messages
		.getString("ITERATION_LOCATION_MESSAGE");
	COHERENCE_TITLE_MESSAGE = KMADeMain.messages
		.getString("COHERENCE_TITLE_MESSAGE");
	COHERENCE_CHECK_MESSAGE = KMADeMain.messages
		.getString("COHERENCE_CHECK_MESSAGE");
	ERRORS_TITLE_MESSAGE = KMADeMain.messages
		.getString("ERRORS_TITLE_MESSAGE");
	MESSAGE_ERROR_COLUMN_NAME = KMADeMain.messages
		.getString("MESSAGE_ERROR_COLUMN_NAME");
	TASK_NAME_COLUMN_NAME = KMADeMain.messages
		.getString("TASK_NAME_COLUMN_NAME");
	ERROR_TYPE_COLUMN_NAME = KMADeMain.messages
		.getString("ERROR_TYPE_COLUMN_NAME");
	LOCATION_COLUMN_NAME = KMADeMain.messages
		.getString("LOCATION_COLUMN_NAME");

	// Statistique du mod�le
	COUNT_TASK_INTO_TASK_MODELS_MESSAGE = KMADeMain.messages
		.getString("COUNT_TASK_INTO_TASK_MODELS_MESSAGE");
	COUNT_USER_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_USER_TASK_MESSAGE");
	COUNT_UNKNOWN_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_UNKNOWN_TASK_MESSAGE");
	COUNT_SYSTEM_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_SYSTEM_TASK_MESSAGE");
	COUNT_ABSTRACT_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_ABSTRACT_TASK_MESSAGE");
	COUNT_INTERACT_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_INTERACT_TASK_MESSAGE");
	COUNT_UNKNOWN_DEC_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_UNKNOWN_DEC_TASK_MESSAGE");
	COUNT_ENABLING_DEC_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_ENABLING_DEC_TASK_MESSAGE");
	COUNT_CHOICE_DEC_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_CHOICE_DEC_TASK_MESSAGE");
	COUNT_CONCURRENT_DEC_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_CONCURRENT_DEC_TASK_MESSAGE");
	COUNT_ELEMENTARY_DEC_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_ELEMENTARY_DEC_TASK_MESSAGE");
	COUNT_NO_ORDER_DEC_TASK_MESSAGE = KMADeMain.messages
		.getString("COUNT_NO_ORDER_DEC_TASK_MESSAGE");
	STATISTIC_TITLE = KMADeMain.messages.getString("STATISTIC_TITLE");
	TYPE_STATISTIC_MESSAGE = KMADeMain.messages
		.getString("TYPE_STATISTIC_MESSAGE");
	RESULT_STATISTIC_MESSAGE = KMADeMain.messages
		.getString("RESULT_STATISTIC_MESSAGE");
	REFRESH_STATISTIC_MESSAGE = KMADeMain.messages
		.getString("REFRESH_STATISTIC_MESSAGE");

	// chargement kxml
	NO_VERSION = KMADeMain.messages.getString("NO_VERSION");

	NEW_SAVE_NEW_FORMAT = KMADeMain.messages
		.getString("NEW_SAVE_NEW_FORMAT");
	VERSION_USE = KMADeMain.messages.getString("VERSION_USE");
	VERSION_FILE = KMADeMain.messages.getString("VERSION_FILE");
	NOT_SAME_VERSION = KMADeMain.messages.getString("NOT_SAME_VERSION");

	OBJECTS_VISUALIZATION_WINDOW_NAME = "Objets KMADe";
	OPEN_ITEMS_FILE = "Charger un fichier d'objets";
	CAN_SAVE_PROJECT = "Vous pouvez sauver le projet ou les objets en cours";
	CAN_NOT_SAVE_PROJECT = "Vous ne pouvez pas sauver le projet ou les objets en cours";

	// Abstract objects
	EMPTY_CELL_NAME = KMADeMain.messages.getString("EMPTY_CELL_NAME");
	NEW_ABSTRACT_OBJECT_TEXT = KMADeMain.messages
		.getString("NEW_ABSTRACT_OBJECT_TEXT");
	REMOVE_ABSTRACT_OBJECT_TEXT = KMADeMain.messages
		.getString("REMOVE_ABSTRACT_OBJECT_TEXT");
	NEW_OBJECT_NAME = KMADeMain.messages.getString("NEW_OBJECT_NAME");
	NO_SELECTED_OBJECT = KMADeMain.messages.getString("NO_SELECTED_OBJECT");
	GENERALITY = KMADeMain.messages.getString("GENERALITY");
	ATTRIBUTES_NAME = KMADeMain.messages.getString("ATTRIBUTES_NAME");
	ADD_ATTRIBUTE = KMADeMain.messages.getString("ADD_ATTRIBUTE");
	REMOVE_ATTRIBUTE = KMADeMain.messages.getString("REMOVE_ATTRIBUTE");

	// Concrete objects
	EDIT_CONCRETE_OBJECT_ACTION_MESSAGE = KMADeMain.messages
		.getString("EDIT_CONCRETE_OBJECT_ACTION_MESSAGE");
	EDIT_OBJECTS_ACTION_MESSAGE = KMADeMain.messages
		.getString("EDIT_OBJECTS_ACTION_MESSAGE");
	OBJECT_INSERTION_ERROR = KMADeMain.messages
		.getString("OBJECT_INSERTION_ERROR");
	NEW_GROUP_NAME = KMADeMain.messages.getString("NEW_GROUP_NAME");
	NEW_STACK_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_STACK_ACTION_MESSAGE");
	NEW_SET_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_SET_ACTION_MESSAGE");
	NEW_ARRAY_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_ARRAY_ACTION_MESSAGE");
	NEW_LIST_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_LIST_ACTION_MESSAGE");
	NEW_SINGLETON_ACTION_MESSAGE = KMADeMain.messages
		.getString("NEW_SINGLETON_ACTION_MESSAGE");
	EDIT_GROUP_ACTION_MESSAGE = KMADeMain.messages
		.getString("EDIT_GROUP_ACTION_MESSAGE");
	EDIT_GROUP_AND_CONCRETE_ACTION_MESSAGE = KMADeMain.messages
		.getString("EDIT_GROUP_AND_CONCRETE_ACTION_MESSAGE");
	DELETE_GROUP_AND_CONCRETE_ACTION_MESSAGE = KMADeMain.messages
		.getString("DELETE_GROUP_AND_CONCRETE_ACTION_MESSAGE");

	// outil de prototypage

	PROTOTYPING_TOOL_TITLE_NAME = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_TITLE_NAME");
	PROTOTYPING_TOOL_RESET = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_RESET");
	PROTOTYPING_TOOL_TITLE_NAME = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_TITLE_NAME");
	PROTOTYPING_TOOL_RESET = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_RESET");
	PROTOTYPING_TOOL_CURRENT_TASK = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_CURRENT_TASK");
	PROTOTYPING_TOOL_NAME_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_NAME_TITLE");
	PROTOTYPING_TOOL_NAME_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_NAME_TOOLTIP");
	PROTOTYPING_TOOL_EXECUTANT_ABS = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_EXECUTANT_ABS");
	PROTOTYPING_TOOL_EXECUTANT_INCONNU = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_EXECUTANT_INCONNU");
	PROTOTYPING_TOOL_EXECUTANT_INT = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_EXECUTANT_INT");
	PROTOTYPING_TOOL_EXECUTANT_SYS = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_EXECUTANT_SYS");
	PROTOTYPING_TOOL_EXECUTANT_USER = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_EXECUTANT_USER");
	PROTOTYPING_TOOL_EXECUTANT_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_EXECUTANT_TOOLTIP");
	PROTOTYPING_TOOL_DECOMPOSITION_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_DECOMPOSITION_TITLE");
	PROTOTYPING_TOOL_PRECONDITION_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_PRECONDITION_TITLE");
	PROTOTYPING_TOOL_PRECONDITION_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_PRECONDITION_TOOLTIP");
	PROTOTYPING_TOOL_ITERATION_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_ITERATION_TITLE");
	PROTOTYPING_TOOL_ITERATION_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_ITERATION_TOOLTIP");
	PROTOTYPING_TOOL_DESCRIPTION_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_DESCRIPTION_TITLE");
	PROTOTYPING_TOOL_DESCRIPTION_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_RESET");
	PROTOTYPING_TOOL_SUBTASK_DECOMP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_SUBTASK_DECOMP");
	PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP1 = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP1");
	PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP2 = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP2");
	PROTOTYPING_TOOL_SUBTASK_TERMINAL_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_SUBTASK_TERMINAL_TOOLTIP");
	PROTOTYPING_TOOL_SUBTASK_DESCRIPTION_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_SUBTASK_DESCRIPTION_TOOLTIP");
	PROTOTYPING_TOOL_SUBTASK_PRECONDITION = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_SUBTASK_PRECONDITION");
	PROTOTYPING_TOOL_CANCEL_BUTTON = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_CANCEL_BUTTON");
	PROTOTYPING_TOOL_END_BUTTON = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_END_BUTTON");
	PROTOTYPING_TOOL_VALIDATE_END_BUTTON = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_VALIDATE_END_BUTTON");
	PROTOTYPING_TOOL_CONTROL_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_CONTROL_TITLE");
	PROTOTYPING_TOOL_ITERATION_BUTTON = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_ITERATION_BUTTON");
	PROTOTYPING_TOOL_MENU_TITLE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_MENU_TITLE");
	PROTOTYPING_TOOL_MENU_TOOLTIP = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_MENU_TOOLTIP");
	PROTOTYPING_TOOL_PRECONDITION_TEXT_PANE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_PRECONDITION_TEXT_PANE");
	PROTOTYPING_TOOL_PRECONDITION_TITLE_PANE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_PRECONDITION_TITLE_PANE");
	PROTOTYPING_TOOL_PASSIVE_INACTIVE_ERROR = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_PASSIVE_INACTIVE_ERROR");
	PROTOTYPING_TOOL_END_ERROR = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_END_ERROR");
	PROTOTYPING_TOOL_TRUE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_TRUE");
	PROTOTYPING_TOOL_FALSE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_FALSE");
	PROTOTYPING_TOOL_INDETERMINATE = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_INDETERMINATE");
	PROTOTYPING_TOOL_HISTORY = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_HISTORY");
	PROTOTYPING_TOOL_HISTORY_DESCRIPTION = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_HISTORY_DESCRIPTION");
	PROTOTYPING_TOOL_DURATION = KMADeMain.messages
		.getString("PROTOTYPING_TOOL_DURATION");

	COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU = KMADeMain.messages
		.getString("COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU");
	DELETE_CELL_ACTION_MESSAGE_PIE_MENU = KMADeMain.messages
		.getString("DELETE_CELL_ACTION_MESSAGE_PIE_MENU");

	PRECONDITION_TAB_FORMAL = KMADeMain.messages
		.getString("PRECONDITION_TAB_FORMAL");
	PRECONDITION_TAB_PROTOTASK = KMADeMain.messages
		.getString("PRECONDITION_TAB_PROTOTASK");
	PROTOTASK_CONDITION_DESCRIPTION = KMADeMain.messages
		.getString("PROTOTASK_CONDITION_DESCRIPTION");
	PROTOTASK_CONDITION_DEFAULT_VALUE = KMADeMain.messages
		.getString("PROTOTASK_CONDITION_DEFAULT_VALUE");
	CONDITION_WORLD_TITLE = KMADeMain.messages
		.getString("CONDITION_WORLD_TITLE");
	CONDITION_ADD_MESSAGE = KMADeMain.messages
		.getString("CONDITION_ADD_MESSAGE");
	CONDITION_REMOVE_NAME_TITLE = KMADeMain.messages
		.getString("CONDITION_REMOVE_NAME_TITLE");

	PROTOTASK_CONDITION_MENU = KMADeMain.messages
		.getString("PROTOTASK_CONDITION_MENU");
	PROTOTASK_CONDITION_MENU_ITEM1 = KMADeMain.messages
		.getString("PROTOTASK_CONDITION_MENU_ITEM1");
	PROTOTASK_HISTORIQUE_MENU = KMADeMain.messages
		.getString("PROTOTASK_HISTORIQUE_MENU");
	PROTOTASK_HISTORIQUE_MENU_ITEM1 = KMADeMain.messages
		.getString("PROTOTASK_HISTORIQUE_MENU_ITEM1");
	PROTOTASK_HISTORIQUE_MENU_ITEM2 = KMADeMain.messages
		.getString("PROTOTASK_HISTORIQUE_MENU_ITEM2");
	PROTOTASK_CONDITION_FRAME_TITLE = KMADeMain.messages
		.getString("PROTOTASK_CONDITION_FRAME_TITLE");
	PROTOTASK_HISTORIQUE_FRAME_TITLE = KMADeMain.messages
		.getString("PROTOTASK_HISTORIQUE_FRAME_TITLE");
	PROTOTASK_END_TITLE = KMADeMain.messages
		.getString("PROTOTASK_END_TITLE");
	PROTOTASK_END_LABEL = KMADeMain.messages
		.getString("PROTOTASK_END_LABEL");
	PROTOTASK_CONDITION_LABEL = KMADeMain.messages
		.getString("PROTOTASK_CONDITION_LABEL");
	PROTOTASK_TASK_LABEL = KMADeMain.messages
		.getString("PROTOTASK_TASK_LABEL");
	PROTOTASK_REPEAT_BUTTON = KMADeMain.messages
		.getString("PROTOTASK_REPEAT_BUTTON");
	PROTOTASK_VALIDATE_BUTTON = KMADeMain.messages
		.getString("PROTOTASK_VALIDATE_BUTTON");

	PROTOTASK_TOOL_MENU_TITLE = KMADeMain.messages
		.getString("PROTOTASK_TOOL_MENU_TITLE");
	PROTOTASK_TOOL_MENU_TOOLTIP = KMADeMain.messages
		.getString("PROTOTASK_TOOL_MENU_TOOLTIP");
    }
}
