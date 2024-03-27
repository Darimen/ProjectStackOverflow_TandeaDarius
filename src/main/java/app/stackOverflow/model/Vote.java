package app.stackOverflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

/*
CREATE TABLE votes (
        v_id BIGINT PRIMARY KEY,
        u_id BIGINT NOT NULL,
        q_id BIGINT,
        a_id BIGINT,
        val SMALLINT NOT NULL,
        FOREIGN KEY (u_id) REFERENCES "user" (u_id),
FOREIGN KEY (q_id) REFERENCES questions (q_id),
FOREIGN KEY (a_id) REFERENCES answer (a_id)
        );*/
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "votes")
public class Vote {
    @Id
    @Column(name = "v_id")
    private BigInteger vId;

    @Column(name = "u_id")
    private BigInteger uId;

    @Column(name="q_id")
    private BigInteger qId;

    @Column(name ="a_id")
    private BigInteger aId;

    @Column(name = "val")
    private short val;
}
