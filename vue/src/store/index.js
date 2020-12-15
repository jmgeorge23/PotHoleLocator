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
    isLoading: false,
    potholes: [],
    token: currentToken || '',
    user: currentUser || {},
    activePothole: {},
    hasMenuSelection: false,
    reportMode: false,
  },

  mutations: {
    START_LOADING(state) {
      state.isLoading = true;
    },
    STOP_LOADING(state) {
      state.isLoading = false;
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
    },
    SET_ACTIVE_POTHOLE(state, pothole) {
      state.activePothole = pothole;
    },
    MENU_SELECTION_MADE(state) {
      state.hasMenuSelection = true;
    },
    MENU_SELECTION_REMOVED(state) {
      state.hasMenuSelection = false;
    },
    REPORT_MODE_ON(state) {
      state.reportMode = true;
    },
    REPORT_MODE_OFF(state) {
      state.reportMode = false;
    },
  },
  
  actions:  {
    // ////////////////// POTHOLE MANAGEMENT /////////////////
    fetchPotholes({commit}) {
      commit('START_LOADING');
      potholeService.getPotholes()
        .then(response => {
          commit('SET_POTHOLES', response.data);
        })
        .catch(error => alert(error));
        commit('STOP_LOADING');
    },
    // ///////////////// MAP MANAGEMENT /////////////////////
    setActivePothole({commit}, pothole) {
      commit('SET_ACTIVE_POTHOLE', pothole);
    },
    setMenuSelection({commit}, selection) {
      commit('SET_ACTIVE_POTHOLE', selection);
      commit('MENU_SELECTION_MADE');
    },
    mapMarkerSelection({commit}, selection) {
      commit('SET_ACTIVE_POTHOLE', selection);
      commit('MENU_SELECTION_REMOVED');
    },
    unsetMenuSelection({commit}) {
      commit('MENU_SELECTION_REMOVED');
    },
    // ////////////////////// REPORTING MANAGEMENT /////////////////
    setReportModeOn({commit}) {
      commit('REPORT_MODE_ON');
    },
    setReportModeOff({commit}) {
      commit('REPORT_MODE_OFF');
    },
    // /////////////////// ACCOUNT MANAGEMENT /////////////////////
    login({ commit }, user) {
      commit('START_LOADING');
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
        });
        commit('STOP_LOADING');
    },
    logout({commit}) {
      commit('LOGOUT');
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
    reportedPotholes: (state) => {
      return state.potholes
      .filter( pothole => pothole.status === 'Reported');
    },
    inspectedPotholes: (state) => {
      return state.potholes
      .filter( pothole => pothole.status === 'Inspected');
    },
    completedPotholes: (state) => {
      return state.potholes
      .filter( pothole => pothole.status === 'Completed');
    },
    notInspectedPotholeCount: (state, getters) => {
      return getters.notInspectedPotholes.length;
    },
    activePothole: (state) => {
      return state.activePothole;
    },
    hasMenuSelection: (state) => {
      return state.hasMenuSelection;
    },
    reportMode: (state) => {
      return state.reportMode;
    },
    // Login data
    isLoggedIn: state => !!state.token,
    authStatus: state => state.status,
    userId: state => state.user.id,
    username: state => state.user.username,
  },
})
