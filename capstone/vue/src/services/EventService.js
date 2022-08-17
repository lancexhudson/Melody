import axios from 'axios';

export default {
    listEventsForBand(bandId) {
        return axios.get(`/events/band/${bandId}`);
    },
    createAnEvent(newEvent) {
        return axios.post(`/events/register`, newEvent);
    }
}