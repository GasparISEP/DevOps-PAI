import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/Form.css';
import {
    getEnrolledProgrammes,
    getProgrammeEditions,
    getAvailableCourses,
    enrolStudent
} from '../../services/enrolmentService';

export default function EnrollStudentForm() {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        studentId: '',
        programme: '',
        edition: '',
        selectedCourses: []
    });

    const [studentName, setStudentName] = useState('');
    const [programmes, setProgrammes] = useState([]);
    const [editions, setEditions] = useState([]);
    const [courses, setCourses] = useState([]);
    const [totalEcts] = useState(60);

    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm(prev => ({
            ...prev,
            [name]: value,
            ...(name === 'programme' && { edition: '', selectedCourses: [] }),
            ...(name === 'edition' && { selectedCourses: [] })
        }));
    };

    const handleCheckboxChange = (acronym) => {
        const clean = acronym.trim().toUpperCase();
        setForm(prev => ({
            ...prev,
            selectedCourses: prev.selectedCourses.includes(clean)
                ? prev.selectedCourses.filter(c => c !== clean)
                : [...prev.selectedCourses, clean]
        }));
    };

    const handleClear = () => {
        setForm({
            studentId: '',
            programme: '',
            edition: '',
            selectedCourses: []
        });
        setProgrammes([]);
        setEditions([]);
        setCourses([]);
        setStudentName('');
        setError('');
        setSuccess(null);
        setShowModal(false);
        setShowErrorModal(false);
    };

    const handleStudentBlur = async () => {
        setError('');
        if (!form.studentId) return;
        try {
            const res = await getEnrolledProgrammes(form.studentId);
            setProgrammes(res.programmeInfo || []);
            setStudentName(res.studentName || '');
            if ((res.programmeInfo || []).length === 0) {
                setError('Este estudante não está inscrito em nenhum programa.');
            }
        } catch (err) {
            console.error(err);
            setError('Erro ao carregar programas.');
            setProgrammes([]);
            setStudentName('');
        }
    };

    useEffect(() => {
        const fetchEditions = async () => {
            if (!form.programme) {
                setEditions([]);
                return;
            }
            try {
                const res = await getProgrammeEditions(form.programme);
                setEditions(res.map(e => ({
                    key: e.description,
                    value: e.schoolYearId,
                    acronym: e.programmeAcronym
                })));
            } catch (err) {
                console.error(err);
                setError('Erro ao carregar edições.');
            }
        };
        fetchEditions();
    }, [form.programme]);

    useEffect(() => {
        const fetchCourses = async () => {
            if (!form.programme || !form.edition) return;
            const edition = editions.find(e => e.value === form.edition);
            if (!edition) return;
            try {
                const res = await getAvailableCourses({
                    programmeAcronym: edition.acronym,
                    schoolYearId: edition.value
                });
                const norm = res.map(c => ({
                    ...c,
                    qtyECTS: Number(c.qtyECTS) || 0,
                    acronym: c.acronym.trim().toUpperCase()
                }));
                setCourses(norm);
            } catch (err) {
                console.error(err);
                setError('Erro ao carregar cursos.');
            }
        };
        fetchCourses();
    }, [form.edition, editions, form.programme]);

    const selectedEcts = form.selectedCourses.reduce((sum, acr) => {
        const c = courses.find(c => c.acronym === acr);
        return sum + (c ? c.qtyECTS : 0);
    }, 0);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!form.studentId || !form.programme || !form.edition || form.selectedCourses.length === 0) {
            setError('Preencha todos os campos.');
            return;
        }

        const edition = editions.find(e => e.value === form.edition);
        const programme = programmes.find(p => p.generatedID === form.programme);
        if (!edition || !programme) {
            setError('Programa ou edição inválidos.');
            return;
        }

        const payload = {
            studentId: parseInt(form.studentId),
            programmeAcronym: edition.acronym,
            schoolYearId: edition.value,
            courseIds: form.selectedCourses.map(acr => {
                const c = courses.find(c => c.acronym === acr);
                return {
                    acronym: c.acronym,
                    name: c.name,
                    studyPlanDate: c.studyPlanDate,
                    programmeAcronym: c.programmeAcronym
                };
            })
        };

        const grouped = courses.reduce((acc, c) => {
            const key = `${c.curricularYear}º ANO | ${c.semester}º SEMESTRE`;
            if (!acc[key]) acc[key] = [];
            acc[key].push(c);
            return acc;
        }, {});

        try {
            await enrolStudent(form.studentId, payload);
            setSuccess({
                studentID: form.studentId,
                studentName: studentName,
                programmeName: programme.programmeName,
                editionDescription: edition.key,
                groupedCourses: grouped,
                selectedCourses: form.selectedCourses,
                selectedEcts: selectedEcts
            });
            setShowModal(true);
        } catch (err) {
            console.error(err);
            setError(err.message);
            setShowErrorModal(true);
        }
    };

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div student-img-background">
                    <div className="form-logo-img-div">
                        <img src="/assets/images/ISEP_logo-branco.png" alt="ISEP Logo" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1>Enroll a Student</h1>
                        <button type="button" className="pagination-btn2 pagination-btn-secondary" onClick={() => navigate("/")}>Back to Home Page</button>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <div className="form-group">
                                <label className="form-label">Student Unique Number</label>
                                <input type="text" name="studentId" className="form-input" value={form.studentId} onChange={handleChange} onBlur={handleStudentBlur} required />
                                {studentName && <p style={{ marginTop: '0.5rem', fontWeight: 'bold' }}>{studentName}</p>}
                            </div>

                            <div className="form-group">
                                <label className="form-label">Programme</label>
                                <select name="programme" className="form-input" value={form.programme} onChange={handleChange}>
                                    <option value="">-- Choose Programme --</option>
                                    {programmes.map(p => (
                                        <option key={p.generatedID} value={p.generatedID}>{p.programmeName}</option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label className="form-label">Programme Edition</label>
                                <select name="edition" className="form-input" value={form.edition} onChange={handleChange}>
                                    <option value="">-- Choose Edition --</option>
                                    {editions.map(e => (
                                        <option key={e.key} value={e.value}>{e.key}</option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label className="form-label">Courses</label>
                                {form.edition && (
                                    <p style={{ fontWeight: 'bold', marginBottom: '0.5rem' }}>ECTS — {selectedEcts} of {totalEcts}</p>
                                )}
                                {Object.entries(
                                    courses.reduce((groups, c) => {
                                        const key = `${c.curricularYear}º ANO | ${c.semester}º SEMESTRE`;
                                        if (!groups[key]) groups[key] = [];
                                        groups[key].push(c);
                                        return groups;
                                    }, {})
                                ).map(([group, groupCourses]) => (
                                    <div key={group} style={{ marginBottom: '1rem' }}>
                                        <h4>{group}</h4>
                                        <div className="form-checkbox-group">
                                            {groupCourses.map(c => (
                                                <label key={c.acronym} style={{ display: 'block' }}>
                                                    <input type="checkbox" value={c.acronym} checked={form.selectedCourses.includes(c.acronym)} onChange={() => handleCheckboxChange(c.acronym)} style={{ marginRight: '0.5rem' }} />
                                                    {c.name} ({c.qtyECTS} ECTS)
                                                </label>
                                            ))}
                                        </div>
                                    </div>
                                ))}
                            </div>

                            {error && <div className="error">{error}</div>}

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={handleClear}>CLEAR</button>
                                <button type="submit" className="btn btn-primary">ENROLL</button>
                            </div>
                        </div>
                    </div>
                </form>

                {showModal && success && (
                    <div className="modal-overlay">
                        <div className="modal-content">
                            <h2 style={{ color: 'green' }}>✅ Enrollment Successful</h2>
                            <p><strong>Student ID:</strong> {success.studentID}</p>
                            <p><strong>Student Name:</strong> {success.studentName}</p>
                            <p><strong>Programme Edition:</strong> {success.programmeName} — {success.editionDescription}</p>

                            <h3 style={{ fontSize: '1.2rem', marginTop: '1rem' }}>Courses:</h3>
                            <div style={{ textAlign: 'left', margin: '0 auto', maxWidth: '400px', fontSize: '1rem' }}>
                                {Object.entries(success.groupedCourses).map(([group, groupCourses]) => {
                                    const selected = groupCourses.filter(c => success.selectedCourses.includes(c.acronym));
                                    if (selected.length === 0) return null;
                                    return (
                                        <div key={group} style={{ marginBottom: '0.8rem' }}>
                                            <strong>{group}</strong>
                                            <ul style={{ margin: '0.4rem 0', paddingLeft: '1.2rem' }}>
                                                {selected.map(c => (
                                                    <li key={c.acronym}>{c.name} ({c.qtyECTS} ECTS)</li>
                                                ))}
                                            </ul>
                                        </div>
                                    );
                                })}
                            </div>

                            <p style={{ marginTop: '1rem', fontWeight: 'bold', fontSize: '1rem' }}>
                                ECTS Used: {success.selectedEcts} / {totalEcts}
                            </p>

                            <button className="modal-btn" onClick={() => {
                                setShowModal(false);
                                window.location.reload();
                            }}>Close</button>
                        </div>
                    </div>
                )}

                {showErrorModal && (
                    <div className="modal-overlay">
                        <div className="modal-content" style={{ borderColor: 'red' }}>
                            <h2 style={{ color: 'red' }}>Enrollment Error</h2>
                            <p>{error}</p>
                            <button className="modal-btn" onClick={() => setShowErrorModal(false)}>Close</button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}