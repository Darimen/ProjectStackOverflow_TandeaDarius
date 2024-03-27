package app.stackOverflow.repository;

import app.stackOverflow.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, BigInteger> {
    public Answer findByAuthorId(BigInteger authorId);

    Iterable<Answer> findByqID(BigInteger qId);
}
