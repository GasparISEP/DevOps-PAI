import { useState } from "react";

export default function useCourseEditionAverageGradeModal() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [averageGrade, setAverageGrade] = useState(null);
    const [statusCode, setStatusCode] = useState(null);
    const [selectedCourse, setSelectedCourse] = useState(null);

    const handleShowAverageGrade = async (edition) => {
        try {
            setSelectedCourse(edition);

            const link = edition._links?.["average-grade"]?.href;
            if (!link) {
                setStatusCode(404); // link não encontrado
                setAverageGrade(null);
                setIsModalOpen(true);
                return;
            }

            const response = await fetch(link);
            setStatusCode(response.status);

            if (!response.ok) {
                throw new Error(`HTTP ${response.status}`);
            }

            const data = await response.json();
            setAverageGrade(data.averageGrade);
        } catch (error) {
            console.error('Error fetching average grade:', error);

            setAverageGrade(null);
        } finally {
            setIsModalOpen(true);
        }
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setSelectedCourse(null);
        setAverageGrade(null);
        setStatusCode(null);
    };

    return {
        isModalOpen,
        averageGrade,
        selectedCourse,
        statusCode,
        handleShowAverageGrade,
        closeModal
    };
}