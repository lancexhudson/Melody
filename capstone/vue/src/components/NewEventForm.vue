<template>
  <div class="addEvent">
    <h5 class="eventDetailsHeader">ADD EVENT DETAILS</h5>
    <input type="date" v-model="newEvent.date" class="dateInput" />
    <input type="time" v-model="newEvent.time" />
    <input type="text" placeholder="venue" v-model="newEvent.venue" />
  </div>
</template>

<script>
export default {
  name: "new-event-form",
  data() {
    return {
      newEvent: {
        date: "",
        time: "",
        venue: "",
      },
    };
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
  margin-left: 54.3rem;
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
}
</style>