package sg.hsdd.drinkare.controller.query;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.hsdd.drinkare.controller.dto.StatisticGetDetailQueryDTO;
import sg.hsdd.drinkare.controller.dto.StatisticGetInfoQueryDTO;
import sg.hsdd.drinkare.controller.dto.StatisticGetMonthInfoQueryDTO;
import sg.hsdd.drinkare.service.query.DrinkareQueryService;
import sg.hsdd.drinkare.service.vo.StatisticDetailVO;
import sg.hsdd.drinkare.service.vo.StatisticInfoVO;
import sg.hsdd.drinkare.service.vo.StatisticMonthVO;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/query")
public class DrinkareQueryController {

    @Autowired
    private DrinkareQueryService drinkareQueryService;

    @PostMapping("/getinfo")
    public StatisticInfoVO getInfo(@RequestBody StatisticGetInfoQueryDTO statisticGetInfoQueryDTO){
        return drinkareQueryService.getInfo(statisticGetInfoQueryDTO);
    }

    @PostMapping("/getdetail")
    public StatisticDetailVO getDetail(@RequestBody StatisticGetDetailQueryDTO statisticGetDetailQueryDTO){
        return drinkareQueryService.getDetail(statisticGetDetailQueryDTO);
    }

    @PostMapping("/getmonth")
    public StatisticMonthVO getMonth(@RequestBody StatisticGetMonthInfoQueryDTO statisticGetMonthInfoQueryDTO){
        return drinkareQueryService.getMonth(statisticGetMonthInfoQueryDTO);
    }
}
