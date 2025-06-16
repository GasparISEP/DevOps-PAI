import React from 'react';
import {render, screen, fireEvent, waitFor} from '@testing-library/react';
import '@testing-library/jest-dom';
import TeacherCareerProgressionDisplay
    from '../../../components/teacherCareerProgressionComponent/TeacherCareerProgressionDisplay';
import {MemoryRouter} from 'react-router-dom';

beforeEach(() => {
    // Clean up mocks before each test
    jest.clearAllMocks();
});

// Loading spinner tests

test('displays loading spinner initially', () => {
    render(<TeacherCareerProgressionDisplay/>);
    const spinner = screen.getByTestId('loading-spinner');
    expect(spinner).toBeInTheDocument();
});

test('hides loading spinner after data is fetched', async () => {
    global.fetch = jest.fn(() =>
        Promise.resolve({
            ok: true,
            json: () =>
                Promise.resolve([
                    {
                        teacherCareerProgressionId: 1,
                        date: '2023-01-01',
                        workingPercentage: 100,
                        teacherCategoryID: 2,
                        teacherID: 5,
                    },
                ]),
        })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    // Waits for data to load
    await screen.findByText('2023-01-01');

    // Checks that the spinner is no longer in the document
    expect(screen.queryByTestId('loading-spinner')).not.toBeInTheDocument();
});

// Fetch tests

test('renders data rows after successful fetch', async () => {
    const mockData = [
        {
            teacherCareerProgressionId: 1,
            date: '2023-01-01',
            workingPercentage: 100,
            teacherCategoryID: 2,
            teacherID: 5
        }
    ];

    global.fetch = jest.fn(() =>
        Promise.resolve({
            ok: true,
            json: () => Promise.resolve(mockData),
        })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    expect(await screen.findByText('2023-01-01')).toBeInTheDocument();

    expect(screen.getByText('100')).toBeInTheDocument();
});

test('shows error message on fetch failure', async () => {
    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: false, status: 500 })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    const error = await screen.findByText(/Failed to load/i);

    expect(error).toBeInTheDocument();
});

test('displays "No results found" when API returns empty list', async () => {
    global.fetch = jest.fn(() =>
        Promise.resolve({
            ok: true,
            json: () => Promise.resolve([]),
        })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    const emptyMessage = await screen.findByText(/No results found/i);

    expect(emptyMessage).toBeInTheDocument();
});

