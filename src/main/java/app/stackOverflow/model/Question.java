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
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")

/*CREATE TABLE questions (
        q_id BIGINT PRIMARY KEY,
        author_id BIGINT NOT NULL,
        title VARCHAR NOT NULL,
        text VARCHAR NOT NULL,
        date TIMESTAMP NOT NULL,
        picture VARCHAR,
        last_update TIMESTAMP NOT NULL,
        visible BOOLEAN DEFAULT TRUE NOT NULL,
        FOREIGN KEY (author_id) REFERENCES "user" (u_id)
        );*/

public class Question {

    public static short upvote = 1;
    public static float upvoteScore = 2.5f;

    public static short downvote = -1;
    public static float downvoteScore = -1.5f;

    @Id
    @Column(name = "q_id")
    private BigInteger qId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_id")
    private BigInteger authorId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "picture")
    private String picture;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Column(name = "visible")
    private boolean visible;

}
