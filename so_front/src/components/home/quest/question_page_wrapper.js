import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Question from "./question_page";
const QuestionWrapper = () => {
    const { question_id } = useParams();

    const [question, setQuestion] = useState({});
    const [answers, setAnswers] = useState([]);


    useEffect(() => {
        fetch("http://localhost:8080/question/get" + question_id, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then(response => response.json())
        .then(data => {
            setQuestion(data);
            return fetch("http://localhost:8080/answer/all" + question_id, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                }
            });
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            setAnswers(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }, [question_id]); 

    

    return (
        <Question question={question} answers={answers} />
    );
}

export default QuestionWrapper;
