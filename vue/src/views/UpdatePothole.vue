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
      <v-toolbar-title>Update a Pothole</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          dark
          text
          form="update-form"
          type="submit"
        >
          Save
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-list
      three-line
      subheader
    >
     
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>Current potholes:</v-list-item-title>
          <v-list-item-subtitle>Pothole ID: {{currentPothole.potholeId}} </v-list-item-subtitle>
          <v-list-item-subtitle>Latitude: {{currentPothole.latitude}} </v-list-item-subtitle>
          <v-list-item-subtitle>Longitude: {{currentPothole.longitude}} </v-list-item-subtitle>
          <v-list-item-subtitle>Street: {{currentPothole.roadName}} </v-list-item-subtitle>
          <v-list-item-subtitle>Direction: {{currentPothole.direction}} </v-list-item-subtitle>
          <v-list-item-subtitle>Current Severity: {{currentPothole.severity}} </v-list-item-subtitle>
          <v-list-item-subtitle>Current Status: {{currentPothole.status}} </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
        <v-list-item-title>Please update this pothole </v-list-item-title>
        <v-form id="update-form"
          @submit.prevent="updatePothole"
        >
          <v-container>
            <v-row> 
              <v-col cols=12>
                <v-select
                    :items="severities"
                    label="Severity"
                    dense
                    v-model="currentPothole.severity"
                    :rules="rules"
                ></v-select>
              </v-col>
               <v-col cols=12>
                  <v-select
                    :items="status"
                    label="Status"
                    dense
                    v-model="currentPothole.status"
                    :rules="rules"
                ></v-select>
              </v-col>
              <v-col cols=12>
                <v-text-field 
                  label="Street Name"
                  v-model="currentPothole.roadName"
                  :rules="rules"
                >
                </v-text-field>
              </v-col>
               <v-col cols=12>
                  <v-text-field 
                    label="Direction"
                    v-model="currentPothole.direction"
                    :rules="rules"
                  >
                </v-text-field>
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
    <v-dialog
      v-model="dialog"
      max-width="350"
    >
      <v-card>
        <v-card-title class="headline">
         Update Successful
        </v-card-title>

        <v-card-text>
          Refresh map to see your changes
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
      status: ['Inspected', 'Completed'],
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
        if (updatedPothole.roadName.length === 0
           || updatedPothole.severity.length === 0
           || updatedPothole.status.length === 0) {
          // runSnackbar()
          console.log('uhh ohh... stinky... ppoooop')
        }
        this.$store.dispatch('updatePothole', updatedPothole)
            .then(response =>{
              console.log(response.status)
              if(response.status === 200){
                this.dialog = true
              }
            })
            .catch(err =>{
                console.log(err)
            });
      },
      endDialog() {
        this.dialog = false;
        this.$router.push({name: 'admin'})
      }

  },
}
</script>