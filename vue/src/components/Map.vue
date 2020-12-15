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
            :zoom="reportMode ? 18 : this.zoom"
            :options="mapOptions"
            ref="mapRef"
            @dragend="handleDrag"
            @click="handleMapClick"
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
                    <v-btn x-small class="mx-auto my-0">View Details</v-btn>
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
            <gmap-marker
                :position="getPosition(mapClick)"
                :clickable="true"
                :draggable="true"
                :visible="reportMode"
                @click="handleMapClick"
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
        mapOptions: {
            clickableIcons: false,
            disableDoubleClickZoom: true
        },
        infoWindowOptions: {
            pixelOffset: {
                witdth: 0,
                height: -35
            }
        },
        mapClick: {
            latitude: 0,
            longitude: 0,
        },
        isInfoWindowOpened: false,
    }),
    computed: {
        potholes() {
            return this.$store.getters.allPotholes;
        },
        reportMode() {
            return this.$store.getters.reportMode;
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
            this.map.setZoom(15);
            this.map.setCenter(this.getPosition(pothole));
        },
        handleInfoWindowClose() {
            this.$store.dispatch('unsetMenuSelection');
        },
        handleMapClick(e) {
            console.log(`lat: ${e.latLng.lat().toFixed(5)} lng: ${e.latLng.lng().toFixed(5)}`);
            this.mapClick.latitude = e.latLng.lat().toFixed(5);
            this.mapClick.longitude = e.latLng.lng().toFixed(5);
            this.$store.dispatch('setMapClick',this.mapClick);
        },
        // addMarker: function(lat, lng) {
        //     let marker = new google.maps.Marker({
        //         position: new google.maps.LatLng(lat, lng),
        //         draggable: true,
        //     });
        //     marker.setMap(this.map);
        // },
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