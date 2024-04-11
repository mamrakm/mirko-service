package cz.ememsoft.mirko.service.mapper;

import cz.ememsoft.mirko.service.api.CreateSubjectRequest;
import cz.ememsoft.mirko.service.api.SubjectResponse;
import cz.ememsoft.mirko.service.domain.Subject;
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
