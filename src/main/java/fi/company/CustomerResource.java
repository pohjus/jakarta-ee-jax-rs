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
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
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
public class CustomerResource{

    @Inject
    CrudRepository crudRepository;


    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteCustomer(@PathParam("id") int id) {
        boolean result = this.crudRepository.deleteCustomerWithId(id);
        if(result) {
            return Response.noContent().build();
        } else {
            throw new NotFoundException("id was not found " + id);
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCustomer(@PathParam("id") int id) {
        Optional<Customer> customer = crudRepository.getCustomerWithId(id);

        if(customer.isPresent()) {
            return Response.ok(customer.get()).build();
        } else {
            throw new NotFoundException("id was not found " + id);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> getCustomers() {
        return crudRepository.getAllCustomers();
    }




    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
