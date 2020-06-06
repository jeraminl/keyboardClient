package webservice;

import javafx.scene.media.MediaPlayer;
import webservice.model.Order;
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

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response addOrder(@FormParam("firstName") String firstName,
                             @FormParam("lastName") String lastName,
                             @FormParam("email") String email,
                             @FormParam("phone") String phone,
                             @FormParam("address") String address,
                             @FormParam("city") String city,
                             @FormParam("state") String state,
                             @FormParam("zip") String zip,
                             @FormParam("shipMeth") String shipMeth,
                             @FormParam("billingFirstName") String billingFirstName,
                             @FormParam("billingLastName") String billingLastName,
                             @FormParam("ccNum") String ccNum,
                             @FormParam("billingAddress") String billingAddress,
                             @FormParam("billingCity") String billingCity,
                             @FormParam("billingState") String billingState,
                             @FormParam("billingZip") String billingZip,
                             @FormParam("productCart") String productCart,
                             @FormParam("finalPrice") String price){

        System.out.println(price);

        Order order = new Order();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZip(zip);
        order.setEmail(email);
        order.setPhone(phone);
        order.setProductCart(productCart);
        order.setShipMeth(shipMeth);
        order.setPrice(price);

        System.out.println(order.toString());

        if(StoreService.AddOrder(order)){
            return Response.ok().entity("order added successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }



}
