<template>
	<v-card style="height: 100%;">
		<v-card-title style="height: 20%;">
			<h2>Les data analogiques</h2>
		</v-card-title>
		<v-card-text class="overflow-y-auto overflow-x-hidden" style="height: 60%;">
			<v-data-table :headers="headers" :items="datas" :items-per-page=-1 hide-default-footer>
				<template v-slot:[`item.actions`]="{ item }">
					<v-icon small @click="editAnalogicData(item)">mdi-pencil</v-icon>
					<v-icon small @click="deleteAnalogicData(item.id)">mdi-delete</v-icon>
				</template>
				<template v-slot:top>
					<v-dialog v-model="dialogDelete" max-width="500">
						<v-card>
							<v-card-title>
								<h4>Etes-vous sûr de vouloir le supprimer?</h4>
							</v-card-title>
							<v-card-actions>
								<v-spacer />
								<v-btn text @click="closeDeleteAnalogicData"> Annuler </v-btn>
								<v-btn text @click="confirmDeleteAnalogicData">OK </v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>
					<v-dialog v-model="dialogEdit" max-width="500">
						<v-card>
							<v-card-title>
								<h4>Mise à jour de la donnée analogique {{ editedAnalogicData.id }}</h4>
							</v-card-title>
							<v-card-text>
								<v-text-field v-model="editedAnalogicData.id" label="Id" readonly disabled/>
								<v-text-field v-model="editedAnalogicData.measureDate" label="Date et heure de mesure" required/>
								<v-text-field v-model="editedAnalogicData.analogicValue" label="Mesure analogique" required/>
								<v-select v-model="sensorSelected" :items="sensors" item-text="name" item-value="id" :hint="`ID:${sensorSelected.id}, SRN:${sensorSelected.serialNumber}, nom:'${sensorSelected.name}'`" label="Capteur analogique" no-data-text="Aucun capteur analogique" dense outlined return-object></v-select>
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
					<v-btn @click="saveAnalogicData">+</v-btn>
				</v-col>
				<v-col cols="3">
					<v-text-field v-model="newAnalogicData.measureDate" label="Date de mesure" required></v-text-field>
				</v-col>
				<v-col cols="3">
					<v-text-field v-model="newAnalogicData.analogicValue" label="Mesure" required></v-text-field>
				</v-col>
				<v-col class="d-flex align-center">
					<v-select v-model="sensorSelected" :items="sensors" item-text="name" item-value="id" :hint="`ID:${sensorSelected.id}, SRN:${sensorSelected.serialNumber}, nom:'${sensorSelected.name}'`" label="Capteur analogique" no-data-text="Aucun capteur analogique" dense outlined return-object></v-select>
				</v-col>
			</v-row>
		</v-card-actions>
	</v-card>
</template>

<script>

export default {
	name: "ListAnalogicDatas",
	data() {
		return {
			dialogDelete: false,
			idDelete: -1,
			dialogEdit: false,
			datas: [],
			sensors: [],
			headers: [
				{text: "Id", sortable: true, value: "id", width: "15.3%"},
				{text: "MeasureDate", sortable: true, value: "measureDate", width: "26.4%"},
				{text: "AnalogicValue", sortable: true, value: "analogicValue", width: "26.4%"},
				{text: "AnalogicSensor", sortable: true, value: "analogicSensor1.name", width: "22%"},
				{text: "Action", sortable: false, value: "actions"}
			],
			newAnalogicData: {
				id: null,
				measureDate: "2000-01-01 00:00:00",
				analogicValue: 0,
				analogicSensor1: null
			},
			defaultNewAnalogicData: {
				id: null,
				measureDate: "2000-01-01 00:00:00",
				analogicValue: 0,
				analogicSensor1: null
			},
			editedAnalogicData: {
				id: null,
				measureDate: "2000-01-01 00:00:00",
				analogicValue: 0,
				analogicSensor1: null
			},
			sensorSelected: {
				id: null,
				serialNumber: "",
				name: ""
			}
		}
	},
	methods: {
		saveAnalogicData() {
			var sensorSelected;
			if (this.sensorSelected.id == null) {
				sensorSelected = null;
			} else {
				sensorSelected = this.sensorSelected;
			}
			var data = {
				id: null,
				measureDate: this.newAnalogicData.measureDate.replace("T"," "),
				analogicValue: this.newAnalogicData.analogicValue,
				analogicSensor1: sensorSelected
			};
			console.log(data);
			this.$store.dispatch("datas/createAnalogicData", data);
			Object.assign(this.newAnalogicData, this.defaultNewAnalogicData);
		},
		editAnalogicData(datas) {
			this.editedAnalogicData = Object.assign({}, datas);
			this.sensorSelected = Object.assign({}, datas.analogicSensor1);
			this.dialogEdit = true;
		},
		closeUpdate() {
			this.dialogEdit = false;
		},
		confirmUpdate() {
			if (this.sensorSelected.id == null) {
				this.editedAnalogicData.analogicSensor1 = null;
			} else {
				this.editedAnalogicData.analogicSensor1 = Object.assign({}, this.sensorSelected);
			}
			this.$store.dispatch("datas/updateAnalogicData", this.editedAnalogicData);
			this.dialogEdit = false;
		},
		deleteAnalogicData(id) {
			this.idDelete = id;
			this.dialogDelete = true;
		},
		closeDeleteAnalogicData() {
			this.dialogDelete = false;
		},
		confirmDeleteAnalogicData() {
			this.$store.dispatch("datas/deleteAnalogicData", this.idDelete);
			this.dialogDelete = false;
		}
	},
	computed: {
		datas1() {
			return this.$store.getters['datas/getAllAnalogicDatas'];
		},
		sensors1() {
			var sensors = this.$store.getters["sensors/getAllAnalogicSensors"];
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
		this.$store.dispatch('datas/getAnalogicDatas');
		this.$store.dispatch('sensors/getAnalogicSensors');
	}
}
</script>