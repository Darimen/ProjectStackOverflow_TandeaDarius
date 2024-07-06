import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const TaggedQuestions = () => {
    const { tag } = useParams();

    const [questions, setQuestions] = useState([]);
    
    useEffect(() => {
        fetch("http://localhost:8080/question/get/tags/" + tag, {
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
            console.error('Error:', error);
        });
    }, [tag]);

    function formatDateTime(timestamp){
        let date = timestamp.substring(0,10)
        let time = timestamp.substring(11,16)
        return time +" "+ date
    };

    const navigate = useNavigate();

    return (

        <>
        
            <p>
                Questions tagged with {tag}:
            </p>
            
            {questions.length > 0 ? (
                    questions.map((question, index) => (
                        <div key={index} className="question">
                            <div className="left">
                                {question.votes}
                                {question.answers}
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
        </>

    );
}

export default TaggedQuestions;
