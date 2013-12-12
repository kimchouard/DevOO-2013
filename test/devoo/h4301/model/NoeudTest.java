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
public class NoeudTest {
    
    Noeud instance;
    
    @Before
    public void setUp() { 
        instance = new Noeud();
    }
    
    @After
    public void tearDown() { 
        instance = null;
    }

    /**
     * Construction d'un noeud valide
     * @throws Exception levé sur la création du noeud
     * @result Creation d'un noeud avec les bon attributs
     */
    @Test
    public void construireNoeudValide() throws Exception {
        Integer noeudId = 1;
        String xmlString = "<Noeud id='" + noeudId + "' x='88' y='10'></Noeud>";
        
        Element noeud = domXml.createDomElement(xmlString);  
        instance.construireAPartirDomXML(noeud);
        
        assertTrue("Same Id", noeudId == instance.getId());
    }   
    
    /**
     * Creation d'un noeud sans id
     * @throws Exception levé au moment du parsing
     * @result Leve une exception de parsing
     */
    @Test(expected = NumberFormatException.class)
    public void construireNoeudSansId() throws Exception {
        Element noeud = domXml.createDomElement("<Noeud x=\"88\" y=\"171\"></Noeud>");
        instance.construireAPartirDomXML(noeud);
    }
    
    /**
     * Creation d'un noeud sans attributs
     * @throws Exception levé au moment du parsing
     * @result Leve une exception de parsing
     */
    @Test(expected = NumberFormatException.class)
    public void construireNoeudSansAttributs() throws Exception {
        Element noeud = domXml.createDomElement("<Noeud></Noeud>");
        instance.construireAPartirDomXML(noeud);
    }
    
    /**
     * Construction d'un noeud avec un attribut malformé
     * @throws Exception au moment du parssing
     * @result Leve une exception de parsing
     */
    @Test(expected = Exception.class)
    public void constructionNoeudInvalideAttributes() throws Exception {
        Element noeud = domXml.createDomElement("<Noeud id='1' x='qzd' y='171'></Noeud>");
        instance.construireAPartirDomXML(noeud);
    }
}
