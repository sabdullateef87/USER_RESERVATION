package com.airline.reservation.user.deployer;


import com.airline.reservation.user.verticles.UserVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class HttpVerticleDeployer {

  @Autowired
  private UserVerticle userVerticle;

  @PostConstruct
  public void deploy(){
    System.out.println(" -------------Hello from the user verticle ???------------------ ");
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(userVerticle);
  }


}
