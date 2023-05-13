package sg.hsdd.drinkare.controller.command;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.hsdd.drinkare.controller.dto.AlcoholSaveCommandDTO;
import sg.hsdd.drinkare.service.command.DrinkareCommandService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/command")
public class DrinkareCommandController {

    @Autowired
    private DrinkareCommandService drinkareCommandService;

    @PostMapping("/save")
    public void save(@RequestBody AlcoholSaveCommandDTO alcoholSaveCommandDTO){
        drinkareCommandService.save(alcoholSaveCommandDTO);
    }
}
