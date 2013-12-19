package dblab.service;


import dblab.domain.Angestellte;
import java.util.List;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import org.apache.cayenne.query.SelectQuery;
import org.apache.click.extras.cayenne.CayenneTemplate;
//import org.springframework.stereotype.Component;

/**
 * Provides a Angestellte Service.
 *
 * @see Angestellte
 */
//@Component
public class AngestellteService extends CayenneTemplate {
    ObjectContext context;
    ServerRuntime runtime;
    
    public void startAngestellteService(){
       runtime = new ServerRuntime("dblab/cayenne-dblab.xml");
       context = runtime.newContext();
    }
    
   public void stopAngestellteService(){
       runtime.shutdown();
    }
    
    
   public List<Angestellte> getAngestelltes() {
        startAngestellteService();
        SelectQuery query = new SelectQuery(Angestellte.class);
        List<Angestellte> temp = context.performQuery(query);
        stopAngestellteService();
        return temp;
    }

    public int getNumberOfAngestelltes() {
        return getAngestelltes().size();
    }

    public void saveAngestellte(Angestellte customer) {
    }

    public int getNumberOfCustomers() {
                startAngestellteService();
        SelectQuery query = new SelectQuery(Angestellte.class);
        //query.addOrdering(Angestellte.NAME_PROPERTY);
        int asdf = context.performQuery(query).size();
       
        stopAngestellteService();
        
        return asdf;
    }


    public Angestellte getCustomerForID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
