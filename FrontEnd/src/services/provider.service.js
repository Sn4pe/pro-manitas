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

    async checkIfProvider(userId) {
        return await api.get(`/provider/${userId}/isProvider`);
    }

    updateProvider(userId, provider, token){

        return api.put(`/provider/update/${userId}`, provider, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export default new ProviderService();