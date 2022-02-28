package com.airline.reservation.user.controller;

import com.airline.reservation.user.dao.util.UserServiceUtil;
import com.airline.reservation.user.models.UserModel;
import com.airline.reservation.user.services.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponse;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserController {

  @Autowired
  UserServices userServices;

  @Autowired
  UserServiceUtil uutil;


  public void createUserController(RoutingContext routingContext) {
    Promise<Void> prom = Promise.promise();
    JsonObject keyObject = routingContext.getBodyAsJson();
    String userName = Optional.ofNullable(keyObject.getString("userName")).orElse("");
    String firstName = keyObject.getString("firstName");
    String lastName = keyObject.getString("lastName");
    String email = keyObject.getString("email");
    String phoneNumber = keyObject.getString("phoneNumber");
    String address = keyObject.getString("address");

    UserModel newUser = new UserModel(userName, firstName, lastName, email, phoneNumber, address);
    HttpServerResponse httpRes = routingContext.response();
    httpRes.putHeader("content-type", "application/json");
    userServices.createUser(routingContext, newUser)
        .onSuccess(r -> {
          uutil.getJsonFromObject(r)
              .onSuccess(resp -> {
                httpRes.end(resp);
                log.info(resp);
              });
        }).onFailure(e -> {
          httpRes.setStatusCode(404).end(e.toString());
        });
    prom.future();
  }

  public void getUserByIdController(RoutingContext routingContext) {
    int _id = Integer.parseInt(routingContext.pathParam("user_id"));
    HttpServerResponse httpRes = routingContext.response();

    httpRes.putHeader("content-type", "application/json");
    userServices.getUserById(routingContext, _id)
        .onSuccess(r -> {
          uutil.getJsonFromObject(r)
              .onSuccess(httpRes::end);
        }).onFailure(e -> {
          httpRes.setStatusCode(404).end(e.toString());
        });
  }


}

