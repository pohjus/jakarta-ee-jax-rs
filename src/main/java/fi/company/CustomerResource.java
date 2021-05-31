package fi.company;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.*;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/customers")
@RequestScoped
public class CustomerResource {

    @Inject
    CrudRepository crudRepository;


    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deleteCustomer(@PathParam("id") int id) {
        boolean result = this.crudRepository.deleteCustomerWithId(id);
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Customer getCustomer(@PathParam("id") int id) {
        Optional<Customer> customer = crudRepository.getCustomerWithId(id);
        return customer.isPresent() ? customer.get() : null;
    }

    @GET
    @Produces("application/json")
    public List<Customer> getCustomers() {
        return crudRepository.getAllCustomers();
    }


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Customer addCustomer(Customer customer) {
        Customer addedCustomer = crudRepository.addCustomer(customer);
        return customer;
    }


}
