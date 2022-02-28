package com.airline.reservation.user.verticles;

import com.airline.reservation.user.controller.UserController;
import com.airline.reservation.user.httpValidations.userHttpValidation;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserVerticle extends AbstractVerticle {

  @Autowired
  UserController userController;

  @Autowired
  userHttpValidation validate;

  @Override
  public void start(Promise<Void> prom) {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.get("/").handler(ctx -> ctx.response().end("Entry point for application !!!"));
    router.post("/user/").handler(validate::createUserEndpointValidation).handler(userController::createUserController);
    router.get("/user/:user_id/").handler(userController::getUserByIdController);

    HttpServer httpServer = vertx.createHttpServer().requestHandler(router);
    vertx.executeBlocking(promise -> {
      httpServer.listen(9090)
          .onSuccess(server -> {
            System.out.println("Okay !!");
          })
          .onFailure(promise::fail);
    });
  }
}
