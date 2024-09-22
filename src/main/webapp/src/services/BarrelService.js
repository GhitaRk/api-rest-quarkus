import http from '../../http-common'

class barrelService {
	getAll() {
		return http.get("/barrel");
	}
	get(id) {
		return http.get(`/barrel/${id}`);
	}
	create(data) {
		return http.post("/barrel", data);
	}
	update(data) {
		return http.put("/barrel", data);
	}
	delete(id) {
		return http.delete(`/barrel/${id}`);
	}
}

export default new barrelService();