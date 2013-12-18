/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301;

import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.model.GraphUtil;
import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.PlageHoraire;
import devoo.h4301.model.Plan;
import devoo.h4301.model.Tournee;
import devoo.h4301.model.Troncon;
import devoo.h4301.outils.MyException;
import devoo.h4301.views.FenetrePrincipale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

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
    public static void main(String[] args) throws MyException, ParseException, Exception {
          
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FenetrePrincipale fp = new FenetrePrincipale();
                fp.setVisible(true);
                fp.controleurPrincipal = new ControleurPrincipal(fp.getpGauche(), fp.getpDroit(), fp);
            }
        });
    }

}
