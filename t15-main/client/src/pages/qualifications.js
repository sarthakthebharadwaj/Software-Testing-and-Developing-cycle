import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getQualifications } from '../services/dataService'
import LocationID from '../utils/location'
import { mediumBlueContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Qualification = (qualification, active) => {
    return (
        <div>
            <div>{qualification.description}</div>
            {active === true ? QualificationBody(qualification) : null}
        </div>
    )
}

const QualificationBody = (qualification) => {
    const numberedWorkers = qualification.workers.map((worker, index) => `${index + 1}. ${worker}`);
    return (
        <div style={mediumBlueContainerStyle}>
            Workers: <ClickList list={numberedWorkers} styles={mediumBlueContainerStyle} path="/workers" />
        </div>
    );
}


const Qualifications = () => {
    const [qualifications, setQualifications] = useState([])
    useEffect(() => { getQualifications().then(setQualifications) }, [])
    const active = LocationID('qualifications', qualifications, 'description')
    return (
        <div style={pageStyle}>
            <h1>
                Qualifications List:
            </h1>
            <ClickList active={active} list={qualifications} item={Qualification} path='/qualifications' id='description' />
        </div>
    )
}

export default Qualifications