<template>
  <div>
    <form class="addEvent" action="" v-on:submit.prevent="addEvent">
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
    };
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
</style>