<template>
  <v-card>
    <v-card-title>
      <h2>Les Barrels</h2>
    </v-card-title>
    <v-card-text>
      <v-data-table :headers="headers" :items="barrels">
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon small @click="editBarrel(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteBarrel(item.id)">mdi-delete</v-icon>
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
                <h4>Mise à jour Barrels {{ editedBarrel.id }}</h4>
              </v-card-title>
              <v-card-text>
                <v-text-field
                  v-model="editedBarrel.id"
                  label="Id"
                  readonly
                  disabled
                />
                <v-text-field
                  v-model="editedBarrel.load"
                  label="Capacité"
                  required
                />
                <v-text-field v-model="editedBarrel.name"
                 label="name"
                 required
                />
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
  name: "ListBarrels",
  data() {
    return {
      dialogDelete: false,
      idDelete: -1,
      dialogEdit: false,
      editedBarrel: {
        id: -1,
        load: "",
        name: "",
      },
      barrels: [],
      headers: [
        { text: "Id", sortable: true, value: "id" },
        { text: "Load", sortable: true, value: "load" },
        { text: "Name", sortable: true, value: "name" },
        { text: "Cellar", sortable: true, value: "barrelCellar.name" },
        { text: "Box", sortable: true, value: "box.name" },
        { text: "Actions", sortable: false, value: "actions" },
      ],
    };
  },
  computed: {
    barrels1() {
      return this.$store.getters["barrels/getAllBarrels"];
    },
  },
  methods: {
    deleteBarrel(id) {
      this.idDelete = id;
      this.dialogDelete = true;
    },
    closeDelete() {
      this.dialogDelete = false;
    },
    confirmDelete() {
      this.$store.dispatch("barrels/deleteBarrel", this.idDelete);
      this.dialogDelete = false;
    },
    editBarrel(barrels) {
      this.editedBarrel = Object.assign({}, barrels);
      this.dialogEdit = true;
    },
    closeUpdate() {
      this.dialogEdit = false;
    },
    confirmUpdate() {
      this.$store.dispatch("barrels/updateBarrel", this.editedBarrel);
      this.dialogEdit = false;
    },
  },
  watch: {
    barrels1: function (barrels1) {
      this.barrels = barrels1;
    },
  },
  mounted() {
    this.$store.dispatch("barrels/getBarrels");
  },
};
</script>