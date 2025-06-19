// __tests__/CourseEditionDisplay.test.jsx
import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CourseEditionDisplay from '../../../components/courseEditionComponent/CourseEditionDisplay';

jest.mock('../../../services/enrolmentCountInCourseEditionService', () => ({
    fetchEnrolmentCount: jest.fn(),
}));

jest.mock('../../../components/courseEditionComponent/useFetchListOfProgrammesById', () => ({
    __esModule: true,
    default: () => ({
        programmesMap: {}, // or mock values if needed
        isLoading: false,
    }),
}));

import { fetchEnrolmentCount } from '../../../services/enrolmentCountInCourseEditionService';

beforeEach(() => {
    jest.clearAllMocks();
    fetchEnrolmentCount.mockResolvedValue({ studentCount: 42 }); // default mock return
    global.fetch = jest.fn(); // mock fetch for course editions
});

// Sample data for tests
const sampleData = Array.from({ length: 15 }, (_, i) => ({
    courseEditionGeneratedID: `id-${i}`,
    programmeAcronym: `P${i}`,
    courseName: `Course ${i}`,
    courseAcronym: `C${i}`,
    schoolYearID: `20${i}/20${i + 1}`,
}));

describe('CourseEditionDisplay Component', () => {
    test('renders table with fetched data', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData.slice(0, 2) });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        await waitFor(() => {
            expect(global.fetch).toHaveBeenCalledWith(
                `${process.env.REACT_APP_API_URL}/course-editions`
            );
        });

        expect(await screen.findByText('P0')).toBeInTheDocument();
        expect(screen.getByText('Course 0')).toBeInTheDocument();
        expect(screen.getByText('200/201')).toBeInTheDocument();
        expect(screen.getByText('P1')).toBeInTheDocument();
    });

    test('filters results based on selection and input', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData.slice(0, 2) });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        fireEvent.change(screen.getByRole('combobox'), {
            target: { value: 'course name' },
        });
        fireEvent.change(screen.getByPlaceholderText(/Search by Course Name/i), {
            target: { value: '1' },
        });

        expect(screen.queryByText('P0')).not.toBeInTheDocument();
        expect(screen.getByText('Course 1')).toBeInTheDocument();
    });

    test('pagination and items-per-page selection', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        expect(screen.getByText('P0')).toBeInTheDocument();
        expect(screen.getByText('P9')).toBeInTheDocument();
        expect(screen.queryByText('P10')).not.toBeInTheDocument();

        fireEvent.click(screen.getByText('Next'));
        expect(await screen.findByText('P10')).toBeInTheDocument();

        fireEvent.click(screen.getByText('5'));
        expect(screen.getByText(/Page 1 of/)).toBeInTheDocument();
        expect(screen.getByText('P4')).toBeInTheDocument();
    });

    test('Previous and Next buttons disabled state on single page', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData.slice(0, 5) });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        const prevBtn = screen.getByText('Previous');
        const nextBtn = screen.getByText('Next');

        expect(prevBtn).toBeDisabled();
        expect(nextBtn).toBeDisabled();
    });

    test('Previous and Next buttons enable and disable on multiple pages', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        const prevBtn = screen.getByText('Previous');
        const nextBtn = screen.getByText('Next');

        expect(prevBtn).toBeDisabled();
        expect(nextBtn).not.toBeDisabled();

        fireEvent.click(nextBtn);
        await waitFor(() => {
            expect(screen.getByText('Next')).toBeDisabled();
            expect(screen.getByText('Previous')).not.toBeDisabled();
        });
    });

    test('clicking Previous navigates to previous page', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        const nextBtn = screen.getByText('Next');
        fireEvent.click(nextBtn);
        expect(
            await screen.findByText('P10')
        ).toBeInTheDocument();

        const prevBtnAfter = screen.getByText('Previous');
        fireEvent.click(prevBtnAfter);
        expect(
            await screen.findByText('P0')
        ).toBeInTheDocument();
    });

    test('selected PerPageButton disabled with selected class', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        const btn10 = screen.getByText('10');

        expect(btn10).toBeDisabled();
        expect(btn10).toHaveClass('selected');

        fireEvent.click(screen.getByText('20'));
        const btn20 = screen.getByText('20');

        expect(btn20).toBeDisabled();
        expect(btn20).toHaveClass('selected');
    });

    test('logs error on initial fetch failure and displays no results message', async () => {
        const errorSpy = jest.spyOn(console, 'error').mockImplementation(() => {});
        global.fetch.mockRejectedValueOnce(new Error('fail'));

        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        await waitFor(() =>
            expect(errorSpy).toHaveBeenCalledWith(
                'Failed to load Course Editions:',
                expect.any(Error)
            )
        );
        expect(screen.getByText('No results found.')).toBeInTheDocument();
        errorSpy.mockRestore();
    });

    test('resets currentPage to 1 on filter or items-per-page change', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData });
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );
        await screen.findByText('P0');

        fireEvent.click(screen.getByText('Next'));
        expect(screen.getByText(/Page 2 of/)).toBeInTheDocument();

        fireEvent.click(screen.getByText('50'));
        expect(screen.getByText(/Page 1 of/)).toBeInTheDocument();

        fireEvent.change(screen.getByRole('combobox'), {
            target: { value: 'course name' },
        });
        expect(screen.getByText(/Page 1 of/)).toBeInTheDocument();
    });

    test('Back to Main Page link has correct href', () => {
        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        expect(screen.getByText('Back to Main Page').closest('a')).toHaveAttribute('href', '/');
    });

});