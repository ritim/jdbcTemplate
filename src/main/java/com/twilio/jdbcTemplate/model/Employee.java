package com.twilio.jdbcTemplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

  @JsonProperty("id")
  private int id;

  @NotBlank
  @JsonProperty("firstName")
  private String firstName;

  @NotBlank
  @JsonProperty("lastName")
  private String lastName;

  @NotBlank
  @JsonProperty("email")
  private String email;

  public Employee(int id, String firstName, String lastName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
