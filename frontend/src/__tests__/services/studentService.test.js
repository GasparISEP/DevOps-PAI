import { registerStudent } from '../../services/studentService';

describe('registerStudent', () => {
    const mockPayload = {
        studentID: "123456",
        name: "Test Student",
        nif: "123456789",
        email: "test@student.com"
        // Adicione outros campos necessários conforme o seu DTO
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
        // De acordo com a implementação, se .json() não for possível num ok, não será tratado como null
        // Então aqui pode-se lançar erro ou tratar como string
        expect(result).toEqual("OK");  // Modifique isto conforme o comportamento esperado
    });
});