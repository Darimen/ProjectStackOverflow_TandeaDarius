package app.stackOverflow.controller;

import app.stackOverflow.DTO.QuestionDTO;
import app.stackOverflow.model.Question;
import app.stackOverflow.model.Tag;
import app.stackOverflow.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/add/")
    public Question getAllQuestions(@RequestBody QuestionDTO questionDTO) {
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

        return questionService.addQuestion(question, tags);
    }

    @GetMapping("/all/")
    public Iterable<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/get/{id}")
    public Map<Question, ArrayList<Tag>> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(BigInteger.valueOf(Long.parseLong(id)));
    }

    @PostMapping("/update/{id}")
    public Question updateQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable String id) {
        Question question = questionDTO.getQuestion();
        ArrayList<String> tags = (ArrayList<String>) questionDTO.getTags();

        question.setQId(BigInteger.valueOf(Long.parseLong(id)));

        return questionService.updateQuestion(question, tags);
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteQuestion(@PathVariable String id) {
        return questionService.deleteQuestion(BigInteger.valueOf(Long.parseLong(id)));
    }
}
