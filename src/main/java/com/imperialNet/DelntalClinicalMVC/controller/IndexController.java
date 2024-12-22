package com.imperialNet.DelntalClinicalMVC.controller;

import com.imperialNet.DelntalClinicalMVC.model.Dentist;
import com.imperialNet.DelntalClinicalMVC.model.Patient;
import com.imperialNet.DelntalClinicalMVC.service.DentistService;
import com.imperialNet.DelntalClinicalMVC.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

    private final PatientService patientService;
    private final DentistService  dentistService;

    public IndexController(PatientService patientService, DentistService dentistService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @GetMapping
    public String findPatientByEmail(Model model, @RequestParam("email") String email,
                                     @RequestParam("id") Integer dentisId){
        Patient patient = patientService.findByEmail(email);
        Dentist dentist = dentistService.findById(dentisId);
        model.addAttribute("name", patient.getName()) ;
        model.addAttribute("last_name", patient.getLastName());
        model.addAttribute("dentistName", dentist.getName());
        model.addAttribute("dentistLastName", dentist.getLastName());
        model.addAttribute("dentistmatricule", dentist.getRegistration());
        return "index";
    }

}
