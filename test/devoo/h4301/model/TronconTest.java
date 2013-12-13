/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import devoo.h4301.fixture.domXml;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 *
 * @author Leslie Breynat
 */
public class TronconTest {

    Troncon instance;
    Plan plan;

    @Before
    public void setUp() throws Exception {
        instance = new Troncon();
        plan = new Plan();
        Element noeudXml = domXml.createDomElement("<Noeud id='13' x='14' y='171'></Noeud>");

        Noeud n1 = new Noeud();
        n1.construireAPartirDomXML(noeudXml);

        plan.addNoeud(n1);
    }

    @After
    public void tearDown() {
        instance = null;
        plan = null;
    }

    /**
     * Construction d'un troncon valide
     *
     * @throws Exception levé sur la création du troncon
     * @result Creation d'un troncon avec les bon attributs
     */
    @Test
    public void construireTronconValide() throws Exception {

        String xmlString = "<TronconSortant nomRue=\"v1\" vitesse=\"4.600000\" longueur=\"546.100000\" destination=\"13\"/>";

        Element troncon = domXml.createDomElement(xmlString);
        instance.construireAPartirDomXML(troncon, plan);

        assertTrue("Good destination", plan.getNoeuds().get(0) == instance.getDestination());
        assertTrue("Good nomRue", instance.getNomRue().equals("v1"));
        assertTrue("Good vitesse", 4.600000 == instance.getVitesse());
    }

    /**
     * Creation d'un troncon sans destination
     *
     * @throws Exception levé au moment du parsing
     * @result Leve une exception de parsing
     */
    @Test(expected = NumberFormatException.class)
    public void construireTronconSansDestination() throws Exception {
        String xmlString = "<TronconSortant nomRue=\"v1\" vitesse=\"4.600000\" longueur=\"546.100000\" />";
        Element troncon = domXml.createDomElement(xmlString);
        instance.construireAPartirDomXML(troncon, plan);
    }

    /**
     * Creation d'un troncon sans attributs
     *
     * @throws Exception levé au moment du parsing
     * @result Leve une exception de parsing
     */
    @Test(expected = NumberFormatException.class)
    public void construireTronconSansAttributs() throws Exception {
        String xmlString = "<TronconSortant/>";
        Element troncon = domXml.createDomElement(xmlString);
        instance.construireAPartirDomXML(troncon, plan);
    }

    /**
     * Construction d'un troncon avec un attribut malformé
     *
     * @throws Exception au moment du parssing
     * @result Leve une exception de parsing
     */
    @Test(expected = Exception.class)
    public void constructionTronconInvalideAttributes() throws Exception {
         String xmlString = "<TronconSortant nomRue=\"4.3\" vitesse=\"4.600000\" longueur=\"546.100000\" destination=\"13.9\"/>";
        Element troncon = domXml.createDomElement(xmlString);
        instance.construireAPartirDomXML(troncon, plan);
    }
    
    /**
     * Construction d'un troncon avec une destination inexistante
     *
     * @throws Exception au moment du parssing
     * @result Leve une exception de parsing
     */
    @Test(expected = Exception.class)
    public void constructionTronconInvalideDestination() throws Exception {
         String xmlString = "<TronconSortant nomRue=\"v1\" vitesse=\"4.600000\" longueur=\"546.100000\" destination=\"20\"/>";
        Element troncon = domXml.createDomElement(xmlString);
        instance.construireAPartirDomXML(troncon, plan);
    }
}
