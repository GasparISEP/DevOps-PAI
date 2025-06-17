import {
    removeStudentFromCourseEditionService,
    getCourseEditionsByStudent,
} from '../../services/removeStudentFromCourseEditionService';

describe('removeStudentFromCourseEditionService', () => {
    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    it('should call fetch with the correct URL and method', async () => {
        fetch.mockResolvedValueOnce({ ok: true });

        const studentId = '123';
        const courseEditionId = 'abc-456';

        await removeStudentFromCourseEditionService(studentId, courseEditionId);

        expect(fetch).toHaveBeenCalledWith(
            `http://localhost:8081/students/${studentId}/course-editions/${courseEditionId}`,
            { method: 'PATCH' }
        );
    });

    it('should throw an error when response is not ok', async () => {
        fetch.mockResolvedValueOnce({ ok: false });

        await expect(
            removeStudentFromCourseEditionService('123', 'abc-456')
        ).rejects.toThrow('Error trying to remove student from course edition.');
    });
});

describe('getCourseEditionsByStudent', () => {
    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    it('should return parsed data when JSON is valid', async () => {
        const mockData = [{ name: 'Course Edition A' }];
        fetch.mockResolvedValueOnce({
            text: jest.fn().mockResolvedValueOnce(JSON.stringify(mockData)),
        });

        const result = await getCourseEditionsByStudent('321');
        expect(result).toEqual(mockData);
    });

    it('should throw error when JSON is invalid', async () => {
        fetch.mockResolvedValueOnce({
            text: jest.fn().mockResolvedValueOnce('not-json'),
        });

        await expect(getCourseEditionsByStudent('321')).rejects.toThrow();
    });
});
