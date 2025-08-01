import React from 'react';
import {render, screen, fireEvent, waitFor, within} from '@testing-library/react';
import '@testing-library/jest-dom';
import TeacherCareerProgressionDisplay
    from '../../../components/teacherCareerProgressionComponent/TeacherCareerProgressionDisplay';
import {MemoryRouter} from 'react-router-dom';

describe('TeacherCareerProgressionDisplay', () => {

    beforeEach(() => {
        // Clean up mocks before each test
        jest.clearAllMocks();
    });

// Loading spinner tests

    test('displays loading spinner initially', () => {
        render(<TeacherCareerProgressionDisplay/>);
        const spinner = screen.getByTestId('loading-spinner');
        expect(spinner).toBeInTheDocument();
    });

    test('hides loading spinner after data is fetched', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        // Waits for data to load
        await screen.findByText('2023-01-01');

        // Checks that the spinner is no longer in the document
        expect(screen.queryByTestId('loading-spinner')).not.toBeInTheDocument();
    });

// Fetch tests

    test('renders data rows after successful fetch', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        expect(await screen.findByText('2023-01-01')).toBeInTheDocument();

        expect(screen.getByText('100')).toBeInTheDocument();
    });

    test('shows error message on fetch failure for teacher progression', async () => {
        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: false,
                status: 500
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        const error = await screen.findByText(/Failed to load/i);

        expect(error).toBeInTheDocument();
    });

    test('shows error message on fetch failure for categories', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: false,
                status: 500
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        const error = await screen.findByText(/Failed to load/i);

        expect(error).toBeInTheDocument();
    });

    test('shows error message on fetch failure', async () => {
        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: false,
                status: 500
            })
            .mockResolvedValueOnce({
                ok: true,
                status: 500
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        const error = await screen.findByText(/Failed to load/i);

        expect(error).toBeInTheDocument();
    });

    test('displays "No results found" when API returns empty list', async () => {
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve([]),
            })
        );

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        const emptyMessage = await screen.findByText(/No results found/i);

        expect(emptyMessage).toBeInTheDocument();
    });

