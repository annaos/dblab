package dblab.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

public class Angestellte extends CayenneDataObject{
    
    static HashMap<String,String[]> columnAngestellte;

        public static final String ENTLASSENE_PROPERTY = "entlassene";
    
    public static final String GEBURTSDATUM_PROPERTY = "geburtsdatum";
    
    public static final String GEHALT_PROPERTY = "gehalt";
    
    public static final String ID_PROPERTY = "id";
    
    public static final String NACHNAME_PROPERTY = "nachname";
    
    public static final String NAME_PROPERTY = "name";
    
    public static final String STELLE_PROPERTY = "stelle";
    
    public static final String SCHAECHTE_ARRAY_PROPERTY = "schaechteArray";
    
    public static final String SCHAECHTE_ARRAY1_PROPERTY = "schaechteArray1";
    
    public static final String ZEIT_ARRAY_PROPERTY = "zeitArray";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<Boolean> ENTLASSENE = new Property<Boolean>("entlassene");
    public static final Property<Date> GEBURTSDATUM = new Property<Date>("geburtsdatum");
    public static final Property<Integer> GEHALT = new Property<Integer>("gehalt");
    public static final Property<Integer> ID = new Property<Integer>("id");
    public static final Property<String> NACHNAME = new Property<String>("nachname");
    public static final Property<String> NAME = new Property<String>("name");
    public static final Property<String> STELLE = new Property<String>("stelle");
    public static final Property<List<Schaechte>> SCHAECHTE_ARRAY = new Property<List<Schaechte>>("schaechteArray");
    public static final Property<List<Schaechte>> SCHAECHTE_ARRAY1 = new Property<List<Schaechte>>("schaechteArray1");
    public static final Property<List<Zeit>> ZEIT_ARRAY = new Property<List<Zeit>>("zeitArray");

    public void setEntlassene(Boolean entlassene) {
        writeProperty("entlassene", entlassene);
    }
    public Boolean getEntlassene() {
        return (Boolean)readProperty("entlassene");
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        writeProperty("geburtsdatum", geburtsdatum);
    }
    public Date getGeburtsdatum() {
        return (Date)readProperty("geburtsdatum");
    }

    public void setGehalt(Integer gehalt) {
        writeProperty("gehalt", gehalt);
    }
    public Integer getGehalt() {
        return (Integer)readProperty("gehalt");
    }

    public void setId(Integer id) {
        writeProperty("id", id);
    }
    public Integer getId() {
        return (Integer)readProperty("id");
    }

    public void setNachname(String nachname) {
        writeProperty("nachname", nachname);
    }
    public String getNachname() {
        return (String)readProperty("nachname");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void setStelle(String stelle) {
        writeProperty("stelle", stelle);
    }
    public String getStelle() {
        return (String)readProperty("stelle");
    }

    public void addToSchaechteArray(Schaechte obj) {
        addToManyTarget("schaechteArray", obj, true);
    }
    public void removeFromSchaechteArray(Schaechte obj) {
        removeToManyTarget("schaechteArray", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Schaechte> getSchaechteArray() {
        return (List<Schaechte>)readProperty("schaechteArray");
    }


    public void addToSchaechteArray1(Schaechte obj) {
        addToManyTarget("schaechteArray1", obj, true);
    }
    public void removeFromSchaechteArray1(Schaechte obj) {
        removeToManyTarget("schaechteArray1", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Schaechte> getSchaechteArray1() {
        return (List<Schaechte>)readProperty("schaechteArray1");
    }


    public void addToZeitArray(Zeit obj) {
        addToManyTarget("zeitArray", obj, true);
    }
    public void removeFromZeitArray(Zeit obj) {
        removeToManyTarget("zeitArray", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Zeit> getZeitArray() {
        return (List<Zeit>)readProperty("zeitArray");
    }
    
    public static HashMap<String,String[]> getColumnAngestellte(){
        columnAngestellte = new HashMap<String,String[]>(); 
        
        String str[] = new String[]{"gehalt",""};
        columnAngestellte.put("Gehalt", str);
        
        str = new String[]{"name","%"};
        columnAngestellte.put("Name", str);
        
        str = new String[]{"nachname","%"};
        columnAngestellte.put("Nachname", str);
        
        str = new String[]{"stelle","%"};
        columnAngestellte.put("Stelle", str);
        return columnAngestellte;
    }
    

}
