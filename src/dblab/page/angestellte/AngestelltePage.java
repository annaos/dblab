package dblab.page.angestellte;


import dblab.service.AngestellteService;
import dblab.domain.Angestellte;
import dblab.page.TemplatePage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.AbstractLink;

import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.PageLink;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.ClickUtils;
//import org.springframework.stereotype.Component;


public class AngestelltePage extends TemplatePage {

    private static final long serialVersionUID = 1L;

    private Table table = new Table("table");
    private PageLink editLink = new PageLink("Edit", EditAngestellte.class);
    private PageLink viewLink = new PageLink("View", ViewAngestellte.class);    
    
    private Form form = new Form("form");
    private final TextField textField;
    private final Select typeSelect;
 
    private final Select sizeSelect = new Select("selectSize");
    //@Resource(name="angestellteService")
    private AngestellteService angestellteService;
    private Column columnViewEdit;

    // Constructor ------------------------------------------------------------

    public AngestelltePage() {
        
        addControl(form);
        addControl(sizeSelect);
        textField = new TextField("Suche");
        form.add(textField);

        typeSelect = new Select("Typ");
        typeSelect.addAll(new String[] {"Name", "Nachname", "Stelle"});
        typeSelect.setValue("Name");
        typeSelect.setStyle("font-size", "9pt");
        form.add(typeSelect);

        form.add(new Submit("search", "Suche"));
        
        
        sizeSelect.addAll(new String[] {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50"});
        
        //form.add(sizeSelect);
        table.getControlLink().addBehavior(new DefaultAjaxBehavior() {

            @Override
            public ActionResult onAction(Control source) {
                table.onProcess();
                return new ActionResult(table.toString(), ActionResult.HTML);
            }
        });
        
         sizeSelect.addBehavior(new DefaultAjaxBehavior() {

            @Override
            public ActionResult onAction(Control source) {
                table.setPageSize(2);
                sizeSelect.onProcess();
                return new ActionResult(table.toString(), ActionResult.HTML);
            }
        });
        
        addControl(table);

        table.setClass(Table.CLASS_ISI);
        table.setPageSize(12);
        table.setShowBanner(true);
        table.setBannerPosition(Table.POSITION_TOP);
        table.setSortable(true);
        table.setWidth("100%");
        

        table.addColumn(new Column("name"));

        table.addColumn(new Column("nachname"));

        table.addColumn(new Column("stelle"));

        table.addColumn(new Column("gehalt"));
        
        viewLink.setImageSrc("/images/form.png");
        viewLink.setTitle("View Angestellte");
        viewLink.setParameter("referrer", "/AngestelltePage.htm");

        editLink.setImageSrc("/images/table-edit.png");
        editLink.setTitle("Edit Angestellte");
        editLink.setParameter("referrer", "/AngestelltePage.htm");
        columnViewEdit = new Column("View/Edit");
        columnViewEdit.setTextAlign("center");
          // sizeSelect.setSize(8);

     
        
        AbstractLink[] links = new AbstractLink[] { viewLink,editLink };
        columnViewEdit.setDecorator(new LinkDecorator(table, links, "id"));
        columnViewEdit.setSortable(false);
        columnViewEdit.setWidth("auto");
        table.addColumn(columnViewEdit);

        
        table.setDataProvider(new DataProvider<Angestellte>() {
        @Override
        public List<Angestellte> getData() {
             return getAngestellteService().getAngestelltes(); 
          }

            public int size() {
                return angestellteService.getNumberOfCustomers();
            }
        });

        table.restoreState(getContext());
    }

    public AngestellteService getAngestellteService() {
        angestellteService = new AngestellteService();
        return angestellteService;
    }

    public void setCustomerService(AngestellteService angestellteService) {
        this.angestellteService = angestellteService;
    }

    
   public boolean onClearClick() {
        // Clear field values
        form.clearErrors();
        form.clearValues();

        // Clear table state
        table.setPageNumber(0);
        table.setSortedColumn(null);

        // Remove table and form state from the session
        Context context = getContext();
        form.removeState(context);
        table.removeState(context);
        return true;
    }
    
        @Override
    public List<Element> getHeadElements() {
        if (headElements == null) { 
            headElements = super.getHeadElements();
            headElements.add(new JsImport("/script/js/jquery-1.4.2.js"));
            headElements.add(new JsScript("/script/table-ajax.js", new HashMap()));
   
        }
        return headElements;
    }
}
