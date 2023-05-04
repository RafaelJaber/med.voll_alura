package med.voll.api.controller;

import med.voll.api.model.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @PostMapping
    public void Create(@RequestBody Patient patient) {
        System.out.println(patient);
    }

}
