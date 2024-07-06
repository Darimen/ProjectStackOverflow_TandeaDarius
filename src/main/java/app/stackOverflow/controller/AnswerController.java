package app.stackOverflow.controller;

import app.stackOverflow.DTO.AnswerDTO;
import app.stackOverflow.model.Answer;
import app.stackOverflow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/add")
    public Answer addAnswer(@RequestBody AnswerDTO answerDTO) {

        Answer answer;

        answer = answerDTO.getAnswer();
        answer.setQID(BigInteger.valueOf(Long.parseLong(answerDTO.getQuestionId())));

        return answerService.addAnswer(answer);
    }

    @GetMapping("/get/{id}")
    public Answer getAnswerById(@PathVariable String id) {
        return answerService.getAnswerById(BigInteger.valueOf(Long.parseLong(id)));
    }

    @PostMapping("/update/{id}")
    public Answer updateAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable String id) {
        Answer oldAnswer = answerService.getAnswerById(BigInteger.valueOf(Long.parseLong(id)));
        Answer answer = answerDTO.getAnswer();

        answer.setAId(oldAnswer.getAId());
        answer.setQID(oldAnswer.getQID());
        answer.setCreated_at(oldAnswer.getCreated_at());
        answer.setLast_update(new Timestamp(new Date().getTime()));
        return answerService.updateAnswer(answer);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteAnswer(@PathVariable String id) {
        return answerService.deleteAnswer(BigInteger.valueOf(Long.parseLong(id)));
    }

    @GetMapping("/all/{qId}")
    public Iterable<Answer> getQuestionAnswers( @PathVariable String qId) {
        return answerService.getQuestionAnswers(BigInteger.valueOf(Long.parseLong(qId)));
    }
}
