<template>
  <v-container>
    <v-row>
      <v-col
        md="5"
        lg="3">
        <v-sheet rounded="lg">
          <v-list color="transparent">
            <v-list-item
              v-for="n in 8"
              :key="n"
              link
            >
              <v-list-item-content>
                <v-list-item-title>
                  List Item {{ n }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>

            <v-divider class="my-2"></v-divider>

            <v-list-item
              link
              color="grey lighten-4"
            >
              <v-list-item-content>
                <v-list-item-title>
                  Refresh Map
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-sheet>
      </v-col>

      <v-col
        md="7"
        lg="9">
        <v-sheet
          min-height="70vh"
          rounded="lg"
        >
          <Map/>
          <!--  -->
        </v-sheet>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import Map from '../components/Map.vue'
export default {
  name: "home",
  components: {
    Map,
  },

}
</script>

<style scoped>
/* #title {
  display: flex;
  justify-content: center;
}
img {
  height: 4vh;
} */
</style>