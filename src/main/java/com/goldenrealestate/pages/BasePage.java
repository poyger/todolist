package com.goldenrealestate.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import static de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents.transform;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    public BasePage(final PageParameters parameters) {
        super(parameters);
        add(new HtmlTag("html"));
        add(newNavbar("navbar"));
    }

    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);

        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);

        // show brand name
        navbar.setBrandName(Model.of("Golden Real Estate"));

        navbar.addComponents(transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(HomePage.class, Model.of("Home")).setIconType(GlyphIconType.home),
                new NavbarButton<Void>(EmployeePage.class, Model.of("Employee"))
        ));
        return navbar;
    }
}
