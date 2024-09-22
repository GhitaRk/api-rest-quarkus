<template>
    <v-card>
        <v-card-title>
            <h2> Ajout d'un sensor Analogique </h2>
        </v-card-title>
        <v-card-text>
            <v-row>
                <v-text-field v-model="sensors.serialNumber" label= "serialNumber" placeholder="Mon sensor" required>
                </v-text-field> 
            </v-row>
            <v-row>
                <v-text-field v-model="sensors.name" label= "name" placeholder="Mon sensor" required>
                </v-text-field> 
            </v-row>
            <v-row>
                <v-btn @click="saveSensors">Ajouter</v-btn>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>

export default {
    name:"AddSensorsAnalogic",
    data(){
        return {
            sensors: {
                id:null,
                serialNumber:"",
                name:""
            },
            defaultsensors: {
                id:null,
                serialNumber:"",
                name:""
            },
        }
    },
    methods:{
        saveSensors(){
            var data ={
                id:null,
                serialNumber:this.sensors.serialNumber,
                name:this.sensors.name
            };
            console.log(data);
            this.$store.dispatch("sensors/createAnalogicSensors",data);

            Object.assign(this.sensors,this.defaultsensors);
        }
    }
}
</script>
