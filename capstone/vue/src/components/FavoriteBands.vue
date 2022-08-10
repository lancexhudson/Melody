<template>
  <div>
    <table>
      <tr v-for="band in this.$store.state.bands" v-bind:key="band.id">
        <td>
          {{ band.bandName }}
        </td>
      </tr>
    </table>
  </div>
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
  created() {
    this.listBands();
  },
};
</script>

<style>
</style>