/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitydbcreate;

/**
 *
 * @author gardhi01
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Universitydbcreate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "jdbc:mysql://localhost:3306";

        // Defines username and password to connect to database server.
        String username = "root";
        String password = "strangehat";

        // SQL command to create a database in MySQL.
        String sql = "CREATE DATABASE IF NOT EXISTS Luther";
        
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
            conn.close();
            url = url + "/Luther";
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DbWriter dbw = new DbWriter();
        try {
        dbw.createTables(username, password, url);
        } catch (SQLException ex) {
            Logger.getLogger(Universitydbcreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}