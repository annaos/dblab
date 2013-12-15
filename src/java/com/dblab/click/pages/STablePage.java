/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.dblab.click.pages;

import com.dblab.click.pages.service.SqlDataProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.springframework.stereotype.Component;

/**
 * Provides an simple Table usage example Page.
 */
@Component
public class STablePage extends Page {

    private static final long serialVersionUID = 1L;

    private Table table;    
    SqlDataProvider provider = new SqlDataProvider("Angestellte");
    
    public STablePage() {
       
        
        List<String> fieldName;
        
        table = new Table("table");
        table.setClass(Table.CLASS_REPORT);
        fieldName = provider.getfieldName();

        Column column;
        for(int i=0;i<fieldName.size();i++){
            column = new Column(fieldName.get(i));
            column.setAutolink(true);
            table.addColumn(column);
        
        }
        
        table.setDataProvider(provider);
        table.setSorted(true);
        addControl(table);
    }

}