package sg.hsdd.drinkare.service.query;

import sg.hsdd.drinkare.controller.dto.StatisticGetDetailQueryDTO;
import sg.hsdd.drinkare.controller.dto.StatisticGetInfoQueryDTO;
import sg.hsdd.drinkare.controller.dto.StatisticGetMonthInfoQueryDTO;
import sg.hsdd.drinkare.service.vo.StatisticDetailVO;
import sg.hsdd.drinkare.service.vo.StatisticInfoVO;
import sg.hsdd.drinkare.service.vo.StatisticMonthVO;

public interface DrinkareQueryService {
    StatisticInfoVO getInfo(StatisticGetInfoQueryDTO statisticGetInfoQueryDTO);

    StatisticDetailVO getDetail(StatisticGetDetailQueryDTO statisticGetDetailQueryDTO);

    StatisticMonthVO getMonth(StatisticGetMonthInfoQueryDTO statisticGetMonthInfoQueryDTO);

}
