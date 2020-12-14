<template>
<div>
  <list-filters/>
  <pothole-list :setHeight="'max-height: 59vh'"/>
  <v-btn
    :to="{name: 'report'}"
    color="info lighten-2"
  >Report Pothole</v-btn>
</div>
  
</template>

<script>
import ListFilters from './ListFilters.vue'
import PotholeList from './PotholeList.vue'

export default {
  name: 'user-dash',
  components: {
    PotholeList,
    ListFilters
  },
  data: () => ({

  }),

  methods: {
  },
}
</script>