package app.stackOverflow.repository;

import app.stackOverflow.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;

@Repository
public interface QuestionRepo extends CrudRepository<Question, BigInteger> {
    public ArrayList<Question> findByTitleContaining(String title);
    public ArrayList<Question> findByTextContaining(String body);
    public ArrayList<Question> findByAuthorId(BigInteger authorId);

}
