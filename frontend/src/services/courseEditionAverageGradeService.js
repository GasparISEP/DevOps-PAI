async function fetchAverageGradeFromLink(link) {
    const response = await fetch(link);

    if (!response.ok) {
        throw new Error(`Failed to fetch average grade: ${response.status} ${response.statusText}`);
    }

    const text = await response.text();

    if (text === null || text.trim() === "" || text.trim() === "null") {
        return { averageGrade: null }; // nenhum dado ainda
    }

    const value = parseFloat(text);
    if (isNaN(value)) {
        throw new Error('Average grade is not a valid number.');
    }

    return { averageGrade: value };
}

module.exports = {
    fetchAverageGradeFromLink
};