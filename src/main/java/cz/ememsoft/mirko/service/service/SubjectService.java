package cz.ememsoft.mirko.service.service;

import cz.ememsoft.mirko.service.api.CreateSubjectRequest;
import cz.ememsoft.mirko.service.api.SubjectResponse;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

  Long save(CreateSubjectRequest request);

  Optional<SubjectResponse> findById(Long id);

  List<SubjectResponse> subjectsWithLowBalance();
}
