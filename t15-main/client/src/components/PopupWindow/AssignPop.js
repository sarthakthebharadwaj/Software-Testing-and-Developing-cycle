import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'
import Select from "react-dropdown-select";
import Multiselect from 'multiselect-react-dropdown';
import { mediumBlueContainerStyle, errorPopup, closeButton, saveButton, buttonContainer } from '../../utils/styles';
import { getProjects, getWorkers, assignWorker } from '../../services/dataService';

const ErrorPopUp = ({ message, onClose }) => (
    <div className="errorPopup" style={errorPopup}>
        <button className="closeButton" style={closeButton} onClick={onClose}>X</button>
        <p>{message}</p>
    </div>
);

const AssignPop = ({ isOpen, onClose, containerStyle }) => {
    const [projectOptions, setProjectOptions] = useState([]);
    const [workerOptions, setWorkerOptions] = useState([]);
    const [selectedProject, setSelectedProject] = useState(null);
    const [selectedWorker, setSelectedWorker] = useState(null);

    const [showErrorState, setShowErrorState] = useState(false); 
    const [showErrorNoProject, setShowErrorNoProject] = useState(false); 
    const [showErrorNoWorker, setShowErrorNoWorker] = useState(false); 

    useEffect(() => {
        if (isOpen) {
            getProjects().then(options => {
                setProjectOptions(options);
            }).catch(error => {
                console.error("Error fetching options: ", error);
            });
        }
    }, [isOpen]);

    useEffect(() => {
        if (isOpen) {
            getWorkers().then(options => {
                setWorkerOptions(options);
            }).catch(error => {
                console.error("Error fetching options: ", error);
            });
        }
    }, [isOpen]);

    if (!isOpen) return null;

    const handleSelectChange = (selectedValues) => {
        if (selectedValues.length > 0) {
            const selectedProject = selectedValues[0]; 
            setSelectedProject(selectedProject);
        } else {
            setSelectedProject(null); 
        }
    };

    const handleSelectChangeWorker = (selectedValues) => {
        if (selectedValues.length > 0) {
            const selectedWorker = selectedValues[0]; 
            setSelectedWorker(selectedWorker);
        } else {
            setSelectedWorker(null); 
        }
    };

    const handleCloseErrorPopUpState = () => {
        setShowErrorState(false);
    };

    const handleCloseErrorPopUpNoProject = () => {
        setShowErrorNoProject(false);
    };

    const handleCloseErrorPopUpNoWorker = () => {
        setShowErrorNoWorker(false);
    };

    const handleSave = async () => {
        console.log(selectedProject);
        if(selectedProject === null || selectedWorker === null){
            if(selectedProject === null){
                setShowErrorNoProject(true);
            }
            if(selectedWorker === null){
                setShowErrorNoWorker(true);
            }
           
        }
        else{
            try {
                await assignWorker(selectedWorker.name, selectedProject.name);
                onClose();
            } catch (error) {
                setShowErrorState(true);
            }
        }
            
    }

    return (
        <div className="popup-container" style={containerStyle}>
            <div className="popup-content">
                <h2 style={{ marginTop: '0' }} >Assign Pop-up </h2>
                <div><Select
                    options={projectOptions}
                    displayValue="name"
                    labelField="name"
                    valueField="name"
                    onChange={handleSelectChange}
                    placeholder="Select Project"

                    />
                </div>
                <div><Select
                     options={workerOptions}
                     displayValue="name"
                     labelField="name"
                     valueField="name"
                     onChange={handleSelectChangeWorker}
                     placeholder="Select Worker"
                    />
                    <button onClick={handleSave}>Save</button>
                </div>
                <button onClick={onClose}>Close</button>
            </div>
            {showErrorState && <ErrorPopUp message="Please ensure that the project is in the correct state, the worker is helpful, the worker is not already assigned, and the worker will not be overloaded" onClose={() => handleCloseErrorPopUpState()} />} 
            {showErrorNoProject && <ErrorPopUp message="Enter a Project" onClose={() => handleCloseErrorPopUpNoProject()} />} 
            {showErrorNoWorker && <ErrorPopUp message="Enter a Worker" onClose={() => handleCloseErrorPopUpNoWorker()} />} 
        </div>
    );
};

export default AssignPop;