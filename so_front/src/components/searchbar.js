import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const SearchBar = () => {

    const [category, setCategory] = useState("tags");
    const navigate = useNavigate();

    const handleSearch = (e) => {
        e.preventDefault();
        fetch("http://localhost:8080/question/get/" + category +"/" + e.target.search.value, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        }
        ).then(response => response.json())
        .then(data => {
            console.log(data);
            localStorage.setItem("search_result", JSON.stringify(data));
            navigate("/search_result");
        }).catch(error => {
            console.log(error)
        }
        )
    }

    return (
        <div>
            <form onSubmit={handleSearch}>
                <input type="text" name="search" placeholder={"Search for "+category} required/>
                <select onChange={(e)=>{
                    setCategory(e.target.value);
                }}>
                    <option value="tags">Tag</option>
                    <option value="title">Title</option>
                    <option value="text">Text</option>
                    <option value="user">User</option>
                </select>
                <button type="submit" >Search</button>
            </form>
        </div>
    )
}

export default SearchBar;