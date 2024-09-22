import http from '../../http-common'

class cellarService {
	getAll() {
		return http.get("/cellar");
	}
	get(id) {
		return http.get(`/cellar/${id}`);
	}
	create(data) {
		return http.post("/cellar", data);
	}
	update(data) {
		return http.put("/cellar", data);
	}
	delete(id) {
		return http.delete(`/cellar/${id}`);
	}
}

export default new cellarService();