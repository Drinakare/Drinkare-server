package sg.hsdd.drinkare.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatisticGetInfoQueryDTO {
    private Long userId;
    private String date;
}
