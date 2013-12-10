/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

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
        String xmlString = "<Noeud id=\"1\" x=\"88\" y=\"171\"></Noeud>";  

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try  
        {  
            builder = factory.newDocumentBuilder();  
            Document document = builder.parse( new InputSource( new StringReader( xmlString ) ) );
            
            Element noeud = document.getDocumentElement();
            instance.construireAPartirDomXML(noeud);
            
            assertTrue("Same Id", instance.getId() == Integer.parseInt(noeud.getAttribute("id")));
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
    }   
    
}
