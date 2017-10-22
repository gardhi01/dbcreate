/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitydbcreate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gardhi01
 */
public class DbWriter {
    
    public void createTables(String user, String pass, String url) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try{
           //STEP 2: Register JDBC driver
           //Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           System.out.println("Connecting to a selected database...");
           conn = DriverManager.getConnection(url, user, pass);
           System.out.println("Connected database successfully...");

           //STEP 4: Execute a query
           System.out.println("Creating table in given database...");
           stmt = conn.createStatement();
           
           stmt.executeUpdate("DROP TABLE IF EXISTS enrollment;");
           stmt.executeUpdate("DROP TABLE IF EXISTS student;");
           stmt.executeUpdate("DROP TABLE IF EXISTS section;");
           stmt.executeUpdate("DROP TABLE IF EXISTS major;");
           stmt.executeUpdate("DROP TABLE IF EXISTS course;");
           stmt.executeUpdate("DROP TABLE IF EXISTS faculty;");
           stmt.executeUpdate("DROP TABLE IF EXISTS semester;");
           stmt.executeUpdate("DROP TABLE IF EXISTS location;");
           stmt.executeUpdate("DROP TABLE IF EXISTS department;");
           
           String sql = "CREATE TABLE semester " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " year INTEGER, " + 
                        " season TEXT, " + 
                        " PRIMARY KEY ( id ))"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE location " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " building TEXT, " + 
                        " room INTEGER, " + 
                        " purpose TEXT, " +
                        " PRIMARY KEY ( id ))"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE department " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " name TEXT, " + 
                        " building TEXT, " + 
                        " PRIMARY KEY ( id ))"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE major " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " department INTEGER, " + 
                        " name TEXT, " + 
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( department ) REFERENCES department(id) ON DELETE CASCADE)"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE course " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " department INTEGER, " + 
                        " abbreviation TEXT, " + 
                        " number INTEGER, " +
                        " title TEXT, " +
                        " credits INTEGER, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( department ) REFERENCES department(id) ON DELETE CASCADE)"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE faculty " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " name TEXT, " + 
                        " department INTEGER, " + 
                        " startDate INTEGER, " +
                        " endDate INTEGER, " +
                        " office INTEGER, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( department ) REFERENCES department(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( startDate ) REFERENCES semester(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( endDate ) REFERENCES semester(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( office ) REFERENCES location(id) ON DELETE CASCADE)"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE section " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " course INTEGER, " + 
                        " instructor INTEGER, " + 
                        " offered INTEGER, " +
                        " location INTEGER, " +
                        " startHour TIME, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( course ) REFERENCES course(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( instructor ) REFERENCES faculty(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( offered ) REFERENCES semester(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( location ) REFERENCES location(id) ON DELETE CASCADE)"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE student " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " name TEXT, " + 
                        " graduationDate INTEGER, " + 
                        " major INTEGER, " +
                        " advisor INTEGER, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( graduationDate ) REFERENCES semester(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( major ) REFERENCES major(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( advisor ) REFERENCES faculty(id) ON DELETE CASCADE)"; 

           stmt.executeUpdate(sql);
           
           sql = "CREATE TABLE enrollment " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " student INTEGER, " + 
                        " section INTEGER, " + 
                        " grade TEXT, " +
                        " PRIMARY KEY ( id ), " +
                        " FOREIGN KEY ( student ) REFERENCES student(id) ON DELETE CASCADE," +
                        " FOREIGN KEY ( section ) REFERENCES section(id) ON DELETE CASCADE)"; 

           stmt.executeUpdate(sql);
           
           System.out.println("Created tables in given database...");
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
        System.out.println("Goodbye!");
     }//end main
     }//end JDBCExample


     
