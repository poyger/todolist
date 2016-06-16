package com.goldenrealestate.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * User: Poyan Gerami
 * Email: poyan.gerami@eniro.com
 * Date: 16/06/16
 */
@MountPath(value = "/", alt = "/home")
public class HomePage extends BasePage {
    public HomePage(PageParameters parameters) {
        super(parameters);
        add(new Label("home-message", "Hello home!"));
    }
}
