import axios from 'axios';


export default {

    listBands() {
        return axios.get('/bands')
    },
    getBand(name) {
        return axios.get(`/bands/name/${name}`);
    },
    getBandsByGenre(genre) {
        return axios.get(`/bands/genre/${genre}`);
    },
    createBand(band) {
        return axios.post(`/bands/register`, band)
    }


}
