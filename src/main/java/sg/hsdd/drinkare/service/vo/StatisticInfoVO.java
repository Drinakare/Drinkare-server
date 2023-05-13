package sg.hsdd.drinkare.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticInfoVO {
    private String name;
    private Long age;
    private Long month;
    private Long count;
    private Long soju;
    private Long beer;
}
