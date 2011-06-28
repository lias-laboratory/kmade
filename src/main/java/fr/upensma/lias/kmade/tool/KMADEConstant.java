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

    // Couleur pour la sélection dans les JTable
    public static final Color ACTIVE_SELECTION = new Color(186, 219, 243); // Color.blue;

    public static Color ACTIVE_SELECTION_FONT_COLOR = Color.black;

    // Couleur pour les Containers
    public static final Color ACTIVE_PANE = Color.white;

    // Couleur pour le nom d'une tâche
    public static Color TASK_NAME_COLOR = Color.black;

    // Couleur pour le nom de l'opération
    public static Color TASK_OPERATOR_COLOR = Color.black;

    // Fontes

    public static final Font fontACTIF = new Font("Tahoma", Font.BOLD, 12);

    public static final Font fontPASSIF = new Font("Tahoma", Font.PLAIN, 10);

    public static final Font TASK_NUM_FONT = new Font("arial", Font.BOLD, 10);

    public static Font TASK_NAME_FONT = new Font("arial", Font.BOLD, 15);

    public static Font TASK_OPERATOR_FONT = new Font("arial", Font.BOLD, 10);

    public static final int ROW_HEIGHT = 35;

    // Task model appearance

    // Hauteur des niveaux
    public static int LEVEL_DISTANCE = 130;

    // Distance entre les tâches
    public static int TASK_DISTANCE = 60;

    // Couleur de la sélection d'une tâche
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
     * Modifie la police de l'opérateur de la tache
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

    // Les mots affichés sur une tâche dans l'espace de tâche
    public static String VERTEX_NUM = "N:";
    public static String VERTEX_PRECONDITION = "PRE";
    public static String VERTEX_ITERATION = "ITER";
    public static String VERTEX_OPT = "OPT";
    public static String VERTEX_INTERRUPTIBLE = "INTER";
    public static String VERTEX_ACTOR = "ACTEUR";
    public static String VERTEX_EFFETSDEBORD = "ACTION";
    public static String VERTEX_OBJET = "OBJET";
    public static String VERTEX_ITER_LETTER = "I:";
    public static String VERTEX_STATE_LETTER = "S:";
    public static String VERTEX_EVENT_IN_LETTER = "IN:";
    public static String VERTEX_EVENT_OUT_LETTER = "OUT:";
    //
    // Les constantes liées aux titres et autres chaines de caractères.
    //
    public static String FENETRE_PRINCIPALE_TITLE_NAME = "KMADe (Kernel of Model for Activity Description environment)";
    public static String ABOUT_TITLE_NAME = "A Propos de "
	    + KMADEToolConstant.TOOL_NAME;
    public static String TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME = "Espace Tâches";
    public static String ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME = "Objets Abstraits";
    public static String USER_TABBEDPANE_TITLE_NAME = "Acteurs potentiels";
    public static String INDIVIDU_TABBEDPANE_TITLE_NAME = "Individus";
    public static String ORGANIZATION_TABBEDPANE_TITLE_NAME = "Organisations";
    public static String MACHINE_TABBEDPANE_TITLE_NAME = "Machines";
    public static String PARCMACHINES_TABBEDPANE_TITLE_NAME = "Parc de machines";
    public static String EVENT_TABBEDPANE_TITLE_NAME = "Evénements";
    public static String CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME = "Objets Concrets";
    public static String PREVIEW_WINDOW_TITLE_NAME = "Fenêtre d'aperçu";
    public static String INFO_DEBUG_TITLE_NAME = "Informations de développement";
    public static String HELP_KMADE_TITLE_NAME = "Aide sur l'outil "
	    + KMADEToolConstant.TOOL_NAME;
    public static String HELP_KMAD_TITLE_NAME = "Aide sur le modèle K-MAD";
    public static String HISTORY_TITLE_NAME = "Historique de l'outil KMADe";
    public static String ABSTRACT_OBJECT_TITLE_NAME = "Editeur Objets Abstraits";
    public static String CONCRETE_OBJECT_TITLE_NAME = "Editeur Objets Concrets";
    public static String USER_TITLE_NAME = "Editeur Utilisateurs";
    public static String INDIVIDU_TITLE_NAME = "Editeur Individu";
    public static String ORGANIZATION_TITLE_NAME = "Editeur Organisation";
    public static String MACHINE_TITLE_NAME = "Editeur Machine";
    public static String PARCMACHINES_TITLE_NAME = "Editeur Parc de machines";
    public static String EVENT_TITLE_NAME = "Editeur Evénements";
    public static String TASK_MODEL_TITLE_NAME = "Arbre de Tâches";
    public static String PREFERENCES_TITLE_NAME = "Préférences";
    public static String COMPLETE_EDITOR_TITLE_NAME = "Editeur complet de tâches";
    public static String CLIPBOARD_TITLE_NAME = "Aperçu du Presse-Papier";
    public static String LOAD_MONITOR_TITLE_NAME = "Moniteur de chargement de projet K-MAD";
    public static String SAVE_MONITOR_TITLE_NAME = "Moniteur d'enregistrement de projet K-MAD";
    public static String EDITOR_TEXT_TITLE_NAME = "Editeur du contenu de l'attribut";
    public static String EVENT_TASK_LINKED_TITLE_NAME = "Liaison Tache/Evénement";
    public static String ACTOR_CREATE_TITLE_NAME = "Création des acteurs";
    public static String ACTORSYSTEM_CREATE_TITLE_NAME = "Création des acteurs systèmes";
    public static String PRINT_PREVIEW_WINDOW_TITLE_NAME = "Aperçu d'impression";
    public static String PROJECT_DIALOG_TITLE_NAME = "Gestion des interviews";

    // Les affichages des boites de dialogue d'erreur de nom

    public static String BAD_CHARACTER_TEXT = "Erreur d'écriture dans le nom";
    public static String BAD_CARACTER_TITLE = "Nom incorrect";
    public static String SAME_NAME_TEXT = "Nom Identique";
    public static String SAME_NAME_TITLE = "Le nom est déjé utilisé, renommer en :";
    // Abstract Object
    public static String BACK_TO_EDITOR = "Retour vers l'éditeur de";
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
    public static String ABSTRACT_ATTRIBUT_ENUMERATE_EDIT_TABLE = "Editer énuméré ...";
    public static String ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE = "Editer intervalle ...";
    public static String ABSTRACT_ATTRIBUT_REMOVE_MESSAGE_TITLE = "Suppression d'attributs abstraits";

    // Enumeration
    public static String ENUMERATION_NAME_TABLE = "Nom énuméré";
    public static String ENUMERATION_DESCRIPTION_TABLE = "Description";
    public static String ENUMERATION_NEW_OBJECT_TABLE = "Ajouter un énuméré ...";
    public static String ENUMERATION_TITLE_TABLE = "Enumération";
    public static String ENUMERATION_REMOVE_MESSAGE_TITLE = "Suppression d'énumérés";

    // Element
    public static String ELEMENT_NAME_TABLE = "Nom";
    public static String ELEMENT_NEW_OBJECT_TABLE = "Ajouter un élément ...";
    public static String ELEMENT_REMOVE_MESSAGE_TITLE = "Suppression d'éléments d'un énuméré";

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
    public static String CONCRETE_ATTRIBUT_NOINIT_TABLE = "Attribut non initialisé";
    public static String CONCRETE_ATTRIBUT_TITLE_TABLE = "Attribut concret de";

    // Evenement
    public static String EVENT_NAME_TABLE = "Nom";
    public static String EVENT_DESCRIPTION_TABLE = "Description";
    public static String EVENT_NEW_ROW_TABLE = "Ajouter un événement ...";
    public static String EVENT_NONEW_ROW_TABLE = "Ajout impossible car pas d'événement disponible";
    public static String EVENT_REMOVE_INTO_TASK = "Suppression d'événements d'une tâche";
    public static String EVENT_REMOVE_NAME_TITLE = "Suppression d'événements";
    public static String EVENT_NO_ELEMENT_INTO_TASK = "Pas d'événement associé";

    // Utilisateur
    public static String USER_NAME_TABLE = "Nom";
    public static String USER_STATUS_TABLE = "Statut";
    public static String USER_ROLE_TABLE = "Rôle";
    public static String USER_PHOTO_TABLE = "Photo";
    public static String USER_MEMBERS = "Membre(s)";
    public static String USER_NEW_ROW_TABLE = "Ajouter un utilisateur ...";
    public static String USER_REMOVE_NAME_TITLE = "Suppression d'utilisateurs";
    public static String USER_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String USER_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String USER_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String USER_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image Défaut";
    public static String USER_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erroné";
    public static String USER_CLEAR_IMAGE_MESSAGE = "Effacer Image";

    // Individu

    public static String INDIVIDU_NAME_TABLE = "Nom";
    public static String INDIVIDU_STATUS_TABLE = "Statut";
    public static String INDIVIDU_ROLE_TABLE = "Rôle";
    public static String INDIVIDU_PHOTO_TABLE = "Photo";
    public static String INDIVIDU_NEW_ROW_TABLE = "Ajouter un individu";
    public static String INDIVIDU_REMOVE_NAME_TITLE = "Suppression d'individus";
    public static String INDIVIDU_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String INDIVIDU_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String INDIVIDU_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String INDIVIDU_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image Défaut";
    public static String INDIVIDU_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erroné";
    public static String INDIVIDU_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String INDIVIDU_ORGANIZATION_TABLE = "Organisation";
    public static String INDIVIDU_OTHERS = "Autres";
    // ORGANIZATION

    public static String ORGANIZATION_NAME_TABLE = "Nom";
    public static String ORGANIZATION_STATUS_TABLE = "Statut";
    public static String ORGANIZATION_ROLE_TABLE = "Rôle";
    public static String ORGANIZATION_PHOTO_TABLE = "Logo";
    public static String ORGANIZATION_NEW_ROW_TABLE = "Ajouter un organisation";
    public static String ORGANIZATION_REMOVE_NAME_TITLE = "Suppression d'organisation";
    public static String ORGANIZATION_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String ORGANIZATION_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String ORGANIZATION_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String ORGANIZATION_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image Défaut";
    public static String ORGANIZATION_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erroné";
    public static String ORGANIZATION_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String ORGANIZATION_MEMBERS_TABLE = "Membres";
    public static String ORGANIZATION_OTHERS = "Autres";

    // Machine

    public static String MACHINE_NAME_TABLE = "Nom";
    public static String MACHINE_DESCRIPTION_TABLE = "Description";
    public static String MACHINE_ISCOMPUTER_TABLE = "est informatisé";
    public static String MACHINE_IMAGE_TABLE = "image";
    public static String MACHINE_NEW_ROW_TABLE = "Ajouter un machine";
    public static String MACHINE_REMOVE_NAME_TITLE = "Suppression de machines";
    public static String MACHINE_CHOOSE_IMAGE_MESSAGE = "Choisir ...";
    public static String MACHINE_DISPLAY_IMAGE_MESSAGE = "Afficher Image";
    public static String MACHINE_DISPLAY_PATH_IMAGE_MESSAGE = "Afficher Chemin";
    public static String MACHINE_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image Défaut";
    public static String MACHINE_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erroné";
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
    public static String PARCMACHINES_DISPLAY_DEFAULT_IMAGE_MESSAGE = "Afficher Image Défaut";
    public static String PARCMACHINES_DISPLAY_BAD_PATH_IMAGE_MESSAGE = "Afficher Chemin Erroné";
    public static String PARCMACHINES_CLEAR_IMAGE_MESSAGE = "Effacer Image";
    public static String PARCMACHINES_MACHINE_TABLE = "Machines";
    public static String PARCMACHINES_OTHERS = "Autres";

    // Label
    public static String LABEL_TITLE_MESSAGE = "Libellé";
    public static String LABEL_REMOVE_NAME_TITLE = "Suppression de libellés";
    public static String LABEL_NUMERO_TITLE = "Numéro";
    public static String LABEL_NAME_TITLE = "Nom";
    public static String LABEL_DESCRIPTION_TITLE = "Description";
    public static String LABEL_COLOR_TITLE = "Couleur";
    public static String LABEL_IS_VISIBLE_COLOR_TITLE = "Couleur Visible";
    public static String LABEL_IS_VISIBLE_TITLE = "Visible";
    public static String LABEL_ADD_NEW_LABEL_TITLE = "Ajouter un libellé ...";
    public static String LABEL_EDITOR_TITLE = "Editeur Libellé";

    // Acteur
    public static String ACTOR_NAME_TABLE = "Nom";
    public static String ACTOR_EXPERIENCE_TABLE = "Expêrience";
    public static String ACTOR_COMPETENCE_TABLE = "Compétence";
    public static String ACTOR_NEW_ROW_TABLE = "Ajouter un acteur ...";
    public static String ACTOR_NONEW_ROW_TABLE = "Ajout impossible d'acteur car pas d'utilisateur disponible";
    public static String ACTOR_NO_ELEMENT_INTO_TASK = "Pas d'acteur associé";
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

    // Propriétés des tâches
    public static String NO_SELECTED_TASK_MESSAGE = "Pas de tâche Sélectionnée";
    public static String PROPERTIES_MESSAGE = "Caractéristiques";
    public static String GENERAL_TABBEDPANE_TITLE_NAME = "Général";
    public static String NO_VALUE_LEGEND_MESSAGE = "pas de valeur";
    public static String NO_CARACTERISTIC_SELECTED_MESSAGE = "Caractéristique non sélectionnée";
    public static String ERROR_CARACTERISTIC_MESSAGE = "Erreur";

    // Informations Générales des propriétés d'une tâches
    public static String TASK_ATTRIBUT_NAME = "Tâche";
    public static String GENERAL_INFORMATION_PANEL_TITLE_NAME = "Générales";
    public static String GENERAL_INFORMATION_PANEL_LEGEND_TITLE_NAME = "Les caractéristiques de type générales sont les premières renseignées et sont prévues pour être assimilées par l'ensemble des intervenants de l'analyse d'une activité. Ces caractéristiques sont informelles et ne font pas références à des objets de l'état du monde";
    public static String TASK_NUMBER_NAME_TITLE = "Numéro";
    public static String TASK_NUMBER_SHORT_NAME_TITLE = "Num";
    public static String TASK_NUMBER_NAME_LEGEND_TITLE = "Numéro";
    public static String NO_MOTHER_TASK_NAME_MESSAGE = "Pas de tâche mère";
    public static String SUB_TASK_PARENT_NAME_TITLE = "Sous-tâche";
    public static String SUB_TASK_PARENT_NAME_LEGEND_TITLE = "Sous-tâche";
    public static String TASK_NAME_TITLE = "Nom";
    public static String TASK_NAME_LEGEND_TITLE = "Nom";
    public static String TASK_PURPOSE_TITLE = "But";
    public static String TASK_PURPOSE_LEGEND_TITLE = "But";
    public static String TASK_MULTIMEDIA_PURPOSE_TITLE = "Multimédia";
    public static String TASK_MULTIMEDIA_PURPOSE_LEGEND_TITLE = "Multimédia";
    public static String TASK_MULTIMEDIA_ERROR_FILE_MESSAGE = "Problème : média introuvable";
    public static String TASK_LABEL_TITLE = "Libellé";
    public static String TASK_LABEL_LEGEND_TITLE = "Libellé";
    public static String TASK_OBJECTS_TITLE = "Objets";
    public static String TASK_OBJECTS_LEGEND_TITLE = "Objets";
    public static String TASK_FEEDBACK_TITLE = "Affichage";
    public static String TASK_FEEDBACK_LEGEND_TITLE = "Affichage";
    public static String TASK_DURATION_TITLE = "Durée";
    public static String TASK_DURATION_LEGEND_TITLE = "Durée";
    public static String TASK_OBSERVATION_TITLE = "Observation";
    public static String TASK_OBSERVATION_LEGEND_TITLE = "Observation";
    public static String EXECUTING_NAME_TITLE = "Exécutant";
    public static String EXECUTING_NAME_LEGEND_TITLE = "Exécutant";
    public static String MODALITY_NAME_TITLE = "Modalité";
    public static String MODALITY_NAME_LEGEND_TITLE = "Modalité";
    public static String FREQUENCY_NAME_TITLE = "Fréquence";
    public static String FREQUENCY_NAME_LEGEND_TITLE = "Fréquence";
    public static String FREQUENCY_VALUE_NAME_TITLE = "Valeur Fréquence";
    public static String FREQUENCY_VALUE_NAME_LEGEND_TITLE = "Valeur Fréquence";
    public static String IMPORTANT_NAME_TITLE = "Importance";
    public static String IMPORTANT_NAME_LEGEND_TITLE = "Importance";
    public static String SENSORI_MOTRICE_NAME_TITLE = "Sensori-motrice";
    public static String COGNITIVE_NAME_TITLE = "Cognitive";

    // Ordonnancement d'une tâche
    public static String SCHEDULE_PANEL_TITLE_NAME = "Ordonnancement";
    public static String NECESSITE_LABEL_NAME = "Nécessité";
    public static String NECESSITE_LABEL_LEGEND_NAME = "Nécessité";
    public static String INTERRUPTIBLE_LABEL_NAME = "Interruptible";
    public static String INTERRUPTIBLE_LABEL_LEGEND_NAME = "Interruptible";
    public static String DECLENCHEMENT_LABEL_NAME = "Déclenchement";
    public static String DECLENCHEMENT_LABEL_LEGEND_NAME = "Déclenchement";
    public static String UTILISATEUR_LABEL_NAME = "Acteur(s)";
    public static String UTILISATEUR_LABEL_LEGEND_NAME = "Utilisateurs";
    public static String SCHEDULE_LABEL_NAME = "Décomposition";
    public static String SCHEDULE_LABEL_LEGEND_NAME = "Décomposition";
    public static String OPTIONAL_NECESSITE_VALUE = "Optionnelle";
    public static String NO_OPTIONAL_NECESSITE_VALUE = "Obligatoire";
    public static String INTERRUPTIBLE_VALUE = "Interruptible";
    public static String NO_INTERRUPTIBLE_VALUE = "Non interruptible";
    public static String PRECONDITION_LABEL_VALUE = "Précondition";
    public static String PRECONDITION_LABEL_LEGEND_VALUE = "Précondition";
    public static String ITERATION_LABEL_VALUE = "Itération";
    public static String ITERATION_LABEL_LEGEND_VALUE = "Itération";
    public static String MATERIEL_LABEL_NAME = "Acteur(s) Système(s)";
    public static String MATERIEL_LABEL_LEGEND = "Materiel";

    // Effets d'une tâche
    public static String POSTCONDITION_PANEL_TITLE_NAME = "Actions";
    public static String EFFETSDEBORD_LABEL_VALUE = "EffetsDeBord";
    public static String EFFETSDEBORD_LABEL_LEGEND_VALUE = "EffetsDeBord";
    public static String EVENT_NAME_TITLE;
    public static String EVENT_NAME_LEGEND_TITLE = "Evénement";

    // Pour l'outil lié au multimédia
    public static String MEDIA_FILE_NAME = "Fichier média";
    public static String MEDIA_FILE_LENGTH = "Taille média (ko)";
    public static String MEDIA_LENGTH = "Durée média (m:s)";
    public static String MEDIA_START_TAG = "Repére départ (m:s)";
    public static String MEDIA_FINISH_TAG = "Repère fin (m:s)";
    public static String MEDIA_NO_INFORMATION_PANEL_MESSAGE = "Aucune Information";
    public static String INFORMATION_MEDIA_TITLE_NAME = "Informations";
    public static String MEDIA_TITLE_NAME = "Média";
    public static String NO_MEDIA_FILE_MESSAGE = "Aucun fichier média";
    public static String QUICK_TIME_NO_INSTALLED_ERROR_MESSAGE = "Pas de prise en compte vidéo : Installer QuickTime";
    public static String CONTROLE_MEDIA_TITLE_NAME = "Contrôle";

    // Editeur complet d'une tâche
    public static String EXECUTION_CONDITION_TITLE_NAME = "Conditions d'éxécution";
    public static String OBJECT_LIST_TITLE_NAME = "Liste des objets";
    public static String POSTCONDITION_TITLE_NAME = "Postconditions";
    public static String EFFETSDEBORD_TITLE_NAME = "Effets de bord";
    public static String NO_MOTHER_TASK_MESSAGE = "Aucune tâche mère";
    public static String NO_LEFT_TASK_MESSAGE = "Aucune tâche à gauche";
    public static String NO_RIGHT_TASK_MESSAGE = "Aucune tâche à droite";
    public static String NO_SUB_TASK_MESSAGE = "Aucune sous-tâche";
    public static String NO_NUMERO_TASK = "Pas de numéro";

    // Caractéristique d'un projet
    public static String INTERVIEWED_NAME_TABLE = "Interviewé";
    public static String PLACE_INFORMATION_TABLE = "Coordonnées";
    public static String STATUT_TABLE = "Statut";
    public static String SENIORITY_TABLE = "Ancienneté";
    public static String DATE_TABLE = "Date";
    public static String INTERVIEW_TYPE_TABLE = "Type de recueil";
    public static String SEARCHED_INFORMATIONS_TABLE = "Informations";
    public static String INTERVIEW_NAME_TABLE = "Interviewer";
    public static String INTERVIEW_NEW_OBJECT_TABLE = "Nouvel interview";
    public static String GENERAL_DESCRIPTION_PROJECT_DIALOG_TITLE = "Description générale";
    public static String COMPANY_LABEL_NAME = "Entreprise";
    public static String SITE_LABEL_NAME = "Site";
    public static String POSTE_TYPE_LABEL_NAME = "Type de Poste";
    public static String DATE_STUDY = "Date du cas étudié";
    public static String OTHER_RESOURCES = "Autres Resources";
    public static String INTERVIEWED_DESCRIPTION_PROJECT_DIALOG_TITLE = "Description des interviewés";
    public static String JUSTIFICATION_PROJECT_DIALOG_TITLE = "Justification de l'étude de cas (en quoi ce cas reflète un problème de charge)";
    public static String REMOVE_INTERVIEW_MESSAGE_TITLE = "Suppression d'interviews";
    public static String ASK_REMOVE_INTERVIEW_MESSAGE_TITLE = "Souhaitez-vous supprimer l'interview de";

    // Editeur des attributs d'une tâche
    public static String EDITOR_MESSAGE_TITLE = "Editeur de caractéristiques";
    public static String EDITOR_OPERATOR_NAME = "Décomposition";
    public static String EDITOR_NAME_NAME = "Nom";
    public static String EDITOR_EXECUTANT_NAME = "Exécutant";

    // Gestion du projet
    public static String EXTENSION_EXPRESS_FILTER_NAME = "Fichier Express (*.spf)";
    public static String EXTENSION_KMADE_FILTER_NAME = "Fichier K-MAD (*.kxml)";
    public static String SET_EXISTING_PROJECT_LONG_MESSAGE = "Modification des propriétés du projet";
    public static String SET_EXISTING_PROJECT_SHORT_MESSAGE = "Propriétés";
    public static String STATE_LOAD_TITLE_NAME = "Etat";
    public static String ELEMENT_PARSE_PROBLEM_MESSAGE = "Problème avec l'élément";
    public static String XML_PARSER_PROBLEM_MESSAGE = "Problème avec le ParserXML";
    public static String XML_PARSER_MISSING_ELEMENT_PROBLEM_MESSAGE = "entités manquantes dans le fichier XML";

    //Gestion des objets
    public static String OPEN_OBJECT_ACTION_MESSAGE = "Charger des objets";
    public static String SAVE_OBJECT_ACTION_MESSAGE = "Enregistrer les objets";
    public static String SAVE_OBJECT_AS_ACTION_MESSAGE = "Enregistrer les objets sous ...";
    public static String WRITE_BEFORE_OPEN_OBJECTS_MESSAGE = "Enregistrer les objets avant l'ouverture d'objets existant";
    
    // Gestion du chargement
    public static String OPEN_CANCELLED_EXPRESS_FILECHOOSER_NAME = "Chargement d'un projet MDA annulé";
    public static String OPEN_ERROR_FILE = "Erreur d'ouverture";
    public static String OPEN_EXPRESS_OK_FILE = "Chargement réussi : ";
    public static String OPEN_EXPRESS_NO_OK_FILE = "Chargement échoué : ";
    public static String EXPRESS_OBJECTS_TITLE_MESSAGE = "Objets de l'utilisateur";
    public static String GRAPHICAL_OBJECTS_MESSAGE = "Objets graphiques";
    public static String CREATE_GRAPHICAL_TASKS_MESSAGE = "Création des tâches graphiques";
    public static String CREATE_GRAPHICAL_EDGES_MESSAGE = "Création des liens";
    public static String LOAD_PROJECT_FINISHED_MESSAGE = "Chargement terminé";
    public static String LOAD_CONSOLE_TITLE_NAME = "Console de chargement";
    public static String IMPLICIT_STOP_LOAD_SPF_FILE = "Chargement stoppé : Problème dans la création des objets de l'utilisateur";
    public static String EXPLICIT_STOP_LOAD_SPF_FILE_DURING_OBJECT = "Chargement annulé : Suppression des objets Express";
    public static String EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT = "Chargement annulé : Suppression des objets Express et Graphiques";

    // Gestion de la sauvegarde
    public static String SAVE_CANCELLED_EXPRESS_FILECHOOSER_NAME = "Enregistrement du projet K-MAD courant annulé";
    public static String WRITE_EXPRESS_ERROR_FILE = "Erreur d'enregistrement du projet K-MAD : ";
    public static String WRITE_ERROR_FILE = "Erreur d'enregistrement d'un projet K-MAD";
    public static String WRITE_EXPRESS_OK_FILE = "Enregistrement du projet K-MAD réussi : ";
    public static String WRITE_EXPRESS_NO_OK_FILE = "Enregistrement du projet K-MAD échoué : ";
    public static String WRITE_BEFORE_OPEN_PROJECT_MESSAGE = "Enregistrer le projet courant avant l'ouverture d'un projet existant";
    public static String WRITE_BEFORE_EXIT_TOOL_MESSAGE = "Enregistrer le projet courant avant de quitter";
    public static String WRITE_BEFORE_NEW_PROJECT_MESSAGE = "Enregistrer le projet courant \n avant de créer un nouveau projet";
    public static String WRITE_BEFORE_CLOSE_PROJECT_MESSAGE = "Enregistrer le projet courant \n avant de le fermer";
    public static String WRITE_BEFORE_CHANGE_LOCALE_MESSAGE = "Enregistrer le projet avant \n de changer la localisation";
    public static String SAVE_CONSOLE_TITLE_NAME = "Console d'enregistrement";
    public static String SAVE_PROJECT_FINISHED_MESSAGE = "Enregistrement terminé";
    public static String IMPLICIT_STOP_SAVE_FILE_MESSAGE = "Enregistrement stoppé : Problème dans l'enregistrement des objets de l'utilisateur";
    public static String EXPLICIT_STOP_SAVE_FILE_MESSAGE = "Enregistrement annulé : Suppression des objets K-MAD";

    // Outil pour lister les entités
    public static String ENTITY_LIST_DIALOG_TITLE = "Outil d'introspection de l'état du monde";
    public static String FORCE_ENTITY_LIST_ACTION_MESSAGE = "Forcer Affichage";

    // Préférences de l'outil
    public static String GENERAL_TABBED_NAME = "Général";
    public static String TASK_MODEL_EDITOR_TABBED_NAME = "Tâches";
    public static String TASK_MODEL_EDITOR_TABBED_LONG_NAME = "Editeur de Tâches";
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
    public static String TASK_PANEL_NAME_BACKGROUND_COLOR_LABEL = "Couleur d'arrière plan : ";
    public static String TASK_PANEL_DECOMPOSITION_PANEL_NAME = "Décomposition";

    public static String TASK_PANEL_MODEL_PANEL_NAME = "Style Prédéfini";
    public static String TASK_PANEL_MODEL_CHOICE_LABEL = "Choix :";
    public static String TASK_PANEL_MODEL_SAVE_BUTTON_LABEL = "Sauvegarde";
    public static String TASK_PANEL_MODEL_LOAD_BUTTON_LABEL = "Chargement";
    public static String TASK_PANEL_OPTIONS_PANEL_NAME = "Options";
    public static String TASK_PANEL_OPTIONS_TRIGGER_EVENT_LABEL = "Indiquer les tâches déclencheuses d'événements";
    public static String TASK_PANEL_OPTIONS_ACTOR_TASK_LABEL = "Indiquer l'acteur de la tâche";
    public static String TASK_PANEL_PREVIEW_PANEL_NAME = "Aperçu";

    public static String TASK_TREE_PANEL_LAYOUT_PANEL_NAME = "Agencement";
    public static String TASK_TREE_PANEL_LAYOUT_ORIENTATION_LABEL = "Orientation de l'arbre";
    public static String TASK_TREE_PANEL_LAYOUT_DISTANCE_LABEL = "Distance entre les noeuds";
    public static String TASK_TREE_PANEL_LAYOUT_SELECTION_COLOR_LABEL = "Couleur de sélection d'une tâche";
    public static String TASK_TREE_PANEL_LAYOUT_LEVEL_DISTANCE_LABEL = "Hauteur des liens";
    public static String TASK_TREE_PANEL_OPTIONS_PANEL_NAME = "Options";
    public static String TASK_TREE_PANEL_OPTIONS_ORTHOGONAL_EDGE_LABEL = "Activer l'orthogonalité des liens";

    // temps (en millisecondes) d'attente avec l'affichage du curseur de grab
    public static long TEMPORISATION_GRAB = 1000;

    // distance (en pixel) servant pour afficher le trait "aimant"
    public static int DISTANCE_AIMANT = 20;

    // Clés des properties
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
    public static String FRENCH_LANGUAGE_INFO = "Français";
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
    public static String PROJECT_MENU_MESSAGE = "Projet";
    public static String HELP_MENU_MESSAGE = "Aide";
    public static String TOOLS_MENU_MESSAGE = "Outils";

    public static String NEW_PROJECT_ACTION_MESSAGE = "Nouveau projet";
    public static String OPEN_PROJECT_ACTION_MESSAGE = "Ouvrir projet";
    public static String SAVE_PROJECT_ACTION_MESSAGE = "Enregistrer projet";
    public static String SAVE_PROJECT_AS_ACTION_MESSAGE = "Enregistrer projet sous ...";
    public static String CLOSE_PROJECT_ACTION_MESSAGE = "Fermer projet";
    public static String PRINT_PROJECT_ACTION_MESSAGE = "Imprimer projet";
    public static String EXIT_ACTION_MESSAGE = "Quitter";
    public static String HIDE_GRID_ACTION_MESSAGE = "Cacher grille";
    public static String SHOW_GRID_ACTION_MESSAGE = "Montrer grille";
    public static String CHOICE_GRID_SIZE_MESSAGE = "Choisir taille de la grille";
    public static String HIDE_RULE_ACTION_MESSAGE = "Cacher règle";
    public static String SHOW_RULE_ACTION_MESSAGE = "Montrer règle";
    public static String PREFERENCE_ACTION_MESSAGE = "Préférences...";
    public static String DEBUG_INFO_ACTION_MESSAGE = "Info de développement";
    public static String ABOUT_ACTION_MESSAGE = "A Propos ...";
    public static String HELP_MODEL_ACTION_MESSAGE = "Aide sur K-MAD";
    public static String HELP_TOOL_ACTION_MESSAGE = "Aide sur KMADe";
    public static String HISTORY_ACTION_MESSAGE = "Historique de KMADe";
    public static String UNDO_ACTION_MESSAGE = "Annuler dernière action";
    public static String REDO_ACTION_MESSAGE = "Rétablir dernière action";
    public static String CUT_ACTION_MESSAGE = "Couper";
    public static String COPY_ACTION_MESSAGE = "Copier";
    public static String PASTE_ACTION_MESSAGE = "Coller";
    public static String SHOW_CLIPBOARD_MESSAGE = "Afficher fenêtre presse-papier";
    public static String SEARCH_ACTION_MESSAGE = "Recherche avancée ...";
    public static String SIMULATION_ACTION_MESSAGE = "Simulation";
    public static String INTERROGATION_ACTION_MESSAGE = "Statistiques";
    public static String CHECK_COHERENCE_ACTION_MESSAGE = "Cohérence";
    public static String ENTITY_LIST_ACTION_MESSAGE = "Liste des entités de l'état du monde";
    public static String VERSION_MESSAGE = "Version";
    public static String MESSAGES_MESSAGE = "Messages";
    public static String ERREURS_MESSAGE = "Erreurs";
    public static String FIND_REPLACE_ACTION_MESSAGE = "Chercher/Remplacer";

    public static String GRID_SIZE_ACTION_MESSAGE = "Taille de la grille (compris entre 1 et 40)";
    public static String INPUT_GRID_SIZE_MESSAGE = "Choix de la taille de la grille";
    public static String INPUT_GRID_SIZE_ERROR_MESSAGE = "Valeur de la grille non conforme";

    public static String NEW_UNKNOWN_TASK_ACTION_MESSAGE = "Nouvelle tâche inconnue";
    public static String NEW_ABSTRACT_TASK_ACTION_MESSAGE = "Nouvelle tâche abstraite";
    public static String NEW_INTERACTION_TASK_ACTION_MESSAGE = "Nouvelle tâche interaction";
    public static String NEW_FEEDBACK_TASK_ACTION_MESSAGE = "Nouvelle tâche système";
    public static String NEW_USER_TASK_ACTION_MESSAGE = "Nouvelle tâche utilisateur";
    public static String EDIT_TASK_ACTION_MESSAGE = "Edition de la tâche";
    public static String COMPLETE_EDIT_TASK_ACTION_MESSAGE = "Edition complète de la tâche";

    public static String COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU = "Edition complète";

    public static String UNKNOWN_TASK_MESSAGE = "Inconnue";
    public static String ABSTRACT_TASK_MESSAGE = "Abstraite";
    public static String INTERACTION_TASK_MESSAGE = "Interaction";
    public static String FEEDBACK_TASK_MESSAGE = "Systeme";
    public static String USER_TASK_MESSAGE = "Utilisateur";

    public static String DELETE_CELL_ACTION_MESSAGE = "Supprimer élément(s) du modèle";

    public static String DELETE_CELL_ACTION_MESSAGE_PIE_MENU = "Supprimer";

    public static String PREVIEW_WINDOW_ACTION_MESSAGE = "Afficher fenêtre aperçue";
    public static String ZOOM_IN_ACTION_MESSAGE = "Agrandissement du modèle de tâches (+)";
    public static String ZOOM_OUT_ACTION_MESSAGE = "Dégrossissement du modèle de tâches (-)";
    public static String ZOOM_DEFAULT_ACTION_MESSAGE = "Taille du modèle de tâches par défaut";
    public static String ZOOM_GLOBAL_ACTION_MESSAGE = "Vue globale du modèle de tâches";
    public static String MAGNET_OPTION_ACTION_MESSAGE = "Activer/Désactiver l'aimant";
    public static String JUSTIFY_OPTION_ACTION_MESSAGE = "Respatialisation d'une tâche";
    public static String SAME_EXECUTANT_MESSAGE = "Uniformisation des sous exécutants";
    public static String VISIBLE_LABEL_ACTION_MESSAGE = "Activer/Désactiver l'affichage des libellés";
    public static String COLOR_LABEL_ACTION_MESSAGE = "Activer/Désactiver la couleur des libellés";
    /*** Boite de dialogue lancement simulation ***/
    public static String OPEN_SIMULATION_MESSAGE = "Lancement de la boite de dialogue de simulation";

    public static String TREE_TASK_PRINT_TITLE_MESSAGE = "Arbre de tâches";
    public static String USER_CARDS_PRINT_TITLE_MESSAGE = "Fiches utilisateur";
    public static String OBJECTS_PRINT_TITLE_MESSAGE = "Objets du monde";
    public static String PRINT_ACTION_MESSAGE = "Impression";
    public static String LAYOUT_PRINT_ACTION_MESSAGE = "Mise en page";
    public static String PRINT_PREVIEW_ACTION_MESSAGE = "Aperçu avant impression";
    public static String PDF_EXPORT_ACTION_MESSAGE = "Exporter en PDF";
    public static String EPS_EXPORT_ACTION_MESSAGE = "Exporter en EPS";
    public static String PORTRAIT_ACTION_MESSAGE = "Portrait";
    public static String LANDSCAPE_ACTION_MESSAGE = "Paysage";
    public static String SCALE_ACTION_MESSAGE = "Echelle";
    public static String INPUT_SCALE_ACTION_MESSAGE = "Choix de la valeur de l'échelle";
    public static String PREVIEW_ZOOM_ACTION_MESSAGE = "Agrandissement/Grossissement de l'aperçu";
    public static String SAVE_HISTORIC_ACTION_MESSAGE = "Sauver historique";
    public static String CLEAR_HISTORIC_ACTION_MESSAGE = "Effacer historique";

    // Messages liés aux expressions
    public static String PRINT_SCALE_ADAPT_MESSAGE = "Adapter au modèle";
    public static String PRINT_SCALE_PERSONALIZE_MESSAGE = "Personnaliser...";
    public static String RESET_ACTION_MESSAGE = "Reset";
    public static String CONCRETE_OBJECTS_LIST_MESSAGE = "Liste Objets";
    public static String EDITOR_PRE_TITLE_NAME = "Editeur Precondition";
    public static String EDITOR_POST_TITLE_NAME = "Editeur EffetsDeBord";
    public static String EDITOR_ITER_TITLE_NAME = "Editeur Iteration";
    public static String CAUSE_MESSAGE = "  CAUSE";
    public static String VALUE_MESSAGE = "  RESULTAT";
    public static String CHECK_ACTION_MESSAGE = "Vérification de l'expression";
    public static String EVALUATE_ACTION_MESSAGE = "Evaluation de l'expression";
    public static String STRING_TO_INTEGER = "Erreur : impossible de transformer le texte en entier";
    public static String STRING_TO_NUMBER = "Erreur : impossible de transformer le texte en nombre";
    public static String NO_EXPRESSION_MESSAGE = "Aucune expression";
    public static String NO_FIRST_EXPRESSION_MESSAGE = "Expression non booléenne ne peut être utilisée toute seule";
    public static String MISSING_USER_VALUE_MESSAGE = "Valeur(s) utilisateur manquante(s)";
    public static String PARSER_PROBLEM_MESSAGE = "Problème dans le module d'analyse grammaticale";
    public static String LEXICAL_ERROR_MESSAGE = "Erreur lexicale";
    public static String SYNTAXICAL_ERROR_MESSAGE = "Erreur syntaxique";
    public static String SEMANTICAL_ERROR_MESSAGE = "Erreur sémantique";
    public static String LINE_MESSAGE = "ligne";
    public static String COLUMN_MESSAGE = "colonne";
    public static String ENCOUNTERED_CHAR_MESSAGE = "caractère rencontré";
    public static String ENCOUNTERED_TOKEN_MESSAGE = "texte rencontré";

    public static String CHECK_ALL_PRECONDITIONS = "Vérification des préconditions ...";
    public static String PRECONDITION_OK_MESSAGE = "précondition correcte";
    public static String PRECONDITION_NO_OK_MESSAGE = "précondition non correcte";
    public static String PRECONDITION_EVAL_OK_MESSAGE = "précondition évaluée";
    public static String PRECONDITION_EVAL_NO_OK_MESSAGE = "précondition non évaluée";
    public static String PRECONDITION_OF_TASK_OK_MESSAGE = "Précondition correcte pour la tâche";
    public static String PRECONDITION_OF_TASK_NO_OK_MESSAGE = "Précondition incorrecte pour la tâche";
    public static String PRECONDITION_ERROR_MESSAGE = "Erreur sur la précondition";
    public static String NOT_YET_CHECKED_PRECONDITION_MESSAGE = "Précondition non vérifiée";
    public static String PRECONDITION_CHECKED_AND_BUILT_MESSAGE = "Vérification et création des préconditions terminées";
    public static String PRECONDITION_PROGRESSBAR_MESSAGE = "Vérification et construction des préconditions";

    public static String EXIT_WITHOUT_SAVE_MESSAGE = "Quitter en modifiant l'état du modèle. L'historique des modifications sera initialisé";
    public static String CHECK_ALL_EFFETSDEBORDS = "Vérification des effetsdebords ...";
    public static String EFFETSDEBORD_OK_MESSAGE = "effetsdebord correcte";
    public static String EFFETSDEBORD_NO_OK_MESSAGE = "effetsdebord non correcte";
    public static String EFFETSDEBORD_EVAL_OK_MESSAGE = "effetsdebord évaluée et modèle K-MAD modifié";
    public static String EFFETSDEBORD_EVAL_NO_OK_MESSAGE = "effetsdebord non évaluée";
    public static String EFFETSDEBORD_OF_TASK_OK_MESSAGE = "EffetsDeBord correcte pour la tâche";
    public static String EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE = "EffetsDeBord incorrecte pour la tâche";
    public static String EFFETSDEBORD_ERROR_MESSAGE = "Erreur sur la effetsdebord";
    public static String NOT_YET_CHECKED_EFFETSDEBORD_MESSAGE = "EffetsDeBord non vérifiée";
    public static String EFFETSDEBORD_CHECKED_AND_BUILT_MESSAGE = "Vérification et création des effetsdebords terminées";
    public static String EFFETSDEBORD_PROGRESSBAR_MESSAGE = "Vérification et construction des effetsdebords";
    public static String EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE = "Objet Concret";
    public static String EFFETSDEBORD_HISTORY_TITLE_MESSAGE = "Historique de K-MAD";
    public static String EFFETSDEBORD_LOAD_STATE_MESSAGE = "Charger";
    public static String NO_SELECTED_HISTORIC_MESSAGE = "Historique non sélectionné";
    public static String HISTORIC_LOADED_MESSAGE = "Historique chargée";
    public static String HISTORIC_NO_LOADED_MESSAGE = "Historique non chargée";

    public static String CHECK_ALL_ITERATIONS = "Vérification des itérations ...";
    public static String ITERATION_OK_MESSAGE = "itération correcte";
    public static String ITERATION_NO_OK_MESSAGE = "itération non correcte";
    public static String ITERATION_EVAL_OK_MESSAGE = "itération évaluée";
    public static String ITERATION_EVAL_NO_OK_MESSAGE = "itération non évaluée";
    public static String NOT_YET_CHECKED_ITERATION_MESSAGE = "Itération non vérifiée";
    public static String STOP_LOOP_ITERATION_MESSAGE = "Itération terminée";
    public static String CONTINUE_LOOP_ITERATION_MESSAGE = "Itération en cours";
    public static String ITERATION_MESSAGE = "ITERATION";
    public static String ITERATION_ERROR_MESSAGE = "Erreur sur l'itération";
    public static String ITERATION_OF_TASK_OK_MESSAGE = "Itération correcte pour la tâche";
    public static String ITERATION_OF_TASK_NO_OK_MESSAGE = "Itération incorrecte pour la tâche";
    public static String ITERATION_CHECKED_AND_BUILT_MESSAGE = "Vérification et création des itérations terminées";
    public static String ITERATION_PROGRESSBAR_MESSAGE = "Vérification et construction des itérations";

    // Calculatrice pour les expressions
    public static String CLEAR_EXPRESSION_MESSAGE = "Effacer";
    public static String CHECK_VALIDATE_MESSAGE = "Vérifier";
    public static String EVALUATE_FORCE1_MESSAGE = "Evaluer";
    public static String PRECONDITION_EDITION_MESSAGE = "Edition de la précondition";
    public static String EFFETSDEBORD_EDITION_MESSAGE = "Edition de la effetsdebord";
    public static String ITERATION_EDITION_MESSAGE = "Edition de l'itération";
    public static String PRECONDITION_TEXTUEL_EDITION_MESSAGE = "Edition textuelle de la précondition";
    public static String EFFETSDEBORD_TEXTUEL_EDITION_MESSAGE = "Edition textuelle de la effetsdebord";
    public static String ITERATION_TEXTUEL_EDITION_MESSAGE = "Edition textuelle de l'itération";
    public static String LITTERAUX_TITLE_MESSAGE = "Litéraux";
    public static String OPERATORS_TITLE_MESSAGE = "Opérateurs";
    public static String FUNCTIONS_TITLE_MESSAGE = "Fonctions";
    public static String ITERATORS_TITLE_MESSAGE = "Itérateurs";
    public static String LOOP_VARIANT_MESSAGE = "Variant Boucle";

    // L'outil de recherche
    public static String WRITE_TXT_FILE_OK = "Enregistrement du fichier texte réussi : ";
    public static String WRITE_TXT_FILE_ERROR = "Erreur d'enregistrement du fichier texte";
    public static String SAVE_CANCELLED_TEXT_FILECHOOSER_NAME = "Enregistrement du fichier texte annulé";
    public static String SEARCH_NAME_NAME = "Nom de la Tâche";
    public static String WHOLE_TASK_NAME_OPTION_REPLACE_MESSAGE = "Nom Complet";
    public static String SEARCH_FAC_NAME = "Tâche optionnelle";
    public static String SEARCH_INT_NAME = "Tâche interruptible";
    public static String EXTENSION_TEXT_FILTER_SEARCH_NAME = "Fichier texte (*.txt)";
    public static String SEARCH_MESSAGE_TITLE = "Outil de recherche ...";
    public static String FIND_MESSAGE = "Rechercher";
    public static String REPLACE_MESSAGE = "Remplacer avec";
    public static String DIRECTION_FIND_MESSAGE = "Direction";
    public static String FORWARD_DIRECTION_FIND_MESSAGE = "En Avant";
    public static String BACKWARD_DIRECTION_FIND_MESSAGE = "En Arrière";
    public static String SCOPE_FIND_MESSAGE = "Etendu des tâches";
    public static String ALL_FIND_MESSAGE = "Toutes";
    public static String SELECTION_FIND_MESSAGE = "Selection";
    public static String OPTION_FIND_MESSAGE = "Options";
    public static String CASE_SENSITIVE_OPTIONS_FIND_MESSAGE = "Sensible à la case";
    public static String WHOLE_TASK_NAME_OPTIONS_FIND_MESSAGE = "Nom de tâche complet";
    public static String REGULAR_EXPRESSIONS_OPTIONS_FIND_MESSAGE = "Expressions régulières";
    public static String SEARCH_RESULT_FIND_MESSAGE = "Résultat Recherche";
    public static String FIND_ACTION_FIND_MESSAGE = "Chercher";
    public static String SAVE_OCCURENCES_ACTION_FIND_MESSAGE = "Enregistrer Occurences";
    public static String REPLACE_NEXT_ACTION_FIND_MESSAGE = "Remplacer/Suivant";
    public static String REPLACE_PREVIOUS_ACTION_FIND_MESSAGE = "Remplacer/Précédent";
    public static String REPLACE_ACTION_FIND_MESSAGE = "Remplacer";
    public static String REPLACE_ALL_ACTION_FIND_MESSAGE = "Remplacer tout";
    public static String NO_TASK_AVAILABLE_FOR_SEARCHING_TOOL_MESSAGE = "Aucune tâche disponible pour la recherche";
    public static String TASK_NOT_FOUNDED_ACCORDING_FIND_CRITERIAS = "Tâche non trouvée";
    public static String COUNT_TASKS_FOUNDED_MESSAGE = "Nombre de tâches";

    // Simulation de manière générale
    public static String SIMULATION_TITLE_MESSAGE = "Simulation";
    public static String ERROR_ITERATION_VIEW_MESSAGE = "Erreur";
    public static String PREDICAT_ITERATION_VIEW_MESSAGE = "Prédicat";
    public static String USER_VALUE_ITERATION_VIEW_MESSAGE = "Val. Utilisateur";
    public static String SCENARIO_FILTER_NAME = "Fichier de scénario" + " ("
	    + KMADE_SCENARIO_EXTENSION_FILE + ")";
    public static String ACTIONS_SIMULATION_MESSAGE = "Actions";
    public static String EDIT_USER_VALUES_SIMULATION_MESSAGE = "Editer Valeurs Utilisateur";
    public static String RECORD_SCENARIO_MESSAGE = "Enregistrer scénario";
    public static String OPEN_CANCELLED_SCENARIO_FILECHOOSER_NAME = "Chargement d'un scénario annulé";
    public static String SAVE_CANCELLED_SCENARIO_FILECHOOSER_NAME = "Enregistrement d'un scénario annulé";
    public static String SUCCEEDED_SAVE_SCENARIO_MESSAGE = "Enregistrement du scénario réussi";
    public static String NO_SUCCEEDED_SAVE_SCENARIO_MESSAGE = "Enregistrement du scénario échoué";
    public static String AUTHORIZED_EXECUTER_USER_MESSAGE = "Autorisée";
    public static String NO_AUTHORIZED_EXECUTER_USER_MESSAGE = "Non Autorisée";
    public static String DISABLED_CONSTRAINT_MESSAGE = "Désactivée";
    public static String EVENT_TITLE_MESSAGE = "Evénements";
    public static String NO_CONCRETE_OBJECT = "Aucun objet concret courant";
    public static String NO_FIRING_EVENT_MESSAGE = "Aucun Evénement Provoqué";
    public static String NO_FIRING_EVENT_TINY_MESSAGE = "Aucun Evénement";
    public static String FIRABLE_EVENT_STATE_MESSAGE = "Déclenchable";
    public static String NO_FIRABLE_EVENT_STATE_MESSAGE = "Non Déclenchable";
    public static String NO_SELECTED_ACTION_FOR_CONSTRAINTS_MESSAGE = "Aucune action \"exécuter\" sélectionnée";
    public static String NO_SELECTED_SCENARIO_ACTION_FOR_CONSTRAINTS_MESSAGE = "Aucune action \"exécuter\" du scénario sélectionnée";
    public static String CONSTRAINTS_TITLE_MESSAGE = "Contraintes";
    public static String SOURCE_TASK_EVENT_MESSAGE = "Tâche";
    public static String NO_USER_MESSAGE = "Aucun Utilisateur";
    public static String EXECUTING_USER_TITLE_MESSAGE = "Utilisateur";
    public static String CONSTRAINTS_NAME_COLUMN_NAME = "Nom";
    public static String CONSTRAINTS_VALEUR_COLUMN_NAME = "Valeur";
    public static String CONSTRAINTS_STATE_COLUMN_NAME = "Etat";
    public static String PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE = "Respectée";
    public static String PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE = "Non Respectée";
    public static String PRECONDITION_CONSTRAINTS_ERROR_STATE_MESSAGE = "Erreur";
    public static String PRECONDITION_CONSTRAINTS_NEED_VALUES_STATE_MESSAGE = "Besoin Valeur(s)";
    public static String EFFETSDEBORD_EXECUTED_STATE_MESSAGE = "Exécutée";
    public static String EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE = "Non Exécutée";
    public static String USER_VALUES_TITLE = "Editeur des valeurs utilisateurs";
    public static String PRECONDITION_USER_EDITION_MESSAGE = "Valeurs utilisateur de la précondition";
    public static String EFFETSDEBORD_USER_EDITION_MESSAGE = "Valeurs utilisateur de la effetsdebord";
    public static String ITERATION_USER_EDITION_MESSAGE = "Valeurs utilisateur de l'itération";
    public static String TRIGGER_VALID_TASK_MESSAGE = "Déclencher";
    public static String NO_TRIGGER_CANCEL_TASK_MESSAGE = "Annuler Déclenchement";
    public static String EXECUTE_TASK_TRAITEMENT_MESSAGE = "Traitement de la téche";
    public static String EXECUTION_CONSTRAINT_MESSAGE = "Contraintes d'exécutation";
    public static String USER_EXECUTION_CONSTRAINT_MESSAGE = "Utilisateur";
    public static String CAN_EXECUTE_TASK_MESSAGE = "peut exécuter la tâche";
    public static String CAN_NOT_EXECUTE_TASK_MESSAGE = "ne peut pas exécuter la tâche";
    public static String EVENT_TRIGGER_CONSTRAINT_MESSAGE = "Evénement";
    public static String NO_TRIGGER_EVENT_CONSTRAINT_MESSAGE = "Aucun d'événement déclencheur";
    public static String CAN_TRIGGER_TASK_MESSAGE = "déclenche la tâche";
    public static String CAN_NOT_TRIGGER_CONSTRAINT_MESSAGE = "non généré et ne peut déclencher la tâche";
    public static String PRECONDITION_CONSTRAINT_MESSAGE = "Précondition";
    public static String ACTION_CONSTRAINT_MESSAGE = "Actions";
    public static String GENERATE_EVENTS_CONSTRAINT_MESSAGE = "Génération Evénements";
    public static String NO_GENERATED_EVENTS_CONSTRAINT_MESSAGE = "Pas d'événement généré";
    public static String EFFETSDEBORD_CONSTRAINT_MESSAGE = "EffetsDeBord";
    public static String USER_VALUES_MISSING_OR_WRONG_MESSAGE = "Valeur(s) utilisateur manquante(s) ou erronée(s)";
    public static String CONCRETE_OBJECT_NO_SELECTED_MESSAGE = "Objet(s) concret(s) non sélectionné(s)";
    public static String USER_VALUES_PROBLEM_MESSAGE = "Probléme Valeurs Utilisateur";
    public static String FINISHED_SCENARIO_PROBLEM_MESSAGE = "Scénario Terminée";
    public static String FINISHED_SIMULATION_PROBLEM_MESSAGE = "Simulation Terminée";
    public static String FINISHED_SCENARIO_AND_SIMULATION_PROBLEM_MESSAGE = "Simulation et scénario Terminés";
    public static String SELECT_TASK_TO_REPLAY_MESSAGE = "Veuillez selectionner une tache";
    public static String ITERATION_FINISH_NO_ACTION = "L'itération est fini, aucun effet de bord n'a lieu";
    // Les actions sur les tâches pour lors de la simulation
    public static String EXECUTE_ACTION_SIMULATION_MESSAGE = "Exécuter";
    public static String PASS_ACTION_SIMULATION_MESSAGE = "Passer";
    public static String SUSPEND_ACTION_SIMULATION_MESSAGE = "Interrompre";
    public static String RESUME_ACTION_SIMULATION_MESSAGE = "Reprendre";
    public static String NO_RESUME_ACTION_SIMULATION_MESSAGE = "Abandonner";

    // L'état des tâches pour lors de la simulation.
    public static String ACTIVE_STATE_TASK_SIMULATION_MESSAGE = "Active";
    public static String ACTIVE_ITERATION_STATE_TASK_SIMULATION_MESSAGE = "ActiveIte";
    public static String FINISHED_STATE_TASK_SIMULATION_MESSAGE = "Terminée";
    public static String PASSIVE_STATE_TASK_SIMULATION_MESSAGE = "Passive";
    public static String NO_ACCESSIBLE_STATE_TASK_SIMULATION_MESSAGE = "Inactive";
    public static String PASSED_STATE_TASK_SIMULATION_MESSAGE = "Passée";
    public static String SUSPENDED_STATE_TASK_SIMULATION_MESSAGE = "Suspendue";
    public static String NO_RESUMED_STATE_TASK_SIMULATION_MESSAGE = "Abandonnée";

    // Pour l'enregistrement de la simulation
    public static String RECORD_TITLE_MESSAGE = "Enregistrement";
    public static String RECORD_BUILDING_SCENARION_MESSAGE = "Scénario Courant";
    public static String RECORD_AVAILABLE_ACTIONABLE_MESSAGE = "Actions Disponibles";
    public static String FINISHED_RECORD_SIMULATION_MESSAGE = "Simulation du modéle de tâches K-MAD terminée : scénario disponible ...";
    public static String NO_SELECTED_TASK_BEFORE_RECORD_SIMULATION_MESSAGE = "Veuillez selectionner une tâche";

    // Pour le rejeu de la simulation
    public static String REPLAY_TITLE_MESSAGE = "Rejeu";
    public static String REPLAY_SLOW_TEMPO = "Lent";
    public static String REPLAY_MODERATE_TEMPO = "Modéré";
    public static String REPLAY_SPEED_TEMPO = "Rapide";
    public static String SCENARIO_REPLAY_MESSAGE = "Scénario à Rejouer";
    public static String REPLAY_OPTIONS_MESSAGE = "Options de rejeu";
    public static String REPLAY_KEEP_USER_VALUES_MESSAGE = "Conserver valeurs";
    public static String REPLAY_TEMPO_MESSAGE = "Tempo";
    public static String NO_ROOT_TASK_PROBLEM_MESSAGE = "Pas de tâche racine";
    public static String NO_FOUNDED_TASK_TO_REPLAY_PROBLEM_MESSAGE = "Tâche non trouvée";
    public static String CAN_NOT_EXECUTE_TASK_TO_REPLAY_PROBLEM_MESSAGE = "Ne peut exécuter cette tâche tout de suite";

    public static String CONFIG_ERROR_DOM = "Erreur de configuration du parseur DOM";
    public static String CALL_NEW_DOCUMENT_BUILDER = "lors de l'appel à fabrique.newDocumentBuilder()";
    public static String SIMULATION_PARSING_ERROR = "Erreur lors du parsing du document";
    public static String CALL_CONSTRUCTEUR_PARSE = "lors de l'appel à construteur.parse(xml)";
    public static String SIMULATION_IO_ERROR = "Erreur d'entrée/sortie";

    // Pour la boite "A Propos de ..."
    public static String PROJECT_LEADER_MESSAGE = "Responsable du Projet";
    public static String DEVELOPMENT_QUERING_TOOL_MESSAGE = "Développement K-MADe et Interrogation K-MAD";
    public static String KMAD_SPECIFY_MESSAGE = "Spécification du modèle K-MAD";
    public static String KMAD_ADAPTATOR_MESSAGE = "Adaptateur du modèle K-MAD";
    public static String JAVA_GRAPHIC_LIBRARIES_MESSAGE = "Bibliothéques Utilisées";
    public static String KMADE_TEAM_MESSAGE = "Participants";
    public static String JAVA_LIBRARIES_MESSAGE = "Bibliothèques";

    // Cohérence du modèle
    public static String COHERENCE_INTRO_ERROR = "L'outil de cohérence a détecté ";
    public static String COHERENCE_ONE_ERROR = " erreur";
    public static String COHERENCE_SOME_ERROR = " erreurs";
    public static String COHERENCE_INTER = " et ";
    public static String COHERENCE_ONE_WARNING = " warning";
    public static String COHERENCE_SOME_WARNING = " warnings";
    public static String COHERENCE_VERIFICATION_TITLE = "Simulation refusée ...";

    public static String NO_ALONE_MESSAGE_PROBLEM = "Pas de tâche seule";
    public static String PRECONDITION_EXPRESSION_MESSAGE_PROBLEM = "Problème dans l'expression de la précondition";
    public static String EFFETSDEBORD_EXPRESSION_MESSAGE_PROBLEM = "Problème dans l'expression de la effetsdebord";
    public static String ITERATION_EXPRESSION_MESSAGE_PROBLEM = "Problème dans l'expression de l'itération";
    public static String NO_ONLY_ONE_SUBTASK_MESSAGE_PROBLEM = "Pas de sous tâche unique";
    public static String TASK_HAS_NO_NAME_MESSAGE_PROBLEM = "Entrez un nom pour la tache";
    public static String INVALID_DECOMPOSITION_MESSAGE_PROBLEM = "Decomposition invalide";
    public static String NO_DECOMPOSITION_SPECIFIED_MESSAGE_PROBLEM = "Décomposition non précisée";
    public static String ELEMENTARY_DECOMPOSITION_FOR_LEAF_TASK = "Décomposition doit être élémentaire pour une tâche feuille";
    public static String NO_EXECUTANT_SPECIFIED_MESSAGE_WARNING = "Exécutant non défini";
    public static String SUBTASKS_EXECUTANT_DIFFERENT_MESSAGE_WARNING = "Exécutant différent";
    public static String HIERARCHY_TYPE_MESSAGE = "Hiérarchie";
    public static String EXPRESSION_TYPE_MESSAGE = "Expression";
    public static String TASKS_SPACE_LOCATION_MESSAGE = "Espace de tâches";
    public static String PRECONDITION_LOCATION_MESSAGE = "Précondition";
    public static String EFFETSDEBORD_LOCATION_MESSAGE = "Action";
    public static String ITERATION_LOCATION_MESSAGE = "Itération";
    public static String COHERENCE_TITLE_MESSAGE = "Cohérence";
    public static String COHERENCE_CHECK_MESSAGE = "Forcer Vérification";
    public static String ERRORS_TITLE_MESSAGE = "Erreurs et Avertissements";
    public static String MESSAGE_ERROR_COLUMN_NAME = "Message";
    public static String TASK_NAME_COLUMN_NAME = "tâche";
    public static String ERROR_TYPE_COLUMN_NAME = "Type";
    public static String LOCATION_COLUMN_NAME = "Localisation";

    // Statistique du modèle
    public static String COUNT_TASK_INTO_TASK_MODELS_MESSAGE = "Nombre de tâches dans l'espace de tâches";
    public static String COUNT_USER_TASK_MESSAGE = "Nombre d'exécutant de type utilisateur";
    public static String COUNT_UNKNOWN_TASK_MESSAGE = "Nombre d'exécutant de type inconnu";
    public static String COUNT_SYSTEM_TASK_MESSAGE = "Nombre d'exécutant de type système";
    public static String COUNT_ABSTRACT_TASK_MESSAGE = "Nombre d'exécutant de type abstrait";
    public static String COUNT_INTERACT_TASK_MESSAGE = "Nombre d'exécutant de type interaction";
    public static String COUNT_UNKNOWN_DEC_TASK_MESSAGE = "Nombre de décomposition de type inconnu";
    public static String COUNT_ENABLING_DEC_TASK_MESSAGE = "Nombre de décomposition de type séquenciel";
    public static String COUNT_CHOICE_DEC_TASK_MESSAGE = "Nombre de décomposition de type alternatif";
    public static String COUNT_CONCURRENT_DEC_TASK_MESSAGE = "Nombre de décomposition de type parallèle";
    public static String COUNT_ELEMENTARY_DEC_TASK_MESSAGE = "Nombre de décomposition de type élémentaire";
    public static String COUNT_NO_ORDER_DEC_TASK_MESSAGE = "Nombre de décomposition de type pas d'ordre";
    public static String STATISTIC_TITLE = "Outil de Statistiques";
    public static String TYPE_STATISTIC_MESSAGE = "Type";
    public static String RESULT_STATISTIC_MESSAGE = "Resultat";
    public static String REFRESH_STATISTIC_MESSAGE = "Mise à jour";

    //
    // Les constantes lièes aux images
    //
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
    /** ICON Evenement des Taches **/
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
    public static final String LISI_LOGO_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME
	    + "lisi.gif";
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
    // Les constantes lièes aux templates de Velocity
    //
    public static final String DEFAULT_CARD_VELOCITY_HTML = KMADEToolConstant.VELOCITY_TEMPLATE_DIRECTORY_NAME
	    + "default.vm";
    public static final String BASIC_CARD_VELOCITY_HTML = KMADEToolConstant.VELOCITY_TEMPLATE_DIRECTORY_NAME
	    + "basic.vm";

    //
    // Les constantes lièes aux autres resources
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
    public static String NO_VERSION = "Aucune version précisée version n'est précisée dans le fichier chargé";
    public static String NO_COMPATIBLE_ITEMS_DOCUMENT = "Ce fichier ne corresponf pas a un fichier d'objets KMADe";
    public static String NEW_SAVE_NEW_FORMAT = "Attention toute nouvelle sauvegarde se fera au nouveau format";
    public static String VERSION_USE = "Vous utilisez la version : ";
    public static String VERSION_FILE = "Vous chargez un fichier avec la version : ";
    public static String NOT_SAME_VERSION = "La version que vous essayez de charger n'est pas la même que celle de l'outil";

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
	VERTEX_OBJET = KMADeMain.messages.getString("VERTEX_OBJET");
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

	// Propriétés des tâches
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

	// Informations Générales des propriétés d'une tâches
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

	// Ordonnancement d'une tâche
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

	// Effets d'une tâche
	POSTCONDITION_PANEL_TITLE_NAME = KMADeMain.messages
		.getString("POSTCONDITION_PANEL_TITLE_NAME");
	EFFETSDEBORD_LABEL_VALUE = KMADeMain.messages
		.getString("EFFETSDEBORD_LABEL_VALUE");
	EFFETSDEBORD_LABEL_LEGEND_VALUE = KMADeMain.messages
		.getString("EFFETSDEBORD_LABEL_LEGEND_VALUE");
	EVENT_NAME_TITLE = KMADeMain.messages.getString("EVENT_NAME_TITLE");
	EVENT_NAME_LEGEND_TITLE = KMADeMain.messages
		.getString("EVENT_NAME_LEGEND_TITLE");

	// Pour l'outil lié au multimédia
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

	// Editeur complet d'une tâche
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

	// Caractéristique d'un projet
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

	// Editeur des attributs d'une ttâcche
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

	// Outil pour lister les entités
	ENTITY_LIST_DIALOG_TITLE = KMADeMain.messages
		.getString("ENTITY_LIST_DIALOG_TITLE");
	FORCE_ENTITY_LIST_ACTION_MESSAGE = KMADeMain.messages
		.getString("FORCE_ENTITY_LIST_ACTION_MESSAGE");

	// Préférences de l'outil
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

	// Messages liés aux expressions
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

	// Simulation de manière générale

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
	// Les actions sur les tâches pour lors de la simulation
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

	// L'état des tâches pour lors de la simulation.
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

	// Cohérence du modèle
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

	// Statistique du modèle
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

    }
}
