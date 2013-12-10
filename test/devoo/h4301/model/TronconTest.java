/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;

/**
 *
 * @author cdupuis
 */
public class TronconTest {
    
    public TronconTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /**
     * Test of construireAPartirDomXML method, of class Troncon
     */
    public void testConstruireAPartirDomXML(){
        System.out.println("construireAPartirDomXml");
        
        //Building test variables
        Element noeud = null;
        String nomRue = "toto";
        Double vitesse = 5.50;
        Double longueur = 2.45;
        Double epsilon = 0.01;
        Integer destId = 5;
        
        //Building the test xml document
        noeud.setAttribute("nomRue",nomRue);
        noeud.setAttribute("vitesse",String.valueOf(vitesse));
        noeud.setAttribute("longueur",String.valueOf(longueur));
        noeud.setAttribute("destination", String.valueOf(destId));
        
        //Creating instances
        Troncon instance = new Troncon();
        Noeud destination = new Noeud();
        destination.id = destId;
        Plan.getInstance().addNoeud(destination);
        
        instance.construireAPartirDomXML(noeud);
        
        //Test value
        assertEquals("rue",instance.nomRue,nomRue);
        assertEquals("vitesse",instance.vitesse,vitesse,epsilon);
        assertEquals("longueur",instance.longueur,longueur,epsilon);
        
        //Test destination
        assertNotNull("destination null",instance.destination);
        assertTrue("destination type",(instance.destination instanceof Noeud));
        assertEquals("destination id",instance.destination.id,destId);
        
        //Clean plan
        Plan.getInstance().removeNoeud(destId);
    }
}
