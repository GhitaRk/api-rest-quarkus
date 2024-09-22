<template>
  <v-row>
    <v-col>
      <ListBarrels></ListBarrels>
    </v-col>
    <v-col>
      <AddBarrel></AddBarrel>
    </v-col>
  </v-row>
</template>

<script>
import ListBarrels from '@/components/ListBarrels.vue';
import AddBarrel from '@/components/AddBarrel.vue'

export default {
	components: {
		ListBarrels,
		AddBarrel
	}
}
</script>