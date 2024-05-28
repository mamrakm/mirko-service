package cz.ememsoft.micro.service.mapper;

import cz.ememsoft.micro.service.domain.Subject;
import cz.ememsoft.micro.service.api.CreateSubjectRequest;
import cz.ememsoft.micro.service.api.SubjectResponse;
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
