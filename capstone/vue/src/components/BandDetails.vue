<template>
  <div class="band-details">
    <router-link
      v-bind:key="band.bandId"
      :to="{ name: 'bandDetails', params: { bandId: 'band.bandId' } }"
      ><band-card v-bind:band="band"
    /></router-link>
  </div>
</template>

<script>
import bandService from "@/services/BandService.js";
export default {
  name: "band-details",
  data() {
    return {
      name: this.$route.params.bandId,
    };
  },
  methods: {
    getBand(bandId) {
      bandService.getBand(bandId).then((response) => {
        if (response.status === 200) {
          this.$router.push("/bands/id/${this.bandId}");
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>