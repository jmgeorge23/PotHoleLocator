<template>
<div>
    
          <v-card-text class="px-1 py-0">
            <v-chip-group
              mandatory
              active-class="info--text"
              center-active
            >
              <v-chip
                v-for="(filter, i) in filters"
                :key="i"
                @click="setCurrentList(filter)"
              >
                {{ filter }}
              </v-chip>
             
            </v-chip-group>
          </v-card-text>

  <v-list color="transparent"
    :style="setHeight"
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
        v-for="pothole in getPotholesByStatus"
        :key="pothole.id"
        link
        @click="setMenuSelection"
      >
        <v-list-item-icon>
          <v-icon v-text="pothole.icon"
            :color="pothole.color"></v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title v-text="pothole.text"></v-list-item-title>
          <v-list-item-subtitle>
            Pothole on {{pothole.roadName}} Severity: {{pothole.severity}} 
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-btn
        :to="{name: 'update'}">Update</v-btn>
      </v-list-item>
      
    </v-list-item-group>
  </v-list>
</div>
</template>

<script>


export default {
  components: {
    
  },
  data() {
    return {
      model: 0,
      clickedPotholeId: 0,
      currentList: "All",
       filters: [
        'All',
        'Reported',
        'Inspected',
        'Completed',
      ],
    }
  },

  props: {
    setHeight: {
      type: String,
      default: '',
    } 
  },
  
  computed: {
    potholes() {
      return this.$store.getters.allPotholes;
    },
    // Grabs currently selected item
    clickedItem() {
      return this.potholes[this.model];
    },
    notInspectedPotholeCount: {
      get() {
        return this.$store.getters.notInspectedPotholeCount;
      }
    },
    currentUserId() {
      return this.$store.getters.userId;
    },
    currentPotholeId() {
        return this.$store.getters.activePothole.potholeId;
    },
    getPotholesByStatus(){
      let list = []
      if(this.currentList === 'Reported'){
        list = (this.$store.getters.reportedPotholes);
      }
      if(this.currentList === 'All'){
        list = (this.$store.getters.allPotholes)
      }
      if(this.currentList === 'Inspected'){
        list = (this.$store.getters.inspectedPotholes);
      }

      return list;
    },
    // Gets list and assigns corresponding colored icons
    listItems() {
      let list = [];
      this.getPotholesByStatus.forEach(pothole => {
            if(pothole.severity === 'Not Inspected'){
                list = list.concat([{
                id: pothole.potholeId,
                icon: 'mdi-new-box',
                text: `New Pothole on ${pothole.roadName}`,
                color: 'info',
                severity: `${pothole.severity}`
                }]);
            }
            if(pothole.severity === 'Low'){
                list = list.concat([{
                id: pothole.potholeId,
                icon: 'mdi-timeline-alert-outline',
                text: `Small Pothole on ${pothole.roadName}`,
                color: 'yellow darken-1',
                severity: `${pothole.severity}`
                }])
            }
            if(pothole.severity === 'Medium'){
                list = list.concat([{
                id: pothole.potholeId,
                icon: 'mdi-timeline-alert-outline',
                text: `Pothole on ${pothole.roadName}`,
                color: 'warning',
                severity: `${pothole.severity}`
                }]);
            }
            if(pothole.severity ==='High'){
                list = list.concat([{
                id: pothole.potholeId,
                icon: 'mdi-alert-octagram',
                text: `Large Pothole on ${pothole.roadName}`,
                color: 'accent',
                severity: `${pothole.severity}`
                }]);
            }
        });
    //   list.sort((a,b) => {
    //       if(a.severity == 'High'){
    //           return -1;
    //         }
    //       if(a.severity == 'Medium'){
    //           return -1;
    //         }
    //       if(a.severity == 'Low'){
    //           return 1;
    //         }
    //       return 0;
    //     })
      return list;
    },
  },

  methods: {
    setMenuSelection() {
      this.$store.dispatch('setMenuSelection', this.clickedItem);
    },
    // TODO :: Allow user to filter map by watchlist / user-submitted
    setCurrentList(filterSetting) {
      this.currentList = filterSetting;
    },
  },
}
</script>