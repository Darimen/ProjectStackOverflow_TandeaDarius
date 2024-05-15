import React, { useRef } from "react";


const AnswerManager = (answers) => {

    useRef(() => {
    if(answers && answers.length > 0){
        return (
            <div className="answers">
                {answers.map((answer) => {
                    return (
                        <div className="answer">
                            <div className="User"> {answer.user}</div>
                            <div className="score">{answer.score}</div>
                            <div className="answer_date">{answer.date}</div>
                            <div className="answer_text">{answer.text}</div>
                        </div>
                    )
                })}
            </div>
        )
    } else if (answers.length <= 0){
        return (
            <div className="answers">
                <div className="answer">
                    <div className="answer_text">No answers yet. Be the first to answer</div>
                </div>
            </div>
        )
    }else{
    return (
        <>
            Loading Answers...
        </>
    )
    }
},[answers]);
    
}
export default AnswerManager;