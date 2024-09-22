<template>
	<v-card>
		<v-card-title>
			<h2>Les data</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="datas">
			</v-data-table>
		</v-card-text>
	</v-card>
</template>

<script>
export default {
	name: "ListDatas",
	data() {
		return {
			datas: [],
			headers: [
				{text: "Id", sortable: true, value: "id"},
				{text: "MeasureDate", sortable: true, value: "measureDate"},
			]
		}
	},
	computed: {
		datas1() {
			return this.$store.getters['datas/getAllDatas'];
		}
	},
	watch: {
		datas1: function(datas1) {
			this.datas = datas1;
		}
	},
	mounted() {
		this.$store.dispatch('datas/getDatas')
	}
}
</script>