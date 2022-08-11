import axios from 'axios';


export default {

    listBands() {
        return axios.get('/bands')
    },
    getBandById(bandId) {
        return axios.get(`/bands/id/${bandId}`);
    },
    getBandsByGenre(genre) {
        return axios.get(`/bands/genre/${genre}`);
    },
    createBand(band) {
        return axios.post(`/bands/register`, band)
    }


}
