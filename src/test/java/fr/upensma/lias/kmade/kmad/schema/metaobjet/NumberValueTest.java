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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;

public class NumberValueTest {
    NumberValue n1;
    NumberValue n2;
    NumberValue n3;
    NumberValue n4;
    NumberValue n5;
    NumberValue n6;
    NumberValue n7;
    NumberValue n8;

    @Before
    public void setUp() throws Exception {
	n1 = new NumberValue();
	n2 = new NumberValue(new Integer(0), new Oid());
	n3 = new NumberValue(new Double(0), new Oid());
	n4 = new NumberValue("0");
	n5 = new NumberValue("0.");
	n6 = new NumberValue("0.0");
	n7 = new NumberValue("12");
	n8 = new NumberValue("7823.42");
    }

    @Test
    // Vérification que le type de renvoi de getValeur est le bon
    // Entier pour les chaines sans point
    // Double pour les chaines avec point
    public void testGetValeur() {

	// Bien qu'au sens strict, la valeur vaut zéro dans les deux cas, n1
	// renvoi un Integer et n2 un Double
	assertFalse(n1.getValeur().equals(n3.getValeur()));
	//
	assertTrue(n1.getValeur().equals(n4.getValeur()));
	// On obtient bien un Integer en passant "0" en paramètre au
	// constructeur ( donc à setValue)
	assertTrue(n2.getValeur().equals(n4.getValeur()));
	// La chaine "0." correspond à un double
	assertFalse(n5.getValeur().equals(n2.getValeur()));
	assertTrue(n5.getValeur().equals(n3.getValeur()));
	// La chaine "0.0" correspond à un double
	// assertTrue(n6.getValeur().equals(n3.getValeur()));
	assertFalse(n6.getValeur().equals(n2.getValeur()));
	//
	assertTrue(n7.getValeur().equals(new Integer(12)));
	//
	assertTrue(n8.getValeur().equals(new Double(7823.42)));

    }

    @Test
    public void testEqualOperator() {
	assertTrue(n2.equalOperator(n2));
    }
}
