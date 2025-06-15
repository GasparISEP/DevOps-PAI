import React, { useState } from 'react';
import { render, screen, act } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { v4 as uuidv4 } from 'uuid';
import CourseEditionSelector from '../../../components/defineRucInCourseEditionComponent/courseEditionSelector';

describe('CourseEditionSelector', () => {
    const mockCourseEditions = [
        {
            id: uuidv4(),
            name: 'Introduction to Computer Science',
            startDate: '2025-09-01',
            endDate: '2026-06-30',
        },
    ];

    it('calls onChange and updates selected value', async () => {
        function Wrapper() {
            const [value, setValue] = useState('');
            return (
                <CourseEditionSelector
                    courseEditions={mockCourseEditions}
                    value={value}
                    onChange={e => setValue(e.target.value)}
                />
            );
        }

        const user = userEvent.setup();
        render(<Wrapper />);

        const select = screen.getByLabelText(/course edition/i);

        // Verify option text is rendered correctly
        expect(
            screen.getByRole('option', {
                name: `${mockCourseEditions[0].name} - ${mockCourseEditions[0].startDate} to ${mockCourseEditions[0].endDate}`,
            })
        ).toBeInTheDocument();

        // Select the option by id (UUID)
        await act(async () => {
            await user.selectOptions(select, mockCourseEditions[0].id);
        });

        expect(select.value).toBe(mockCourseEditions[0].id);
    });
});
