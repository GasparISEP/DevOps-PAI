import React, { useState, useEffect, useRef } from 'react';

function ActionMenu({ edition, onCountEnrolments, onShowAverageGrade }) {
    const [showMenu, setShowMenu] = useState(false);
    const menuRef = useRef(null);

    useEffect(() => {
        function handleClickOutside(event) {
            if (menuRef.current && !menuRef.current.contains(event.target)) {
                setShowMenu(false);
            }
        }

        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    const handleCountEnrolments = async () => {
        setShowMenu(false);
        await onCountEnrolments(edition);
    };

    const handleShowAverageGrade = async () => {
        setShowMenu(false);
        await onShowAverageGrade(edition);
    };

    return (
        <div className="action-menu-container" ref={menuRef}>
            <button
                className="action-menu-button"
                onClick={() => setShowMenu(!showMenu)}
            >
                ⋮
            </button>
            {showMenu && (
                <div className="action-menu-dropdown">
                    <button
                        className="action-menu-item"
                        onClick={handleCountEnrolments}
                    >
                        Count Enrolments
                    </button>
                    <button
                        className="action-menu-item"
                        onClick={handleShowAverageGrade}
                    >
                        Show Average Grade
                    </button>
                </div>
            )}
        </div>
    );
}

export default ActionMenu;