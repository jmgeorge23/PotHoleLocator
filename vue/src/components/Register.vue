<template>
  <v-dialog
      v-model="dialog"
      width="400"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="secondary"
          dark
          v-bind="attrs"
          v-on="on"
          style="margin-right: 1rem;"
          rounded
        >
          Sign Up
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="justify-center">
          <span class="headline">Register an Account</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" >
                <v-text-field
                  label="Username*"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Password*"
                  type="password"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Confirm Password*"
                  type="password"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions class="justify-center">
          <!-- <v-spacer></v-spacer> -->
          <v-btn
            color="success lighten-1"
            @click="dialog = false"
          >
            Create
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
export default {
    data() {
      return {
        dialog: false,
      }
    }
}
</script>