<template>
  <v-container fluid>
    <v-row>

      <v-col
        md="5"
        lg="4">
        <v-sheet rounded
          elevation="2">
          <!-- ////////////// POTHOLE LIST ////////////////////  -->
          <v-list color="transparent"
            style="max-height: 45vh"
            class="overflow-y-auto"
            >
            <v-subheader>Potholes Uninspected: {{ this.notInspectedPotholeCount }}</v-subheader>
            <v-list-item-group
              v-model="model"
              active-class="border"
              color="blue-grey"
            >
              <v-list-item
                v-for="(pothole, i) in listItems"
                :key="i"
                link
              >
                <v-list-item-icon>
                  <v-icon v-text="pothole.icon"
                    :color="pothole.color"></v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title v-text="pothole.text"></v-list-item-title>
                  <v-list-item-subtitle>
                    Secondary line text Lorem ipsum dolor sit amet,
                  </v-list-item-subtitle>
                  <v-list-item-subtitle>
                    consectetur adipiscing elit.
                  </v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
<!-- 
            <v-divider class="my-2"></v-divider>
            <v-list-item
              link
              color="grey lighten-4"
            >
              <v-list-item-content>
                <v-list-item-title>
                  Refresh Map
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item> -->
          </v-list>
        </v-sheet>
            <!-- ///////////////// USER ACTIONS ///////////////// -->
      </v-col>

      <v-col
        md="7"
        lg="8">
        <v-sheet
          min-height="80vh"
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
  name: "home",
  components: {
    Map,
  },
  data: () => ({
    selectedItem: 0,
    model: 1,
  }),
  computed: {
    potholes: {
      get() {
        return this.$store.getters.allPotholes;
      }
    },
    notInspectedPotholeCount: {
      get() {
        return this.$store.getters.notInspectedPotholeCount;
      }
    },
    listItems() {
      let list = [];
      this.potholes.forEach(pothole => {
        switch(pothole.severity) {
          case 'Not Inspected':
            list = list.concat([{
              icon: 'mdi-new-box',
              text: `New Pothole on ${pothole.roadName}`,
              color: 'info'
            }]);
            break;
          case 'Low':
            list = list.concat([{
              icon: 'mdi-timeline-alert-outline',
              text: `Pothole on ${pothole.roadName}`,
              color: 'yellow darken-1'
            }]);
            break;
          case 'Medium':
            list = list.concat([{
              icon: 'mdi-timeline-alert-outline',
              text: `Pothole on ${pothole.roadName}`,
              color: 'warning'
            }]);
            break;
          case 'High':
            list = list.concat([{
              icon: 'mdi-alert-octagram',
              text: `Large Pothole on ${pothole.roadName}`,
              color: 'accent'
            }]);
            break;
        }
      });
      return list;
    }
  },
  methods: {

  },
  // created() {
  //   this.potholes = this.$store.state.potholes;
  // }

}
</script>

<style scoped>
/* #title {
  display: flex;
  justify-content: center;
}
img {
  height: 4vh;
} */
</style>
