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
          <v-list-item>
        <v-list-item-content>
          <v-list-item-title>Please select a pothole pin from the map for your claim</v-list-item-title>
          <v-list-item-subtitle>Pothole ID: {{currentPothole.potholeId}} </v-list-item-subtitle>
          <v-list-item-subtitle>Street: {{currentPothole.roadName}} </v-list-item-subtitle>
          <v-list-item-subtitle>Direction: {{currentPothole.direction}}</v-list-item-subtitle>

        </v-list-item-content>
      </v-list-item>

          <v-container>
             <v-form
            ref="form"
            v-model="valid"
           lazy-validation
          
    
  >
   <!----------------------- Name ------------------->
    <v-text-field
      v-model="name"
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
 <!----------------------- Amount ------------------->
    <v-text-field
      v-model="amount"
      :counter="7"
      :rules="amountRules"
      label="Dollar Amount of Claim"
      required
    ></v-text-field>
    <!----------------------- Message ------------------->   
    <v-textarea
      v-model="newClaim.description"
      :counter="180"
      :rules="messageRules"
      label="Please enter your message"
      single-line
    ></v-textarea>
    
 <!----------------------- Submit ------------------->
    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="sendClaim"
    >
      Send Claim
    </v-btn>

 <!----------------------- Reset ------------------->
    <!-- <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset Form
    </v-btn> -->

<!----------------------- Reset ------------------->
    <v-btn
      color="error"
      class="mr-4"
      @click="goBack"
    >
      Cancel
    </v-btn>
  </v-form>  

<!----------------------- Snackbar------------------->

      <v-snackbar
      v-model="snackbar"
      :multi-line="multiLine"
      centered
      left
     
    >
      {{ text }}


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
import { mapGetters } from 'vuex';

  export default {

    data: () => ({
      multiLine: true,
      newClaim: {
        amount: 0,
        description: '',
        username: '',
        potholeId: ''
        
      },
      snackbar: false,
      text: `Claim Received.`,
      valid: true,
      name: '',
      nameRules: [v => !!v || 'Name is required'],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      amount: 0.00,
      amountRules: [
        v => !!v || 'Amount is required',
        v => (v && v.length <= 7) || 'Amount must be less than $10,000',
      ],
      messageRules: [
        v => ( v.length <= 180) || 'Message must be less than 180 characters'] ,

    }),
     computed:{
        ...mapGetters({
          currentUser: 'username',
          currentPothole: 'activePothole'
    }),
  },

    methods: {
      sendClaim(){
        this.newClaim.username=this.currentUser
        this.newClaim.potholeId=this.currentPothole.potholeId
        this.newClaim.amount=parseFloat(this.amount);
        this.$store.dispatch('sendClaim', this.newClaim)
          .then(response=>{
            if(response.status===201){
                console.log('its working!')
            }
          })
          .catch(err=>{
            console.log(err)
          })
      },
      validate () {
        this.$refs.form.validate()
        this.sendClaim()
        this.snackbar = true;
        window.setTimeout(this.goBack, 1500);
        
      },
      reset () {
        this.$refs.form.reset()
      },
      goBack(){
        this.reset()
        this.$router.go(-1);

      }

    }
  }
</script>


<style scoped>
</style>
