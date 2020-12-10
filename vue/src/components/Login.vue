<template>
  <v-dialog
      v-model="dialog"
      width="400"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="success"
          dark
          v-bind="attrs"
          v-on="on"
          style="margin-right: 1rem;"
          rounded
          depressed
        >
          Login
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="justify-center">
          <span class="headline">Login to Your Account</span>
        </v-card-title>
        <v-card-text>
          <v-form v-model="validForm">
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
          <!-- <v-spacer></v-spacer> -->
          <v-btn
            color="success lighten-1"
            type="submit"
            :disabled="!validForm"
            rounded
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
      </v-card>
      <v-snackbar
      v-model="snackbar"
      :timeout="timeout"
      >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="blue"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-dialog>
</template>

<script>
import authService from "../services/AuthService";
export default {
    data() {
      return {
        dialog: false,
        validForm: false,
        validCredentials: false,
        snackbar: false,
        text: 'Invalid Username / Password.',
        timeout: 3000,
        user: {
          username: "",
          password: ""
        },
        nameRules: [
          v => !!v || 'Name is required',
          v => v.length <= 25 || 'Name must be less than 25 characters',
        ],
        passwordRules: [
          v => !!v || 'Password is required'
        ],
        // email: '',
        // emailRules: [
        //   v => !!v || 'E-mail is required',
        //   v => /.+@.+/.test(v) || 'E-mail must be valid',
        // ],
      }
    },
    methods: {
      login() {
        authService
          .login(this.user)
          .then(response => {
            if (response.status == 200) {
              this.invalidCredentials = false;
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              this.closeDialog();
              this.$router.go();
            }
          })
          .catch(error => {
            const response = error.response;

            if (response.status === 401) {
              this.invalidCredentials = true;
              this.runSnackbar();
            }
          });
      },
      closeDialog() {
        this.dialog = !this.dialog;
      },
      runSnackbar() {
        this.snackbar = true;
      }
    }
}
</script>