// "Filter by" tests

    test('filters out items with unsupported field types', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 101,
                unsupportedField: { something: 'wrong type' } // 👈 not string or number
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay />
            </MemoryRouter>
        );

        // Wait for table to load
        await screen.findByText('2023-01-01');

        // Change filter to the unsupported field
        const filterSelect = screen.getByRole('combobox');
        fireEvent.change(filterSelect, { target: { value: 'unsupportedField' } });

        // Enter a filter value (any string)
        const input = screen.getByRole('textbox');
        fireEvent.change(input, { target: { value: 'test' } });

        // Table should show "No results found"
        expect(await screen.findByText('No results found')).toBeInTheDocument();
    });


    test('filters data by date', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID:"1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            },
            {
                teacherCareerProgressionId: 2,
                date: '2024-05-10',
                workingPercentage: 80,
                teacherCategoryID: "b2c4d678-90ab-4321-8cde-1234567890ab",
                teacherID: 6
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
            {
                id: "b2c4d678-90ab-4321-8cde-1234567890ab",
                name: "Professor Adjunto",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        const input = screen.getByPlaceholderText(/Search by Date/i);
        fireEvent.change(input, {target: {value: '2024'}});

        expect(await screen.findByText('2024-05-10')).toBeInTheDocument();
        expect(screen.queryByText('2023-01-01')).not.toBeInTheDocument();
    });

    test('filters data by working percentage', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            },
            {
                teacherCareerProgressionId: 2,
                date: '2024-05-10',
                workingPercentage: 80,
                teacherCategoryID: "b2c4d678-90ab-4321-8cde-1234567890ab",
                teacherID: 6
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
            {
                id: "b2c4d678-90ab-4321-8cde-1234567890ab",
                name: "Professor Adjunto",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        fireEvent.change(screen.getByDisplayValue('Date'), {target: {value: 'workingPercentage'}});
        fireEvent.change(screen.getByPlaceholderText(/Working Percentage/i), {target: {value: '80'}});

        expect(await screen.findByText('80')).toBeInTheDocument();
        expect(screen.queryByText('100')).not.toBeInTheDocument();
    });


    test('filters data by Teacher Career Progression ID', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: "1a23b456-7890-1234-5678-9abcdef01234",
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            },
            {
                teacherCareerProgressionId: "b2c4d678-90ab-4321-8cde-1234567890ab",
                date: '2024-05-10',
                workingPercentage: 80,
                teacherCategoryID: "b2c4d678-90ab-4321-8cde-1234567890ab",
                teacherID: 6
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
            {
                id: "b2c4d678-90ab-4321-8cde-1234567890ab",
                name: "Professor Adjunto",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });


        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        fireEvent.change(screen.getByDisplayValue('Date'), {target: {value: 'teacherCareerProgressionId'}});
        fireEvent.change(screen.getByPlaceholderText(/ID/i), {target: {value: 'b2c4d678-90ab-4321-8cde-1234567890ab'}});

        expect(await screen.findByText('b2c4d678-90ab-4321-8cde-1234567890ab')).toBeInTheDocument();
        expect(screen.queryByText('1a23b456-7890-1234-5678-9abcdef01234')).not.toBeInTheDocument();
    });

    test('filters data by Teacher Category', async () => {
        const mockProgressionData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5
            },
            {
                teacherCareerProgressionId: 2,
                date: '2024-05-10',
                workingPercentage: 80,
                teacherCategoryID: "b2c4d678-90ab-4321-8cde-1234567890ab",
                teacherID: 6
            }
        ];
        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
            {
                id: "b2c4d678-90ab-4321-8cde-1234567890ab",
                name: "Professor Adjunto",
            }
        ];

        global.fetch = jest.fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockProgressionData
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        fireEvent.change(screen.getByDisplayValue('Date'), {target: {value: 'teacherCategoryName'}});
        fireEvent.change(screen.getByPlaceholderText(/Teacher Category/i), {target: {value: "Professor Assistente"}});

        expect(await screen.findByText("Professor Assistente")).toBeInTheDocument();
        expect(screen.queryByText('Professor Adjunto')).not.toBeInTheDocument();
    });

    test('filters data by Teacher', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID:"1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: "b2c4d678-90ab-4321-8cde-1234567890ab"
            },
            {
                teacherCareerProgressionId: 2,
                date: '2024-05-10',
                workingPercentage: 80,
                teacherCategoryID: "b2c4d678-90ab-4321-8cde-1234567890ab",
                teacherID: "1a23b456-7890-1234-5678-9abcdef01234"
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
            {
                id: "b2c4d678-90ab-4321-8cde-1234567890ab",
                name: "Professor Adjunto",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        fireEvent.change(screen.getByDisplayValue('Date'), {target: {value: 'teacherID'}});
        fireEvent.change(screen.getByPlaceholderText(/Teacher/i), {target: {value: '1a23b456-7890-1234-5678-9abcdef01234'}});

        expect(await screen.findByText('1a23b456-7890-1234-5678-9abcdef01234')).toBeInTheDocument();
        expect(screen.queryByText('b2c4d678-90ab-4321-8cde-1234567890ab')).not.toBeInTheDocument();
    });


    test('displays "No results found" when filter yields no match', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 5,
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });


        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        // Apply filter that won’t match
        fireEvent.change(screen.getByPlaceholderText(/Search by Date/i), {
            target: {value: 'gibberish-unlikely-to-match'}
        });

        expect(await screen.findByText(/no results found/i)).toBeInTheDocument();
    });


