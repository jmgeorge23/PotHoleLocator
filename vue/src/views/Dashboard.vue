<template>
  <v-container fluid>
    <v-row dense>

      <v-col
        md="5"
        lg="4"
      >
        <!-- LIST VIEW -->
        <v-sheet rounded
          elevation="2"
          height="100%"
        >

          <v-card flat>
            <v-card-title class="info lighten-2 white--text py-2">
              <span class="headline">{{ isLoggedIn? `Welcome, ${username}`: 'Report Map'}}</span>
              <v-spacer></v-spacer>
              <v-menu
                bottom
                left
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    dark
                    icon
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn>
                </template>

                <v-list>
                  <!-- <v-list-item
                    v-for="(item, i) in items"
                    :key="i"
                  >
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item> -->
              </v-list>
              </v-menu>
            </v-card-title>
          </v-card>
          <!-- ////////////// POTHOLE LIST ////////////////////  -->
          <v-divider></v-divider>


          <!-- ///////////////// USER ACTIONS ///////////////// -->
          <v-divider class="ma-0"></v-divider>
          <!-- <v-btn :to="{name: 'report'}">
            User Actions Test
          </v-btn> -->
          <transition
            mode="out-in"
            enter-active-class="animate__animated animate__zoomIn"
            leave-active-class="animate__animated animate__zoomOut"
          >
            <router-view></router-view>
          </transition>
        </v-sheet>
      </v-col>


      <!-- MAP -->
      <v-col
        md="7"
        lg="8">
        <v-sheet
          min-height="90vh"
          rounded
          elevation="2"
        >
          <Map/>
          <!--  -->
        </v-sheet>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import Map from '../components/Map.vue'
export default {
  name: "dashboard",
  components: {
    Map,
  },
  data: () => ({
    selectedItem: 0,
    model: 1,
    currentList: [],
  }),

  computed: {
    potholes: {
      get() {
        return this.$store.getters.allPotholes;
      }
    },
    isLoggedIn: {
      get() {
        return this.$store.getters.isLoggedIn;
      }
    },
    username: {
      get() {
        return this.$store.getters.username;
      }
    }
  },

  methods: {
    sendToMap(selection) {
      this.selection = selection;
    }
  },
}
</script>

<style scoped>
.animate__animated.animate__zoomIn {
  --animate-duration: 250ms;
}
</style>
