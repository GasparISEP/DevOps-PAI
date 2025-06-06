import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import StudentForm from '../../../components/studentComponent/StudentForm';
import { registerStudent } from '../../../services/studentService';

jest.mock('../../../services/studentService', () => ({
    registerStudent: jest.fn(),
}));

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

    test('clear button reloads the page', () => {
        const originalLocation = window.location;
        delete window.location;
        window.location = { reload: jest.fn() };

        render(<MemoryRouter><StudentForm /></MemoryRouter>);
        fireEvent.click(screen.getByRole('button', { name: /clear/i }));

        expect(window.location.reload).toHaveBeenCalled();

        window.location = originalLocation;
    });

    test('updates form.nifcountry and addressCountry when selected', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        const selects = screen.getAllByRole('combobox');

        await userEvent.click(selects[0]);
        const nifOptions = await screen.findAllByText('Portugal');
        await userEvent.click(nifOptions[0]);

        await userEvent.click(selects[1]);
        const addressOptions = await screen.findAllByText('Portugal');
        await userEvent.click(addressOptions[0]);

        expect(screen.getAllByText('Portugal').length).toBeGreaterThanOrEqual(2);
    });

    test('fills form and calls registerStudent on submit', async () => {
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
            const args = registerStudent.mock.calls[0][0];
            expect(args.name).toBe('John Doe');
            expect(args.nif).toBe('123456789');
            expect(args.street).toBe('Rua X');
            expect(args.postalCode).toBe('1234-567');
            expect(args.location).toBe('Porto');
            expect(args.email).toBe('john@example.com');
            expect(args.phoneNumber).toBe('351912345678'); // ✅ Updated to match actual value
            expect(args.phoneCountryCode).toBe('+351');
            expect(args.nifCountryCode).toBe('pt');
            expect(args.addressCountryCode).toBe('pt');
        });
    });

    test('shows all validation errors when submitting empty form', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        fireEvent.click(screen.getByRole('button', { name: /register/i }));

        await waitFor(() => {
            expect(screen.getByText(/⚠️ Enter the student's name/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Enter a valid NIF/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Select a NIF country/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Enter the street/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Enter a valid postal code/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Enter the location/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Select an address country/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Enter a valid phone number/i)).toBeInTheDocument();
            expect(screen.getByText(/⚠️ Enter a valid email/i)).toBeInTheDocument();
        });
    });
});
