import React from "react";
import Questions from "./questions";
import SignComp from "./sign_comp";

const Home = () => {

    const user = localStorage.getItem("user");

    if (user) {
        return (
            <>
                <Questions />
            </>
        )
    } else {
        return (
            <>
                <SignComp />
            </>
        )
    }
}

export default Home;
