/********************************************************************************* * This file is part of KMADe Project. * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA *  * KMADe is free software: you can redistribute it and/or modify * it under the terms of the GNU Lesser General Public License as published by * the Free Software Foundation, either version 3 of the License, or * (at your option) any later version. * * KMADe is distributed in the hope that it will be useful, * but WITHOUT ANY WARRANTY; without even the implied warranty of * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the * GNU Lesser General Public License for more details. *  * You should have received a copy of the GNU Lesser General Public License * along with KMADe.  If not, see <http://www.gnu.org/licenses/>. **********************************************************************************/package fr.upensma.lias.kmade.kmad;import fr.upensma.lias.kmade.KMADEToolConstant;import fr.upensma.lias.kmade.KMADeMain;import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;import fr.upensma.lias.kmade.kmad.schema.metaobjet.BoolValue;import fr.upensma.lias.kmade.kmad.schema.metaobjet.Element;import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnumValue;import fr.upensma.lias.kmade.kmad.schema.metaobjet.Enumeration;import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;import fr.upensma.lias.kmade.kmad.schema.metaobjet.Intervalle;import fr.upensma.lias.kmade.kmad.schema.metaobjet.IntervalleValue;import fr.upensma.lias.kmade.kmad.schema.metaobjet.ListeAg;import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;import fr.upensma.lias.kmade.kmad.schema.metaobjet.PileAg;import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;import fr.upensma.lias.kmade.kmad.schema.metaobjet.StrValue;import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;import fr.upensma.lias.kmade.kmad.schema.metaobjet.UniqAg;import fr.upensma.lias.kmade.kmad.schema.project.GeneralInformation;import fr.upensma.lias.kmade.kmad.schema.project.InterviewIndex;import fr.upensma.lias.kmade.kmad.schema.project.Project;import fr.upensma.lias.kmade.kmad.schema.tache.Actor;import fr.upensma.lias.kmade.kmad.schema.tache.ActorSystem;import fr.upensma.lias.kmade.kmad.schema.tache.Event;import fr.upensma.lias.kmade.kmad.schema.tache.Label;import fr.upensma.lias.kmade.kmad.schema.tache.Machine;import fr.upensma.lias.kmade.kmad.schema.tache.Material;import fr.upensma.lias.kmade.kmad.schema.tache.Media;import fr.upensma.lias.kmade.kmad.schema.tache.Organization;import fr.upensma.lias.kmade.kmad.schema.tache.ParkMachines;import fr.upensma.lias.kmade.kmad.schema.tache.Person;import fr.upensma.lias.kmade.kmad.schema.tache.Point;import fr.upensma.lias.kmade.kmad.schema.tache.Task;import fr.upensma.lias.kmade.kmad.schema.tache.User;/** * @author Mickael BARON */public class ExpressConstant {	// Name for the packages	public static String PROJECT_PACKAGE = "project";	public static String CORE_PACKAGE = "tache";	public static String METAOBJECT_PACKAGE = "metaobjet";	// Class Names for project package	public static String PROJECT_CLASS = Project.class.getSimpleName();	public static String GENERAL_INFORMATION_CLASS = GeneralInformation.class.getSimpleName();	public static String INTERVIEW_CLASS = InterviewIndex.class.getSimpleName();	// Class Names for metaobject package	public static String ABSTRACT_OBJECT_CLASS = ObjetAbstrait.class.getSimpleName();	public static String ABSTRACT_ATTRIBUTE_CLASS = AttributAbstrait.class.getSimpleName();	public static String CONCRETE_ATTRIBUTE_CLASS = AttributConcret.class.getSimpleName();	public static String CONCRETE_OBJECT_CLASS = ObjetConcret.class.getSimpleName();	public static String ELEMENT_CLASS = Element.class.getSimpleName();	public static String ENUMERATION_CLASS = Enumeration.class.getSimpleName();	public static String GROUP_CLASS = Groupe.class.getSimpleName();	public static String RANGE_CLASS = Intervalle.class.getSimpleName();	public static String BOOLEAN_VALUE_CLASS = BoolValue.class.getSimpleName();	public static String ENUM_VALUE_CLASS = EnumValue.class.getSimpleName();	public static String RANGE_VALUE_CLASS = IntervalleValue.class.getSimpleName();	public static String NUMBER_VALUE_CLASS = NumberValue.class.getSimpleName();	public static String STRING_VALUE_CLASS = StrValue.class.getSimpleName();	public static String ARRAY_CLASS = TableauAg.class.getSimpleName();	public static String LIST_CLASS = ListeAg.class.getSimpleName();	public static String SET_CLASS = EnsembleAg.class.getSimpleName();	public static String SINGLETON_CLASS = UniqAg.class.getSimpleName();	public static String STACK_CLASS = PileAg.class.getSimpleName();	// Class Names for core package	public static String TASK_CLASS = Task.class.getSimpleName();	public static String POINT_CLASS = Point.class.getSimpleName();	public static String LABEL_CLASS = Label.class.getSimpleName();	public static String PROTOTASKCONDITON_CLASS = ProtoTaskCondition.class.getSimpleName();	public static String MEDIA_CLASS = Media.class.getSimpleName();	public static String EVENT_CLASS = Event.class.getSimpleName();	public static String USER_CLASS = User.class.getSimpleName();	public static String ACTOR_CLASS = Actor.class.getSimpleName();	public static String ACTOR_SYSTEM_CLASS = ActorSystem.class.getSimpleName();	public static String PERSON_CLASS = Person.class.getSimpleName();	public static String ORGANIZATION_CLASS = Organization.class.getSimpleName();	public static String MACHINE_CLASS = Machine.class.getSimpleName();	public static String MATERIAL_CLASS = Material.class.getSimpleName();	public static String MACHINE_PARK_CLASS = ParkMachines.class.getSimpleName();	// Array of the mother classes names stored in the xml	// Others classes are created from these	public static String[] MOTHER_CLASSES = { PROJECT_CLASS, TASK_CLASS, USER_CLASS, PERSON_CLASS, ORGANIZATION_CLASS,			MACHINE_CLASS, MACHINE_PARK_CLASS, ABSTRACT_OBJECT_CLASS, LABEL_CLASS, EVENT_CLASS,			PROTOTASKCONDITON_CLASS };	// Expression	public static String ADD_UNARY_FUNCTION_EXPRESSION = "add";	public static String AND_OPERATOR_EXPRESSION = "AND";	public static String ASSIGNMENT_EXPRESSION = ":=";	public static String BOOLEAN_CONSTANT_EXPRESSION = "BCst";	public static String CREATE_FUNCTION_EXPRESSION = "create";	public static String DIFF_OPERATOR_EXPRESSION = "!=";	public static String EQUAL_OPERATOR_EXPRESSION = "==";	public static String GET_FUNCTION_EXPRESSION = "getValue";	public static String INF_EQUAL_OPERATOR_EXPRESSION = "<=";	public static String INF_OPERATOR_EXPRESSION = "<";	public static String INTEGER_CONSTANT_EXPRESSION = "ICst";	public static String INTEGER_VARIANT_EXPRESSION = "IVariant";	public static String IN_UNARY_FUNCTION_EXPRESSION = "IN";	public static String IS_EMPTY_UNARY_FUNCTION_EXPRESSION = "isEmpty";	public static String IS_EXIST_FUNCTION_EXPRESSION = "isExist";	public static String IS_EXIST_AT_FUNCTION_EXPRESSION = "isExistAt";	public static String LENGTH_FUNCTION_EXPRESSION = "card";	public static String MINUS_ASSIGNMENT_EXPRESSION = "-=";	public static String MINUS_COMPUTING_EXPRESSION = "-";	public static String NOT_COMMA_EXPRESSION = ";";	public static String NOT_UNARY_EXPRESSION = "not";	public static String NOT_WHILE_UNARY_EXPRESSION = "notWhile";	public static String OR_OPERATOR_EXPRESSION = "OR";	public static String PLUS_ASSIGNMENT_EXPRESSION = "+=";	public static String ATTRIBUT_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE = "Type de l'Attribut Non Trouvé";	public static String GROUP_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE = "Groupe non trouvé";	// Task Caracteristics Name	public static String NEW_NAME_TASK = "Nom de la tâche";	public static String ROOT_TASK_NAME = "Racine";	public static String NO_MOTHER_TASK_NAME_MESSAGE = "Pas de tâche mère";	public static String SINGLETON_NAME = "Unique";	public static String STACK_NAME = "Pile";	public static String SET_NAME = "Ensemble";	public static String LIST_NAME = "Liste";	public static String ARRAY_NAME = "Tableau";	public static String BOOLEAN_NAME = "Booléen";	public static String ENUMERATION_NAME = "Enumération";	public static String NUMBER_NAME = "Nombre";	public static String INTERVALLE_NAME = "Intervalle";	public static String STRING_NAME = "Texte";	public static String UNKNOWN_EXECUTANT_NAME = "Inconnu";	public static String SYSTEM_EXECUTANT_NAME = "Système";	public static String INTERACTION_EXECUTANT_NAME = "Interactif";	public static String USER_EXECUTANT_NAME = "Utilisateur";	public static String ABSTRACT_EXECUTANT_NAME = "Abstrait";	public static String UNKNOWN_IMPORTANCE_NAME = "Non déterminée";	public static String HIGH_IMPORTANCE_NAME = "Très importante";	public static String AVERAGE_IMPORTANCE_NAME = "Assez importante";	public static String LOW_IMPORTANCE_NAME = "Peu importante";	public static String UNKNOWN_FREQUENCY_NAME = "Nom déterminée";	public static String HIGH_FREQUENCY_NAME = "Elevée";	public static String AVERAGE_FREQUENCY_NAME = "Moyenne";	public static String LOW_FREQUENCY_NAME = "Faible";	public static String UNKNOWN_MODALITY_NAME = "Nom déterminée";	public static String SENSORI_MODALITY_NAME = "Sensori-Motrice";	public static String COGNITIF_MODALITY_NAME = "Cognitive";	public static String UNKNOWN_EXPERIENCE_NAME = "Nom déterminée";	public static String HIGH_EXPERIENCE_NAME = "Expert";	public static String AVERAGE_EXPERIENCE_NAME = "Moyenne";	public static String LOW_EXPERIENCE_NAME = "Novice";	public static String OPERATOR_NAME = "Décomposition";	public static String ALTERNATIF_LONG_NAME = "Alternatif";	public static String PARALLELE_LONG_NAME = "Parallèle";	public static String SEQUENTIEL_LONG_NAME = "Séquentiel";	public static String NO_ORDER_LONG_NAME = "Pas d'ordre";	public static String LEAF_LONG_NAME = "Elémentaire";	public static String UNKNOWN_LONG_NAME = "Inconnu";	// Error Messages	public static String REMOVE_ABSTRACT_OBJECT = "Suppression de l'objet abstrait";	public static String REMOVE_ABSTRACT_ATTRIBUT = "Suppression de l'attribut abstrait";	public static String REMOVE_GROUP = "Suppression du groupe";	public static String REMOVE_USER = "Suppression de l'utilisateur";	public static String REMOVE_EVENT = "Suppression de l'événement";	public static String REMOVE_CONCRETE_OBJECT = "Suppression de l'objet concret";	public static String REMOVE_CONCRETE_ATTRIBUT = "Suppression de l'attribut concret";	public static String NO_USED_GROUP_ATTRIBUT = "Groupe ou attribut abstrait jamais utilis�s";	public static String NO_USED_ABSTRACT_OBJECT = "Objet abstrait jamais utilisé";	public static String REMOVE_ACTOR = "Suppression de l'acteur";	public static String REMOVE_ACTOR_SYSTEM = "Suppression de l'acteur système";	public static String REMOVE_MATERIEL = "Suppression de l'équipement";	public static String REMOVE_ENUMERATION = "Suppression de l'énumération";	public static String REMOVE_ELEMENT = "Suppression de l'élément";	public static String REMOVE_INTERVALLE = "Suppression de l'intervalle";	public static String REMOVE_LABEL = "Suppression du libellé";	public static String CHANGE_AGREGAT = "Modification du type d'ensemble";	public static String REMOVE_OF_THE_TASK_MESSAGE = "de la tâche";	public static String REMOVE_OF_THE_ABSTRACT_ATTRIBUT_MESSAGE = "de l'attribut abstrait";	public static String REMOVE_OF_THE_ABSTRACT_OBJECT_MESSAGE = "de l'objet abstrait";	public static String GROUP_MESSAGE = "Groupe";	public static String CONCRETE_OBJECT_MESSAGE = "OC";	public static String FROM_GROUP_MESSAGE = "du groupe";	public static String ATTRIBUT_ABSTRAIT = "Attribut abstrait";	public static String NOT_FOUNDED_ERROR = "non trouvé";	public static String INTEGER_STRING_COMPARISON_OPERATOR_ERROR = "Conversion de la chaîne de caractères en entier impossible";	public static String INTEGER_STRINGS_COMPARISON_OPERATOR_ERROR = "Conversion des chaînes de caractères en entier impossible";	public static String COMPARISON_OPERATOR_ERROR = "Problème de typage pour la décomposition de comparaison";	public static String BOOLEAN_IS_NEEDED_ERROR = "Fonction nécessite une expression boolèenne comme paramètre";	public static String NO_SAME_ABSTRACT_OBJECT = "Les groupes ont des objets abstraits différents";	public static String TYPAGE_ERROR = "Les types ne sont identiques";	public static String TYPE_NOT_A_NUMBER = "Les types doivent être des nombres";	public static String TYPE_NEED_BOOLEAN = "Le type n'est pas booléen";	public static String TYPE_PLUS_ASSIGNMENT = "Les types doivent être deux nombres ou deux textes";	// ClassLoader Error	public static String CLASS_LOADER_PROBLEM_MESSAGE = "Probleme avec la classe Java";	public static String ENTITY_NO_EXIST_PROBLEM_MESSAGE = "Entity n'existe pas";	// Image Paths	public static final String ABSTRACT_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME + "abstract16.png";	public static final String USER_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME + "user16.png";	public static final String INTERACTIF_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME + "interaction16.png";	public static final String FEEDBACK_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME + "feedback16.png";	public static final String UNKNOWN_TASK_16_IMAGE = KMADEToolConstant.IMAGE_DIRECTORY_NAME + "unknown16.png";	public static final String REMOVE_CONDITION = "TODO REMOVE CONDITION";	// PROTOTASK CONDITION VALUE	public static String TRUE = "true";	public static String FALSE = "false";	public static String INDETERMINATE = "INDETERMINATE";	public static void loadMessagesFromBundle() {		ATTRIBUT_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE = KMADeMain.messages				.getString("ATTRIBUT_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE");		GROUP_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE = KMADeMain.messages				.getString("GROUP_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE");		NEW_NAME_TASK = KMADeMain.messages.getString("NEW_NAME_TASK");		ROOT_TASK_NAME = KMADeMain.messages.getString("ROOT_TASK_NAME");		SINGLETON_NAME = KMADeMain.messages.getString("SINGLETON_NAME");		STACK_NAME = KMADeMain.messages.getString("STACK_NAME");		SET_NAME = KMADeMain.messages.getString("SET_NAME");		LIST_NAME = KMADeMain.messages.getString("LIST_NAME");		ARRAY_NAME = KMADeMain.messages.getString("ARRAY_NAME");		BOOLEAN_NAME = KMADeMain.messages.getString("BOOLEAN_NAME");		ENUMERATION_NAME = KMADeMain.messages.getString("ENUMERATION_NAME");		NUMBER_NAME = KMADeMain.messages.getString("NUMBER_NAME");		INTERVALLE_NAME = KMADeMain.messages.getString("INTERVALLE_NAME");		STRING_NAME = KMADeMain.messages.getString("STRING_NAME");		UNKNOWN_EXECUTANT_NAME = KMADeMain.messages.getString("UNKNOWN_EXECUTANT_NAME");		SYSTEM_EXECUTANT_NAME = KMADeMain.messages.getString("SYSTEM_EXECUTANT_NAME");		INTERACTION_EXECUTANT_NAME = KMADeMain.messages.getString("INTERACTION_EXECUTANT_NAME");		USER_EXECUTANT_NAME = KMADeMain.messages.getString("USER_EXECUTANT_NAME");		ABSTRACT_EXECUTANT_NAME = KMADeMain.messages.getString("ABSTRACT_EXECUTANT_NAME");		UNKNOWN_IMPORTANCE_NAME = KMADeMain.messages.getString("UNKNOWN_IMPORTANCE_NAME");		HIGH_IMPORTANCE_NAME = KMADeMain.messages.getString("HIGH_IMPORTANCE_NAME");		AVERAGE_IMPORTANCE_NAME = KMADeMain.messages.getString("AVERAGE_IMPORTANCE_NAME");		LOW_IMPORTANCE_NAME = KMADeMain.messages.getString("LOW_IMPORTANCE_NAME");		UNKNOWN_FREQUENCY_NAME = KMADeMain.messages.getString("UNKNOWN_FREQUENCY_NAME");		HIGH_FREQUENCY_NAME = KMADeMain.messages.getString("HIGH_FREQUENCY_NAME");		AVERAGE_FREQUENCY_NAME = KMADeMain.messages.getString("AVERAGE_FREQUENCY_NAME");		LOW_FREQUENCY_NAME = KMADeMain.messages.getString("LOW_FREQUENCY_NAME");		UNKNOWN_MODALITY_NAME = KMADeMain.messages.getString("UNKNOWN_MODALITY_NAME");		SENSORI_MODALITY_NAME = KMADeMain.messages.getString("SENSORI_MODALITY_NAME");		COGNITIF_MODALITY_NAME = KMADeMain.messages.getString("COGNITIF_MODALITY_NAME");		UNKNOWN_EXPERIENCE_NAME = KMADeMain.messages.getString("UNKNOWN_EXPERIENCE_NAME");		HIGH_EXPERIENCE_NAME = KMADeMain.messages.getString("HIGH_EXPERIENCE_NAME");		AVERAGE_EXPERIENCE_NAME = KMADeMain.messages.getString("AVERAGE_EXPERIENCE_NAME");		LOW_EXPERIENCE_NAME = KMADeMain.messages.getString("LOW_EXPERIENCE_NAME");		OPERATOR_NAME = KMADeMain.messages.getString("OPERATOR_NAME");		ALTERNATIF_LONG_NAME = KMADeMain.messages.getString("ALTERNATIF_LONG_NAME");		PARALLELE_LONG_NAME = KMADeMain.messages.getString("PARALLELE_LONG_NAME");		SEQUENTIEL_LONG_NAME = KMADeMain.messages.getString("SEQUENTIEL_LONG_NAME");		NO_ORDER_LONG_NAME = KMADeMain.messages.getString("NO_ORDER_LONG_NAME");		LEAF_LONG_NAME = KMADeMain.messages.getString("LEAF_LONG_NAME");		UNKNOWN_LONG_NAME = KMADeMain.messages.getString("UNKNOWN_LONG_NAME");		// Error Messages		REMOVE_ABSTRACT_OBJECT = KMADeMain.messages.getString("REMOVE_ABSTRACT_OBJECT");		REMOVE_ABSTRACT_ATTRIBUT = KMADeMain.messages.getString("REMOVE_ABSTRACT_ATTRIBUT");		REMOVE_GROUP = KMADeMain.messages.getString("REMOVE_GROUP");		REMOVE_USER = KMADeMain.messages.getString("REMOVE_USER");		REMOVE_EVENT = KMADeMain.messages.getString("REMOVE_EVENT");		REMOVE_CONCRETE_OBJECT = KMADeMain.messages.getString("REMOVE_CONCRETE_OBJECT");		REMOVE_CONCRETE_ATTRIBUT = KMADeMain.messages.getString("REMOVE_CONCRETE_ATTRIBUT");		NO_USED_GROUP_ATTRIBUT = KMADeMain.messages.getString("NO_USED_GROUP_ATTRIBUT");		NO_USED_ABSTRACT_OBJECT = KMADeMain.messages.getString("NO_USED_ABSTRACT_OBJECT");		REMOVE_ACTOR = KMADeMain.messages.getString("REMOVE_ACTOR");		REMOVE_ACTOR_SYSTEM = KMADeMain.messages.getString("REMOVE_ACTOR_SYSTEM");		REMOVE_MATERIEL = KMADeMain.messages.getString("REMOVE_MATERIEL");		REMOVE_ENUMERATION = KMADeMain.messages.getString("REMOVE_ENUMERATION");		REMOVE_ELEMENT = KMADeMain.messages.getString("REMOVE_ELEMENT");		REMOVE_INTERVALLE = KMADeMain.messages.getString("REMOVE_INTERVALLE");		REMOVE_LABEL = KMADeMain.messages.getString("REMOVE_LABEL");		CHANGE_AGREGAT = KMADeMain.messages.getString("CHANGE_AGREGAT");		REMOVE_OF_THE_TASK_MESSAGE = KMADeMain.messages.getString("REMOVE_OF_THE_TASK_MESSAGE");		REMOVE_OF_THE_ABSTRACT_ATTRIBUT_MESSAGE = KMADeMain.messages				.getString("REMOVE_OF_THE_ABSTRACT_ATTRIBUT_MESSAGE");		REMOVE_OF_THE_ABSTRACT_OBJECT_MESSAGE = KMADeMain.messages.getString("REMOVE_OF_THE_ABSTRACT_OBJECT_MESSAGE");		GROUP_MESSAGE = KMADeMain.messages.getString("GROUP_MESSAGE");		CONCRETE_OBJECT_MESSAGE = KMADeMain.messages.getString("CONCRETE_OBJECT_MESSAGE");		FROM_GROUP_MESSAGE = KMADeMain.messages.getString("FROM_GROUP_MESSAGE");		ATTRIBUT_ABSTRAIT = KMADeMain.messages.getString("ATTRIBUT_ABSTRAIT");		NOT_FOUNDED_ERROR = KMADeMain.messages.getString("NOT_FOUNDED_ERROR");		INTEGER_STRING_COMPARISON_OPERATOR_ERROR = KMADeMain.messages				.getString("INTEGER_STRING_COMPARISON_OPERATOR_ERROR");		INTEGER_STRINGS_COMPARISON_OPERATOR_ERROR = KMADeMain.messages				.getString("INTEGER_STRINGS_COMPARISON_OPERATOR_ERROR");		COMPARISON_OPERATOR_ERROR = KMADeMain.messages.getString("COMPARISON_OPERATOR_ERROR");		BOOLEAN_IS_NEEDED_ERROR = KMADeMain.messages.getString("BOOLEAN_IS_NEEDED_ERROR");		NO_SAME_ABSTRACT_OBJECT = KMADeMain.messages.getString("NO_SAME_ABSTRACT_OBJECT");		TYPAGE_ERROR = KMADeMain.messages.getString("TYPAGE_ERROR");		TYPE_NOT_A_NUMBER = KMADeMain.messages.getString("TYPE_NOT_A_NUMBER");		TYPE_NEED_BOOLEAN = KMADeMain.messages.getString("TYPE_NEED_BOOLEAN");		TYPE_PLUS_ASSIGNMENT = KMADeMain.messages.getString("TYPE_PLUS_ASSIGNMENT");		// ClassLoader Error		CLASS_LOADER_PROBLEM_MESSAGE = KMADeMain.messages.getString("CLASS_LOADER_PROBLEM_MESSAGE");		ENTITY_NO_EXIST_PROBLEM_MESSAGE = KMADeMain.messages.getString("ENTITY_NO_EXIST_PROBLEM_MESSAGE");		ENTITY_NO_EXIST_PROBLEM_MESSAGE = KMADeMain.messages.getString("ENTITY_NO_EXIST_PROBLEM_MESSAGE");		TRUE = KMADeMain.messages.getString("TRUE");		FALSE = KMADeMain.messages.getString("FALSE");		INDETERMINATE = KMADeMain.messages.getString("INDETERMINATE");	}}