export async function fetchAverageGrade(programmeAcronym, courseAcronym, schoolYearId, studyPlanDate) {
    try {
        const url = `${process.env.REACT_APP_API_URL}/course-editions/average-grade` +
            `?programmeAcronym=${programmeAcronym}` +
            `&courseAcronym=${courseAcronym}` +
            `&schoolYearId=${schoolYearId}` +
            `&studyPlanDate=${studyPlanDate}`;

        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Failed to fetch average grade: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        throw error;
    }
}