import { updateTeacherCategory } from '../../services/updateTeacherCategoryService';

describe('updateTeacherCategory', () => {
    const originalFetch = global.fetch;
    const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

    afterEach(() => {
        global.fetch = originalFetch;
        jest.clearAllMocks();
    });

    it('should call fetch with correct URL and payload', async () => {
        const payload = {
            teacher: '123',
            teacherCategory: '456',
            date: '2025-06-01'
        };

        const mockResponseData = {
            id: '789',
            name: 'Category Name',
            _links: {
                self: { href: `${API_URL}/teachers/123/careerprogressions/category/789` },
                all: { href: `${API_URL}/teachers/123/careerprogressions/category` }
            }
        };

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockResponseData),
            })
        );

        const result = await updateTeacherCategory(payload);

        expect(global.fetch).toHaveBeenCalledWith(
            `${API_URL}/teachers/123/careerprogressions/category`,
            expect.objectContaining({
                method: 'POST',
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify({
                    teacherCategoryID: '456',
                    date: '2025-06-01'
                }),
            })
        );

        expect(result).toEqual({
            data: mockResponseData,
            links: {
                self: mockResponseData._links.self.href,
                all: mockResponseData._links.all.href
            }
        });
    });

    it('should throw error with message from response on failure', async () => {
        const payload = {
            teacher: '123',
            teacherCategory: '456',
            date: '2025-06-01'
        };

        const errorMessage = { message: 'Error occurred' };

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: false,
                status: 400,
                json: () => Promise.resolve(errorMessage),
            })
        );

        await expect(updateTeacherCategory(payload)).rejects.toThrow('Error occurred');
    });

    it('should throw generic error if no message in error response', async () => {
        const payload = {
            teacher: '123',
            teacherCategory: '456',
            date: '2025-06-01'
        };

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: false,
                status: 500,
                json: () => Promise.resolve({}),
            })
        );

        await expect(updateTeacherCategory(payload)).rejects.toThrow('HTTP 500');
    });

    it('should handle response without JSON body gracefully', async () => {
        const payload = {
            teacher: '123',
            teacherCategory: '456',
            date: '2025-06-01'
        };

        const mockResponseData = {
            id: '789',
            name: 'Category Name',
            _links: {
                self: { href: `${API_URL}/teachers/123/careerprogressions/category/789` },
                all: { href: `${API_URL}/teachers/123/careerprogressions/category` }
            }
        };

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => { throw new Error('No JSON'); },
            })
        );

        // Spy console.warn to check if warning is triggered
        const warnSpy = jest.spyOn(console, 'warn').mockImplementation(() => {});

        const result = await updateTeacherCategory(payload);

        expect(warnSpy).toHaveBeenCalledWith('Response without JSON body.');
        expect(result).toEqual({
            data: null,
            links: {
                self: null,
                all: null
            }
        });

        warnSpy.mockRestore();
    });
});
