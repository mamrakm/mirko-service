package cz.ememsoft.demo.mapper;

import cz.ememsoft.demo.api.CreateSubjectRequest;
import cz.ememsoft.demo.api.SubjectResponse;
import cz.ememsoft.demo.domain.Subject;
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
