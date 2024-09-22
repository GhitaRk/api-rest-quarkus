import http from '../../http-common'

class SensorService {
	getAll() {
		return http.get("/sensors");
	}
	getAllAnalogic() {
		return http.get("/analogicsensors");
	}
	getAllNumeric() {
		return http.get("/numericsensors");
	}
	get(id) {
		return http.get(`/sensors/${id}`);
	}
	createAnalogic(data) {
		return http.post("/analogicsensors", data);
	}
	createNumeric(data) {
		return http.post("/numericsensors", data);
	}
	updateAnalogic(data) {
		return http.put("/analogicsensors", data);
	}
	updateNumeric(data) {
		return http.put("/numericsensors", data);
	}
	delete(id) {
		return http.delete(`/sensors/${id}`);
	}
}

export default new SensorService();