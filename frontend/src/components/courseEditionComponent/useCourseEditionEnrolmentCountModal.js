import { useState } from "react";
import { fetchEnrolmentCount } from "../../services/enrolmentCountInCourseEditionService";

export default function useCourseEditionEnrolmentCountModal() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [enrolmentCount, setEnrolmentCount] = useState(null);
    const [selectedCourse, setSelectedCourse] = useState(null);

    const handleCountEnrolments = async (edition) => {
        try {
            setSelectedCourse(edition);
            const response = await fetchEnrolmentCount(edition.courseEditionGeneratedID);
            setEnrolmentCount(response.studentCount);
            setIsModalOpen(true);
        } catch (error) {
            console.error('Error counting enrolments:', error);
            alert('Error counting enrolments: ' + error.message);
        }
    };

    const closeModal = () => setIsModalOpen(false);

    return {
        isModalOpen,
        enrolmentCount,
        selectedCourse,
        handleCountEnrolments,
        closeModal
    };
}