package com.goldenrealestate.pages;

import com.goldenrealestate.model.Employee;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/", alt = "/home")
public class HomePage extends BasePage {
    private Label firstLabel;
    private Label secondLabel;
    public HomePage(PageParameters pageParameters){
        super(pageParameters);
        Employee label = new Employee();
        label.setAge(24);
        firstLabel = new Label("label", label);
        secondLabel = new Label("label", "Second label");
        add(firstLabel);
        add(new Link("reload") {
            @Override
            public void onClick() {
                if(getPage().contains(firstLabel, true))
                    getPage().replace(secondLabel);
                else
                    getPage().replace(firstLabel);
            }
        });
    }

  /**  @Override
    protected void onBeforeRender() {
        if(contains(firstLabel, true))
            replace(secondLabel);
        else
            replace(firstLabel);

        super.onBeforeRender();
    } **/

  @Override
  protected void onInitialize() {
      super.onInitialize();
      visitChildren(new IVisitor<Component, Void>() {
          @Override
          public void component(Component component, IVisit<Void> arg1) {
              if(!component.isStateless())
                  System.out.println("Component " + component.getId() + " is not stateless");
          }
      });
  }


}
