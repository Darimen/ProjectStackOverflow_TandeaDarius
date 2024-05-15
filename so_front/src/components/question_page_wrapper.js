import React, { useState } from "react";
import { useParams } from "react-router-dom";
import Question from "./question_page";
const QuestionWrapper = () => {
    const { question_id } = useParams();
    const [question, setQuestion] = useState({});
    const [answers, setAnswers] = useState([]);

    const dummy_question = {
            "authorName": "username",
            "authorId": 2,
            "title": "Title",
            "text": "Text",
            "date": "2024-03-27T12:50:10.261+00:00",
            "picture": null,
            "lastUpdate": "2024-03-27T12:50:10.261+00:00",
            "visible": true,
            "qid": 1
        }  
        
    const dummy_answers = [
        {
            "authorId": 2,
            "created_at": "2024-03-27T12:50:10.296+00:00",
            "text": "Text",
            "picture": null,
            "last_update": "2024-03-27T12:50:10.296+00:00",
            "visible": true,
            "qid": 1,
            "aid": 1
        },
        {
            "authorId": 2,
            "created_at": "2024-03-27T12:50:10.317+00:00",
            "text": "Text2",
            "picture": null,
            "last_update": "2024-03-27T12:50:10.317+00:00",
            "visible": true,
            "qid": 1,
            "aid": 2
        }
    ]

    React.useEffect(() => {
        fetch("http://localhost:8080/question/get/" + question_id, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then(response => response.json())
        .then(data => {
            setQuestion(data);
            return fetch("http://localhost:8080/answer/all/" + question_id, {
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
