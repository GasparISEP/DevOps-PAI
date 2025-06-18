import React from 'react';
import { render, screen } from '@testing-library/react';
import DisplayTeacherCareerProgressionPage from '../../pages/DisplayTeacherCareerProgression';

jest.mock('../../components/NavBar', () => () => <div>Mocked NavBar</div>);
jest.mock('../../components/Footer', () => () => <div>Mocked Footer</div>);
jest.mock('../../components/teacherCareerProgressionComponent/TeacherCareerProgressionDisplay', () => () => <div>Mocked TeacherCareerProgressionDisplay</div>);

test('renders NavBar, TeacherCareerProgressionDisplay and Footer', () => {
    render(<DisplayTeacherCareerProgressionPage />);

    expect(screen.getByText('Mocked NavBar')).toBeInTheDocument();
    expect(screen.getByText('Mocked TeacherCareerProgressionDisplay')).toBeInTheDocument();
    expect(screen.getByText('Mocked Footer')).toBeInTheDocument();
});