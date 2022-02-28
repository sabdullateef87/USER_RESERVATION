package com.airline.reservation.user.httpValidations;

import com.airline.reservation.user.models.HttpError;
import com.airline.reservation.user.util.DataTransformUtil;
import com.microsoft.sqlserver.jdbc.StringUtils;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class userHttpValidation {

  public void createUserEndpointValidation(RoutingContext routingContext) {
    HttpError error = new HttpError();
    JsonObject body = routingContext.getBodyAsJson();
    try {
      String userName = Optional.ofNullable(body.getString("userName")).orElse("");
      String firstName = body.getString("firstName");
      String lastName = body.getString("lastName");
      String email = body.getString("email");
      String address = body.getString("address");
      String phoneNumber = body.getString("phoneNumber");
      if (StringUtils.isEmpty(firstName)) {
        if (StringUtils.isEmpty(firstName)) {
          error.setErrorMessage("firstname is required");
        } else if (StringUtils.isEmpty(lastName)) {
          error.setErrorMessage("lastname is required");
        } else if (StringUtils.isEmpty(email)) {
          error.setErrorMessage("email is required");
        } else if (StringUtils.isEmpty(address)) {
          error.setErrorMessage("address is required");
        } else if (StringUtils.isEmpty(phoneNumber)) {
          error.setErrorMessage("phoneNumber is required");
        } else if (StringUtils.isEmpty(userName)) {
          error.setErrorMessage("username is required");
        }
        String errorString = DataTransformUtil.JsonString(error);
        HttpServerResponse res = routingContext.response();
        res.end(errorString);
        return;
      }
    } catch (Exception e) {
      log.info("Error in Validation service !!");
    }
    routingContext.next();
  }
}
