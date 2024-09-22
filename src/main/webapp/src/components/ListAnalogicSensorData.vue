<template>
  <v-card>
    <v-card-title>
      <div id="title-info">
        Data of the sensor {{ SensorNumber }}
      </div>
    </v-card-title>
    <v-card-text>
      <v-data-table :headers="headers" :items="datas"> </v-data-table>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "ListSensorData",
  props: {
  SensorNumber: {
      type: Number,
      default: 21,
    },
  },
  data() {
    return {
      datas: [],
      headers: [
        { text: "Id", sortable: true, value: "id" },
        { text: "MeasureDate", sortable: true, value: "measureDate" },
        { text: "AnalogicValue", sortable: true, value: "analogicValue" },
      ],
    };
  },
  computed: {
    sensordata1() {
      return this.$store.getters['datas/getAllAnalogicSensorDatas'];
    },
  },
  watch: {
    sensordata1: function (sensordata1) {
      this.datas = sensordata1;
    },
  },
  mounted() {
    this.$store.dispatch("datas/getAnalogicDatasSensors", this.SensorNumber);
  },
};
</script>