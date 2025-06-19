import { useEffect, useState, useRef } from "react";

export default function useFetchMultipleResources(links: string[]) {
    const [resources, setResources] = useState<Record<string, any>>({});
    const fetchedLinks = useRef<Set<string>>(new Set());

    useEffect(() => {
        if (!links || links.length === 0) return;

        async function fetchResources() {
            const newResources: Record<string, any> = {};
            const linksToFetch = links.filter(link => !fetchedLinks.current.has(link));

            if (linksToFetch.length === 0) return;

            try {
                const promises = linksToFetch.map(async (link) => {
                    const response = await fetch(link);
                    if (!response.ok) throw new Error(`Failed to fetch ${link}`);
                    const data = await response.json();
                    return { link, data };
                });

                const results = await Promise.all(promises);

                results.forEach(({ link, data }) => {
                    newResources[link] = data;
                    fetchedLinks.current.add(link);
                });

                if (Object.keys(newResources).length > 0) {
                    setResources(prev => ({ ...prev, ...newResources }));
                }
            } catch (error) {
                console.error("Error fetching resources:", error);
            }
        }

        fetchResources();
    }, [links]);

    return resources;
}