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
