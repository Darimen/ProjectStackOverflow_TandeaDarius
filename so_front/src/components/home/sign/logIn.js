import React from "react"
import { useAuth } from '../../auth_context.js';
import "./signup.css"

const LogIn = () => {
    const { login } = useAuth();

    let handleLogin = async (e) => {
        e.preventDefault();
        const email = e.target.email.value;
        const password = e.target.password.value;
        await login(email, password, email);
        window.location.reload();
    }

    return(
        <div className="login">
            <form onSubmit={handleLogin}>
                <div className="form-group">
                    <label>Email or username</label>
                    <input name="email" placeholder="email or username" required />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" name="password" placeholder="password" required />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default LogIn;