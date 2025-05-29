import { registerTeacher, getAllTeachers } from '../../services/teacherService';

global.fetch = jest.fn();

beforeEach(() => {
    fetch.mockClear();
});

describe('teacherService', () => {
    describe('registerTeacher', () => {
        it('should send a POST request and return the response data (success case)', async () => {
            const mockTeacherDTO = { name: 'Maria Silva', nif: '123456789' };
            const mockResponseData = { id: 1, ...mockTeacherDTO };

            fetch.mockResolvedValueOnce({
                ok: true,
                json: jest.fn().mockResolvedValueOnce(mockResponseData)
            });

            const result = await registerTeacher(mockTeacherDTO);
            expect(fetch).toHaveBeenCalledWith(expect.stringContaining('/teachers'), expect.objectContaining({
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(mockTeacherDTO)
            }));
            expect(result).toEqual(mockResponseData);
        });

        it('should throw an error if the response is not ok with JSON error body', async () => {
            const mockErrorData = { message: 'Erro ao registar' };

            fetch.mockResolvedValueOnce({
                ok: false,
                headers: {
                    get: () => 'application/json'
                },
                json: jest.fn().mockResolvedValueOnce(mockErrorData)
            });

            await expect(registerTeacher({})).rejects.toThrow('Erro ao registar');
        });

        it('should throw an error if the response is not ok with plain text body', async () => {
            fetch.mockResolvedValueOnce({
                ok: false,
                headers: {
                    get: () => 'text/plain'
                },
                text: jest.fn().mockResolvedValueOnce('Erro em texto plano')
            });

            await expect(registerTeacher({})).rejects.toThrow('Erro em texto plano');
        });

        it('should warn if response is ok but has no JSON body', async () => {
            const mockResponse = {
                ok: true,
                json: jest.fn().mockRejectedValue(new Error('Unexpected end of JSON input'))
            };

            fetch.mockResolvedValueOnce(mockResponse);
            const warnSpy = jest.spyOn(console, 'warn').mockImplementation(() => {});

            const result = await registerTeacher({ name: 'João', nif: '987654321' });

            expect(result).toBe(null); // responseData remains null
            expect(warnSpy).toHaveBeenCalledWith('Resposta sem corpo JSON');

            warnSpy.mockRestore();
        });
    });

    describe('getAllTeachers', () => {
        it('should fetch all teachers successfully', async () => {
            const mockTeachers = [{ id: 1, name: 'Maria Silva' }];

            fetch.mockResolvedValueOnce({
                ok: true,
                json: jest.fn().mockResolvedValueOnce(mockTeachers)
            });

            const result = await getAllTeachers();
            expect(fetch).toHaveBeenCalledWith(expect.stringContaining('/teachers'));
            expect(result).toEqual(mockTeachers);
        });

        it('should throw an error if fetch fails', async () => {
            fetch.mockResolvedValueOnce({
                ok: false
            });

            await expect(getAllTeachers()).rejects.toThrow('Failed to fetch teachers');
        });
    });
});
