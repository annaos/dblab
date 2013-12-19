package dblab.domain;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;



public abstract class Zeit extends CayenneDataObject {

   
    public static final String EINGANG_PROPERTY = "eingang";
   
    public static final String TO_ANGESTELLTE_PROPERTY = "toAngestellte";
    
    public static final String TO_SCHAECHTE_PROPERTY = "toSchaechte";

    public static final String ANGESTELLTER_ID_PK_COLUMN = "Angestellter_id";
    public static final String ZEIT_PK_COLUMN = "Zeit";

    public static final Property<Boolean> EINGANG = new Property<Boolean>("eingang");
    public static final Property<Angestellte> TO_ANGESTELLTE = new Property<Angestellte>("toAngestellte");
    public static final Property<Schaechte> TO_SCHAECHTE = new Property<Schaechte>("toSchaechte");

    public void setEingang(Boolean eingang) {
        writeProperty("eingang", eingang);
    }
    public Boolean getEingang() {
        return (Boolean)readProperty("eingang");
    }

    public void setToAngestellte(Angestellte toAngestellte) {
        setToOneTarget("toAngestellte", toAngestellte, true);
    }

    public Angestellte getToAngestellte() {
        return (Angestellte)readProperty("toAngestellte");
    }


    public void setToSchaechte(Schaechte toSchaechte) {
        setToOneTarget("toSchaechte", toSchaechte, true);
    }

    public Schaechte getToSchaechte() {
        return (Schaechte)readProperty("toSchaechte");
    }


}
