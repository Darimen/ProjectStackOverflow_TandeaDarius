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

//CREATE TABLE "user" (
//u_id BIGINT PRIMARY KEY,
//username VARCHAR NOT NULL,
//password VARCHAR NOT NULL,
//score INTEGER NOT NULL,
//banned BOOLEAN DEFAULT FALSE NOT NULL,
//email VARCHAR NOT NULL,
//phone_nr VARCHAR,
//phone_pf VARCHAR,
//surname VARCHAR,
//lastname VARCHAR,
//is_mod BOOLEAN DEFAULT FALSE NOT NULL,
//created_at TIMESTAMP NOT NULL,
//last_update TIMESTAMP NOT NULL
//);

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "\"user\"")
public class User {

    public static float dislikeScore = -1.5f;

    @Id
    @Column(name = "u_id")
    private BigInteger uId;
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private int score;

    @Column(name = "banned")
    private boolean banned;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_nr")
    private String phoneNr;

    @Column(name = "phone_pf")
    private String phonePf;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "is_mod")
    private boolean isMod;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Column(name = "reason")
    private String reason;

}
