<template>
<div>
  <v-subheader>User Land</v-subheader>
  <pothole-list :setHeight="'max-height: 79vh'"/>
</div>
  
</template>

<script>
import PotholeList from './PotholeList.vue'

export default {
  name: 'user-dash',
  components: {
    PotholeList
  },
  data: () => ({

  }),

  methods: {
  },
}
</script>