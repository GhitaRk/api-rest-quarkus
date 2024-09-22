<template>
  <v-card>
    <v-card-title>
      <h2>Les sensors numériques</h2>
    </v-card-title>
    <v-card-text>
      <v-data-table :headers="headers" :items="sensors">
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon small @click="editSensors(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteSensors(item.id)">mdi-delete</v-icon>
          <v-icon small @click="choseSensors(item)">mdi-loupe</v-icon>
        </template>
        <template v-slot:top>
          <v-dialog v-model="dialogDelete" max-width="500">
            <v-card>
              <v-card-title>
                <h4>Etes-vous sûr de vouloir le supprimer?</h4>
              </v-card-title>
              <v-card-actions>
                <v-spacer />
                <v-btn text @click="closeDelete"> Annuler </v-btn>
                <v-btn text @click="confirmDelete">OK </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogEdit" max-width="500">
            <v-card>
              <v-card-title>
                <h4>Mise à jour numeric sensors {{ editedSensors.id }}</h4>
              </v-card-title>
              <v-card-text>
                <v-text-field
                  v-model="editedSensors.id"
                  label="Id"
                  readonly
                  disabled
                />
                <v-text-field
                  v-model="editedSensors.serialNumber"
                  label="serialNumber"
                  required
                />
                <v-text-field
                  v-model="editedSensors.name"
                  label="name"
                  required
                />
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn text @click="closeUpdate"> Annuler </v-btn>
                <v-btn text @click="confirmUpdate">OK </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog v-model="choseSensorSelected" max-width="500">
            <v-card>
              <v-card-title>
                <h4>Data of a specific sensor:</h4>
              </v-card-title>
              <v-card-text>
                <ListSensorData :SensorNumber=sensorchosen.id> </ListSensorData>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn text @click="closeSensorSelected"> Close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </template>
      </v-data-table>
    </v-card-text>
  </v-card>
</template>

<script>
import ListSensorData from "@/components/ListNumericSensorData.vue";

export default {
  components: {
    ListSensorData,
  },
  name: "ListNumericSensors",
  data() {
    return {
      number: 21,
      choseSensorSelected: false,
      dialogDelete: false,
      idDelete: -1,
      dialogEdit: false,
      sensorchosen:{
        id: -1,
        serialNumber: "",
        name: "",
      },
      editedSensors: {
        id: -1,
        serialNumber: "",
        name: "",
      },
      sensors: [],
      headers: [
        { text: "Id", sortable: true, value: "id" },
        { text: "SerialNumber", sortable: true, value: "serialNumber" },
        { text: "Name", sortable: true, value: "name" },
        { text: "Actions", sortable: false, value: "actions" },
      ],
    };
  },
  computed: {
    sensors1() {
      return this.$store.getters["sensors/getAllNumericSensors"];
    },
  },
  methods: {
    deleteSensors(id) {
      this.idDelete = id;
      this.dialogDelete = true;
    },
    choseSensors(sensors) {
      this.sensorchosen = Object.assign({}, sensors);
      this.$store.dispatch("datas/getNumericDatasSensors", sensors.id);
      this.choseSensorSelected = true;
    },
    closeDelete() {
      this.dialogDelete = false;
    },
    confirmDelete() {
      this.$store.dispatch("sensors/deleteSensors", this.idDelete);
      this.dialogDelete = false;
    },
    editSensors(sensors) {
      this.editedSensors = Object.assign({}, sensors);
      this.dialogEdit = true;
    },
    closeUpdate() {
      this.dialogEdit = false;
    },
    confirmUpdate() {
      this.$store.dispatch("sensors/updateNumericSensors", this.editedSensors);
      this.dialogEdit = false;
    },

    closeSensorSelected() {
      this.choseSensorSelected = false;
    },
  },
  watch: {
    sensors1: function (sensors1) {
      this.sensors = sensors1;
    },
  },
  mounted() {
    this.$store.dispatch("sensors/getNumericSensors");
  },
};
</script>