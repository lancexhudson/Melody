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
    },
    updateBand(bandId, band) {
        return axios.put(`/bands/update/${bandId}`, band)
    },
    makeFavorite(favorite) {
        return axios.put(`/bands/favorites`, favorite)
    },
    favoriteBandsByUser(userId) {
        return axios.get(`/bands/favorites/${userId}`)
    },
    usersFollowingMyBand(bandId) {
        return axios.get(`/bands/myFollowers/${bandId}`);
    },
    getMyManager(bandId) {
        return axios.get(`/bands/myManager/${bandId}`);
    },
    UNfollow(userId, bandId) {
        return axios.delete(`/band/${bandId}/delete/favorite/${userId}`);
    }


}
