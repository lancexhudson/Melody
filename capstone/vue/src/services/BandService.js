import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {

    listBands() {
        return http.get('/bands')
    },
    getBand(name) {
        return http.get(`/bands/name/${name}`);
    },
    getBandsByGenre(genre) {
        return http.get(`/bands/genre/${genre}`);
    },
    createBand(band) {
        return http.post(`/bands/register`, band)
    }


}
