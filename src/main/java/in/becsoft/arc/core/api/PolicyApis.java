package in.becsoft.arc.core.api;

import io.vertx.core.*;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;



public class PolicyApis implements ApiEndpoints {


    Router policyRouter;


    @Override
    public void init(Vertx vertx,Router router) {
        this.policyRouter = Router.router(vertx);
        router.route("/policy*").subRouter(this.policyRouter);
        System.out.println("setting up policy route");
        info();
        resolveAccess();

        for(Route r:this.policyRouter.getRoutes()){
            System.out.println(r.getPath());
        }


    }

    private void info() {

        System.out.println("setting up info route");
        this.policyRouter.route("/info").handler(event -> {
            HttpServerRequest request = event.request();
            HttpServerResponse response = request.response();
            response.putHeader("contentType","application/text");
            response.end(" working on policy info");
        });
    }

    void resolveAccess(){
        this.policyRouter.get("/resolveAccess").handler(event -> {
            HttpServerRequest request = event.request();
            String accountId = request.getHeader("arc-account-id");
            String jwt = request.getHeader("security");
            String serviceId = request.getParam("serviceId");
            String resourceId = request.getParam("resourceId");
            HttpServerResponse response = request.response();
            response.putHeader("contentType","application/text");
            response.end(" worked ");
        });
    }


}
