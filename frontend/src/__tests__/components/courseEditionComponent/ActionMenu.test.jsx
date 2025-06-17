import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import ActionMenu from '../../../components/courseEditionComponent/ActionMenu';

describe('ActionMenu Component', () => {
    const edition = { id: 'id-1' };
    const onCountEnrolments = jest.fn();

    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('displays dropdown open and closed correctly', () => {
        render(<ActionMenu edition={edition} onCountEnrolments={onCountEnrolments} />);

        expect(screen.queryByText('Count Enrolments')).toBeNull();

        fireEvent.click(screen.getByText('⋮'));
        expect(screen.getByText('Count Enrolments')).toBeInTheDocument();

        fireEvent.mouseDown(document.body);
        expect(screen.queryByText('Count Enrolments')).toBeNull();
    });

    test('handleCountEnrolments closes menu and calls onCountEnrolments', async () => {
        const onCountEnrolments = jest.fn().mockResolvedValue(); // mock da prop async
        const edition = { courseEditionGeneratedID: 'id-123' };

        render(<ActionMenu edition={edition} onCountEnrolments={onCountEnrolments} />);

        expect(screen.queryByText('Count Enrolments')).toBeNull();

        fireEvent.click(screen.getByText('⋮'));
        expect(screen.getByText('Count Enrolments')).toBeInTheDocument();

        fireEvent.click(screen.getByText('Count Enrolments'));

        await waitFor(() => {
            expect(onCountEnrolments).toHaveBeenCalledWith(edition);
        });

        expect(screen.queryByText('Count Enrolments')).toBeNull();
    });

});