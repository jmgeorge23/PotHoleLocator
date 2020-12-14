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
            :zoom="this.zoom"
            ref="mapRef"
            @dragend="handleDrag"
        >
            <gmap-info-window
                :options="infoWindowOptions"
                :position="infoWindowPosition"
                :opened="hasMenuSelection"
                @closeclick="handleInfoWindowClose"
                >
                <div class="info-window"
                    >
                    <h2>
                        <v-icon
                            medium
                            >
                        mdi-road
                        </v-icon>
                        Pothole on {{activePothole.roadName}}
                    </h2>
                    <p>
                        <v-icon
                            medium
                            color="orange darken-2"
                            >
                            mdi-alert
                        </v-icon>
                        Severity: {{activePothole.severity}}
                    </p>
                    <p>
                        <v-icon
                            medium
                            color="blue"
                            >
                            mdi-clipboard-check
                        </v-icon>
                        Report Status: {{activePothole.status}}
                    </p>
                </div>
            </gmap-info-window>
            <gmap-marker
                v-for="pothole in potholes"
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

//import potholeServices from '../services/PotholeService.js';

export default {
    data: () => ({
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
        isInfoWindowOpened: false,
    }),
    computed: {
        potholes() {
            return this.$store.getters.allPotholes;
        },
        mapCoordinates() {
            if(!this.map) {
                return {
                    lat: 0,
                    lng: 0
                };
            }
            return {
                lat: this.map.getCenter().lat().toFixed(5),
                lng: this.map.getCenter().lng().toFixed(5)
            }
        },
        infoWindowPosition() {
            if (this.activePothole.latitude == undefined) {
                return {
                    lat: 0,
                    lng: 0
                }
            }
            return {
                lat: parseFloat(this.activePothole.latitude),
                lng: parseFloat(this.activePothole.longitude),
            }
        },
        activePothole() {
            return this.$store.getters.activePothole;
        },
        hasMenuSelection() {
            return this.$store.getters.hasMenuSelection;
        }
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
        handleMarkerClicked(pothole) {
            this.$store.dispatch('setMenuSelection', pothole);
        },
        handleInfoWindowClose() {
            this.$store.dispatch('unsetMenuSelection');
        },
    },
    created() {
        // does the user have a saved center? use it instead of the default
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
        if(this.hasMenuSelection) {
            this.isInfoWindowOpened = true;
        }
        // add the map to a data object
        this.$refs.mapRef.$mapPromise.then(map => this.map = map);
    },
}
</script>
<style>
    .gmap {
        /* width: 500px; */
        height: 89vh;
        margin: 5px auto;
    }
    .info-window {
        padding: 0.5em;
    }
    .info-window h2 {
        padding-bottom: 0.5em;
    }
    .info-window p {
        margin: 0;
    }
</style>