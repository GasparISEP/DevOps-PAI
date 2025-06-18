import { useState, useEffect } from "react";

type CourseEdition = {
    courseEditionGeneratedID: string;
    programmeAcronym: string;
    schoolYearID: string;
    courseAcronym: string;
    courseName: string;
    studyPlanImplementationDate: string;
    courseEditionID: string;
    teacherID: string;
}

export default function useFetchCourseEditions() {
    const [courseEditions, setCourseEditions] = useState<CourseEdition[]>([]);

    useEffect(() => {
        async function fetchCourseEditions() {
            try {
                const res = await fetch(`${process.env.REACT_APP_API_URL}/course-editions`);
                const data = await res.json();
                const editions = data._embedded?.courseEditionResponseDTOList || [];
                setCourseEditions(editions);

            } catch (err) {
                console.error("Failed to load Course Editions:", err);
            }
        }

        fetchCourseEditions();
    }, []);

    return courseEditions;
}
