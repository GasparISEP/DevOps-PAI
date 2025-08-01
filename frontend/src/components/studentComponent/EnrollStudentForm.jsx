import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/Form.css';
import '../../styles/Buttons.css';
import logo from '../../assets/images/ISEP_logo-branco.png';
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
    const [studentIdError, setStudentIdError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [reviewData, setReviewData] = useState(null);
    const [showReviewModal, setShowReviewModal] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;

        if (name === "studentId") {
            const sanitized = value.replace(/\D/g, '');

            if (value !== sanitized) {
                setStudentIdError("Student ID must contain only numbers.");
                return;
            }

            if (sanitized === "") {
                setStudentIdError("Student ID cannot be empty.");
                setForm(f => ({ ...f, [name]: "" }));
                return;
            }

            if (sanitized.length > 7) {
                setStudentIdError("Student ID must have exactly 7 digits.");
                setForm(f => ({ ...f, [name]: sanitized.slice(0, 7) }));
                return;
            }

            const studentID = parseInt(sanitized, 10);
            if (!isNaN(studentID) && studentID >= 1000000 && studentID <= 2000000) {
                setStudentIdError("");
            } else {
                setStudentIdError("Student ID must be between 1000000 and 2000000.");
            }

            setForm(f => ({ ...f, [name]: sanitized }));
            return;
        }

        setForm(prev => ({
            ...prev,
            [name]: value,
            ...(name === 'programme' && { edition: '', selectedCourses: [] }),
            ...(name === 'edition' && { selectedCourses: [] })
        }));
    };

    const handleCheckboxChange = (acronym) => {
        const clean = acronym.trim().toUpperCase();

        // Verifica se já está selecionado
        const isSelected = form.selectedCourses.includes(clean);

        // Temporariamente cria nova lista de selecionados se fosse para marcar/desmarcar
        const newSelected = isSelected
            ? form.selectedCourses.filter(c => c !== clean)
            : [...form.selectedCourses, clean];

        // Recalcula ECTS total
        const newTotalEcts = newSelected.reduce((sum, acr) => {
            const c = courses.find(c => c.acronym === acr);
            return sum + (c ? c.qtyECTS : 0);
        }, 0);

        // Recalcula ECTS por semestre
        const semesterSums = { 1: 0, 2: 0 };
        newSelected.forEach(acr => {
            const c = courses.find(c => c.acronym === acr);
            if (c) semesterSums[c.semester] += c.qtyECTS;
        });

        // Se for tentativa de selecionar (não remover)
        if (!isSelected) {
            if (newTotalEcts > totalEcts) {
                setError('Total ECTS limit (60) exceeded.');
                return;
            }
            if (semesterSums[1] > 30 || semesterSums[2] > 30) {
                setError('Semester ECTS limit (30) exceeded.');
                return;
            }
        }

        // Limpa erro e aplica nova seleção
        setError('');
        setForm(prev => ({
            ...prev,
            selectedCourses: newSelected
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
        setStudentIdError('');
        setSuccess(null);
        setShowModal(false);
        setShowErrorModal(false);
        setShowReviewModal(false);
        setReviewData(null);
    };

    const handleStudentBlur = async () => {
        setError('');
        if (!form.studentId) return;
        try {
            const res = await getEnrolledProgrammes(form.studentId);
            setProgrammes(res.programmeInfo || []);
            setStudentName(res.studentName || '');
            if ((res.programmeInfo || []).length === 0) {
                setError('This student is not enrolled in any programme.');
            }
        } catch (err) {
            console.error(err);
            setError('Error loading student programmes.');
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
                setError('Error loading programme editions.');
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
                setError('Error loading available courses.');
            }
        };
        fetchCourses();
    }, [form.edition, editions, form.programme]);

    const selectedEcts = form.selectedCourses.reduce((sum, acr) => {
        const c = courses.find(c => c.acronym === acr);
        return sum + (c ? c.qtyECTS : 0);
    }, 0);

    const semester1Ects = form.selectedCourses.reduce((sum, acr) => {
        const c = courses.find(c => c.acronym === acr && c.semester === 1);
        return sum + (c ? c.qtyECTS : 0);
    }, 0);

    const semester2Ects = form.selectedCourses.reduce((sum, acr) => {
        const c = courses.find(c => c.acronym === acr && c.semester === 2);
        return sum + (c ? c.qtyECTS : 0);
    }, 0);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!form.studentId || !form.programme || !form.edition || form.selectedCourses.length === 0) {
            setError('Please fill in all fields and select at least one course.');
            return;
        }

        const edition = editions.find(e => e.value === form.edition);
        const programme = programmes.find(p => p.generatedID === form.programme);

        if (!edition || !programme) {
            setError('Invalid programme or edition.');
            return;
        }

        // ⚡️ Grouped for preview
        const grouped = courses.reduce((acc, c) => {
            if (!acc[c.curricularYear]) acc[c.curricularYear] = {};
            if (!acc[c.curricularYear][c.semester]) acc[c.curricularYear][c.semester] = [];
            acc[c.curricularYear][c.semester].push(c);
            return acc;
        }, {});

        // ✅ PREVIEW only
        setReviewData({
            studentID: form.studentId,
            studentName: studentName,
            programmeName: programme.programmeName,
            editionDescription: edition.key,
            groupedCourses: grouped,
            selectedCourses: form.selectedCourses,
            selectedEcts: selectedEcts,
            payload: {   // 👈 Guarda o payload para depois
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
            }
        });

        setShowReviewModal(true);
    };

    const confirmEnrollment = async () => {
        try {
            const response = await enrolStudent(form.studentId, reviewData.payload);

            // ✅ Agora guarda tudo incluindo _links
            setSuccess({
                ...reviewData,
                links: response._links
            });

            setShowReviewModal(false);
            setShowModal(true);
        } catch (err) {
            console.error(err);
            setError(err.message);
            setShowReviewModal(false);
            setShowErrorModal(true);
        }
    };

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div student-img-background">
                    <div className="form-logo-img-div">
                        <img src={logo} alt="ISEP Logo"/>
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1>Programme Edition<br />and Courses Enrolment</h1>
                        <button type="button" className="pagination-btn2 pagination-btn-secondary" onClick={() => navigate("/")}>Back to Home Page</button>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <div className="form-group">
                                <label className="form-label">Student Unique Number</label>
                                <input
                                    type="text"
                                    name="studentId"
                                    className="form-input"
                                    value={form.studentId}
                                    onChange={handleChange}
                                    onBlur={handleStudentBlur}
                                    required
                                />
                                {studentIdError && <div className="error">{studentIdError}</div>}
                                {studentName && (
                                    <p style={{
                                        marginTop: '0.5rem',
                                        fontWeight: 'bold',
                                        fontSize: '1.4rem',
                                        color: '#6c757d'
                                    }}>
                                        {studentName}
                                    </p>
                                )}
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
                                <div style={{
                                    display: 'flex',
                                    justifyContent: 'space-between',
                                    alignItems: 'center',
                                    width: '100%'
                                }}>
                                    <label className="form-label" style={{ margin: 0 }}>Courses</label>
                                    {form.edition && (
                                        <span className="form-label" style={{
                                            margin: 0,
                                            color: selectedEcts === totalEcts ? 'red' : '#333'
                                        }}>
        ECTS — {selectedEcts} of {totalEcts}
                                            {selectedEcts > totalEcts && ' ⚠️'}
      </span>
                                    )}
                                </div>

                                {form.edition && (
                                    <>
                                        <p style={{
                                            fontWeight: 'bold',
                                            marginBottom: '0.5rem',
                                            color: semester1Ects >= 30 ? 'red' : '#333'
                                        }}>
                                            1º Semester — {semester1Ects} of 30
                                        </p>
                                        <p style={{
                                            fontWeight: 'bold',
                                            marginBottom: '0.5rem',
                                            color: semester2Ects >= 30 ? 'red' : '#333'
                                        }}>
                                            2º Semester — {semester2Ects} of 30
                                        </p>
                                    </>
                                )}

                                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', width: '100%' }}>
                                    {Object.entries(
                                        courses.reduce((acc, course) => {
                                            const year = course.curricularYear;
                                            const sem = course.semester;
                                            if (!acc[year]) acc[year] = { 1: [], 2: [] };
                                            acc[year][sem].push(course);
                                            return acc;
                                        }, {})
                                    ).sort(([a], [b]) => a - b)
                                        .map(([year, semesters]) => (
                                            <div key={year} style={{ marginBottom: '1.5rem', width: '100%', maxWidth: '700px' }}>
                                                <h3>{year}º YEAR</h3>
                                                <div style={{ display: 'flex', gap: '2rem', justifyContent: 'center' }}>
                                                    {[1, 2].map(sem => (
                                                        <div key={sem} style={{ flex: 1 }}>
                                                            <h4>{sem}º SEMESTER</h4>
                                                            {semesters[sem].length === 0 ? (
                                                                <p style={{ fontStyle: 'italic', color: '#888' }}>No courses</p>
                                                            ) : (
                                                                semesters[sem].map(c => (
                                                                    <label key={c.acronym} style={{ display: 'block', marginBottom: '0.5rem' }}>
                                                                        <input
                                                                            type="checkbox"
                                                                            value={c.acronym}
                                                                            checked={form.selectedCourses.includes(c.acronym)}
                                                                            disabled={
                                                                                !form.selectedCourses.includes(c.acronym) &&
                                                                                (
                                                                                    (selectedEcts + c.qtyECTS) > totalEcts ||
                                                                                    courses.filter(x =>
                                                                                        form.selectedCourses.includes(x.acronym) && x.semester === c.semester
                                                                                    ).reduce((sum, x) => sum + x.qtyECTS, 0) + c.qtyECTS > 30
                                                                                )
                                                                            }
                                                                            onChange={() => handleCheckboxChange(c.acronym)}
                                                                            style={{ marginRight: '0.5rem' }}
                                                                        />
                                                                        {c.name} ({c.qtyECTS} ECTS)
                                                                    </label>
                                                                ))
                                                            )}
                                                        </div>
                                                    ))}
                                                </div>
                                            </div>
                                        ))}
                                </div>
                            </div>

                            {error && <div className="error">{error}</div>}
                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={handleClear}>CLEAR</button>
                                <button type="submit" className="btn btn-primary">ENROL</button>
                            </div>
                        </div>
                    </div>
                </form>

                {showReviewModal && reviewData && (
                    <div className="modal-overlay">
                        <div className="modal-content" style={{ maxHeight: '80vh', overflowY: 'auto' }}>
                            <h2 style={{ color: '#E1A200' }}>🔍 Review Enrolment Data</h2>
                            <p><strong>Student ID:</strong> {reviewData.studentID}</p>
                            <p><strong>Student Name:</strong> {reviewData.studentName}</p>
                            <p><strong>Programme Edition:</strong> {reviewData.programmeName} — {reviewData.editionDescription}</p>

                            <h3>Courses:</h3>
                            <div style={{ textAlign: 'center' }}>
                                {Object.entries(reviewData.groupedCourses)
                                    .sort(([a], [b]) => a - b)
                                    .map(([year, semesters]) => (
                                        <div
                                            key={year}
                                            style={{
                                                marginBottom: '2rem',
                                                display: 'flex',
                                                flexDirection: 'column',
                                                alignItems: 'center'
                                            }}
                                        >
                                            <h3 style={{ color: '#800000' }}>{year}º YEAR</h3>
                                            <div
                                                style={{
                                                    display: 'flex',
                                                    justifyContent: 'space-between',
                                                    width: '100%',
                                                    maxWidth: '500px',
                                                    marginTop: '1rem'
                                                }}
                                            >
                                                {[1, 2].map(sem => {
                                                    const selected = semesters[sem]?.filter(c =>
                                                        reviewData.selectedCourses.includes(c.acronym)
                                                    ) || [];
                                                    return (
                                                        <div
                                                            key={sem}
                                                            style={{
                                                                flex: '1 1 50%',
                                                                padding: '0 10px',
                                                                boxSizing: 'border-box'
                                                            }}
                                                        >
                                                            <h4 style={{ textAlign: 'center', fontWeight: 'bold' }}>
                                                                {sem}º SEMESTER
                                                            </h4>
                                                            {selected.length === 0 ? (
                                                                <p style={{ fontStyle: 'italic', color: '#888', textAlign: 'center' }}>No courses</p>
                                                            ) : (
                                                                <ul style={{ listStyle: 'none', padding: 0, textAlign: 'left' }}>
                                                                    {selected.map(c => (
                                                                        <li key={c.acronym} style={{ marginBottom: '0.3rem' }}>
                                                                            {c.name} ({c.qtyECTS} ECTS)
                                                                        </li>
                                                                    ))}
                                                                </ul>
                                                            )}
                                                        </div>
                                                    );
                                                })}
                                            </div>
                                        </div>
                                    ))}
                            </div>

                            <p style={{ marginTop: '1rem', fontWeight: 'bold' }}>
                                ECTS Used: {reviewData.selectedEcts} / {totalEcts}
                            </p>

                            <div className="form-actions">
                                <button
                                    type="button"
                                    className="btn btn-secondary"
                                    onClick={() => setShowReviewModal(false)}
                                >
                                    Cancel
                                </button>
                                <button
                                    type="button"
                                    className="btn btn-primary"
                                    onClick={confirmEnrollment}
                                >
                                    Confirm
                                </button>
                            </div>
                        </div>
                    </div>
                )}

                {showModal && success && (
                    <div className="modal-overlay">
                        <div className="modal-content" style={{ maxHeight: '80vh', overflowY: 'auto' }}>
                            <h2 style={{ color: 'green' }}>✅ Enrolment Successful</h2>
                            <p><strong>Student ID:</strong> {success.studentID}</p>
                            <p><strong>Student Name:</strong> {success.studentName}</p>
                            <p><strong>Programme Edition:</strong> {success.programmeName} — {success.editionDescription}</p>

                            <h3>Courses:</h3>
                            <div style={{ textAlign: 'center' }}>
                                {Object.entries(success.groupedCourses)
                                    .sort(([a], [b]) => a - b)
                                    .map(([year, semestersRaw]) => {
                                        const semesters = { 1: [], 2: [], ...semestersRaw };
                                        const bothEmpty = semesters[1].length === 0 && semesters[2].length === 0;
                                        if (bothEmpty) return null;

                                        return (
                                            <div
                                                key={year}
                                                style={{
                                                    marginBottom: '2rem',
                                                    display: 'flex',
                                                    flexDirection: 'column',
                                                    alignItems: 'center'
                                                }}
                                            >
                                                <h3 style={{ color: '#800000' }}>{year}º YEAR</h3>
                                                <div
                                                    style={{
                                                        display: 'flex',
                                                        justifyContent: 'space-between',
                                                        width: '100%',
                                                        maxWidth: '500px',
                                                        marginTop: '1rem'
                                                    }}
                                                >
                                                    {[1, 2].map(sem => {
                                                        const selected = semesters[sem].filter(c =>
                                                            success.selectedCourses.includes(c.acronym)
                                                        );
                                                        return (
                                                            <div
                                                                key={sem}
                                                                style={{
                                                                    flex: '1 1 50%',
                                                                    padding: '0 10px',
                                                                    boxSizing: 'border-box'
                                                                }}
                                                            >
                                                                <h4 style={{ textAlign: 'center', fontWeight: 'bold' }}>
                                                                    {sem}º SEMESTER
                                                                </h4>
                                                                {selected.length === 0 ? (
                                                                    <p style={{ fontStyle: 'italic', color: '#888', textAlign: 'center' }}>No courses</p>
                                                                ) : (
                                                                    <ul style={{ listStyle: 'none', padding: 0, textAlign: 'left' }}>
                                                                        {selected.map(c => (
                                                                            <li key={c.acronym} style={{ marginBottom: '0.3rem' }}>
                                                                                {c.name} ({c.qtyECTS} ECTS)
                                                                            </li>
                                                                        ))}
                                                                    </ul>
                                                                )}
                                                            </div>
                                                        );
                                                    })}
                                                </div>
                                            </div>
                                        );
                                    })}
                            </div>

                            <div style={{ display: 'flex', justifyContent: 'center', gap: '2rem', marginTop: '2rem' }}>
                                <i
                                    className="fas fa-book-open icon-button"
                                    title="Student Enrolled Courses"
                                    onClick={() => window.open(
                                        `/students/enrolled-courses?link=${encodeURIComponent(success.links['enrolled-course-editions'].href)}`,
                                        '_blank'
                                    )}
                                />

                                <i
                                    className="fas fa-graduation-cap icon-button"
                                    title="Student Programme Editions"
                                    onClick={() => window.open(
                                        `/students/programme-editions?link=${encodeURIComponent(success.links['enrolled-programme-editions'].href)}`,
                                        '_blank'
                                    )}
                                />
                            </div>

                            <p style={{ marginTop: '1rem', fontWeight: 'bold' }}>
                                ECTS Used: {success.selectedEcts} / {totalEcts}
                            </p>

                            <button
                                type="button"
                                className="btn btn-secondary"
                                onClick={() => {
                                    setShowModal(false);
                                    window.location.reload();
                                }}
                            >
                                Close
                            </button>
                        </div>
                    </div>
                )}

                {showErrorModal && (
                    <div className="modal-overlay">
                        <div className="modal-content" style={{ borderColor: 'red' }}>
                            <h2 style={{ color: 'red' }}>Enrolment Error</h2>
                            <p>{error}</p>
                            <button className="modal-btn" onClick={() => setShowErrorModal(false)}>Close</button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}