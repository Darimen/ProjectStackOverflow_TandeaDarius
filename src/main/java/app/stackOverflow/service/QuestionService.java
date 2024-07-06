package app.stackOverflow.service;

import app.stackOverflow.model.*;
import app.stackOverflow.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QTTRepo qttRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private VoteRepo voteRepo;

    public Iterable<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Map<Question, ArrayList<Tag>> getQuestionById(BigInteger id) {

        Question question = questionRepo.findById(id).get();
        ArrayList<Tag> tags = new ArrayList<>();

        for(QTT questionToTag : qttRepo.findByqId(id)){
            tags.add(tagRepo.findById(questionToTag.getTagId()).get());
        }

        Map<Question, ArrayList<Tag>> questionTagMap = new HashMap<>();
        questionTagMap.put(question, tags);

        return questionTagMap;
    }

    @Transactional
    public Question addQuestion(Question question, ArrayList<String> tags) {

        question.setQId(BigInteger.valueOf(questionRepo.count()).add(BigInteger.ONE));
        question.setDate(new Timestamp(new Date().getTime()));
        question.setLastUpdate(question.getDate());
        questionRepo.save(question);


        for(String i : tags){
            if(tagRepo.findByName(i) == null){
                tagRepo.save(new Tag(tagRepo.findMaxTagId().add(BigInteger.ONE), i));
            }

            QTT qtt = new QTT();
            qtt.setQttId(BigInteger.valueOf(qttRepo.count()).add(BigInteger.ONE));
            qtt.setQId(question.getQId());
            qtt.setTagId(tagRepo.findByName(i).getTagId());

            qttRepo.save(qtt);
        }

        return question;
    }

    @Transactional
    public boolean deleteQuestion(BigInteger id) {
        qttRepo.deleteByqId(id);
        questionRepo.deleteById(id);
        return true;
    }

    @Transactional
    public Question updateQuestion(Question question, ArrayList<String> tags) {

        if (!questionRepo.existsById(question.getQId())) {
            throw new RuntimeException("Question does not exist");
        }

        Question oldQuestion = questionRepo.findById(question.getQId()).get();

        question.setDate(oldQuestion.getDate());
        question.setAuthorId(oldQuestion.getAuthorId());
        question.setLastUpdate(new Timestamp(new Date().getTime()));

        questionRepo.save(question);

        qttRepo.deleteByqId(question.getQId());

        for(String i : tags){
            if(tagRepo.findByName(i) == null){
                tagRepo.save(new Tag(tagRepo.findMaxTagId().add(BigInteger.ONE), i));
            }

            QTT qtt = new QTT();
            qtt.setQttId(BigInteger.valueOf(qttRepo.count()).add(BigInteger.ONE));
            qtt.setQId(question.getQId());
            qtt.setTagId(tagRepo.findByName(i).getTagId());

            qttRepo.save(qtt);
        }

        return question;
    }

    //GOT BORED
    //!!TODO!!: Check the following methods

    public Iterable<Question> getQuestionsByTitle(String title) {
        return questionRepo.findByTitleContaining(title);
    }

    public Iterable<Question> getQuestionsByText(String body) {
        return questionRepo.findByTextContaining(body);
    }

    public Iterable<Question> getQuestionsByAuthorName(String authorName) {
        if(authorName.trim().equalsIgnoreCase("")) {
            return questionRepo.findAll();
        }

        ArrayList<User> users = userRepo.findByUsernameContainingOrLastnameContainingOrFirstnameContaining(authorName, authorName, authorName);

        ArrayList<Question> questions = new ArrayList<>();

        for(User i : users){
            questions.addAll(questionRepo.findByAuthorId(i.getUId()));
        }

        return questions;
    }

    public Iterable<Question> getQuestionsByTagId(BigInteger tagId) {
        ArrayList<Question> questions = new ArrayList<>();
        for(QTT i :qttRepo.findByTagId(tagId)){
            questions.add(questionRepo.findById(i.getQId()).get());
        }
        return questions;
    }

    public boolean likeQuestion(BigInteger qId, BigInteger uId) {
        User user = userRepo.findByuId(uId);
        Vote vote = voteRepo.findByUIdAndQId(uId, qId);
        if(vote!= null){
            return false;
        }

        voteRepo.save(new Vote(voteRepo.findMaxVoteId().add(BigInteger.ONE), uId, qId, (BigInteger) null, (short) 1));
        return true;
    }

    public Iterable<Question> getQuestionsByTagName(String tagName) {
        ArrayList<Question> questions = new ArrayList<>();
        BigInteger tagId = tagRepo.findByName(tagName).getTagId();
        for(QTT i :qttRepo.findByTagId(tagId)){
            questions.add(questionRepo.findById(i.getQId()).get());
        }
        return questions;
    }


    public Iterable<Question> getQuestionByTags(String tagString) {
        String[] tags = tagString.split(",");
        ArrayList<Question> questions = new ArrayList<>();
        for(String i : tags){
            questions.addAll((ArrayList<Question>) getQuestionsByTagName(i));
        }
        return questions;
    }

    public Iterable<Question> getQuestionByUser(String username) {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User does not exist");
        }
        return questionRepo.findByAuthorId(user.getUId());
    }

    public Iterable<Question> getQuestionByText(String text) {
        return questionRepo.findByTextContaining(text);
    }
}
