package nl.verheulconsultants.palettebugdemo;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.extensions.markup.html.form.palette.theme.DefaultTheme;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.model.util.ListModel;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    private final List<Person> selected = new ArrayList<>();
    private final List<Person> persons = new ArrayList<>();
    
    public HomePage(final PageParameters parameters) {
        super(parameters);
        
        persons.add(new Person(Integer.toString(0), "Jan Verheul"));
        persons.add(new Person(Integer.toString(1), "Erik Verheul"));
        persons.add(new Person(Integer.toString(2), "Kees Verheul"));

        IChoiceRenderer<Person> renderer = new ChoiceRenderer<>("fullName", "id");

        final Palette<Person> palette = new Palette<>("palette",
                new ListModel<>(selected),
                new CollectionModel<>(persons),
                renderer, 10, true, true);
//        uncomment this line for Wicket 7.*.*
        palette.add(new DefaultTheme());

        Form<Void> form = new Form<Void>("form") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                for (Person person : selected) {
                    info("Selected person: " + person);
                }
            }
        };

        add(form);
        form.add(palette);

        add(new FeedbackPanel("feedback"));
    }
}
