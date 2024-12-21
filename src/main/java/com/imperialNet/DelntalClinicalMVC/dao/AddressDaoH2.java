package com.imperialNet.DelntalClinicalMVC.dao;

import com.imperialNet.DelntalClinicalMVC.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoH2 implements IDao<Address>{

    private final static String SQL_INSERT = "INSERT INTO ADDRESSES (STREET, "+
            " NUMBER, LOCATION, PROVINCE) VALUES (?,?,?,?)";

    private final static String SQL_SELECT_ID= "SELECT * FROM ADDRESSES WHERE ID = ?";
    private final static String SQL_UPDATE = "UPDATE ADDRESSES SET STREET=?, NUMBER=?, LOCATION=?,"+
            " PROVINCE = ? WHERE ID =?";
    private  final static  String SQL_DELETE = "DELETE FROM ADDRESSES WHERE ID =?";

    private final static String SQL_SELECT_ALL = "SELECT * FROM ADDRESSES";

    @Override
    public Address save(Address address) {
        Connection connection =null;
        try {
            connection =DB.getConnection();
            PreparedStatement ps= connection.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,address.getStreet());
            ps.setInt(2,address.getNumber());
            ps.setString(3,address.getLocation());
            ps.setString(4,address.getProvince());
            ps.executeQuery();

            ResultSet rs= ps.getGeneratedKeys();
            while (rs.next()){
                address.setId(rs.getInt(1));
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
        return address;
    }

    @Override
    public Address findById(Integer id) {
        Connection connection =null;
        Address address=null;
        try {
            connection =DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_SELECT_ID);
             ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                address = new Address(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5));

            }

        } catch (Exception e){
        e.printStackTrace();
    }finally {
        try{
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
        return address;
    }

    @Override
    public void update(Address address) {
        Connection connection=null;
        try{
            connection= DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, address.getStreet());
            ps.setInt(2,address.getNumber());
            ps.setString(3, address.getLocation());
            ps.setString(4, address.getProvince());
            ps.setInt(5,address.getId());
            ps.execute();


        } catch (Exception e){
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
        Connection connection=null;
        try{
            connection= DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_DELETE);
            ps.setInt(1,id);
            ps.execute();


        } catch (Exception e){
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
    public List<Address> findAll() {
        Connection connection=null;
        Address addres=null;
        List<Address> addresses = new ArrayList<>();
        try{
            connection= DB.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                addres=new Address(rs.getInt(1),rs.getString(2),rs.getInt(3),
                rs.getString(4), rs.getString(5));
                addresses.add(addres);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return addresses;
    }

    @Override
    public Address findByString(String value) {
        return null;
    }
}
