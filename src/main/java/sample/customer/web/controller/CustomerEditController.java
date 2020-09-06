package sample.customer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.customer.biz.domain.Customer;
import sample.customer.biz.service.CustomerService;
import sample.customer.biz.service.DataNotFoundException;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by haruna on 2018/04/30.
 */
//@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/customer/{customerId}")
@SessionAttributes(value = "editCustomer")
public class CustomerEditController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/edit", method = GET)
    public String redirectEntryForm(@PathVariable(value = "customerId") int customerId, Model model)
            throws DataNotFoundException
    {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("editCustomer", customer);
        return "redirect:enter";
    }

    @RequestMapping(value = "/enter", method=GET)
    public String showEntryForm(){
        return "customer/edit/enter";
    }

    @RequestMapping(value = "/enter", params="_event_proceed", method = POST)
    public String verify(@Valid @ModelAttribute("editCustomer") Customer customer, Errors errors)
    {
        if(errors.hasErrors()){
            return "customer/edit/enter";
        }
        return "redirect:review";
    }
    @RequestMapping(value = "/review", method = GET)
    public String showReview() {
        return "customer/edit/review";
    }

    @RequestMapping(value = "/review", params = "_event_revise", method = POST)
    public String revise() {
        return "redirect:enter";
    }

    @RequestMapping(
            value = "/review", params = "_event_confirmed", method = POST)
    public String edit(
            @ModelAttribute("editCustomer") Customer customer,
            SessionStatus sessionStatus,
            RedirectAttributes redirectAttributes)
            throws DataNotFoundException {

        customerService.update(customer);

        return "redirect:edited";
    }

    @RequestMapping(value = "/edited", method = GET)
    public String showEdited(
            @ModelAttribute("editCustomer") Customer customer,
            SessionStatus sessionStatus,
            RedirectAttributes redirectAttributes) {

        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("editedCustomer", customer);

//        return "customer/edit/edited";
        return "redirect:showEiteded";
    }

    @RequestMapping(value = "/showEiteded", method=GET)
    public String showEiteded(){
        return "customer/edit/edited";
    }

    @ExceptionHandler(DataNotFoundException.class)
    public String handlerException(){
        return "customer/notfound";
    }




}
