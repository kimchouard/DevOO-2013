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
     * Construction d'un plan a partir d'un fichier XML Crée d'abord tous les
     * noeuds puis les tronçons
     *
     * @param nomFichier path du fichier XML contenant le plan
     * @throws Exception levées par le fichier XML lu
     * @return le plan créé
     */
    public Plan construirePlanAPartirXML(String nomFichier) throws Exception {
        Plan plan = new Plan();
        Tournee.getInstance().resetTournee();
        Tournee.getInstance().setPlan(plan);
        File planXML = ouvrirFichier(nomFichier);

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

            } else
                throw new Exception("Le premier noeud du plan n'est pas réseau");
        }

        return plan;

    }

    /**
     * Construction de la tournée à partir d'un fichier XML. Fait appel au
     * constructeur à partir de DomXML de tournée.
     *
     * @param nomFichier path du fichier XML contenant les livraisons
     * @return
     * @throws Exception
     */
    public Tournee construireLivraisonAPartirXML(String nomFichier) throws Exception {

        Tournee tournee = Tournee.getInstance();

        File tourneeXML = ouvrirFichier(nomFichier);
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
                tournee.cheekPlageHoraires();

            } else
                throw new Exception("Le premier noeud du fichier de livraison n'est pas journéeType");

        }

        return tournee;
    }
}
