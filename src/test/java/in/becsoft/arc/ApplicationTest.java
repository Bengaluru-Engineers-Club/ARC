package in.becsoft.arc;

import in.becsoft.arc.core.api.RestEntry;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class ApplicationTest {

    @BeforeEach
    void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
        vertx.deployVerticle(new RestEntry(), testContext.succeeding(id -> testContext.completeNow()));
    }

    @Test
    void verticle_deployed(Vertx vertx, VertxTestContext testContext) throws Throwable {
        testContext.completeNow();
    }
}