import SensorService from "@/services/SensorService"

// Initial state
const state = {
	allSensors: [],
	allAnalogicSensors: [],
	allNumericSensors: []
}

// Getters
const getters = {
	getAllSensors: state => {
		return state.allSensors;
	},
	getAllAnalogicSensors: state => {
		return state.allAnalogicSensors;
	},
	getAllNumericSensors: state => {
		return state.allNumericSensors;
	}
}

// Actions
const actions = {
	getSensors({ commit }) {
		SensorService.getAll().then(
			response => {
				console.log(response.data);
				commit('setAllSensors', response.data);
			}
		);
	},
	getAnalogicSensors({ commit }) {
		SensorService.getAllAnalogic().then(
			response => {
				console.log(response.data);
				commit('setAllAnalogicSensors', response.data);
			}
		);
	},
	getNumericSensors({ commit }) {
		SensorService.getAllNumeric().then(
			response => {
				console.log(response.data);
				commit('setAllNumericSensors', response.data);
			}
		);
	},
	createAnalogicSensors({ commit }, sensors) {
		SensorService.createAnalogic(sensors).then(response => {
			commit('newAnalogicSensor', response.data);
		}).catch(error => console.log(error));
	},
	createNumericSensors({ commit }, sensors) {
		SensorService.createNumeric(sensors).then(response => {
			commit('newNumericSensor', response.data);
		}).catch(error => console.log(error));
	},
	updateAnalogicSensors({ commit }, sensors) {
		SensorService.updateAnalogic(sensors).then(response => {
			commit('updateAnalogicSensors', response.data);
		});
	},
	updateNumericSensors({ commit }, sensors) {
		SensorService.updateNumeric(sensors).then(response => {
			commit('updateNumericSensors', response.data);
		});
	},
	deleteSensors({ commit }, id) {
		SensorService.delete(id).then(() => {
			commit('deleteSensors', id);
		});
	}
}

// Mutations
const mutations = {
	setAllSensors(state, sensors) {
		state.allSensors = sensors;
	},
	setAllAnalogicSensors(state, sensors) {
		state.allAnalogicSensors = sensors;
	},
	setAllNumericSensors(state, sensors) {
		state.allNumericSensors = sensors;
	},
	newSensor(state, sensors) {
		state.allSensors.push(sensors);
	},
	newAnalogicSensor(state, sensors) {
		state.allAnalogicSensors.push(sensors);
	},
	newNumericSensor(state, sensors) {
		state.allNumericSensors.push(sensors);
	},
	updateSensors(state, sensors) {
		state.allSensors.splice(state.allSensors.findIndex(m => m.id == sensors.id), 1, sensors);
	},
	deleteSensors(state, id) {
		state.allSensors.splice(state.allSensors.findIndex(m => m.id, id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}
