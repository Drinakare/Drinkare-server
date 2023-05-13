package sg.hsdd.drinkare.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrinkareDTO {
    private String id;
    private String user_id;
    private String date;
    private String soju;
    private String beer;
}
