import { useState, useEffect } from "react";
import { findAllCourseEditions } from "../../services/DefineRucInCourseEditionService";
import { fetchEnrolmentCount } from "../../services/enrolmentCountInCourseEditionService";

type CourseEdition = {
    courseEditionGeneratedID: string;
    programmeAcronym: string;
    schoolYearID: string;
    courseAcronym: string;
    courseName: string;
    studyPlanImplementationDate: string;
    courseEditionID: string;
    teacherID: string;
    enrolmentCount?: number; // optional because it's added later
};

export default function useFetchCourseEditions() {
    const [courseEditions, setCourseEditions] = useState<CourseEdition[]>([]);

    useEffect(() => {
        async function fetchAndSortCourseEditions() {
            try {
                const res = await findAllCourseEditions();
                const editions: CourseEdition[] = res._embedded?.courseEditionResponseDTOList || [];

                const editionsWithCounts: CourseEdition[] = await Promise.all(
                    editions.map(async (edition) => {
                        try {
                            const countDTO = await fetchEnrolmentCount(edition.courseEditionGeneratedID);
                            return {
                                ...edition,
                                enrolmentCount: countDTO.studentCount
                            };
                        } catch (e) {
                            console.error("Failed to fetch enrolment count for edition:", edition, e);
                            return {
                                ...edition,
                                enrolmentCount: 0
                            };
                        }
                    })
                );

                editionsWithCounts.sort((a, b) => b.enrolmentCount! - a.enrolmentCount!); // use ! since we know it's defined now
                setCourseEditions(editionsWithCounts);
            } catch (err) {
                console.error("Failed to load Course Editions:", err);
            }
        }

        fetchAndSortCourseEditions();
    }, []);

    return courseEditions;
}