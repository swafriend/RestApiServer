package sample.customer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.customer.biz.domain.Customer;
import sample.customer.biz.service.CustomerService;
import sample.customer.biz.service.DataNotFoundException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by haruna on 2018/04/30.
 */

@Controller
public class CustomerListController {


//    @Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerListController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/", method = GET)
    public String home(){
        return "forward:/customer";
    }

    @RequestMapping(value = "/customer", method = GET)
    public String showAllCustomers(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @RequestMapping(value = "/customer/{customerId}", method = GET)
    public String showCustomerDetail(@PathVariable("customerId") int id, Model model) throws DataNotFoundException{
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/detail";
    }

    @ExceptionHandler(DataNotFoundException.class)
    public String handleException(){
        return "customer/notfound";
    }


    private static String xml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<customer>")
                .append("  <name>追加郎</name>")
                .append("  <emailAddress>tsuika@aa.bb.cc</emailAddress>")
                .append("  <birthday>1985-03-21T00:00:00+09:00</birthday>")
                .append("  <favoriteNumber>7</favoriteNumber>")
                .append("</customer>");

        return sb.toString();
    }
}
