import { registerProgramme} from '../../services/programmeService';

describe('registerProgramme', () => {
    const mockPayload = { name: "Test Programme" };
    const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8081';

    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should successfully register a programme and return response data', async () => {
        const mockResponse = { id: "TP", name: "Test Programme" };

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockResponse,
        });

        const result = await registerProgramme(mockPayload);

        expect(result).toEqual(mockResponse);
    });

    it('should throw an error if response is not ok and has JSON message', async () => {
        const errorMessage = "Programme already exists";

        global.fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({ message: errorMessage }),
            headers: new Headers({ 'Content-Type': 'application/json' })
        });

        await expect(registerProgramme(mockPayload)).rejects.toThrow(errorMessage);
    });

    it('should throw an error with HTTP status if response is not ok and no JSON message', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: false,
            text: async () => "Internal Server Error",
            headers: new Headers({ 'Content-Type': 'text/plain' }),
            status: 500   // <-- esta linha que faltava
        });

        await expect(registerProgramme(mockPayload)).rejects.toThrow("HTTP 500");
    });


    it('should handle non-JSON responses gracefully', async () => {
        const warnSpy = jest.spyOn(console, 'warn').mockImplementation(() => {});

        global.fetch.mockResolvedValueOnce({
            ok: true,
            text: async () => "OK",
            headers: new Headers({ 'Content-Type': 'text/plain' }),
            status: 200
        });

        const result = await registerProgramme(mockPayload);
        expect(result).toBe(null);

        warnSpy.mockRestore();
    });
});
