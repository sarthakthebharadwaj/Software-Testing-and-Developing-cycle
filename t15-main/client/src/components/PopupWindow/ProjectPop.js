import React, { useState, useEffect } from 'react';
import Multiselect from 'multiselect-react-dropdown';
import { createProject, getQualifications } from '../../services/dataService';
import { mediumBlueContainerStyle, errorPopup, closeButton } from '../../utils/styles';

const ErrorPopUp = ({ message, onClose }) => (
    <div className="errorPopup" style={errorPopup}>
        <button className="closeButton" style={closeButton} onClick={onClose}>X</button>
        <p>{message}</p>
    </div>
);

const ProjectPop = ({ isOpen, onClose, containerStyle }) => {
    const [qualificationOptions, setQualificationOptions] = useState([]);
    const [selectedQualifications, setSelectedQualifications] = useState([]);
    const [showErrorName, setShowErrorName] = useState(false);
    const [projectName, setProjectName] = useState('');
    const [projectSize, setProjectSize] = useState('SMALL');

    useEffect(() => {
        if (isOpen) {
            getQualifications()
                .then(quals => {
                    setQualificationOptions(quals.map(q => ({ id: q.id, description: q.description })));
                })
                .catch(error => console.error('Failed to fetch qualifications:', error));
        }
    }, [isOpen]);

    const onSelect = (selectedList, selectedItem) => {
        setSelectedQualifications(selectedList);
    };

    const onRemove = (selectedList, removedItem) => {
        setSelectedQualifications(selectedList);
    };

    const handleInputChangeName = (event) => {
        setProjectName(event.target.value);
    };

    const handleSelectProjectSize = (event) => {
        setProjectSize(event.target.value);
    };

    const handleCloseErrorPopUpName = () => {
        setShowErrorName(false);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!projectName.trim()) {
            setShowErrorName(true);
            return;
        }

        try {
            const quals = selectedQualifications.map(q => q.description);
            const response = await createProject(projectName, quals, projectSize);
            if (response?.data === 'OK') {
                console.log("Project created successfully");
                onClose();
            } else {
                throw new Error('Failed to create project due to server error with status code: ' + response.status);
            }
        } catch (error) {
            console.error('Error creating project:', error);
            setShowErrorName(true);
        }
    };


    if (!isOpen) return null;

    return (
        <div className="popup-container" style={containerStyle}>
            <div className="popup-content" style={mediumBlueContainerStyle}>
                <h2>Create New Project</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Project Name:</label>
                        <input
                            type="text"
                            value={projectName}
                            onChange={handleInputChangeName}
                            placeholder="Enter Project Name"
                            required
                        />
                    </div>
                    <div>
                        <label>Qualifications Required:</label>
                        <Multiselect
                            options={qualificationOptions}
                            selectedValues={selectedQualifications}
                            onSelect={onSelect}
                            onRemove={onRemove}
                            displayValue="description"
                            placeholder="Select Qualifications"
                        />
                    </div>
                    <div>
                        <label>Project Size:</label>
                        <select name="projectSize" value={projectSize} onChange={handleSelectProjectSize} style={{ width: '100%' }}>
                            <option value="SMALL">SMALL</option>
                            <option value="MEDIUM">MEDIUM</option>
                            <option value="BIG">BIG</option>
                        </select>
                    </div>
                    <div>
                        <button type="submit">Save</button>
                        <button type="button" onClick={onClose}>Close</button>
                    </div>
                </form>
                {showErrorName && <ErrorPopUp message="Invalid Entry" onClose={handleCloseErrorPopUpName} />}
            </div>
        </div>
    );
};

export default ProjectPop;
