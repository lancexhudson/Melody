<template>
  <div class="band-container">
    <band-card
      v-bind:band="band"
      v-for="band in $store.state.bands"
      v-bind:key="band.id"
    >
    </band-card>
  </div>
</template>

<script>
import bandCard from "@/components/BandCard.vue";
import bandService from "@/services/BandService.js";
export default {
  name: "band-list",
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
.band-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>