package sample.customer.biz.service;

import org.springframework.stereotype.Service;
import sample.customer.biz.domain.Customer;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MockCustomerService implements CustomerService {
    private Map<Integer, Customer> customerMap = new LinkedHashMap<Integer, Customer>();

    private int nextId = 0;

    @Override
    public List<Customer> findAll() {
        return new ArrayList<Customer>(customerMap.values());
    }

    @Override
    public Customer findById(int id) throws DataNotFoundException {
        if(!customerMap.containsKey(id)){
            throw new DataNotFoundException();
        }
        return customerMap.get(id);
    }

    @Override
    public void register(Customer customer) {
        customer.setId(nextId++);
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public void update(Customer customer) throws DataNotFoundException {
        if(!customerMap.containsKey(customer.getId())){
            throw new DataNotFoundException();
        }
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public void delete(int id) throws DataNotFoundException {
        if(!customerMap.containsKey(id)){
            throw new DataNotFoundException();
        }
        customerMap.remove(id);
    }

    @PostConstruct
    public void initCustomer() {
        nextId = 1;

        register(new Customer("tarou", "taro@aa.bb.cc", LocalDate.of(2018, 10, 1), 1));
        register(new Customer("jirou", "jiro@aa.bb.cc", LocalDate.of(2018, 10, 1), 2));
        register(new Customer("saburou", "sabu@aa.bb.cc", LocalDate.of(2018, 10, 1), 3));
    }
}
