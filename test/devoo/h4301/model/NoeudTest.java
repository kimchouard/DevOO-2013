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
    
    public NoeudTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getId method, of class Noeud.
     */
    @Test
    public void testGetId(){
        System.out.println("getId");
        Noeud instance = new Noeud();
        assertNull("Id null",instance.getId());
    }
    
    /**
     * Test of construireAPartirDomXML method, of class Noeud.
     */
    @Test
    public void testConstruireAPartirDomXML() {
        System.out.println("construireAPartirDomXML");
        
        Element noeudDOMRacine = null;
        noeudDOMRacine.setAttribute("id","1");
        noeudDOMRacine.setAttribute("x","5");
        noeudDOMRacine.setAttribute("y","6");
        Noeud instance = new Noeud();
        instance.construireAPartirDomXML(noeudDOMRacine);
        // Check data update
        assertEquals("id",noeudDOMRacine.getAttribute("id"),instance.id);
        assertEquals("x",noeudDOMRacine.getAttribute("x"),instance.x);
        assertEquals("y",noeudDOMRacine.getAttribute("y"),instance.y);
    }   
    
}
