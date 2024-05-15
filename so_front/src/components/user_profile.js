import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const UserProfile = () => {
    const [profile, setProfile] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const { username } = useParams();

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await fetch(`http://localhost:8080/user/get/${username}`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch');
                }
                const data = await response.json();
                setProfile(data);
                setIsLoading(false);
            } catch (error) {
                console.error('Fetch error:', error);
            }
        };

        fetchProfile();
    }, [username]);

    return (
        <div className="profile">
            <h1>Profile</h1>
            {isLoading ? (
                <p>Loading Profile...</p>
            ) : profile ? (
                <div key={profile.id} className="user">
                    <div className="user_username">Username: {profile.username}</div>
                    <div className="user_email">Email: {profile.email}</div>
                    <div className="user_firstname">First Name: {profile.firstname}</div>
                    <div className="user_lastname">Last Name: {profile.lastname}</div>
                    <div className="user_phoneNr">Phone Number: {profile.phoneNr}</div>
                    <div className="user_phonePf">Phone Prefix: {profile.phonePf}</div>
                </div>
            ) : (
                <p>User profile not found.</p>
            )}
        </div>
    );
};

export default UserProfile;
