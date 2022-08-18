<template>
  <div>
    <form class="addEvent" action="" v-on:submit.prevent="updateEvent">
      <h5 class="eventDetailsHeader">UPDATE YOUR EVENT</h5>
      <input type="date" v-model="newEvent.eventDate" />
      <input type="time" v-model="newEvent.eventTime" />
      <input type="text" placeholder="venue" v-model="newEvent.venue" />
      <button>Update</button>
    </form>
  </div>
</template>

<script>
import eventService from "@/services/EventService";
export default {
  name: "update-event",
  data() {
    return {
      newEvent: {
        eventId: this.$route.params.id,
        eventDate: "",
        eventTime: "",
        venue: "",
        bandId: parseInt(this.$route.params.theBandId),
      },
    };
  },
  methods: {
    updateEvent() {
      this.newEvent.eventTime = this.newEvent.eventTime + ":00";
      eventService.updateEvent(this.newEvent).then((response) => {
        if (response.status == 200) {
          this.$router.go(-1);
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
  min-width: 320px;
  margin-left: 50rem;
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