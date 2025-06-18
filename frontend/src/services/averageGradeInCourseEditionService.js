export async function fetchAverageGradeFromLink(link) {
    try {
        const response = await fetch(link);
        if (!response.ok) {
            throw new Error(`Failed to fetch average grade: ${response.status} ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        throw error;
    }
}