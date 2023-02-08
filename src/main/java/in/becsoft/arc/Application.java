package in.becsoft.arc;


import in.becsoft.arc.core.api.PolicyApis;
import in.becsoft.arc.core.api.RestEntry;
import io.vertx.core.Vertx;

public class Application  {

        public static void main(String[] args){
                Vertx  vertx= Vertx.vertx();
                vertx.deployVerticle(new RestEntry(new PolicyApis()));

        }
}
