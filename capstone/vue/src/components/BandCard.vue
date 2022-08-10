<template>
  <div class="card">
    <h2 class="band-name">{{ band.bandName }}</h2>
    <img
      src="https://pyxis.nymag.com/v1/imgs/0a3/50b/a09fe23d1c207b16070f503e282b480cdf-2-dave.rsquare.w330.jpg"
      alt="Dave"
    />
  </div>
</template>

<script>
export default {
  name: "band-card",
  props: ["band"],
};
</script>

<style>
</style>