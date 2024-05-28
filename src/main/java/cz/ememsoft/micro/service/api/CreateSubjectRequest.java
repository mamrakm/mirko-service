package cz.ememsoft.micro.service.api;

import lombok.Data;

@Data
public class CreateSubjectRequest {
  private String name;
  private String givenName;
}
