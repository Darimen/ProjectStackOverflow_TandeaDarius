import React, { useRef } from "react";


const QuestionManager = ({question = {  "user":"none",
                                        "score":NaN,
                                        "date":"01.Jun.1970 00:00:00",
                                        "text": "This shouldn't be here"}}) => {

    useRef(() => {
    if(question){
        return (
            <div className="question">
                <div className="User"> {question.user}</div>
                <div className="score">{question.votes}</div>
                <div className="question_date">{question.date}</div>
                <div className="question_text">{question.text}</div>

                {question.image !== "" && 
                question.image !== null &&
                question.image !== undefined ?
                <div className="image">
                    <img src={question.image} alt="Question"/>
                </div> : null}
                
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