// Sorting tests - parameterized

    const mockData = [
        {
            teacherCareerProgressionId: 'b1f55d7c-9c11-4cb3-920e-7b9fcb5bde13',
            date: '2023-01-01',
            workingPercentage: 50,
            teacherCategoryID: 'f8a12b1a-d3d6-4cd0-8b0f-9c412d839e9e',
            teacherID: 'a32457dd-2ab0-4f5d-baae-c9c1453e937e',
        },
        {
            teacherCareerProgressionId: '8d56a676-b21a-40b2-9c6e-c85d9372e1f2',
            date: '2022-01-01',
            workingPercentage: 100,
            teacherCategoryID: '9b4f4e9b-27a9-4b88-96c3-52cb12f02e24',
            teacherID: 'b6732ef2-8932-4a2a-8452-b7e7eea9d273',
        },
    ];

    const mockCategoriesData = [
        {
            id: 'f8a12b1a-d3d6-4cd0-8b0f-9c412d839e9e',
            name: "Professor Assistente",
        },
        {
            id: '9b4f4e9b-27a9-4b88-96c3-52cb12f02e24',
            name: "Professor Adjunto",
        }
    ];

    const testCases = [
        {
            columnHeaderTestId: 'date-header',
            expectedOrderAsc: ['2022-01-01', '2023-01-01'],
            expectedOrderDesc: ['2023-01-01', '2022-01-01'],
        },
        {
            columnHeaderTestId: 'working-percentage-header',
            expectedOrderAsc: ['50', '100'],
            expectedOrderDesc: ['100', '50'],
        },
        {
            columnHeaderTestId: 'id-header',
            expectedOrderAsc: [
                '8d56a676-b21a-40b2-9c6e-c85d9372e1f2',
                'b1f55d7c-9c11-4cb3-920e-7b9fcb5bde13',
            ],
            expectedOrderDesc: [
                'b1f55d7c-9c11-4cb3-920e-7b9fcb5bde13',
                '8d56a676-b21a-40b2-9c6e-c85d9372e1f2',
            ],
        },
        {
            columnHeaderTestId: 'teacher-category-header',
            expectedOrderAsc: [
                'Professor Adjunto',
                'Professor Assistente'
            ],
            expectedOrderDesc: [
                'Professor Assistente',
                'Professor Adjunto',
            ],
        },
        {
            columnHeaderTestId: 'teacher-header',
            expectedOrderAsc: [
                'a32457dd-2ab0-4f5d-baae-c9c1453e937e',
                'b6732ef2-8932-4a2a-8452-b7e7eea9d273',
            ],
            expectedOrderDesc: [
                'b6732ef2-8932-4a2a-8452-b7e7eea9d273',
                'a32457dd-2ab0-4f5d-baae-c9c1453e937e',
            ],
        },
    ];


    test('sorting handles equal values correctly', async () => {
        const mockData = [
            {
                teacherCareerProgressionId: 1,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 101,
            },
            {
                teacherCareerProgressionId: 2,
                date: '2023-01-01',
                workingPercentage: 100,
                teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
                teacherID: 102,
            }
        ];

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay />
            </MemoryRouter>
        );

        // Confirm both rows with same date appear
        const dateCells = await screen.findAllByText('2023-01-01');
        expect(dateCells).toHaveLength(2);

        // Click to trigger sorting (even if values are equal)
        const dateHeader = screen.getByTestId('date-header');
        fireEvent.click(dateHeader);

        // Check that the order hasn't changed (ensuring return 0 was hit)
        const rows = screen.getAllByRole('row');
        const firstRowText = rows[1].textContent;
        const secondRowText = rows[2].textContent;

        expect(firstRowText).toContain('101');
        expect(secondRowText).toContain('102');
    });


    describe('TeacherCareerProgressionDisplay sorting', () => {
        beforeEach(() => {
            global.fetch = jest.fn()
                .mockResolvedValueOnce({
                    ok: true,
                    json: async () => mockData
                })
                .mockResolvedValueOnce({
                    ok: true,
                    json: async () => mockCategoriesData
                });
        });

        afterEach(() => {
            jest.resetAllMocks();
        });

        test.each(testCases)(
            'sorts column correctly when clicking $columnHeaderTestId',
            async ({columnHeaderTestId, expectedOrderAsc, expectedOrderDesc}) => {
                render(
                    <MemoryRouter>
                        <TeacherCareerProgressionDisplay/>
                    </MemoryRouter>
                );

                // Wait for table to load
                await screen.findByText(mockData[0].date);

                const header = screen.getByTestId(columnHeaderTestId);

                fireEvent.click(header);
                const rowsAsc = screen.getAllByRole('row').slice(1).map(row => row.textContent);
                expectedOrderAsc.forEach((value, i) => expect(rowsAsc[i]).toContain(value));

                fireEvent.click(header);
                const rowsDesc = screen.getAllByRole('row').slice(1).map(row => row.textContent);
                expectedOrderDesc.forEach((value, i) => expect(rowsDesc[i]).toContain(value));
            }
        );
    });


