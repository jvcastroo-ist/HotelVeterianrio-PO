package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownTreeKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreDuplicateTreeKeyException;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.core.exception.CoreUnknownTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idTree", Prompt.treeKey());
    addStringField("nameTree", Prompt.treeName());
    addIntegerField("ageTree", Prompt.treeAge());
    addIntegerField("difficultyTree", Prompt.treeDifficulty());
    addOptionField("typeTree", Prompt.treeType(), "CADUCA", "PERENE");
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
        _receiver.createTree(stringField("idTree"),
                  stringField("nameTree"),
                  integerField("ageTree"),
                  integerField("difficultyTree"),
                  stringField("typeTree")
        );
        _receiver.registerTree(stringField("idHabitat"), stringField("idTree"));
        _display.addLine(_receiver.getArvore(stringField("idTree")));
        _display.display();
    } catch (CoreDuplicateTreeKeyException t) {
      throw new DuplicateTreeKeyException(t.getId());
    } catch(CoreUnknownHabitatKeyException h){
      throw new UnknownHabitatKeyException(h.getId());
    } catch(CoreUnknownTreeKeyException t){
      throw new UnknownTreeKeyException(t.getId());
    }
  }
}
