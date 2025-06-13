const API_URL = process.env.REACT_APP_API_URL

export async function updateTeacherCategory (payload) {
    const { teacher, ...rest } = payload;

    const response = await fetch(`${API_URL}/teachers/${teacher}/careerprogressions/category`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            teacherCategoryID: payload.teacherCategory,
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

    return {
        data: responseData,
        links: {
            self: selfLink,
            all: allLink
        }
    };
}
