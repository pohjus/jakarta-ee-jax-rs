package fi.company;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.ws.rs.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/customers")
@ApplicationScoped
public class CustomerResource {

    List<Customer> customers;

    @PostConstruct
    public void init() {
        System.out.println("INIT!");
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Jack"));
        customers.add(new Customer(2, "Tina"));
        customers.add(new Customer(3, "Hannah"));
    }


    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deleteCustomer(@PathParam("id") int id) {
        List<Customer> temp = customers.stream().filter(cust -> cust.getId() != id).collect(Collectors.toList());
        System.out.println(temp);
        if(temp.size() != customers.size()) {
            this.customers = temp;
            return "{ \"status\": \"HTTP DELETE with id = " + id + "\" }";
        } else {
            return "{\"status\": \"not found\"}";
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getCustomer(@PathParam("id") int id) {
        Optional<Customer> customer = customers.stream().filter(cust -> cust.getId() == id).findFirst();
        if(customer.isPresent()) {
            return customer.get().toString();
        } else {
            return "{\"error\": \"id not found\"}";
        }
    }

    @GET
    @Produces("application/json")
    public String getDateInHtml() {
        System.out.println(customers);

        return customers.toString();
    }

}
