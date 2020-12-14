<template>
  

      <v-card>
        <v-card-title class="justify-center">
          <span class="headline">Login to Your Account</span>
        </v-card-title>
        <v-card-text>
          <v-form v-model="validForm" id="login-form"
            >
            <v-container>
              <v-row>
                <v-col cols="12" >
                  <v-text-field
                    v-model="user.username"
                    label="Username*"
                    :rules="nameRules"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    v-model="user.password"
                    label="Password*"
                    type="password"
                    :rules="passwordRules"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn
            color="success lighten-1"
            
            :disabled="!validForm"
            rounded
            form="login-form"
            @click="login"
          >
            Login
          </v-btn>
          <v-btn
            color="primary lighten-1"
            text
            rounded
            @click="dialog = false"
          >
            Cancel
          </v-btn>
        </v-card-actions>
        <v-snackbar
          v-if="!snackbar"
          :timeout="timeout"
          color="accent"
          centered
          >
          {{ text }}

          <template v-slot:action="{ attrs }">
            <v-btn
              color="white"
              text
              v-bind="attrs"
              @click="snackbar = false"
            >
              Close
            </v-btn>
          </template>
        </v-snackbar>
      </v-card>

</template>

<script>
export default {
    data: () => ({
      dialog: false,
      validForm: false,
      snackbar: false,
      text: 'Invalid Username / Password.',
      timeout: 99999,
      user: {
        username: "",
        password: ""
      },
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 25 || 'Name must be less than 25 characters',
      ],
      passwordRules: [
        v => !!v || 'Password is required',
        v => v.length >= 8 || 'Password must be at least 8 characters',
      ],
    }),

    computed: {
      isLoggedIn() {
        return this.$store.getters.isLoggedIn;
      },
    },

    methods: {
      login() {
        const payload = this.user;
        this.$store.dispatch('login', payload)
          .then(() => {
            
             this.$router.push({name: 'user'})

            
          });
        if(!this.snackbar) {
          this.runSnackbar();
        }
        
      },
      closeDialog() {
        this.dialog = !this.dialog;
      },
      runSnackbar() {
        this.snackbar = true;
      },
     
    }
}
</script>