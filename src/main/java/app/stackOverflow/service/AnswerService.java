package app.stackOverflow.service;

import app.stackOverflow.model.Answer;
import app.stackOverflow.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class AnswerService {

    @Autowired
    AnswerRepo answerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VoteRepo voteRepo;

    public Answer updateAnswer(Answer answer) {
        if(!answerRepo.existsById(answer.getAId())){
            throw new RuntimeException("Answer does not exist");
        }



        return answerRepo.save(answer);
    }

    @Transactional
    public Answer addAnswer(Answer answer) {
        if(answer.getAuthorId() == null){
            throw new RuntimeException("Author ID is required");
        }

        if(answer.getQID() == null){
            throw new RuntimeException("Question ID is required");
        }

        if(answer.getText() == null){
            throw new RuntimeException("Text is required");
        }

        answer.setCreated_at(new Timestamp(new Date().getTime()));
        answer.setLast_update(answer.getCreated_at());
        answer.setAId(BigInteger.valueOf(answerRepo.count()).add(BigInteger.ONE));
        return answerRepo.save(answer);
    }

    public Iterable<Answer> getAllAnswers() {
        return answerRepo.findAll();
    }

    public Answer getAnswerById(BigInteger id) {
        return answerRepo.findById(id).get();
    }

    public Answer getAnswerByAuthorUsername(String username) {
        return answerRepo.findByAuthorId(userRepo.findByUsername(username).getUId());
    }

    public Iterable<Answer> getQuestionAnswers(BigInteger qId) {
        return answerRepo.findByqID(qId);
    }

    public boolean deleteAnswer(BigInteger id) {
        answerRepo.deleteById(id);
        return true;
    }
}
