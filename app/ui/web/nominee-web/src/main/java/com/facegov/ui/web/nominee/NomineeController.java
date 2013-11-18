package com.facegov.ui.web.nominee;

import com.facegov.ui.web.nominee.model.Member;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Controller for serving an processing the Nominee form.
 *
 * @author Emanuele Pugliese
 */
public class NomineeController {

    // ---------------------- CONSTANTS SECTION ----------------------

    private static final Logger LOGGER = Logger.getLogger(NomineeController.class);

    // ---------------------- ATTRIBUTES SECTION ----------------------

    private String thanksViewName;

    // ---------------------- METHODS SECTION ----------------------

    /**
     * Inject the Thanks view name.
     *
     * @param thanksViewName thanks view name
     */
    public void setThanksViewName(String thanksViewName) {
        this.thanksViewName = thanksViewName;
    }

    /**
     * Handler for GET request and return to the view a {@link Member} object
     *
     * @return {@link Member}being nominated
     */
    @RequestMapping(method = RequestMethod.GET)
    public Member form() {
        return new Member();
    }

//    /**
//     * Handler for GET request for allowing the form to be prepopulate with the user data. Returns to the view a
//     * {@link Model} object.
//     *
//     * @param model nominee {@link Model}
//     */
//    @RequestMapping(method = RequestMethod.GET)
//    public void form(Model model) {
//        model.addAttribute("nominee", new Member());
//    }

    /**
     * Handler POST Request.
     *
     * @param member {@link Member} to nominate. By taking a Member parameter, the submitted form data will be
     *                             automatically bound to a Member bean and placed on the Model.
     * @return Thanks View name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processFormData(final Member member) {
        LOGGER.info("Processing nominee: " + member);
//        return "redirect:/" + thanksViewName;
        return thanksViewName;
    }


//
//    /**
//     * Handler POST Request.
//     *
//     * @param member {@link Member} to nominate. By taking a Member parameter, the submitted form data will be
//     *                             automatically bound to a Member bean and placed on the Model.
//     * @return Thanks View name
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public String processFormData(
//            @ModelAttribute("nominee") Member member,
//            Model model) {
//        LOGGER.info("Processing nominee: " + member);
//        Map map = model.asMap();
//        LOGGER.info("model[member]=" + map.get("member"));
//        LOGGER.info("model[nominee]=" + map.get("nominee"));
//        return thanksViewName;
//    }


}
