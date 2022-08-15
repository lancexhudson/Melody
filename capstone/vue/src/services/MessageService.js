import axios from 'axios';

export default {
    createMessage(message) {
        return axios.post(`/message/send`, message);
    },

    getMessageByMessageId(messageId) {
        return axios.get(`/message/${messageId}`);
    },

    getMessagesForUser(userId) {
        return axios.get(`/message/user/${userId}`);
    },

    getMessagesForBand(bandId) {
        return axios.get(`/message/band/${bandId}`);
    },

    deleteMessage(messageId) {
        return axios.delete(`/message/delete/${messageId}`);
    },
    
}