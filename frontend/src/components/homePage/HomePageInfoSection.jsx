import '../../styles/HomePageInfoSection.css'
import infoData from "./InfoSectionData";
import InfoElement from "./InfoElement";

export default function HomePageInfoSection () {
    return (
        <section className="home-page-info-section">

            <div className="info-page-left-column">
                <h2>Welcome to <span>ISEP</span>, a prestigious university</h2>
                <p>Where innovation meets engineering excellence. With a hands-on approach to learning, a vibrant community,
                    and strong industry connections, ISEP empowers students to shape the future through technology.</p>
            </div>

            <div className="info-page-right-column">
                    {infoData.map((item) => (
                        <InfoElement
                            key={item.id}
                            title={item.title}
                            description={item.description}
                            image={item.image}
                        />
                    ))}
            </div>

        </section>
    )
}