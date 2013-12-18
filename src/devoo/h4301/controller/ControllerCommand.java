/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Command;
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

    public ControllerCommand() {
    }

    ControllerCommand(ControleurPrincipal controleurPrincipal, FenetrePrincipale fenParent) {
        this.fenetrePrincipale = fenParent;
        this.controleurPrincipal = controleurPrincipal;
        this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
    }
    
    public Boolean possibleUndo() {
        return undoStack.size() > 0;
    }
    
    public Boolean possibleRedo() {
        return redoStack.size() > 0;
    }
    
    public void resetCommand() {
        undoStack.clear();
        redoStack.clear();
        this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
    }
    
    public void addCommand(Livraison livraison, Boolean deleted ) {
        redoStack.clear();
        Command executedCommand = new Command(livraison, deleted);
        undoStack.add(executedCommand);
        this.fenetrePrincipale.updateCommandState(this.possibleUndo(), this.possibleRedo());
    }
    
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
