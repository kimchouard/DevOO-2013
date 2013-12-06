/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Plan;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Leslie Breynat
 */
public class LecteurXml {

    /**
     *
     */
    public LecteurXml() {
    }

    /**
     *
     * @return
     */
    public File ouvrirFichier(){
        File xml = new File("C:/Users/Leslie Breynat/Desktop/plan10x10.xml");
                return xml;
    }

    /**
     *
     * @return
     */
    public Plan construirePlanAParirXML(){
Plan plan = new Plan();

File planXML = ouvrirFichier();

if (planXML != null) {
              try {
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
}	catch (Exception e) {
                System.out.println(e.getMessage());}



}
return plan;

}
}
