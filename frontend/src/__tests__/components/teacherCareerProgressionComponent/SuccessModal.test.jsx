import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import SuccessModal from '../../../components/teacherCareerProgressionComponent/SuccessModal';

describe('SuccessModal - full coverage', () => {
    const form = {
        teacher: 'FallbackTeacher',
        teacherCategory: 'FallbackCategory',
        date: '2024-01-01',
    };

    const fullData = {
        teacher: 'T001',
        teacherCategory: 'Senior',
        date: '2025-06-14',
        _links: {
            self: { href: 'http://localhost/teacher-career-progressions/123' },
            all: { href: 'http://localhost/teacher-career-progressions' },
        },
    };

    beforeEach(() => {
        window.open = jest.fn(); // mock global open
    });

    it('does not render when data is null', () => {
        const { container } = render(<SuccessModal data={null} form={form} onClose={jest.fn()} />);
        expect(container.firstChild).toBeNull();
    });

    it('renders with full data and triggers window.open for self and all links', () => {
        render(<SuccessModal data={fullData} form={form} onClose={jest.fn()} />);
        expect(screen.getByText('Success!')).toBeInTheDocument();
        expect(screen.getByText('T001')).toBeInTheDocument();
        expect(screen.getByText('Senior')).toBeInTheDocument();
        expect(screen.getByText('2025-06-14')).toBeInTheDocument();

        fireEvent.click(screen.getByTitle('View Entry'));
        expect(window.open).toHaveBeenCalledWith(
            'http://localhost/teacher-career-progressions/123',
            '_blank'
        );

        fireEvent.click(screen.getByTitle('View All'));
        expect(window.open).toHaveBeenCalledWith(
            'http://localhost/teachers/displayTeacherCareerProgressions',
            '_blank'
        );
    });

    it('falls back to form values if data fields are missing', () => {
        const partialData = {
            _links: {
                self: { href: 'http://localhost/teacher-career-progressions/789' },
                all: { href: 'http://localhost/teacher-career-progressions' },
            },
        };

        render(<SuccessModal data={partialData} form={form} onClose={jest.fn()} />);
        expect(screen.getByText('FallbackTeacher')).toBeInTheDocument();
        expect(screen.getByText('FallbackCategory')).toBeInTheDocument();
        expect(screen.getByText('2024-01-01')).toBeInTheDocument();
    });

    it('does not call window.open when ID is empty in self.href', () => {
        const badSelf = {
            ...fullData,
            _links: {
                self: { href: 'http://localhost/teacher-career-progressions/' },
            },
        };

        render(<SuccessModal data={badSelf} form={form} onClose={jest.fn()} />);
        fireEvent.click(screen.getByTitle('View Entry'));
        expect(window.open).not.toHaveBeenCalled();
    });

    it('does not call window.open when route is not mapped', () => {
        const badAll = {
            ...fullData,
            _links: {
                all: { href: 'http://localhost/api/not-mapped' },
            },
        };

        render(<SuccessModal data={badAll} form={form} onClose={jest.fn()} />);
        fireEvent.click(screen.getByTitle('View All'));
        expect(window.open).not.toHaveBeenCalled();
    });

    it('calls onClose when clicking "Close"', () => {
        const onClose = jest.fn();
        render(<SuccessModal data={fullData} form={form} onClose={onClose} />);
        fireEvent.click(screen.getByText('Close'));
        expect(onClose).toHaveBeenCalledTimes(1);
    });

    it('does not render icons when _links are missing entirely', () => {
        const noLinks = {
            teacher: 'NoLink',
            teacherCategory: 'X',
            date: '2022-01-01',
        };

        render(<SuccessModal data={noLinks} form={form} onClose={jest.fn()} />);
        expect(screen.queryByTitle('View Entry')).not.toBeInTheDocument();
        expect(screen.queryByTitle('View All')).not.toBeInTheDocument();
    });
});
