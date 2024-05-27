import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { mediumBlueContainerStyle, errorPopup, closeButton, saveButton, buttonContainer } from '../../utils/styles';
import Multiselect from 'multiselect-react-dropdown';
import { getQualifications, createWorker, getWorkers } from '../../services/dataService';

const ErrorPopUp = ({ message, onClose }) => (
    <div className="errorPopup" style={errorPopup}>
        <button className="closeButton" style={closeButton} onClick={onClose}>X</button>
        <p>{message}</p>
    </div>
);

const WorkerPop = ({ isOpen, onClose, containerStyle }) => {
    
    const [qualificationOptions, setQualificationOptions] = useState([]);
    const [inputValueName, setInputValueName] = useState('');
    const [inputValueSalary, setInputValueSalary] = useState('');
    const [quals, setQuals] = useState([]); 

    const [showErrorName, setShowErrorName] = useState(false); 
    const [showErrorSalary, setShowErrorSalary] = useState(false); 
    const [showErrorQual, setShowErrorQual] = useState(false); 
    const [showErrorNameSame, setShowErrorNameSame] = useState(false); 



    

    useEffect(() => {
        if (isOpen) {
            getQualifications().then(options => {
                setQualificationOptions(options);
            }).catch(error => {
                console.error("Error fetching options: ", error);
            });
        }
    }, [isOpen]);

    if (!isOpen) return null;

    const onSelect = (selectedList, selectedItem) => {
        quals.push(selectedItem.description);
    }

    const onRemove = (selectedList, removedItem) => {
        quals.filter(item => item !== removedItem.description);
    }


    const handleInputChangeName = (event) => {
        setInputValueName(event.target.value);
    }

    const handleInputChangeSalary = (event) => {
        setInputValueSalary(event.target.value);
    }

    const handleCloseErrorPopUpName = () => {
        setShowErrorName(false);
    };

    const handleCloseErrorPopUpSalary = () => {
        setShowErrorSalary(false);
    };

    const handleCloseErrorPopUpQual = () => {
        setShowErrorQual(false);
    };

    const handleCloseErrorPopUpNameSame = () => {
        setShowErrorNameSame(false);
    }

    const handleSave = async () => {
        if (inputValueName === "") {
            setShowErrorName(true); 
        } 
        if ( inputValueSalary === ""){
            setShowErrorSalary(true); 
        }
        if( quals.length === 0){
            setShowErrorQual(true);
        }

        try {
            const actualWorkers = await getWorkers();
            let bool = true;
            for (let i = 0; i < actualWorkers.length; i++) {
                if (inputValueName === actualWorkers[i].name) {
                    setShowErrorNameSame(true);
                    bool = false;
                }
            }
    
            if (inputValueName !== "" && inputValueSalary !== "" && quals.length !== 0 && bool === true) {
                createWorker(inputValueName, quals, inputValueSalary);
                onClose();
            }
        } catch (error) {
            console.error("Error fetching workers:", error);
        }

        
    }

    return (
        <div className="popup-container" style={containerStyle}>
            <div className="popup-content">
                <h2 style={{ marginTop: '0' }} >Create Worker Pop-up </h2>
                <div className="mediumBlueContainerStyle">
                    <input 
                        type="text"
                        value={inputValueName}
                        onChange={handleInputChangeName}
                        placeholder="Enter a name"
                    />
                    <input 
                        type="text"
                        value={inputValueSalary}
                        onChange={handleInputChangeSalary}
                        placeholder="Enter a salary"
                    />
                    <Multiselect
                        options={qualificationOptions}
                        onSelect={onSelect}
                        onRemove={onRemove}
                        displayValue="description"
                        placeholder="Select Qualifications"
                    />
                    </div>
                    <div > 
                        <button onClick={handleSave}>Save</button>
                        <button onClick={onClose}>Close</button>
                    </div>
                <br></br>
                {showErrorName && <ErrorPopUp message="Enter a Name" onClose={() => handleCloseErrorPopUpName()} />} 
                {showErrorSalary && <ErrorPopUp message="Enter a Salary" onClose={() => handleCloseErrorPopUpSalary()} />}
                {showErrorQual && <ErrorPopUp message="Select Qualifications" onClose={() => handleCloseErrorPopUpQual()} />}
                {showErrorNameSame && <ErrorPopUp message="That name already exists" onClose={() => handleCloseErrorPopUpNameSame()} />} 
            </div>
        </div>
    );
};

export default WorkerPop;
