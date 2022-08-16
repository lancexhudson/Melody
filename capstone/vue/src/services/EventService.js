import axios from 'axios';

export default {
    listEventsForBand(bandId) {
        axios.get(`/events/band/${bandId}`);
    }
}