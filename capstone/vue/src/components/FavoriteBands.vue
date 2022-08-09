<template>
  <div></div>
</template>

<script>
import bandService from "@/services/BandService.js";
export default {
  name: "favorite-bands",
  methods: {
    listBands() {
      bandService.listBands().then((response) => {
        this.$store.commit("SET_BANDS", response.data);
      });
    },
  },
};
</script>

<style>
</style>