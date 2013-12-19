package dblab.domain;

import java.util.List;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

public class Angestellte extends CayenneDataObject{
        

    public static final String GEHALT_PROPERTY = "gehalt";
    
    public static final String ID_PROPERTY = "id";
    
    public static final String NACHNAME_PROPERTY = "nachname";
    
    public static final String NAME_PROPERTY = "name";
    
    public static final String STELLE_PROPERTY = "stelle";
   
    public static final String SCHAECHTE_ARRAY_PROPERTY = "schaechteArray";
    
    public static final String SCHAECHTE_ARRAY1_PROPERTY = "schaechteArray1";
    
    public static final String ZEIT_ARRAY_PROPERTY = "zeitArray";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<Integer> GEHALT = new Property<Integer>("gehalt");
    public static final Property<Integer> ID = new Property<Integer>("id");
    public static final Property<String> NACHNAME = new Property<String>("nachname");
    public static final Property<String> NAME = new Property<String>("name");
    public static final Property<String> STELLE = new Property<String>("stelle");
    public static final Property<List<Schaechte>> SCHAECHTE_ARRAY = new Property<List<Schaechte>>("schaechteArray");
    public static final Property<List<Schaechte>> SCHAECHTE_ARRAY1 = new Property<List<Schaechte>>("schaechteArray1");
    public static final Property<List<Zeit>> ZEIT_ARRAY = new Property<List<Zeit>>("zeitArray");

    public void setGehalt(Integer gehalt) {
        writeProperty("gehalt", gehalt);
    }
    public Integer getGehalt() {
        return (Integer)readProperty("gehalt");
    }

    public void setId(int id) {
        writeProperty("id", id);
    }
    public int getId() {
        Object value = readProperty("id");
        return (value != null) ? (Integer) value : 0;
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

}
