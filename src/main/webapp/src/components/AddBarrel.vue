<template>
  <v-card>
    <v-card-title>
      <h2>Ajout d'un Barrel</h2>
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-text-field
          v-model="barrel.name"
          label="Nom"
          placeholder="Mon Barrel"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          v-model="barrel.load"
          label="Capacité"
          placeholder="Mon Barrel"
          required
        ></v-text-field>
      </v-row>
      <v-row>
        <v-btn @click="saveBarrel">Ajouter</v-btn>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>

export default {
    name:"AddBarrel",
    data(){
        return {
            barrel: {
                id:null,
                load:"",
                name:""
            },
            defaultbarrel: {
                id:null,
                load:"",
                name:""
            },
        }
    },
    methods:{
        saveBarrel(){
            var data ={
                id:null,
                load:this.barrel.load,
                name:this.barrel.name
            };
            console.log(data);
            this.$store.dispatch("barrels/createBarrel",data);

            Object.assign(this.barrel,this.defaultbarrel);
        }
    }
}
</script>
