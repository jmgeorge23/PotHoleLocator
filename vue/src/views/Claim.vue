<template>
  <v-app id="inspire">
       <!----------------------- Parallax ------------------->
 <v-container fluid ma-0 pa-0>
    <v-parallax class="parallax"
          dark
          src="@/assets/yellowteam.png"
  >
          <v-row
          align="center"
          justify="center"
          
    >
      <v-col
        class="text-center"
        cols="12"
        
      >
        <h1 class="display-3 mb-3">
          Did you hit a pothole?
        </h1>
  
      </v-col>
    </v-row>
  </v-parallax>
  </v-container>
 <!----------------------- Contact Us Form ------------------->
      <v-main class="grey lighten-3">
      <v-container>
        <v-row>
        <v-col
            cols="12"
            sm="12"
            
          >
            <v-sheet
              min-height="45vh"
              rounded="lg"

              
            >
            <v-card-title>
            Submit a Claim
          </v-card-title>
          <v-container>
             <v-form
            ref="form"
            v-model="valid"
           lazy-validation
          
    
  >
   <!----------------------- Name ------------------->
    <v-text-field
      v-model="name"
      :counter="10"
      :rules="nameRules"
      label="Name"
      required
    ></v-text-field>
 <!----------------------- Email ------------------->
    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>
 <!----------------------- Reason ------------------->
    <v-text-field
      v-model="Amount"
      :counter="10"
      label="Dollar Amount of Claim"
      required
    ></v-text-field>
    <!----------------------- Message ------------------->   
    <v-textarea
      v-model="message"
      :counter="180"
      label="Please enter your message"
      single-line
    ></v-textarea>
    
 <!----------------------- Submit ------------------->
    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate"
    >
      Send Claim
    </v-btn>

 <!----------------------- Reset ------------------->
    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset Form
    </v-btn>
  </v-form>  

<!----------------------- Snackbar------------------->

      <v-snackbar
      v-model="snackbar"
      :multi-line="multiLine"
    >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="green"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>

</v-container> 
  </v-sheet>
  </v-col>
  </v-row>
</v-container>
<Footer/>
</v-main>
</v-app>
</template>

<script>
  export default {

    data: () => ({
      multiLine: true,
      snackbar: false,
      text: `Claim Received.`,
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 20 characters',
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      amount: 0,

      message: '',

    }),

    methods: {
      validate () {
        this.$refs.form.validate()
        
        this.$router.go(-1);
        
      },
      reset () {
        this.$refs.form.reset()
      },

    }
  }
</script>


<style scoped>
</style>
