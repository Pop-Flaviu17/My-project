//package com.company;
//package sample;
package com.example.project3;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname, user,pass);
            if(connection !=null){
                System.out.println("Connection established!");
            }
            else {
                System.out.println("Connection failed");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return connection;
    }

    public void insert_row(Connection conn, String table, String username, String password){
        Statement statement;
        try{
            String query=String.format("insert into %s(username,password) values('%s', '%s');",table,username,password);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void add_account(Connection conn, String username, String password){
        Statement statement;
        try{
            String query=String.format("insert into login(username,password) values('%s', '%s');",username,password);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Account inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void add_card(Connection conn, String iban, String number, String cvv, String expdate){
        Statement statement;
        try{
            String query=String.format("insert into card(number,cvv,expdate) values('%s', '%s', '%s');",number,cvv,expdate);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Card inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void read_data(Connection conn, String table){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from %s;", table);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("sold")+" ");
                System.out.println(rs.getString("monetary")+" ");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void update_date(Connection conn, String table, String old_data, String new_data){
        Statement statement;
        try{
            String query = String.format("update %s set name='%s' where name='%s';", table, new_data, old_data);
            statement= conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public ResultSet search_data(Connection conn, String cnp){
        Statement statement;
        ResultSet rs = null;
        try{
            String query=String.format("select * from account join person on account.id_pers = person.cnp where cnp='%s';", cnp);
            statement= conn.createStatement();
            rs =statement.executeQuery(query);
            /*
            while(rs.next()){
                System.out.print(rs.getString("empid"));
                System.out.print(rs.getString("empid"));
                System.out.println(rs.getString("empid"));
            }*/
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }
    public boolean login(Connection conn, String username, String password, Label messagelogin){
        boolean verif=false;
        Statement statement;
        ResultSet rs = null;
        try{
            String query=String.format("select * from login join transfer on login.ac_iban=transfer.iban_trimis where username='%s' and password='%s';", username, password);
            statement= conn.createStatement();
            rs =statement.executeQuery(query);
            if(rs.isBeforeFirst()){
                verif = true;
            }
            while(rs.next()){
                //iban=rs.getString("ac_iban");
                //System.out.println(iban);
                //iban.setText("it works");
            }
        }catch(Exception e){
            messagelogin.setText("Invalid data! Do you have an account?");
            System.out.println(e);
            verif = false;
        }
        return verif;
    }

    public ResultSet upload(Connection conn, String username, String password){
        Statement statement;
        ResultSet rs = null;
        try{
            String query=String.format("select * from login join transfer on login.ac_iban=transfer.iban_trimis where username='%s' and password='%s';", username, password);
            statement= conn.createStatement();
            rs =statement.executeQuery(query);

        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }

    public void add_transfer(Connection conn, String iban_trimis, String iban_primit, String suma, String detalii){
        Statement statement;
        try{
            String query=String.format("insert into transfer(iban_trimis,iban_primit,suma,moneda,data,detalii) values('%s', '%s', '%s', 'lei', NOW(), '%s');",iban_trimis,iban_primit,suma,detalii);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void update_sold(Connection conn, String iban_trimis, String iban_primit, int suma){
        Statement statement1;
        Statement statement2;
        Statement statement3;
        Statement statement4;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            String query1 = String.format("select sold from account where iban='%s';", iban_trimis);
            statement1 = conn.createStatement();
            rs1 = statement1.executeQuery(query1);
            Integer suma1 = null;
            while (rs1.next()) {
                suma1 = rs1.getInt("sold");
                suma1 = suma1 - suma;
            }

            String query2 = String.format("select sold from account where iban='%s';", iban_primit);
            statement2 = conn.createStatement();
            rs2 = statement2.executeQuery(query2);
            Integer suma2 = null;
            while (rs2.next()) {
                suma2 = rs2.getInt("sold");
                suma2 = suma2 + suma;
            }
            String s1 = Integer.toString(suma1);
            String query3 = String.format("update account set sold='%s' where iban='%s';", s1, iban_trimis);
            statement3 = conn.createStatement();
            statement3.executeQuery(query3);

            String s2 = Integer.toString(suma2);
            String query4 = String.format("update account set sold='%s' where iban='%s';", s2, iban_primit);
            statement4 = conn.createStatement();
            statement4.executeQuery(query4);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
