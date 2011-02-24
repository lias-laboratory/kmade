package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;


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
public class ReplayScenarioModel {
    
    private Tache rootTask;
    
    private ArrayList<TokenReplayScenarioSimulation> replayScenarioList = new ArrayList<TokenReplayScenarioSimulation>();
    
    public ReplayScenarioModel() {
    }
    
    public ReplayScenarioModel(ArrayList<TokenReplayScenarioSimulation> replayScenarioList, Tache rootTask) {
        this.replayScenarioList = replayScenarioList;
        this.rootTask = rootTask;
    }
    
    public ArrayList<TokenReplayScenarioSimulation> getTokenReplayScenarioSimulation() {
        return replayScenarioList;
    }
    
    public void setTokenReplayScenarioSimulation(ArrayList<TokenReplayScenarioSimulation> p) {
        replayScenarioList = p;
    }

    public Tache getRootTask() {
        return rootTask;
    }

    public void setRootTask(Tache rootTask) {
        this.rootTask = rootTask;
    }    
}
