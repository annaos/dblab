package dblab.service;

import dblab.domain.Angestellte;
import java.util.List;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.ExpressionFactory;

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
    String columnName;
    String columnVolue;
    String etlassente;

    public AngestellteService(String columnName, String columnVolue, String etlassente) {
        this.columnName = columnName;
        this.columnVolue = columnVolue;
        this.etlassente = etlassente;
    }
    
    public void startAngestellteService(){
       runtime = new ServerRuntime("dblab/cayenne-dblab.xml");
       context = runtime.newContext();
    }
    
   public void stopAngestellteService(){
       runtime.shutdown();
    }
    
    
   public List<Angestellte> getAngestelltes() {
        startAngestellteService();
        String str[] = Angestellte.getColumnAngestellte().get(columnName);
        SelectQuery query = new SelectQuery(Angestellte.class);
        if ("Alle".equals(etlassente)){
            
            query.andQualifier(
                    ExpressionFactory.likeIgnoreCaseExp(
                            str[0],
                            str[1] 
                             + columnVolue + 
                            str[1]
                    ));
        }
        if ("Entlassente".equals(etlassente)){

            query.andQualifier(
                    ExpressionFactory.likeIgnoreCaseExp(
                            str[0],
                            str[1] 
                             + columnVolue + 
                            str[1]
                    ));
            query.andQualifier(ExpressionFactory.likeIgnoreCaseExp(Angestellte.ENTLASSENE_PROPERTY,1));
        }
        if ("im Betrieb".equals(etlassente)){

            query.andQualifier(
                    ExpressionFactory.likeIgnoreCaseExp(
                            str[0],
                            str[1] 
                             + columnVolue + 
                            str[1]
                    ));
            query.andQualifier(ExpressionFactory.likeIgnoreCaseExp(Angestellte.ENTLASSENE_PROPERTY,0));
        }
        
        List<Angestellte> temp = context.performQuery(query);
        stopAngestellteService();
        return temp;
    }

    public void saveAngestellte(Angestellte angestellte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Angestellte getAngestellteForID(Integer id) {
        return (Angestellte) getObjectForPK(Angestellte.class, id);
    }
}
