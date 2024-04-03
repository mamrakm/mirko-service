package com.example.demo.mapper;

import com.example.demo.api.CreateSubjectRequest;
import com.example.demo.api.SubjectResponse;
import com.example.demo.domain.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SubjectMapper {

  /**
   * Map CreateSubjectRequest to Subject fields correctly without changing API
   *
   * @return Subject mapped from request
   */
  @Mapping(source = "name", target = "firstName")
  @Mapping(source = "givenName", target = "lastName")
  Subject map(CreateSubjectRequest request);

  SubjectResponse map(Subject subject);
}
