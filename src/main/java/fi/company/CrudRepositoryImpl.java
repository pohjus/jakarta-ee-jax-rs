package fi.company;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CrudRepositoryImpl implements CrudRepository {

    List<Customer> customers;

    @PostConstruct
    public void init() {
        System.out.println("INIT!");
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Jack"));
        customers.add(new Customer(2, "Tina"));
        customers.add(new Customer(3, "Hannah"));
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
        this.customers.add(customer);
        return customer;
    }
}
