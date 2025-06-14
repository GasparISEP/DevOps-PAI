import { defineRucInCourseEdition, getAllSchoolYears } from '../../services/DefineRucInCourseEditionService';

// Mock global fetch
global.fetch = jest.fn();

beforeEach(() => {
    fetch.mockClear();
});

describe('defineRucInCourseEdition', () => {
    it('should call the correct API endpoint with PATCH', async () => {
        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => ({ success: true }),
        });

        const courseEditionId = '123';
        const teacherId = '456';
        const result = await defineRucInCourseEdition({ courseEditionId, teacherId });

        expect(fetch).toHaveBeenCalledWith(
            expect.stringContaining(`/course-editions/${courseEditionId}/ruc`),
            expect.objectContaining({
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ teacherId }),
            })
        );

        expect(result).toEqual({ success: true });
    });

    it('should throw error with API error message', async () => {
        fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({ message: 'Some error' }),
        });

        await expect(defineRucInCourseEdition({ courseEditionId: '1', teacherId: '2' }))
            .rejects.toThrow('Some error');
    });

    it('should throw fallback error if response body is invalid JSON', async () => {
        const warnSpy = jest.spyOn(console, 'warn').mockImplementation(() => {});

        fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => { throw new Error('Invalid JSON'); },
            status: 500
        });

        await expect(defineRucInCourseEdition({ courseEditionId: '1', teacherId: '2' }))
            .rejects.toThrow('Fail to define RUC. HTTP 500');

        warnSpy.mockRestore();
    });
});

describe('getAllSchoolYears', () => {
    it('should return school years if request succeeds', async () => {
        const mockData = [{ year: '2023-2024' }];
        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockData,
        });

        const result = await getAllSchoolYears();
        expect(fetch).toHaveBeenCalledWith(expect.stringContaining('/school-years/description/'), expect.objectContaining({
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        }));
        expect(result).toEqual(mockData);
    });

    it('should throw error when response is not ok with message', async () => {
        fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({ message: 'Failed to fetch school years' }),
            status: 404,
        });

        await expect(getAllSchoolYears()).rejects.toThrow('Failed to fetch school years');
    });

    it('should throw fallback error when response is not ok and JSON is invalid', async () => {
        fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => { throw new Error('Invalid JSON'); },
            status: 500
        });

        await expect(getAllSchoolYears()).rejects.toThrow('Failed to fetch school years. HTTP 500');
    });
});