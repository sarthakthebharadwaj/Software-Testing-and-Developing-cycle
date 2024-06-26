import axios from 'axios'

const SERVER_ADDRESS = 'http://localhost:4567/api/'

export function getQualification(description) {
    return axios.get(SERVER_ADDRESS + 'qualifications/' + description).then((res) => JSON.parse(res.request.response))
}

export function getQualifications() {
    return axios.get(SERVER_ADDRESS + 'qualifications').then((res) => JSON.parse(res.request.response).sort((a, b) => a.description.localeCompare(b.description)))
}

export function createQualification(description) {
    return axios.post(SERVER_ADDRESS + 'qualifications/' + description, { description: description });
}

export function getProjects() {
    return axios.get(SERVER_ADDRESS + 'projects').then(res => JSON.parse(res.request.response)).then(data => data.sort((a,b)=>a.name.localeCompare(b.name)));
}

export function getWorkers() {
    return axios.get(SERVER_ADDRESS + 'workers').then((res) => JSON.parse(res.request.response).sort((a, b) => a.name.localeCompare(b.name)))
}

export function getWorker(name) {
    return axios.get(SERVER_ADDRESS + 'workers/' + name).then((res) => JSON.parse(res.request.response))
}

export function createWorker(name, qualifications, salary) {
    return axios.post(SERVER_ADDRESS + 'workers/' + name, { name: name, qualifications: qualifications, salary: salary });
}

export function assignWorker(worker, project){
    return axios.put(SERVER_ADDRESS + 'assign', { worker: worker, project: project });
}

export function unassignWorker(worker, project){
    return axios.put(SERVER_ADDRESS + 'unassign', { worker: worker, project: project });
}

export function createProject(name, quals, size) {
    return axios.post(SERVER_ADDRESS + 'projects/' + name, { name: name, qualifications: quals, size: size })
}

export function startProject(name){
    return axios.put(SERVER_ADDRESS + 'start' , { name: name })
}

export function finishProject(name){
    return axios.put(SERVER_ADDRESS + 'finish' , { name: name })
}