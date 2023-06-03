package sg.hsdd.drinkare.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticInfoVO {
    private String name;
    private Long age;
    private String gender;
    List<StatisticInfoDetailVO> list;
}
