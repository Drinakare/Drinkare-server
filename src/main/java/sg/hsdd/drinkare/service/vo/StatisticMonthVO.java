package sg.hsdd.drinkare.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticMonthVO {
    //List<StatisticMonthDetailVO> list;
    List<String> partyDate;
}
