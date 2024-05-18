import api from './api';

class UserService {
    getPublicContent() {
        return api.get('/test/all');
    }

    getUserBoard() {
        return api.get('/test/user');
    }

    getModeratorBoard() {
        return api.get('/provider/all');
    }

    getAdminBoard() {
        return api.get('/test/admin');
    }
}

export default new UserService();