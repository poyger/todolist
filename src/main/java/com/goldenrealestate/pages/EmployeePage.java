package com.goldenrealestate.pages;

import com.goldenrealestate.dao.EmployeeDao;
import com.goldenrealestate.dao.TaskDao;
import com.goldenrealestate.model.Employee;
import com.goldenrealestate.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.goldenrealestate.dao.EmployeeDao.getAll;
import static com.goldenrealestate.dao.EmployeeDao.save;

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
        final TextField name = new RequiredTextField("name");
        final TextField age = new RequiredTextField("age");
        form.add(name);
        form.add(age);
        add(form);

        ListView<Employee> listView = new ListView<Employee>("employees", new PropertyModel(this, "employees")) {
            @Override
            protected void populateItem(ListItem item) {
                Employee result = (Employee) item.getModelObject();
                item.add(new Label("name", result.getName()));
                item.add(new Label("age", result.getAge()));
            }
        };
        add(listView);


        final Task task = new Task();
        final IModel<Task> iModel = new CompoundPropertyModel<>(task);
        final Form getTasks = new Form("get-tasks-form", iModel);
        IModel<List<Employee>> modelEmployeeChoice = new AbstractReadOnlyModel<List<Employee>>() {
            @Override
            public List<Employee> getObject() {
                return EmployeeDao.getAll();
            }
        };


        DropDownChoice employeeDropDownChoice = new DropDownChoice(
                "employee",
                modelEmployeeChoice);
        getTasks.add(employeeDropDownChoice);


        final Model<String> outputTaskModel = Model.of();
        final Label outputTaskLabel = new Label("tasks", outputTaskModel);
        outputTaskLabel.setOutputMarkupId(true);
        add(outputTaskLabel);

        AjaxButton ab = new AjaxButton("get-tasks-button") {
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                if (target != null) {
                    List<Task> tasks = TaskDao.getTask(task.getEmployee());
                    List<String> taskDescriptions = tasks.stream().map(Task::getTaskDescription).collect(Collectors.toList());
                    if (!taskDescriptions.isEmpty()) {
                        outputTaskModel.setObject(taskDescriptions.toString());
                    } else {
                        outputTaskModel.setObject("No tasks for employee " + task.getEmployee());
                    }
                    target.add(outputTaskLabel);
                }
            }
        };
        getTasks.add(ab);
        add(getTasks);
    }

    @Override
    protected void onBeforeRender() {
        employees = getAll();
        super.onBeforeRender();
    }

}
