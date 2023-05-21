package sg.hsdd.drinkare.controller.command;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sg.hsdd.drinkare.controller.dto.AlcoholSaveCommandDTO;
import sg.hsdd.drinkare.service.command.DrinkareCommandService;
import sg.hsdd.drinkare.service.command.S3Upload;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/command")
public class DrinkareCommandController {

    @Autowired
    private DrinkareCommandService drinkareCommandService;

    @Autowired
    private S3Upload s3Upload;

    @RequestMapping(value = "/save", method = RequestMethod.POST
            ,consumes = {MediaType.APPLICATION_JSON_VALUE ,MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public void uploadFile(
            @RequestPart("images") MultipartFile multipartFile,
            @RequestPart("data") AlcoholSaveCommandDTO alcoholSaveCommandDTO,
            HttpServletRequest request
    ) throws IllegalStateException, IOException {

        String url = s3Upload.upload(multipartFile);
        drinkareCommandService.save(alcoholSaveCommandDTO, url);
    }

}
