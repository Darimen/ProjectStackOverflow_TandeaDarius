import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Questions = () => {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/question/all/", {
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

    return (
        <div className="questions">
            <h1>Questions</h1>
            {questions.length > 0 ? (
                questions.map((question, index) => (
                    <div key={index} className="question">
                        <div className="User">User: {question.authorName}</div>
                        <div className="question_date">Date: {question.date}</div>
                        <div className="question_title" onClick={()=>{navigate("/question_page/"+question.qid)}}>Title: {question.title}</div>
                        <div className="question_tags">Tags: {question.tags}</div>
                        <div className="question_text">Text: {question.text}</div>
                    </div>
                ))
            ) : (
                <p>Loading Questions...</p>
            )}
        </div>
    );
};

export default Questions;
