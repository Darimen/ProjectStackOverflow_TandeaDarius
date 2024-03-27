package app.stackOverflow.model;


/*CREATE TABLE tags (
        tag_id BIGINT PRIMARY KEY,
        name VARCHAR NOT NULL
);*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "tag_id")
    private BigInteger tagId;

    @Column(name = "name")
    private String name;
}
