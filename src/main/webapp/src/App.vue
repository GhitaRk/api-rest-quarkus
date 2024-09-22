<template>
  <div>
    <v-app>
      <Sidebar />
      <v-main>
        <v-container fluid>
          <router-view />
        </v-container>
      </v-main>
    </v-app>
  </div>
</template>

<script>
import Sidebar from "./components/Side-bar.vue";

export default {
  components: {
    Sidebar
  }
};
</script>
