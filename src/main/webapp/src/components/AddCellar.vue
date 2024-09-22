<template>
  <v-card>
    <v-card-title>
      <h2>Ajout d'un Cellar</h2>
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-text-field
          v-model="cellar.name"
          label="Nom"
          placeholder="Mon cellar"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="cellar.capacity"
          label="Capacité"
          placeholder="Mon cellar"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-btn @click="saveCellar">Ajouter</v-btn>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>

export default {
    name:"AddCellar",
    data(){
        return {
            cellar: {
                id:null,
                capacity:"",
                name:""
            },
            defaultCellar: {
                id:null,
                capacity:"",
                name:""
            },
        }
    },
    methods:{
        saveCellar(){
            var data ={
                id:null,
                capacity:this.cellar.capacity,
                name:this.cellar.name
            };
            console.log(data);
            this.$store.dispatch("cellars/createCellar",data);

            Object.assign(this.cellar,this.defaultCellar);
        }
    }
}
</script>
