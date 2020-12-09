<template>
    <div>
        <div style="max-width: 800px; margin: 0 auto; display: flex; align-items: center; justify-content: space-between">
            <div hidden>
                <h1>Your coordinates:</h1>
                <p>{{ myCoordinates.lat }} Latitude, {{ myCoordinates.lng }} Longitude</p>
            </div>
            <div hidden>
                <h1>Map coordinates:</h1>
                <p>{{ mapCoordinates.lat }} Latitude, {{ mapCoordinates.lng }} Longitude</p>
            </div>
        </div>
        <gmap-map
            class="gmap"
            :center="myCoordinates"
            :zoom="12.8"
            ref="mapRef"
            @dragend="handleDrag"
        >
            <gmap-info-window
                :options="infoWindowOptions"
                :position="infoWindowPosition"
                :opened="isInfoWindowOpened"
                @closeclick="handleInfoWindowClose"
                >
                    <div class="info-window">
                        <h2>Pothole on [street name]</h2>
                        <h5>Severity: [severity-level]</h5>
                        <p>Reported on: [date]</p>
                        <button>View Details</button>
                    </div>
                </gmap-info-window>
            <gmap-marker
                v-for="pothole in this.$store.state.potholes"
                :key="pothole.potholeId"
                :position="getPosition(pothole)"
                :clickable="true"
                :draggable="false"
                @click="handleMarkerClicked(pothole)"
            ></gmap-marker>    
        </gmap-map>
    </div>
</template>
<script>

import potholeServices from '../services/PotholeService.js';

    export default {
        data() {
            return {
                map: null,
                myCoordinates: {
                    lat: 0,
                    lng: 0
                },
                zoom: 12,
                infoWindowOptions: {
                    pixelOffset: {
                        witdth: 0,
                        height: -35
                    }
                },
                activePothole: {},
                isInfoWindowOpened: false
            }
        },
        created() {
            // does the user have a saved center? use it instead of the default
            this.getAllPotholes();

            if(localStorage.center) {
                this.myCoordinates = JSON.parse(localStorage.center);
            } else {
                // get user's coordinates from browser request
                this.$getLocation({})
                    .then(coordinates => {
                        this.myCoordinates = coordinates;
                    })
                    .catch(error => alert(error));
            }
            // does the user have a saved zoom? use it instead of the default
            if(localStorage.zoom) {
                this.zoom = parseInt(localStorage.zoom);
            }
        },
        mounted() {
            // add the map to a data object
            this.$refs.mapRef.$mapPromise.then(map => this.map = map);
        },
        methods: {
            handleDrag() {
                // get center and zoom level, store in localstorage
                let center = {
                    lat: this.map.getCenter().lat(),
                    lng: this.map.getCenter().lng()
                };
                let zoom = this.map.getZoom();
                localStorage.center = JSON.stringify(center);
                localStorage.zoom = zoom;
            },
            getPosition(pothole) {
                return {
                    lat: parseFloat(pothole.latitude),
                    lng: parseFloat(pothole.longitude)
                }
            },
            getAllPotholes() {
                potholeServices.getAllPothole()
                    .then(response => {
                        this.$store.commit('SET_POTHOLES', response.data);
                    })
                    .catch(error => alert(error));
            },
            handleMarkerClicked(pothole) {
                this.activePothole = pothole;
                this.isInfoWindowOpened = true;
            },
            handleInfoWindowClose() {
                this.activePothole = {};
                this.isInfoWindowOpened = false;
            },
        },
        computed: {
            mapCoordinates() {
                if(!this.map) {
                    return {
                        lat: 0,
                        lng: 0
                    };
                }
                return {
                    lat: this.map.getCenter().lat().toFixed(4),
                    lng: this.map.getCenter().lng().toFixed(4)
                }
            },
            infoWindowPosition() {
                return {
                    lat: parseFloat(this.activePothole.latitude),
                    lng: parseFloat(this.activePothole.longitude),
                }
            }
        }
    }
</script>
<style>
    .gmap {
        width:800px;
        height:800px;
        margin: 32px auto;
    }
    @media screen and (max-width: 900px){
        .gmap {
            width:400px;
            height:400px;
            margin: 32px auto;
        }       
    }
    @media screen and (max-width: 1264px){
        .gmap {
            width:700px;
            height:700px;
            margin: 32px auto;
        }       
    }
</style>