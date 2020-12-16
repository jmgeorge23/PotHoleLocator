<template>
  <v-card>
    <v-toolbar
      dark
      color="secondary darken-2"
    >
      <v-btn
        icon
        dark
        @click="goBack"
      >
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>Update a Pothole</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          dark
          text
          @click="sendReport"
        >
          Save
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-list
      three-line
      subheader
    >
      <v-subheader>Admin Controls</v-subheader>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>Current potholes:</v-list-item-title>
          <v-list-item-subtitle
          v-model="newPothole.latitude"
          >Latitude: {{currentPothole.latitude}} </v-list-item-subtitle>
          <v-list-item-subtitle>Longitude: {{currentPothole.longitude}} </v-list-item-subtitle>
          <v-list-item-subtitle>Street: {{currentPothole.roadName}} </v-list-item-subtitle>
          <v-list-item-subtitle>Direction: {{currentPothole.direction}} </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
        <v-list-item-title>Please update this pothole </v-list-item-title>
        <v-form id="login-form">
          <v-container>
            <v-row> 
              <v-col cols=12>
                  
                <v-select
                    :items="severities"
                    label="Severity"
                    dense
                    v-model="newPothole.severity"
                ></v-select>
              </v-col>
               <v-col cols=12>
                  <v-select
                    :items="status"
                    label="Status"
                    dense
                    v-model="newPothole.status"
                ></v-select>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
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
        severity: 'Not Inspected',
        status: 'Reported',
        username: '',
      },
      severities: ['High', 'Medium', 'Low'],
      status: ['Inspected', 'Completed', 'Deleted']
    }
  },  
  computed:{
      currentUser(){
        return this.$store.getters.username;
      },
      currentPothole(){
        return this.$store.getters.activePothole;
      }
  },
  methods:{
      goBack(){
        // this.$store.dispatch('setReportModeOff');
         this.$router.go(-1);
      },
      sendReport(){
        // this.newPothole.latitude = this.mapClick.latitude;
        // this.newPothole.longitude = this.mapClick.longitude;
        // this.newPothole.username = this.$store.getters.username;
        // console.log(this.$store.getters.username);
        // // this.$store.dispatch('sendReport',this.newPothole)
        // potholeService.sendReport(this.newPothole)
        //     .then(response =>{
        //       console.log(response.status)
        //       if(response.status === 201){
        //         console.log(response)
        //       }
        //     })
        //     .catch(error =>{
        //       if(error.status ===418){
        //         console.log(error)
        //       }
        //     });
        // console.log('Here!');
      },

  },
  // beforeRouteLeave: (to,from,next) => {
  //   console.log(to.name);
  //   console.log(from.name);
  //   // const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
  //   // if (answer) {
  //   //   next()
  //   // } else {
  //   //   next(false)
  //   // }
  // },
  // beforeDestroy:() => {
  //   const answer = window.confirm('Do you really want to leave? you have unsaved changes!');
  //   if (answer) {
  //     this.setReportModeOff();
  //     this.goBack();
  //   } else {
  //     this.goBack();
  //   }
  // },
}
</script>