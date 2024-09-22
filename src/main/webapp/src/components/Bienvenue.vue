<template>
  <div class="landing-page">
      <v-row class="text-center">
        <v-col>
          <h1 class="display-3 font-weight-bold">Cave Connectée</h1>
          <p class="lead">Bienvenue sur Cave Connectée, votre outil de gestion de cave en ligne.Louez des caves ou gérez la vôtre en toute simplicité.
            <br>Surveillez la température, l'humidité et le nombre de tonneaux de votre cave en temps réel.
            <br>Rejoignez notre communauté de passionnés de vin et optimisez la qualité de vos vins grâce à Cave Connectée.</p>
        </v-col>
      </v-row>
  </div>
</template>


<script>
export default {
  name: 'Bien-venue',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.landing-page {
  background-image: linear-gradient(to top, #f7f7f7, hsl(280, 69%, 87%));
  height: 100vh;
}
</style>