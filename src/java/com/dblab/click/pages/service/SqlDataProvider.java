/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dblab.click.pages.service;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.click.dataprovider.DataProvider;

/**
 *
 * @author andrey
 */
public class SqlDataProvider implements DataProvider{
    String tableName;

    List<String> fieldName = new ArrayList<String>();
    List<Map> list=new ArrayList<Map>();
    
    public SqlDataProvider(String tableName){
        this.tableName = tableName;
        getTable(this.tableName);
    }
    
    public List<String> getfieldName(){
        return fieldName;
    }
    
 
    @Override
    public List<Map> getData() {
        
        return list;
        
    }
    
    private void getTable(String tableName) {
        Connection connection;
        
        Map ls = null;
        
        try{   
            // Class.forName("oracle.jdbc.driver.OracleDriver")
            Class.forName("com.mysql.jdbc.Driver");        

            //Connection connection = DriverManager.getConnection ("jdbc:oracle:thin:@bert.mi.fh-offenburg.de:1521:orcl", "dblab13", "dblab13");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.33.49/Bergwerk",
                    "admin", "12345");
            Statement statement=connection.createStatement();
            String query ="SELECT * from Angestellte";
            ResultSet result=statement.executeQuery(query);
            
            while(result.next()){
                ls = new HashMap();
                fieldName.clear();
                for (int i=0;i<result.getMetaData().getColumnCount();i++){
                     
                    ls.put(result.getMetaData().getColumnLabel(i+1), result.getString(i+1));
                    fieldName.add(result.getMetaData().getColumnName(i+1));
                }
                list.add(ls);
            }

            result.close();
            statement.close();
            connection.close();


        } catch (Exception ex) {
            Logger.getLogger(SqlDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    

    }
}
