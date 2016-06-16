package com.goldenrealestate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.goldenrealestate.model.*;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * User: Poyan Gerami
 * Email: poyan.gerami@eniro.com
 * Date: 16/06/16
 */
@MountPath(value = "/employee")
public class EmployeePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(EmployeePage.class);

    public EmployeePage(PageParameters pageParameters) {
        super(pageParameters);
        final Employee employee = new Employee();
        final Form form = new Form("form", new CompoundPropertyModel(employee)) {
            protected void onSubmit() {
                LOGGER.info("Created employee {}", employee);
                //setResponsePage(new EmployeePage(employee));
            }
        };
        final TextField name = new TextField("name");
        final TextField age = new TextField("age");
        form.add(name);
        form.add(age);
        add(form);
    }
}
