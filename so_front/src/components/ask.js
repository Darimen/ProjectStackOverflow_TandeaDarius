import React, {useState} from "react";
import "./styles/ask.css";
const Ask = () => {

    const [title, setTitle] = useState("");
    const [text, setText] = useState("");
    const [image, setImage] = useState("");
    const [tags, setTags] = useState("");

    let user = JSON.parse(localStorage.getItem("user"));

    const handleSubmit = (e) => {
        e.preventDefault();
        let tagArray = tags.split(",");
        console.log(title, text, image, tagArray);
        fetch("http://localhost:8080/question/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                question: {
                    authorName: user.username,
                    authorId: user.uid,
                    title: title,
                    text: text,
                    picture: image,
                    visible: true
                },
                tags: tagArray
            })
        })
        .then((response) => {
            if (response.ok) {
                console.log("Question added successfully");
                window.location.reload();
            } else {
                console.log("Question not added");
            }
        })
        .catch((error) => {
            console.log("Error: ", error);
        });
    }

    return (
        <div className="ask">
            <h1>Ask a question</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Title:
                    <input type="text" name="title" onChange={
                        (e) => {
                            setTitle(e.target.value);
                        }
                    } />
                </label>
                <label>
                    Text:
                    <textarea name="text" onChange={
                        (e) => {
                            setText(e.target.value);
                        }
                    } />
                </label>
                <label>
                    Image Link:
                    <input type="text" name="image" onChange={
                        (e) => {
                            setImage(e.target.value);
                        }
                    
                    } />
                </label>

                <label>
                    Tags:
                    <input type="text" name="tags" onChange={
                        (e) => {
                            setTags(e.target.value);
                        }
                    }/>
                </label>
                
                <button type="submit">Submit</button>
            </form>
        </div>
    )
}

export default Ask;