package app.stackOverflow.DTO;

import app.stackOverflow.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Question question;
    private List<String> tags;
}