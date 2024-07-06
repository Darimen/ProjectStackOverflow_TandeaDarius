package app.stackOverflow.controller;

import app.stackOverflow.DTO.QuestionDTO;
import app.stackOverflow.model.Question;
import app.stackOverflow.model.Tag;
import app.stackOverflow.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<?> getAllQuestions(@RequestBody QuestionDTO questionDTO) {
        Question question = questionDTO.getQuestion();
        ArrayList<String> tags = (ArrayList<String>) questionDTO.getTags();

        for(Field field : question.getClass().getDeclaredFields()){
            field.setAccessible(true);

            try {
                System.out.println(field.getName() + " - " + field.get(question));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        tags.forEach(System.out::println);

        if(question.getTitle() == null || question.getText() == null){
            return ResponseEntity.badRequest().body("Title and body are required");
        }

        return ResponseEntity.ok(questionService.addQuestion(question, tags));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable String id) {
        Map<Question, ArrayList<Tag>> questionAndTags = questionService.getQuestionById(BigInteger.valueOf(Long.parseLong(id)));
        Map<String, Object> response = new HashMap<>();

        response.put("question", questionAndTags.keySet().toArray()[0]);
        response.put("tags", questionAndTags.values().toArray()[0]);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable String id) {
        Question question = questionDTO.getQuestion();
        ArrayList<String> tags = (ArrayList<String>) questionDTO.getTags();

        question.setQId(BigInteger.valueOf(Long.parseLong(id)));

        if(question.getTitle() == null || question.getText() == null){
            return ResponseEntity.badRequest().body("Title and body are required");
        }

        return ResponseEntity.ok(questionService.updateQuestion(question, tags));
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteQuestion(@PathVariable String id) {
        return questionService.deleteQuestion(BigInteger.valueOf(Long.parseLong(id)));
    }

    @GetMapping("/get/tags/{tagString}")
    public Iterable<Question> getQuestionByTags(@PathVariable String tagString){
        return questionService.getQuestionByTags(tagString);
    }

    @GetMapping("/get/user/{username}")
    public Iterable<Question> getQuestionByUser(@PathVariable String username){
        return questionService.getQuestionByUser(username);
    }

    @GetMapping("/get/text/{text}")
    public Iterable<Question> getQuestionByText(@PathVariable String text){
        return questionService.getQuestionByText(text);
    }

    @GetMapping("/get/title/{text}")
    public Iterable<Question> getQuestionByTitle(@PathVariable String text){
        return questionService.getQuestionsByTitle(text);
    }


}
