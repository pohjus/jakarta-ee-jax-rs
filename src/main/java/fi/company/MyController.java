package fi.company;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.inject.*;
import java.util.*;
import javax.ws.rs.*;
import javax.persistence.*;
import javax.annotation.*;
import javax.enterprise.context.*;

@Path("/customers")
public class MyController {
    @GET
    @Produces("text/html")
    public String result() {
        String result = "<html><head><title></title></head><body><h1>Hello!</h1></body></html>";
        return result;
    }
}
