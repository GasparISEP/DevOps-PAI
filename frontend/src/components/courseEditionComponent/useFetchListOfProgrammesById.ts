import { useEffect, useState } from "react";
import isEqual from "lodash.isequal";

export default function useFetchListOfProgrammesById(programmeAcronymList: string[]) {
    const [programmesMap, setProgrammesMap] = useState<Record<string, any>>({});

    useEffect(() => {
        if (!programmeAcronymList || programmeAcronymList.length === 0) {
            setProgrammesMap({});
            return;
        }

        async function fetchProgrammes() {
            const fetches = programmeAcronymList.map(async (acronym) => {
                const res = await fetch(`${process.env.REACT_APP_API_URL}/programmes/id/${acronym}`);
                if (!res.ok) return null;
                return await res.json();
            });

            const results = await Promise.all(fetches);

            const map: Record<string, any> = {};
            programmeAcronymList.forEach((acronym, idx) => {
                map[acronym] = results[idx];
            });

            setProgrammesMap(prev => {
                if (isEqual(prev, map)) return prev;
                return map;
            });
        }

        fetchProgrammes();
    }, [programmeAcronymList]);

    return programmesMap;
}
