import React, { useRef } from "react";


const QuestionManager = (question) => {

    useRef(() => {
    if(question){
        return (
            <div className="question">
                <div className="User"> {question.user}</div>
                <div className="score">{question.score}</div>
                <div className="question_date">{question.date}</div>
                <div className="question_text">{question.text}</div>
            </div>
        )
    }else{
    return (
        <>
            Loading Question...
        </>
    )
    }
},[question]);

}
export default QuestionManager;