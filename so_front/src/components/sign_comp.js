import React, { useState } from "react";
import LogIn from "./logIn";
import SignUp from "./signUp";

const SignComp = () => {

    const [index, setIndex] = useState(0); 
    const unregistered = [<LogIn />, <SignUp />]; 

    return (
        <>
            <div className="login_menu">
                <button onClick={() => setIndex(0)}>
                    LogIn
                </button>
            </div>

            <div className="signup_menu">
                <button onClick={() => setIndex(1)}>
                    SignUp
                </button>
            </div>

            <div className="credentials_area">
                {unregistered[index]}
            </div>
        </>
    );
}

export default SignComp;