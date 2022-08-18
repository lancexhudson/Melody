<template>
  <div class="new-event-wrapper">
    <form
      v-if="isManager"
      class="addEvent"
      action=""
      v-on:submit.prevent="addEvent"
    >
      <h5 class="eventDetailsHeader">ADD YOUR EVENT</h5>
      <input type="date" v-model="newEvent.eventDate" />
      <input type="time" v-model="newEvent.eventTime" />
      <input type="text" placeholder="venue" v-model="newEvent.venue" />
      <button>Add Event</button>
    </form>
  </div>
</template>

<script>
import eventService from "@/services/EventService.js";
import bandService from "@/services/BandService.js";
export default {
  name: "new-event-form",
  data() {
    return {
      newEvent: {
        eventDate: "",
        eventTime: "",
        venue: "",
        bandId: this.$route.params.bandId,
      },
      myManager: 0,
    };
  },
  computed: {
    isManager() {
      return this.myManager == this.$store.state.user.id ? true : false;
    },
  },
  methods: {
    addEvent() {
      this.newEvent.eventTime = this.newEvent.eventTime + ":00";

      eventService.createAnEvent(this.newEvent).then((response) => {
        if (response.status == 201) {
          this.newEvent = {
            eventDate: "",
            eventTime: "",
            venue: "",
            bandId: this.$route.params.bandId,
          };
          this.$router.go();
        }
      });
    },
    setMyManager() {
      bandService.getMyManager(this.newEvent.bandId).then((response) => {
        this.myManager = response.data;
      });
    },
  },
  created() {
    this.setMyManager();
  },
};
</script>

<style>
.addEvent {
  display: flex;

  justify-content: center;
  flex-direction: column;
  border: solid white 1px;
  padding: 20px;
  max-width: 320px;

  border-radius: 0.5rem;
  margin-top: 1rem;
  background: rgba(0, 0, 0, 0.8);
}
.eventDetailsHeader {
  margin: 20px;
  margin-top: -2px;
  font-weight: 900;
  font-size: 15pt;
  text-decoration: underline;
  color: white;
}
.new-event-wrapper {
  display: flex;
  margin: 0rem;
}
</style>