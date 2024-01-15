package com.example.project3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label messagelogin_lbl;
    @FXML
    private Button newaccount_btn;
    @FXML
    private Button login_btn;
    @FXML
    private TextField username_field;
    @FXML
    private PasswordField password_field;

    @FXML
    private Label newaccountmessage_lbl;
    @FXML
    private Button loginreturn_btn;
    @FXML
    private Button addaccount_btn;
    @FXML
    private TextField createusername_field;
    @FXML
    private TextField createpassword_field;
    @FXML
    private TextField confirmpassword_field;
    @FXML
    private TextField iban_field;
    @FXML
    private TextField number_field;
    @FXML
    private TextField cvv_field;
    @FXML
    private TextField expdate_field;

    @FXML
    private Label number_lbl;
    @FXML
    private Label name_lbl;
    @FXML
    private Label cvv_lbl;
    @FXML
    private Label expdate_lbl;
    @FXML
    private Label iban_lbl;
    @FXML
    private Label sold_lbl;
    @FXML
    private Button transfer_money_btn;

    @FXML
    private TableView<Transfer> transfer_table;
    @FXML
    private TableColumn<Transfer, String> date_colum;
    @FXML
    private TableColumn<Transfer, String> amount_colum;
    @FXML
    private TableColumn<Transfer, String> monetary_colum;
    ObservableList<Transfer> list;

    @FXML
    private TextField cash_field;
    @FXML
    private Button goback_btn;
    @FXML
    private TextField receiver_field;
    @FXML
    private Button send_btn;
    @FXML
    private TextField sender_field;
    @FXML
    private TextField detalii_field;
    @FXML
    private Label transfer_message_lbl;

    @FXML
    private TextField cnp_field;
    @FXML
    private Label search_address_lbl;
    @FXML
    private Button search_btn;
    @FXML
    private Label search_iban_lbl;
    @FXML
    private Label search_name_lbl;
    @FXML
    private Label search_sold_lbl;

    public void newaccount_btnOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("CreateNewAccount.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void loginreturn_btnOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addaccount_btnOnAction(ActionEvent event) throws IOException {
        if(createpassword_field.getText() != confirmpassword_field.getText()){
            newaccountmessage_lbl.setText("Password invalid");
        }
        if(createusername_field.getText().isBlank() == true || createpassword_field.getText().isBlank() == true
        || confirmpassword_field.getText().isBlank() == true || iban_field.getText().isBlank() == true
        || number_field.getText().isBlank() == true || cvv_field.getText().isBlank() == true
        || expdate_field.getText().isBlank() == true){
            newaccountmessage_lbl.setText("You need to fill all the fields");
        }else{
            DbFunctions db = new DbFunctions();
            Connection conn = db.connect_to_db("Bank", "postgres", "gardevoir17");
            db.add_account(conn, createusername_field.getText(), createpassword_field.getText());
            db.add_card(conn, iban_field.getText(), number_field.getText(),cvv_field.getText(), expdate_field.getText());
            newaccountmessage_lbl.setText("Your data was successfully registered");
        }
    }

    public void login_btnOnAction(ActionEvent event) throws IOException {

        String username, password;
        if(username_field.getText().isBlank() == true || password_field.getText().isBlank() == true){
            messagelogin_lbl.setText("You need to fill all the fields");
        }else{
            if(username_field.getText().equals("admin") && password_field.getText().equals("1234")){
                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                DbFunctions db = new DbFunctions();
                Connection conn = db.connect_to_db("Bank", "postgres", "gardevoir17");
                boolean verif = db.login(conn,username_field.getText(), password_field.getText(),messagelogin_lbl);
                //iban_lbl.setText(iban);
                //System.out.println(iban);
                if(verif==true){
                    username=username_field.getText();
                    password=password_field.getText();
                    System.out.println(username);
                    System.out.println(password);
                    Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    ResultSet reSet = db.upload(conn,username, password);;
                    try{
                        String ibanaux=null;
                        while(reSet.next()){
                            ibanaux=reSet.getString("ac_iban");
                            //rs.close();
                        }
                        iban_lbl.setText(ibanaux);
                        System.out.println(ibanaux);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
                else{
                    messagelogin_lbl.setText("Invalid data! Do you have an account?");
                }
            }
        }
    }

    public void transfer_money_btnOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Transfer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goback_btnOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void send_btnOnAction(ActionEvent event) throws IOException {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("Bank", "postgres", "gardevoir17");
        int cash=Integer.parseInt(cash_field.getText());
        db.add_transfer(conn,sender_field.getText(), receiver_field.getText(), cash_field.getText(), detalii_field.getText());
        db.update_sold(conn, sender_field.getText(), receiver_field.getText(),cash);
        transfer_message_lbl.setText("Transfer realised successfully!");
    }

    public void search_btnOnAction(ActionEvent event) throws IOException {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("Bank", "postgres", "gardevoir17");
        ResultSet rs = db.search_data(conn, cnp_field.getText());
        try{
            String name=null;
            String address=null;
            String iban=null;
            String sold=null;
            while(rs.next()){
                name=rs.getString("name");
                address=rs.getString("address");
                iban=rs.getString("iban");
                sold=rs.getString("sold");
            }
            search_name_lbl.setText(name);
            search_address_lbl.setText(address);
            search_iban_lbl.setText(iban);
            search_sold_lbl.setText(sold);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        date_colum.setCellValueFactory(new PropertyValueFactory<Transfer,String>("date_colum"));
        amount_colum.setCellValueFactory(new PropertyValueFactory<Transfer,String>("amount_colum"));
        monetary_colum.setCellValueFactory(new PropertyValueFactory<Transfer,String>("monetary_colum"));

        transfer_table.setItems(list);
    }*/
}