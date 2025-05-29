import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import RegisterProgrammePage from '../../pages/RegisterProgrammePage';

beforeEach(() => {
    jest.spyOn(global, 'fetch').mockImplementation((url) => {
        if (url.endsWith('/departments')) {
            return Promise.resolve({ json: () => Promise.resolve([{ id: 'dept1', name: 'Dept 1' }]) });
        }
        if (url.endsWith('/teachers')) {
            return Promise.resolve({ json: () => Promise.resolve([{ id: 'teacher1', name: 'Teacher 1' }]) });
        }
        if (url.endsWith('/degreetypes')) {
            return Promise.resolve({ json: () => Promise.resolve([{ id: 'degree1', name: 'Degree 1', maxEcts: 180 }]) });
        }
        return Promise.reject(new Error('Unknown endpoint'));
    });
});

afterEach(() => {
    global.fetch.mockRestore();
});

test('renders RegisterProgrammePage with NavBar, ProgrammeForm and Footer', async () => {
    render(
        <MemoryRouter
            future={{
                v7_startTransition: true,
                v7_relativeSplatPath: true,
            }}
        >
            <RegisterProgrammePage />
        </MemoryRouter>
    );

    await waitFor(() => {
        expect(screen.getByRole('heading', { name: /programme/i })).toBeInTheDocument();
    });

    await waitFor(() => {
        expect(screen.getByRole('option', { name: /degree 1/i })).toBeInTheDocument();
    });

    await waitFor(() => {
        expect(screen.getByRole('heading', { name: /register programme/i })).toBeInTheDocument();
    });

    await waitFor(() => {
        expect(screen.getByRole('contentinfo')).toBeInTheDocument();
    });
});
