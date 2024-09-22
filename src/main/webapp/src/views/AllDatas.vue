<template>
	<v-container style="height: 90vh;" fluid>
		<v-row style="height: 50%;">
			<v-col cols="12" style="height: 100%;">
				<ListAnalogicDatas></ListAnalogicDatas>
			</v-col>

		</v-row>
		<v-row style="height: 50%;">
			<v-col cols="12" style="height: 100%;">
				<ListNumericDatas></ListNumericDatas>
			</v-col>
		</v-row>
	</v-container>
</template>

<script>
import ListAnalogicDatas from '@/components/ListAnalogicDatas.vue';
import ListNumericDatas from '@/components/ListNumericDatas.vue';


export default {
	components: {
		ListAnalogicDatas,
		ListNumericDatas
	}
}
</script>
