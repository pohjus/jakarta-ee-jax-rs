package fi.company;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.LocalDate;

@Path("/date")
public class MyResource {

    @Path("/text")
    @GET
    @Produces("text/plain")
    @Tag(name = "Date API", description = "This API is just for testing.")
    public String getDateInText() {
        return LocalDate.now().toString();
    }

    @Path("/html")
    @GET
    @Produces("text/html")
    @Tag(name = "Date API", description = "This API is just for testing.")
    public String getDateInHtml() {
        return LocalDate.now().toString();
    }


}
