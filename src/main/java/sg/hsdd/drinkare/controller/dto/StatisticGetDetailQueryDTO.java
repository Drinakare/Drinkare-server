package sg.hsdd.drinkare.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatisticGetDetailQueryDTO {
    private Long userId;
    private String date;
}
