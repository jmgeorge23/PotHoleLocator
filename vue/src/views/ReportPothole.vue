<template>
  <v-card>
    <v-toolbar
      dark
      color="primary"
    >
      <v-btn
        icon
        dark
        @click="goBack"
      >
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>Report a Pothole</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          dark
          text
          @click="dialog = false"
        >
          Save
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-list
      three-line
      subheader
    >
      <v-subheader>User Controls</v-subheader>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>Click a spot on the map where you encountered a pothole</v-list-item-title>
          <v-list-item-subtitle>latitude:longitude:</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
        <v-list-item-title>Please provide additional details(optional)</v-list-item-title>
        <v-form id="login-form">
          <v-container>
            <v-row> 
              <v-col cols=12>
                <v-text-field label="roadname"
                  v-model="newPothole.roadName"
                >
                </v-text-field>
              </v-col>
               <v-col cols=12>
                  <v-text-field label="direction"
                    v-model="newPothole.direction"
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
      <v-subheader>General</v-subheader>

      <v-list-item>
        <v-list-item-action>
          <v-checkbox v-model="widgets"></v-checkbox>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Auto-add widgets</v-list-item-title>
          <v-list-item-subtitle>Automatically add home screen widgets</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
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
        username: this.username,
      }
    }
  },  
  computed:{
      username(){
        return this.$store.getters.username;
      }
  },
  methods:{
      goBack(){
        this.$store.dispatch('setReportModeOff');
        this.$router.go(-1);
      },

  },
  beforeRouteLeave: (to,from,next) => {
    console.log(to.name);
    console.log(from.name);
    const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
    if (answer) {
      next()
    } else {
      next(false)
    }
  },
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