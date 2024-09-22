<template>
	<v-container fluid>
		<v-row>
			<v-col>
				<ListAnalogicSensors></ListAnalogicSensors>
			</v-col>
			<v-col>
				<AddSensorsAnalogic></AddSensorsAnalogic>
			</v-col>
		</v-row>
		<v-row>
			<v-col>
				<ListNumericSensors></ListNumericSensors>
			</v-col>
			<v-col>
				<AddSensorsNumeric></AddSensorsNumeric>
			</v-col>
		</v-row>
	</v-container>
</template>

<script>
import ListNumericSensors from "@/components/ListNumericSensors.vue";
import ListAnalogicSensors from "@/components/ListAnalogicSensors.vue";
import AddSensorsAnalogic from "@/components/AddSensorsAnalogic.vue";
import AddSensorsNumeric from "@/components/AddSensorsNumeric.vue";

export default {
	components: {
		ListAnalogicSensors,
		ListNumericSensors,
		AddSensorsAnalogic,
		AddSensorsNumeric,
		},
	};
</script>