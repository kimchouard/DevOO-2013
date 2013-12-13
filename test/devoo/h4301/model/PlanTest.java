/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

import devoo.h4301.fixture.domXml;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;

/**
 *
 * @author pmdartus
 */
public class PlanTest {
    Plan plan;
    
    @Before
    public void setUp() {
        plan = new Plan();
    }
    
    @After
    public void tearDown() {
        plan = null;
    }

    /**
     * Ajouter un noeud dans le plan
     * @throws Exception levé à la creation du noeud
     * @result Ajout effectif du noeud dans l'array
     */
    @Test
    public void testAddNoeud() throws Exception {
        Element noeudXml = domXml.createDomElement("<Noeud id='13' x='14' y='171'></Noeud>");
        
        Noeud n1 = new Noeud();
        n1.construireAPartirDomXML(noeudXml);
        
        plan.addNoeud(n1);
        
        assertEquals("Same length", plan.getNoeuds().size(), 1);
        assertEquals ("Same instnce", plan.getNoeuds().get(0), n1);
        
        assertEquals ("Update minX", plan.getMinX(), 14);
        assertEquals ("Update maxX", plan.getMaxX(), 14);

        assertEquals ("Update minX", plan.getMinY(), 171);
        assertEquals ("Update maxX", plan.getMaxY(), 171);
    }
    
    /**
     * Ajouter un noeud dans le plan alors que l'Id existe déjà
     * @throws Exception levé à la creation du noeud
     * @result Ajout refusé
     */
    @Test(expected = Exception.class)
    public void testAddNoeudExistant() throws Exception {
        Element noeudXml = domXml.createDomElement("<Noeud id='13' x='14' y='171'></Noeud>");
        
        Noeud n1 = new Noeud();
        n1.construireAPartirDomXML(noeudXml);
        plan.addNoeud(n1);
        plan.addNoeud(n1);
    }

    /**
     * Suppression d'un noeud qui n'existe pas.
     * @throws Exception levé lors de la suppression
     * @result Suppression impossible
     */
    @Test(expected = Exception.class)
    public void supprimerNoeudImpossible() throws Exception {
        plan.removeNoeud(1);
    }
    
    /**
     * Ajouter un noeud dans le plan
     * @throws Exception levé à la creation du noeud
     * @result Ajout effectif du noeud dans l'array
     */
    @Test
    public void supprimerNoeud() throws Exception {
        Element noeudXml1 = domXml.createDomElement("<Noeud id='13' x='14' y='171'></Noeud>");
        Noeud n1 = new Noeud();
        n1.construireAPartirDomXML(noeudXml1);
        plan.addNoeud(n1);
        
        Element noeudXml2 = domXml.createDomElement("<Noeud id='28' x='9' y='11'></Noeud>");
        Noeud n2 = new Noeud();
        n2.construireAPartirDomXML(noeudXml2);
        plan.addNoeud(n2);
        
        plan.removeNoeud(13);
        
        assertEquals("Same length", plan.getNoeuds().size(), 1);
        assertEquals ("Same instnace", plan.getNoeuds().get(0), n2);
    }
    
    /**
     * Retouver un noeud qui n'existe pas.
     * @throws Exception levé lors de la suppression
     * @result Retroune une exception impossible
     */
    @Test(expected = Exception.class)
    public void retourverNoeudNonExistant() throws Exception {
        plan.getNoeudById(0);
    }

    /**
     * Ajouter un troncon au plan
     */
    @Test
    public void testAddTroncon() {
    }

    /**
     * Retrouve un noeud stocké dans le plan
     * @throws Exception levé à la creation du noeud
     * @result Retrouver la bonne instace de noeud
     */
    @Test
    public void testGetNoeudById() throws Exception {
        Element noeudXml1 = domXml.createDomElement("<Noeud id='13' x='14' y='171'></Noeud>");
        Noeud n1 = new Noeud();
        n1.construireAPartirDomXML(noeudXml1);
        plan.addNoeud(n1);
        
        assertEquals ("Same instnace", plan.getNoeudById(13), n1);
    }
    
    

    /**
     * Test of construireAPartirDomXML method, of class Plan.
     */
    @Test
    public void testConstruireAPartirDomXML() throws Exception {
    }
    
}
