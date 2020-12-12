import axios from 'axios';

export default {

    getPotholes() {
        return axios.get('/potholes')
    }
}