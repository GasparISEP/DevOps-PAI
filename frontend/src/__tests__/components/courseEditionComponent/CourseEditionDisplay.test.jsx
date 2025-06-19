import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CourseEditionDisplay from '../../../components/courseEditionComponent/CourseEditionDisplay';
import useCourseEditionEnrolmentCountModal from '../../../components/courseEditionComponent/useCourseEditionEnrolmentCountModal';
import useCourseEditionAverageGradeModal from '../../../components/courseEditionComponent/useCourseEditionAverageGradeModal';
import useFetchCourseEditions from '../../../components/courseEditionComponent/useFetchCourseEditions';
import useFetchListOfProgrammesById from '../../../components/courseEditionComponent/useFetchListOfProgrammesById';
import useFetchMultipleResources from '../../../components/courseEditionComponent/useFetchMultipleResources';

import '@testing-library/jest-dom';

jest.mock('../../../components/courseEditionComponent/useCourseEditionEnrolmentCountModal');
jest.mock('../../../components/courseEditionComponent/useCourseEditionAverageGradeModal');
jest.mock('../../../components/courseEditionComponent/useFetchCourseEditions');
jest.mock('../../../components/courseEditionComponent/useFetchListOfProgrammesById');
jest.mock('../../../components/courseEditionComponent/useFetchMultipleResources');

const mockCourseEditions = [
    {
        programmeAcronym: 'CS',
        courseName: 'Computer Science 101',
        courseAcronym: 'CS101',
        teacherID: 'T001',
        schoolYearID: '2024',
        _links: {
            'school-year': { href: '/schoolyears/2024' }
        }
    },
    {
        programmeAcronym: 'MATH',
        courseName: 'Advanced Mathematics',
        courseAcronym: 'MATH201',
        teacherID: null,
        schoolYearID: '2024',
        _links: {
            'school-year': { href: '/schoolyears/2024' }
        }
    }
];

const mockProgrammesMap = {
    'CS': { name: 'Computer Science' },
    'MATH': { name: 'Mathematics' }
};

const mockSchoolYearsResources = {
    '/schoolyears/2024': {
        description: '2024/2025',
        startDate: '2024-09-01',
        endDate: '2025-08-31'
    }
};

const renderComponent = () => {
    return render(
        <MemoryRouter
            future={{
                v7_startTransition: true,
                v7_relativeSplatPath: true,
            }}
        >
            <CourseEditionDisplay />
        </MemoryRouter>
    );
};

