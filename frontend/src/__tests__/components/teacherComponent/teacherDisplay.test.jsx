import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import TeacherDisplay from '../../../components/teacherComponent/TeacherDisplay';
import * as teacherService from '../../../services/teacherService';
import { MemoryRouter } from 'react-router-dom';

const mockTeachers = [
  {
    id: 'JDO',
    name: 'John Doe',
    email: 'john@isep.ipp.pt',
    nif: '123456789',
    academicBackground: 'PhD',
    street: 'Main St',
    postalCode: '4000-001',
    location: 'Porto',
    country: 'Portugal',
    countryCode: '+351',
    phoneNumber: '912345678',
    departmentID: '2',
    department: { id: '2', name: 'Physics' }
  },
  {
    id: 'JSM',
    name: 'Jane Smith',
    email: 'jane@isep.ipp.pt',
    nif: '987654321',
    academicBackground: 'MSc',
    street: 'Second St',
    postalCode: '4000-002',
    location: 'Lisbon',
    country: 'Portugal',
    countryCode: '+351',
    phoneNumber: '934567890',
    departmentID: '1',
    department: { id: '1', name: 'Math' }
  }
];

jest.mock('../../../services/teacherService');

describe('TeacherDisplay', () => {
  beforeEach(() => {
    teacherService.getAllTeachers.mockResolvedValue(mockTeachers);
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('renders a list of teachers', async () => {
    render(
        <MemoryRouter>
          <TeacherDisplay/>
        </MemoryRouter>
    );
    await waitFor(() => {
      // Use a flexible matcher for names (in case of extra markup or links)
      expect(screen.getByText((content) => content.includes('John Doe'))).toBeInTheDocument();
      expect(screen.getByText((content) => content.includes('Jane Smith'))).toBeInTheDocument();
      // expect(screen.getByText('Physics')).toBeInTheDocument();
      // expect(screen.getByText('Math')).toBeInTheDocument();
    });
  });

  it('renders message when no teachers are present', async () => {
    teacherService.getAllTeachers.mockResolvedValueOnce([]);
    render(
        <MemoryRouter>
          <TeacherDisplay/>
        </MemoryRouter>
    );
    await waitFor(() => {
      expect(screen.getByText((content) => content.includes('No results found'))).toBeInTheDocument();
      expect(screen.getByText(/No results found/i)).toBeInTheDocument();
    });
  });

  it('renders teacher email and NIF', async () => {
    render(
        <MemoryRouter>
          <TeacherDisplay/>
        </MemoryRouter>
    );
    await waitFor(() => {
      expect(screen.getByText('john@isep.ipp.pt')).toBeInTheDocument();
      expect(screen.getByText('987654321')).toBeInTheDocument();
    });
  });
});
