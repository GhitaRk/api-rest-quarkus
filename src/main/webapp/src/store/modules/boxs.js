import BoxService from "@/services/BoxService"

// Initial state
const state = {
	allBoxs: []
}

// Getters
const getters = {
	getAllBoxs: state => {
		return state.allBoxs;
	}
}

// Actions
const actions = {
	getBoxs({ commit }) {
		BoxService.getAll().then(
			response => {
				console.log(response.data);
				commit('setAllBoxs', response.data);
			}
		);
	},
	createBox({ commit }, box) {
		BoxService.create(box).then(response => {
			commit('newBox', response.data);
		}).catch(error => console.log(error));
	},
	updateBox({ commit }, box) {
		BoxService.update(box).then(response => {
			commit('updateBox', response.data);
		});
	},
	deleteBox({ commit }, id) {
		BoxService.delete(id).then(() => {
			commit('deleteBox', id);
		});
	}
}

// Mutations
const mutations = {
	setAllBoxs(state, boxs) {
		state.allBoxs = boxs;
	},
	newBox(state, box) {
		state.allBoxs.push(box);
	},
	updateBox (state, box) {
		state.allBoxs.splice(state.allBoxs.findIndex(m => m.id ==
		box.id), 1, box);
	},
	deleteBox (state, id) {
		state.allBoxs.splice(state.allBoxs.findIndex(m => m.id == id),1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}
