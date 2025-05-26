import '../../styles/HomePage.css'

export default function HomePageCTA() {

    function Greeting() {
        const hour = new Date().getHours();
        let message;

        if (hour < 12) {
            message = 'Good morning!';
        } else if (hour < 18) {
            message = 'Good afternoon!';
        } else {
            message = 'Good evening!';
        }

        return message;
    }

    return (
        <section className="home-page-cta-section">
            <div className="cta-main-div">
                <h1><span><Greeting/></span> <br/> Welcome to ISEP</h1>
            </div>
        </section>
    )
}