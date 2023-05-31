import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import com.dani.model.WraperCreatePayment;
import com.dani.model.ResponseResult;
import com.dani.service.PaymentServiceImpl;
import java.util.concurrent.ExecutionException;


/**
 *
 * @author danyr59
 */
@Path("v1/payment")
public class PaymentResource {
    PaymentServiceImpl service = new PaymentServiceImpl();
    
    @POST
    @Consumes("application/json")
    public Response create_payment(WraperCreatePayment request) throws InterruptedException, ExecutionException{
        System.out.println("En payment");
        ResponseResult result = service.createPayment(request);
        //service.createPayment(request);
        
                
        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }
    
}
