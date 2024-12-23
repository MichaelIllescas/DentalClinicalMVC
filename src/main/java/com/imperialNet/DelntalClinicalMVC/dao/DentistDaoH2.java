package com.imperialNet.DelntalClinicalMVC.dao;

import com.imperialNet.DelntalClinicalMVC.model.Address;
import com.imperialNet.DelntalClinicalMVC.model.Dentist;
import com.imperialNet.DelntalClinicalMVC.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistDaoH2 implements IDao<Dentist> {
    private static final String SQL_INSERT="INSERT INTO DENTISTS (REGISTRATION, "+
        "NAME, LAST_NAME) VALUES (?,?,?);";
    private  final static String SQL_FIND_BY_ID="SELECT * FROM DENTISTS WHERE ID=?";
    private final static String SQL_UPDATE ="UPDATE DENTISTS SET REGISTRATION=?, NAME=?, LAST_NAME =? "+
            " WHERE ID =?";
    private  final static String SQL_DELETE="DELETE FROM DENTISTS WHERE ID=?";
    private final static  String SQL_SELECT_ALL="SELECT * FROM DENTISTS";
    @Override
    public Dentist save(Dentist dentist) {
        Connection connection =null;
        try {
            connection =DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); //esto genera las ids
            ps.setInt(1, dentist.getRegistration());
            ps.setString(2, dentist.getName());
            ps.setString(3,dentist.getLastName());
            ps.execute();
            ResultSet rs =ps.getGeneratedKeys();
            while (rs.next()){
                dentist.setId(rs.getInt(1));
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
        return dentist;
    }

    @Override
    public Dentist findById(Integer id) {
        Connection connection= null;
        Dentist dentist=null;
        try {
            connection= DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();


            while (rs.next()){

                dentist = new Dentist(rs.getInt(1), rs.getInt(2),rs.getString(3),
                        rs.getString(4));

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
        return dentist;
    }

    @Override
    public void update(Dentist dentist) {
        Connection connection= null;
        try {
            connection=DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_UPDATE);
            ps.setInt(1,dentist.getRegistration());
            ps.setString(2,dentist.getName());
            ps.setString(3,dentist.getLastName());
            ps.setInt(4,dentist.getId());

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
    public List<Dentist> findAll() {
        Connection connection= null;
        List<Dentist> dentistList=new ArrayList<>();
        try {
            connection=DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){

                dentistList.add(new Dentist(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4) ));
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
        return dentistList;
    }

    @Override
    public Dentist findByString(String value) {
        return null;
    }
}
