package com.airline.reservation.user.models;

import lombok.Data;

@Data
public class HttpError {
  private String errorMessage;
  private String statusCode;
}
