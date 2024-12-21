package com.imperialNet.DelntalClinicalMVC.controller;

import com.imperialNet.DelntalClinicalMVC.model.Patient;
import com.imperialNet.DelntalClinicalMVC.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String findPatientByEmail(Model model, @RequestParam("email") String email){
        Patient patient = patientService.findByEmail(email);
        model.addAttribute("name", patient.getName()) ;
        model.addAttribute("last_name", patient.getLastName());
        return "index";
    }
}
