import CellarService from "@/services/CellarService"

// Initial state
const state = {
	allCellars: []
}

// Getters
const getters = {
	getAllCellars: state => {
		return state.allCellars;
	}
}

// Actions
const actions = {
	getCellars ({ commit }) {
		CellarService.getAll().then(
			response => {
				console.log(response.data);
				commit('setAllCellars', response.data);
			}
		);
	},
	createCellar({ commit }, cellar) {
		CellarService.create(cellar).then(response => {
			commit('newCellar', response.data);
		}).catch(error => console.log(error));
	},
	updateCellar({ commit }, cellar) {
		CellarService.update(cellar).then(response => {
			commit('updateCellar', response.data);
		});
	},
	deleteCellar({ commit }, id) {
		CellarService.delete(id).then(() => {
			commit('deleteCellar', id);
		});
	}
}

// Mutations
const mutations = {
	setAllCellars (state, Cellars) {
		state.allCellars = Cellars;
	},
	newCellar(state, cellar) {
		state.allCellars.push(cellar);
	},
	updateCellar (state, cellar) {
		state.allCellars.splice(state.allCellars.findIndex(m => m.id ==
		cellar.id), 1, cellar);
	},
	deleteCellar (state, id) {
		state.allCellars.splice(state.allCellars.findIndex(m => m.id == id),1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}