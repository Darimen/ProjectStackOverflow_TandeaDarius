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

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions_to_tags")
public class QTT {
    @Column(name = "q_id")
    private BigInteger qId;

    @Column(name = "tag_id")
    private BigInteger tagId;

    @Id
    @Column(name ="qtt_id")
    private BigInteger qttId;
}
