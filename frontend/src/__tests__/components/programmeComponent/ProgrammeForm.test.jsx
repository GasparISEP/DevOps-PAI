import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import ProgrammeForm from '../../../components/programmeComponent/ProgrammeForm';
import * as programmeService from '../../../services/programmeService';

jest.mock('../../../services/programmeService', () => ({
    registerProgramme: jest.fn()
}));

jest.mock('../../../assets/images/ISEP_logo-branco.png', () => 'logo.png');
jest.mock('../../../styles/Form.css', () => {});

// Preserve and restore globals to avoid cross-test pollution
let originalFetch;
let originalConsoleError;

beforeAll(() => {
    originalFetch = global.fetch;
    originalConsoleError = console.error;
});
afterEach(() => {
    jest.clearAllMocks();
    global.fetch = originalFetch;
    console.error = originalConsoleError;
});

const defaultFetch = (mocks) => {
    if (mocks?.error) {
        global.fetch = jest.fn().mockRejectedValue(new Error('Fetch failed'));
        return;
    }
    const departments = mocks?.departments || [{ id: 1, name: 'Dept A', acronym: 'DA' }];
    const teachers = mocks?.teachers || [{ id: 2, name: 'Teacher B' }];
    const degreeTypes = mocks?.degreeTypes || [{ id: 3, name: 'Degree C', maxEcts: 90 }];
    global.fetch = jest.fn()
        .mockResolvedValueOnce({ json: () => Promise.resolve(departments) })
        .mockResolvedValueOnce({ json: () => Promise.resolve(teachers) })
        .mockResolvedValueOnce({ json: () => Promise.resolve(degreeTypes) });
};

const routerProps = { future: { v7_startTransition: true, v7_relativeSplatPath: true } };

describe('ProgrammeForm', () => {
    test('logs error on fetch failure', async () => {
        console.error = jest.fn();
        defaultFetch({ error: true });
        render(
            <MemoryRouter {...routerProps}>
                <ProgrammeForm />
            </MemoryRouter>
        );
        await waitFor(() => {
            expect(console.error).toHaveBeenCalledWith(
                expect.stringContaining('Failed to load options:'), expect.any(Error)
            );
        });
    });

    test('reset maxEcts and quantSemesters on invalid degree selection', async () => {
        defaultFetch();
        render(
            <MemoryRouter {...routerProps}>
                <ProgrammeForm />
            </MemoryRouter>
        );
        await screen.findByText('Degree C');
        fireEvent.change(screen.getByLabelText('Degree Type'), { target: { value: '999' } });
        expect(screen.queryByText(/Semesters:/)).not.toBeInTheDocument();
        expect(screen.queryByText(/Max ECTS:/)).not.toBeInTheDocument();
    });

    test('close success modal', async () => {
        defaultFetch();
        programmeService.registerProgramme.mockResolvedValue({ id: 1, name: 'X' });
        render(
            <MemoryRouter {...routerProps}>
                <ProgrammeForm />
            </MemoryRouter>
        );
        await screen.findByText('Degree C');
        fireEvent.change(screen.getByLabelText('Degree Type'), { target: { value: '3' } });
        fireEvent.change(screen.getByLabelText('Department'), { target: { value: '1' } });
        fireEvent.change(screen.getByLabelText("Programme's Director"), { target: { value: '2' } });
        fireEvent.change(screen.getByLabelText('Name'), { target: { value: 'Test' } });
        fireEvent.change(screen.getByLabelText('Acronym'), { target: { value: 'T' } });
        fireEvent.click(screen.getByText('REGISTER'));
        const okButton = await screen.findByRole('button', { name: /close/i });
        fireEvent.click(okButton);
        await waitFor(() => expect(okButton).not.toBeInTheDocument());
    });

    test('close error modal', async () => {
        defaultFetch();
        programmeService.registerProgramme.mockRejectedValue(new Error('Err'));
        render(
            <MemoryRouter {...routerProps}>
                <ProgrammeForm />
            </MemoryRouter>
        );
        await screen.findByText('Degree C');
        fireEvent.change(screen.getByLabelText('Degree Type'), { target: { value: '3' } });
        fireEvent.click(screen.getByText('REGISTER'));
        const closeBtn = await screen.findByRole('button', { name: /close/i });
        fireEvent.click(closeBtn);
        await waitFor(() => expect(closeBtn).not.toBeInTheDocument());
    });

    test('updates department, teacher and text inputs', async () => {
        defaultFetch();
        render(
            <MemoryRouter {...routerProps}>
                <ProgrammeForm />
            </MemoryRouter>
        );
        await screen.findByText('Degree C');
        fireEvent.change(screen.getByLabelText('Department'), { target: { value: '1' } });
        expect(screen.getByLabelText('Department').value).toBe('1');
        fireEvent.change(screen.getByLabelText("Programme's Director"), { target: { value: '2' } });
        expect(screen.getByLabelText("Programme's Director").value).toBe('2');
        fireEvent.change(screen.getByLabelText('Name'), { target: { value: 'New Name' } });
        fireEvent.change(screen.getByLabelText('Acronym'), { target: { value: 'NN' } });
        expect(screen.getByLabelText('Name').value).toBe('New Name');
        expect(screen.getByLabelText('Acronym').value).toBe('NN');
        fireEvent.click(screen.getByText('CLEAR'));
        expect(screen.getByLabelText('Department').value).toBe('');
        expect(screen.getByLabelText("Programme's Director").value).toBe('');
        expect(screen.getByLabelText('Name').value).toBe('');
        expect(screen.getByLabelText('Acronym').value).toBe('');
    });

    test('submit without selecting degree type shows error modal', async () => {
        defaultFetch();
        render(
            <MemoryRouter {...routerProps}>
                <ProgrammeForm />
            </MemoryRouter>
        );
        fireEvent.click(screen.getByText('REGISTER'));
        const errModal = await screen.findByText('Degree Type not selected or invalid.');
        expect(errModal).toBeInTheDocument();
        const closeBtn = screen.getByRole('button', { name: /close/i });
        fireEvent.click(closeBtn);
        await waitFor(() => expect(closeBtn).not.toBeInTheDocument());
    });
});
