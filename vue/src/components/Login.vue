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
          elevation="5"
        >
          Login
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="justify-center">
          <span class="headline">Login to Your Account</span>
        </v-card-title>
        <v-card-text>
          <v-form 
            v-model="valid"
            ref="form">
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
            @click="submit"
          >
            Login
          </v-btn>
          <v-btn
            color="primary lighten-1"
            text
            @click="dialog = false"
          >
            Cancel
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
</template>

<script>
import authService from "../services/AuthService";
export default {
    data() {
      return {
        dialog: false,
        valid: false,
        user: {
          username: "",
          password: ""
        },
        invalidCredentials: false,
        nameRules: [
          v => !!v || 'Name is required',
          v => v.length <= 25 || 'Name must be less than 25 characters',
        ],
        // email: '',
        // emailRules: [
        //   v => !!v || 'E-mail is required',
        //   v => /.+@.+/.test(v) || 'E-mail must be valid',
        // ],
      }
    },
    methods: {
      validate() {
        this.$refs.form.validate()
      },
      reset () {
        this.$refs.form.reset()
      },
      submit() {
        this.validate();
        this.login();
        this.dialog = false;
        window.location.reload();
      },
      login() {
        authService
          .login(this.user)
          .then(response => {
            if (response.status == 200) {
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              //this.$router.push("/");
            }
          })
          .catch(error => {
            const response = error.response;

            if (response.status === 401) {
              this.invalidCredentials = true;
            }
          });
    }
  }
}
</script>