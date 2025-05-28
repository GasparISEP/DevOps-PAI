import React from 'react';
import { render, screen, fireEvent, waitFor, act } from '@testing-library/react';
import ProgrammeForm from '../../../components/programmeComponent/ProgrammeForm';
import * as programmeService from '../../../services/programmeService';

jest.mock('../../../services/programmeService');

describe('ProgrammeForm tests', () => {
    beforeEach(() => {
        jest.clearAllMocks();

        global.fetch = jest.fn((url) => {
            if (url.endsWith('/departments')) {
                return Promise.resolve({
                    ok: true,
                    json: () => Promise.resolve([
                        { id: 'DI', name: 'Department 1', acronym: 'DI' },
                        { id: 'DII', name: 'Department 2', acronym: 'DII' },
                    ]),
                });
            }
            if (url.endsWith('/teachers')) {
                return Promise.resolve({
                    ok: true,
                    json: () => Promise.resolve([
                        { id: 'ABC', name: 'Teacher One' },
                        { id: 'CBD', name: 'Teacher Two' },
                    ]),
                });
            }
            if (url.endsWith('/degreetypes')) {
                return Promise.resolve({
                    ok: true,
                    json: () => Promise.resolve([
                        { id: '11111111-1111-1111-1111-111111111111', name: 'Bachelor', maxEcts: 180 },
                        { id: '22222222-2222-2222-2222-222222222222', name: 'Master', maxEcts: 120 },
                    ]),
                });
            }
            return Promise.reject(new Error('Unknown endpoint'));
        });
    });

    afterEach(() => {
        jest.restoreAllMocks();
        delete global.fetch;
    });

    test('submits form successfully and shows success modal', async () => {
        programmeService.registerProgramme.mockResolvedValue({ message: 'Programme created' });

        await act(async () => {
            render(<ProgrammeForm />);
        });

        await waitFor(() => expect(screen.getByLabelText(/degree type/i)).not.toBeDisabled());

        fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'New Programme' } });
        fireEvent.change(screen.getByLabelText(/acronym/i), { target: { value: 'NP' } });
        fireEvent.change(screen.getByLabelText(/semesters/i), { target: { value: '6' } });
        fireEvent.change(screen.getByLabelText(/degree type/i), { target: { value: '11111111-1111-1111-1111-111111111111' } });
        fireEvent.change(screen.getByLabelText(/department/i), { target: { value: 'DI' } });
        fireEvent.change(screen.getByLabelText(/programme's director/i), { target: { value: 'ABC' } });

        await act(async () => {
            fireEvent.click(screen.getByRole('button', { name: /register/i }));
        });

        await waitFor(() => {
            expect(programmeService.registerProgramme).toHaveBeenCalledWith({
                name: 'New Programme',
                acronym: 'NP',
                quantSemesters: 6,
                degreeTypeID: '11111111-1111-1111-1111-111111111111',
                departmentID: 'DI',
                teacherID: 'ABC',
                maxECTS: 180,
            });
            expect(screen.getByText(/success!/i)).toBeInTheDocument();
        });
    });

    test('shows error message when submission fails', async () => {
        programmeService.registerProgramme.mockRejectedValue(new Error('Server error'));

        await act(async () => {
            render(<ProgrammeForm />);
        });

        await waitFor(() => expect(screen.getByLabelText(/degree type/i)).not.toBeDisabled());

        fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'Fail Programme' } });
        fireEvent.change(screen.getByLabelText(/acronym/i), { target: { value: 'FP' } });
        fireEvent.change(screen.getByLabelText(/semesters/i), { target: { value: '4' } });
        fireEvent.change(screen.getByLabelText(/degree type/i), { target: { value: '22222222-2222-2222-2222-222222222222' } });
        fireEvent.change(screen.getByLabelText(/department/i), { target: { value: 'DII' } });
        fireEvent.change(screen.getByLabelText(/programme's director/i), { target: { value: 'CBD' } });

        await act(async () => {
            fireEvent.click(screen.getByRole('button', { name: /register/i }));
        });

        await waitFor(() => {
            expect(programmeService.registerProgramme).toHaveBeenCalled();
            expect(screen.getByText(/server error/i)).toBeInTheDocument();
        });
    });

    test('loads options and populates selects', async () => {
        await act(async () => {
            render(<ProgrammeForm />);
        });

        await waitFor(() => {
            expect(screen.getByRole('option', { name: 'Bachelor' })).toBeInTheDocument();
            expect(screen.getByRole('option', { name: 'Department 1 (DI)' })).toBeInTheDocument();
            expect(screen.getByRole('option', { name: 'Teacher One' })).toBeInTheDocument();
        });
    });

    test('cancel button calls window.history.back()', async () => {
        await act(async () => {
            render(<ProgrammeForm />);
        });

        await waitFor(() => expect(screen.getByLabelText(/degree type/i)).not.toBeDisabled());

        const backSpy = jest.spyOn(window.history, 'back').mockImplementation(() => {});

        fireEvent.click(screen.getByRole('button', { name: /cancel/i }));

        expect(backSpy).toHaveBeenCalled();

        backSpy.mockRestore();
    });

    test('modal closes when clicking Close button', async () => {
        programmeService.registerProgramme.mockResolvedValue({ message: 'Programme created' });

        await act(async () => {
            render(<ProgrammeForm />);
        });

        await waitFor(() => expect(screen.getByLabelText(/degree type/i)).not.toBeDisabled());

        fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'New Programme' } });
        fireEvent.change(screen.getByLabelText(/acronym/i), { target: { value: 'NP' } });
        fireEvent.change(screen.getByLabelText(/semesters/i), { target: { value: '6' } });
        fireEvent.change(screen.getByLabelText(/degree type/i), { target: { value: '11111111-1111-1111-1111-111111111111' } });
        fireEvent.change(screen.getByLabelText(/department/i), { target: { value: 'DI' } });
        fireEvent.change(screen.getByLabelText(/programme's director/i), { target: { value: 'ABC' } });

        await act(async () => {
            fireEvent.click(screen.getByRole('button', { name: /register/i }));
        });

        await waitFor(() => {
            expect(screen.getByText(/success!/i)).toBeInTheDocument();
        });

        fireEvent.click(screen.getByRole('button', { name: /close/i }));

        await waitFor(() => {
            expect(screen.queryByText(/success!/i)).not.toBeInTheDocument();
        });
    });

    test('should log error when fetch fails', async () => {
        global.fetch = jest.fn().mockRejectedValueOnce(new Error('Network error'));

        const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation(() => {});

        render(<ProgrammeForm />);

        await screen.findByText('Register Programme');

        expect(consoleErrorSpy).toHaveBeenCalledWith('Failed to load options:', expect.any(Error));

        consoleErrorSpy.mockRestore();
        global.fetch.mockRestore();
    });
});
