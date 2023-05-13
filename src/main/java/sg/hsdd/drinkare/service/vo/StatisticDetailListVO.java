package sg.hsdd.drinkare.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticDetailListVO {
    private Long people;
    private Long soju;
    private Long beer;
}
