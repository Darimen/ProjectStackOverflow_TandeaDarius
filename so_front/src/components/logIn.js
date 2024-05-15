import React from "react"
import { useAuth } from './auth_context.js';

const LogIn = () => {
    const { login } = useAuth();

    let handleLogin = async (e) => {
        e.preventDefault(); 
        const email = e.target.email.value;
        const password = e.target.password.value;
        await login(email, password, email);
    }

    return(
        <div className="login">
            <form onSubmit={handleLogin}>
                <label>Email or username</label>
                <input name="email" placeholder="email or username" required />
                <label>Password</label>
                <input type="password" name="password" placeholder="password" required />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default LogIn;