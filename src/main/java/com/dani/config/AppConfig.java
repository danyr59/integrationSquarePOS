
package com.dani.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import com.helpers.ClientSquareImpl;

@ApplicationPath("/api")
public class AppConfig extends Application {
  // public ClientSquareImpl sq;
   public AppConfig(){
       //sq = new ClientSquareImpl();
   }
    
    
}
