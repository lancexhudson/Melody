
<template>
  <div class="band-home">
    <h1 class="band-name">{{ band.bandName }}</h1>
    <img :src="band.imageLink" class="hero-picture" />
    <p class="description">
      <span style="font-style: italic">About the Band:: </span>
      {{ band.description }}
    </p>
    <p class="genre" v-for="genre in band.genres" v-bind:key="genre.genreId">
      <span style="font-style: italic">Genre(s): </span>
      {{ genre.genreName }}
    </p>
  </div>
</template>

<script>
// import bandService from "@/services/BandService.js";
export default {
  name: "band-details",
  data() {
    return {
      genreWord: this.returnString(this.band),
    };
  },

  props: ["band"],
  computed: {
    returnString(band) {
      let genre = band.genres[0].genreName;
      return genre;
    },
  },
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

.band-home {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  border: solid rgb(255, 255, 255) 1px;
  border-radius: 0.5rem;
  padding: 50px;
  width: 100%;
  max-width: 700px;
  height: auto;
}

.hero-picture {
  max-height: 600px;
  max-width: 600px;
  min-width: 600px;
  max-width: 600px;
}

.description {
  inline-size: 600px;
}

.genre {
  /* display: inline-flex; */
  inline-size: 600px;
}
</style>