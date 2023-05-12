
package com.dani.config;
import com.helpers.ClientSquare;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import com.helpers.ClientSquare;

@ApplicationPath("/api")
public class AppConfig extends Application {
   public ClientSquare sq;
   public AppConfig(){
       sq = new ClientSquare();
   }
    
    
}
