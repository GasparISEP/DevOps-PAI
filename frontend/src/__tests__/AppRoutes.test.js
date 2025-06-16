import { render, screen } from '@testing-library/react';
import {MemoryRouter, Route} from 'react-router-dom';
import AppRoutes from '../AppRoutes'; // novo ficheiro

describe('Rotas da aplicação', () => {

    test('Renderiza a Home page', () => {
        render(
            <MemoryRouter initialEntries={['/']}>
                <AppRoutes />
            </MemoryRouter>
        );
        expect(screen.getByText(/welcome to isep/i)).toBeInTheDocument();
    });

    test('Renderiza a página de registo de professores', () => {
        render(
            <MemoryRouter initialEntries={['/teachers/register']}>
                <AppRoutes />
            </MemoryRouter>
        );
        expect(screen.getAllByText(/register teacher/i).length).toBeGreaterThan(0);
        expect(screen.getByRole('heading', { name: /register teacher/i })).toBeInTheDocument();
    });

    test('Renderiza a página de visualização de professores', async () => {
        render(
            <MemoryRouter initialEntries={['/teachers/display']}>
                <AppRoutes/>
            </MemoryRouter>
        );
        const heading = await screen.findByText(/Teachers/i);
        expect(heading).toBeInTheDocument();
    });

    test('Renderiza a página de registo de estudantes', () => {
        render(
            <MemoryRouter initialEntries={['/students']}>
                <AppRoutes />
            </MemoryRouter>
        );
        expect(screen.getByText(/register student|new student/i)).toBeInTheDocument();
    });

    test('Renderiza a página de registo de programas', () => {
        render(
            <MemoryRouter initialEntries={['/programmes']}>
                <AppRoutes />
            </MemoryRouter>
        );
        expect(screen.getByText(/register programme|new programme/i)).toBeInTheDocument();
        expect(screen.getByRole('heading', { name: /register programme/i })).toBeInTheDocument();
    });

    test('Renderiza a página de registo de unidades curriculares no plano de estudo', () => {
        render(
            <MemoryRouter initialEntries={['/courses']}>
                <AppRoutes />
            </MemoryRouter>
        );
        expect(
            screen.getByText(/register a course/i)
        ).toBeInTheDocument();
    });
    test('Renderiza a página de unidades curriculares no plano de estudo', () => {
        render(
            <MemoryRouter initialEntries={['/course-editions/define-ruc']}>
                <AppRoutes />
            </MemoryRouter>
        );

        // Check for heading text
        expect(screen.getByText(/define a ruc/i)).toBeInTheDocument();

        // Check for buttons
        expect(screen.getByRole('button', { name: /register/i })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /clear/i })).toBeInTheDocument();

        // Check for link text
        expect(screen.getByText(/back to home page/i)).toBeInTheDocument();
    });
});
