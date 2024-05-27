import { useEffect , useState} from 'react';
import {getProjects} from '../services/dataService';
import ClickList from '../components/ClickList';
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles';
import LocationID from '../utils/location';

const Project = (project , active) => {
    return (
        <div>
            <div>{project.name} - {project.status}</div>
            {active == true? ProjectBody(project) : null }
        </div>
    )
}

const ProjectBody = (project ) => {
    return (
        <div style = {grayContainerStyle}>
            {project.workers.length === 0 ? (
                <div>Assigned Employees: None</div>
            ) :
            (
                <div>Assigned Employees:<ClickList list={project.workers} styles={darkGrayContainerStyle} path="/workers" /></div>
            )}
            {project.qualifications.length === 0 ? (
                <div>Required Qualifications: None</div>
            ) :
            (
                <div>Required Qualifications: <ClickList list={project.qualifications} styles={darkGrayContainerStyle} path="/qualifications" /></div>
            )}
            {project.missingQualifications.length === 0 ? (
                <div>Missing Qualifications: None</div>
            ) :
            (
                <div>Missing Qualifications: <ClickList list={project.missingQualifications} styles={darkGrayContainerStyle} path="/qualifications" /></div>
            )}

        </div>
    )
}


const Projects = () => {
    const [projects,setProjects] = useState([]);
    useEffect(() => { getProjects().then(setProjects);},[]);

    const active = LocationID('projects',projects,'name');

    return (
        <div style={pageStyle}>
            <h1>This page displays a table containing all the projects.</h1>
            <ClickList active={active} list={projects} item={Project} path='/projects' id='name' />
        </div>
    )
}

export default Projects