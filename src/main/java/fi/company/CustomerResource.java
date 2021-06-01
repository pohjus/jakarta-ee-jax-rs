package fi.company;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/customers")
@RequestScoped
public class CustomerResource{

    @Inject
    CrudRepository crudRepository;


    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Delete customer by id", description = "Deletes customer with the given id from the database")
    @APIResponse(responseCode = "204", description = "Customer with the id was deleted")
    @APIResponse(responseCode = "404", description = "Customer was not found")
    @Tag(name = "Customer API", description = "This API is crud api for customers")
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
    @Operation(summary = "Get customer by id", description = "Returns customer with the given id from the database")
    @APIResponse(responseCode = "404", description = "Customer id was not found")
    @APIResponse(responseCode = "200", description = "Customer was found")
    @Tag(name = "Customer API", description = "This API is crud api for customers")
    public Response getCustomer(@PathParam("id") int id) {
        Optional<Customer> customer = crudRepository.getCustomerWithId(id);

        if(customer.isPresent()) {
            return Response.ok(customer.get()).build();
        } else {
            throw new NotFoundException("id was not found " + id);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get all customers", description = "Returns all available customers from the database")
    @Tag(name = "Customer API", description = "This API is crud api for customers")
    public List<Customer> getCustomers() {
        return crudRepository.getAllCustomers();
    }


    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Add customer", description = "Adds a customer to the database")
    @APIResponse(description = "Customer",
                 content = @Content(schema = @Schema(implementation = Customer.class)))
    @APIResponse(responseCode = "201", description = "Customer was added")
    @Tag(name = "Customer API", description = "This API is crud api for customers")
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
