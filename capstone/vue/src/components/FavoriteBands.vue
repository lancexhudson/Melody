<template>
  <div>
    <band-card v-bind:band="band" />
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
import bandCard from "@/components/BandCard.vue";
export default {
  name: "favorite-bands",
  components: {
    bandCard,
  },
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