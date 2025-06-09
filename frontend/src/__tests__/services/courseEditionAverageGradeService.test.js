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

    it('should fetch average grade and return data', async () => {
        const mockData = {
            courseEditionId: 'PAI-2324',
            programmeAcronym: 'LESI',
            schoolYear: '2023/2024',
            averageGrade: 15.2
        };

        const link = 'http://localhost:8081/courseeditions/PAI-2324/average-grade';

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockData,
        });

        const result = await fetchAverageGradeFromLink(link);
        expect(global.fetch).toHaveBeenCalledWith(link);
        expect(result).toEqual(mockData);
    });

    it('should throw an error if response is not ok', async () => {
        const link = 'http://localhost:8081/courseeditions/INVALID/average-grade';

        global.fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({}),
        });

        await expect(fetchAverageGradeFromLink(link)).rejects.toThrow('Failed to fetch average grade');
    });

    it('should throw an error if fetch fails', async () => {
        const link = 'http://localhost:8081/courseeditions/ERROR/average-grade';

        global.fetch.mockRejectedValueOnce(new Error('Network Error'));

        await expect(fetchAverageGradeFromLink(link)).rejects.toThrow('Network Error');
    });

    it('should throw an error if response.json() fails', async () => {
        const link = 'http://localhost:8081/courseeditions/PAI-2324/average-grade';

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => { throw new Error('JSON parse error'); }
        });

        await expect(fetchAverageGradeFromLink(link)).rejects.toThrow('JSON parse error');
    });
});
