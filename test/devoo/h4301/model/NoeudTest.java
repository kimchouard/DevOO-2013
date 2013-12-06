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
    public void testGetId() {
        System.out.println("getId");
        Noeud instance = new Noeud();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of construireAPartirDomXML method, of class Noeud.
     */
    @Test
    public void testConstruireAPartirDomXML() {
        System.out.println("construireAPartirDomXML");
        Element noeudDOMRacine = null;
        Noeud instance = new Noeud();
        instance.construireAPartirDomXML(noeudDOMRacine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
