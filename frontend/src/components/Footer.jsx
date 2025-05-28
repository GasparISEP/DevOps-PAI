import ISEPLogo from '../../src/assets/images/isep-logo-branco-2.png'
import InstaLogo from '../../src/assets/images/icons8-insta-48.png'
import FacebookLogo from '../../src/assets/images/icons8-facebook-48.png'
import {useState} from 'react'
import '../../src/styles/Footer.css'

export default function Footer() {
    const [email, setEmail] = useState("");

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    return (
        <footer className="footer">
            <div className="footer-grid">
                <div className="footer-div">
                    <div className="logo-footer-div">
                        <img src={ISEPLogo} alt="Isep logo"/>
                    </div>
                    <form className="footer-form" onSubmit={handleSubmit}>
                        <input
                            className="footer-form-input"
                            type="email"
                            value={email}
                            onChange={handleEmailChange}
                            placeholder="Your Email"
                            required
                        />

                        <button className="footer-form-btn" type="submit">
                            SUBSCRIBE NOW
                        </button>
                    </form>
                </div>
                <div className="footer-div">
                    <h4 className="h4">Information</h4>
                    <p className="footer-div-p">Rua Dr. António Bernardino de Almeida, 431
                        4249-015 Porto
                        Portugal</p>
                    <p className="footer-div-p">About Us</p>
                    <p className="footer-div-p">Events</p>
                </div>
                <div className="footer-div">
                    <h4 className="h4">Helpful Links</h4>
                    <p className="footer-div-p">Services</p>
                    <p className="footer-div-p">Supports</p>
                    <p className="footer-div-p">Terms & Condition</p>
                    <p className="footer-div-p">Privacy Policy</p>
                </div>
                <div className="footer-div">
                    <h4 className="h4">Contact Us</h4>
                    <p className="footer-div-p">+351 228 340 500</p>
                    <p className="footer-div-p">info-sa@isep.ipp.pt</p>
                    <div className="footer-socials-div">
                        <a href="https://www.instagram.com/isep.pporto/" target="_blank">
                            <img src={InstaLogo} alt="Instagram logo"/>
                        </a>
                        <a href="https://www.facebook.com/isep.pporto/?locale=pt_PT" target="_blank">
                            <img src={FacebookLogo} alt="Facebook Logo"/>
                        </a>
                    </div>
                </div>
            </div>
        </footer>
    );
}