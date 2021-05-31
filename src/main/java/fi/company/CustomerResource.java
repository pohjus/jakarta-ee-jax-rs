package fi.company;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/customers")
public class CustomerResource {

    List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Jack"));
    }


    @GET
    @Produces("application/json")
    public String getDateInHtml() {
        return customers.toString();
    }

}
