import { renderHook, waitFor } from '@testing-library/react';
import useFetchListOfProgrammesById from '../../../components/courseEditionComponent/useFetchListOfProgrammesById';
import fetchMock from 'jest-fetch-mock';

beforeAll(() => {
    fetchMock.enableMocks();
    process.env.REACT_APP_API_URL = 'http://mocked-api.com';
});

beforeEach(() => {
    fetchMock.resetMocks();
});

describe('useFetchListOfProgrammesById', () => {
    it('returns an empty object if the list is empty', async () => {
        const { result } = renderHook(() => useFetchListOfProgrammesById([]));
        expect(result.current).toEqual({});
    });

    it('fetches data for each acronym and updates state', async () => {
        fetchMock.mockResponses(
            [JSON.stringify({ name: 'Programme A' }), { status: 200 }],
            [JSON.stringify({ name: 'Programme B' }), { status: 200 }]
        );

        const acronyms = ['PA', 'PB'];

        const { result } = renderHook(() =>
            useFetchListOfProgrammesById(acronyms)
        );

        await waitFor(
            () =>
                expect(result.current).toEqual({
                    PA: { name: 'Programme A' },
                    PB: { name: 'Programme B' },
                }),
            { timeout: 2000 }
        );

        expect(fetchMock).toHaveBeenCalledTimes(2);
        expect(fetchMock).toHaveBeenCalledWith(
            'http://mocked-api.com/programmes/id/PA'
        );
        expect(fetchMock).toHaveBeenCalledWith(
            'http://mocked-api.com/programmes/id/PB'
        );
    });

    it('does not fetch again or update state if data is equal (isEqual check)', async () => {
        const acronyms = ['PA'];
        const mockResponse = { name: 'Same Programme' };

        fetchMock.mockResponseOnce(JSON.stringify(mockResponse));

        const { result, rerender } = renderHook(
            ({ list }) => useFetchListOfProgrammesById(list),
            { initialProps: { list: acronyms } }
        );

        await waitFor(() =>
                expect(result.current).toEqual({ PA: mockResponse }),
            { timeout: 2000 }
        );

        rerender({ list: [...acronyms] }); // new reference, but same data

        await waitFor(() =>
                expect(result.current).toEqual({ PA: mockResponse }),
            { timeout: 2000 }
        );

        expect(fetchMock).toHaveBeenCalledTimes(1);
    });


    it('sets null for failed fetch (res.ok === false)', async () => {
        fetchMock.mockResponseOnce('', { status: 404 });

        const acronyms = ['INVALID'];

        const { result } = renderHook(() =>
            useFetchListOfProgrammesById(acronyms)
        );

        await waitFor(
            () =>
                expect(result.current).toEqual({
                    INVALID: null,
                }),
            { timeout: 2000 }
        );
    });
});
