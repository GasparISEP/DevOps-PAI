import { gradeAStudent, findAllCourseEditions } from '../../services/courseEditionGradeStudentService';

global.fetch = jest.fn();

beforeEach(() => {
    fetch.mockClear();
});

describe('courseEditionGradeStudentService', () => {

    // Mock GradeAStudentRequestDTO
    describe('gradeAStudent', () => {
        const mockGradeStudentDTO = {
            studentUniqueNumber: 123456,
            grade: 18.5,
            date: '2025-06-09',
            courseAcronym: 'CS',
            courseName: 'Computer Science',
            courseEditionID: 'f81d4fae-7dec-11d0-a765-00a0c91e6bf6',
            programmeAcronym: 'DSOFT',
            programmeName: 'Desenvolvimento de Software',
            studyPlanImplementationDate: '15-08-2017'
        };

        // Should successfully send a POST request and receive a valid response
        it('should send a POST request and return the response data (success case)', async () => {
            const mockResponseData = { id: 1, ...mockGradeStudentDTO };

            fetch.mockResolvedValueOnce({
                ok: true,
                json: jest.fn().mockResolvedValueOnce(mockResponseData)
            });

            const result = await gradeAStudent(mockGradeStudentDTO);
            expect(fetch).toHaveBeenCalledWith(expect.stringContaining('/courseeditions/studentgrades/register'), expect.objectContaining({
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(mockGradeStudentDTO)
            }));
            expect(result).toEqual(mockResponseData);
        });

        // Should throw an error if the response contains a JSON error message
        it('should throw an error if the response is not ok with JSON error body', async () => {
            const mockErrorData = { message: 'Erro ao registar a nota' };

            fetch.mockResolvedValueOnce({
                ok: false,
                headers: { get: () => 'application/json' },
                json: jest.fn().mockResolvedValueOnce(mockErrorData)
            });

            await expect(gradeAStudent({})).rejects.toThrow('Erro ao registar a nota');
        });

        // Should throw an error if the response contains a plain text error message
        it('should throw an error if the response is not ok with plain text body', async () => {
            fetch.mockResolvedValueOnce({
                ok: false,
                headers: { get: () => 'text/plain' },
                text: jest.fn().mockResolvedValueOnce('Erro em texto plano')
            });

            await expect(gradeAStudent({})).rejects.toThrow('Erro em texto plano');
        });

        // Should issue a warning if response is successful but contains no JSON
        it('should warn if response is ok but has no JSON body', async () => {
            const mockResponse = {
                ok: true,
                json: jest.fn().mockImplementation(() => Promise.reject(new Error('Unexpected end of JSON input')))
            };

            fetch.mockResolvedValueOnce(mockResponse);
            const warnSpy = jest.spyOn(console, 'warn').mockImplementation(() => {});

            const result = await gradeAStudent(mockGradeStudentDTO);

            expect(result).toBe(null); // responseData remains null
            expect(warnSpy).toHaveBeenCalledWith('Resposta sem corpo JSON');

            warnSpy.mockRestore();
        });
    });

    describe('findAllCourseEditions', () => {
        // Should successfully fetch all course editions
        it('should fetch all course editions successfully', async () => {
            const mockCourseEditions = [{ id: 1, courseName: 'Computer Science' }];

            fetch.mockResolvedValueOnce({
                ok: true,
                json: jest.fn().mockResolvedValueOnce(mockCourseEditions)
            });

            const result = await findAllCourseEditions();
            expect(fetch).toHaveBeenCalledWith(expect.stringContaining('/courseeditions'));
            expect(result).toEqual(mockCourseEditions);
        });

        // Should throw an error if fetching course editions fails
        it('should throw an error if fetch fails', async () => {
            fetch.mockResolvedValueOnce({
                ok: false
            });

            await expect(findAllCourseEditions()).rejects.toThrow('Failed to fetch Course Editions');
        });
    });
});
