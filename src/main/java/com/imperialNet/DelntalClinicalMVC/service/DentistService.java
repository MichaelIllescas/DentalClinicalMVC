package com.imperialNet.DelntalClinicalMVC.service;

import com.imperialNet.DelntalClinicalMVC.dao.DentistDaoH2;
import com.imperialNet.DelntalClinicalMVC.dao.IDao;
import com.imperialNet.DelntalClinicalMVC.model.Dentist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {

    private IDao<Dentist> dentistDao;

    public DentistService() {
        this.dentistDao = new DentistDaoH2();
    }

    public Dentist save(Dentist dentist) {
        return dentistDao.save(dentist);
    }

    public void update(Dentist dentist) {
        dentistDao.update(dentist);
    }
    public Dentist findById(int id) {
            return dentistDao.findById(id);
    }
    public List<Dentist> findAll() {
        return dentistDao.findAll();
    }
    public void delete(Integer id) {
        dentistDao.delete(id);
    }

}
