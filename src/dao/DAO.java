/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafxapplication4.Person;

/**
 *
 * @author Inder
 */
public class DAO {
     private final String userName;
    private final String password;
    private final String host;

    String database = "person_javafx";
    
    public DAO(String host, String userName, String password)
    {
        this.userName = userName;
        this.password = password;
        this.host = host;
    }
    
     public void getPerson(TableView view) {
       
        ObservableList<Person> data = FXCollections.observableArrayList();
        try{
            //Create a connection to our JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Create a connection to the SQL Server
            //(Database, user, password)
            Connection con = DriverManager.getConnection
             (host + database, userName, password);
            
            Statement st = con.createStatement();
            
            String Query = "SELECT * FROM " + database; //MySQL statement
            
            ResultSet rs =st.executeQuery(Query);
            //ResultSet rs = st.executeUpdate(Query);
            
            
       
            
            while (rs.next()){
               Person p = new Person(rs.getString(1),rs.getString(2),rs.getString(3));
                data.add(p);
            }
            view.setItems(data);
            
            con.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
     
    public void addPerson(Person p){
    
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            //Try to connect to the database.  If the database does 
	    //not exist then create it.
	    //The database in this case is called "mycontacts"
	    
            try
            {
                con = DriverManager.getConnection
			(host + database, userName, password);
            
            }catch(Exception e)
            {
                con = DriverManager.getConnection
                (host, userName, password);
                Statement st = con.createStatement();
                st.executeUpdate("CREATE DATABASE " + database);
                con = DriverManager.getConnection
			(host + database, userName, password);
                
                
            }
            
             //String to add a new row goes here.
            
            String add = "INSERT INTO " + database + " (firstname, lastname, email)" 
                    + " VALUES "+
                    "('"+ p.getFirstName()
                        +"', '"+ p.getLastName()
                        +"', '"+ p.getEmail()
                         +"')";
            System.out.println(add);   
            Statement st = con.createStatement();
            
            //Add the new contact into the SQL table.
            try
            {
                st.executeUpdate(add);
                System.out.println(add);
            }catch(Exception e)
            {
                 //String to make a new table goes here.
                
                
            String make = 
                    "CREATE TABLE " + database +
                    "(firstname  CHAR(50)"
                    + ",lastname CHAR(15)"
                     + ",email CHAR(20)"
                    + ")";
            
                     
                System.out.println(make);
                st.executeUpdate(make);
                st.executeUpdate(add);
            }
            con.close();

            
                    
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    } 
     
     

