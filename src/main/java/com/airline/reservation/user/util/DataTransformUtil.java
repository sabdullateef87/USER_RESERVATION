package com.airline.reservation.user.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTransformUtil {
    public static <T> String JsonString(Object err) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(err);
    }
}
