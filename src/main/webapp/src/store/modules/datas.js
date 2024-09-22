import DatasService from "@/services/DatasService"

// Initial state
const state = {
	allDatas: [],
	allAnalogicDatas: [],
	allNumericDatas: [],
	allNumericSensorDatas:[],
	allAnalogicSensorDatas:[]
}

// Getters
const getters = {
	getAllDatas: state => {
		return state.allDatas;
	},
	getAllAnalogicDatas: state => {
		return state.allAnalogicDatas;
	},
	getAllNumericDatas: state => {
		return state.allNumericDatas;
	},
	getAllNumericSensorDatas: state => {
		return state.allNumericSensorDatas;
	},
	getAllAnalogicSensorDatas: state => {
		return state.allAnalogicSensorDatas;
	}
}

// Actions
const actions = {
	getDatas ({ commit }) {
		DatasService.getAll().then(
			response => {
				console.log(response.data);
				commit('setAllDatas', response.data);
			}
		);
	},
	getAnalogicDatas ({ commit }) {
		DatasService.getAllAnalogic().then(
			response => {
				console.log(response.data);
				commit('setAllAnalogicDatas', response.data);
			}
		);
	},
	getNumericDatas ({ commit }) {
		DatasService.getAllNumeric().then(
			response => {
				console.log(response.data);
				commit('setAllNumericDatas', response.data);
			}
		);
	},
	getNumericDatasSensors ({ commit }, id) {
		DatasService.getDataNumericSensor(id).then(
			response => {
				console.log(response.data);
				commit('setallNumericSensorDatas', response.data);
			}
		);
	},
	getAnalogicDatasSensors ({ commit }, id) {
		DatasService.getDataAnalogicSensor(id).then(
			response => {
				console.log(response.data);
				commit('setallAnalogicSensorDatas', response.data);
			}
		);
	},
	createAnalogicData({ commit }, analogicData) {
		DatasService.createAnalogic(analogicData).then(response => {
			commit('newAnalogicData', response.data);
		}).catch( error => console.log(error));
	},
	createNumericData({ commit }, numericData) {
		DatasService.createNumeric(numericData).then(response => {
			commit('newNumericData', response.data);
		}).catch( error => console.log(error));
	},
	updateAnalogicData({ commit }, data) {
		DatasService.updateAnalogic(data).then(response => {
			commit('updateAnalogicData', response.data);
		});
	},
	updateNumericData({ commit }, data) {
		DatasService.updateNumeric(data).then(response => {
			commit('updateNumericData', response.data);
		});
	},
	deleteAnalogicData({ commit }, id) {
		DatasService.delete(id).then(
			() => {
				commit('deleteAnalogicData', id);
			}
		);
	},
	deleteNumericData({ commit }, id) {
		DatasService.delete(id).then(
			() => {
				commit('deleteNumericData', id);
			}
		);
	}
}

// Mutations
const mutations = {
	setAllDatas (state, datas) {
		state.allDatas = datas;
	},
	setAllAnalogicDatas (state, datas) {
		state.allAnalogicDatas = datas;
	},
	setAllNumericDatas (state, datas) {
		state.allNumericDatas = datas;
	},
	setallNumericSensorDatas(state, datas) {
		state.allNumericSensorDatas = datas;
	},
	setallAnalogicSensorDatas(state, datas) {
		state.allAnalogicSensorDatas = datas;
	},
	newAnalogicData(state, analogicData) {
		state.allDatas.push(analogicData);
		state.allAnalogicDatas.push(analogicData);
	},
	newNumericData(state, numericData) {
		state.allDatas.push(numericData);
		state.allNumericDatas.push(numericData);
	},
	updateAnalogicData(state, data) {
		state.allDatas.splice(state.allDatas.findIndex(m => m.id == data.id), 1, data);
		state.allAnalogicDatas.splice(state.allAnalogicDatas.findIndex(m => m.id == data.id), 1, data);
	},
	updateNumericData(state, data) {
		state.allDatas.splice(state.allDatas.findIndex(m => m.id == data.id), 1, data);
		state.allNumericDatas.splice(state.allNumericDatas.findIndex(m => m.id == data.id), 1, data);
	},
	deleteAnalogicData(state, id) {
		state.allDatas.splice(state.allDatas.findIndex(m => m.id == id), 1);
		state.allAnalogicDatas.splice(state.allAnalogicDatas.findIndex(m => m.id == id), 1);
	},
	deleteNumericData(state, id) {
		state.allDatas.splice(state.allDatas.findIndex(m => m.id == id), 1);
		state.allNumericDatas.splice(state.allNumericDatas.findIndex(m => m.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}
