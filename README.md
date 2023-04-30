# KMADe: Kernel of Model for Activity Description environment

K-MADe (Kernel of Model for Activity Description environment) is a tool for contributing to the incorporation of ergonomics into the interactive systems design process, through task and activity analysis. The originality of the tool is to be based on a model whose expressive power lies on a formal semantic. This facilitates task description and analysis, but also querying the model and migrating within models and software life-cycle steps.

## Software requirements

* Java
* Maven (for compiling)

## Download 

The latest version of KMADe can be downloaded at https://github.com/lias-laboratory/kmade/releases.

## Run

* Unpack the archive in a target directory, eg: _c:\kmade_.

* Double click on the _kmade.jar_ file (it is an Java executable)

Or

* Execute the following shell command:

```console
$ java -jar kmade.jar
```

## Compile

* From the root of directory, execute the following shell command:

```console
$ mvn clean package 
```

## What's KMADe

### Potential users

The K-MADe tool is intended for people wishing to describe, analyze and formalize the activities of human operators, of users, in environments (computerized or not), in real or simulated situation; in the field, or in the laboratory. Although all kinds of profiles of people are possible, this environment is particularly intended for ergonomics and HCI (Human Computer Interaction) specialists in.

### Purpose of the K-MADe tool

K-MADe makes it possible to create, edit and query models of users tasks. It is an environment resulting from ergonomics and IHM research, and intended to facilitate the implementation of a user-centered analytic perspective, according a model-based approach, for the design and the ergonomic evaluation of the interactive software.

Contributions to the integration of ergonomics to the process of design are in particular: to facilitate, by formal semantics and generic formats the description, the examination, and analysis of tasks; to limit the inter-individual variations; to increase the level of completion of descriptions; to authorize further the processing of descriptions, in particular the queries of the model; and to facilitate the paths between models of the field and the stages of the software lifecycle.

### Phases of use

K-MADe can be used at the time of data collection in the field; during the analysis of user activities; during the validation of the models; etc. It can be also be used during the various phases of the development, for example: for the specification of hypothetical tasks models, to contribute with the design and the evaluation of usability as well as the drafting of documentation. K-MADe can also be considered to help the end-user in the form of task documentation, e.g., for training.

### K-MADe features

The K-MADe tool implements the whole set of the model characteristics. It makes it possible to edit, modify and question (the querying functionalities of the model are under development) the task models. The tool is available in French and in English and consists mainly of the following tools:

* Graphic editor of K-MAD task models. Use of direct manipulation techniques for creating, modifying and removing tasks

* Editor of tasks characteristics (see the list above). The editor works three ways. A reduced “light” way with only the characteristics considered to be most important, a way which lists in tables all the characteristics, and finally a form which shows in an organized form all task characteristics (precondition, tasks body and postcondition). This last representation is used for the printing of the full task

* Editor of abstract objects, users, events and concrete objects. Possibility of adding, modifying and removing objects. The modification and removal of an object involves obligatorily a modification on the directly dependant objects

* Tool for the editing of expressions for the preconditions, the postconditions and the iterations. The tool is capable to check if the grammar of an expression is correct

* Simulator to animate models of K-MAD tasks

* Various tools for task model analysis (statistical, coherence, advanced search...)

* Tool for printing the task trees and the task characteristics

* And so on

### K-MADe history and past references

The initial work concerning task modelling for user interfaces (Scapin, 1988) had several objectives: to consider the way in which users represents their task, not the logic of the data-processing, or the “prescribed” task; to take into account the conceptual and semantic aspects, not only syntactic and lexical aspects; to obtain a structuring of the tasks in a uniform way; to carry out descriptions from a declarative point of view (state of things) as well as procedural (way of reaching these states); to allow parallelism and not only sequential representation(synchronization of the tasks); and to be computable.

From these first ideas and various requirements, a first task analysis model (“MAD”: Méthode Analytique de Description) was proposed (Scapin, D.L. & Pierret-Golbreich, C., 1989, 1990) at the intersection of ergonomics, computer science, and artificial intelligence. Moreover, the tool EMAD was developed (Pierret, C., Delouis, I. & Scapin, D.L., 1989); Delouis I., Pierret C., 1991).

