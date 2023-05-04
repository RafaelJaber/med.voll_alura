package med.voll.api.controller;

import med.voll.api.dto.DoctorDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @PostMapping
    public String Create(@RequestBody DoctorDto doctorDto){
        System.out.println(doctorDto);
        return "Aqui maluco";
    }

}
