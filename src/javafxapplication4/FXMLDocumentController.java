/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import dao.DAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Marcelo Monier Jr.
 */
public class FXMLDocumentController implements Initializable {
    
     String dbHost = "jdbc:mysql://localhost/";
        String dbUser = "root";
        String dbPassword = "88030341";
        
     @FXML   
     private TableColumn emailCol; 
     
     @FXML   
     private TableColumn firstNameCol; 
     
      @FXML   
     private TableColumn lastNameCol; 
      
      
     @FXML   
     private TableView tableview; 
    
     
     
    @FXML
    private Label label;
    
    @FXML
    private Button add;
    
    @FXML
    private TextField firstname;
    
    @FXML
    private TextField lastname;
    
     @FXML
    private TextField email;
    
    @FXML
    private Button show;
    
    
    
    
    
    @FXML
    public void addUser() {
        DAO dao = new DAO(dbHost,dbUser,dbPassword);
        Person p = new Person(firstname.getText(),lastname.getText(),email.getText());
        
        ;
        dao.addPerson(p);    
        clearText();
    }
    
     @FXML
    public void getPerson() {
        DAO dao = new DAO(dbHost,dbUser,dbPassword);
        dao.getPerson(tableview);
        
        
    }
    
    
    @FXML
    public void clearText(){
        firstname.clear();
        lastname.clear();
        email.clear();
    }
    
    @FXML
    public void editFirstName(String s){
     
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert tableview != null : "fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
     firstNameCol.setCellValueFactory(
        new PropertyValueFactory<Person,String>("firstName"));    
   
    lastNameCol.setCellValueFactory(                
        new PropertyValueFactory<Person,String>("lastName"));
    emailCol.setCellValueFactory(
        new PropertyValueFactory<Person,String>("email"));        
       tableview.setEditable(true);
   
    
}
}
