const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

/**
 * Updates the category of a teacher.
 * @param {string} teacherId - The teacher's ID
 * @param {Object} updateRequestDTO - Payload containing the new category data (UpdateTeacherCategoryRequestDTO)
 * @returns {Promise<Object>} - ResponseDTO with updated category and HATEOAS links
 */
export async function updateTeacherCategory(teacherId, updateRequestDTO) {
    const response = await fetch(`${API_URL}/teachers/${teacherId}/careerprogressions/category`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updateRequestDTO)
    });

    let responseData = null;

    try {
        responseData = await response.json();
    } catch (error) {
        console.warn("Response without JSON body.");
    }

    if (!response.ok) {
        const errorMsg = responseData?.message || `HTTP ${response.status} Error`;
        throw new Error(errorMsg);
    }

    return responseData;
}
