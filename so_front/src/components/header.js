import React from "react";
import SearchBar from "./searchbar";
import { useAuth } from './auth_context.js';
import "./styles/header.css"

const Corner = () => {

    const {logout} = useAuth();
    
    let user = localStorage.getItem("user");

    if(localStorage.getItem("user") === null){
        console.log("no user");
    }else{
        user= JSON.parse(localStorage.getItem("user"));
    }

    if(user !== null){
        return (
            <div className="user-heading">
                <button>{user.username}</button>
                <button onClick={logout}>
                    <a href="/home">
                        Logout
                    </a>
                </button>
            </div>
        )
    }else{
        return (
            <div className="user-heading">
                <button>LogIn</button>
                <button>SignUp</button>
            </div>
        )
    }
}

const Header = (props) => {

    return (
        <>
            <div className="header">
                <a href="/home">
                <h1>SO wannabe</h1>
                </a>
                <SearchBar/>
                <Corner/>
            </div>

            {props.children}
        </>
    )
}


export default Header;