package sg.hsdd.drinkare.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AlcoholSaveCommandDTO {
    private Long userId;
    private Long people;
    private Long soju;
    private Long beer;
    private String date;
}
