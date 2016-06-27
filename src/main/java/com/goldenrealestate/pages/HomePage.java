package com.goldenrealestate.pages;

import org.apache.wicket.markup.html.image.InlineImage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/", alt = "/home")
public class HomePage extends BasePage {
    public HomePage(PageParameters pageParameters){
        super(pageParameters);
        add(new InlineImage("imagetest", new PackageResourceReference(getClass(),"Dubair-Tower-1.jpg")));
    }
}
