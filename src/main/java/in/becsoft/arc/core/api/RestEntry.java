package in.becsoft.arc.core.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

public class RestEntry extends AbstractVerticle  {

    Router router;
    Route route;
    List<ApiEndpoints> apiEndpointsList;
    public RestEntry(ApiEndpoints ... apiEndpoints){
         apiEndpointsList = Collections.unmodifiableList(Arrays.asList(apiEndpoints));
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        HttpServer server = vertx.createHttpServer();


        router = Router.router(vertx);

        route = router.route("/core*");

        apiEndpointsList.forEach(apiEndpoints -> {
            System.out.println(" initializing endpoints");
            Router subRouter = Router.router(vertx);
            route.subRouter(subRouter);
            apiEndpoints.init(vertx,subRouter);
        });

        router.route("/core/info").handler(event -> {

            HttpServerRequest request = event.request();
            HttpServerResponse response = request.response();
            response.putHeader("contentType","application/text");
            response.end(" working on policy info");
        });

        server.requestHandler(router).listen(config().getInteger("http.port",8088),result->{
            if (result.succeeded()) {
                startPromise.complete();
            } else {
                startPromise.fail(result.cause());
            }
        });

    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        super.stop(stopPromise);
    }
}
