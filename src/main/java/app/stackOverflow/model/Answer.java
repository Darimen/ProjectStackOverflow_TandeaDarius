package app.stackOverflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer {
    @Id
    @Column(name = "a_id")
    private BigInteger aId;

    @Column(name = "author_id")
    private BigInteger authorId;

    @Column(name = "q_id")
    private BigInteger qID;

    @Column(name = "creation_date")
    private Timestamp created_at;

    @Column(name = "text")
    private String text;

    @Column(name = "picture")
    private String picture;

    @Column(name = "last_update")
    private Timestamp last_update;

    @Column(name = "visible")
    private boolean visible;

}
