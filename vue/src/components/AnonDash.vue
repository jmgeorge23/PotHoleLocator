<template>
<v-sheet rounded
  elevation="2"
  height="100%">

  <!-- ////////////// POTHOLE LIST ////////////////////  -->
  <v-list color="transparent"
    style="max-height: 59vh"
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
  </v-list>

  <!-- ///////////////// USER ACTIONS ///////////////// -->
  <v-divider class="ma-0"></v-divider>
  <v-card flat width="100%" height="21vh"
    class="mx-auto">
    <v-navigation-drawer permanent width='100%'>
      <v-list-item>
        <v-list-item-title>
          Want to report a pothole?
        </v-list-item-title>
      </v-list-item>
      <v-divider></v-divider>
      <v-list-item>
        <v-btn>Login</v-btn>
      </v-list-item>
      <v-list-item>
        <v-btn>Sign Up</v-btn>
      </v-list-item>
    </v-navigation-drawer>
  </v-card>
</v-sheet>

</template>

<script>
export default {
  name: 'anon-dash',
  data: () => ({
    selectedItem: 0,
    model: 1,
    currentList: [],
  }),
  props: {
    potholes: {
      type: Array,
      required: true,
    }
  },
  computed: {
    // potholes: {
    //   get() {
    //     return this.$store.getters.allPotholes;
    //   }
    // },
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
    },
  },
  methods: {
    fetchPotholes() {
      this.$store.dispatch('fetchPotholes');
    },
    // TODO :: Allow user to filter map by watchlist / user-submitted
    setCurrentList(filterSetting) {
      this.currentList = filterSetting;
    },
  },
  // created() {
  //   this.fetchPotholes();
  // },
}
</script>