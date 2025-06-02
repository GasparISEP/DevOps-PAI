import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import StudentForm from '../../../components/studentComponent/StudentForm';
import { registerStudent } from '../../../services/studentService';

jest.mock('../../../services/studentService', () => ({
    registerStudent: jest.fn(),
}));

global.fetch = jest.fn();

jest.mock('react-country-flag', () => ({
    __esModule: true,
    default: ({ countryCode }) => (
        <img alt={countryCode} data-testid="country-flag" />
    ),
}));

jest.mock('react-select-country-list', () => () => ({
    getData: () => [
        { value: 'pt', label: 'Portugal' },
        { value: 'es', label: 'Spain' },
        { value: 'fr', label: 'France' },
    ],
}));

describe('StudentForm Tests', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders StudentForm correctly', () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);
        expect(screen.getByText(/Register Student/i)).toBeInTheDocument();
    });

    test('accepts postal code with letters and numbers', () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);
        const postalInput = screen.getByLabelText(/Postal Code/i);
        fireEvent.change(postalInput, { target: { value: '4000-123A' } });
        expect(postalInput.value).toBe('4000-123A');
    });

    test('shows error if phone number is missing', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);
        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'Ana' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '123456789' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Rua 1' } });
        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '4000-123' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'ana@test.com' } });

        fireEvent.click(screen.getByRole('button', { name: /register/i }));

        await waitFor(() => {
            expect(screen.getByText(/⚠️ Enter a valid phone number/i)).toBeInTheDocument();
        });
    });

    test('shows error if postal code is empty', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);
        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'Ana' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '123456789' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Rua 1' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'ana@test.com' } });

        const phoneInput = screen.getByPlaceholderText('1 (702) 123-4567');
        fireEvent.change(phoneInput, { target: { value: '+351912345678' } });

        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '' } });

        fireEvent.click(screen.getByRole('button', { name: /register/i }));

        await waitFor(() => {
            expect(screen.getByText(/⚠️ Enter a valid postal code\./i)).toBeInTheDocument();
        });
    });

    test('clear button reloads the page', () => {
        const originalLocation = window.location;
        delete window.location;
        window.location = { reload: jest.fn() };

        render(<MemoryRouter><StudentForm /></MemoryRouter>);
        fireEvent.click(screen.getByRole('button', { name: /clear/i }));

        expect(window.location.reload).toHaveBeenCalled();

        window.location = originalLocation;
    });

    test('updates form.nifcountry when a country is selected', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        const placeholder = screen.getByText('Select Countries');
        await userEvent.click(placeholder);

        const portugalOptions = await screen.findAllByText('Portugal');
        await userEvent.click(portugalOptions[0]);

        expect(screen.getAllByText('Portugal')[0]).toBeInTheDocument();
    });

    test('updates form.addressCountry when a country is selected', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        const placeholder = screen.getByText('Select Country');
        await userEvent.click(placeholder);

        const portugalOptions = await screen.findAllByText('Portugal');
        await userEvent.click(portugalOptions[0]);

        expect(screen.getAllByText('Portugal')[0]).toBeInTheDocument();
    });

    test('fills form and calls registerStudent on submit', async () => {
        global.fetch.mockResolvedValue({
            ok: true,
            json: async () => ({ lastStudentID: 99 }),
        });

        registerStudent.mockResolvedValue({ message: 'Student registered successfully' });

        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'John Doe' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '123456789' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Rua X' } });
        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '1234-567' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'john@example.com' } });

        const selects = screen.getAllByRole('combobox');
        fireEvent.keyDown(selects[0], { key: 'ArrowDown' });
        fireEvent.keyDown(selects[0], { key: 'Enter' });
        fireEvent.keyDown(selects[1], { key: 'ArrowDown' });
        fireEvent.keyDown(selects[1], { key: 'Enter' });

        const phoneInput = screen.getByPlaceholderText('1 (702) 123-4567');
        await userEvent.clear(phoneInput);
        await userEvent.type(phoneInput, '+351912345678');
        fireEvent.blur(phoneInput);

        fireEvent.click(screen.getByRole('button', { name: /^REGISTER$/i }));

        await waitFor(() => {
            expect(registerStudent).toHaveBeenCalledTimes(1);
        });
    });
});
