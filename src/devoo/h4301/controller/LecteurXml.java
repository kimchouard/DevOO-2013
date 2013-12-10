/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.controller;

import devoo.h4301.model.Plan;
import devoo.h4301.model.Tournee;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 *
 * @author Leslie Breynat
 */
public class LecteurXml {

    /**
     * Constructeur
     */
    public LecteurXml() {
    }

    /**
     * Ouvre un fichier XML
     *
     * @param nomFichier nom du fichier à ouvrir
     * @return fichier ouvert
     */
    public File ouvrirFichier(String nomFichier) {

        File xml = new File(nomFichier);
        return xml;

    }

    /**
     * Construction d'un plan a partir d'un fichier XML
     *
     * @return plan créé
     */
    public Plan construirePlanAPartirXML() throws Exception {
        Plan plan = new Plan();
        Tournee.getInstance().setPlan(plan);
        File planXML = ouvrirFichier("C:/Users/Leslie Breynat/Desktop/plan10x10.xml");

        if (planXML != null) {
            
                // creation d'un constructeur de documents a l'aide d'une fabrique

                DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                // lecture du contenu d'un fichier XML avec DOM
                Document document = constructeur.parse(planXML);
                Element racine = document.getDocumentElement();
                if (racine.getNodeName().equals("Reseau")) {
                    System.out.println("debut de la construction du plan");
//Passer le plan a la fabrique de plan a partir de domxml
                    plan.construireAPartirDomXML(racine);
//Gerer le cas de pb de lecture de fichier

                }
           

        }
        
        return plan;

    }

    public Tournee construireLivraisonAPartirXML()throws Exception{

        Tournee tournee = Tournee.getInstance();
        
                File tourneeXML = ouvrirFichier("C:/Users/Leslie Breynat/Desktop/livraison10x10-2.xml");
        System.out.println("fichier ouvert ");
        if (tourneeXML != null) {

            // creation d'un constructeur de documents a l'aide d'une fabrique
            DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // lecture du contenu d'un fichier XML avec DOM
            Document document = constructeur.parse(tourneeXML);
            Element racine = document.getDocumentElement();
            if (racine.getNodeName().equals("JourneeType")) {
                System.out.println("début de construction de tournee ");
                tournee.construireAPartirDomXML(racine);

            }

        }
        
      
        return tournee;
}
}
