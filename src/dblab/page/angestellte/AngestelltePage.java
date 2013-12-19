package dblab.page.angestellte;


import dblab.AngestellteService;
import dblab.domain.Angestellte;
import dblab.page.SpacerButton;
import dblab.page.TemplatePage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.AbstractLink;

import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.PageLink;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.extras.control.TableInlinePaginator;
//import org.springframework.stereotype.Component;
/**
 * Provides an advanced Table usage example Page.
 * <p/>
 * This example also demonstrates how a stateful Page can be used to preserve
 * the Table sort and paging state while editing customers.
 */
//@Component
public class AngestelltePage extends TemplatePage {

    private static final long serialVersionUID = 1L;

    private Table table = new Table("table");
    private PageLink editLink = new PageLink("Edit", EditAngestellte.class);
    private ActionLink deleteLink = new ActionLink("Delete", this, "onDeleteClick");
    
    private Form form = new Form("form");
    private Select columnSelect;
    private TextField nameField = new TextField("=");
    //private DateField dateField = new DateField(Angestellte.DATE_JOINED_PROPERTY, "Start Date");

    /**
     * Spring injected CustomerService bean. The service is marked as transient
     * since the page is stateful and we don't want to serialize the service
     * along with the page.
     */
    @Resource(name="angestellteService")
    private AngestellteService angestellteService;
    private Column column;
    private final TextField textField;
    private final Select typeSelect;

    // Constructor ------------------------------------------------------------

    public AngestelltePage() {
        
        addControl(form);
        
        textField = new TextField("search");
        form.add(textField);

        typeSelect = new Select("type");
        typeSelect.addAll(new String[] {"ID", "Name", "Age"});
        typeSelect.setValue("Name");
        typeSelect.setStyle("font-size", "9pt");
        form.add(typeSelect);

        form.add(new Submit("go", " Go "));
        
        
        
        
        
        //form.add(new Submit("Clear", this, "onClearClick"));
        //form.add(new SpacerButton());
        //form.add(new Submit("New...", this, "onNewClick"));
        // Add controls
       table.getControlLink().addBehavior(new DefaultAjaxBehavior() {

            @Override
            public ActionResult onAction(Control source) {

                // NOTE: Ajax requests only process the target Control. Here we
                // process the table in order to update paging and sorting state
                table.onProcess();
                
                return new ActionResult(table.toString(), ActionResult.HTML);
            }
        });
        addControl(table);
        //addControl(editLink);
        

        // Setup table
        table.setClass(Table.CLASS_ISI);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setWidth("100%");
        
        
        column = new Column("name");
        column.setSortable(true);
        table.addColumn(column);

        table.addColumn(new Column("nachname"));

        table.addColumn(new Column("stelle"));
        //column.setAutolink(true);
        table.addColumn(new Column("gehalt"));
        //column.setTitleProperty("gehalt");
        //table.addColumn(column);

        
        editLink.setImageSrc("/images/table-edit.png");
        editLink.setTitle("Edit customer details");
        editLink.setParameter("referrer", "/AngestelltePage.htm");

        column = new Column("Edit");
        column.setTextAlign("center");
        column.setWidth("50px");
        AbstractLink[] links = new AbstractLink[] { editLink };
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);
        table.setDataProvider(new DataProvider<Angestellte>() {
        @Override
        public List<Angestellte> getData() {
                int start = table.getFirstRow();
                int count = table.getPageSize();
                String sortColumn = table.getSortedColumn();
                boolean ascending = table.isSortedAscending();
                

                List<Angestellte> dfsd = getAngestellteService().getAngestellteForPage(start, count, sortColumn, ascending);
                return dfsd; 
            }

            public int size() {
                return angestellteService.getNumberOfCustomers();
            }
        });


        // Restore the table sort and paging state from the session between requests
        table.restoreState(getContext());
    }

    // Event Handlers ---------------------------------------------------------

    public boolean onDeleteClick() {
        Integer id = deleteLink.getValueInteger();
        //getAngestellteService().deleteAngestellte(id);
        return true;
    }

    // Public Methods ---------------------------------------------------------

    /**
     * Return CustomerService instance from Spring application context.
     *
     * @return CustomerService instance
     */
    public AngestellteService getAngestellteService() {
        angestellteService = new AngestellteService();
        return angestellteService;
    }

    /**
     * Set the CustomerService instance from Spring application context.
     *
     * @param angestellteService the customerService instance to inject
     */
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
            headElements.add(new JsImport("/assets/js/jquery-1.4.2.js"));
            headElements.add(new JsScript("/ajax/table/table-ajax.js", new HashMap()));
        }
        return headElements;
    }
}
