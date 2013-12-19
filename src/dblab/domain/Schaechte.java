package dblab.domain;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;


public abstract class Schaechte extends CayenneDataObject {

    
    public static final String NAME_PROPERTY = "name";
  
    public static final String ANGESTELLTE_ARRAY_PROPERTY = "angestellteArray";
    
    public static final String TO_ANGESTELLTE_PROPERTY = "toAngestellte";
    
    public static final String ZEIT_ARRAY_PROPERTY = "zeitArray";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> NAME = new Property<String>("name");
    public static final Property<List<Angestellte>> ANGESTELLTE_ARRAY = new Property<List<Angestellte>>("angestellteArray");
    public static final Property<Angestellte> TO_ANGESTELLTE = new Property<Angestellte>("toAngestellte");
    public static final Property<List<Zeit>> ZEIT_ARRAY = new Property<List<Zeit>>("zeitArray");

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void addToAngestellteArray(Angestellte obj) {
        addToManyTarget("angestellteArray", obj, true);
    }
    public void removeFromAngestellteArray(Angestellte obj) {
        removeToManyTarget("angestellteArray", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Angestellte> getAngestellteArray() {
        return (List<Angestellte>)readProperty("angestellteArray");
    }


    public void setToAngestellte(Angestellte toAngestellte) {
        setToOneTarget("toAngestellte", toAngestellte, true);
    }

    public Angestellte getToAngestellte() {
        return (Angestellte)readProperty("toAngestellte");
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
