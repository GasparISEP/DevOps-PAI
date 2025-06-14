import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ErrorModal from '../../../components/teacherCareerProgressionComponent/ErrorModal';

describe('ErrorModal', () => {
    it('does not render when message is null', () => {
        const { container } = render(<ErrorModal message={null} onClose={jest.fn()} />);
        expect(container.firstChild).toBeNull();
    });

    it('renders correctly when message is provided', () => {
        render(<ErrorModal message="Something went wrong" onClose={jest.fn()} />);
        expect(screen.getByText('Registration Error')).toBeInTheDocument();
        expect(screen.getByText('Something went wrong')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /close/i })).toBeInTheDocument();
    });

    it('calls onClose when "Close" button is clicked', () => {
        const handleClose = jest.fn();
        render(<ErrorModal message="Error occurred" onClose={handleClose} />);
        fireEvent.click(screen.getByRole('button', { name: /close/i }));
        expect(handleClose).toHaveBeenCalledTimes(1);
    });
});
