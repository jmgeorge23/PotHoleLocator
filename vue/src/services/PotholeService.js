import axios from 'axios';

export default {

    getPotholes() {
        return axios.get('/potholes')
    },
    sendReport(report){
        return axios.post('/potholes',report)
    }
}