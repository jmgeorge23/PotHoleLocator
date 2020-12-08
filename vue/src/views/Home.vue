<template>
  <div class="home">
    <div id="title">
      <img src="../assets/PHL_Love_Logo.png" alt="">
      <h2>P</h2><p>ot</p>
      <h2>H</h2><p>ole</p>
      <h2>L</h2><p>ocator</p>
    </div>
    <router-view/>
      <Map/>
  </div>
</template>

<script>
import Map from '../components/Map';

export default {
  name: "home",
  components: {
    Map
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