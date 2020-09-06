package sample.customer.web.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sample.customer.biz.domain.Customer;
import sample.customer.biz.domain.CustomerResourceQuery;
import sample.customer.biz.service.CustomerService;
import sample.customer.biz.service.DataNotFoundException;

import javax.validation.Valid;
import javax.xml.crypto.Data;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Void> register(@RequestBody Customer customer,
                                         UriComponentsBuilder uriBuilder){
        customerService.register(customer);

        URI resourceUri = uriBuilder
                .path("api/customer/{customerId}")
                .buildAndExpand(customer.getId())
                .encode()
                .toUri();

        return ResponseEntity.created(resourceUri).build();
    }

    @RequestMapping(path="/{customerId}", method = GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Customer findById(@PathVariable int customerId) throws DataNotFoundException {

//        throw new DataNotFoundException();
        return customerService.findById(customerId);
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public String handleException(DataNotFoundException e){
//        return "customer is not found";
//    }

    @RequestMapping(method = GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> searchCustomers(@Valid CustomerResourceQuery customerResourceQuery) throws DataNotFoundException {
        List<Customer> list = customerService.findAll();
        List<Customer> result =  list.stream().filter(tt ->
            ((Objects.isNull(customerResourceQuery.getName()) || tt.getName().contains(customerResourceQuery.getName())) &&
                    (Objects.isNull(customerResourceQuery.getBirthday()) || tt.getBirthday().equals(customerResourceQuery.getBirthday())))
        ).collect(Collectors.toList());

        if(result.isEmpty()) throw new DataNotFoundException();

        return result;
    }

}
