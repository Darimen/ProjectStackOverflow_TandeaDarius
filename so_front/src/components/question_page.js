import React from "react";
import QuestionManager from "./question_manager.js";
import AnswerManager from "./answer_manager.js";

const Question = (question, answers) => {

    return (
        <>
            <QuestionManager question = {question}/>
            <AnswerManager answers = {answers}/>
        </>
    )
    
}
export default Question;