package fi.company;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.LocalDate;

@Path("/date")
public class MyResource {

    @Path("/text")
    @GET
    @Produces("text/plain")
    public String getDateInText() {
        return LocalDate.now().toString();
    }

    @Path("/html")
    @GET
    @Produces("text/html")
    public String getDateInHtml() {
        return LocalDate.now().toString();
    }


}
