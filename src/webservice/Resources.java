package webservice;

import webservice.model.Product;

import webservice.service.StoreService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/products")
public class Resources {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces( { MediaType.APPLICATION_JSON })
    public Response loadProducts() throws Exception{
        List<Product> productList = StoreService.getAllProducts();

        if (productList == null || productList.isEmpty()){

        }

        return Response.ok(productList).build();
    }


    @Path("{id}")
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces( { MediaType.APPLICATION_JSON })
    public Response loadSingleProduct(@PathParam("id") int id) throws Exception{
        Product product = StoreService.getProductById(id);

        //Respond with a 404 if there is no such product_list item for the id provided
        if(product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a product_list_item object to return as response
        return Response.ok(product).build();
    }

}
