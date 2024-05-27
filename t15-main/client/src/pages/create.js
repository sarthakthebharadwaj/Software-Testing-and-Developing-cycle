import { pageStyle, lightBlueContainerStylePopup } from '../utils/styles'
import WorkerPop from '../components/PopupWindow/WorkerPop'
import ProjectPop from '../components/PopupWindow/ProjectPop'
import QualificationPop from '../components/PopupWindow/QualificationPop'
import AssignPop from '../components/PopupWindow/AssignPop'
import React, { useState } from 'react'
import UnassignPop from '../components/PopupWindow/UnassignPop'
import ProjectStatusPop from '../components/PopupWindow/ProjectStatusPop'


const Create = () => {
    const [isWorkerPopupOpen, setWorkerPopupOpen] = useState(false);


    const openWorkerPopup = () => {
        setWorkerPopupOpen(true);
    };

    const closePopupWorker = () => {
        setWorkerPopupOpen(false);
    }


    const [isProjectPopupOpen, setProjectPopupOpen] = useState(false);
    const openProjectPopup = () => {
        setProjectPopupOpen(true);
    };

    const closePopupProject = () => {
        setProjectPopupOpen(false);
    }

    const [isQualificationPopupOpen, setQualificationPopupOpen] = useState(false);
    const openQualificationPopup = () => {
        setQualificationPopupOpen(true);
    };

    const closePopupQualification = () => {
        setQualificationPopupOpen(false);
    }

    const [isAssignPopupOpen, setAssignPopupOpen] = useState(false);
    const openAssignPopup = () => {
        setAssignPopupOpen(true);
    };

    const closePopupAssign = () => {
        setAssignPopupOpen(false);
    }
    const [isUnassignPopupOpen, setUnassignPopupOpen] = useState(false);
    const openUnassignPopup = () => {
        setUnassignPopupOpen(true);
    };

    const closePopupUnassign = () => {
        setUnassignPopupOpen(false);
    }

    const [isProjectStatusPopupOpen, setProjectStatusPopupOpen] = useState(false);

    const openProjectStatusPopup = () => {
        ProjectStatusPop(true);
    }

    const closePopupProjectStatus = () => {
        ProjectStatusPop(false);
    }

    return (
        <div style={pageStyle}>
            <div style={{'text-align': 'center'}}>
                <h1> Create Worker</h1>
                <div>
                    <button onClick={openWorkerPopup}>Create Worker</button>
                </div>
                <WorkerPop isOpen={isWorkerPopupOpen} onClose={closePopupWorker}
                           containerStyle={lightBlueContainerStylePopup}/>
            </div>

            <div style={{'text-align': 'center'}}>
                <h1> Create Project</h1>
                <div>
                    <button onClick={openProjectPopup}>Create Project</button>
                </div>
                <ProjectPop isOpen={isProjectPopupOpen} onClose={closePopupProject}
                            containerStyle={lightBlueContainerStylePopup}/>
            </div>


            <div style={{'text-align': 'center'}}>
                <h1> Create Qualification</h1>
                <div>
                    <button onClick={openQualificationPopup}>Create Qualification</button>
                </div>
                <QualificationPop isOpen={isQualificationPopupOpen} onClose={closePopupQualification}
                                  containerStyle={lightBlueContainerStylePopup}/>
            </div>

            <div style={{'text-align': 'center'}}>
                <h1> Assign Worker to a Project </h1>
                <div>
                    <button onClick={openAssignPopup}>Assign</button>
                </div>
                <AssignPop isOpen={isAssignPopupOpen} onClose={closePopupAssign}
                           containerStyle={lightBlueContainerStylePopup}/>
            </div>

            <div style={{'text-align': 'center'}}>
                <h1> Unassign Worker to a Project </h1>
                <div>
                    <button onClick={openUnassignPopup}>Unassign</button>
                </div>
                <UnassignPop isOpen={isUnassignPopupOpen} onClose={closePopupUnassign}
                             containerStyle={lightBlueContainerStylePopup}/>
            </div>
            <div style={{textAlign: 'center'}}>
                <h1>Edit Status for a Project</h1>
                <div>
                    <button onClick={() => setProjectStatusPopupOpen(true)}>Edit Status</button>
                </div>
                <ProjectStatusPop isOpen={isProjectStatusPopupOpen} onClose={() => setProjectStatusPopupOpen(false)}
                                  containerStyle={lightBlueContainerStylePopup}/>
            </div>
        </div>
    )
}

export default Create