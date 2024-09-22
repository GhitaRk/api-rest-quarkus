<template>
  <div>
    <v-navigation-drawer v-model="drawer" app>
      <template>
        <div>
          <v-treeview :hoverable=true :shaped=true :open-on-click=true :color="red" :items="items">
            <template slot="label" slot-scope="props">
              <v-icon size="xlarge">
                {{ props.item.icon }}
              </v-icon>
              <router-link :to="props.item.to" v-if="props.item.to">{{ props.item.name }}</router-link>
              <span v-else>{{ props.item.name }}</span>
            </template>
          </v-treeview>
        </div>
      </template>
    </v-navigation-drawer>
    <v-app-bar app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Cave Connectée</v-toolbar-title>
    </v-app-bar>

  </div>
</template>

<script>
export default {
  data() {
    return {
      drawer: false,
      items: [
        {
          id: 1,
          name: "   Infrastructure",
          icon: "mdi-home-city-outline",
          children: [
            {
              id: 2,
              name: " Cellars",
              icon: "mdi-home-variant-outline",
              to: "/cellars"
            },
            {
              id: 3,
              name: " Barrels",
              icon: "mdi-cylinder",
              to: "/barrels"
            },
            {
              id: 4,
              name: " Boxes",
              icon: "mdi-leak",
              to: "/boxs"
            },
            {
              id: 5,
              name: " Sensors",
              icon: "mdi-water-thermometer-outline",
              to: "/sensors"
            }
          ]
        },
        {
          id: 6,
          name: "Measure",
          icon: "mdi-chart-line",

          children: [
            {
              id: 7,
              name: " Datas",
              icon: "mdi-database-outline",
              to: "/datas"
            }
              
            
          ]
        }
      ]
    };
  }
};
</script>

<style>
.v-navigation-drawer:hover{
  background-color: #e7e4fb;
}
</style>