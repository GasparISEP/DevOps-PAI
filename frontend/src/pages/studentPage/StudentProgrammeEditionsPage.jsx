import React, { useState, useEffect } from 'react';
import { useSearchParams, Link } from 'react-router-dom';
import NavBar from '../../components/NavBar';
import Footer from '../../components/Footer';
import '../../styles/DisplayATeacherCareerProgression.css';

export default function StudentProgrammeEditionsPage() {
    const [params] = useSearchParams();
    const link = params.get('link');

    const [editions, setEditions] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        if (!link) {
            setError('No link provided.');
            return;
        }

        const apiLink = link.startsWith('http') ? link : `${process.env.REACT_APP_API_URL}${link}`;

        async function fetchEditions() {
            try {
                const res = await fetch(apiLink);
                const data = await res.json();
                console.log("[Programme Editions] RAW:", data);

                if (data._embedded?.programmeEditionEnrolmentDetailDtoList) {
                    setEditions(data._embedded.programmeEditionEnrolmentDetailDtoList);
                } else if (Array.isArray(data)) {
                    setEditions(data);
                } else {
                    setError('Invalid data format.');
                }
            } catch (err) {
                console.error(err);
                setError('Failed to load programme editions.');
            }
        }

        fetchEditions();
    }, [link]);

    return (
        <div className="component-page-wrapper">
            <NavBar />

            <div className="component-main-content">
                <div className="tcp-container">
                    <Link to="/" className="back-to-home-link">
                        Back to Home Page
                    </Link>

                    <h2 className="tcp-title">Student Programme Editions</h2>

                    {error && <p className="error">{error}</p>}
                    {editions.length === 0 && !error && (
                        <p className="no-data">No programme editions enrolled.</p>
                    )}

                    {editions.map((edition, idx) => (
                        <table key={idx} className="tcp-table">
                            <tbody>
                            <tr className="tcp-row">
                                <th className="tcp-label">Programme</th>
                                <td className="tcp-value">{edition.programmeAcronym}</td>
                            </tr>
                            <tr className="tcp-row">
                                <th className="tcp-label">School Year</th>
                                <td className="tcp-value">{edition.schoolYearDescription}</td>
                            </tr>
                            </tbody>
                        </table>
                    ))}
                </div>
            </div>

            <div className="component-footer-wrapper">
                <Footer />
            </div>
        </div>
    );
}