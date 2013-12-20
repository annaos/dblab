package dblab.page.schaechte;


import dblab.page.TemplatePage;
import org.apache.click.control.Table;
//import org.springframework.stereotype.Component;


public class SchaechtePage extends TemplatePage {

    private static final long serialVersionUID = 1L;

    private Table table = new Table("table");


    public SchaechtePage() {
    
        addControl(table);
    }
 
}
