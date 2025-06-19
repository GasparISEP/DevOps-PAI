import { useState, useEffect } from 'react';

export default function useFetchMultipleResources(urls) {
    const [resources, setResources] = useState({});

    useEffect(() => {
        if (!urls || urls.length === 0) {
            setResources({});
            return;
        }

        let isMounted = true;
        Promise.all(
            urls.map(url =>
                fetch(url)
                    .then(res => res.json())
                    .catch(() => null)
            )
        ).then(results => {
            if (isMounted) {
                const resourcesMap = {};
                urls.forEach((url, i) => {
                    resourcesMap[url] = results[i];
                });
                setResources(resourcesMap);
            }
        });

        return () => {
            isMounted = false;
        };
    }, [urls]);

    return resources;
}
