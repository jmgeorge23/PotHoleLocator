
<template>
  <v-sheet
    class="mx-auto"
    
  >
    <v-slide-group
      v-model="model"
      class="pa-10"
      >
      <v-slide-item
        v-for="n in 4"
        :key="n"
        v-slot="{ active, toggle }"
      >
        <v-card
          :color="active ? 'primary' : 'green'"
          class="ma-10"
          height="500"
          width="350"
          @click="toggle"
        >
        
          <v-row
            class="fill-height"
            align="center"
            justify="center"
          >
            <v-scale-transition>
              <v-icon
                v-if="active"

                
              ></v-icon>
            </v-scale-transition>
          </v-row>
        </v-card>
      </v-slide-item>
    </v-slide-group>

    <v-expand-transition>
      <v-sheet
        v-if="model != null"
        height="500"
        tile
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <h3 class="title">
            Selected {{ model.name }}
          </h3>
        </v-row>
      </v-sheet>
    </v-expand-transition>
  </v-sheet>
</template>

<script>
  export default {
    data: () => ({
      model: {
        
      }
    }),
  }
</script>

<style>

</style>