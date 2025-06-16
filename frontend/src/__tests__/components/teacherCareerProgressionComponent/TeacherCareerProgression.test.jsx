import React from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import TeacherCareerProgressionComponent from '../../../components/teacherCareerProgressionComponent/TeacherCareerProgressionComponent';

jest.mock('../../../components/NavBar', () => () => <div data-testid="navbar">NavBar</div>);
jest.mock('../../../components/Footer', () => () => <div data-testid="footer">Footer</div>);

describe('TeacherCareerProgressionComponent', () => {

    afterEach(() => {
        jest.clearAllMocks();
    });

    test('shows loading state initially', () => {
        global.fetch = jest.fn(() => new Promise(() => {}));

        render(
            <MemoryRouter initialEntries={['/teacher-career-progressions/123']}>
                <Routes>
                    <Route path="/teacher-career-progressions/:id" element={<TeacherCareerProgressionComponent />} />
                </Routes>
            </MemoryRouter>
        );

        expect(screen.getByText(/Loading data.../i)).toBeInTheDocument();
    });

    test('renders progression details after successful fetch', async () => {
        const mockProgression = {
            teacherCareerProgressionId: 'prog-abc-123',
            date: '2025-06-14',
            teacherID: 'T-456',
            teacherCategoryID: 'CAT-A',
            workingPercentage: 80,
        };

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockProgression),
            })
        );

        render(
            <MemoryRouter initialEntries={['/teacher-career-progressions/123']}>
                <Routes>
                    <Route path="/teacher-career-progressions/:id" element={<TeacherCareerProgressionComponent />} />
                </Routes>
            </MemoryRouter>
        );

        await screen.findByText('Teacher Career Progression Details');

        expect(screen.getByText('Progression ID')).toBeInTheDocument();
        expect(screen.getByText('prog-abc-123')).toBeInTheDocument();

        expect(screen.getByText('Date')).toBeInTheDocument();
        expect(screen.getByText('14/06/2025')).toBeInTheDocument();

        expect(screen.getByText('Teacher ID')).toBeInTheDocument();
        expect(screen.getByText('T-456')).toBeInTheDocument();

        expect(screen.getByText('Category ID')).toBeInTheDocument();
        expect(screen.getByText('CAT-A')).toBeInTheDocument();

        expect(screen.getByText('Working Percentage')).toBeInTheDocument();
        expect(screen.getByText('80%')).toBeInTheDocument();

        expect(screen.getByTestId('navbar')).toBeInTheDocument();
        expect(screen.getByTestId('footer')).toBeInTheDocument();
    });

    test('displays an error message when fetch fails', async () => {
        global.fetch = jest.fn(() => Promise.reject(new Error('Network Error')));
        const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation(() => {});

        render(
            <MemoryRouter initialEntries={['/teacher-career-progressions/123']}>
                <Routes>
                    <Route path="/teacher-career-progressions/:id" element={<TeacherCareerProgressionComponent />} />
                </Routes>
            </MemoryRouter>
        );

        await screen.findByText(/Error:/i);

        expect(screen.getByText(/Error:.*Network Error/i)).toBeInTheDocument();

        consoleErrorSpy.mockRestore();
    });

    test('displays "no data" message when API returns null', async () => {
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(null),
            })
        );

        render(
            <MemoryRouter initialEntries={['/teacher-career-progressions/123']}>
                <Routes>
                    <Route path="/teacher-career-progressions/:id" element={<TeacherCareerProgressionComponent />} />
                </Routes>
            </MemoryRouter>
        );

        await screen.findByText(/No data found./i);

        expect(screen.getByText(/No data found./i)).toBeInTheDocument();
    });

    test('navigates to the home page when the "Back to Home Page" link is clicked', async () => {
        const user = userEvent.setup();
        const DummyHomeComponent = () => <h1>Welcome to the Home Page</h1>;

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({
                    teacherCareerProgressionId: 'prog-123',
                    date: '2025-06-15',
                    teacherID: 'T-123',
                    teacherCategoryID: 'CAT-B',
                    workingPercentage: 100,
                }),
            })
        );

        render(
            <MemoryRouter initialEntries={['/teacher-career-progressions/123']}>
                <Routes>
                    <Route path="/teacher-career-progressions/:id" element={<TeacherCareerProgressionComponent />} />
                    <Route path="/" element={<DummyHomeComponent />} />
                </Routes>
            </MemoryRouter>
        );

        await screen.findByText('Teacher Career Progression Details');

        const backLink = screen.getByRole('link', { name: /back to home page/i });

        await user.click(backLink);

        expect(screen.getByText(/Welcome to the Home Page/i)).toBeInTheDocument();
    });
});