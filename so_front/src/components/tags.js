import React, { useEffect , useState } from "react";

const Tags = () => {

    const [tags, setTags] = useState([]);
    const [filter, setFilter] = useState("");

    useEffect(() => {
        fetch("http://localhost:8080/tag/all", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            setTags(data);
        })
        .catch(error => {
            console.log('Error fetching tags:', error);
        });
    }, []);

    


    return(
        <>
            <h1>
                Tags
            </h1>

            <p>
                A tag is a keyword or label that categorizes your question with other, similar questions. Using <br/>the right tags makes it easier for others to find and answer your question.
            </p>

            <input type="index" name="filter_tag" placeholder="Filter by tag name"
            onChange={
                (e) => {
                    setFilter(e.target.value);
                }
            }>
            </input>

            <div className="tags">

                {tags.length > 0 ? (
                    tags.filter((tag) => {
                        if(filter === ""){
                            return tag;
                        }else if(tag.name.toLowerCase().includes(filter.toLowerCase())){
                            return tag;
                        }
                        return null;
                    }).map((tag, index) => (
                        <div key={index} className="tag">
                            <a href={`/${tag.name}/questions`}>
                                <div className="name">
                                    {tag.name}
                                </div>
                                <div className="description">
                                    {tag.description}
                                </div>
                            </a>
                        </div>
                    ))
                ) : (
                    <div>
                        No tags found
                    </div>
                )}    
            
            </div>

        </>
    )

}

export default Tags;
