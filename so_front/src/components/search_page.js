import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import QuestionManager from "./home/quest/question_manager";

const SearchPage = () => {
    const [searchResult, setSearchResult] = useState([]);

    useEffect(() => {
        const result = localStorage.getItem("search_result");
        try {
            setSearchResult(result ? JSON.parse(result) : null);
        } catch (error) {
            console.error("Failed to parse search results:", error);
            setSearchResult(null);
        }
    }, []);

    if (searchResult && searchResult.length > 0) {
        return (
            <>
                {searchResult.map(result => (
                    <Link key={result.id} to={`/question/${result.id}`}>
                        <QuestionManager question={result} />
                    </Link>
                ))}
            </>
        );
    } else if (searchResult && searchResult.length === 0) {
        return <div>No results found</div>;
    } else {
        return <div>Loading Question...</div>;
    }
};

export default SearchPage;
