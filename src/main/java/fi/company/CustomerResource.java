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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
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
    public Response deleteCustomer(@PathParam("id") int id) {
        boolean result = this.crudRepository.deleteCustomerWithId(id);
        return result ? Response.noContent().build() : Response.status(404).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCustomer(@PathParam("id") int id) {
        Optional<Customer> customer = crudRepository.getCustomerWithId(id);
        return customer.isPresent() ? Response.ok(customer.get()).build() : Response.status(404).build();
    }

    @GET
    @Produces("application/json")
    public List<Customer> getCustomers() {
        return crudRepository.getAllCustomers();
    }




    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCustomer(Customer customer) {
        Customer addedCustomer = crudRepository.addCustomer(customer);
        return Response.created(getURI(addedCustomer.getId())).entity(addedCustomer).build();
    }

    @Context
    private UriInfo info;

    private URI getURI(int id) {
        UriBuilder builder = info.getAbsolutePathBuilder();
        builder.path("" + id);
        return builder.build();
    }


}
