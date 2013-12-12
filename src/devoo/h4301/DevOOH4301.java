/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301;

import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.controller.LecteurXml;
import devoo.h4301.views.FenetrePrincipale;

/**
 *
 * @author chouard
 */
public class DevOOH4301 {

    // DEFINE CONSTANTS***********
    public static final double EPSILON = 0.000001;
    //****************************
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenetrePrincipale fp = new FenetrePrincipale();
                fp.setVisible(true);
                fp.controleurPrincipal = new ControleurPrincipal(fp.getpGauche(), fp);
            }
        });
    }

}
