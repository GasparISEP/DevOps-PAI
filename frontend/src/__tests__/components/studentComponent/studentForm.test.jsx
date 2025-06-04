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

    test('shows error modal if fetch fails before registering', async () => {
        global.fetch.mockResolvedValue({
            ok: false,
            status: 500,
            text: async () => 'Server error',
        });

        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'Jane' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '987654321' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'A Street' } });
        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '1234-567' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Lisbon' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'jane@example.com' } });

        // Select countries
        const selects = screen.getAllByRole('combobox');
        fireEvent.keyDown(selects[0], { key: 'ArrowDown' });
        fireEvent.keyDown(selects[0], { key: 'Enter' });
        fireEvent.keyDown(selects[1], { key: 'ArrowDown' });
        fireEvent.keyDown(selects[1], { key: 'Enter' });

        // Phone input
        const phoneInput = screen.getByPlaceholderText('1 (702) 123-4567');
        await userEvent.clear(phoneInput);
        await userEvent.type(phoneInput, '+351912345678');
        fireEvent.blur(phoneInput);

        fireEvent.click(screen.getByRole('button', { name: /^REGISTER$/i }));

        await waitFor(() => {
            expect(screen.getByText(/Registration Error/i)).toBeInTheDocument();
            expect(screen.getByText(/HTTP 500 - Server error/i)).toBeInTheDocument();
        });
    });

    test('closes success modal and reloads page on Close button click', async () => {
        // Save original location
        const originalLocation = window.location;

        // Replace window.location with a mock object
        delete window.location;
        window.location = {
            ...originalLocation,
            reload: jest.fn()
        };

        global.fetch.mockResolvedValue({
            ok: true,
            json: async () => ({ lastStudentID: 456 }),
        });

        registerStudent.mockResolvedValue({
            name: 'Mario',
            nif: '111222333',
            nifcountry: 'Portugal',
            street: 'Rua X',
            postalCode: '1000-001',
            location: 'Lisbon',
            addressCountry: 'Portugal',
            phoneNumber: '912345678',
            email: 'mario@example.com'
        });

        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'Mario' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '111222333' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Rua X' } });
        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '1000-001' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Lisbon' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'mario@example.com' } });

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

        const closeButton = await screen.findByRole('button', { name: /Close/i });
        fireEvent.click(closeButton);

        expect(window.location.reload).toHaveBeenCalled();

        // Restore original location after test
        window.location = originalLocation;
    });
    test('closes error modal when clicking Close', async () => {
        global.fetch.mockResolvedValue({
            ok: false,
            status: 500,
            text: async () => 'Server failure',
        });

        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'Ana' } });
        fireEvent.change(screen.getByLabelText(/NIF/i), { target: { value: '999888777' } });
        fireEvent.change(screen.getByLabelText(/Street/i), { target: { value: 'Rua Y' } });
        fireEvent.change(screen.getByLabelText(/Postal Code/i), { target: { value: '4000-123' } });
        fireEvent.change(screen.getByLabelText(/Location/i), { target: { value: 'Porto' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'ana@example.com' } });

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

        const closeErrorBtn = await screen.findByRole('button', { name: /Close/i });
        fireEvent.click(closeErrorBtn);

        // Optional: assert that modal is gone
        await waitFor(() => {
            expect(screen.queryByText(/Registration Error/i)).not.toBeInTheDocument();
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
