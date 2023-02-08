package in.becsoft.arc.core.api;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public interface ApiEndpoints {
    public void init(Vertx vertx, Router router);
}
