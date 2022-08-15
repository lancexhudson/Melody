import axios from "axios";

export default {
    getIdForUser(userName) {
        return axios.get(`/user/${userName}`);
    }
}