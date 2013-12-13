/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author cdupuis
 */
public class ItineraireTest {
    Itineraire instance;
    
    public ItineraireTest() {
        
    }
    
    @Before
    public void setUp(){
        instance = new Itineraire();
    }
    
    @Test
    public void setEnsembleTronconsTest(){
        LinkedList<Troncon> test = new LinkedList<>();
        Noeud n1 = new Noeud();
        Noeud n2 = new Noeud();
        Noeud n3 = new Noeud();
        Noeud n4 = new Noeud();
        Troncon tr1 = new Troncon(n1,n2,"a",2.50,1.50);
        Troncon tr2 = new Troncon(n2,n3,"b",2.50,1.50);
        Troncon tr3 = new Troncon(n3,n4,"c",2.50,1.50);
        test.add(tr1);
        test.add(tr2);
        test.add(tr3);
        instance.setEnsembleTroncons(test);
        int i;
        int max = instance.getEnsembleTroncons().size();
        for (i=0;i<max;i++)
        {
            assertEquals(instance.getEnsembleTroncons().get(i),test.get(i));
        }
    } 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
