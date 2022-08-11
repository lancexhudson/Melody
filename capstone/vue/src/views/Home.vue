


<template>
  <div>
    <router-link v-bind:to="{ name: 'home' }" class="logoContainer">
      <img
        src="..\assets\LOGO TRANSPARENCY white.png"
        alt="Melody Logo"
        class="homelogo"
      />
    </router-link>
    <div class="home">
      <router-link class="addBand" :to="{ name: 'addBand' }"
        >Add new Band</router-link
      >
      <band-list></band-list>
    </div>
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
.logoContainer {
  display: flex;
  align-self: flex-start;
  margin-right: 1600px;
}
.homelogo {
  align-self: flex-start;
  justify-items: flex-start;
  max-width: 300px;
  margin: 8px;
  flex-shrink: 1;
}
.addBand {
  color: rgb(250, 250, 250);
  font-size: 14pt;
}
</style>