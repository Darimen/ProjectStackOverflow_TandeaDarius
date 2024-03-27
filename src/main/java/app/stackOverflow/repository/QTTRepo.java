package app.stackOverflow.repository;

import app.stackOverflow.model.QTT;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface QTTRepo extends CrudRepository<QTT, BigInteger> {
    public Iterable<QTT> findByTagId(BigInteger tagId);

    public Iterable<QTT> findByqId(BigInteger questionId);

    void deleteByqId(BigInteger id);

}
