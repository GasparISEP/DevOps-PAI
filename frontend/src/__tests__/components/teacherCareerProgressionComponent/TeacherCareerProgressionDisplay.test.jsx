import React from 'react';
import {render, screen, fireEvent} from '@testing-library/react';
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

// "Filter by" tests

test('filters data by date', async () => {
    const mockData = [
        { teacherCareerProgressionId: 1, date: '2023-01-01', workingPercentage: 100, teacherCategoryID: 2, teacherID: 5 },
        { teacherCareerProgressionId: 2, date: '2024-05-10', workingPercentage: 80, teacherCategoryID: 1, teacherID: 6 }
    ];

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    const input = screen.getByPlaceholderText(/Search by Date/i);
    fireEvent.change(input, { target: { value: '2024' } });

    expect(await screen.findByText('2024-05-10')).toBeInTheDocument();
    expect(screen.queryByText('2023-01-01')).not.toBeInTheDocument();
});

test('filters data by working percentage', async () => {
    const mockData = [
        { teacherCareerProgressionId: 1, date: '2023-01-01', workingPercentage: 100, teacherCategoryID: 2, teacherID: 5 },
        { teacherCareerProgressionId: 2, date: '2024-05-10', workingPercentage: 80, teacherCategoryID: 1, teacherID: 6 }
    ];

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    fireEvent.change(screen.getByDisplayValue('Date'), { target: { value: 'workingPercentage' } });
    fireEvent.change(screen.getByPlaceholderText(/Working Percentage/i), { target: { value: '80' } });

    expect(await screen.findByText('80')).toBeInTheDocument();
    expect(screen.queryByText('100')).not.toBeInTheDocument();
});


test('filters data by Teacher Career Progression ID', async () => {
    const mockData = [
        { teacherCareerProgressionId: "1a23b456-7890-1234-5678-9abcdef01234", date: '2023-01-01', workingPercentage: 100, teacherCategoryID: 2, teacherID: 5 },
        { teacherCareerProgressionId: "b2c4d678-90ab-4321-8cde-1234567890ab", date: '2024-05-10', workingPercentage: 80, teacherCategoryID: 1, teacherID: 6 }
    ];

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    fireEvent.change(screen.getByDisplayValue('Date'), { target: { value: 'teacherCareerProgressionId' } });
    fireEvent.change(screen.getByPlaceholderText(/ID/i), { target: { value: 'b2c4d678-90ab-4321-8cde-1234567890ab' } });

    expect(await screen.findByText('b2c4d678-90ab-4321-8cde-1234567890ab')).toBeInTheDocument();
    expect(screen.queryByText('1a23b456-7890-1234-5678-9abcdef01234')).not.toBeInTheDocument();
});

test('filters data by Teacher Category', async () => {
    const mockData = [
        { teacherCareerProgressionId: 1, date: '2023-01-01', workingPercentage: 100, teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234", teacherID: 5 },
        { teacherCareerProgressionId: 2, date: '2024-05-10', workingPercentage: 80, teacherCategoryID: "b2c4d678-90ab-4321-8cde-1234567890ab", teacherID: 6 }
    ];

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    fireEvent.change(screen.getByDisplayValue('Date'), { target: { value: 'teacherCategoryID' } });
    fireEvent.change(screen.getByPlaceholderText(/Teacher Category/i), { target: { value: '1a23b456-7890-1234-5678-9abcdef01234' } });

    expect(await screen.findByText('1a23b456-7890-1234-5678-9abcdef01234')).toBeInTheDocument();
    expect(screen.queryByText('b2c4d678-90ab-4321-8cde-1234567890ab')).not.toBeInTheDocument();
});

test('filters data by Teacher', async () => {
    const mockData = [
        { teacherCareerProgressionId: 1, date: '2023-01-01', workingPercentage: 100, teacherCategoryID: 2, teacherID: "b2c4d678-90ab-4321-8cde-1234567890ab" },
        { teacherCareerProgressionId: 2, date: '2024-05-10', workingPercentage: 80, teacherCategoryID: 1, teacherID: "1a23b456-7890-1234-5678-9abcdef01234" }
    ];

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay/>
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    fireEvent.change(screen.getByDisplayValue('Date'), { target: { value: 'teacherID' } });
    fireEvent.change(screen.getByPlaceholderText(/Teacher/i), { target: { value: '1a23b456-7890-1234-5678-9abcdef01234' } });

    expect(await screen.findByText('1a23b456-7890-1234-5678-9abcdef01234')).toBeInTheDocument();
    expect(screen.queryByText('b2c4d678-90ab-4321-8cde-1234567890ab')).not.toBeInTheDocument();
});