const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function updateTeacherCategory (payload) {
    const { teacherId, teacherCategoryID, date } = payload;

    const response = await fetch(`${API_URL}/teachers/${teacherId}/careerprogressions/category`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            teacherCategoryID: payload.teacherCategoryID,
            date: payload.date
        })
    });

    let responseData = null;

    try {
        responseData = await response.json();
    } catch (e) {
        console.warn("Response without JSON body.");
    }

    if (!response.ok) {
        const errMsg = responseData?.message || `HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    const selfLink = responseData?._links?.self?.href || null;
    const allLink = responseData?._links?.all?.href || null;
    const detailsLink = responseData?._links?.details?.href || null;
    const collectionLink = responseData?._links?.collection?.href || null;

    return {
        data: responseData,
        links: {
            self: selfLink,
            all: allLink,
            details: detailsLink,
            collection: collectionLink
        }
    };
}
