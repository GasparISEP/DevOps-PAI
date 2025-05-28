
import { registerCourseInStudyPlan } from '../../../services/courseService';

describe('registerCourseInStudyPlan', () => {
    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    it('should return response data when request is successful', async () => {
        const mockResponse = { message: 'Course registered' };
        fetch.mockResolvedValue({
            ok: true,
            json: jest.fn().mockResolvedValue(mockResponse)
        });

        const payload = { courseId: 'CS101', planId: '2025A' };
        const result = await registerCourseInStudyPlan(payload);

        expect(result).toEqual(mockResponse);
        expect(fetch).toHaveBeenCalledWith(
            expect.stringContaining('/course-in-study-plan'),
            expect.objectContaining({
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            })
        );
    });

    it('should throw an error when response is not ok with error message', async () => {
        const mockError = { error: 'Invalid course ID' };
        fetch.mockResolvedValue({
            ok: false,
            json: jest.fn().mockResolvedValue(mockError)
        });

        const payload = { courseId: 'INVALID', planId: '2025A' };

        await expect(registerCourseInStudyPlan(payload)).rejects.toThrow('Invalid course ID');
    });

    it('should throw generic error when response is not ok and no JSON', async () => {
        fetch.mockResolvedValue({
            ok: false,
            json: jest.fn().mockRejectedValue(new Error('no body')),
            status: 500
        });

        const payload = { courseId: 'ANY', planId: 'ANY' };

        await expect(registerCourseInStudyPlan(payload)).rejects.toThrow('HTTP 500');
    });

    it('should handle non-JSON response gracefully on success', async () => {
        fetch.mockResolvedValue({
            ok: true,
            json: jest.fn().mockRejectedValue(new Error('not JSON'))
        });

        const payload = { courseId: 'CS101', planId: '2025A' };
        const result = await registerCourseInStudyPlan(payload);

        expect(result).toBeNull();
    });

    it('should throw error when fetch fails', async () => {
        fetch.mockRejectedValue(new Error('Network failure'));

        const payload = { courseId: 'CS101', planId: '2025A' };

        await expect(registerCourseInStudyPlan(payload)).rejects.toThrow('Network failure');
    });

    it('should return null when response is ok but no JSON body', async () => {
        fetch.mockResolvedValue({
            ok: true,
            json: jest.fn().mockRejectedValue(new Error('no JSON body')),
        });

        const payload = { courseId: 'CS101', planId: '2025A' };
        const result = await registerCourseInStudyPlan(payload);

        expect(result).toBeNull();
    });

    it('should throw generic HTTP error when response is not ok and no JSON body', async () => {
        fetch.mockResolvedValue({
            ok: false,
            status: 400,
            json: jest.fn().mockRejectedValue(new Error('no JSON')),
        });

        const payload = { courseId: 'CS101', planId: '2025A' };

        await expect(registerCourseInStudyPlan(payload)).rejects.toThrow('HTTP 400');
    });

});