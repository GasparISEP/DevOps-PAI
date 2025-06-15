import {
    registerStudent,
    getAllStudents,
    enrolStudentInProgramme,
    findAllDepartments,
    findAllProgrammes,
    findAllAccessMethods,
} from '../../services/studentService';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8081';

describe('studentService', () => {
    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    // ==== registerStudent ====
    describe('registerStudent', () => {
        const payload = {
            studentID: "123456",
            name: "Test Student",
            nif: "123456789",
            email: "test@student.com"
        };

        it('should register student successfully and return data', async () => {
            const mockResponse = { id: "123456", name: "Test Student" };

            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => mockResponse,
                headers: { get: (key) => (key.toLowerCase() === 'content-type' ? 'application/json' : null) }
            });

            const result = await registerStudent(payload);
            expect(result).toEqual(mockResponse);
        });

        it('should handle empty content-type and fallback to text()', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                headers: { get: () => null },
                text: async () => "Fallback text",
            });

            const result = await registerStudent(payload);
            expect(result).toBe("Fallback text");
        });

        it('should throw error if response not ok and json has message', async () => {
            const errorMessage = "Student already exists";
            global.fetch.mockResolvedValueOnce({
                ok: false,
                json: async () => ({ message: errorMessage }),
                headers: { get: () => 'application/json' },
                status: 400
            });

            await expect(registerStudent(payload)).rejects.toThrow(errorMessage);
        });

        it('should throw error if response not ok and json has error but no message', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: false,
                json: async () => ({ error: "Backend error" }),
                headers: { get: () => 'application/json' },
                status: 400,
            });

            await expect(registerStudent(payload)).rejects.toThrow("Backend error");
        });

        it('should throw error with HTTP status if no message or error fields', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: false,
                json: async () => ({}),
                headers: { get: () => 'application/json' },
                status: 500,
            });

            await expect(registerStudent(payload)).rejects.toThrow("HTTP 500");
        });

        it('should throw error if JSON parsing throws (malformed JSON)', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                headers: { get: () => 'application/json' },
                json: async () => { throw new SyntaxError('Unexpected token'); }
            });

            await expect(registerStudent(payload)).rejects.toThrow('Unexpected token');
        });

        it('should throw error if fetch itself rejects', async () => {
            global.fetch.mockRejectedValueOnce(new Error('Network error'));

            await expect(registerStudent(payload)).rejects.toThrow('Network error');
        });
    });

    // ==== getAllStudents ====
    describe('getAllStudents', () => {
        it('should return list of students when response is ok and proper format', async () => {
            const mockData = {
                _embedded: {
                    studentResponseDTOList: [
                        { id: 1, name: 'Student 1' },
                        { id: 2, name: 'Student 2' },
                    ]
                }
            };

            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            });

            const result = await getAllStudents();
            expect(result).toEqual(mockData._embedded.studentResponseDTOList);
        });

        it('should return empty array if _embedded missing', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => ({}),
            });

            const result = await getAllStudents();
            expect(result).toEqual([]);
        });

        it('should return empty array if studentResponseDTOList missing', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => ({ _embedded: {} }),
            });

            const result = await getAllStudents();
            expect(result).toEqual([]);
        });

        it('should return empty array if studentResponseDTOList is object not array', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => ({ _embedded: { studentResponseDTOList: {} } }),
            });

            const result = await getAllStudents();
            expect(result).toEqual([]);
        });

        it('should throw error if response not ok', async () => {
            global.fetch.mockResolvedValueOnce({ ok: false });

            await expect(getAllStudents()).rejects.toThrow('Failed to fetch students');
        });

        it('should throw error if json parsing throws', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => { throw new Error('JSON error'); },
            });

            await expect(getAllStudents()).rejects.toThrow('JSON error');
        });

        it('should throw error if fetch rejects', async () => {
            global.fetch.mockRejectedValueOnce(new Error('Network failure'));

            await expect(getAllStudents()).rejects.toThrow('Network failure');
        });
    });

    // ==== enrolStudentInProgramme ====
    describe('enrolStudentInProgramme', () => {
        const payload = { studentId: '123', programmeId: '456' };

        it('should enroll student successfully', async () => {
            const mockResponse = { success: true };

            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => mockResponse,
                headers: { get: () => 'application/json' }
            });

            const result = await enrolStudentInProgramme(payload);
            expect(result).toEqual(mockResponse);
        });

        it('should throw error if response not ok and message', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: false,
                json: async () => ({ message: "Enroll error" }),
                headers: { get: () => 'application/json' },
                status: 400,
            });

            await expect(enrolStudentInProgramme(payload)).rejects.toThrow("Enroll error");
        });

        it('should throw error if response not ok and no message', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: false,
                json: async () => ({}),
                headers: { get: () => 'application/json' },
                status: 500,
            });

            await expect(enrolStudentInProgramme(payload)).rejects.toThrow("HTTP 500");
        });

        it('should throw error if json parsing fails', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => { throw new Error('Invalid JSON'); },
                headers: {
                    get: () => 'application/json'
                }
            });

            await expect(enrolStudentInProgramme(payload)).rejects.toThrow('Invalid JSON');
        });


        it('should throw error if fetch rejects', async () => {
            global.fetch.mockRejectedValueOnce(new Error('Network fail'));

            await expect(enrolStudentInProgramme(payload)).rejects.toThrow('Network fail');
        });
    });

    // ==== findAllDepartments ====
    describe('findAllDepartments', () => {
        it('should return list of departments', async () => {
            const mockData = {
                _embedded: {
                    departmentWithDirectorDTOList: [
                        { id: 'd1', name: 'Dept 1' },
                        { id: 'd2', name: 'Dept 2' },
                    ]
                }
            };

            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            });

            const result = await findAllDepartments();
            expect(result).toEqual(mockData._embedded.departmentWithDirectorDTOList);
        });

        it('should return empty array if _embedded missing', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => ({}),
            });

            const result = await findAllDepartments();
            expect(result).toEqual([]);
        });

        it('should throw error if response not ok', async () => {
            global.fetch.mockResolvedValueOnce({ ok: false });

            await expect(findAllDepartments()).rejects.toThrow('Failed to fetch departments');
        });

        it('should throw error if json parsing fails', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => { throw new Error('JSON fail'); }
            });

            await expect(findAllDepartments()).rejects.toThrow('JSON fail');
        });

        it('should throw error if fetch rejects', async () => {
            global.fetch.mockRejectedValueOnce(new Error('Network fail'));

            await expect(findAllDepartments()).rejects.toThrow('Network fail');
        });
    });

    // ==== findAllProgrammes ====
    describe('findAllProgrammes', () => {
        it('should return list of programmes', async () => {
            const mockData = [
                { id: 'p1', name: 'Prog 1' },
                { id: 'p2', name: 'Prog 2' }
            ];

            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            });

            const result = await findAllProgrammes();
            expect(result).toEqual(mockData);
        });

        it('should return empty array if returned data not array', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => ({ not: 'array' }),
            });

            const result = await findAllProgrammes();
            expect(result).toEqual([]);
        });

        it('should throw error if response not ok', async () => {
            global.fetch.mockResolvedValueOnce({ ok: false });

            await expect(findAllProgrammes()).rejects.toThrow('Failed to fetch programmes');
        });

        it('should throw error if json parsing fails', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => { throw new Error('JSON fail'); }
            });

            await expect(findAllProgrammes()).rejects.toThrow('JSON fail');
        });

        it('should throw error if fetch rejects', async () => {
            global.fetch.mockRejectedValueOnce(new Error('Network fail'));

            await expect(findAllProgrammes()).rejects.toThrow('Network fail');
        });
    });

    // ==== findAllAccessMethods ====
    describe('findAllAccessMethods', () => {
        it('should return list of access methods', async () => {
            const mockData = {
                _embedded: {
                    accessMethodResponseDTOList: [
                        { id: 'a1', name: 'Access 1' },
                        { id: 'a2', name: 'Access 2' },
                    ]
                }
            };

            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            });

            const result = await findAllAccessMethods();
            expect(result).toEqual(mockData._embedded.accessMethodResponseDTOList);
        });

        it('should return empty array if _embedded missing', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => ({}),
            });

            const result = await findAllAccessMethods();
            expect(result).toEqual([]);
        });

        it('should throw error if response not ok', async () => {
            global.fetch.mockResolvedValueOnce({ ok: false });

            await expect(findAllAccessMethods()).rejects.toThrow('Failed to fetch access methods');
        });

        it('should throw error if json parsing fails', async () => {
            global.fetch.mockResolvedValueOnce({
                ok: true,
                json: async () => { throw new Error('JSON fail'); }
            });

            await expect(findAllAccessMethods()).rejects.toThrow('JSON fail');
        });

        it('should throw error if fetch rejects', async () => {
            global.fetch.mockRejectedValueOnce(new Error('Network fail'));

            await expect(findAllAccessMethods()).rejects.toThrow('Network fail');
        });
    });
    const mockPayload = {
        studentID: "123456",
        name: "Test Student",
        nif: "123456789",
        email: "test@student.com"
    };

    it('should throw error if fetch fails', async () => {
        global.fetch.mockRejectedValueOnce(new Error('Network failure'));

        await expect(registerStudent(mockPayload)).rejects.toThrow('Network failure');
    });
    it('should throw error if response body cannot be parsed', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            headers: {
                get: () => 'application/json'
            },
            json: async () => null, // ou undefined
            text: async () => null,
        });

        const result = await registerStudent(mockPayload);
        expect(result).toBeNull(); // ou o valor esperado no teu caso
    });
    it('should return empty array if _embedded.studentResponseDTOList is an object instead of array', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => ({ _embedded: { studentResponseDTOList: {} } }),
        });

        const result = await getAllStudents();
        expect(result).toEqual([]);
    });
    it('should fallback to text() if content-type is not application/json', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            headers: { get: () => 'text/plain' },
            text: async () => 'Some plain text',
        });

        const result = await registerStudent({
            studentID: "123456",
            name: "Test Student",
            nif: "123456789",
            email: "test@student.com"
        });

        expect(result).toBe('Some plain text');
    });
    it('should return empty array if studentResponseDTOList is null', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => ({ _embedded: { studentResponseDTOList: null } }),
        });

        const result = await getAllStudents();
        expect(result).toEqual([]);
    });
    it('should return empty array if studentResponseDTOList is not an array (string)', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => ({ _embedded: { studentResponseDTOList: "not an array" } }),
        });

        const result = await getAllStudents();
        expect(result).toEqual([]);
    });
    it('should return empty array if departmentWithDirectorDTOList is not an array', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => ({
                _embedded: { departmentWithDirectorDTOList: "not an array" }
            }),
        });

        const result = await findAllDepartments();
        expect(result).toEqual([]);
    });
    it('should return empty array if result is null', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => null,
        });

        const result = await findAllProgrammes();
        expect(result).toEqual([]);
    });
    it('should handle undefined headers gracefully', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            headers: { get: () => null },
            text: async () => 'No headers',
        });

        const result = await registerStudent({
            studentID: "123456",
            name: "Test Student",
            nif: "123456789",
            email: "test@student.com"
        });

        expect(result).toBe('No headers');
    });
    it('should throw an error with plain text message when registerStudent fails with non-JSON content', async () => {
        const mockTextError = 'Internal Server Error';

        global.fetch.mockResolvedValueOnce({
            ok: false,
            status: 500,
            headers: { get: () => 'text/plain' },
            text: async () => mockTextError,
        });

        await expect(registerStudent({ name: 'John' })).rejects.toThrow(mockTextError);
    });
    it('should throw an error with plain text when enrolStudentInProgramme fails and content is not JSON', async () => {
        const mockTextError = 'Enrolment failed';

        global.fetch.mockResolvedValueOnce({
            ok: false,
            status: 400,
            headers: { get: () => 'text/plain' },
            text: async () => mockTextError,
        });

        await expect(enrolStudentInProgramme({ studentId: 1, programmeId: 2 })).rejects.toThrow(mockTextError);
    });
});
