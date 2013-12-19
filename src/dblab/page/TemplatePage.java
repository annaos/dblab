
package dblab.page;

import org.apache.click.Page;
import org.apache.click.control.ActionButton;
import org.apache.click.util.ClickUtils;

/**
 * Provides a page border template. This Page returns the template
 * <tt>"border-template.htm"</tt>, and sets the Page model values <tt>$title</tt> and
 * <tt>$srcPath</tt>.
 * <p/>
 * Please note this page is designed for extending by Page subclasses and will
 * not be auto mapped as the template name <tt>"border-template.htm"</tt> does
 * not match the Pages class name <tt>BorderPage</tt>.
 */
public class TemplatePage extends Page {

    private static final long serialVersionUID = 1L;
    private ActionButton button = new ActionButton("button");


    // Constructor ------------------------------------------------------------

    /**
     * Create a BorderedPage and set the model attributes <tt>$title</tt> and
     * <tt>$srcPath</tt>.
     * <ul>
     * <li><tt>$title</tt> &nbsp; - &nbsp; the Page title from classname</li>
     * <li><tt>$srcPath</tt> &nbsp; - &nbsp; the Page Java source path</li>
     * </ul>
     */
    public TemplatePage() {
        String className = getClass().getName();

        String shortName = className.substring(className.lastIndexOf('.') + 1);
        String title = ClickUtils.toLabel(shortName);
        addModel("title", title);

        String srcPath = className.replace('.', '/') + ".java";
        addModel("srcPath", srcPath);
    }

    // Event Handlers ---------------------------------------------------------

    /**
     * @see org.apache.click.Page#onInit()
     */
    @Override
    public void onInit() {
        super.onInit();

        addControl(button);
    }

    /**
     * @see org.apache.click.Page#onDestroy()
     */
    @Override
    public void onDestroy() {
        // Remove menu for when BorderPage is serialized
     //   if (button != null) {
     //       removeControl(button);
     //   }
    }

    // Public Methods ---------------------------------------------------------

    /**
     * Returns the name of the border template: &nbsp; <tt>"/border-template.htm"</tt>
     * <p/>
     * Please note this page is designed for extending by Page subclasses and will
     * not be auto mapped as the template name <tt>"border-template.htm"</tt> does
     * not match the Pages class name <tt>BorderPage</tt>.
     *
     * @return 
     * @see org.apache.click.Page#getTemplate()
     */
    @Override
    public String getTemplate() {
        return "/template.htm";
    }

}
