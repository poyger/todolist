package com.goldenrealestate;

import com.goldenrealestate.pages.BasePage;
import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return BasePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
        Bootstrap.install(this);
        new AnnotatedMountScanner().scanPackage("com.goldenrealestate.pages").mount(this);
    }
}
