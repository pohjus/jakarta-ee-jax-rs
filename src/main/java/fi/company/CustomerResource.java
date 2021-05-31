package fi.company;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/customers")
public class CustomerResource {

    List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Jack"));
        customers.add(new Customer(2, "Tina"));
        customers.add(new Customer(3, "Hannah"));

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
        return customers.toString();
    }

}
