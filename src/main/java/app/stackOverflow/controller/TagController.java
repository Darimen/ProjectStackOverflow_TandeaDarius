package app.stackOverflow.controller;

import app.stackOverflow.repository.TagRepo;
import app.stackOverflow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagRepo tagRepo;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok(tagRepo.findAll());
    }

}
