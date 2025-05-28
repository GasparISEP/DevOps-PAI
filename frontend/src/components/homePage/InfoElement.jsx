export default function InfoElement ({ title, description, image, alt }) {

    return (
        <div className="info-element-div">
            <img src={image} alt={alt}/>
            <h3>{title}</h3>
            <p>{description}</p>
        </div>
)
}