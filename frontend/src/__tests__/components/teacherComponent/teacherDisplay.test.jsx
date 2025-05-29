import React from 'react';
import { render, screen, waitFor, fireEvent } from '@testing-library/react';
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

  test('clicking the Name header sorts teachers asc and desc by name', async () => {
    const teachers = [
      { id: 'B', name: 'Bob', email: 'bob@isep.ipp.pt', nif: '222', academicBackground: 'MSc', street: 'Street 2', postalCode: '2000', location: 'Lisbon', country: 'Portugal', countryCode: '+351', phoneNumber: '922', departmentID: '2', department: { id: '2', name: 'Physics' } },
      { id: 'A', name: 'Alice', email: 'alice@isep.ipp.pt', nif: '111', academicBackground: 'BSc', street: 'Street 1', postalCode: '1000', location: 'Porto', country: 'Portugal', countryCode: '+351', phoneNumber: '911', departmentID: '1', department: { id: '1', name: 'Math' } }
    ];
    teacherService.getAllTeachers.mockResolvedValueOnce(teachers);
    render(
        <MemoryRouter>
          <TeacherDisplay />
        </MemoryRouter>
    );
    await waitFor(() => expect(screen.getByText('Alice')).toBeInTheDocument());
    const nameHeader = screen.getByRole('columnheader', { name: /Name/i });

    fireEvent.click(nameHeader);
    await waitFor(() => {
      const rows = screen.getAllByRole('row');
      expect(rows[1].textContent).toContain('Alice');
      expect(rows[2].textContent).toContain('Bob');
    });

    fireEvent.click(nameHeader);
    await waitFor(() => {
      const rows = screen.getAllByRole('row');
      expect(rows[1].textContent).toContain('Bob');
      expect(rows[2].textContent).toContain('Alice');
    });
  });

  test('filters teachers by string field (name)', async () => {
    const manyTeachers = [
      { id: 'JDO', name: 'John Doe', email: 'john@isep.ipp.pt', nif: '123456789', academicBackground: 'PhD', street: 'Main St', postalCode: '4000-001', location: 'Porto', country: 'Portugal', countryCode: '+351', phoneNumber: '912345678', departmentID: '2', department: { id: '2', name: 'Physics' } },
    ];
    teacherService.getAllTeachers.mockResolvedValueOnce(manyTeachers);
    render(
        <MemoryRouter>
          <TeacherDisplay />
        </MemoryRouter>
    );

    await waitFor(() => expect(screen.getByText('John Doe')).toBeInTheDocument());

    const filterInput = screen.getByPlaceholderText(/Search by Name/i);
    fireEvent.change(filterInput, { target: { value: 'Jane' } });
    expect(screen.queryByText('John Doe')).not.toBeInTheDocument();
  });

  test('filter teacher returns false for non-string field (nif)', async () => {
    const manyTeachers = [
      { id: 'JDO', name: 'John Doe', email: 'john@isep.ipp.pt', nif: 123456789, academicBackground: 'PhD', street: 'Main St', postalCode: '4000-001', location: 'Porto', country: 'Portugal', countryCode: '+351', phoneNumber: '912345678', departmentID: '2', department: { id: '2', name: 'Physics' } },
    ];
    teacherService.getAllTeachers.mockResolvedValueOnce(manyTeachers);
    render(
        <MemoryRouter>
          <TeacherDisplay />
        </MemoryRouter>
    );
    await waitFor(() => expect(screen.getByText('John Doe')).toBeInTheDocument());

    const filterSelect = screen.getByDisplayValue('Name');
    fireEvent.change(filterSelect, { target: { value: 'nif' } });
    const nifInput = screen.getByPlaceholderText(/Search by NIF/i);
    fireEvent.change(nifInput, { target: { value: 'abc' } });
    expect(screen.queryByText('John Doe')).not.toBeInTheDocument();
  });

  test('renders a list of teachers', async () => {
    render(
        <MemoryRouter>
          <TeacherDisplay/>
        </MemoryRouter>
    );
    await waitFor(() => {
      expect(screen.getByText((content) => content.includes('John Doe'))).toBeInTheDocument();
      expect(screen.getByText((content) => content.includes('Jane Smith'))).toBeInTheDocument();

    });
  });

  test('renders message when no teachers are present', async () => {
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

  test('sets error to "Failed to load teachers" when loading fails', async () => {
    teacherService.getAllTeachers.mockRejectedValueOnce(new Error('Network error'));
    render(
      <MemoryRouter>
        <TeacherDisplay />
      </MemoryRouter>
    );
    await waitFor(() => {
      expect(screen.getByText('No results found')).toBeInTheDocument();
    });
  });

  test('renders teacher email and NIF', async () => {
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

  test('renders all table headers', async () => {
    render(
        <MemoryRouter>
          <TeacherDisplay />
        </MemoryRouter>
    );
    const headers = await screen.findAllByRole('columnheader');
    const headerTexts = headers.map(h => h.textContent);
    expect(headerTexts).toEqual(
        expect.arrayContaining([
          expect.stringContaining('Name'),
          expect.stringContaining('Acronym'),
          expect.stringContaining('Email'),
          expect.stringContaining('NIF'),
          expect.stringContaining('Academic Background'),
          expect.stringContaining('Street'),
          expect.stringContaining('Postal Code'),
          expect.stringContaining('Location'),
          expect.stringContaining('Country'),
          expect.stringContaining('Department')
        ])
    );
  });

  test('renders all table headers and sort icons', async () => {
    render(
      <MemoryRouter>
        <TeacherDisplay />
      </MemoryRouter>
    );

    const headers = await screen.findAllByRole('columnheader');
    const headerLabels = [
      'Name',
      'Acronym',
      'Email',
      'NIF',
      'Academic Background',
      'Street',
      'Postal Code',
      'Location',
      'Country',
      'Department'
    ];

    const clickAndCheckSortIcon = async (label) => {
      const header = headers.find(h => h.textContent.includes(label));
      expect(header).toBeInTheDocument();
      fireEvent.click(header);
      await waitFor(() => {
        expect(header.textContent).toMatch(/▲|▼/);
      });
    };

    for (const label of headerLabels) {
      await clickAndCheckSortIcon(label);
    }
  });

  test('page next/previous buttons work correctly', async () => {

    const manyTeachers = Array.from({ length: 45 }, (_, i) => ({
      id: `T${i+1}`,
      name: `Teacher ${i+1}`,
      email: `teacher${i+1}@isep.ipp.pt`,
      nif: `${100000000 + i}`,
      academicBackground: 'PhD',
      street: 'Main St',
      postalCode: '4000-001',
      location: 'Porto',
      country: 'Portugal',
      countryCode: '+351',
      phoneNumber: '912345678',
      departmentID: '2',
      department: { id: '2', name: 'Physics' }
    }));
    teacherService.getAllTeachers.mockResolvedValueOnce(manyTeachers);

    render(
      <MemoryRouter>
        <TeacherDisplay />
      </MemoryRouter>
    );

    await waitFor(() => expect(screen.getByText(/Page 1 of/i)).toBeInTheDocument());

    let nextButton = screen.getByRole('button', { name: /Next/i });
    let prevButton = screen.getByRole('button', { name: /Previous/i });
    let pageInfo = screen.getByText(/Page \d+ of \d+/i);

    expect(prevButton).toBeDisabled();
    expect(pageInfo.textContent).toMatch(/Page 1 of \d+/);

    fireEvent.click(nextButton);
    await waitFor(() => {
      expect(screen.getByText(/Page 2 of \d+/)).toBeInTheDocument();
    });

    prevButton = screen.getByRole('button', { name: /Previous/i });
    expect(prevButton).not.toBeDisabled();

    fireEvent.click(prevButton);
    await waitFor(() => {
      expect(screen.getByText(/Page 1 of \d+/)).toBeInTheDocument();
    });
    prevButton = screen.getByRole('button', { name: /Previous/i });
    expect(prevButton).toBeDisabled();

    const totalPages = Math.ceil(manyTeachers.length / 20);
    nextButton = screen.getByRole('button', { name: /Next/i });
    for (let i = 2; i <= totalPages; i++) {
      fireEvent.click(nextButton);
      await waitFor(() => {
        expect(screen.getByText(new RegExp(`Page ${i} of ${totalPages}`))).toBeInTheDocument();
      });
      nextButton = screen.getByRole('button', { name: /Next/i });
    }
    expect(nextButton).toBeDisabled();
  });

  test('clicking per-page button changes teachersPerPage and updates pagination', async () => {
    const teachers = Array.from({ length: 25 }, (_, i) => ({
      id: `T${i+1}`,
      name: `Teacher ${i+1}`,
      email: `teacher${i+1}@isep.ipp.pt`,
      nif: `${100000000 + i}`,
      academicBackground: 'PhD',
      street: 'Main St',
      postalCode: '4000-001',
      location: 'Porto',
      country: 'Portugal',
      countryCode: '+351',
      phoneNumber: '912345678',
      departmentID: '2',
      department: { id: '2', name: 'Physics' }
    }));
    teacherService.getAllTeachers.mockResolvedValueOnce(teachers);
    render(
      <MemoryRouter>
        <TeacherDisplay />
      </MemoryRouter>
    );
    await waitFor(() => expect(screen.getByText('Teacher 1')).toBeInTheDocument());
    // Default is 20 per page, so page info should be Page 1 of 2
    expect(screen.getByText(/Page 1 of 2/)).toBeInTheDocument();
    // Click per-page button for 10
    const perPageBtn10 = screen.getByRole('button', { name: '10' });
    fireEvent.click(perPageBtn10);
    await waitFor(() => {
      // Now there should be 3 pages
      expect(screen.getByText(/Page 1 of 3/)).toBeInTheDocument();
    });
    // Click per-page button for 5
    const perPageBtn5 = screen.getByRole('button', { name: '5' });
    fireEvent.click(perPageBtn5);
    await waitFor(() => {
      expect(screen.getByText(/Page 1 of 5/)).toBeInTheDocument();
    });
    // Click per-page button for 50 (should be 1 page)
    const perPageBtn50 = screen.getByRole('button', { name: '50' });
    fireEvent.click(perPageBtn50);
    await waitFor(() => {
      expect(screen.getByText(/Page 1 of 1/)).toBeInTheDocument();
    });
  });

});
