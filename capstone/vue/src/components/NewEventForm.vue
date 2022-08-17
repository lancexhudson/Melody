<template>
  <div>
    <input type="date" v-model="newEvent.date" />
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

<style lang="scss" scoped>
</style>