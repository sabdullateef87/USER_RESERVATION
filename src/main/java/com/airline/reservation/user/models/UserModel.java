package com.airline.reservation.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
public class UserModel {
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private String address;
  private String phoneNumber;

}
