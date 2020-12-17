<template>
<div>
  
  <employee-pothole-list :setHeight="'max-height: 73vh'"/>
  <v-btn
    class="ma-2"
    outlined
    color="secondary darken-2"
  >
    Submit a Damage Claim
  </v-btn>
</div>
  
</template>

<script>
import EmployeePotholeList from './EmployeePotholeList.vue'

export default {
  name: 'employee-dash',
  components: {
    EmployeePotholeList,
  
  },
  data: () => ({

  }),
  computed: {
    potholes() {
      return this.$store.getters.allPotholes;
    },
  
  },


}
</script>