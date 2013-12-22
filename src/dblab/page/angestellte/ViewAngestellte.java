/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dblab.page.angestellte;

import dblab.domain.Angestellte;
import dblab.page.HomePage;
import dblab.page.TemplatePage;
import dblab.service.AngestellteService;
import javax.annotation.Resource;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.DoubleField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.util.Bindable;


/**
 *
 * @author andrey
 */
class ViewAngestellte extends TemplatePage{
    private static final long serialVersionUID = 1L;

    private final Form form = new Form("form");
    private final HiddenField referrerField = new HiddenField("referrer", String.class);
    private final HiddenField idField = new HiddenField("id", Integer.class);

    // Bindable variables can automatically have their value set by request parameters
    @Bindable protected Integer id;
    @Bindable protected String referrer;

    @Resource(name="angestellteService")
    private AngestellteService angestellteService;
    
        // Constructor -----------------------------------------------------------

    public ViewAngestellte() {
        addControl(form);

        form.add(referrerField);

        form.add(idField);

        FieldSet fieldSet = new FieldSet("angestellter");
        form.add(fieldSet);

        TextField nameField = new TextField("name", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(nameField);

        


        form.add(new Submit("ok", "  OK  ", this, "onOkClick"));
        form.add(new Submit("cancel", this, "onCancelClick"));
    }
    
    
        /**
     * When page is first displayed on the GET request.
     *
     * @see Page#onGet()
     */
    @Override
    public void onGet() {
        if (id != null) {
            Angestellte angestellte = angestellteService.getAngestellteForID(id);

            if (angestellte != null) {
                // Copy angestellte data to form. The idField value will be set by
                // this call
                form.copyFrom(angestellte);
            }
        }

        if (referrer != null) {
            // Set HiddenField to bound referrer field
            referrerField.setValue(referrer);
        }
    }

    public boolean onOkClick() {
        if (form.isValid()) {
            Integer id = (Integer) idField.getValueObject();
            Angestellte angestellte  = angestellteService.getAngestellteForID(id);

            if (angestellte == null) {
                angestellte = new Angestellte();
            }
            form.copyTo(angestellte);

            angestellteService.saveAngestellte(angestellte);

            String referrer = referrerField.getValue();
            if (referrer != null) {
                setRedirect(referrer);
            } else {
                setRedirect(HomePage.class);
            }

            return true;

        } else {
            return true;
        }
    }

    public boolean onCancelClick() {
        String referrer1 = referrerField.getValue();
        if (referrer1 != null) {
            setRedirect(referrer1);
        } else {
            setRedirect(HomePage.class);
        }
        return true;
    }
}
