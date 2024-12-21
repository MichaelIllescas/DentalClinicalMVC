package com.imperialNet.DelntalClinicalMVC.service;


import com.imperialNet.DelntalClinicalMVC.dao.IDao;
import com.imperialNet.DelntalClinicalMVC.dao.PatientDaoH2;
import com.imperialNet.DelntalClinicalMVC.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService {

    private IDao<Patient> patientIDao;

    public PatientService() {
        this.patientIDao = new PatientDaoH2();
    }

    public Patient save (Patient patient){
        return patientIDao.save(patient);
    }

    public Patient findById(Integer id){
        return patientIDao.findById(id);
    }
    public void update (Patient patient){
        patientIDao.update(patient);
    }

    public void delete (Integer id){
        patientIDao.delete(id);
    }
    public List<Patient> findAll(){
        return patientIDao.findAll();
    }

    public Patient findByEmail(String email) {
        return patientIDao.findByString(email);
    }
}
