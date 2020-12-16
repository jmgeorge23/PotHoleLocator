<template>
  <div>
    <v-subheader>Please sign in to report a pothole</v-subheader>
    <pothole-list :setHeight="'max-height: 74vh'"/>
  </div>
</template>

<script>
import PotholeList from './PotholeList.vue'
export default {
  components: { PotholeList },
  name: 'anon-dash',
  data: () => ({
  }),
  props: {
  },
  computed: {
  },
  methods: {
  },
}
</script>