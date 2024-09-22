<template>
  <v-row>
    <v-col>
      <ListBoxs></ListBoxs>
    </v-col>
    <v-col>
      <AddBox></AddBox>
    </v-col>
  </v-row>
</template>


<script>
import ListBoxs from '@/components/ListBoxs.vue';
import AddBox from '@/components/AddBox.vue'

export default {
	components: {
		ListBoxs,
		AddBox
	}
}
</script>