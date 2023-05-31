package sg.hsdd.drinkare.service.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
//    private Long age;
    private String accessToken;
    private String refreshToken;
    private Boolean isNew;
}
