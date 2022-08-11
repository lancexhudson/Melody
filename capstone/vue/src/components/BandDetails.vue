<template>
  <div class="band-details">
    <h1>{{ this.$store.state }}</h1>
  </div>
</template>

<script>
import bandService from "@/services/BandService.js";
export default {
  name: "band-details",
  data() {
    return {
      name: this.$route.params.bandName,
    };
  },
  methods: {
    getBand(name) {
      bandService.getBand(name).then((response) => {
        if (response.status === 200) {
          this.$router.push("/bands/name/${this.bandName}");
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>