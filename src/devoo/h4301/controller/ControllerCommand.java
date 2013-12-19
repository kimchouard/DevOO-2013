/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.outils.Command;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Tournee;
import devoo.h4301.views.FenetrePrincipale;
import java.util.ArrayList;

/**
 *
 * @author pmdartus
 */
public class ControllerCommand {
    private final ArrayList <Command> undoStack = new ArrayList();
    private final ArrayList <Command> redoStack = new ArrayList();
    private ControleurPrincipal controleurPrincipal;
    private FenetrePrincipale fenetrePrincipale;

    /**
     * Constructeur du command controleur
     */
    public ControllerCommand() {
    }

    /**
     * Constructeur du command controleur
     * @param controleurPrincipal controleur princpical de l'application
     * @param fenParent fenetre principale de l'application
     */
    public ControllerCommand(ControleurPrincipal controleurPrincipal, FenetrePrincipale fenParent) {
        this.fenetrePrincipale = fenParent;
        this.controleurPrincipal = controleurPrincipal;
        this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
    }
    
    /**
     * Possibilité de revenir en arrière dans les changements
     * @return booleen décrivant la possibilité
     */
    public Boolean possibleUndo() {
        return undoStack.size() > 0;
    }
    
    /**
     * Possibilité de refaire une action précédement annulée
     * @return booleen décrivant la possibilité de refaire l'action
     */
    public Boolean possibleRedo() {
        return redoStack.size() > 0;
    }
    
    /**
     * Remettre à zéro l'état du controleur
     */
    public void resetCommand() {
        undoStack.clear();
        redoStack.clear();
        this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
    }
    
    /**
     * Ajouter une commander la pile de modification
     * @param livraison livraison considérée
     * @param deleted état de la modification sur la livraison
     */
    public void addCommand(Livraison livraison, Boolean deleted ) {
        redoStack.clear();
        Command executedCommand = new Command(livraison, deleted);
        undoStack.add(executedCommand);
        this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
    }
    
    /**
     * Retour sur la précédente commande
     * @param tournee tournée considérée
     * @throws Exception
     */
    public void undo(Tournee tournee) throws Exception {
        if (this.possibleUndo()) {
            Command lastCommand = undoStack.get(undoStack.size()-1);
            Command invertedCommand = ControllerCommand.invertCommand(tournee, lastCommand);
            
            redoStack.add(invertedCommand);
            undoStack.remove(undoStack.size()-1);
            
            this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
            
            this.controleurPrincipal.reloadUI();
        } else {
            throw new Exception("Aucune commande a undo");
        }
    }
    
    /**
     * Annule le dernier retour en arrière réalisé
     * @param tournee tournée considérée
     * @throws Exception
     */
    public void redo(Tournee tournee) throws Exception {
        if (this.possibleRedo()) {
            Command commandToRedo = redoStack.get(redoStack.size()-1);
            Command invertedCommand = ControllerCommand.invertCommand(tournee, commandToRedo);
            
            undoStack.add(invertedCommand);
            redoStack.remove(redoStack.size()-1);
            
            this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
            
            this.controleurPrincipal.reloadUI();
        } else {
            throw new Exception("Aucune commande a redo");
        }
    }
    
    /**
     * Inverser la commander passée en parametre
     * @param tournee tournée considérée
     * @param command commande considérée
     * @return commande réalisée
     * @throws Exception
     */
    public static Command invertCommand(Tournee tournee, Command command) throws Exception {
        if (command.getDeleted()) {
            tournee.addLivraison(command.getLivraison());
        } else {
            tournee.supprimerLivraison(command.getLivraison());
        }
        
        command.setDeleted(!command.getDeleted());
        return command;
    }
}
