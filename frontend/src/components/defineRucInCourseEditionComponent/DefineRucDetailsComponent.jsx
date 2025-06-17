import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import '../../styles/DisplayPage.css';
import NavBar from '../NavBar';
import Footer from '../Footer';

const API_BASE = process.env.REACT_APP_API_URL;

const DefineRucDetailsComponent = () => {
    const { id } = useParams();
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`${API_BASE}/course-editions/by-id/${id}`)
            .then(async (response) => {
                const text = await response.text();

                // Detect HTML error page
                if (text.trim().startsWith('<!DOCTYPE html>')) {
                    throw new Error('Received HTML instead of JSON — possible 404 or server error.');
                }

                try {
                    return JSON.parse(text);
                } catch (e) {
                    console.error('Invalid JSON response:', text);
                    throw new Error('Failed to parse JSON: ' + e.message);
                }
            })
            .then((json) => {
                setData(json);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
                setError(error.toString());
                setLoading(false);
            });
    }, [id]);

    return (
        <div className="component-page-wrapper">
            <NavBar />

            <div className="component-main-content">
                <div className="tcp-container">
                    <Link to="/" className="back-to-home-link">
                        Back to Home Page
                    </Link>

                    <h2 className="tcp-title">Course Edition Details</h2>

                    {loading && <p>Loading...</p>}

                    {error && <p style={{ color: 'red' }}>{error}</p>}

                    {data && (
                        <>
                            <table className="tcp-table">
                                <tbody>
                                <tr className="tcp-row">
                                    <th className="tcp-label">Course Edition ID</th>
                                    <td className="tcp-value">{data.courseEditionGeneratedID}</td>
                                </tr>
                                <tr className="tcp-row">
                                    <th className="tcp-label">Course Edition Name</th>
                                    <td className="tcp-value">{data.courseName || 'N/A'}</td>
                                </tr>
                                <tr className="tcp-row">
                                    <th className="tcp-label">RUC</th>
                                    <td className="tcp-value">{data.teacherID || 'N/A'}</td>
                                </tr>
                                </tbody>
                            </table>

                            <div style={{ marginTop: '1rem' }}>
                                <Link to={`/course-editions/by-id/${id}`} className="details-link">
                                    Refresh Details Page
                                </Link>
                            </div>
                        </>
                    )}
                </div>
            </div>

            <div className="component-footer-wrapper">
                <Footer />
            </div>
        </div>
    );
};

export default DefineRucDetailsComponent;
