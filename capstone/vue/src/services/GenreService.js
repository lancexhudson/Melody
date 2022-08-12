import axios from 'axios';


export default {

    listGenres() {
        return axios.get('/genres')
    },
    getGenreById(genreId) {
        return axios.get(`/genres/${genreId}`)
    },
    getGenreByName(genreName) {
        return axios.get(`/genres/${genreName}`)
    },
}