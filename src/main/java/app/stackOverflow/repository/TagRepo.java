package app.stackOverflow.repository;

import app.stackOverflow.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;

@Repository
public interface TagRepo extends CrudRepository<Tag, BigInteger> {
    public Tag findByName(String tagName);
    public Tag findByTagId(BigInteger tId);

    public default BigInteger findMaxTagId(){
        ArrayList<Tag> tags = (ArrayList<Tag>) findAll();
        return BigInteger.valueOf(tags.size());
    }

}
