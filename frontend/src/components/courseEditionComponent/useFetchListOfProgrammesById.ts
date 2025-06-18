import { useEffect, useState, useRef } from "react";

type Programme = {
    name: string;
    acronym: string;
    maxEcts: number;
    quantSemesters: number;
    degreeTypeID: string;
    departmentID: string;
    teacherID: string;
};

function arraysEqual(a: string[], b: string[]) {
    if (a.length !== b.length) return false;
    for (let i = 0; i < a.length; i++) {
        if (a[i] !== b[i]) return false;
    }
    return true;
}

export default function useFetchListOfProgrammesById(programmeAcronymList: string[]) {
    const [programmesMap, setProgrammesMap] = useState<Record<string, Programme | null>>({});
    const previousListRef = useRef<string[]>([]);

    useEffect(() => {
        if (!programmeAcronymList || programmeAcronymList.length === 0) {
            setProgrammesMap({});
            previousListRef.current = [];
            return;
        }

        if (arraysEqual(programmeAcronymList, previousListRef.current)) {
            // Same list as before — skip fetch
            return;
        }

        previousListRef.current = programmeAcronymList;

        async function fetchProgrammes() {
            try {
                const fetches = programmeAcronymList.map(async (acronym) => {
                    const res = await fetch(`${process.env.REACT_APP_API_URL}/programmes/id/${acronym}`);
                    if (!res.ok) {
                        console.error(`Failed to fetch programme for acronym: ${acronym}, status: ${res.status}`);
                        return null;
                    }
                    return await res.json();
                });

                const results = await Promise.all(fetches);

                const map: Record<string, Programme | null> = {};
                programmeAcronymList.forEach((acronym, idx) => {
                    map[acronym] = results[idx];
                });

                setProgrammesMap(map);
            } catch (error) {
                console.error("Error fetching programmes:", error);
                setProgrammesMap({});
            }
        }

        fetchProgrammes();
    }, [programmeAcronymList]);

    return programmesMap;
}
