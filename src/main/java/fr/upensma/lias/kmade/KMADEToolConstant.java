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
package fr.upensma.lias.kmade;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mickael BARON
 */
public class KMADEToolConstant {
    public static final String IMAGE_DIRECTORY_NAME = "/fr/upensma/lias/kmade/resources/images/";

    public static final String HTML_DIRECTORY_NAME = "/fr/upensma/lias/kmade/resources/html/";

    public static final String VELOCITY_TEMPLATE_DIRECTORY_NAME = "/fr/upensma/lias/kmade/resources/vm/";

    public static final String BUNDLE_FILE = "fr.upensma.lias.kmade.resources.languages.KMADeConstant";

    public static final String DTD_DIRECTORY_NAME = "/fr/upensma/lias/kmade/resources/dtd";

    public static final String TOOL_NAME = "KMADe";

    public static final String VERSION_VALUE = "1.4";

    public static String PACKAGE_PATH_NAME = "fr.upensma.lias.kmade.kmad.schema.";		
    
	public static final Map<String, String> MAP_CLASS = createMap();

	private static  Map<String, String> createMap(){
		Map<String, String>  map = new HashMap<String,String>();
		map.put("IntValue", "NumberValue"); // change for 1.0 -> 1.1 or before 1.0 to 1.0
		//change for 1.21
		map.put("tache.Tache","tache.Task");
		map.put("tache.Acteur", "tache.Actor");
		map.put("tache.ActeurSysteme", "tache.ActorSystem");
		map.put("tache.Evenement", "tache.CurrentEvents");
		map.put("tache.Evenement", "tache.Decomposition");
		map.put("tache.Enumere", "tache.Enumerated");
		map.put("tache.Evenement", "tache.Event");
		map.put("tache.Executant", "tache.Executor");
		map.put("tache.Materiel", "tache.Material");
		map.put("tache.Modalite", "tache.Modality");
		map.put("tache.ParcMachines", "tache.ParkMachines");
		map.put("tache.Individu", "tache.Person");
		map.put("tache.EffetsDeBordExpression", "tache.SideEffectExpression");
		return map;
	}

}
