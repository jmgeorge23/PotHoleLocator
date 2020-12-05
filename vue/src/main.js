import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import VueGeolocation from 'vue-browser-geolocation'


Vue.config.productionTip = false
Vue.use(VueGeolocation)


axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

import * as VueGoogleMaps from 'vue2-google-maps'
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCt2Xrz7cjy3dyahlfegbOmyZ4vrTJyOL4'
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
