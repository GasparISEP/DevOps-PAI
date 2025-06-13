import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CourseEditionDisplay from '../../../components/courseEditionComponent/CourseEditionDisplay';

test('renders CourseEditionDisplay component successfully', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );
    expect(screen.getByText(/Course Editions/i)).toBeInTheDocument();
});

test('always renders table headers even if no data', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    const headers = screen.getAllByRole('columnheader');
    expect(headers).toHaveLength(4);

    expect(screen.getByRole('table')).toBeInTheDocument();
});

test('renders all table headers in CourseEditionDisplay', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );
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

test('default filter field is programme name', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    const select = screen.getByRole('combobox');
    expect(select.value).toBe('programme name');

    const input = screen.getByPlaceholderText(/Search by Programme Name/i);
    expect(input).toBeInTheDocument();
});

test('changes filter field and updates placeholder', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    const select = screen.getByRole('combobox');
    fireEvent.change(select, { target: { value: 'course acronym' } });

    const input = screen.getByPlaceholderText(/Search by Course Acronym/i);
    expect(input).toBeInTheDocument();
});

test('disables Previous button on first page', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );
    expect(screen.getByText('Previous')).toBeDisabled();
});

test('changes items per page when clicking per page button', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );
    const perPageButton = screen.getByRole('button', { name: '10' }); // Ex: valor 10
    fireEvent.click(perPageButton);
    expect(screen.getByText(/per page/i)).toBeInTheDocument();
});

test('changes filter field and input value', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    const select = screen.getByRole('combobox');
    const input = screen.getByPlaceholderText(/Search by/i);

    fireEvent.change(select, { target: { value: 'course name' } });
    expect(select.value).toBe('course name');

    fireEvent.change(input, { target: { value: 'Math' } });
    expect(input.value).toBe('Math');
});

test('search input starts empty', () => {
    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    const input = screen.getByPlaceholderText(/Search by/i);
    expect(input.value).toBe('');
});

test('fetches course editions on mount and renders them in the table', async () => {
    global.fetch = jest.fn(() =>
        Promise.resolve({
            json: () =>
                Promise.resolve([
                    {
                        programmeAcronym: 'CS',
                        courseName: 'Computer Science',
                        courseAcronym: 'CS101',
                        schoolYearID: '2024/2025',
                    },
                ]),
        })
    );

    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    expect(global.fetch).toHaveBeenCalledWith(`${process.env.REACT_APP_API_URL}/course-editions`);

    await waitFor(() => {
        expect(screen.getByText('Computer Science')).toBeInTheDocument();
        expect(screen.getByText('CS')).toBeInTheDocument();
        expect(screen.getByText('CS101')).toBeInTheDocument();
        expect(screen.getByText('2024/2025')).toBeInTheDocument();
    });

    jest.clearAllMocks();
});

test('logs error when fetch fails', async () => {
    const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation(() => {});

    global.fetch = jest.fn(() => Promise.reject(new Error('Failed to fetch')));

    render(
        <MemoryRouter>
            <CourseEditionDisplay />
        </MemoryRouter>
    );

    await waitFor(() => {
        expect(consoleErrorSpy).toHaveBeenCalledWith(
            'Failed to load Course Editions:',
            expect.any(Error)
        );
    });

    consoleErrorSpy.mockRestore();
    jest.clearAllMocks();
});












