import React from 'react';

export default function Modal({ message, onClose }) {
    return (
        <div
            style={{
                position: 'fixed',
                top: 0, left: 0, right: 0, bottom: 0,
                backgroundColor: 'rgba(0,0,0,0.4)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
            }}
        >
            <div
                style={{
                    background: '#fff',
                    padding: '2rem',
                    borderRadius: '8px',
                    maxWidth: '400px',
                    textAlign: 'center',
                }}
            >
                <p style={{ marginBottom: '1.5rem' }}>{message}</p>
                <button
                    style={{
                        padding: '0.5rem 1rem',
                        border: 'none',
                        backgroundColor: '#800000',
                        color: '#fff',
                        borderRadius: '4px',
                        cursor: 'pointer',
                    }}
                    onClick={onClose}
                >
                    OK
                </button>
            </div>
        </div>
    );
}
