


<template>
  <div class="home">
    <img
      src="..\assets\LOGO TRANSPARENCY white.png"
      alt="Melody Logo"
      class="homelogo"
    />
    <router-link :to="{ name: 'addBand' }">Add new Band</router-link>
    <!-- <h1>Home</h1>
    <p>You must be authenticated to see this</p> -->
    <!-- <favorite-bands /> -->
    <band-list></band-list>
  </div>
</template>

<script>
// import FavoriteBands from "@/components/FavoriteBands";
import BandList from "@/components/BandList.vue";
export default {
  beforeCreate: function () {
    document.body.className = "home";
  },
  name: "home",
  components: { BandList },
};
</script>

<style>
body.home {
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
.homelogo {
  align-self: center;
  justify-items: center;
  max-width: 300px;
  margin: 100px;
}
</style>