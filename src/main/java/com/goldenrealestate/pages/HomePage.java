package com.goldenrealestate.pages;

import com.goldenrealestate.model.Employee;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.InlineImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/", alt = "/home")
public class HomePage extends BasePage {
    public HomePage(PageParameters pageParameters){
        super(pageParameters);
        add(new InlineImage("imagetest", new PackageResourceReference(getClass(),"Dubair-Tower-1.jpg")));
    }
}
