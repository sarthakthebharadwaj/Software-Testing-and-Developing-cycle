import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getWorkers } from '../services/dataService'
import LocationID from '../utils/location'
import { mediumBlueContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Worker = ( worker, active ) => {
    return (
        <div>
            <div>{worker.name}</div>
            {active === true ? WorkerBody(worker) : null}
        </div>
    );
}

const WorkerBody = ( worker ) => {
    const qualificationsWithNumbers = worker.qualifications.map((qualification, index) => 
    `${index + 1}. ${qualification}`
    );

    return (
        <div style={mediumBlueContainerStyle}>
            Salary: <br /> {worker.salary} <br />
            <br />
            Workload: <br /> {worker.workload} <br />
            <br />
            {worker.qualifications.length === 0 ? (
                <div>Qualifications: None</div>
            ) :
            (
                <div> Qualifications: <ClickList list={worker.qualifications} styles={mediumBlueContainerStyle} path="/qualifications" /></div>
            )}
            <br />
            {worker.projects.length === 0 ? (
                <div>Projects: None</div>
            ) :
            (
                <div> Projects: <ClickList list={worker.projects} styles={mediumBlueContainerStyle} path="/projects" /></div>
            )}

        </div>
    )
}

const Workers = () => {
    const [workers, setWorkers] = useState([])
    useEffect(() => { getWorkers().then(setWorkers) }, [])
    const active = LocationID('workers', workers, 'name')


    return (
        <div style={pageStyle}>
            <h1>
                Employed Workers List:
            </h1>
            <ClickList active={active} list={workers} item={Worker} path='/workers' id='name' />
        </div>
    )
}

export default Workers