In parallel, a method for task information gathering was defined (Sebillotte S., 1991); it consists mainly of semi-directed interviews based on the “Why?” “How?” technique. Also, one practical methodology of task analysis for extracting the relevant characteristics useful for the design of interfaces was also proposed (Sebillotte, S. & Scapin, D.L., 1994).

Since then the work has been continued according to an iterative process: development; for example: implementation of the model in an object oriented fashion, organization of the tasks at various levels of abstraction, validation in the field (house automation, management of ship fires, air traffic control, railway regulation, etc), modifications, validations again (further references are available upon request).

Research work (Gamboa-Rodriguez, F. & Scapin, D.L., 1997) also focused on the design and specification of interfaces. Two models were designed: a task model (MAD *) and a model for conceptual interface design (ICS), as well as procedures for moving from one model to the other, and links with the low level interface aspects (via the toolbox ILOG-Views). The work led to the implementation of the task analysis editor EMAD and to a software design environment (ALACIE).

* Delouis I., & Pierret C. (1991) : EMAD, manuel de référence, Rapport interne INRIA.

* Gamboa-Rodriguez, F. & Scapin, D. L. (1997). Editing MAD* task descriptions for specifying user interfaces, at both semantic and presentation levels, in Proceedings DSV-IS '97, 4th International Eurographics Workshop on Design, Specification, and Verification of Interactive Systems, Granada, Spain, 4-6 June 1997.

* Pierret, C., Delouis, I. & Scapin, D. L. (1989). Un outils d’acquisition et de representation des tâches orienté-objets (Rapport de recherche N° 1063). Rocquencourt, France : Institut National de Recherche en Informatique et en Automatique.

* Lucquiaud, V., Scapin, D. L. et Jambon, F. (2002). Outils de modélisation des tâches utilisateurs : exigences du point de vue utilisation. IHM 2002, Poitiers, 26-29 Novembre 2002.

* Scapin, D. L. (1988). Vers des outils formels de description des tâches orientés conception d’interfaces (Rapport de recherche N° 893). Rocquencourt, France : Institut National de Recherche en Informatique et en Automatique.

* Scapin, D. L. et Bastien, J. M. C., (2001). Analyse des tâches et aide ergonomique à la conception : l'approche MAD* in Systèmes d’information et Interactions homme-machine, C. Kolski (Ed.), Hermés

* Scapin, D. L. & Pierret-Golbreich, C. (1990). Towards a method for task description. In L. Berlinguet and D. Berthelette (Eds.), Work with Display Unit. Amsterdam : Elsevier, 371-380.

* Sebillotte S. (1991) : Décrire des tâches selon les objectifs des opérateurs, de l'interview à la formalisation, Le Travail Humain 54,3 p193-223

* Sebillotte, S. & Scapin, D. L. (1994). From users' task knowledge to high level interface specification. International Journal of Human-computer Interaction, 6, 1-15.

## Prototask extention

Prototask is a new task model simulator, which does not visualize the task tree during the simulation. Prototask is a project initiated during the PhD of Thomas Lachaume.

Part of the K-MAD project, it leads on the K-MAD formalism, and allows users to run task models in a pseudo prototype way. Prototask encourage end-users and HCI designers to exchange around scenarios, in order to formalize the user needs.

## Publications

### Journals

* Allan FOUSSE, Patrick GIRARD, Laurent GUITTET, Thomas LACHAUME, Task model simulators: a review, Journal d'Interaction Personne-Système (JIPS), vol. 3, 2015

* Thomas Lachaume, Laurent Guittet, Patrick Girard, Allan Fousse, Task model simulators: a review, Journal d'Interaction Personne-Système, 2014, Volume 3, Issue 3, Special issue : Task Models

* Sybille CAFFIAU, Patrick GIRARD, A Global Process for Model-Driven Approaches in User Interface Design, A Global Process for Model-Driven Approaches in User Interface Design, edited by IGI Global, 2012, pp. 219-234

