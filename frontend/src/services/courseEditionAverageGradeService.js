async function fetchAverageGradeFromLink(link) {
    const response = await fetch(link);
    if (!response.ok) {
        throw new Error(`Failed to fetch average grade: ${response.status} ${response.statusText}`);
    }

    const data = await response.json();
    const { courseEditionId, averageGrade } = data;
    return { courseEditionId, averageGrade };
}

module.exports = {
    fetchAverageGradeFromLink
};