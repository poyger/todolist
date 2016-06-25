package com.goldenrealestate.pages;

import com.goldenrealestate.dao.BuildingDao;
import com.goldenrealestate.model.Building;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

import static com.goldenrealestate.dao.BuildingDao.*;

@MountPath(value = "/building")
public class BuildingPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(BuildingPage.class);
    List buildings = new ArrayList<>();

    public BuildingPage(PageParameters pageParameters) {
        super(pageParameters);
        final Building building = new Building();
        final Form form = new Form("form", new CompoundPropertyModel(building)) {
            protected void onSubmit() {
                LOGGER.info("Created building {}", building);
                save(building);
            }
        };
        final TextField name = new RequiredTextField("name");
        form.add(name);
        add(form);

        ListView<Building> listView = new ListView<Building>("buildings", new PropertyModel(this, "buildings")) {
            @Override
            protected void populateItem(ListItem item) {
                Building result = (Building) item.getModelObject();
                item.add( new Label( "name", result.getName() ) );
            }
        };

        add(listView);


    }

    @Override
    protected void onBeforeRender() {
        buildings = getAll();
        super.onBeforeRender();
    }
}
