package dblab.page.angestellte;


import dblab.service.AngestellteService;
import dblab.domain.Angestellte;
import dblab.page.TemplatePage;
import java.util.HashMap;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.AbstractLink;

import org.apache.click.control.*;
import org.apache.click.control.Form;
import org.apache.click.control.PageLink;
import org.apache.click.control.Select;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.FieldColumn;
import org.apache.click.extras.control.LinkDecorator;
//import org.springframework.stereotype.Component;


public final class AngestelltePage extends TemplatePage {

    private static final long serialVersionUID = 1L;

    private final Table table = new Table("table");
    private final PageLink editLink = new PageLink("Edit", EditAngestellte.class);
    private final PageLink viewLink = new PageLink("View", ViewAngestellte.class);    
    
    private final Form form = new Form("form");
    private final TextField searchField = new TextField("Suche","Was?");
    private final Select typeSelect = new Select("Typ","Wo?");
    private final Select zeigenEntlassente = new Select("zeigenEntlassente","");
    
    private final Submit submit = new Submit("suchen");
    
    private final Form newForm = new Form("newform");
    private final TextField fieldName = new TextField("fieldName","Name",true);
    private final TextField fieldNachName = new TextField("fieldNachName","Nachname",true);
    private final TextField fieldStelle = new TextField("fieldStelle","Stelle");
    private final TextField fieldGehalt = new TextField("fieldGehalt","Gehalt");
    private final Submit newSubmit = new Submit("new");
    
    private final Select sizeSelect = new Select("pageSize","Seitengröße");
    //@Resource(name="angestellteService")
    private AngestellteService angestellteService;
    private Column columnViewEdit;
    private int tableSize = 3;
    private Column column;

    // Constructor ------------------------------------------------------------

    public AngestelltePage() {
        initNewForm();
        initForm();
        initTable();
    }
    
    public void initTable(){
        addControl(table);
        table.setClass(Table.CLASS_ISI);
        table.setPageSize(Integer.valueOf(sizeSelect.getValue()));
        table.setShowBanner(true);
        table.setBannerPosition(Table.POSITION_TOP);
        table.setSortable(true);
        table.setWidth("100%");

        table.addColumn(new Column("id"));
        
        table.addColumn(new Column("name"));

        table.addColumn(new Column("nachname"));
        
        column = new Column("geburtsdatum");
        column.setFormat("{0,date,medium}");
        column.setWidth("90px");
        
        table.addColumn(column);
        
        table.addColumn(new Column("stelle"));

        table.addColumn(new Column("gehalt"));
        
        Checkbox checkbox = new Checkbox();
        checkbox.setDisabled(true);
        column = new FieldColumn("entlassene", checkbox);
        column.setTextAlign("center");
        column.setWidth("50px");
        table.addColumn(column);
        

        viewLink.setImageSrc("/images/form.png");
        viewLink.setTitle("View Angestellte");
        viewLink.setParameter("referrer", "/page/angestellte/AngestelltePage.htm");

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
        
        table.getControlLink().addBehavior(new DefaultAjaxBehavior() {
            @Override
            public ActionResult onAction(Control source) {
                table.saveState(getContext());
                table.onProcess();
                return new ActionResult(table.toString(), ActionResult.HTML);
            }});
        
        table.setDataProvider(new DataProvider<Angestellte>() {
            @Override
            public List<Angestellte> getData() {return getAngestellteService().getAngestelltes();}});

        table.restoreState(getContext());
    }
    
    public void initForm(){
                FieldSet paymentFieldSet1 = new FieldSet("Suchen");
        paymentFieldSet1.setStyle("background-color", "#f4f4f4");
        addControl(form);
        form.add(paymentFieldSet1);
        
        paymentFieldSet1.add(searchField);
        searchField.setTabIndex(1);
        searchField.setFocus(true);
        paymentFieldSet1.add(typeSelect);
        paymentFieldSet1.add(submit);
        
        paymentFieldSet1.add(zeigenEntlassente);
        paymentFieldSet1.add(sizeSelect);
        form.setColumns(3);
        
        zeigenEntlassente.addAll(new String[] {"Alle", "Entlassente", "im Betrieb"});
        
        typeSelect.addAll(new String[] {"Name", "Nachname", "Stelle", "Gehalt"});
        
        sizeSelect.addAll(new String[] {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50"});
        sizeSelect.setAttribute("onchange", "form.submit();");
        typeSelect.setAttribute("onchange", "form.submit();");
        zeigenEntlassente.setAttribute("onchange", "form.submit();");
        //textField.setAttribute("onkeyup", "onAction");
        searchField.setActionListener(new ActionListener(){

            @Override
            public boolean onAction(Control source) {
                 table.onRender();
                return true;
            }
      
        });
        submit.setAttribute("onclick", "form.submit();");
        
        form.restoreState(getContext());

 
    }
    
    public void initNewForm(){
        FieldSet paymentFieldSet = new FieldSet("Neuer Angestellter");
        paymentFieldSet.setStyle("background-color", "#f4f4f4");
        addControl(newForm);
        newForm.add(paymentFieldSet);
        
        paymentFieldSet.add(fieldName);
        paymentFieldSet.add(fieldNachName);
        
        paymentFieldSet.add(newSubmit);

        
        newForm.restoreState(getContext());

 
    }
   
    public AngestellteService getAngestellteService() {
        
        String type = typeSelect.getValue();
        String value = searchField.getValue();
        String etlassente = zeigenEntlassente.getValue();
        if ("Gehalt".equals(type)) {
            try{
              int num = Integer.parseInt(value);
              // is an integer!
            } catch (NumberFormatException e) {
                searchField.setValue("");
                value = "";
                // not an integer!
            }

        }

        
        angestellteService = new AngestellteService(type, value, etlassente);
        return angestellteService;
    }

    @Override
    public void onPost() {

        form.saveState(getContext());
        table.saveState(getContext());
    }
    
    @Override
    public void onRender() {
        
        table.setPageSize(Integer.valueOf(sizeSelect.getValue()));
        
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
