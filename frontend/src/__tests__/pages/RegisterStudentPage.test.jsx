import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from "react-router-dom";
import RegisterStudentPage from '../../pages/RegisterStudentPage';

test('renders RegisterStudentPage with NavBar, StudentForm and Footer', async () => {
    render(
        <MemoryRouter
            future={{
                v7_startTransition: true,
                v7_relativeSplatPath: true,
            }}
        >
            <RegisterStudentPage />
        </MemoryRouter>
    );

    expect(screen.getByRole('navigation')).toBeInTheDocument();

    expect(await screen.findByRole('heading', { name: /register student/i })).toBeInTheDocument();

    expect(screen.getByLabelText(/name/i)).toBeInTheDocument();

    expect(screen.getByRole('contentinfo')).toBeInTheDocument();

});