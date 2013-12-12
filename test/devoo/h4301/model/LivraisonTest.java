/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import devoo.h4301.fixture.domXml;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.w3c.dom.Element;

/**
 *
 * @author cdupuis
 */
public class LivraisonTest {
    
    Livraison livraison;
    Plan plan;
    Noeud destination;
    PlageHoraire horraires;
    
    
    public LivraisonTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        Element xmlHorraire = domXml.createDomElement("<Plage heureDebut=\"8:0:0\" heureFin=\"12:0:0\"></Plage>");
        horraires = new PlageHoraire();
        horraires.construireAPartirDomXML(xmlHorraire);
        
        Element noeudXml = domXml.createDomElement("<Noeud id='13' x='14' y='171'></Noeud>");
        destination = new Noeud();
        destination.construireAPartirDomXML(noeudXml);
        
        plan = new Plan();
        plan.addNoeud(destination);
        
        livraison = new Livraison();
    }
    
    @After
    public void tearDown() {
        livraison = null;
        plan = null;
        horraires = null;
    }
    
    /**
     * Creation d'une livraison valide
     * @throws Exception levée sur la création d'une livraison lors du parsing
     * @result La livraison seras stockée sous la bonne forme
     */
    @Test
    public void construireValide () throws Exception {
        String xmlString;
        xmlString = "<Livraison id='1' client='611' adresse='13' />";
        
        Element livraisonElement = domXml.createDomElement(xmlString);
        
        livraison.construireAPartirDomXML(livraisonElement, horraires, plan);
        assertTrue("Same client Id", livraison.getClient().getId() == 611);
        assertEquals("Same node instance", livraison.getDestination(), destination);
    }
    
    /**
     * Construction d'une livraison sans que le noeud soit contenu dans le plan
     * @throws Exception levée pour retrouver le noeud
     * @result Retourne une exception lors de la création
     */
    @Test(expected = Exception.class)
    public void construireSansNoeudContenu () throws Exception {
        String xmlString;
        xmlString = "<Livraison id='1' client='611' adresse='3'/>";
        
        Element livraisonElement = domXml.createDomElement(xmlString);
        
        livraison.construireAPartirDomXML(livraisonElement, horraires, plan);
    }
    
    /**
     * Construction d'un noeud malformé
     * @throws Exception lovée lors de la création du client
     * @result Retourne une exception
     */
    @Test(expected = Exception.class)
    public void construireLivraisonMalforme () throws Exception {
        String xmlString;
        xmlString = "<Livraison id='1'/>";
        
        Element livraisonElement = domXml.createDomElement(xmlString);
        
        livraison.construireAPartirDomXML(livraisonElement, horraires, plan);
    }
}
