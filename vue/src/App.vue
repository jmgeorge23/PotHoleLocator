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
      color="info"
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

        <v-responsive max-width="500px">
          <v-btn
            v-if="isLoggedIn"
            color="accent"
            elevation="5"
            style="margin-right: 1rem;"
            @click="logout"
            >Logout</v-btn>
          <login class="login"
            v-else/>
          <register/>
        </v-responsive>
      </v-container>
    </v-app-bar>
    <v-main class="grey lighten-3">
      <router-view/>
    </v-main>
    <v-footer
      color="info darken-1"
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
          class="info darken-2 text-center white--text"
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
import Login from './components/Login.vue'
import Register from './components/Register.vue'
export default {
  name: 'App',
  components: {
    Login,
    Register,
  },
  data: () => ({
      dialog: false,
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
  computed: {
    isLoggedIn() {
      return (localStorage
        .getItem('user') === null ? false : true);
    }
  },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      window.location.reload();
    }
  }
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
.login {
  margin-right: 1rem;
}
</style>