<template>
  <v-card>
    <v-card-title>
      <h2>Les Cellars</h2>
    </v-card-title>
    <v-card-text>
      <v-data-table :headers="headers" :items="cellars">
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon small @click="editCellar(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteCellar(item.id)">mdi-delete</v-icon>
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
                <h4>Mise à jour Cellars {{ editedCellar.id }}</h4>
              </v-card-title>
              <v-card-text>
                <v-text-field
                  v-model="editedCellar.id"
                  label="Id"
                  readonly
                  disabled
                />
                <v-text-field
                  v-model="editedCellar.capacity"
                  label="Capacité"
                  required
                />
                <v-text-field v-model="editedCellar.name" label="name" required />
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
  name: "ListCellars",
  data() {
    return {
      dialogDelete: false,
      idDelete: -1,
      dialogEdit: false,
      editedCellar: {
        id: -1,
        capacity: "",
        name: "",
      },
      cellars: [],
      headers: [
        { text: "Id", sortable: true, value: "id" },
        { text: "Capacity", sortable: true, value: "capacity" },
        { text: "Name", sortable: true, value: "name" },
        { text: "Actions", sortable: false, value: "actions" },
      ],
    };
  },
  computed: {
    cellars1() {
      return this.$store.getters["cellars/getAllCellars"];
    },
  },
  methods: {
    deleteCellar(id) {
      this.idDelete = id;
      this.dialogDelete = true;
    },
    closeDelete() {
      this.dialogDelete = false;
    },
    confirmDelete() {
      this.$store.dispatch("cellars/deleteCellar", this.idDelete);
      this.dialogDelete = false;
    },
    editCellar(cellars) {
      this.editedCellar = Object.assign({}, cellars);
      this.dialogEdit = true;
    },
    closeUpdate() {
      this.dialogEdit = false;
    },
    confirmUpdate() {
      this.$store.dispatch("cellars/updateCellar", this.editedCellar);
      this.dialogEdit = false;
    },
  },
  watch: {
    cellars1: function (cellars1) {
      this.cellars = cellars1;
    },
  },
  mounted() {
    this.$store.dispatch("cellars/getCellars");
  },
};
</script>