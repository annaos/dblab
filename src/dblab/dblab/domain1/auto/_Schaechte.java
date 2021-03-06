package dblab.domain1.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import dblab.domain.Angestellte;
import dblab.domain.Zeit;

/**
 * Class _Schaechte was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Schaechte extends CayenneDataObject {

    @Deprecated
    public static final String GESCHLOSSEN_PROPERTY = "geschlossen";
    @Deprecated
    public static final String ID_PROPERTY = "id";
    @Deprecated
    public static final String NAME_PROPERTY = "name";
    @Deprecated
    public static final String TIEF_PROPERTY = "tief";
    @Deprecated
    public static final String ANGESTELLTE_ARRAY_PROPERTY = "angestellteArray";
    @Deprecated
    public static final String TO_ANGESTELLTE_PROPERTY = "toAngestellte";
    @Deprecated
    public static final String ZEIT_ARRAY_PROPERTY = "zeitArray";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<Boolean> GESCHLOSSEN = new Property<Boolean>("geschlossen");
    public static final Property<Integer> ID = new Property<Integer>("id");
    public static final Property<String> NAME = new Property<String>("name");
    public static final Property<Integer> TIEF = new Property<Integer>("tief");
    public static final Property<List<Angestellte>> ANGESTELLTE_ARRAY = new Property<List<Angestellte>>("angestellteArray");
    public static final Property<Angestellte> TO_ANGESTELLTE = new Property<Angestellte>("toAngestellte");
    public static final Property<List<Zeit>> ZEIT_ARRAY = new Property<List<Zeit>>("zeitArray");

    public void setGeschlossen(Boolean geschlossen) {
        writeProperty("geschlossen", geschlossen);
    }
    public Boolean getGeschlossen() {
        return (Boolean)readProperty("geschlossen");
    }

    public void setId(Integer id) {
        writeProperty("id", id);
    }
    public Integer getId() {
        return (Integer)readProperty("id");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void setTief(Integer tief) {
        writeProperty("tief", tief);
    }
    public Integer getTief() {
        return (Integer)readProperty("tief");
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
