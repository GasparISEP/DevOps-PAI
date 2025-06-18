import { useState } from "react";
import { fetchAverageGradeFromLink } from "../../services/averageGradeInCourseEditionService";

export default function useCourseEditionAverageGradeModal() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [averageGrade, setAverageGrade] = useState(null);
    const [selectedCourse, setSelectedCourse] = useState(null);

    const handleShowAverageGrade = async (edition) => {
        try {
            setSelectedCourse(edition);

            const link = edition._links?.["average-grade"]?.href;

            if (!link) throw new Error("No HATEOAS link for average-grade");

            const response = await fetchAverageGradeFromLink(link);

            setAverageGrade(response.averageGrade);
            setIsModalOpen(true);
        } catch (error) {
            console.error('Error fetching average grade:', error);
            alert('Error fetching average grade: ' + error.message);
        }
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setSelectedCourse(null);
        setAverageGrade(null);
    };

    return {
        isModalOpen,
        averageGrade,
        selectedCourse,
        handleShowAverageGrade,
        closeModal
    };
}