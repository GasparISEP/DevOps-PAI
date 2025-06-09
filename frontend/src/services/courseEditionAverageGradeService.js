async function fetchAverageGradeFromLink(link) {
    const response = await fetch(link);
    if (!response.ok) {
        throw new Error('Failed to fetch average grade');
    }
    return response.json();
}

module.exports = {
    fetchAverageGradeFromLink
};
