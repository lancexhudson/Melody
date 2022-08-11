<template>
  <div>
    <h1 class="band-name">{{ band.bandName }}</h1>
    <img :src="band.imageLink" class="hero-picture" />
    <h4 class="genre">Genre: {{ band.genre }}</h4>
    <p class="description">{{ band.description }}</p>
  </div>
</template>

<script>
// import bandService from "@/services/BandService.js";
export default {
  name: "band-details",

  props: ["band"],
};
</script>

<style>
body.bandDetails {
  display: flex;
  text-align: center;
  flex-direction: column;
  justify-content: flex-center;

  align-content: center;
  background: black;
  color: white;
  background-image: url("~@/assets/crowd.png");
  background-position: bottom;
  background-size: cover;
  background-attachment: fixed;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}
.hero-picture {
  max-height: 500px;
  max-width: 500px;
}
</style>