import { fetchEnrolmentCount } from '../enrolmentCountInCourseEditionService';

// Mock the global fetch function
global.fetch = jest.fn();

describe('enrolmentCountInCourseEditionService', () => {
    beforeEach(() => {
        // Clear all mocks before each test
        jest.clearAllMocks();
        
        // Reset the environment variable
        process.env.REACT_APP_API_URL = 'http://localhost:8081';
    });

    test('successfully fetches enrolment count', async () => {
        // Mock data
        const mockResponse = { count: 5 };
        const courseEditionId = '123';

        // Mock successful fetch response
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: () => Promise.resolve(mockResponse)
        });

        // Call the service
        const result = await fetchEnrolmentCount(courseEditionId);

        // Assertions
        expect(fetch).toHaveBeenCalledWith(
            `${process.env.REACT_APP_API_URL}/course-editions/${courseEditionId}/enrolments/count`
        );
        expect(result).toEqual(mockResponse);
    });

    test('handles non-OK response', async () => {
        // Mock data
        const courseEditionId = '123';
        const errorStatus = 404;
        const errorStatusText = 'Not Found';

        // Mock failed fetch response
        global.fetch.mockResolvedValueOnce({
            ok: false,
            status: errorStatus,
            statusText: errorStatusText
        });

        // Call the service and expect it to throw
        await expect(fetchEnrolmentCount(courseEditionId)).rejects.toThrow(
            `Failed to fetch enrolment count: ${errorStatus} ${errorStatusText}`
        );

        // Verify fetch was called with correct URL
        expect(fetch).toHaveBeenCalledWith(
            `${process.env.REACT_APP_API_URL}/course-editions/${courseEditionId}/enrolments/count`
        );
    });

    test('handles network errors', async () => {
        // Mock data
        const courseEditionId = '123';
        const networkError = new Error('Network error');

        // Mock fetch to throw a network error
        global.fetch.mockRejectedValueOnce(networkError);

        // Call the service and expect it to throw
        await expect(fetchEnrolmentCount(courseEditionId)).rejects.toThrow(networkError);

        // Verify fetch was called with correct URL
        expect(fetch).toHaveBeenCalledWith(
            `${process.env.REACT_APP_API_URL}/course-editions/${courseEditionId}/enrolments/count`
        );
    });

    test('handles invalid JSON response', async () => {
        // Mock data
        const courseEditionId = '123';

        // Mock fetch to return invalid JSON
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: () => Promise.reject(new Error('Invalid JSON'))
        });

        // Call the service and expect it to throw
        await expect(fetchEnrolmentCount(courseEditionId)).rejects.toThrow('Invalid JSON');

        // Verify fetch was called with correct URL
        expect(fetch).toHaveBeenCalledWith(
            `${process.env.REACT_APP_API_URL}/course-editions/${courseEditionId}/enrolments/count`
        );
    });

    test('uses correct API URL from environment variable', async () => {
        // Mock data
        const mockResponse = { count: 5 };
        const courseEditionId = '123';
        const customApiUrl = 'http://custom-api-url:8080';
        process.env.REACT_APP_API_URL = customApiUrl;

        // Mock successful fetch response
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: () => Promise.resolve(mockResponse)
        });

        // Call the service
        await fetchEnrolmentCount(courseEditionId);

        // Verify fetch was called with correct URL including custom API URL
        expect(fetch).toHaveBeenCalledWith(
            `${customApiUrl}/course-editions/${courseEditionId}/enrolments/count`
        );
    });
}); 