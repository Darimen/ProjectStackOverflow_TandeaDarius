package app.stackOverflow.DTO;

import app.stackOverflow.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    Answer answer;
    String questionId;
}
