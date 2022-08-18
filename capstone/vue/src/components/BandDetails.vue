
<template>
  <div class="band-home">
    <h1 class="band-name">{{ band.bandName }}</h1>
    <img :src="band.imageLink" class="hero-picture" />

    <h3 class="about-the-band-h3">About the Band</h3>
    <p class="description">
      {{ band.description }}
    </p>

    <h3 class="genre-h3">Genre</h3>
    <p class="genre" v-for="genre in band.genres" v-bind:key="genre.genreId">
      {{ genre.genreName }}
    </p>

    <h3 class="upcoming-events-h3">Upcoming Events</h3>
    <p class="upcoming-events"></p>

    <table class="upcoming-events-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Time</th>
          <th>Venue</th>
          <th v-if="isManager">Edit</th>
          <th v-if="isManager">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr
          class="input-for-upcoming-events-table"
          v-for="event in this.myEvents"
          v-bind:key="event.eventId"
        >
          <td>{{ event.eventDate }}</td>
          <td>{{ formatTime(event.eventTime) }}</td>
          <td>
            <a
              class="venue-link"
              href="https://www.livenation.com/venue/KovZpZAEk6IA/bogart-s-events"
              target="_blank"
              >{{ event.venue }}</a
            >
          </td>
          <td v-if="isManager">
            <router-link
              :to="{
                name: 'updateEvent',
                params: {
                  id: event.eventId,
                  theBandId: $route.params.bandId,
                },
              }"
              ><button>edit</button></router-link
            >
          </td>
          <td v-if="isManager">
            <button @click="deleteEvent(event.eventId)" class="deleteButton">
              delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import bandService from "@/services/BandService.js";
import eventService from "@/services/EventService.js";

export default {
  name: "band-details",
  data() {
    return {
      myEvents: [],
      myBandId: this.$route.params.bandId,
      myManager: 0,
    };
  },
  props: ["band"],

  methods: {
    setEvents() {
      eventService.listEventsForBand(this.myBandId).then((response) => {
        this.myEvents = response.data;
      });
    },
    formatTime(time) {
      var hours = time.substring(0, 2);
      var minutes = time.substring(3, 5);
      var ampm = hours >= 12 ? "pm" : "am";
      hours = hours % 12;
      hours = hours ? hours : 12; // the hour '0' should be '12'
      //minutes = minutes < 10 ? "0" + minutes : minutes;
      var strTime = hours + ":" + minutes + " " + ampm;
      return strTime;
    },
    deleteEvent(eventId) {
      eventService.deleteEvent(eventId).then((response) => {
        if (confirm("Do YOU wish to delete this event?")) {
          if (response.status == 200) {
            this.$router.go();
          }
        }
      });
    },
    setMyManager() {
      bandService.getMyManager(this.myBandId).then((response) => {
        this.myManager = response.data;
      });
    },
  },
  computed: {
    isManager() {
      return this.myManager == this.$store.state.user.id ? true : false;
    },
  },
  created() {
    this.setEvents();
    this.setMyManager();
  },
};
</script>

<style>
body.bandDetails {
  display: flex;
  text-align: center;
  flex-direction: column;
  justify-content: flex-center;
  align-content: center;
  background: black;
  color: white;
  background-image: url("~@/assets/crowd.png");
  background-position: bottom;
  background-size: cover;
  background-attachment: fixed;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}

.band-home {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.8);
  border-radius: 0.5rem;
  padding: 50px;
  width: 100%;
  margin-top: 5rem;
  margin-bottom: 100px;
  max-width: 700px;
  height: auto;
}

.hero-picture {
  max-height: 600px;
  max-width: 600px;
  min-width: 600px;
  max-width: 600px;
  border-radius: 1rem;
}

.description {
  inline-size: 600px;
  margin-top: 0px;
  margin-bottom: 0px;
  font-weight: lighter;
}

.genre {
  inline-size: 600px;
  margin-top: 0px;
  margin-bottom: 0px;
  font-weight: lighter;
}

.upcoming-events {
  inline-size: 600px;
}

.about-the-band-h3 {
  text-decoration: underline;
  font-weight: lighter;
}

.genre-h3 {
  text-decoration: underline;
  font-weight: lighter;
}

.upcoming-events-h3 {
  text-decoration: underline;
  font-weight: lighter;
  margin-bottom: 0px;
}

.upcoming-events-table {
  /* border-collapse: collapse; */
  border-radius: 0.5rem;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.8);
  margin: 1rem;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 600px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
  margin-top: 0px;
  margin-bottom: 0px;
}

.upcoming-events-table thead tr {
  background-color: rgb(29, 26, 26);
  color: #ffffff;
  text-align: center;
}

.upcoming-events-table th,
.upcoming-events-table td {
  border-radius: 0.5rem;
  padding: 4px 8px;
}

.upcoming-events-table tbody tr {
  border-bottom: thin transparent #dddddd;
}

/* .upcoming-events-table tbody tr:last-of-type {
  border-bottom: 2px solid #ffffff;
} */

.input-for-upcoming-events-table tbody tr.active-row {
  font-weight: bold;
  color: #009879;
}

.venue-link {
  text-decoration: none;
  color: white;
}
</style>