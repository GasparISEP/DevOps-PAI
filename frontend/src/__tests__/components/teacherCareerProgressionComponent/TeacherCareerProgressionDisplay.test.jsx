import React from 'react';
import {render, screen, fireEvent, waitFor} from '@testing-library/react';
import '@testing-library/jest-dom';
import TeacherCareerProgressionDisplay from '../../../components/teacherCareerProgressionComponent/TeacherCareerProgressionDisplay';
import { MemoryRouter } from 'react-router-dom';

beforeEach(() => {
    // Clean up mocks before each test
    jest.clearAllMocks();
});

test('displays loading spinner initially', () => {
    render(<TeacherCareerProgressionDisplay />);
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
            <TeacherCareerProgressionDisplay />
        </MemoryRouter>
    );

    // Waits for data to load
    await screen.findByText('2023-01-01');

    // Checks that the spinner is no longer in the document
    expect(screen.queryByTestId('loading-spinner')).not.toBeInTheDocument();
});