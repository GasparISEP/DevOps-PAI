import { gradeAStudentWithLink, getEnrolmentsForStudent } from '../../services/courseEditionGradeStudentService';

global.fetch = jest.fn();

beforeEach(() => {
    fetch.mockClear();
});

describe('courseEditionGradeStudentService', () => {

    describe('gradeAStudentWithLink', () => {
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

        // Teste de sucesso com link HATEOAS
        it('should send a POST request and return response with student link', async () => {
            const mockResponseData = {
                id: 1,
                ...mockGradeStudentDTO,
                links: [{ rel: "student-details", href: "/students/123456" }]
            };

            fetch.mockResolvedValueOnce({
                ok: true,
                json: jest.fn().mockResolvedValueOnce(mockResponseData)
            });

            const result = await gradeAStudentWithLink(mockGradeStudentDTO);

            expect(fetch).toHaveBeenCalledWith(expect.stringContaining('/studentgrades/register/hateoas'), expect.objectContaining({
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(mockGradeStudentDTO)
            }));

            expect(result.data).toEqual(mockResponseData);
            expect(result.studentLink).toBe("/students/123456");
        });

        // Teste de erro com mensagem JSON
        it('should throw an error if response contains JSON error', async () => {
            const mockErrorData = { message: 'Erro ao registrar a nota' };

            fetch.mockResolvedValueOnce({
                ok: false,
                headers: { get: () => 'application/json' },
                json: jest.fn().mockResolvedValueOnce(mockErrorData)
            });

            await expect(gradeAStudentWithLink({})).rejects.toThrow('Erro ao registrar a nota');
        });

        // Teste de erro com mensagem em texto
        it('should throw an error if response contains plain text error', async () => {
            fetch.mockResolvedValueOnce({
                ok: false,
                headers: { get: () => 'text/plain' },
                text: jest.fn().mockResolvedValueOnce('Erro em texto plano')
            });

            await expect(gradeAStudentWithLink({})).rejects.toThrow('Erro em texto plano');
        });
    });

    describe('getEnrolmentsForStudent', () => {
        const studentID = 123456;
        const mockEnrolments = [
            { editionID: 'ed1', courseName: 'Computer Science' },
            { editionID: 'ed2', courseName: 'Software Engineering' }
        ];

        // Teste de sucesso
        it('should fetch enrolments for a student successfully', async () => {
            fetch.mockResolvedValueOnce({
                ok: true,
                json: jest.fn().mockResolvedValueOnce(mockEnrolments)
            });

            const result = await getEnrolmentsForStudent(studentID);

            expect(fetch).toHaveBeenCalledWith(expect.stringContaining(`/students/${studentID}/courseeditionenrolments`));
            expect(result).toEqual(mockEnrolments);
        });

        // Teste de erro ao buscar inscrições
        it('should throw an error if fetch fails', async () => {
            fetch.mockResolvedValueOnce({
                ok: false
            });

            await expect(getEnrolmentsForStudent(studentID)).rejects.toThrow(`Erro ao buscar inscrições do estudante ${studentID}`);
        });
    });
});
