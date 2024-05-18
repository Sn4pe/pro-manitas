import api from './api';
import http from "@/services/api";

class ProviderService {

    getAllProviders() {
        return api.get('/provider/all');
    }
    getProvider(id) {
        return http.get(`/provider/${id}`);
    }

    createProvider(userId, provider, token) {

        return api.post(`/provider/create/${userId}`, provider, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export default new ProviderService();