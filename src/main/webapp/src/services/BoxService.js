import http from '../../http-common'

class boxService {
	getAll() {
		return http.get("/box");
	}
	get(id) {
		return http.get(`/box/${id}`);
	}
	create(data) {
		return http.post("/box", data);
	}
	update(data) {
		return http.put("/box", data);
	}
	delete(id) {
		return http.delete(`/box/${id}`);
	}
}

export default new boxService();