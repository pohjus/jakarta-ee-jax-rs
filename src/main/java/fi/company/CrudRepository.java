package fi.company;


import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    public List<Customer> getAllCustomers();
    public Optional<Customer> getCustomerWithId(int id);
    public boolean deleteCustomerWithId(int id);
    public Customer addCustomer(Customer customer);
}
