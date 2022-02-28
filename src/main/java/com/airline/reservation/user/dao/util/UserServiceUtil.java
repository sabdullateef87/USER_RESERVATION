package com.airline.reservation.user.dao.util;

import com.airline.reservation.user.models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class UserServiceUtil {
  public Future<String> getJsonFromObject(Object obj) {
    Promise<String> prom = Promise.promise();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String res = objectMapper.writeValueAsString(obj);
      prom.complete(res);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      prom.fail(e.toString());
    }
    return prom.future();
  }

  public <T> Future<T> getObjectFromJson(JsonObject str, Class<T> tClass) {
    Promise<T> prom = Promise.promise();
    try {
      T tObj = new Gson().fromJson(String.valueOf(str), tClass);
      prom.complete(tObj);
    } catch (Exception ex) {
      prom.fail(ex.toString());
    }
    return prom.future();
  }
}
