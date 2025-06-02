import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import StudentForm from '../../../components/studentComponent/StudentForm';
import PhoneInput from 'react-phone-input-2';

// MOCK de registerStudent e fetch global
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

        // Substituir a linha problemática
        const phoneInput = screen.getByPlaceholderText('1 (702) 123-4567'); // Placeholder default
        fireEvent.change(phoneInput, { target: { value: '+351912345678' } });

        // Postal code vazio
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

        const spainOption = await screen.findByText('Spain');
        await userEvent.click(spainOption);

        expect(screen.getByText('Spain')).toBeInTheDocument();
    });

    test('updates form.addressCountry when a country is selected', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        const placeholder = screen.getByText('Select Country');
        await userEvent.click(placeholder);

        const spainOption = await screen.findByText('Spain');
        await userEvent.click(spainOption);

        expect(screen.getByText('Spain')).toBeInTheDocument();
    });

    test('updates form state on phone input change', async () => {
        render(<MemoryRouter><StudentForm /></MemoryRouter>);

        const input = screen.getByTestId('phone-input');

        await userEvent.clear(input);
        await userEvent.type(input, '912345678');

        expect(input).toHaveValue('912345678');

    });
});