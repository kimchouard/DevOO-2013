/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Plan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pmdartus
 */
public class LecteurXmlTest {
    
    LecteurXml lecteurXml;
    
    @Before
    public void setUp() {
        lecteurXml = new LecteurXml ();
    }
    
    @After
    public void tearDown() {
        lecteurXml = null;
    }

    /**
     * Test of construirePlanAPartirXML method, of class LecteurXml.
     * @throws java.lang.Exception
     */
    @Test
    public void testConstruirePlanAPartirXML() throws Exception {
        Plan plan = lecteurXml.construirePlanAPartirXML("./test/xml/plan.xml");
        
        assertEquals("Right number of nodes", plan.getNoeuds().size(), 4);        
        assertEquals("Max X updated", plan.getMaxX(), 103);     
        assertEquals("Min X updated", plan.getMinX(), 63);
    }
    
     /**
     * Test of construirePlanAPartirXML method, of class LecteurXml.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testConstruirePlanSansReseau() throws Exception {
        Plan plan = lecteurXml.construirePlanAPartirXML("./test/xml/plan_sansReaseau.xml");
    }
    
    /**
     * Test of construirePlanAPartirXML method, of class LecteurXml.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testConstruireTronconSansDestination() throws Exception {
        Plan plan = lecteurXml.construirePlanAPartirXML("./test/xml/plan_tronconNonComplet.xml");
    }
    
    /**
     * Test of construirePlanAPartirXML method, of class LecteurXml.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testConstruireVitesseNegative() throws Exception {
        Plan plan = lecteurXml.construirePlanAPartirXML("./test/xml/plan_vitesseNegative.xml");
    }
    
    /**
     * Test of construirePlanAPartirXML method, of class LecteurXml.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testConstruireDistanceNegative() throws Exception {
        Plan plan = lecteurXml.construirePlanAPartirXML("./test/xml/plan_distanceNegative.xml");
    }


    /**
     * Test of construireLivraisonAPartirXML method, of class LecteurXml.
     */
    @Test
    public void testConstruireLivraisonAPartirXML() throws Exception {
    }
    
}
