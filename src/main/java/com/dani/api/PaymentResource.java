import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.dani.model.Order_;
import com.dani.model.Orders_;

import com.dani.service.OrderServiceImpl;
import com.squareup.square.models.Payment;
import com.dani.model.Payment_;
import com.dani.model.ResponseResult;
import com.squareup.square.models.*;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.dani.service.PaymentServiceImpl;
import com.squareup.square.exceptions.ApiException;

import java.util.Collections;
import java.util.concurrent.ExecutionException;




/*


/**
 *
 * @author danyr59
 */
@Path("v1/payment")
public class PaymentResource {
    PaymentServiceImpl service = new PaymentServiceImpl();
    
    @POST
    @Consumes("application/json")
    public Response create_payment(Payment_ request) throws InterruptedException, ExecutionException{
        System.out.println("En payment");
        ResponseResult result = service.createPayment(request);
        //service.createPayment(request);
        
                
        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }
    
}
