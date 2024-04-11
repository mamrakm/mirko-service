package cz.ememsoft.mirko.service.api;

import lombok.Data;

@Data
public class CreateSubjectRequest {
  private String name;
  private String givenName;
}
