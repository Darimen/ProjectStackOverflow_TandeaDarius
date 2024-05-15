import React, { createContext, useContext, useEffect, useState } from 'react';

const AuthContext = createContext(null);

export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {

  const [user, setUser] = useState(null);

  const login = async (email, password, username) => {
    await fetch("http://localhost:8080/user/login", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password, username }),
    })
      .then(response => response.json())
      .then(data => {
        if(data.user){
        console.log(data);
        setUser(data);
        localStorage.setItem('user', JSON.stringify(data));  
        }
      })
  };



  const signup = (email, password, username, first_name, last_name, phone, country) => {
    fetch("http://localhost:8080/user/signup/", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "uId": 0, // number, not string
        "username": username,
        "password": password,
        "score": 0, // number, not string
        "banned": false, // boolean, not string
        "email": email,
        "phoneNr": phone,
        "phonePf": country,
        "firstname": first_name,
        "lastname": last_name,
        "mod": false // boolean, not string
      })
    })
      .then(response => response.json())
      .then(data => {
        setUser(data);
        localStorage.setItem('user', JSON.stringify(data));  // Store user in local storage
        console.log(data);
      })
      .catch(error => console.error('Error:', error));
  }

  const logout = () => {
    setUser(null);
    localStorage.removeItem('user');  // Clear user from local storage
  };

  useEffect(() => {
    console.log(user);
  }, [user]);

  return (
    <AuthContext.Provider value={{ user, login, logout, signup }}>
      {children}
    </AuthContext.Provider>
  );
}
