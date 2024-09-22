import http from '../../http-common'

class DatasService {
	getAll() {
		return http.get("/data");
	}
	getAllAnalogic() {
		return http.get("/data/analogic");
	}
	getAllNumeric() {
		return http.get("/data/numeric");
	}
	get(id) {
		return http.get(`/data/${id}`);
	}
	getDataNumericSensor(id) {
		return http.get(`numericsensors/${id}/data`);
	}
	getDataAnalogicSensor(id) {
		return http.get(`analogicsensors/${id}/data`);
	}
	createAnalogic(data) {
		return http.post("/data/analogic", data);
	}
	createNumeric(data) {
		return http.post("/data/numeric", data);
	}
	updateAnalogic(data) {
		return http.put("/data/analogic", data);
	}
	updateNumeric(data) {
		return http.put("/data/numeric", data);
	}
	delete(id) {
		return http.delete(`/data/${id}`);
	}
}

export default new DatasService();