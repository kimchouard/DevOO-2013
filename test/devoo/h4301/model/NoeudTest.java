/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;

/**
 *
 * @author pmdartus
 */
public class NoeudTest {
    
    Noeud instance;
    
    public NoeudTest() {
    }
    
    @Before
    public void setUp() { 
        instance = new Noeud();
    }


    /**
     * Test of getId method, of class Noeud.
     */
    @Test
    public void testGetId(){
        assertNull("Id null",instance.getId());
    }
    
    /**
     * Test of construireAPartirDomXML method, of class Noeud.
     */
    @Test
    public void testConstruireAPartirDomXML() {
        
        Element noeud = null;
        noeud.setAttribute("id","1");
        noeud.setAttribute("x","5");
        noeud.setAttribute("y","6");
        Noeud instance = new Noeud();
        instance.construireAPartirDomXML(noeud);
        // Check data update
        assertEquals("id",noeud.getAttribute("id"),instance.id);
        assertEquals("x",noeud.getAttribute("x"),instance.x);
        assertEquals("y",noeud.getAttribute("y"),instance.y);
    }   
    
}
