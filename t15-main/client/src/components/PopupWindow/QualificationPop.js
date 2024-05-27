import React, { useState, useEffect } from 'react';
import {createQualification, getQualifications} from '../../services/dataService';
import { mediumBlueContainerStyle, errorPopup, closeButton, saveButton, buttonContainer } from '../../utils/styles';

const ErrorPopUp = ({ message, onClose }) => (
    <div className="errorPopup" style={errorPopup}>
        <button className="closeButton" style={closeButton} onClick={onClose}>X</button>
        <p>{message}</p>
    </div>
);

const QualificationPop = ({ isOpen, onClose, containerStyle }) => {
    const [qualName, setQualName] = useState('');
    const [showErrorName, setShowErrorName] = useState(false);


    const handleInputChangeName = (event) => {
        setQualName(event.target.value);
    }

    const handleCloseErrorPopUpName = () => {
        setShowErrorName(false);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!qualName.trim()) {
            setShowErrorName(true);
            return;
        }

        try {
            const response = await createQualification(qualName.trim());
            if (response?.data === 'OK') {
                const updatedQualifications = await getQualifications();
                setQualName(updatedQualifications);
                onClose();
            } else {
                throw new Error('Failed to create qualification due to server error.');
            }
        } catch (error) {
            console.error('Error creating qualification:', error);
            setShowErrorName(true);
        }
    };


    if (!isOpen) return null;

    return (
        <div className="popup-container" style={containerStyle}>
            <div className="popup-content" style={mediumBlueContainerStyle}>
                <h2 style={{ marginTop: '0' }}>Create Qualification</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Qualification Name:</label>
                        <input
                            type="text"
                            value={qualName}
                            onChange={handleInputChangeName}
                            placeholder="Enter Qualification"
                            required
                        />
                    </div>
                    <div style={{ marginTop: '10px' }}>
                        <button type="submit">Save</button>
                        <button type="button" onClick={onClose}>Close</button>
                    </div>
                </form>
                {showErrorName && <ErrorPopUp message="Invalid Entry" onClose={handleCloseErrorPopUpName} />}
            </div>
        </div>
    );
};

export default QualificationPop;
