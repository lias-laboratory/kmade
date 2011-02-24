package kmade.kmade.adaptatorFC.simulation;

import java.util.ArrayList;

import kmade.nmda.schema.metaobjet.ObjetConcret;
import kmade.nmda.schema.tache.Tache;
import kmade.nmda.schema.tache.User;

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
public class TokenRecordScenarioSimulation extends TokenSimulation {
	protected ArrayList<String> myUserValuePre = new ArrayList<String>();
	
    protected ArrayList<ObjetConcret> myConcretePre = new ArrayList<ObjetConcret>();

    protected ArrayList<String> myUserValuePost = new ArrayList<String>();
	
    protected ArrayList<ObjetConcret> myConcretePost = new ArrayList<ObjetConcret>();
	
    protected ArrayList<String> myUserValueIter = new ArrayList<String>();
	
    protected ArrayList<ObjetConcret> myConcreteIter = new ArrayList<ObjetConcret>();
	
    protected User refUser;
    
	public TokenRecordScenarioSimulation(TokenSimulation p) {
		super(p.getTask(), p.getAction());
		myUserValuePre = p.getTask().getPreExpression().getObjectValues();
		myUserValuePost = p.getTask().getEffetsDeBordExpression().getObjectValues();
		myUserValueIter = p.getTask().getIteExpression().getObjectValues();
		// TODO : les objets concrets ...
	}

    public TokenRecordScenarioSimulation(TokenSimulation p, User u) {
        this(p);
        refUser = u;
    }
    
    public TokenRecordScenarioSimulation(Tache p, int paction) {
        super(p,paction);
    }
    
	/**
	 * @return Returns the myConcreteIter.
	 */
	public ArrayList<ObjetConcret> getConcreteIter() {
		return myConcreteIter;
	}

	/**
	 * @param myConcreteIter The myConcreteIter to set.
	 */
	public void setConcreteIter(ArrayList<ObjetConcret> myConcreteIter) {
		this.myConcreteIter = myConcreteIter;
	}

	/**
	 * @return Returns the myConcretePost.
	 */
	public ArrayList<ObjetConcret> getConcretePost() {
		return myConcretePost;
	}

	/**
	 * @param myConcretePost The myConcretePost to set.
	 */
	public void setConcretePost(ArrayList<ObjetConcret> myConcretePost) {
		this.myConcretePost = myConcretePost;
	}

	/**
	 * @return Returns the myConcretePre.
	 */
	public ArrayList<ObjetConcret> getConcretePre() {
		return myConcretePre;
	}

	/**
	 * @param myConcretePre The myConcretePre to set.
	 */
	public void setConcretePre(ArrayList<ObjetConcret> myConcretePre) {
		this.myConcretePre = myConcretePre;
	}

	/**
	 * @return Returns the myUserValueIter.
	 */
	public ArrayList<String> getUserValueIter() {
		return myUserValueIter;
	}

	/**
	 * @param myUserValueIter The myUserValueIter to set.
	 */
	public void setUserValueIter(ArrayList<String> myUserValueIter) {
		this.myUserValueIter = myUserValueIter;
	}

	/**
	 * @return Returns the myUserValuePost.
	 */
	public ArrayList<String> getUserValuePost() {
		return myUserValuePost;
	}

	/**
	 * @param myUserValuePost The myUserValuePost to set.
	 */
	public void setUserValuePost(ArrayList<String> myUserValuePost) {
		this.myUserValuePost = myUserValuePost;
	}

	/**
	 * @return Returns the myUserValuePre.
	 */
	public ArrayList<String> getUserValuePre() {
		return myUserValuePre;
	}

	/**
	 * @param myUserValuePre The myUserValuePre to set.
	 */
	public void setUserValuePre(ArrayList<String> myUserValuePre) {
		this.myUserValuePre = myUserValuePre;
	}

    /**
     * @return Returns the refUser.
     */
    public User getUser() {
        return refUser;
    }

    /**
     * @param refUser The refUser to set.
     */
    public void setUser(User refUser) {
        this.refUser = refUser;
    }
}
