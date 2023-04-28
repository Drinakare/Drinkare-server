package sg.hsdd.drinkare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sg.hsdd.drinkare.controller.dto.TestDTO;
import sg.hsdd.drinkare.service.TestService;
import sg.hsdd.drinkare.service.vo.TestVO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class DrinkareController {
    @Autowired
    private TestService testService;

    @PostMapping("/getname")
    public TestVO getName(@RequestBody TestDTO testDTO){
        return testService.testService(Long.parseLong(testDTO.getId()));
    }
}
