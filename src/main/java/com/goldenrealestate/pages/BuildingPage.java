package com.goldenrealestate.pages;

import com.goldenrealestate.dao.BuildingDao;
import com.goldenrealestate.dao.TaskDao;
import com.goldenrealestate.model.Building;
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
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.List;
import java.util.stream.Collectors;

import static com.goldenrealestate.dao.BuildingDao.save;

@MountPath(value = "/building")
public class BuildingPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(BuildingPage.class);
    public BuildingPage(PageParameters pageParameters) {
        super(pageParameters);
        final Building building = new Building();
        final Form form = new Form("form", new CompoundPropertyModel(building)) {
            protected void onSubmit() {
                LOGGER.info("Created building {}", building);
                save(building);
                building.setName(null);
            }
        };
        final TextField name = new RequiredTextField("name");
        form.add(name);
        add(form);

        final Task task = new Task();
        final IModel<Task> iModel = new CompoundPropertyModel<>(task);
        final Form getTasks = new Form("get-tasks-form", iModel);
        IModel<List<Building>> modelBuildingChoice = new AbstractReadOnlyModel<List<Building>>() {
            @Override
            public List<Building> getObject() {
                return BuildingDao.getAll();
            }
        };


        DropDownChoice buildingDropDownChoice = new DropDownChoice(
                "building",
                modelBuildingChoice);
        getTasks.add(buildingDropDownChoice);


        final Model<String> outputTaskModel = Model.of();
        final Label outputTaskLabel = new Label("tasks", outputTaskModel);
        outputTaskLabel.setOutputMarkupId(true);
        add(outputTaskLabel);

        AjaxButton ab = new AjaxButton("get-tasks-button") {
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                if (target != null) {
                    List<Task> tasks = TaskDao.getTask(task.getBuilding());
                    List<String> taskDescriptions = tasks.stream().map(Task::getTaskDescription).collect(Collectors.toList());
                    if (!taskDescriptions.isEmpty()) {
                        outputTaskModel.setObject(taskDescriptions.toString());
                    } else {
                        outputTaskModel.setObject("No tasks for building " + task.getBuilding());
                    }
                    target.add(outputTaskLabel);
                }
            }
        };
        getTasks.add(ab);
        add(getTasks);



    }
}
