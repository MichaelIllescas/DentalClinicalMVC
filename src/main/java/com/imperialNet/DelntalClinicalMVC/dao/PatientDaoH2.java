package com.imperialNet.DelntalClinicalMVC.dao;

import com.imperialNet.DelntalClinicalMVC.model.Address;
import com.imperialNet.DelntalClinicalMVC.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoH2 implements IDao<Patient>{
    private final static String SQL_INSERT = "INSERT INTO PATIENTS (NAME, "+
            " LAST_NAME, CARD_IDENTITY, ADMISSION_OF_DATE, ADDRESS_ID, EMAIL) VALUES (?,?,?,?,?,?)";
    private  final static String SQL_FIND_BY_ID="SELECT * PATIENTS WHERE ID=?";
    private final static String SQL_UPDATE ="UPDATE PATIENTS SET NAME=?, LAST_NAME =?, CARD_IDENTITY=?, "+
            "ADMISSION_OF_DATE=?, ADDRESS_ID=?, EMAIL=? WHERE ID =?";
    private  final static String SQL_DELETE="DELETE FROM PATIENTS WHERE ID=?";
    private final static  String SQL_SELECT_ALL="SELECT * FROM PATIENTS";
    private final static String SQL_SELECT_EMAIL="SELECT * FROM PATIENTS WHERE EMAIL=?";

    @Override
    public Patient save(Patient patient) {
        Connection connection =null;
        try {

            AddressDaoH2 addressDaoH2 =new AddressDaoH2();
            addressDaoH2.save(patient.getAddress());
            connection =DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastName());
            ps.setInt(3,patient.getCardIdentity());
            ps.setDate(4, Date.valueOf(patient.getAdmissionOfDate()));
            ps.setInt(5,patient.getAddress().getId());
            ps.setString(6, patient.getEmail());
            ps.execute();
            ResultSet rs =ps.getGeneratedKeys();
            while (rs.next()){
                patient.setId(rs.getInt(1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public Patient findById(Integer id) {
        Connection connection= null;
        Patient patient=null;
        try {
            connection= DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            AddressDaoH2 addressDaoH2=new AddressDaoH2();

            while (rs.next()){
               Address address= addressDaoH2.findById(rs.getInt(6));
                patient = new Patient(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getDate(5).toLocalDate(), address,rs.getString(7));

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public void update(Patient patient) {
        Connection connection= null;
        try {
            connection=DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_UPDATE);
            ps.setString(1,patient.getName());
            ps.setString(2,patient.getLastName());
            ps.setInt(3,patient.getCardIdentity());
            ps.setDate(4, Date.valueOf(patient.getAdmissionOfDate()));
            ps.setInt(5,patient.getAddress().getId());
            ps.setString(6,patient.getEmail());
            ps.setInt(7,patient.getId());

            ps.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Integer id) {
        Connection connection= null;
        try {
            connection=DB.getConnection();
           PreparedStatement ps= connection.prepareStatement(SQL_DELETE);
           ps.setInt(1,id);
           ps.execute();

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Patient> findAll() {
        Connection connection= null;
        Address address;
        List<Patient> patientList=new ArrayList<>();
        try {
            connection=DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            AddressDaoH2 addressDaoH2 =new AddressDaoH2();
            while (rs.next()){
                address= addressDaoH2.findById(rs.getInt(6)); //columna 6 porque es donde esta el id address
                patientList.add(new Patient(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getDate(5).toLocalDate(), address, rs.getString(7) ));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return patientList;
    }

    @Override
    public Patient findByString(String value) {
        Connection connection= null;
        Patient patient =null;

        try {
            connection=DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_SELECT_EMAIL);
            ps.setString(1,value);
            ResultSet rs= ps.executeQuery();
            AddressDaoH2 addressDaoH2=new AddressDaoH2();
            while (rs.next()){
                Address address= addressDaoH2.findById(rs.getInt(6)); // 7 porque es la columna id_addres
                patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),
                        rs.getDate(5).toLocalDate(), address, rs.getString(7));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        };
        return patient;
    }
}
