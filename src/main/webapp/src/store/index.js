import Vue from 'vue'
import Vuex from 'vuex'

import sensors from './modules/sensors'
import datas from './modules/datas'
import boxs from './modules/boxs'
import barrels from './modules/barrels'
import cellars from './modules/cellars'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
	},
	getters: {
	},
	mutations: {
	},
	actions: {
	},
	modules: {
		sensors,
		datas,
		boxs,
		barrels,
		cellars
	}
})
