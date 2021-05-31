package fi.company;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CrudRepositoryImpl implements CrudRepository {


    int lastId = 0;

    List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();
        customers.add(new Customer(lastId++, "Jack"));
        customers.add(new Customer(lastId++, "Tina"));
        customers.add(new Customer(lastId++, "Hannah"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerWithId(int id) {
        return customers.stream().filter(cust -> cust.getId() == id).findFirst();
    }

    @Override
    public boolean deleteCustomerWithId(int id) {
        List<Customer> temp = customers.stream().filter(cust -> cust.getId() != id).collect(Collectors.toList());
        System.out.println(temp);
        if(temp.size() != customers.size()) {
            this.customers = temp;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setId(lastId++);
        this.customers.add(customer);
        return customer;
    }
}
