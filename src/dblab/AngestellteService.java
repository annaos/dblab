package dblab;


import dblab.domain.Angestellte;
import java.util.Date;
import java.util.List;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.query.SortOrder;
import org.apache.click.extras.cayenne.CayenneTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Provides a Angestellte Service.
 *
 * @see Angestellte
 */
@Component
public class AngestellteService extends CayenneTemplate {
    ObjectContext context;
    ServerRuntime runtime;
    public void startAngestellteService(){
       runtime = new ServerRuntime("dblab/cayenne-dblab.xml");
       context = runtime.newContext();
        //context.performQuery(null)getChannel();
    }
    
   public void stopAngestellteService(){
       //context = null;
       runtime.shutdown();

    }
    
    
    //@SuppressWarnings("unchecked")
    public List<Angestellte> getAngestelltes() {
        startAngestellteService();
        SelectQuery query = new SelectQuery(Angestellte.class);
        //query.addOrdering(Angestellte.NAME_PROPERTY);
        List<Angestellte> asdf = context.performQuery(query);
        stopAngestellteService();
        return asdf;
    }

    public int getNumberOfAngestelltes() {
        
        return getAngestelltes().size();
    }



    public void saveAngestellte(Angestellte customer) {
    }

    public List<Angestellte> getAngestellteForPage(int start, int count, String sortColumn, boolean ascending) {
        
        startAngestellteService();
        SelectQuery query = new SelectQuery(Angestellte.class);
        if (StringUtils.isNotBlank(sortColumn)) {
            query.addOrdering(sortColumn, SortOrder.ASCENDING);
        }
        
        //query.setFetchOffset(start);
        //query.setFetchLimit(count);
        
        //query.addOrdering(Angestellte.NAME_PROPERTY);
        List<Angestellte> asdf = context.performQuery(query);
       
        stopAngestellteService();
        return asdf;
        
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
