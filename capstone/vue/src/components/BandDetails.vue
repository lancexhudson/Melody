<template>
  <div>
    <h1 class="band-name">{{ band.bandName }}</h1>
  </div>
</template>

<script>
// import bandService from "@/services/BandService.js";
export default {
  name: "band-details",

  props: ["band"],
};
</script>

<style lang="scss" scoped>
</style>