package cz.ememsoft.demo.service;

import cz.ememsoft.demo.api.CreateSubjectRequest;
import cz.ememsoft.demo.api.SubjectResponse;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

  Long save(CreateSubjectRequest request);

  Optional<SubjectResponse> findById(Long id);

  List<SubjectResponse> subjectsWithLowBalance();
}
