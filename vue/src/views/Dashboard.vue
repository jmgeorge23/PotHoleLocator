<template>
  <v-container fluid>
    <v-row dense>

      <v-col
        md="5"
        lg="4">
      <transition
        mode="out-in"
        enter-active-class="animate__animated animate__zoomIn"
        leave-active-class="animate__animated animate__zoomOut"
      >
        <user-dash 
          v-if="isLoggedIn"
          :potholes="potholes"
          @sendSelectionToMap="sendToMap($event)"
        />
        <anon-dash
          v-else
          :potholes="potholes"
        />
      </transition>
      </v-col>

      <v-col
        md="7"
        lg="8">
        <v-sheet
          min-height="80vh"
          rounded
          elevation="2"
        >
          <Map :potholes="potholes"
            :selection="selection"/>
          <!--  -->
        </v-sheet>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import Map from '../components/Map.vue'
import AnonDash from '../components/AnonDash.vue'
import UserDash from '../components/UserDash.vue'
export default {
  name: "dashboard",
  components: {
    Map,
    UserDash,
    AnonDash,
  },
  data: () => ({
    selectedItem: 0,
    model: 1,
    currentList: [],
    selection: {},
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
    }
  },

  methods: {
    fetchPotholes() {
      this.$store.dispatch('fetchPotholes');
    },
    sendToMap(selection) {
      this.selection = selection;
    }
  },
  created() {
    this.fetchPotholes();
  },
}
</script>

<style scoped>
.animate__animated.animate__zoomIn {
  --animate-duration: 250ms;
}
</style>
