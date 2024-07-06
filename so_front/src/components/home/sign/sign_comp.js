import React, { useState } from "react";
import LogIn from "./logIn";
import SignUp from "./signUp";
import "./signup.css"

const SignComp = () => {

    const [index, setIndex] = useState(1);
    const unregistered = [<LogIn />, <SignUp />];

    return (
        <div className="sign_comp">
            <div className="selection_menu">
                    <button onClick={() => setIndex(0)}>
                        LogIn
                    </button>
                    
                    <button onClick={() => setIndex(1)}>
                        SignUp
                    </button>
            </div>
            <div className="credentials_area">
                {unregistered[index]}
            </div>
        </div>
    );
}

export default SignComp;