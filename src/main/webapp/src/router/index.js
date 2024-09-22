/* eslint-disable */

import Vue from 'vue'
import VueRouter from 'vue-router'

import Bienvenue from '../components/Bienvenue.vue'
import AllSensors from '../views/AllSensors.vue'
import AllDatas from '../views/AllDatas.vue'
import AllBoxs from '../views/AllBoxs.vue'
import AllBarrels from '../views/AllBarrels.vue'
import AllCellars from '../views/AllCellars.vue'

Vue.use(VueRouter)

const routes = [
	{path: '/', name: 'Home', component: Bienvenue},
	{path: '/sensors', name: 'AllSensors', component: AllSensors},
	{path: '/datas', name: 'Alldatas', component: AllDatas},
	{path: '/boxs', name: 'AllBoxs', component: AllBoxs},
	{path: '/barrels', name: 'AllBarrels', component: AllBarrels},
	{path: '/cellars', name: 'AllCellars', component: AllCellars}

]

const router = new VueRouter({
	routes
})

export default router;