<template>
  <v-row>
    <v-col>
      <ListCellars></ListCellars>
    </v-col>
    <v-col>
      <AddCellar></AddCellar>
    </v-col>
  </v-row>
</template>

<script>
import ListCellars from '@/components/ListCellars.vue';
import AddCellar from '@/components/AddCellar.vue'

export default {
	components: {
		ListCellars,
		AddCellar
	}
}
</script>