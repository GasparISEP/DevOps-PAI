import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import TeacherForm from '../../../components/teacherComponent/TeacherForm';
import { registerTeacher } from '../../../services/teacherService';
import { fetchDepartments } from '../../../services/departmentService';
import { MemoryRouter } from 'react-router-dom';

jest.mock('../../../services/teacherService');
jest.mock('../../../services/departmentService');

describe('TeacherForm Tests', () => {

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
        expect(nameInput.value).toBe('John');
    });

    test('acronym input allows max 3 uppercase letters', () => {
        render(
            <MemoryRouter>
                <TeacherForm />
            </MemoryRouter>
        );
        const acronymInput = screen.getByLabelText(/Acronym/i);
        fireEvent.change(acronymInput, { target: { value: 'ABC123' } });
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

        const closeButton = screen.getByRole('button', { name: /close/i });
        fireEvent.click(closeButton);

        await waitFor(() => {
            expect(screen.queryByText(/The teacher was registered successfully/i)).not.toBeInTheDocument();
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

        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'John Doe' } });
        fireEvent.change(screen.getByLabelText(/Acronym/i), { target: { value: 'ABC' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'john@example.com' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '123456789' } });
        fireEvent.change(screen.getByLabelText(/Academic Background/i), { target: { value: 'PhD' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Main St' } });
        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '1234-567' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
        fireEvent.change(screen.getByLabelText(/Department/i), { target: { value: '' } });

        fireEvent.click(screen.getByRole('button', { name: /register/i }));

        await waitFor(() => {
            expect(screen.getByText(/Registration failed/i)).toBeInTheDocument();
            expect(registerTeacher).toHaveBeenCalled();
        });

        const closeButton = screen.getByRole('button', { name: /close/i });
        fireEvent.click(closeButton);

        await waitFor(() => {
            expect(screen.queryByText(/Registration failed/i)).not.toBeInTheDocument();
        });
    });

    test('updates phone number and country code on PhoneInput change', () => {
        render(
            <MemoryRouter>
                <TeacherForm />
            </MemoryRouter>
        );
        const phoneInput = screen.getByPlaceholderText(/Enter phone number/i);

        fireEvent.change(phoneInput, { target: { value: '+351912345678' } });

        expect(phoneInput.value).toContain('+351 912 345 678');
    });

    test('clear button resets the form', async () => {
        fetchDepartments.mockResolvedValueOnce([{ id: 'D1', name: 'CS' }]);

        render(
            <MemoryRouter>
                <TeacherForm />
            </MemoryRouter>
        );

        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'Maria' } });
        fireEvent.click(screen.getByText(/CLEAR/i));

        expect(screen.getByLabelText(/Name/i).value).toBe('');
    });

    test('loads departments on mount successfully', async () => {
        const mockDepartments = [{ id: 'D1', name: 'Computer Science' }];
        fetchDepartments.mockResolvedValueOnce(mockDepartments);

        render(
            <MemoryRouter>
                <TeacherForm />
            </MemoryRouter>
        );

        await waitFor(() => {
            expect(fetchDepartments).toHaveBeenCalledTimes(1);
            expect(screen.getByText('Computer Science')).toBeInTheDocument();
        });
    });

    test('loads departments and displays in select dropdown', async () => {
        const mockDepartments = [{ id: 'D1', name: 'CS' }];
        fetchDepartments.mockResolvedValueOnce(mockDepartments);

        render(
            <MemoryRouter>
                <TeacherForm />
            </MemoryRouter>
        );

        const select = screen.getByText(/Select Department/i);
        fireEvent.keyDown(select, { key: 'ArrowDown' });

        await waitFor(() => {
            expect(screen.getByText('CS')).toBeInTheDocument();
        });
    });

    test('handles error when loading departments fails', async () => {
        const consoleSpy = jest.spyOn(console, 'error').mockImplementation(() => {});
        fetchDepartments.mockRejectedValueOnce(new Error('API is down'));

        render(
            <MemoryRouter>
                <TeacherForm />
            </MemoryRouter>
        );

        await waitFor(() => {
            expect(fetchDepartments).toHaveBeenCalledTimes(1);
        });

        expect(consoleSpy).toHaveBeenCalledWith(
            'Failed to load departments:',
            expect.any(Error)
        );

        consoleSpy.mockRestore();
    });

    test('renders country select and allows selecting a country', async () => {
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

        const comboboxes = screen.getAllByRole('combobox');
        let countrySelectInput = Array.from(comboboxes).find(input =>
            input.getAttribute('aria-label') === 'Country' ||
            input.getAttribute('placeholder') === 'Select Country'
        );
        if (!countrySelectInput) countrySelectInput = comboboxes[0];
        fireEvent.focus(countrySelectInput);
        fireEvent.change(countrySelectInput, { target: { value: 'Portugal' } });
        fireEvent.keyDown(countrySelectInput, { key: 'ArrowDown' });
        const portugalOption = await screen.findByText('Portugal');
        fireEvent.click(portugalOption);
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
        fireEvent.change(screen.getByLabelText(/Department/i), { target: { value: 'D1' } });

        fireEvent.click(screen.getByRole('button', { name: /register/i }));

        await waitFor(() => {
            expect(screen.getByText(/The teacher was registered successfully/i)).toBeInTheDocument();
            expect(registerTeacher).toHaveBeenCalled();
        });
    });

});
