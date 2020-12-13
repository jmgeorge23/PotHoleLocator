<template>
  <v-app id="inspire">
    <v-app-bar
      app
      color="info"
      flat
    >
      <v-container class="py-0 fill-height" fluid>
        <v-avatar
          class="mr-10"
          color="grey darken-1"
          tile
          size="48"
          style="border-radius: 3px;"
          @click="$router.push('/').catch(()=>{})"
        >
        <img src="@/assets/phl-logo-cropped.png"
          alt="favicon">
        </v-avatar>

        <v-btn
          v-for="link in headerLinks"
          :key="link"
          @click="$router.push({name: link}).catch(()=>{})"
          text
          color="white"
        >
          {{ link }}
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn
          v-if="isLoggedIn"
          color="info lighten-2"
          style="margin-right: 1rem;"
          rounded
          @click="logout"
          >Logout</v-btn>
        <login class="login"
          v-else/>
        <register
          v-if="!isLoggedIn"/>
      </v-container>
    </v-app-bar>
    <v-main class="grey lighten-3">
      <transition
        mode="out-in"
        enter-active-class="animate__animated animate__fadeIn"
        leave-active-class="animate__animated animate__pulse"
      >
        <router-view/>
      </transition>
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
          class="my-0"
          @click="$router.push({name: link}).catch(()=>{})"
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
import Login from './components/Login.vue'
import Register from './components/Register.vue'

export default {
  name: 'App',
  components: {
    Login,
    Register
  },
  data: () => ({
    dialog: false,
    headerLinks: [
      'dashboard',
      'Profile',
    ],
    footerLinks: [
      'test',
      'team',
      'blog',
      'contact',
    ],
  }),
  
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    }
  },

  methods: {
    logout() {
      this.$store.dispatch('logout');
      this.$router.go();
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
  /* margin-top: 60px; */
}
.login {
  margin-right: 1rem;
}
</style>