package kmade.kmade.coreadaptator;
import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.Intervalle;

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
public class ExpressInterval { 
    public static String createInterval(){
        Oid  oidIntervalle =  InterfaceExpressJava.createEntity("metaobjet","Intervalle");
        return ( oidIntervalle.get() );
    }
    
    public static void removeInterval(String oid){
        Intervalle  m = (Intervalle) InterfaceExpressJava.prendre( new Oid(oid));
        m.delete();
    }
    
    public static void affRemoveInterval(String oid){
        Intervalle  m = (Intervalle) InterfaceExpressJava.prendre( new Oid(oid));
        m.affDelete();
    }
    
    public static String setIntervalName(String oid, String name ){
        Intervalle  m = (Intervalle) InterfaceExpressJava.prendre( new Oid(oid));
        m.setName(name);
        return m.getName();
    }
    
    public static void setIntervalDescription(String oid, String description){
        Intervalle  m  = (Intervalle) InterfaceExpressJava.prendre( new Oid(oid));
        m.setDescription (description);
    }
    
    public static void setIntervalMin(String oid, Integer min ){
        Intervalle  m = (Intervalle) InterfaceExpressJava.prendre( new Oid(oid));
        m.setMin(min);
    }
    
    public static void setIntervalMax(String oid, Integer max ){
        Intervalle  m  = (Intervalle) InterfaceExpressJava.prendre( new Oid(oid));
        m.setMax(max);
    }
    
    public static String[][] getArrayIntervals(){
        Object[] intervalles = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Intervalle" );
        if (intervalles.length==0) 
            return null;
        String[][] s = new String[intervalles.length][2];
        for ( int i=0; i<intervalles.length; i++){
            Intervalle intervalle = (Intervalle) intervalles[i];
            s[i][0]= intervalle.getName();
            s[i][1]= intervalle.getOid().get();
        }
        return s;
    }

    public static ArrayList<Intervalle> getIntervals() {
        ArrayList<Intervalle> lst = new ArrayList<Intervalle>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Intervalle");
        for (int i = 0; i < objs.length; i++) {
            Intervalle obj = (Intervalle) objs[i];
            lst.add(obj);
        }
        return lst;
    }
    
    public static Intervalle getInterval(String name) {
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Intervalle");
        for (int i = 0; i < objs.length; i++) {
            Intervalle obj = (Intervalle) objs[i];
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }
}
