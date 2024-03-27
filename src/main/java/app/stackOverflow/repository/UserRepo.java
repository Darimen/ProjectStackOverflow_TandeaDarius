package app.stackOverflow.repository;

import app.stackOverflow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;

@Repository
public interface UserRepo extends CrudRepository<User, BigInteger> {
    public User findByEmail(String email);
    public User findByUsername(String username);
    public User findByEmailAndPassword(String email, String password);
    public User findByUsernameAndPassword(String username, String password);
    public default BigInteger findMaxUserId(){
        ArrayList<User> users = (ArrayList<User>) findAll();
        return BigInteger.valueOf(users.size());
    }

    public Boolean existsByEmailOrUsername(String email, String username);

    public User findByuId(BigInteger userId);
    public ArrayList<User> findByUsernameContainingOrLastnameContainingOrFirstnameContaining(String username, String lastname, String firstname);

}
