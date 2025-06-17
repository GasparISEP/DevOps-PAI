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

// Sorting tests - parameterized

const mockData = [
    {
        teacherCareerProgressionId: 'b1f55d7c-9c11-4cb3-920e-7b9fcb5bde13',
        date: '2023-01-01',
        workingPercentage: 50,
        teacherCategoryID: 'f8a12b1a-d3d6-4cd0-8b0f-9c412d839e9e',
        teacherID: 'a32457dd-2ab0-4f5d-baae-c9c1453e937e',
    },
    {
        teacherCareerProgressionId: '8d56a676-b21a-40b2-9c6e-c85d9372e1f2',
        date: '2022-01-01',
        workingPercentage: 100,
        teacherCategoryID: '9b4f4e9b-27a9-4b88-96c3-52cb12f02e24',
        teacherID: 'b6732ef2-8932-4a2a-8452-b7e7eea9d273',
    },
];

const testCases = [
    {
        columnHeaderTestId: 'date-header',
        expectedOrderAsc: ['2022-01-01', '2023-01-01'],
        expectedOrderDesc: ['2023-01-01', '2022-01-01'],
    },
    {
        columnHeaderTestId: 'working-percentage-header',
        expectedOrderAsc: ['50', '100'],
        expectedOrderDesc: ['100', '50'],
    },
    {
        columnHeaderTestId: 'id-header',
        expectedOrderAsc: [
            '8d56a676-b21a-40b2-9c6e-c85d9372e1f2',
            'b1f55d7c-9c11-4cb3-920e-7b9fcb5bde13',
        ],
        expectedOrderDesc: [
            'b1f55d7c-9c11-4cb3-920e-7b9fcb5bde13',
            '8d56a676-b21a-40b2-9c6e-c85d9372e1f2',
        ],
    },
    {
        columnHeaderTestId: 'teacher-category-header',
        expectedOrderAsc: [
            '9b4f4e9b-27a9-4b88-96c3-52cb12f02e24',
            'f8a12b1a-d3d6-4cd0-8b0f-9c412d839e9e',
        ],
        expectedOrderDesc: [
            'f8a12b1a-d3d6-4cd0-8b0f-9c412d839e9e',
            '9b4f4e9b-27a9-4b88-96c3-52cb12f02e24',
        ],
    },
    {
        columnHeaderTestId: 'teacher-header',
        expectedOrderAsc: [
            'a32457dd-2ab0-4f5d-baae-c9c1453e937e',
            'b6732ef2-8932-4a2a-8452-b7e7eea9d273',
        ],
        expectedOrderDesc: [
            'b6732ef2-8932-4a2a-8452-b7e7eea9d273',
            'a32457dd-2ab0-4f5d-baae-c9c1453e937e',
        ],
    },
];

describe('TeacherCareerProgressionDisplay sorting', () => {
    beforeEach(() => {
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockData),
            })
        );
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    test.each(testCases)(
        'sorts column correctly when clicking $columnHeaderTestId',
        async ({ columnHeaderTestId, expectedOrderAsc, expectedOrderDesc }) => {
            render(
                <MemoryRouter>
                    <TeacherCareerProgressionDisplay />
                </MemoryRouter>
            );

            // Wait for table to load
            await screen.findByText(mockData[0].date);

            const header = screen.getByTestId(columnHeaderTestId);

            fireEvent.click(header);
            const rowsAsc = screen.getAllByRole('row').slice(1).map(row => row.textContent);
            expectedOrderAsc.forEach((value, i) => expect(rowsAsc[i]).toContain(value));

            fireEvent.click(header);
            const rowsDesc = screen.getAllByRole('row').slice(1).map(row => row.textContent);
            expectedOrderDesc.forEach((value, i) => expect(rowsDesc[i]).toContain(value));
        }
    );
});

// Next and Previous controls tests

test('navigates pages using pagination buttons', async () => {
    const mockData = Array.from({ length: 25 }, (_, i) => ({
        teacherCareerProgressionId: i + 1,
        date: `2023-01-${String(i + 1).padStart(2, '0')}`,
        workingPercentage: 100,
        teacherCategoryID: 1,
        teacherID: i + 1,
    }));

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay />
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    const nextButton = screen.getByText(/Next/i);

    fireEvent.click(nextButton);

    expect(await screen.findByText('2023-01-21')).toBeInTheDocument();
});

test('navigates pages using previous button', async () => {
    const mockData = Array.from({ length: 25 }, (_, i) => ({
        teacherCareerProgressionId: i + 1,
        date: `2023-01-${String(i + 1).padStart(2, '0')}`,
        workingPercentage: 100,
        teacherCategoryID: 1,
        teacherID: i + 1,
    }));

    global.fetch = jest.fn(() =>
        Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) })
    );

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay />
        </MemoryRouter>
    );

    // Wait for initial load
    await screen.findByText('2023-01-01');

    // Go to page 2
    fireEvent.click(screen.getByText(/Next/i));
    await screen.findByText('2023-01-21'); // should now be on second page

    // Now go back to page 1
    fireEvent.click(screen.getByText(/Previous/i));

    // Check that page 1 data is shown again
    expect(await screen.findByText('2023-01-01')).toBeInTheDocument();
});

test('disables previous button on first page', async () => {
    const mockData = Array.from({ length: 25 }, (_, i) => ({
        teacherCareerProgressionId: i + 1,
        date: `2023-01-${String(i + 1).padStart(2, '0')}`,
        workingPercentage: 100,
        teacherCategoryID: 1,
        teacherID: i + 1,
    }));

    global.fetch = jest.fn(() => Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) }));

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay />
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    const previousButton = screen.getByText(/Previous/i);
    expect(previousButton).toBeDisabled();
});


test('disables next button on last page', async () => {
    const mockData = Array.from({ length: 25 }, (_, i) => ({
        teacherCareerProgressionId: i + 1,
        date: `2023-01-${String(i + 1).padStart(2, '0')}`,
        workingPercentage: 100,
        teacherCategoryID: 1,
        teacherID: i + 1,
    }));

    global.fetch = jest.fn(() => Promise.resolve({ ok: true, json: () => Promise.resolve(mockData) }));

    render(
        <MemoryRouter>
            <TeacherCareerProgressionDisplay />
        </MemoryRouter>
    );

    await screen.findByText('2023-01-01');

    // Going to page 2
    fireEvent.click(screen.getByText(/Next/i));
    await screen.findByText('2023-01-21');

    // Re-query the button AFTER rerender
    const updatedNextButton = screen.getByText(/Next/i);
    expect(updatedNextButton).toBeDisabled();
});
