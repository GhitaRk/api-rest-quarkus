import BarrelService from "@/services/BarrelService"

// Initial state
const state = {
	allBarrels: []
}

// Getters
const getters = {
	getAllBarrels: state => {
		return state.allBarrels;
	}
}

// Actions
const actions = {
	getBarrels ({ commit }) {
		BarrelService.getAll().then(
			response => {
				console.log(response.data);
				commit('setAllBarrels', response.data);
			}
		);
	},
	createBarrel({ commit }, barrel) {
		BarrelService.create(barrel).then(response => {
			commit('newBarrel', response.data);
		}).catch(error => console.log(error));
	},
	updateBarrel({ commit }, barrel) {
		BarrelService.update(barrel).then(response => {
			commit('updateBarrel', response.data);
		});
	},
	deleteBarrel({ commit }, id) {
		BarrelService.delete(id).then(() => {
			commit('deleteBarrel', id);
		});
	}
}

// Mutations
const mutations = {
	setAllBarrels (state, Barrels) {
		state.allBarrels = Barrels;
	},
	newBarrel(state, barrel) {
		state.allBarrels.push(barrel);
	},
	updateBarrel (state, barrel) {
		state.allBarrels.splice(state.allBarrels.findIndex(m => m.id ==
		barrel.id), 1, barrel);
	},
	deleteBarrel (state, id) {
		state.allBarrels.splice(state.allBarrels.findIndex(m => m.id == id),1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}