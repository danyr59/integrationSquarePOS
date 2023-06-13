
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import com.dani.model.WraperCreatePayment;
import com.dani.model.ResponseResult;
import com.dani.service.PaymentServiceImpl;
import com.squareup.square.SquareClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author danyr59
 */
@Path("v1/payment")
public class PaymentResource {

    @POST
    @Consumes("application/json")
    public Response create_payment(WraperCreatePayment request, @Context HttpServletRequest httpRequest) throws InterruptedException, ExecutionException {

        String token = httpRequest.getHeader("Authorization");
        if (token.equals("") || token == null) {

            return Response.ok().status(Response.Status.CONFLICT).entity(new ResponseResult("FAILURE Token not found", null, null)).build();
        }
        PaymentServiceImpl service = new PaymentServiceImpl(token);

        System.out.println("En payment");
        ResponseResult result = service.createPayment(request);
        //SquareClient.shutdown();
        //service.createPayment(request);

        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }

}
