import React, { useState, useEffect } from 'react';
import { getProjects, startProject, finishProject } from '../../services/dataService';

const ProjectStatusPop = ({ isOpen, onClose, containerStyle }) => {
    const [projectOptions, setProjectOptions] = useState([]);
    const [selectedProject, setSelectedProject] = useState('');
    const [projectStatus, setProjectStatus] = useState('');

    useEffect(() => {
        if (isOpen) {
            getProjects().then(projects => {
                setProjectOptions(projects.map(p => ({ id: p.name, name: p.name })));
            }).catch(error => {
                console.error("Failed to fetch projects: ", error);
            });
        }
    }, [isOpen]);

    const handleProjectChange = (event) => {
        setSelectedProject(event.target.value);
    };

    const handleStatusChange = (event) => {
        setProjectStatus(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!selectedProject || !projectStatus) {
            alert("Please select both a project and a status.");
            return;
        }
        try {
            if (projectStatus === "START") {
                const response = await startProject(selectedProject);
                console.log('Project started:', response);
            } else if (projectStatus === "FINISH") {
                const response = await finishProject(selectedProject);
                console.log('Project finished:', response);
            }
            onClose();  // Close the popup after action
        } catch (error) {
            console.error('Error updating project status:', error);
            alert("Failed to update project status.");
        }
    };

    if (!isOpen) return null;

    return (
        <div className="popup-container" style={containerStyle}>
            <div className="popup-content">
                <h2>Edit Project Status</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Project List:</label>
                        <select value={selectedProject} onChange={handleProjectChange} style={{ width: '100%' }}>
                            <option value="">Select Project</option>
                            {projectOptions.map((option) => (
                                <option key={option.id} value={option.id}>{option.name}</option>
                            ))}
                        </select>
                    </div>
                    <div>
                        <label>Project Status:</label>
                        <select value={projectStatus} onChange={handleStatusChange} style={{ width: '100%' }}>
                            <option value="">Select Status</option>
                            <option value="START">Start</option>
                            <option value="FINISH">Finish</option>
                        </select>
                    </div>
                    <div>
                        <button type="submit">Save</button>
                        <button type="button" onClick={onClose}>Close</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default ProjectStatusPop;
