import axios from 'axios';

export default {

    getAllPothole() {
        return axios.get('/potholes')
    }
}