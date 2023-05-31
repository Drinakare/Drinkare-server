package sg.hsdd.drinkare.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.hsdd.drinkare.service.oauth.LoginResponse;
import sg.hsdd.drinkare.service.oauth.OAuth2Service;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class OAuthController {

    @Autowired
    private OAuth2Service oAuth2Service;

    @GetMapping("/kakao")
    public ResponseEntity<LoginResponse> login(@RequestParam String code){
        LoginResponse loginResponse = oAuth2Service.login("kakao", code);
        return ResponseEntity.ok().body(loginResponse);
    }

}
