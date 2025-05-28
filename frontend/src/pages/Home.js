import React from 'react';
import NavBar from '../components/NavBar';
import HomePageCTA from "../components/homePage/HomePageCTA";
import '../styles/RegisterStudentPage.css';
import HomePageInfoSection from "../components/homePage/HomePageInfoSection";
import Footer from "../components/Footer";

function Home() {
    return (
        <>
            <NavBar />
            <HomePageCTA/>
            <HomePageInfoSection/>
            <Footer/>
        </>

    );
}

export default Home;