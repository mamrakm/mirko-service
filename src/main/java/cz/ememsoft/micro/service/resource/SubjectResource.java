package cz.ememsoft.micro.service.resource;

import cz.ememsoft.micro.service.api.CreateSubjectRequest;
import cz.ememsoft.micro.service.api.SubjectResponse;
import cz.ememsoft.micro.service.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class SubjectResource {

    private final SubjectService subjectService;

    @PostMapping("/subjects")
    public ResponseEntity<Void> create(@RequestBody CreateSubjectRequest request) {
        return ResponseEntity.created(URI.create("http://localhost:8080/subjects/" + subjectService.save(request))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> findById(@PathVariable Long id) {
        var result = subjectService.findById(id);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
