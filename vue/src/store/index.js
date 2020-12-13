import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import potholeService from '../services/PotholeService'
import authService from '../services/AuthService'
//import authService from "../services/AuthService";
Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    loadingStatus: 'notLoading',
    potholes: [],
    token: currentToken || '',
    user: currentUser || {}
  },

  mutations: {
    SET_LOADING_STATUS(state, status) {
      state.loadingStatus = status;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_POTHOLES(state, data) {
      state.potholes = data;
    }
  },
  
  actions:  {
    fetchPotholes(context) {
      context.commit('SET_LOADING_STATUS', 'loading');
      potholeService.getPotholes()
        .then(response => {
          context.commit('SET_LOADING_STATUS', 'notLoading')
          context.commit('SET_POTHOLES', response.data)
        })
        .catch(error => alert(error));
    },
    login({ commit }, user) {
      authService.login(user)
        .then( response => {
          const token = response.data.token;
          const user = response.data.user;
          commit('SET_AUTH_TOKEN', token);
          commit('SET_USER', user);
        })
        .catch( err => {
          localStorage.removeItem('token');
          console.log(err);
        })
    },
    logout(context) {
      context.commit('LOGOUT');
    }
  },
  
  getters: {
    // Potholes and filters
    allPotholes: (state) => {
      return state.potholes;
    },
    notInspectedPotholes: (state) => {
      return state.potholes
        .filter( pothole => pothole.severity === 'Not Inspected');
    },
    notInspectedPotholeCount: (state, getters) => {
      return getters.notInspectedPotholes.length;
    },
    // Login data
    isLoggedIn: state => !!state.token,
    authStatus: state => state.status,
  },
})
