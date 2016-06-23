package com.goldenrealestate.pages;

import com.goldenrealestate.dao.EmployeeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.goldenrealestate.model.*;
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
import java.util.LinkedHashMap;

@MountPath(value = "/employee")
public class EmployeePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(EmployeePage.class);
    ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public EmployeePage(PageParameters pageParameters) {
        super(pageParameters);
        final Employee employee = new Employee();
        final Form form = new Form("form", new CompoundPropertyModel(employee)) {
            protected void onSubmit() {
                Employee insertEmployee = new Employee();
                insertEmployee.setName(employee.getName());
                insertEmployee.setAge(employee.getAge());
                LOGGER.info("Created employee {}", employee);
                EmployeeDao.save(insertEmployee);
                //setResponsePage(new EmployeePage(employee));
            }
        };
        final TextField name = new TextField("name");
        final TextField age = new TextField("age");
        form.add(name);
        form.add(age);
        add(form);

     /**   Label label = new Label("employee", employeeArrayList) {
            @Override
            public boolean isVisible() {
                return !employeeArrayList.isEmpty();
            }
        };  **/

        ListView<Employee> listView = new ListView<Employee>("employees", employeeArrayList) {
            @Override
            protected void populateItem(ListItem<Employee> item) {
                item.add(new Label("name", new PropertyModel(item.getModel(), "name")));
                item.add(new Label("age", new PropertyModel(item.getModel(), "age")));
            }
            @Override
            public boolean isVisible() {
                return !employeeArrayList.isEmpty();
            }
        };

        add(listView);

    }

}