describe('CourseEditionDisplay', () => {
    beforeEach(() => {
        useFetchCourseEditions.mockReturnValue(mockCourseEditions);
        useFetchListOfProgrammesById.mockReturnValue(mockProgrammesMap);
        useFetchMultipleResources.mockReturnValue(mockSchoolYearsResources);

        useCourseEditionEnrolmentCountModal.mockReturnValue({
            isModalOpen: false,
            enrolmentCount: 0,
            selectedCourse: null,
            handleCountEnrolments: jest.fn(),
            closeModal: jest.fn(),
        });

        useCourseEditionAverageGradeModal.mockReturnValue({
            isModalOpen: false,
            averageGrade: null,
            selectedCourse: null,
            statusCode: null,
            handleShowAverageGrade: jest.fn(),
            closeModal: jest.fn(),
        });
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    describe('Basic Rendering', () => {
        test('renders main components', () => {
            renderComponent();

            expect(screen.getByText('Course Editions')).toBeInTheDocument();
            expect(screen.getByText('Back to Main Page')).toBeInTheDocument();
            expect(screen.getByRole('table')).toBeInTheDocument();
        });

        test('renders table headers correctly', () => {
            renderComponent();

            expect(screen.getByRole('columnheader', { name: 'Programme Name' })).toBeInTheDocument();
            expect(screen.getByRole('columnheader', { name: 'Course Name' })).toBeInTheDocument();
            expect(screen.getByRole('columnheader', { name: 'Course Acronym' })).toBeInTheDocument();
            expect(screen.getByRole('columnheader', { name: 'School Year' })).toBeInTheDocument();
            expect(screen.getByRole('columnheader', { name: 'RUC' })).toBeInTheDocument();
        });

        test('renders course editions data', () => {
            renderComponent();

            expect(screen.getByText('Computer Science')).toBeInTheDocument();
            expect(screen.getByText('Computer Science 101')).toBeInTheDocument();
            expect(screen.getByText('CS101')).toBeInTheDocument();
            expect(screen.getByText('T001')).toBeInTheDocument();
            expect(screen.getAllByText('2024/2025')).toHaveLength(2);

            expect(screen.getByText('Mathematics')).toBeInTheDocument();
            expect(screen.getByText('Advanced Mathematics')).toBeInTheDocument();
            expect(screen.getByText('MATH201')).toBeInTheDocument();
            expect(screen.getByText('No RUC assigned')).toBeInTheDocument();
        });
    });

    describe('Filtering', () => {
        test('changes filter field', () => {
            renderComponent();

            const filterSelect = screen.getByDisplayValue('Programme Name');
            fireEvent.change(filterSelect, { target: { value: 'courseName' } });

            expect(screen.getByDisplayValue('Course Name')).toBeInTheDocument();
            expect(screen.getByPlaceholderText('Search by Course Name')).toBeInTheDocument();
        });

        test('filters by programme name', () => {
            renderComponent();

            const filterInput = screen.getByPlaceholderText('Search by Programme Name');
            fireEvent.change(filterInput, { target: { value: 'Computer' } });

            expect(screen.getByText('Computer Science')).toBeInTheDocument();
            expect(screen.queryByText('Mathematics')).not.toBeInTheDocument();
        });

        test('shows no results when filter matches nothing', () => {
            renderComponent();

            const filterInput = screen.getByPlaceholderText('Search by Programme Name');
            fireEvent.change(filterInput, { target: { value: 'NonExistent' } });

            expect(screen.getByText('No results found.')).toBeInTheDocument();
        });
    });

    describe('Pagination', () => {
        test('renders pagination controls', () => {
            renderComponent();

            expect(screen.getByText('Previous')).toBeInTheDocument();
            expect(screen.getByText('Next')).toBeInTheDocument();
            expect(screen.getByText('Page 1 of 1')).toBeInTheDocument();
        });

        test('renders per-page options', () => {
            renderComponent();

            expect(screen.getByText('Show:')).toBeInTheDocument();
            expect(screen.getByText('per page')).toBeInTheDocument();
            expect(screen.getByRole('button', { name: '5' })).toBeInTheDocument();
            expect(screen.getByRole('button', { name: '10' })).toBeInTheDocument();
            expect(screen.getByRole('button', { name: '20' })).toBeInTheDocument();
            expect(screen.getByRole('button', { name: '50' })).toBeInTheDocument();
        });

        test('changes items per page', () => {
            renderComponent();

            const fivePerPageBtn = screen.getByRole('button', { name: '5' });
            const tenPerPageBtn = screen.getByRole('button', { name: '10' });

            expect(fivePerPageBtn).not.toBeDisabled();
            expect(tenPerPageBtn).toHaveClass('selected');
            expect(tenPerPageBtn).toBeDisabled();

            fireEvent.click(fivePerPageBtn);

            const updatedFivePerPageBtn = screen.getByRole('button', { name: '5' });
            const updatedTenPerPageBtn = screen.getByRole('button', { name: '10' });

            expect(updatedFivePerPageBtn).toBeInTheDocument();
            expect(updatedFivePerPageBtn).toHaveClass('selected');
            expect(updatedFivePerPageBtn).toBeDisabled();

            expect(updatedTenPerPageBtn).not.toHaveClass('selected');
            expect(updatedTenPerPageBtn).not.toBeDisabled();
        });
    });

    describe('Modals', () => {
        test('displays enrolment count modal when triggered', () => {
            useCourseEditionEnrolmentCountModal.mockReturnValue({
                isModalOpen: true,
                enrolmentCount: 5,
                selectedCourse: { courseName: 'Computer Science 101' },
                handleCountEnrolments: jest.fn(),
                closeModal: jest.fn(),
            });

            renderComponent();

            expect(screen.getByText('Enrolment Count')).toBeInTheDocument();
            expect(screen.getByText('Enrolled Students:')).toBeInTheDocument();
            expect(screen.getByText((content, element) => {
                return element?.tagName === 'P' && content.includes('5');
            })).toBeInTheDocument();
            expect(screen.getByRole('button', { name: 'Close' })).toBeInTheDocument();
        });

        test('displays average grade modal when triggered', () => {
            useCourseEditionAverageGradeModal.mockReturnValue({
                isModalOpen: true,
                averageGrade: 16,
                selectedCourse: { courseName: 'Advanced Mathematics' },
                statusCode: 200,
                handleShowAverageGrade: jest.fn(),
                closeModal: jest.fn(),
            });

            renderComponent();

            expect(screen.getByText('Average Grade')).toBeInTheDocument();
            expect(screen.getByText('The average grade for this course is: 16')).toBeInTheDocument();
            expect(screen.getByRole('button', { name: 'Close' })).toBeInTheDocument();
        });

        test('displays no modal when both are closed', () => {
            renderComponent();

            expect(screen.queryByText('Enrolment Count')).not.toBeInTheDocument();
            expect(screen.queryByText('Average Grade')).not.toBeInTheDocument();
            expect(screen.getByText('Course Editions')).toBeInTheDocument();
        });
    });

    describe('Empty State', () => {
        test('shows no results when no course editions exist', () => {
            useFetchCourseEditions.mockReturnValue([]);

            renderComponent();

            expect(screen.getByText('No results found.')).toBeInTheDocument();
        });
    });

    describe('Navigation', () => {
        test('back to main page link is present', () => {
            renderComponent();

            const backLink = screen.getByText('Back to Main Page');
            expect(backLink).toBeInTheDocument();
            // eslint-disable-next-line testing-library/no-node-access
            expect(backLink.closest('a')).toHaveAttribute('href', '/');
        });
    });
});