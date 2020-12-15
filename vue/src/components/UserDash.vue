<template>
<div>
  <list-filters/>
  <pothole-list :setHeight="'max-height: 59vh'"/>
  <v-btn :to="{name: 'report'}"
    color="info lighten-2"
  >Report Pothole</v-btn>
  <v-btn :to="{name: 'claim'}"
    color="info lighten-2"
  >File a Claim</v-btn>
  <v-btn color="info lighten-2"
    @click="$router.go().catch(()=>{})"
  >Refresh Map</v-btn>
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
    makeReport(){
        this.$store.dispatch('setReportModeOn');
    }
  },
}
</script>