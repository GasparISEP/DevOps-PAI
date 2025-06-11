const {
    fetchAverageGradeFromLink
} = require('../../services/courseEditionAverageGradeService');

describe('fetchAverageGradeFromLink', () => {
    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should fetch average grade and return only courseEditionId and averageGrade', async () => {
        const mockResponse = {
            courseEditionId: 'ff0ecb58-8b8a-4be5-99c6-110ebf6a9d38',
            averageGrade: 15.2,
            extraField: 'should not be included'
        };

        const expectedData = {
            courseEditionId: 'ff0ecb58-8b8a-4be5-99c6-110ebf6a9d38',
            averageGrade: 15.2
        };

        const link = 'http://localhost:8081/courseeditions/ff0ecb58-8b8a-4be5-99c6-110ebf6a9d38/average-grade';

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockResponse,
        });

        const result = await fetchAverageGradeFromLink(link);
        expect(global.fetch).toHaveBeenCalledWith(link);
        expect(result).toEqual(expectedData);
    });

    it('should throw a specific error if response is not ok', async () => {
        const link = 'http://localhost:8081/courseeditions/invalid-id/average-grade';

        global.fetch.mockResolvedValueOnce({
            ok: false,
            status: 404,
            statusText: 'Not Found',
        });

        await expect(fetchAverageGradeFromLink(link)).rejects.toThrow('Failed to fetch average grade: 404 Not Found');
    });

    it('should propagate network errors', async () => {
        const link = 'http://localhost:8081/courseeditions/network-error/average-grade';

        global.fetch.mockRejectedValueOnce(new Error('Network Error'));

        await expect(fetchAverageGradeFromLink(link)).rejects.toThrow('Network Error');
    });

    it('should propagate JSON parsing errors', async () => {
        const link = 'http://localhost:8081/courseeditions/ff0ecb58-8b8a-4be5-99c6-110ebf6a9d38/average-grade';

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => {
                throw new Error('JSON parse error');
            }
        });

        await expect(fetchAverageGradeFromLink(link)).rejects.toThrow('JSON parse error');
    });

    it('should return an object with only courseEditionId and averageGrade keys', async () => {
        const mockResponse = {
            courseEditionId: 'ff0ecb58-8b8a-4be5-99c6-110ebf6a9d38',
            averageGrade: 15.2,
            extraField: 'should not be included',
            anotherOne: 'also ignored'
        };

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockResponse,
        });

        const link = 'http://localhost:8081/courseeditions/ff0ecb58-8b8a-4be5-99c6-110ebf6a9d38/average-grade';

        const result = await fetchAverageGradeFromLink(link);

        expect(Object.keys(result)).toEqual(['courseEditionId', 'averageGrade']);
        expect(result.courseEditionId).toBe(mockResponse.courseEditionId);
        expect(result.averageGrade).toBe(mockResponse.averageGrade);
    });
});
