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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.project.GeneralInformation;
import fr.upensma.lias.kmade.kmad.schema.project.InterviewIndex;
import fr.upensma.lias.kmade.kmad.schema.project.Project;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public class ExpressProjectManager {
    public static String createProjectManager() {
	Oid oidObjAbs = InterfaceExpressJava.createEntity(
		ExpressConstant.PROJECT_PACKAGE, ExpressConstant.PROJECT_CLASS);
	return (oidObjAbs.get());
    }

    public static void removeAllGeneralInformationAndInterviewIndexEntity(
	    Oid oidProject) {
	// Suppression du projet
	Project myProject = (Project) InterfaceExpressJava.prendre(oidProject);
	GeneralInformation myGeneralInformation = myProject
		.getGeneralInformation();
	InterfaceExpressJava.remove(myGeneralInformation.getOid());

	ArrayList<InterviewIndex> myInterviewIndex = myProject
		.getInterviewIndex();
	for (int i = 0; i < myInterviewIndex.size(); i++) {
	    InterfaceExpressJava.remove(myInterviewIndex.get(i).getOid());
	}

	// Suppression du projet
	myProject.removeGeneralInformationAndInterviewIndexAttributes();
    }

    public static String getProjectManager() {
	Object[] temp = InterfaceExpressJava.prendreAllOidOfEntity(
		ExpressConstant.PROJECT_PACKAGE, ExpressConstant.PROJECT_CLASS);
	return ((Project) temp[0]).getOid().get();
    }

    public static void displayProjectManagerSPF(String oid) {
	Project m = (Project) InterfaceExpressJava.prendre(new Oid(oid));
	KMADEHistoryMessageManager.printlnMessage(m.toSPF());
    }

    public static String createInterviewIndex(Oid oidProject) {
	Oid oidInterviewIndex = InterfaceExpressJava.createEntity(
		ExpressConstant.PROJECT_PACKAGE,
		ExpressConstant.INTERVIEW_CLASS);
	InterviewIndex objInterviewIndex = (InterviewIndex) InterfaceExpressJava
		.prendre(oidInterviewIndex);
	Project objProject = (Project) InterfaceExpressJava.prendre(oidProject);

	objInterviewIndex.setInverseProject(objProject);
	objProject.addInterviewIndex(objInterviewIndex);
	return objInterviewIndex.getOid().get();
    }

    public static String createInterviewIndex(Oid oidProject,
	    String interviewed, String placeInformation, String statut,
	    String seniority, String date, String interviewType,
	    String searchedInformations, String interviewerName) {
	Oid oidInterviewIndex = InterfaceExpressJava.createEntity(
		ExpressConstant.PROJECT_PACKAGE,
		ExpressConstant.INTERVIEW_CLASS);
	InterviewIndex objInterviewIndex = (InterviewIndex) InterfaceExpressJava
		.prendre(oidInterviewIndex);
	objInterviewIndex.setInterviewedName(interviewed);
	objInterviewIndex.setPlaceInformation(placeInformation);
	objInterviewIndex.setStatut(statut);
	objInterviewIndex.setSeniority(seniority);
	objInterviewIndex.setDate(date);
	objInterviewIndex.setType(interviewType);
	objInterviewIndex.setSearchedInformations(searchedInformations);
	objInterviewIndex.setInterviewerName(interviewerName);

	Project objProject = (Project) InterfaceExpressJava.prendre(oidProject);

	objInterviewIndex.setInverseProject(objProject);
	objProject.addInterviewIndex(objInterviewIndex);
	return objInterviewIndex.getOid().get();
    }

    public static String createGeneralInformation(Oid oidProject,
	    String entreprise, String site, String typePost, String date,
	    String resources, String justification) {
	Oid oidGeneralInformation = InterfaceExpressJava.createEntity(
		ExpressConstant.PROJECT_PACKAGE,
		ExpressConstant.GENERAL_INFORMATION_CLASS);
	GeneralInformation objGeneralInformation = (GeneralInformation) InterfaceExpressJava
		.prendre(oidGeneralInformation);

	objGeneralInformation.setEntreprise(entreprise);
	objGeneralInformation.setSite(site);
	objGeneralInformation.setTypePost(typePost);
	objGeneralInformation.setDate(date);
	objGeneralInformation.setResources(resources);
	objGeneralInformation.setJustification(justification);

	Project objProject = (Project) InterfaceExpressJava.prendre(oidProject);

	objGeneralInformation.setInverseProject(objProject);
	objProject.setGeneralInformation(objGeneralInformation);
	return objGeneralInformation.getOid().get();
    }

    public static String createGeneralInformation(Oid oidProject) {
	Oid oidGeneralInformation = InterfaceExpressJava.createEntity(
		ExpressConstant.PROJECT_PACKAGE,
		ExpressConstant.GENERAL_INFORMATION_CLASS);
	GeneralInformation objGeneralInformation = (GeneralInformation) InterfaceExpressJava
		.prendre(oidGeneralInformation);
	Project objProject = (Project) InterfaceExpressJava.prendre(oidProject);

	objGeneralInformation.setInverseProject(objProject);
	objProject.setGeneralInformation(objGeneralInformation);
	return objGeneralInformation.getOid().get();
    }

    public static String[] getAllGeneralInformation(Oid oidProject) {
	Project myProject = (Project) InterfaceExpressJava.prendre(oidProject);
	GeneralInformation generalInformation = myProject
		.getGeneralInformation();
	String[] temp = new String[6];
	temp[0] = generalInformation.getEntreprise();
	temp[1] = generalInformation.getSite();
	temp[2] = generalInformation.getTypePost();
	temp[3] = generalInformation.getDate();
	temp[4] = generalInformation.getResources();
	temp[5] = generalInformation.getJustification();
	return temp;
    }

    public static ArrayList<Object[]> getAllInterviewIndex(Oid oidProject) {
	Project myProject = (Project) InterfaceExpressJava.prendre(oidProject);
	ArrayList<InterviewIndex> interviewIndex = myProject
		.getInterviewIndex();

	ArrayList<Object[]> temp = new ArrayList<Object[]>();
	for (int i = 0; i < interviewIndex.size(); i++) {
	    Object[] data = new Object[8];
	    data[0] = interviewIndex.get(i).getInterviewedName();
	    data[1] = interviewIndex.get(i).getPlaceInformation();
	    data[2] = interviewIndex.get(i).getStatut();
	    data[3] = interviewIndex.get(i).getSeniority();
	    data[4] = interviewIndex.get(i).getDate();
	    data[5] = interviewIndex.get(i).getType();
	    data[6] = interviewIndex.get(i).getSearchedInformations();
	    data[7] = interviewIndex.get(i).getInterviewerName();
	    temp.add(data);
	}
	return temp;
    }

    public static void setInterviewedName(String oid, String interviewedName) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setInterviewedName(interviewedName);
    }

    public static void setPlaceInformation(String oid, String placeInformation) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setPlaceInformation(placeInformation);
    }

    public static void setStatut(String oid, String statut) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setStatut(statut);
    }

    public static void setSeniority(String oid, String seniority) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setSeniority(seniority);
    }

    public static void setDate(String oid, String date) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setDate(date);
    }

    public static void setInterviewType(String oid, String type) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setType(type);
    }

    public static void setSearchedInformations(String oid,
	    String searchedInformations) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setSearchedInformations(searchedInformations);
    }

    public static void setInterviewerName(String oid, String interviewerName) {
	InterviewIndex m = (InterviewIndex) InterfaceExpressJava
		.prendre(new Oid(oid));
	m.setInterviewerName(interviewerName);
    }
}
