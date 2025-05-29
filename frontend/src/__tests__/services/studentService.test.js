import { registerStudent } from '../../services/studentService';

describe('registerStudent', () => {
    const mockPayload = {
        studentID: "123456",
        name: "Test Student",
        nif: "123456789",
        email: "test@student.com"

    };
    const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8081';

    beforeEach(() => {
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('deve registar o estudante com sucesso e devolver os dados da resposta', async () => {
        const mockResponse = {
            id: "123456",
            name: "Test Student",
            email: "test@student.com"
        };

        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockResponse,
            headers: {
               get: (key) => {
                   if (key.toLocaleLowerCase() === 'content-type') {
                       return 'application/json';
                   }
                   return null;
               }
            }
        });

        const result = await registerStudent(mockPayload);
        expect(result).toEqual(mockResponse);
        expect(global.fetch).toHaveBeenCalledWith(
            `${apiUrl}/students`,
            expect.objectContaining({
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(mockPayload)
            })
        );
    });

    it('deve lançar um erro se a resposta não for ok e contiver message', async () => {
        const errorMessage = "Estudante já existe";

        global.fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({ message: errorMessage }),
            headers: new Headers({ 'Content-Type': 'application/json' }),
            status: 400
        });

        await expect(registerStudent(mockPayload)).rejects.toThrow(errorMessage);
    });

    it('deve lançar um erro com HTTP status se a resposta não for ok e sem message', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: false,
            json: async () => ({}),
            headers: new Headers({ 'Content-Type': 'application/json' }),
            status: 500
        });

        await expect(registerStudent(mockPayload)).rejects.toThrow('HTTP 500');
    });

    it('deve tratar respostas não-JSON de forma adequada', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: true,
            text: async () => "OK",
            headers: new Headers({ 'Content-Type': 'text/plain' }),
            status: 200
        });

        const result = await registerStudent(mockPayload);

        expect(result).toEqual("OK");
    });

    it('deve lançar um erro com texto simples se a resposta não for JSON e não for ok', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: false,
            text: async () => 'Erro genérico do servidor',
            headers: {
                get: (key) => {
                    if (key.toLowerCase() === 'content-type') {
                        return 'text/plain'; // Not JSON
                    }
                    return null;
                }
            },
            status: 400
        });

        await expect(registerStudent(mockPayload)).rejects.toThrow('Erro genérico do servidor');
    });

    it('deve tratar erro mesmo sem header Content-Type', async () => {
        global.fetch.mockResolvedValueOnce({
            ok: false,
            text: async () => 'Erro sem header',
            headers: {
                get: () => null
            },
            status: 500
        });

        await expect(registerStudent(mockPayload)).rejects.toThrow('Erro sem header');
    });
});