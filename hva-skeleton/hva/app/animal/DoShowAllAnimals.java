package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import java.util.List;
import java.util.Map;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    //FIXME implement command
    
    // Obtém todos os animais do hotel
    Map<String, Animal> animaisMap = _receiver.getAnimals();

    // Ordena os animais usando o método sortIds
    List<Animal> sortedAnimals = (List<Animal>) _receiver.sortIds(animaisMap);

    for (Animal animal : sortedAnimals) {
      _display.addLine(animal.visualiza(_receiver)); // Adiciona cada animal em uma nova linha
    }
  
    _display.display(); // Mostra todos os animais
  }
}
