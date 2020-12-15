<template>
<div>
  
  <employee-pothole-list :setHeight="'max-height: 59vh'"/>
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
    filterReported() {
      let reported = [];
      this.potholes.forEach(pothole => {
        if(pothole.status === 'Reported'){
          reported.push(pothole);
        }
      });
      return reported;
    }
  },


}
</script>