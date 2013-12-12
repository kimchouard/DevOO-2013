/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pmdartus
 */
public class ClientTest {
    
    Client client;
    Integer id;
    
    @Before
    public void setUp() {
        id = 9;
        client = new Client(9);
    }
    
    @After
    public void tearDown() {
        client = null;
    }

    /**
     * Test of getId method, of class Client.
     */
    @Test
    public void testGetId() {
        assertEquals("Same client Id", id, client.getId());
    }
    
}
