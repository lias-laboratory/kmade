package kmade.nmda.schema.expression;

import kmade.kmade.coreadaptator.ExpressGroup;
import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
public class GroupExpressExpression extends ExpressExpression {

    private static final long serialVersionUID = -3796792735826302638L;

    private Groupe myGroup;
    
    public GroupExpressExpression(String ie) throws ExpressException {
        super(ie);
        myGroup = ExpressGroup.getRefAbstractGroupFromName(ie);
        if (myGroup == null) {
            throw new ExpressException(ExpressConstant.GROUP_MESSAGE + " \"" + this.identExpress + "\" " + ExpressConstant.NOT_FOUNDED_ERROR + ".");
        }
    }

    public Groupe getGroup() {
        return myGroup;
    }  
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException { }
}
