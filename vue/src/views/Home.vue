<template>
  <div class="home">
    <!-- <div id="title">
      <img src="../assets/PHL_Love_Logo.png" alt="">
      <h2>P</h2><p>ot</p>
      <h2>H</h2><p>ole</p>
      <h2>L</h2><p>ocator</p>
    </div> -->
    <constrained/>
  </div>
</template>

<script>
import Constrained from '../components/Constrained.vue';

export default {
  name: "home",
  components: {
    Constrained
  }
};
</script>

<style scoped>
#title {
  display: flex;
  justify-content: center;
}
img {
  height: 4vh;
}
</style>