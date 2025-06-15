import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CourseEditionDisplay from '../../../components/courseEditionComponent/CourseEditionDisplay';
import fetchMock from 'jest-fetch-mock';

fetchMock.enableMocks();

beforeEach(() => {
    fetchMock.resetMocks();
    process.env.REACT_APP_API_URL = 'http://localhost:8081';
});

const mockCourseEditions = [
    {
        programmeAcronym: 'SWITCH',
        courseName: 'Web Development',
        courseAcronym: 'WD',
        schoolYearID: '2024/2025'
    },
    {
        programmeAcronym: 'AI',
        courseName: 'Machine Learning',
        courseAcronym: 'ML',
        schoolYearID: '2024/2025'
    },
    {
        programmeAcronym: 'SWITCH',
        courseName: 'Databases',
        courseAcronym: 'DB',
        schoolYearID: '2023/2024'
    }
];

describe('CourseEditionDisplay Component', () => {
    test('renders CourseEditionDisplay component successfully', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        expect(screen.getByText(/Course Editions/i)).toBeInTheDocument();
    });

    test('always renders table headers even if no data', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const headers = screen.getAllByRole('columnheader');
        expect(headers).toHaveLength(4);
        expect(screen.getByRole('table')).toBeInTheDocument();
    });

    test('renders all table headers in CourseEditionDisplay', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const headers = screen.getAllByRole('columnheader');
        const headerTexts = headers.map(h => h.textContent);
        expect(headerTexts).toEqual(
            expect.arrayContaining([
                'Programme Acronym',
                'Course Name',
                'Course Acronym',
                'School Year',
            ])
        );
    });

    test('default filter field is programme acronym', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const select = screen.getByRole('combobox');
        expect(select.value).toBe('programme acronym');
        const input = screen.getByPlaceholderText(/Search by Programme Acronym/i);
        expect(input).toBeInTheDocument();
    });

    test('filter field "course acronym" updates placeholder correctly', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const select = screen.getByRole('combobox');
        fireEvent.change(select, { target: { value: 'course acronym' } });
        const input = screen.getByPlaceholderText(/Search by Course Acronym/i);
        expect(input).toBeInTheDocument();
    });

    test('filter field "course name" updates placeholder correctly', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const select = screen.getByRole('combobox');
        fireEvent.change(select, { target: { value: 'course name' } });
        const input = screen.getByPlaceholderText(/Search by Course Name/i);
        expect(input).toBeInTheDocument();
    });

    test('disables Previous button on first page', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        expect(screen.getByText('Previous')).toBeDisabled();
    });

    test('changes items per page when clicking per page button', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const perPageButton = screen.getByRole('button', { name: '10' });
        fireEvent.click(perPageButton);
        expect(screen.getByText(/per page/i)).toBeInTheDocument();
    });

    test('changes filter field and input value', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const select = screen.getByRole('combobox');
        const input = screen.getByPlaceholderText(/Search by/i);
        fireEvent.change(select, { target: { value: 'course name' } });
        expect(select.value).toBe('course name');
        fireEvent.change(input, { target: { value: 'Math' } });
        expect(input.value).toBe('Math');
    });

    test('search input starts empty', () => {
        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);
        const input = screen.getByPlaceholderText(/Search by/i);
        expect(input.value).toBe('');
    });

    test('fetches course editions on mount and renders them in the table', async () => {
        fetchMock.mockResponseOnce(JSON.stringify([
            {
                programmeAcronym: 'CS',
                courseName: 'Computer Science',
                courseAcronym: 'CS101',
                schoolYearID: '2024/2025',
            },
        ]));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => {
            expect(fetchMock).toHaveBeenCalledWith('http://localhost:8081/course-editions');
        });

        expect(await screen.findByText('Computer Science')).toBeInTheDocument();
        expect(screen.getByText('CS')).toBeInTheDocument();
        expect(screen.getByText('CS101')).toBeInTheDocument();
        expect(screen.getByText('2024/2025')).toBeInTheDocument();
    });

    test('logs error when fetch fails', async () => {
        const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation(() => {});
        fetchMock.mockRejectOnce(new Error('Failed to fetch'));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => {
            expect(consoleErrorSpy).toHaveBeenCalledWith('Failed to load Course Editions:', expect.any(Error));
        });

        consoleErrorSpy.mockRestore();
    });

    test('calls fetch with correct URL and renders data', async () => {
        fetchMock.mockResponseOnce(JSON.stringify(mockCourseEditions));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => {
            expect(fetchMock).toHaveBeenCalledWith('http://localhost:8081/course-editions');
        });

        expect(await screen.findByText('Web Development')).toBeInTheDocument();
    });

    test('filters by programme acronym', async () => {
        fetchMock.mockResponseOnce(JSON.stringify(mockCourseEditions));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => screen.getByText('Web Development'));

        const select = screen.getByRole('combobox');
        const input = screen.getByPlaceholderText(/Search by/i);

        fireEvent.change(select, { target: { value: 'programme acronym' } });
        fireEvent.change(input, { target: { value: 'AI' } });

        await waitFor(() => {
            expect(screen.getByText('Machine Learning')).toBeInTheDocument();
            expect(screen.queryByText('Web Development')).not.toBeInTheDocument();
            expect(screen.queryByText('Databases')).not.toBeInTheDocument();
        });
    });

    test('shows "No results found" when filter has no match', async () => {
        fetchMock.mockResponseOnce(JSON.stringify(mockCourseEditions));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => screen.getByText('Web Development'));

        const input = screen.getByPlaceholderText(/Search by/i);
        fireEvent.change(input, { target: { value: 'ZZZZZZ' } });

        expect(await screen.findByText('No results found.')).toBeInTheDocument();
    });

    test('changing items per page resets to page 1', async () => {
        const editions = Array.from({ length: 25 }, (_, i) => ({
            programmeAcronym: `X${i}`,
            courseName: `Test ${i}`,
            courseAcronym: `T${i}`,
            schoolYearID: '2024/2025'
        }));

        fetchMock.mockResponseOnce(JSON.stringify(editions));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => screen.getByText('Test 0'));

        fireEvent.click(screen.getByText('Next'));
        expect(await screen.findByText('Page 2 of 3')).toBeInTheDocument();

        fireEvent.click(screen.getByText('20'));
        expect(await screen.findByText('Page 1 of 2')).toBeInTheDocument();
    });

    test('Previous button decrements page when currentPage > 1', async () => {
        const manyEditions = Array.from({ length: 15 }, (_, i) => ({
            programmeAcronym: `P${i}`,
            courseName: `Course ${i}`,
            courseAcronym: `C${i}`,
            schoolYearID: '2024/2025',
        }));

        fetchMock.mockResponseOnce(JSON.stringify(manyEditions));

        render(<MemoryRouter><CourseEditionDisplay /></MemoryRouter>);

        await waitFor(() => screen.getByText('Course 0'));

        expect(screen.getByText(/Page 1 of 2/i)).toBeInTheDocument();

        fireEvent.click(screen.getByText('Next'));

        expect(await screen.findByText(/Page 2 of 2/i)).toBeInTheDocument();

        const prevButton = screen.getByText('Previous');

        expect(prevButton).not.toBeDisabled();

        fireEvent.click(prevButton);

        expect(await screen.findByText(/Page 1 of 2/i)).toBeInTheDocument();
    });

});


