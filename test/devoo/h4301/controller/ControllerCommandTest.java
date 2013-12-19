/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Livraison;
import devoo.h4301.model.PlageHoraire;
import devoo.h4301.model.Tournee;
import devoo.h4301.views.FenetrePrincipale;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author pmdartus
 */
public class ControllerCommandTest {
    
    ControllerCommand controllerCommand;
    FenetrePrincipale fenPrinc;

    
    @Before
    public void setUp() {
        fenPrinc = new FenetrePrincipale();
    }
    
    @After
    public void tearDown() {
        controllerCommand = null;
    }

    /**
     * Test of possibleUndo method, of class ControllerCommand.
     */
    @Test
    public void testPossibleUndo() {
        assertEquals("Undo is not possible", controllerCommand.possibleUndo(), false);      

        Livraison liv = new Livraison();
        controllerCommand.addCommand(liv, false);
        
        assertEquals("Undo is possible", controllerCommand.possibleUndo(), true);        
    }

    /**
     * Test of resetCommand method, of class ControllerCommand.
     */
    @Test
    public void testResetCommand() {
        Livraison liv = new Livraison();
        controllerCommand.addCommand(liv, true);
        
        assertEquals("Undo is possible", controllerCommand.possibleUndo(), true); 
        
        controllerCommand.resetCommand();
        
        assertEquals("All undo and redo deleted", controllerCommand.possibleUndo(), false); 
    }

    /**
     * Test of undo method, of class ControllerCommand.
     */
    @Test
    public void testUndo() throws Exception {
    }

    /**
     * Test of redo method, of class ControllerCommand.
     */
    @Test
    public void testRedo() throws Exception {
    }

    /**
     * Test of invertCommand method, of class ControllerCommand.
     */
    @Test
    public void testInvertCommand() {
    }
    
}