// Next and Previous controls tests

    test('navigates pages using Next button', async () => {
        const mockData = Array.from({length: 25}, (_, i) => ({
            teacherCareerProgressionId: i + 1,
            date: `2023-01-${String(i + 1).padStart(2, '0')}`,
            workingPercentage: 100,
            teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
            teacherID: i + 1,
        }));

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });


        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        const nextButton = screen.getByText(/Next/i);

        fireEvent.click(nextButton);

        expect(await screen.findByText('2023-01-21')).toBeInTheDocument();
    });


    test('navigates pages using Previous button', async () => {
        const mockData = Array.from({length: 25}, (_, i) => ({
            teacherCareerProgressionId: i + 1,
            date: `2023-01-${String(i + 1).padStart(2, '0')}`,
            workingPercentage: 100,
            teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
            teacherID: i + 1,
        }));

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            }
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });


        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        // Wait for initial load
        await screen.findByText('2023-01-01');

        // Go to page 2
        fireEvent.click(screen.getByText(/Next/i));
        await screen.findByText('2023-01-21'); // should now be on second page

        // Now go back to page 1
        fireEvent.click(screen.getByText(/Previous/i));

        // Check that page 1 data is shown again
        expect(await screen.findByText('2023-01-01')).toBeInTheDocument();
    });

    test('disables previous button on first page', async () => {
        const mockData = Array.from({length: 25}, (_, i) => ({
            teacherCareerProgressionId: i + 1,
            date: `2023-01-${String(i + 1).padStart(2, '0')}`,
            workingPercentage: 100,
            teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
            teacherID: i + 1,
        }));

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        const previousButton = screen.getByText(/Previous/i);
        expect(previousButton).toBeDisabled();
    });


    test('disables next button on last page', async () => {
        const mockData = Array.from({length: 25}, (_, i) => ({
            teacherCareerProgressionId: i + 1,
            date: `2023-01-${String(i + 1).padStart(2, '0')}`,
            workingPercentage: 100,
            teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
            teacherID: i + 1,
        }));

        const mockCategoryData = [
            {
                id: "1a23b456-7890-1234-5678-9abcdef01234",
                name: "Professor Assistente",
            },
        ];

        global.fetch = jest
            .fn()
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockData,
            })
            .mockResolvedValueOnce({
                ok: true,
                json: async () => mockCategoryData,
            });

        render(
            <MemoryRouter>
                <TeacherCareerProgressionDisplay/>
            </MemoryRouter>
        );

        await screen.findByText('2023-01-01');

        // Going to page 2
        fireEvent.click(screen.getByText(/Next/i));
        await screen.findByText('2023-01-21');

        // Re-query the button AFTER rerender
        const updatedNextButton = screen.getByText(/Next/i);
        expect(updatedNextButton).toBeDisabled();
    });

// Pagination controls tests

    const mockDataForPaginationTests = Array.from({length: 50}, (_, i) => ({
        teacherCareerProgressionId: i + 1,
        date: `2023-01-${String(i + 1).padStart(2, '0')}`,
        workingPercentage: 100,
        teacherCategoryID: "1a23b456-7890-1234-5678-9abcdef01234",
        teacherID: i + 1,
    }));

    const mockCategoryDataForPaginationTests = [
        {
            id: "1a23b456-7890-1234-5678-9abcdef01234",
            name: "Professor Assistente",
        },
    ];

    describe('Pagination: Per Page Controls', () => {
        beforeEach(async () => {
            global.fetch = jest.fn()
                .mockResolvedValueOnce({
                    ok: true,
                    json: async () => mockDataForPaginationTests
                })
                .mockResolvedValueOnce({
                    ok: true,
                    json: async () => mockCategoryDataForPaginationTests
                });

            render(
                <MemoryRouter>
                    <TeacherCareerProgressionDisplay/>
                </MemoryRouter>
            );

            // Wait for initial render
            await screen.findByText('2023-01-01');
        });

        test('default per-page value is 20 and is selected/disabled', () => {
            const btn20 = screen.getByRole('button', {name: '20'});
            expect(btn20).toBeDisabled();
        });

        test('renders correct number of items per page (default 20)', async () => {
            await waitFor(() =>
                expect(screen.queryByTestId('loading-spinner')).not.toBeInTheDocument()
            );

            const table = screen.getByTestId('career-progression-table');
            const rows = within(table).getAllByRole('row');
            expect(rows.length - 1).toBe(20); // exclude header row
        });

        test('pagination: resets to first page on filter change', async () => {
            await waitFor(() => screen.getByPlaceholderText(/Search by/i));
            fireEvent.click(screen.getByText('Next')); // Page 2
            fireEvent.change(screen.getByPlaceholderText(/Search by/i), {target: {value: 'Teacher1'}});
            await waitFor(() => expect(screen.getByText(/Page 1 of/)).toBeInTheDocument());
        });

        test('total pages update when per-page value changes', () => {
            fireEvent.click(screen.getByRole('button', {name: '5'}));
            expect(screen.getByText('Page 1 of 10')).toBeInTheDocument();

            fireEvent.click(screen.getByRole('button', {name: '50'}));
            expect(screen.getByText('Page 1 of 1')).toBeInTheDocument();
        });

        test('clicking same selected per-page value does not change state', () => {
            const btn20 = screen.getByRole('button', {name: '20'});
            fireEvent.click(btn20); // already selected

            expect(screen.getByText('Page 1 of 3')).toBeInTheDocument(); // 50 items, 20 per page
        });

        test.each([5, 10, 20, 50])('per-page option %i renders correct number of rows', async (value) => {
            fireEvent.click(screen.getByRole('button', {name: value.toString()}));

            const expectedCount = Math.min(mockDataForPaginationTests.length, value);
            const rows = screen.getAllByRole('row');
            expect(rows.length).toBe(expectedCount + 1); // header + data rows
        });
    });
});