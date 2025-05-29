import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import TeacherForm from '../../../components/teacherComponent/TeacherForm';
import { registerTeacher } from '../../../services/teacherService';
import { fetchDepartments } from '../../../services/departmentService';

jest.mock('../../../services/teacherService');
jest.mock('../../../services/departmentService');

import { MemoryRouter } from 'react-router-dom';

// Render Test
test('renders TeacherForm correctly', async () => {
    fetchDepartments.mockResolvedValueOnce([
        { id: 'D1', name: 'Computer Science' },
    ]);

    render(
        <MemoryRouter>
            <TeacherForm />
        </MemoryRouter>
    );

    expect(screen.getByText(/Register Teacher/i)).toBeInTheDocument();
    expect(await screen.findByText(/Computer Science/i)).toBeInTheDocument();
});

// Controlled Input
test('name input only allows letters and spaces', () => {
    render(
        <MemoryRouter>
            <TeacherForm />
        </MemoryRouter>
    );
    const nameInput = screen.getByLabelText(/Name/i);
    fireEvent.change(nameInput, { target: { value: 'John123' } });
    expect(nameInput.value).toBe('John'); // "123" não permitido
});

test('acronym input allows max 3 uppercase letters', () => {
    render(
        <MemoryRouter>
            <TeacherForm />
        </MemoryRouter>
    );
    const acronymInput = screen.getByLabelText(/Acronym/i);
    fireEvent.change(acronymInput, { target: { value: 'abcd' } });
    expect(acronymInput.value).toBe('ABC');
});

// Submission with success
test('submits form successfully and shows modal', async () => {
    fetchDepartments.mockResolvedValueOnce([{ id: 'D1', name: 'CS' }]);
    registerTeacher.mockResolvedValueOnce({
        id: 'ABC',
        name: 'John Doe',
        email: 'john@example.com',
        nif: '123456789',
        academicBackground: 'PhD',
        street: 'Main St',
        postalCode: '1234-567',
        location: 'Porto',
        country: 'Portugal',
        countryCode: '+351',
        phoneNumber: '912345678',
        departmentID: 'D1'
    });

    render(
        <MemoryRouter>
            <TeacherForm />
        </MemoryRouter>
    );

    fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'John Doe' } });
    fireEvent.change(screen.getByLabelText(/Acronym/i), { target: { value: 'ABC' } });
    fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'john@example.com' } });
    fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '123456789' } });
    fireEvent.change(screen.getByLabelText(/Academic Background/i), { target: { value: 'PhD' } });
    fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Main St' } });
    fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '1234-567' } });
    fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
    fireEvent.change(screen.getByLabelText(/Department/i), { target: { value: 'D1' } });

    fireEvent.click(screen.getByRole('button', { name: /register/i }));

    await waitFor(() => {
        expect(screen.getByText(/The teacher was registered successfully/i)).toBeInTheDocument();
        expect(registerTeacher).toHaveBeenCalled();
    });
});

//Submission Error
test('shows error modal on submission failure', async () => {
    fetchDepartments.mockResolvedValueOnce([]);
    registerTeacher.mockRejectedValueOnce(new Error('Registration failed'));

    render(
        <MemoryRouter>
            <TeacherForm />
        </MemoryRouter>
    );

    // preencher só o necessário para o teste
    fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'John Doe' } });
    fireEvent.change(screen.getByLabelText(/Acronym/i), { target: { value: 'ABC' } });
    fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'john@example.com' } });
    fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '123456789' } });
    fireEvent.change(screen.getByLabelText(/Academic Background/i), { target: { value: 'PhD' } });
    fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Main St' } });
    fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '1234-567' } });
    fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });

    fireEvent.click(screen.getByRole('button', { name: /register/i }));

    await waitFor(() => {
        expect(screen.getByText(/Registration Error/i)).toBeInTheDocument();
        expect(screen.getByText(/Registration failed/i)).toBeInTheDocument();
    });
});





