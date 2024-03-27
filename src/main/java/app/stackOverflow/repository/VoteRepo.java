package app.stackOverflow.repository;

import app.stackOverflow.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public interface VoteRepo extends CrudRepository<Vote, BigInteger> {
//    public Iterable<Votes> findVotesByAId(BigInteger aId);
//    public int countVotesByAId(BigInteger aId);
//    public Iterable<Votes> findVotesByQId(BigInteger qId);
//    public int countVotesByQId(BigInteger qId);

//    public Votes findByUId(BigInteger uId);
//    public Votes findByAId(BigInteger aId);
//    public Votes findByQId(BigInteger qId);
//
//    public Votes findByUIdAndAId(BigInteger uId, BigInteger aId);
//    public Votes findByUIdAndQId(BigInteger uId, BigInteger qId);

    public ArrayList<Vote> findByaId(BigInteger aId);
    public ArrayList<Vote> findByqId(BigInteger qId);
    public ArrayList<Vote> findByuId(BigInteger uId);
    public default ArrayList<Vote> findByUIdAndAId(BigInteger uId, BigInteger aId){
        ArrayList<Vote> votes = new ArrayList<>();
        for(Vote vote : findByuId(uId)){
            if(vote.getAId().equals(aId)){
                votes.add(vote);
            }
        }
        return votes;
    }

    public default Vote findByUIdAndQId(BigInteger uId, BigInteger qId){
        Vote votes;

        for(Vote vote : findByuId(uId)){
            if(vote.getQId().equals(qId)){
                return vote;
            }
        }

        return null;
    }

    public default BigInteger findMaxVoteId(){
        ArrayList<Vote> votes = (ArrayList<Vote>) findAll();
        return BigInteger.valueOf(votes.size());
    }
}
