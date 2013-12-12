/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Command;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Tournee;
import java.util.ArrayList;

/**
 *
 * @author pmdartus
 */
public class ControllerCommand {
    private ArrayList <Command> undoStack = new ArrayList();
    private ArrayList <Command> redoStack = new ArrayList();

    public ControllerCommand() {
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
    }
    
    public void addCommand(Livraison livraison, Boolean deleted ) {
        redoStack.clear();
        Command executedCommand = new Command(livraison, deleted);
        undoStack.add(executedCommand);
    }
    
    public void undo(Tournee tournee) throws Exception {
        if (this.possibleUndo()) {
            Command lastCommand = undoStack.get(undoStack.size());
            Command invertedCommand = ControllerCommand.invertCommand(tournee, lastCommand);
            
            redoStack.add(invertedCommand);
            undoStack.remove(undoStack.size());
        } else {
            throw new Exception("Aucune commande a undo");
        }
    }
    
    public void redo(Tournee tournee) throws Exception {
        if (this.possibleRedo()) {
            Command commandToRedo = redoStack.get(redoStack.size());
            Command invertedCommand = ControllerCommand.invertCommand(tournee, commandToRedo);
            
            undoStack.add(invertedCommand);
            redoStack.remove(redoStack.size());
        } else {
            throw new Exception("Aucune commande a redo");
        }
    }
    
    public static Command invertCommand(Tournee tournee, Command command) {
        if (command.getDeleted()) {
            tournee.addLivraison(command.getLivraison());
        } else {
            // Supprimer livraison
        }
        
        command.setDeleted(!command.getDeleted());
        return command;
    }
}
