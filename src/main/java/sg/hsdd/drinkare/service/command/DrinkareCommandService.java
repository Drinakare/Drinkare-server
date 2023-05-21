package sg.hsdd.drinkare.service.command;

import sg.hsdd.drinkare.controller.dto.AlcoholSaveCommandDTO;

public interface DrinkareCommandService {
    void save(AlcoholSaveCommandDTO alcoholSaveCommandDTO, String url);

}
