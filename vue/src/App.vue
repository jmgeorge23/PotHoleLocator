<template>
  <!-- <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div> -->
    <!-- <router-view /> -->
    <!-- <constrained/> -->
  <!-- </div> -->
  <v-app id="inspire">
    <v-app-bar
      app
      color="primary"
      flat
    >
      <v-container class="py-0 fill-height">
        <v-avatar
          class="mr-10"
          color="grey darken-1"
          size="32"
        >
        <img src="@/assets/favicon-32x32.png"
          alt="favicon">
        </v-avatar>

        <v-btn
          v-for="link in headerLinks"
          :key="link"
          text
          color="white"
        >
          {{ link }}
        </v-btn>

        <v-spacer></v-spacer>

        <v-responsive max-width="260">
          <v-btn
            color="secondary"
            elevation="5"
            style="margin-right: 1rem;"
            >Login</v-btn>
          <v-btn
            color="success"
            elevation="5"
            >Sign Up</v-btn>
          <!-- <v-text-field
            dense
            flat
            hide-details
            rounded
            solo-inverted
          ></v-text-field> -->
        </v-responsive>
      </v-container>
    </v-app-bar>
    <v-main class="grey lighten-3">
      <router-view/>
    </v-main>
    <v-footer
      color="primary"
      padless
    >
      <v-row
        justify="center"
        no-gutters
      >
        <v-btn
          v-for="link in footerLinks"
          :key="link"
          color="white"
          text
          rounded
          class="my-2"
        >
          {{ link }}
        </v-btn>
        <v-col
          class="secondary text-center white--text"
          cols="12"
        >
          {{ new Date().getFullYear() }} — <strong>Kony</strong>
        </v-col>
      </v-row>
    </v-footer>
  </v-app>
</template>

<script>
//import Constrained from './components/Constrained.vue'
export default {
  name: 'App',
  data: () => ({
      headerLinks: [
        'Dashboard',
        'Notifications',
        'Profile',
      ],
      footerLinks: [
        'About Us',
        'Team',
        'Blog',
        'Contact Us',
      ],
  }),
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>