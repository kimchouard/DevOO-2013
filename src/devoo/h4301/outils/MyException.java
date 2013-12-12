/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.outils;

/**
 * Classe étendant Exception, permettant de créer des exceptions particulières
 * adaptées au modèle
 *
 * @author Leslie Breynat
 */
public class MyException extends Exception {

    /**
     * Constructeur permettant de fixer le message remonté par l'exception
     * @param message attaché à l'exception
     */
    public MyException(String message) {
        super(message);
    }

}
