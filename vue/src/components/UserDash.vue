<template>
<v-sheet rounded
  elevation="2"
  height="100%">

  <!-- ////////////// POTHOLE LIST ////////////////////  -->
  <v-card flat>
    <v-card-text>
      <span>Filters</span>
      <v-chip-group
        mandatory
        active-class="info--text"
        center-active
      >
        <v-chip
          v-for="(filter, i) in filters"
          :key="i"
        >
          {{ filter }}
        </v-chip>
      </v-chip-group>
    </v-card-text>
  </v-card>

  <v-divider></v-divider>

  <v-list color="transparent"
    style="max-height: 20vh"
    class="overflow-y-auto"
    subheader
    two-line
    >
    <!-- <v-subheader>Potholes Uninspected: {{ this.notInspectedPotholeCount }}</v-subheader> -->
    <v-list-item-group
      v-model="model"
      active-class="border"
      color="blue-grey"
      mandatory
    >
      <v-list-item
        v-for="(pothole, i) in listItems"
        :key="i"
        link
        @click="sendListSelection"
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
        </v-list-item-content>
      </v-list-item>
    </v-list-item-group>
  </v-list>

  <!-- ///////////////// USER ACTIONS ///////////////// -->
  <v-divider class="ma-0"></v-divider>
  <v-card flat width="100%" height="49vh"
    class="mx-auto">
    <v-navigation-drawer permanent width='100%'>
      <v-list-item>
        <v-list-item-title>
          YOU ARE LOGGED IN!!!
        </v-list-item-title>
      </v-list-item>
      <v-divider></v-divider>
      <v-list-item>
        YOU. YES YOU!!
      </v-list-item>
      <v-list-item>
        YOU ARE LOGGED IN AS A USER
      </v-list-item>
    </v-navigation-drawer>
  </v-card>
</v-sheet>

</template>

<script>
export default {
  name: 'user-dash',
  data: () => ({
    selectedItem: 0,
    model: 1,
    currentList: [],
    filters: [
      'All',
      'New',
      'My Watchlist',
      'My Reported',
    ]
  }),

  props: {
    potholes: {
      type: Array,
      required: true,
    }
  },
  
  computed: {
    // Grabs currently selected item
    clickedItem() {
      return this.potholes[this.model];
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
    sendListSelection() {
      this.$emit('sendSelectionToMap', this.clickedItem);
    }
  },
}
</script>