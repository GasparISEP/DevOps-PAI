import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import TeacherErrorModal from '../../../components/teacherComponent/TeacherErrorModal';

describe('TeacherErrorModal', () => {
    it('should not render when error is falsy', () => {
        const { container } = render(<TeacherErrorModal error={null} onClose={jest.fn()} />);
        expect(container.firstChild).toBeNull();
    });

    it('should render error message and close button', () => {
        const errorMsg = 'Something went wrong!';
        render(<TeacherErrorModal error={errorMsg} onClose={jest.fn()} />);
        expect(screen.getByText('Registration Error')).toBeInTheDocument();
        expect(screen.getByText(errorMsg)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /close/i })).toBeInTheDocument();
    });

    it('should call onClose when close button is clicked', () => {
        const onClose = jest.fn();
        render(<TeacherErrorModal error={'Error!'} onClose={onClose} />);
        fireEvent.click(screen.getByRole('button', { name: /close/i }));
        expect(onClose).toHaveBeenCalledTimes(1);
    });
});

