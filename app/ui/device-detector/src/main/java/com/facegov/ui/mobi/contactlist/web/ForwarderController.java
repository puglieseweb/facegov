/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.facegov.ui.mobi.contactlist.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.site.SitePreferenceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author Emanuele Pugliese
 */
@Controller
public final class ForwarderController {
    // -------------------- CONSTANTS SECTION --------------------

    private static final Log LOGGER = LogFactory.getLog(ForwarderController.class);

    @RequestMapping("/developers/jira")
    public String redirectToJira() {
        LOGGER.debug("Entering RequestMapping for Jira");
        return "redirect:http://facegov.homepc.it/developers/jira";
    }

    @RequestMapping("/developers/wiki")
    public String redirectToWiki() {
        LOGGER.debug("Entering RequestMapping for Jira");
        return "redirect:http://facegov.homepc.it:8000";

    }

    @RequestMapping("/*")
    public String redirect() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        final SitePreference sitePreference = SitePreferenceUtils.getCurrentSitePreference(request);
        final Device device = DeviceUtils.getRequiredCurrentDevice(request);

        if (SitePreference.MOBILE == sitePreference || device.isMobile() && sitePreference == null) {
            LOGGER.debug("Mobile device");
            return "redirect:http://facegov.homepc.it/w/home";
        } else {
            LOGGER.debug("Web browser");
            return "redirect:http://facegov.homepc.it/w/home";
        }
    }
}