<template>
    <v-container fluid ma-0 pa-0>
     <!----------------------- Parallax ------------------->
    <v-parallax class="parallax"
          dark
          src="@/assets/parallaximage.png"
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
          PotHole Locator
        </h1>
        <h3 class="subheading">
          Report a Pothole Today
        </h3>
      </v-col>
    </v-row>
  </v-parallax>
      <v-container>
          <v-row>
             <!----------------------- Card 1 ------------------->
          <v-col
            cols="12"
            sm="4"
          >
            <v-sheet class="ma-0">
            <v-img
              src="@/assets/pothole1.png" class="pothole-images">
              </v-img>
               <v-card class="ma-0">
                  <v-card-title>
                  Pothole Severity
                  </v-card-title>
                <v-card-text  class="text-md-left">
                We use a system that allows us to respond to high priority potholes by ranking them in severity from 1 to 3.
                <br>
                In order to be classified as a severity 3
                pothole the minimum width is 12" and minimum length of 24" with a depth of 8" or more.
                <br>
                The classification for a severity level 2 pothole is minimum width
                of 12", length 24" and depth of 4".
                <br>Anything smaller is classed as a level 1 severity, although we also take
                into account the speed limit on the road and the location in regards to a vehicle passing over it. <br><br><br>
              </v-card-text>
              </v-card>
            </v-sheet>
             </v-col>
              <!----------------------- Card 2 ------------------->
          <v-col
            cols="12"
            sm="4"
          >
            <v-sheet>
            <v-img
              src="@/assets/pothole2.png" class="pothole-images">
              </v-img>
               <v-card class="pothole-cards">
                  <v-card-title>
                  What are the causes of Potholes?
                  </v-card-title>
              <v-card-text  class="text-md-left">
              There are several different causes of potholes from water damage to being worn out due to use. <br><br>
              Poor drainage upon the road can lead to washing out of the base or cause ice to form which can expand creating voids
              and allowing water movement.
              <br> <br>
              Some potholes are caused via the asphalt becoming warn out over time or from direct impact damage. <br>
              </v-card-text>
              </v-card>
            </v-sheet>
          </v-col>
          <!----------------------- Card 3 ------------------->
          <v-col
            cols="12"
            sm="4"
          >
            <v-sheet>
            <v-img
              src="@/assets/potholerepair.png" class="pothole-images">
              </v-img>
               <v-card class="pothole-cards">
                  <v-card-title>
                  How are repairs conducted?
                  </v-card-title>
              <v-card-text  class="text-md-left">
              Repairs are scheduled based on the level of severity in which priority is given to high severity rated cases.
              The repairs are made by a number of different methods, these include:
              <br>
              - 25 pound bags of pre-made cold patch asphalt mix, manually placed and compressed into the pothole.
              <br>
              - Dump trucks containing a large quantity of cold mix is driven to the site and manually compressed into the pothole by our staff.
              <br>
              Once the pothole has been repaired a staff member will mark the repair as being completed.
              </v-card-text>
              </v-card>
            </v-sheet>
          </v-col>
          <!----------------------- FAQ SECTION ------------------->
            <v-card-title>
            Frequently Asked Questions
            </v-card-title>
              <v-expansion-panels focusable>
              <v-expansion-panel>
                <v-expansion-panel-header>Why do potholes suck?</v-expansion-panel-header>
                <v-expansion-panel-content>Because they do</v-expansion-panel-content>
              </v-expansion-panel>
                <v-expansion-panel>
                <v-expansion-panel-header>Can you fix this pothole?</v-expansion-panel-header>
                <v-expansion-panel-content>No</v-expansion-panel-content>
              </v-expansion-panel>
                <v-expansion-panel>
                <v-expansion-panel-header>Am I a pothole?</v-expansion-panel-header>
                <v-expansion-panel-content>Yes you are</v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
         </v-row>
         </v-container>
      </v-container>
</template>



<script> 

</script>

<style scoped>

.pothole-cards {
  padding: 1rem;
  

}

.pothole-images {
   border-radius: 3px 3px;
   height: 175px;
   
}
</style>