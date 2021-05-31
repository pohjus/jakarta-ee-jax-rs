package fi.company;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
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
        return result ? "{ \"status\": \"HTTP DELETE with id = " + id + "\" }" : "{\"status\": \"not found\"}";
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getCustomer(@PathParam("id") int id) {
        Optional<Customer> customer = crudRepository.getCustomerWithId(id);
        return customer.isPresent() ? customer.get().toString() : "{\"status\": \"not found\"}";
    }

    @GET
    @Produces("application/json")
    public String getCustomers() {
        return crudRepository.getAllCustomers().toString();
    }

}
