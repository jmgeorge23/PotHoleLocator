<template>
<div>
  <employee-filters/>
  <pothole-list :setHeight="'max-height: 59vh'"/>
  <v-btn
    :to="{name: 'report'}"
    color="info lighten-2"
  >Report Pothole</v-btn>
</div>
  
</template>

<script>
import EmployeeFilters from './EmployeeFilters.vue'
import PotholeList from './PotholeList.vue'

export default {
  name: 'user-dash',
  components: {
    PotholeList,
    EmployeeFilters
  },
  data: () => ({

  }),

  methods: {
  },
}
</script>