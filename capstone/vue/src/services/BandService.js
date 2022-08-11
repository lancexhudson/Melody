import axios from 'axios';


export default {

    listBands() {
        return axios.get('/bands')
    },
    getBand(bandName) {
        return axios.get(`/bands/name/${bandName}`);
    },
    getBandsByGenre(genre) {
        return axios.get(`/bands/genre/${genre}`);
    },
    createBand(band) {
        return axios.post(`/bands/register`, band)
    }


}
