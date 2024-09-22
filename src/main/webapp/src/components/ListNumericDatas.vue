<template>
	<v-card style="height: 100%;">
		<v-card-title style="height: 20%;">
			<h2>Les data numeriques</h2>
		</v-card-title>
		<v-card-text class="overflow-y-auto overflow-x-hidden" style="height: 60%;">
			<v-data-table :headers="headers" :items="datas" :items-per-page=-1 hide-default-footer>
				<template v-slot:[`item.actions`]="{ item }">
					<v-icon small @click="editNumericData(item)">mdi-pencil</v-icon>
					<v-icon small @click="deleteNumericData(item.id)">mdi-delete</v-icon>
				</template>
				<template v-slot:top>
					<v-dialog v-model="dialogDelete" max-width="500">
						<v-card>
							<v-card-title>
								<h4>Etes-vous sûr de vouloir le supprimer?</h4>
							</v-card-title>
							<v-card-actions>
								<v-spacer />
								<v-btn text @click="closeDeleteNumericData"> Annuler </v-btn>
								<v-btn text @click="confirmDeleteNumericData">OK </v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>
					<v-dialog v-model="dialogEdit" max-width="500">
						<v-card>
							<v-card-title>
								<h4>Mise à jour de la donnée numérique {{ editedNumericData.id }}</h4>
							</v-card-title>
							<v-card-text>
								<v-text-field v-model="editedNumericData.id" label="Id" readonly disabled/>
								<v-text-field v-model="editedNumericData.measureDate" label="Date et heure de mesure" required/>
								<v-select v-model="numericValueSelected" :items="numericValues" label="État" dense outlined return-object></v-select>
								<v-select v-model="sensorSelected" :items="sensors" item-text="name" item-value="id" :hint="`ID:${sensorSelected.id}, SRN:${sensorSelected.serialNumber}, nom:'${sensorSelected.name}'`" label="Capteur numérique" no-data-text="Aucun capteur numérique" dense outlined return-object></v-select>
							</v-card-text>
							<v-card-actions>
								<v-spacer />
								<v-btn text @click="closeUpdate">Annuler</v-btn>
								<v-btn text @click="confirmUpdate">OK</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>
				</template>
			</v-data-table>
		</v-card-text>
		<v-card-actions style="height: 20%;">
			<v-row>
				<v-col cols="2" class="d-flex align-center">
					<v-btn @click="saveNumericData">+</v-btn>
				</v-col>
				<v-col cols="3">
					<v-text-field v-model="newNumericData.measureDate" label="Date de mesure" required></v-text-field>
				</v-col>
				<v-col cols="3">
					<v-select v-model="numericValueSelected" :items="numericValues" label="État" dense outlined return-object></v-select>
				</v-col>
				<v-col class="d-flex align-center">
					<v-select v-model="sensorSelected" :items="sensors" item-text="name" item-value="id" :hint="`ID:${sensorSelected.id}, SRN:${sensorSelected.serialNumber}, nom:'${sensorSelected.name}'`" label="Capteur numérique" no-data-text="Aucun capteur numérique" dense outlined return-object></v-select>
				</v-col>
			</v-row>
		</v-card-actions>
	</v-card>
</template>

<script>
export default {
	name: "ListNumericDatas",
	data() {
		return {
			dialogDelete: false,
			idDelete: -1,
			dialogEdit: false,
			numericValueSelected: {text: "false", value: false},
			datas: [],
			sensors: [],
			headers: [
				{text: "Id", sortable: true, value: "id", width: "15.3%"},
				{text: "MeasureDate", sortable: true, value: "measureDate", width: "26.4%"},
				{text: "NumericValue", sortable: true, value: "numericValue", width: "26.4%"},
				{text: "NumericSensor", sortable: true, value: "numericSensor1.name", width: "22%"},
				{text: "Action", sortable: false, value: "actions"}
			],
			numericValues: [
				{text: "true", value: true},
				{text: "false", value: false}
			],
			newNumericData: {
				id: null,
				measureDate: "2000-01-01 00:00:00",
				numericValue: false,
				numericSensor1: null
			},
			defaultNewNumericData: {
				id: null,
				measureDate: "2000-01-01 00:00:00",
				numericValue: false,
				numericSensor1: null
			},
			editedNumericData: {
				id: null,
				measureDate: "2000-01-01 00:00:00",
				numericValue: false,
				numericSensor1: null
			},
			sensorSelected: {
				id: null,
				serialNumber: "",
				name: ""
			}
		}
	},
	methods: {
		saveNumericData() {
			var sensorSelected;
			if (this.sensorSelected.id == null) {
				sensorSelected = null;
			} else {
				sensorSelected = this.sensorSelected;
			}
			var data = {
				id: null,
				measureDate: this.newNumericData.measureDate.replace("T"," "),
				numericValue: this.numericValueSelected.value,
				numericSensor1: sensorSelected
			};
			console.log(data);
			this.$store.dispatch("datas/createNumericData", data);
			Object.assign(this.newNumericData, this.defaultNewNumericData);
		},
		editNumericData(datas){
			this.editedNumericData = Object.assign({}, datas);
			this.numericValueSelected = {text: "", value: datas.numericValue};
			this.sensorSelected = Object.assign({}, datas.numericSensor1);
			this.dialogEdit = true;
		},
		closeUpdate() {
			this.dialogEdit = false;
		},
		confirmUpdate() {
			if (this.sensorSelected.id == null) {
				this.editedNumericData.numericSensor1 = null;
			} else {
				this.editedNumericData.numericSensor1 = Object.assign({}, this.sensorSelected);
			}
			this.editedNumericData.numericValue = this.numericValueSelected.value;
			this.$store.dispatch("datas/updateNumericData", this.editedNumericData);
			this.dialogEdit = false;
		},
		deleteNumericData(id) {
			this.idDelete = id;
			this.dialogDelete = true;
		},
		closeDeleteNumericData() {
			this.dialogDelete = false;
		},
		confirmDeleteNumericData() {
			this.$store.dispatch("datas/deleteNumericData", this.idDelete);
			this.dialogDelete = false;
		}
	},
	computed: {
		datas1() {
			return this.$store.getters['datas/getAllNumericDatas'];
		},
		sensors1() {
			var sensors = this.$store.getters["sensors/getAllNumericSensors"];
			sensors.unshift({id: null,serialNumber: "",name: "Aucun"});
			return sensors;
		}
	},
	watch: {
		datas1: function(datas1) {
			this.datas = datas1;
		},
		sensors1: function (sensors1) {
			this.sensors = sensors1;
		}
	},
	mounted() {
		this.$store.dispatch('datas/getNumericDatas');
		this.$store.dispatch('sensors/getNumericSensors');
	}
}
</script>