* Sybille Caffiau, Dominique Scapin, Patrick Girard, Mickaël Baron and Francis Jambon, Increasing the expressive power of task analysis: systematic comparison and empirical assessment of tool-supported task models , Interacting with computers, 2010 [link](http://dx.doi.org/10.1016/j.intcom.2010.06.003)

* Kamel Boukhalfa, Ladjel Bellatreche and Sybille Caffiau, De l’optimisation de requêtes aux outils d’administration des entrepôts de données, RSTI - ISI (Ingénierie des Systèmes d'Information), vol. 13(6/2008 ), 2009, pp. 33-62

### Conferences

* Thomas LACHAUME, Patrick GIRARD, Laurent GUITTET, Allan FOUSSE, Avoiding Inaccuracies in Task Models, 18th International Conference on Human-Computer Interaction (HCII 2016), Lecture Notes in Computer Science, edited by Springer, 2016, pp. 498--509

* Thomas LACHAUME, Patrick GIRARD, Laurent GUITTET, Allan FOUSSE, ProtoTask, new task model simulator, Human-Centered Software Engineering (Lachaume2012), edited by Winckler, Marco and Forbrig, Peter and Bernhaupt, Regina, Berlin, Heidelberg, edited by Springer Berlin Heidelberg, 2012, pp. 323--330

* Sybille CAFFIAU, Allan FOUSSE, Patrick GIRARD, Laurent GUITTET, Thomas LACHAUME, Comparaison de différentes approches de simulation dans les modèles de tâches, Ergonomie et interaction homme-machine (ERGO'IHM), ESTIA, 2012

* Thomas LACHAUME, Patrick GIRARD, Laurent GUITTET, Allan FOUSSE, Prototypage à partir de modèle de tâche : une étude pilote, 23ième Conférence Francophone Sur l'IHM 24-27 octobre 2011 Nice - Sophia Antipolis, edited by ACM New York, NY, USA ©2011, 2011

* Thomas Lachaume, Patrick Girard, Laurent Guittet, and Allan Fousse. 2011. Prototypage basé sur les modèles de tâches: une étude pilote. In 23rd French Speaking Conference on Human-Computer Interaction (IHM '11). ACM, New York, NY, USA, , Article 23 , 4 pages. DOI=10.1145/2044354.2044383 http://doi.acm.org/10.1145/2044354.2044383

* Sybille CAFFIAU and Patrick GIRARD, Approche dirigée par les modèles pour une conception des modèles de tâches et de dialogue des applications interactives, GDR-GPL Session IDM, Pau, Mars 2010, 2010

* Sybille Caffiau, Patrick Girard, Dominique Scapin and Laurent Guittet, Prise en compte de l'utilisateur dans le processus de conception d'une application d'édition de modèles de tâches, ERGO'IA (à paraître), 2010

* Sybille Caffiau, Patrick Girard, Laurent Guittet and Dominique Scapin, Hierarchical Structure: A Step for Jointly Designing Interactive Software Dialog and Task Model, HCI International 2009, vol. LNCS 5611, edited by Springer Berlin/Heidelberg, 2009, pp. 667-673

* Ladjel Bellatreche, Kamel Boukhalfa and Sybille Caffiau, ParAdmin: Un Outil d’Assistance à l’Administration et Tuning d’un Entrepôt de Données, Revue des Nouvelles Technologies de l'Information (EDA'2008), edited by Editionns Cépaduès, Juin, 2008

* Sybille Caffiau, Patrick Girard, Dominique Scapin, Laurent Guittet and Loé Sanou, Assessment of Object Use for Task Modeling, Engineering Interactive Systems (HCSE and TAMODIA), vol. LNCS 5247, edited by Springer Berlin/Heidelberg, 2008, pp. 14-28 [link](https://www.lias-lab.fr/publications/8133/2008-CaffiauTAMODIA.pdf)

* Sybille Caffiau, Laurent Guittet, Dominique Scapin and Loé Sanou, Utiliser les outils de simulation des modèles de tâches pour la validation des besoins utilisateur : une revue des problèmes, ERGO'IA, 2008, pp. 257-258 [link](https://www.lias-lab.fr/publications/8148/2008-ERGOIA-Caffiau.pdf)

* Sybille Caffiau, Dominique Scapin and Loé Sanou, Retour d'Expérience en Enseignement de la Modélisation de Tâches, ERGO'IA, 2008, pp. 135-143 [link](https://www.lias-lab.fr/publications/8141/2008-Caffiau-ERGOIA.pdf)

* Loé Sanou, Sybille Caffiau, Patrick Girard and Laurent Guittet, Evaluation de l'usage de l'exemple pour l'apprentissage de la programmation dans l'environnement MELBA, Ergo'IA 08, edited by ESTIA Bidart-Biarritz, ESTIA, 14-17 Octobre, 2008, pp. 213-220 [link](https://www.lias-lab.fr/publications/8185/2008-IA-Sanou.pdf)

* Loé Sanou, Sybille Caffiau, Patrick Girard and Laurent Guittet, Example usage evaluation for the programming learning in the MELBA environment, IADIS-MCCSIS 08, Amsterdam, The Netherlands, MCISIS, IADIS, 25-27 July, 2008, pp. 35-42 [link](https://www.lias-lab.fr/publications/8192/2008-IADIS-Sanou.pdf)

* Loé Sanou, Patrick Girard, Laurent Guittet and Sybille Caffiau, Tester la conformité d'une interface homme machine à son modèle de tâches, IHM 08, edited by AFIHM, Metz, France, Université Paul Verlaine / AFIHM, ACM, 2008, pp. 159-162 [link](https://www.lias-lab.fr/publications/8199/2008-IHM_08-Sanou.pdf)

* Sybille Caffiau, Patrick Girard, Dominique Scapin and Laurent Guittet, Generating Interactive Applications from task Models: a Hard Challenge, Task Models and Diagrams (TAMODIA), vol. LNCS 4849, 2007, pp. 267-272 [link](https://www.lias-lab.fr/publications/7907/2007-TMD-Caffiau.pdf)

* Mickaël Baron, Vincent Lucquiaud, Delphine Autard and Dominique Scapin, K-MADe : un environement pour le noyau du modèle de description de l'activité, 18ème Conférence Francophone sur l'Interaction Homme-Machine (IHM'2006), edited by ACM Press, Montréal, 2006, pp. 287-288 [link](https://www.lias-lab.fr/publications/7652/2006-IHM-Baron.pdf)

* Sybille Caffiau, Du modèle de tâches au modèle de dialogue des applications interactives, Rencontres Jeunes Chercheurs en IHM , Novembre, 2006 [link](https://www.lias-lab.fr/publications/7913/2006-IHM-Caffiau.pdf)

* Vincent Lucquiaud, Proposition d’un noyau et d’une structure pour les modèles de tâches orientés utilisateurs, 17ème Conférence Francophone sur l'Interaction Homme-Machine (IHM'2005), edited by ACM Press, Toulouse, 27-30 septembre, 2005, pp. 83-90 [link](https://www.lias-lab.fr/publications/7641/2005-IHM-Lucquiaud.pdf)

* Vincent Lucquiaud, Dominique Scapin and Francis Jambon, Outils de modélisation des tâches utilisateurs : exigences du point de vue utilisation, 14ème Conférence Francophone sur l'Interaction Homme-Machine (IHM'2002), edited by ACM Press, Poitiers, 27-29 novembre, 2002, pp. 243-246 [link](https://www.lias-lab.fr/publications/7630/2002-IHM-Lucquiaud.pdf)

### Thesis

* Sybille Caffiau, Approche dirigée par les modèles pour la conception et la validation des applications interactives : une démarche basée sur la modelisation des taches, Thesis, Ecole Nationale Superieure de Mecanique et d'Aerotechnique, 2009 [link](https://tel.archives-ouvertes.fr/tel-00461497/file/theseCAFFIAUSybille.pdf)

* Vincent Lucquiaud, Sémantique et outil pour la modélisation des tâches utilisateur : N-MDA, Thesis, Université de Poitiers / ENSMA / INRIA, Décembre, 2005

## Software licence agreement

Details the license agreement of KMADe: [LICENSE](LICENSE)

## Historic Contributors (core developers first followed by alphabetical order)

* [Mickael BARON(core developer)](https://www.lias-lab.fr/members/mickaelbaron)
* [Vincent LUCQUIAUD(core developer)](https://www.lias-lab.fr/members/vincentlucquiaud)
* Delphine AUTARD
* [Sybille CAFFIAU](https://www.lias-lab.fr/members/sybillecaffiau)
* [Allan FOUSSE](https://www.lias-lab.fr/members/sybillecaffiau)
* [Patrick GIRARD](https://www.lias-lab.fr/members/patrickgirard)
* [Laurent GUITTET](https://www.lias-lab.fr/members/laurentguittet)
* [Francis JAMBON](https://www.lias-lab.fr/members/francisjambon)
* [Thomas LACHAUME](https://www.lias-lab.fr/members/thomaslachaume)
* Dominique SCAPIN

## Code analysis

* Lines of Code: 77 284
* Programming Languages: Java, XML
