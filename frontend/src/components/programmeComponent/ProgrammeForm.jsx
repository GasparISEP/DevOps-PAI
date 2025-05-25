import React, {useEffect, useState} from 'react';
import { registerProgramme } from '../../services/programmeService';
import formImage from '../../assets/images/form-image.jpg';

export default function ProgrammeForm() {
    const [form, setForm] = useState({
        name: '',
        acronym: '',
        quantSemesters: '',
        degreeTypeID: '',
        departmentID: '',
        teacherID: ''
    });

    const [departments, setDepartments] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [degreeTypes, setDegreeTypes] = useState([]);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);

    useEffect(() => {
        async function fetchOptions() {
            try {
                const [deptRes, teacherRes, degreeTypeRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/departments`),
                    fetch(`${process.env.REACT_APP_API_URL}/teachers`),
                    fetch(`${process.env.REACT_APP_API_URL}/degreetypes`)


                ]);
                const deptData = await deptRes.json();
                const teacherData = await teacherRes.json();
                const degreeTypeData = await degreeTypeRes.json();

                console.log("Fetched departments:", deptData);
                console.log("Fetched teachers:", teacherData);
                console.log("Fetched degree types:", degreeTypeData);


                setDepartments(deptData);
                setTeachers(teacherData);
                setDegreeTypes(degreeTypeData);



            } catch (err) {
                console.error("Failed to load options:", err);
            }
        }
        fetchOptions();
    }, []);

    function handleChange(e) {
        setForm(f => ({ ...f, [e.target.name]: e.target.value }));

        console.log(form.degreeTypeID)
    }

    useEffect(() => {
        console.log("API URL:", process.env.REACT_APP_API_URL);
    }, []);


    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);
        try {
            const selectedDegreeType = degreeTypes.find(dt => dt.id === form.degreeTypeID);

            // Converte quantECTS e quantSemesters para número
            const payload = {
                ...form,
                quantECTS: selectedDegreeType.maxECTS,
                quantSemesters: parseInt(form.quantSemesters)
            };

            const response = await registerProgramme(payload);
            setSuccess(response);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    return (

        <div className="programme-main-component-div">
        <div className="programme-main-grid">

            <div className="img-main-div">
                <img className="form-img" src={formImage} alt="Person typing on a computer."/>
            </div>

            <form className="programme-form" onSubmit={handleSubmit}>
                <h1>Register Programme</h1>

                <div className="programme-form-and-buttons-main-div">

                    <div className="programme-form-div">

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="name">Name</label>
                            <input className="programme-form-input" placeholder="Enter Programme's name" id="name" name="name"
                                   value={form.name} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="acronym">Acronym</label>
                            <input className="programme-form-input" placeholder="Enter Programme's acronym" id="acronym" name="acronym" value={form.acronym}
                                   onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="quantSemesters">Semesters</label>
                            <input className="programme-form-input" placeholder="Enter number of semesters" id="quantSemesters" name="quantSemesters" type="number"
                                   value={form.quantSemesters} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="degreeTypeID">Degree Type</label>
                            <select className="programme-form-input" id="degreeTypeID" name="degreeTypeID"
                                    value={form.degreeTypeID} onChange={handleChange} required>
                                <option value="">Select Degree Type</option>
                                {degreeTypes.map(d => (
                                    <option key={d.id} value={d.id}>{d.name}</option>
                                ))}
                            </select>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="departmentID">Department</label>
                            <select className="programme-form-input" id="departmentID" name="departmentID"
                                    value={form.departmentID} onChange={handleChange} required>
                                <option value="">Select Department</option>
                                {departments.map(d => (
                                    <option key={d.id} value={d.id}>{d.name}</option>
                                ))}
                            </select>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="teacherID">Programme's Director</label>
                            <select className="programme-form-input" id="teacherID" name="teacherID"
                                    value={form.teacherID} onChange={handleChange} required>
                                <option value="">Select Teacher</option>
                                {teachers.map(t => (
                                    <option key={t.id} value={t.id}>{t.name}</option>
                                ))}
                            </select>
                        </div>

                        {error && <div className="error">{error}</div>}

                        <div className="programme-form-actions">
                            <button type="button" className="btn btn-secondary" onClick={() => window.history.back()}
                                    disabled={loading}>
                                CANCEL
                            </button>
                            <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ? 'Registering…' : 'REGISTER'}
                        </button>
                    </div>
                </div>

                {success && (
                    <div className="success" style={{marginTop: '1rem', color: '#080'}}>
                        Programme registered successfully!
                    </div>
                )}
                </div>
            </form>
        </div>
        </div>
    );
}
