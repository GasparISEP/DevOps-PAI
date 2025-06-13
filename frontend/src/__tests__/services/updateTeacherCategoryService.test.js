import { updateTeacherCategory } from '../../services/UpdateTeacherCategoryService';

global.fetch = jest.fn(); // mock global fetch

describe('updateTeacherCategory', () => {
    const teacherId = '123';
    const payload = { newCategory: 'Associate Professor' };
    const expectedUrl = `http://localhost:8081/teachers/${teacherId}/careerprogressions/category`;

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should send POST request with correct URL and body and return response JSON', async () => {
        const mockResponse = {
            id: '456',
            newCategory: 'Associate Professor',
            _links: { self: { href: expectedUrl } }
        };

        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockResponse,
        });

        const result = await updateTeacherCategory(teacherId, payload);

        expect(fetch).toHaveBeenCalledWith(expectedUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload),
        });

        expect(result).toEqual(mockResponse);
    });

    it('should throw error if response is not ok and JSON body exists', async () => {
        const errorMessage = 'Invalid category';

        fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({ message: errorMessage }),
        });

        await expect(updateTeacherCategory(teacherId, payload)).rejects.toThrow(errorMessage);
    });

    it('should throw generic error if response is not ok and no JSON body', async () => {
        fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => { throw new Error('Invalid JSON'); },
        });

        await expect(updateTeacherCategory(teacherId, payload)).rejects.toThrow('HTTP 200 Error');
    });

    it('should warn if response body is not JSON but still ok', async () => {
        const warnSpy = jest.spyOn(console, 'warn').mockImplementation(() => {});

        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => { throw new Error('Invalid JSON'); },
        });

        const result = await updateTeacherCategory(teacherId, payload);

        expect(warnSpy).toHaveBeenCalledWith('Response without JSON body.');
        expect(result).toBeNull();

        warnSpy.mockRestore();
    });
});
