import axios from 'axios';

export default {
    listEventsForBand(bandId) {
        return axios.get(`/events/band/${bandId}`);
    }
}