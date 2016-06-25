package com.goldenrealestate.pages;

import com.goldenrealestate.dao.EmployeeDao;
import com.goldenrealestate.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

import static com.goldenrealestate.dao.EmployeeDao.*;

@MountPath(value = "/employee")
public class EmployeePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(EmployeePage.class);
    List<Employee> employees = new ArrayList<>();

    public EmployeePage(PageParameters pageParameters) {
        super(pageParameters);
        final Employee employee = new Employee();
        final Form form = new Form("form", new CompoundPropertyModel(employee)) {
            protected void onSubmit() {
                LOGGER.info("Created employee {}", employee);
                save(employee);
            }
        };
        final TextField name = new TextField("name");
        final TextField age = new TextField("age");
        form.add(name);
        form.add(age);
        add(form);

        ListView<Employee> listView = new ListView<Employee>("employees", new PropertyModel(this, "employees")) {
            @Override
            protected void populateItem(ListItem item) {
                Employee result = (Employee) item.getModelObject();
                item.add(new Label( "name", result.getName()));
                item.add(new Label("age", result.getAge()));
            }
        };
        add(listView);
    }

    @Override
    protected void onBeforeRender() {
        employees = getAll();
        super.onBeforeRender();
    }

}
