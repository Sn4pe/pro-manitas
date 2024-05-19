import api from './api';
import http from "@/services/api";

class ServicesService {
    getAllServices(token) {
        return api.get('/services', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    getService(id, token) {
        return http.get(`/services/${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    createService(service, token) {
        return api.post('/services', service, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    updateService(id, service, token) {
        return http.put(`/services/${id}`, service, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    deleteService(id, token) {
        return http.delete(`/services/${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    checkOwnership(serviceId, token) {
        return api.get(`/services/${serviceId}/isOwner`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export default new ServicesService();
