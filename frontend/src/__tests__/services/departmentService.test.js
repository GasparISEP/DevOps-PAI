import { fetchDepartments } from '../../services/departmentService';

describe('fetchDepartments', () => {
    const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8081';

    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should fetch departments and return data', async () => {
        const mockDepartments = [
            { id: '1', name: 'Department A' },
            { id: '2', name: 'Department B' }
        ];
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockDepartments,
        });

        const result = await fetchDepartments();
        expect(global.fetch).toHaveBeenCalledWith(`${apiUrl}/departments`);
        expect(result).toEqual(mockDepartments);
    });

    it('should throw an error if response is not ok', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ([]),
        });
        await expect(fetchDepartments()).rejects.toThrow('Failed to fetch departments');
    });

    it('should throw an error if fetch fails', async () => {
        global.fetch.mockRejectedValueOnce(new Error('Network Error'));
        await expect(fetchDepartments()).rejects.toThrow('Network Error');
    });
});

