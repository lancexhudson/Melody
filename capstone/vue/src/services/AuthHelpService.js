export default {
    canModify(userId, managerId) {
        return userId == managerId ? true : false;
    }
}