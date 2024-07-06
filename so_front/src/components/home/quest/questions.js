import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import "./question.css";

const Questions = () => {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/question/all", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            setQuestions(data);
        })
        .catch(error => {
            console.log('Error fetching questions:', error);
        });
    }, []);

    const navigate = useNavigate();

    function formatDateTime(timestamp){
        let date = timestamp.substring(0,10)
        let time = timestamp.substring(11,16)
        return time +" "+ date
    };

    return (
            <div className="questions">
                <div className="top_title">
                    <h1>All Questions</h1>
                    <button onClick={()=>{navigate("/ask")}}>Ask Question</button>
                </div>
                
                {questions.length > 0 ? (
                    questions.map((question, index) => (
                        <div key={index} className="question">
                            <div className="left">
                                {question.votes}
                                {question.answers}
                                {(question.authorName === JSON.parse(localStorage.getItem("user")).username)?
                                    <button onClick={()=>{navigate("/edit/"+question.qid)}}>Edit</button>
                                : null}
                            
                            </div>
                            <div className="right">
                                <div className="title" onClick={()=>{navigate("/question/"+question.qid)}}>
                                    Title:
                                    {question.title}
                                </div>
                                <div className="text">
                                    Text:
                                    {question.text}
                                </div>
                                <div className="lower">
                                    <div className="tags">
                                        Tags:
                                        {question.tags}
                                    </div>
                                    <div className="user">
                                        {question.authorName}
                                        . posted at .
                                        {formatDateTime(question.date)}
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    ))
                ) : (
                    <p>Loading Questions...</p>
                )}
            </div>
    );
};

export default Questions;
