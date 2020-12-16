<template>
  <v-card>
    <v-toolbar
      dark
      color="warning darken-1"
    >
      <v-btn
        icon
        dark
        @click="goBack"
      >
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>Pothole details</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
      </v-toolbar-items>
    </v-toolbar>
    <v-list
      three-line
      subheader
      
    >

      <v-list-item>
        <v-list-item-content>
          <h2 class="font-weight-regular">Pothole ID: {{currentPothole.potholeId}} </h2>
          <h2 class="font-weight-regular">Latitude: {{currentPothole.latitude}} </h2>
          <h2 class="font-weight-regular">Longitude: {{currentPothole.longitude}} </h2>
          <h2 class="font-weight-regular">Street: {{currentPothole.roadName}} </h2>
          <h2 class="font-weight-regular">Direction: {{currentPothole.direction}} </h2>
          <h2 class="font-weight-regular">Current Severity: {{currentPothole.severity}} </h2>
          <h2 class="font-weight-regular">Current Status: {{currentPothole.status}} </h2>
        </v-list-item-content>
      </v-list-item>
      <v-list-item    v-if="currentUser === 'admin'">
        <v-list-item-content>
          <v-container>
            <v-row> 
            <v-btn color="info" :to="{name: 'update'}">Edit</v-btn>
            </v-row>
          </v-container>

        </v-list-item-content>
      </v-list-item>
    </v-list>
    <v-divider></v-divider>
    <v-list
      three-line
      subheader
    >
     
    </v-list>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex';
// import potholeService from '../services/PotholeService'
export default {
  data () {
    return {
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      newPothole: {
        direction: '',
        lane: '',
        latitude: 0,
        longitude: 0,
        roadName: '',
        severity: '',
        status: '',
        username: '',
      },
      severities: ['High', 'Medium', 'Low'],
      status: ['Inspected', 'Completed', 'Deleted'],
      rules: [ (v) => !!v || 'Cannot be blank' ],
    }
  },  
  computed:{
    ...mapGetters({
      currentUser: 'username',
      currentPothole: 'activePothole'
    }),
  },
  methods:{
      goBack(){
        // this.$store.dispatch('setReportModeOff');
         this.$router.go(-1);
      },
      updatePothole(){
        const updatedPothole = this.currentPothole;
        updatedPothole.username = this.currentUser;

         
        }


  },
}
</script>

<style scoped>
.h2 {
  font-size: xx-small;
}
</style>
