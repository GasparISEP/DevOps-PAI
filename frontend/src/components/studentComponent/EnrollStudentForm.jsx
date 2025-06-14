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

    const [programmes, setProgrammes] = useState([]);
    const [editions, setEditions] = useState([]);
    const [courses, setCourses] = useState([]);

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

    const handleCheckboxChange = (course) => {
        setForm(prev => ({
            ...prev,
            selectedCourses: prev.selectedCourses.includes(course)
                ? prev.selectedCourses.filter(c => c !== course)
                : [...prev.selectedCourses, course]
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
        setError('');
        setSuccess(null);
        setShowModal(false);
        setShowErrorModal(false);
    };

    const handleStudentBlur = async () => {
        setError('');
        if (!form.studentId) return;

        try {
            const response = await getEnrolledProgrammes(form.studentId);

            // ✅ Usa o campo certo do DTO:
            const programmes = response.programmeInfo || [];

            if (programmes.length === 0) {
                setError('Este estudante não está inscrito em nenhum programa.');
                setProgrammes([]);
                return;
            }

            setProgrammes(programmes);
        } catch (err) {
            console.error(err);
            setError('Erro ao carregar programas para este estudante.');
            setProgrammes([]);
        }
    };

    useEffect(() => {
        const fetchEditions = async () => {
            if (!form.programme) {
                setEditions([]);
                return;
            }

            try {
                const editions = await getProgrammeEditions(form.programme);
                const mapped = editions.map((e, index) => ({
                    key: `${e.programmeAcronym} - ${index + 1}`,
                    value: e.schoolYearId,
                    acronym: e.programmeAcronym
                }));
                setEditions(mapped);
            } catch (err) {
                console.error(err);
                setError('Erro ao carregar edições do programa.');
                setEditions([]);
            }
        };

        fetchEditions();
    }, [form.programme]);

    useEffect(() => {
        const fetchCourses = async () => {
            setCourses([]);

            if (!form.programme || !form.edition) return;

            const selectedEdition = editions.find(e => e.value === form.edition);
            if (!selectedEdition) return;

            const payload = {
                programmeAcronym: selectedEdition.acronym,
                schoolYearId: selectedEdition.value
            };

            try {
                const courseList = await getAvailableCourses(payload);
                setCourses(courseList);
            } catch (err) {
                console.error(err);
                setError('Erro ao carregar cursos para esta edição.');
            }
        };

        fetchCourses();
    }, [form.edition]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!form.studentId || !form.programme || !form.edition || form.selectedCourses.length === 0) {
            setError('Preencha todos os campos e selecione pelo menos um curso.');
            return;
        }

        const selectedEdition = editions.find(e => e.value === form.edition);
        if (!selectedEdition) return;

        const payload = {
            studentId: parseInt(form.studentId),
            programmeAcronym: selectedEdition.acronym,
            schoolYearId: selectedEdition.value,
            courseIds: form.selectedCourses.map(acronym => ({ acronym }))
        };

        try {
            const result = await enrolStudent(form.studentId, payload);
            setSuccess({
                studentID: form.studentId,
                programmeEdition: `${selectedEdition.acronym} - ${selectedEdition.value}`,
                courses: form.selectedCourses
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
                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1 style={{ margin: 0 }}>Enroll a Student</h1>
                        <button type="button" className="pagination-btn2 pagination-btn-secondary" onClick={() => navigate("/")}>Back to Home Page</button>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <div className="form-group">
                                <label htmlFor="studentId" className="form-label">Student Unique Number</label>
                                <input type="text" name="studentId" id="studentId" className="form-input" value={form.studentId} onChange={handleChange} onBlur={handleStudentBlur} required />
                            </div>

                            <div className="form-group">
                                <label htmlFor="programme" className="form-label">Programme</label>
                                <select name="programme" id="programme" className="form-input" value={form.programme} onChange={handleChange}>
                                    <option value="">-- Choose Programme --</option>
                                    {programmes.map(p => (
                                        <option key={p.programmeEnrolmentGeneratedID} value={p.programmeEnrolmentGeneratedID}>{p.programmeAcronym}</option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label htmlFor="edition" className="form-label">Programme Edition</label>
                                <select name="edition" id="edition" className="form-input" value={form.edition} onChange={handleChange}>
                                    <option value="">-- Choose Edition --</option>
                                    {editions.map(e => (
                                        <option key={e.key} value={e.value}>{e.key}</option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label className="form-label">Courses</label>
                                <div className="form-checkbox-group">
                                    {courses.map(c => (
                                        <label key={c.acronym} className="form-label" style={{ fontWeight: 'normal' }}>
                                            <input type="checkbox" value={c.acronym} checked={form.selectedCourses.includes(c.acronym)} onChange={() => handleCheckboxChange(c.acronym)} style={{ marginRight: '0.5rem' }} />
                                            {c.name}
                                        </label>
                                    ))}
                                </div>
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
                            <h2>Success!</h2>
                            <p>Enrollment completed successfully.</p>
                            <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                                <p><strong>Student ID:</strong> {success.studentID}</p>
                                <p><strong>Programme Edition:</strong> {success.programmeEdition}</p>
                                <p><strong>Courses:</strong></p>
                                <ul>
                                    {success.courses.map((course, index) => (
                                        <li key={index}>{course}</li>
                                    ))}
                                </ul>
                            </div>
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