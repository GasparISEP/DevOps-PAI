import '../../styles/HomePage.css'
import DownArrow from '../../assets/images/white-down-arrow.png'

export default function HomePageCTA() {

    function Greeting() {
        const hour = new Date().getHours();
        let message;

        if (hour < 12) {
            message = 'Good morning!';
        } else if (hour < 19) {
            message = 'Good afternoon!';
        } else {
            message = 'Good evening!';
        }

        return message;
    }

    const handleScroll = () => {
        const section = document.getElementById('home-page-info-section');
        if (section) {
            section.scrollIntoView({ behavior: 'smooth' });
        }
    };


    return (
        <section className="home-page-cta-section">
            <div className="cta-main-div">
                <span><Greeting/> Welcome to ISEP</span>
                <h1>Creating Milestones for<br/>Future Generations</h1>
                <div className="cta-btn-div">
                    <button onClick={handleScroll}>LEARN MORE</button>
                </div>

            </div>
        </section>
    )
}