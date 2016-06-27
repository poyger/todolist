package com.goldenrealestate.pages;

import com.goldenrealestate.dao.BuildingDao;
import com.goldenrealestate.dao.EmployeeDao;
import com.goldenrealestate.dao.TaskDao;
import com.goldenrealestate.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

import static com.goldenrealestate.dao.TaskDao.save;

@MountPath(value = "/task")
public class TaskPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(TaskPage.class);
    List<Task> taskList = new ArrayList<>();

    public TaskPage(PageParameters pageParameters) {
        super(pageParameters);

        final Task task = new Task();
        final IModel<Task> iModel = new CompoundPropertyModel<>(task);
        final Form<Task> form = new Form<Task>("form", iModel) {
            @Override
            protected void onSubmit() {
                LOGGER.info("Created task {}", task);
                save(task);
                task.setTaskDescription(null);
                super.onSubmit();
            }
        };

        TextField taskDescriptionTextField = new RequiredTextField("taskDescription");
        form.add(taskDescriptionTextField);

        DropDownChoice employeeDropDownChoice = new DropDownChoice(
                "employee",
                EmployeeDao.getAll());
        form.add(employeeDropDownChoice);

        DropDownChoice buildingDropDownChoice = new DropDownChoice(
                "building",
                BuildingDao.getAll());
        form.add(buildingDropDownChoice);

        add(form);

        ListView<Task> listView = new ListView<Task>("tasks", new PropertyModel(this, "taskList")) {
            @Override
            protected void populateItem(ListItem item) {
                Task result = (Task) item.getModelObject();
                item.add(new Label("taskDescription", result.getTaskDescription()));
                item.add(new Label("employee", result.getEmployee()));
                item.add(new Label("building", result.getBuilding()));
            }
        };
        add(listView);
    }

    @Override
    protected void onBeforeRender() {
        taskList = TaskDao.getAll();
        super.onBeforeRender();
    }

}
