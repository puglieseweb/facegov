package com.facegov.ui.mobi.contactlist.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.site.SitePreferenceUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Based on the device making the request and on the SitePreferences selected resolves the logical view name retuning
 * the appropriate view object.
 *
 * @author Emanuele Pugliese
 */
public class SitePreferenceDelegatingViewResolver extends WebApplicationObjectSupport
        implements
        /*  Mark this object as one used to resolve views by name */
        ViewResolver,
        Ordered,
        /* This interface is used to check that this object is initialized correctly */
        InitializingBean {

    // -------------------- CONSTANTS SECTION --------------------

    private static final Log LOGGER = LogFactory.getLog(SitePreferenceDelegatingViewResolver.class);

    // -------------------- ATTRIBUTES SECTION --------------------

    private int order = Ordered.HIGHEST_PRECEDENCE;

    private List<ViewResolver> normalViewResolvers;
    private List<ViewResolver> mobileViewResolvers;

    // -------------------- METHODS SECTION --------------------

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public void setNormalViewResolvers(List<ViewResolver> normalViewResolvers) {
        LOGGER.debug("Injected normal views are: " + normalViewResolvers);
        this.normalViewResolvers = normalViewResolvers;
    }

    public void setMobileViewResolvers(List<ViewResolver> mobileViewResolvers) {
        LOGGER.debug("Injected mobile views are: " + mobileViewResolvers);
        this.mobileViewResolvers = mobileViewResolvers;
    }

    /**
     * Resolve the logic viewName to a View Object.
     *
     * @param viewName not device related view name
     * @param locale   internationalization
     * @return View Object or null
     * @throws Exception
     */
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        final SitePreference sitePreference = SitePreferenceUtils.getCurrentSitePreference(request);
        final Device device = DeviceUtils.getRequiredCurrentDevice(request);

        List<ViewResolver> viewResolvers = null;
        if (SitePreference.MOBILE == sitePreference || device.isMobile() && sitePreference == null) {
            LOGGER.debug("resolving viewName '" + viewName + "' using mobileViewResolvers.");
            viewResolvers = this.mobileViewResolvers;
        } else {
            LOGGER.debug("resolving viewName '" + viewName + "' using normalViewResolvers.");
            viewResolvers = this.normalViewResolvers;
        }

        return resolveViewNameInternal(viewResolvers, viewName, locale);
    }


    /**
     * Resolve the given view name into a View object.  The default implementation
     * asks all provided ViewResolvers until a
     *
     * @param viewResolvers Device specific List of View Object
     * @param viewName      view name
     * @param locale        internationalization
     * @return view Object or null
     * @throws Exception
     */
    protected View resolveViewNameInternal(List<ViewResolver> viewResolvers, String viewName, Locale locale) throws Exception {

        for (ViewResolver viewResolver : viewResolvers) {
            View view = viewResolver.resolveViewName(viewName, locale);
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * Validate that we have at least one viewResolver specified for normal and mobile
         */
        Assert.notNull(this.normalViewResolvers, "'normalViewResolvers' must not be null");
        Assert.notEmpty(this.normalViewResolvers, "'normalViewResolvers' must not be empty");
        // Keep the normal ViewResolvers in sorted order.
        OrderComparator.sort(this.normalViewResolvers);

        Assert.notNull(mobileViewResolvers, "'mobileViewResolvers' must not be null");
        Assert.notEmpty(mobileViewResolvers, "'mobileViewResolvers' must not be empty");
        // Keep the mobile ViewResolvers in sorted order.
        OrderComparator.sort(this.normalViewResolvers);
    }

}
