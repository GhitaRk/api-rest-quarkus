<template>
  <v-card>
    <v-card-title>
      <h2>Ajout d'une Box</h2>
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-text-field
          v-model="box.name"
          label="Nom"
          placeholder="Ma Box"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="box.serialNumber"
          label="Numéro de série"
          placeholder="Ma Box"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-btn @click="saveBox">Ajouter</v-btn>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>

export default {
    name:"AddBox",
    data(){
        return {
            box: {
                id:null,
                serialNumber:"",
                name:""
            },
            defaultboxs: {
                id:null,
                serialNumber:"",
                name:""
            },
        }
    },
    methods:{
        saveBox(){
            var data ={
                id:null,
                serialNumber:this.box.serialNumber,
                name:this.box.name
            };
            console.log(data);
            this.$store.dispatch("boxs/createBox",data);

            Object.assign(this.box,this.defaultboxs);
        }
    }
}
</script>
