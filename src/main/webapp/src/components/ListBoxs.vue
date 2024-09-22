<template>
  <v-card>
    <v-card-title>
      <h2>Les Boxs</h2>
    </v-card-title>
    <v-card-text>
      <v-data-table :headers="headers" :items="boxs">
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon small @click="editBox(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteBox(item.id)">mdi-delete</v-icon>
        </template>
        <template v-slot:top>
          <v-dialog v-model="dialogDelete" max-width="500">
            <v-card>
              <v-card-title>
                <h4>Estes-vous sûr de vouloir le supprimer?</h4>
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
                <h4>Mise à jour Boxs {{ editedBox.id }}</h4>
              </v-card-title>
              <v-card-text>
                <v-text-field
                  v-model="editedBox.id"
                  label="Id"
                  readonly
                  disabled
                />
                <v-text-field
                  v-model="editedBox.serialNumber"
                  label="serialNumber"
                  required
                />
                <v-text-field v-model="editedBox.name" label="name" required />
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
  </v-card>
</template>


<script>
export default {
  name: "ListBoxs",
  data() {
    return {
      dialogDelete: false,
      idDelete: -1,
      dialogEdit: false,
      editedBox: {
        id: -1,
        serialNumber: "",
        name: "",
      },
      boxs: [],
      headers: [
        { text: "Id", sortable: true, value: "id" },
        { text: "SerialNumber", sortable: true, value: "serialNumber" },
        { text: "Name", sortable: true, value: "name" },
        { text: "Actions", sortable: false, value: "actions" },
      ],
    };
  },
  computed: {
    boxs1() {
      return this.$store.getters["boxs/getAllBoxs"];
    },
  },
  methods: {
    deleteBox(id) {
      this.idDelete = id;
      this.dialogDelete = true;
    },
    closeDelete() {
      this.dialogDelete = false;
    },
    confirmDelete() {
      this.$store.dispatch("boxs/deleteBox", this.idDelete);
      this.dialogDelete = false;
    },
    editBox(boxs) {
      this.editedBox = Object.assign({}, boxs);
      this.dialogEdit = true;
    },
    closeUpdate() {
      this.dialogEdit = false;
    },
    confirmUpdate() {
      this.$store.dispatch("boxs/updateBox", this.editedBox);
      this.dialogEdit = false;
    },
  },
  watch: {
    boxs1: function (boxs1) {
      this.boxs = boxs1;
    },
  },
  mounted() {
    this.$store.dispatch("boxs/getBoxs");
  },
};
</script>