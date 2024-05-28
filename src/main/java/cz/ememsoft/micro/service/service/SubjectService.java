package cz.ememsoft.micro.service.service;

import cz.ememsoft.micro.service.api.CreateSubjectRequest;
import cz.ememsoft.micro.service.api.SubjectResponse;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

  Long save(CreateSubjectRequest request);

  Optional<SubjectResponse> findById(Long id);

  List<SubjectResponse> subjectsWithLowBalance();
}
