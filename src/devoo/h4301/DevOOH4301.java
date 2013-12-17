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
        Tournee tournee = new Tournee();
        Noeud entrepot = new Noeud(0, 0, 0);
        tournee.setEntrepot(entrepot);
        PlageHoraire ph = new PlageHoraire();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date debut = formatter.parse("12:00:00");
        Date fin = formatter.parse("13:00:00");
        ph.setDebut(debut);
        ph.setFin(fin);
        Noeud a = new Noeud(1, 1, 1);
        Noeud b = new Noeud(2,2,2);
        Noeud c = new Noeud(3,3,3);
        Noeud d = new Noeud(4,4,4);
        Troncon t1 = new Troncon(entrepot, a, null, 1, 2);
        Troncon t5 = new Troncon(a, entrepot, null, 5, 25);
        Troncon t2 = new Troncon(entrepot, c, null, 5, 25);
        Troncon t8 = new Troncon(c, d, null, 5, 25);
        Troncon t9 = new Troncon(d, b, null, 5, 25);
        Troncon t4 = new Troncon(b, a, null, 5, 25);
        Troncon t3 = new Troncon(c, entrepot, null, 1, 2);
        Troncon t10 = new Troncon(a, b, null, 1, 2);
        Troncon t6 = new Troncon(b, d, null, 1, 2);
        Troncon t7 = new Troncon(d, c, null, 1, 2);
        Livraison l2 = new Livraison();
        l2.setDestination(b);
        l2.setHorraire(ph);
        Livraison l3 = new Livraison();
        l3.setDestination(c);
        l3.setHorraire(ph);
        Plan plan = new Plan();
        plan.addNoeud(entrepot);
        plan.addNoeud(a);
        plan.addNoeud(b);
        plan.addNoeud(c);
        plan.addNoeud(d);
        plan.addTroncon(t1);
        plan.addTroncon(t2);
        plan.addTroncon(t3);
        plan.addTroncon(t4);
        plan.addTroncon(t5);
        plan.addTroncon(t6);
        plan.addTroncon(t7);
        plan.addTroncon(t8);
        plan.addTroncon(t9);
        plan.addTroncon(t10);
        tournee.setPlan(plan);
        tournee.addLivraison(l2);
        tournee.addLivraison(l3);
        System.out.println("coucou");
        GraphUtil instance = new GraphUtil(tournee);
        ArrayList<Date> PlagesHoraires = instance.getOrderedTabDuration(tournee.getLivraisons());
        instance.enterIdSuccAndCost(PlagesHoraires, tournee.getLivraisons(), tournee);
        for(int i=0;i<instance.getNbVertices();i++)
        {
            System.out.println("i: "+i);
            System.out.println("prev: "+instance.getEnsembleTrajets().get(i).getPrevLivraison().getDestination().getId());
            System.out.println("next: "+instance.getEnsembleTrajets().get(i).getNextLivraison().getDestination().getId());
        }        
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FenetrePrincipale fp = new FenetrePrincipale();
                fp.setVisible(true);
                fp.controleurPrincipal = new ControleurPrincipal(fp.getpGauche(), fp);
            }
        });*/
    }

}
