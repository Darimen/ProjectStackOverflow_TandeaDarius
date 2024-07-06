import React from "react";
import Questions from "./home/quest/questions";
import SignComp from "./home/sign/sign_comp";

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
