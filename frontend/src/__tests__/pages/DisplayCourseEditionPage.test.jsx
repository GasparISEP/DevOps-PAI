import React from 'react';
import { render, screen } from '@testing-library/react';
import DisplayCourseEditionPage from '../../pages/CourseEditionPage/DisplayCourseEditionPage';

jest.mock('../../components/NavBar', () => () => <div>Mocked NavBar</div>);
jest.mock('../../components/Footer', () => () => <div>Mocked Footer</div>);
jest.mock('../../components/courseEditionComponent/CourseEditionDisplay', () => () => <div>Mocked CourseEditionDisplay</div>);

test('renders NavBar, CourseEditionDisplay and Footer', () => {
    render(<DisplayCourseEditionPage />);

    expect(screen.getByText('Mocked NavBar')).toBeInTheDocument();
    expect(screen.getByText('Mocked CourseEditionDisplay')).toBeInTheDocument();
    expect(screen.getByText('Mocked Footer')).toBeInTheDocument();
});
