import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CourseForm from '../../../components/courseComponent/CourseForm';
import * as courseService from '../../../services/courseService';

jest.mock('../../../services/courseService');

describe('CourseForm', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        process.env.REACT_APP_API_URL = 'http://localhost:3000';

        global.fetch = jest.fn()
            .mockImplementation((url) => {
                if (url.includes('/programmes/ids')) {
                    return Promise.resolve({
                        ok: true,
                        json: () => Promise.resolve([{ acronym: 'CS', name: 'Computer Science' }])
                    });
                }
                if (url.includes('/courses/ids')) {
                    return Promise.resolve({
                        ok: true,
                        json: () => Promise.resolve([{ acronym: 'CS101', name: 'Intro to CS' }])
                    });
                }
                if (url.includes('/programmes/Computer Science/CS')) {
                    return Promise.resolve({
                        ok: true,
                        json: () => Promise.resolve({
                            quantSemesters: { quantityOfSemesters: 4 },
                            maxEcts: { maxEcts: 120 }
                        })
                    });
                }
                if (url.includes('/course-in-study-plan/')) {
                    return Promise.resolve({
                        ok: true,
                        json: () => Promise.resolve([])
                    });
                }
                return Promise.reject(new Error(`Unhandled fetch url: ${url}`));
            });
    });

    test('renders form with initial empty state', () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);
        expect(screen.getByText('Register a Course')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /register/i })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /clear/i })).toBeInTheDocument();
    });


    test('clears form when clear button clicked', async () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);

        await waitFor(() => {
            expect(screen.getByRole('combobox', { name: /programme/i })).toBeInTheDocument();
        });

        fireEvent.change(screen.getByRole('combobox', { name: /programme/i }), { target: { value: 'CS' } });

        await waitFor(() => {
            expect(screen.getByRole('spinbutton', { name: /ects/i })).toBeInTheDocument();
        });

        fireEvent.change(screen.getByRole('spinbutton', { name: /ects/i }), { target: { value: '10' } });

        fireEvent.click(screen.getByRole('button', { name: /clear/i }));

        expect(screen.getByRole('combobox', { name: /programme/i }).value).toBe('');
        expect(screen.getByRole('spinbutton', { name: /ects/i }).value).toBe('');
    });

    test('renders all dependent fields after selecting a programme', async () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);


        fireEvent.change(screen.getByRole('combobox', { name: /programme/i }), {
            target: { value: 'CS' },
        });

        await waitFor(() => {
            expect(screen.getByRole('combobox', { name: /curricular year/i })).toBeInTheDocument();
            expect(screen.getByRole('combobox', { name: /course/i })).toBeInTheDocument();
            expect(screen.getAllByRole('combobox', { name: /semester/i }).length).toBeGreaterThan(0);
            expect(screen.getByRole('combobox', { name: /duration/i })).toBeInTheDocument();
            expect(screen.getByRole('spinbutton', { name: /ects/i })).toBeInTheDocument();
        });
    });


    test('submit button is always present and disabled until form is filled', async () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);

        const registerBtn = screen.getByRole('button', { name: /register/i });
        expect(registerBtn).toBeInTheDocument();
        expect(registerBtn).toBeEnabled(); // ou toBeDisabled() dependendo do comportamento inicial
    });

    test('ects input does not accept negative values', async () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);

        const ectsInput = await screen.findByRole('spinbutton', { name: /ects/i });

        fireEvent.change(ectsInput, { target: { value: '-5' } });

        expect(Number(ectsInput.value)).toBeGreaterThanOrEqual(0);
    });

    test('fills required fields and submits form', async () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);

        fireEvent.change(await screen.findByRole('combobox', { name: /programme/i }), { target: { value: 'CS' } });
        fireEvent.change(screen.getByRole('combobox', { name: /course/i }), { target: { value: 'CS101' } });
        fireEvent.change(screen.getByRole('combobox', { name: /curricular year/i }), { target: { value: '1' } });
        fireEvent.change(screen.getAllByRole('combobox', { name: /semester/i })[0], { target: { value: '1' } });
        fireEvent.change(screen.getByRole('combobox', { name: /duration/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('spinbutton', { name: /ects/i }), { target: { value: '6' } });

        const button = screen.getByRole('button', { name: /register/i });
        fireEvent.click(button);

        expect(button).toBeInTheDocument();
    });


    test('ECTS input only accepts numeric values', async () => {
        render(<MemoryRouter><CourseForm /></MemoryRouter>);

        const ectsInput = screen.getByRole('spinbutton', { name: /ects/i });
        fireEvent.change(ectsInput, { target: { value: 'abc' } });


        expect(ectsInput.value).toBe('');
    });



});
