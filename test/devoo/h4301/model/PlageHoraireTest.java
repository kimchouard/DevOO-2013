/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

import devoo.h4301.fixture.domXml;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;

/**
 *
 * @author pmdartus
 */
public class PlageHoraireTest {
    
    PlageHoraire plageHorraire;
    DateFormat formatter;
    
    @Before
    public void setUp() {
        plageHorraire = new PlageHoraire();
        formatter = new SimpleDateFormat("HH:mm:ss");
    }
    
    @After
    public void tearDown() {
        plageHorraire = null;
        formatter = null;
    }

    /**
     * Construction d'une plage horraire valide 
     * @throws Exception levé lors de la création de la plge horraire
     */
    @Test
    public void testConstruireAPartirDomXML() throws Exception {        
        Date debut = formatter.parse("14:0:0");
        Date fin = formatter.parse("16:0:0");
        
        Element horraireElement = domXml.createDomElement("<Plage heureDebut=\"14:0:0\" heureFin=\"16:0:0\"></Plage>");
        plageHorraire.construireAPartirDomXML(horraireElement);
        
        assertEquals("Same Hour for beginning", plageHorraire.getDebut().getTime(),debut.getTime());
        assertEquals("Same Hour for end", plageHorraire.getFin().getTime(),fin.getTime());
    }
    
}
