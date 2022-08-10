


<template>
  <div class="nav">
    <img
      src="..\assets\LOGO TRANSPARENCY white.png"
      alt="Melody Logo"
      class="homelogo"
    />

    <!-- <h1>Home</h1>
    <p>You must be authenticated to see this</p> -->
    <favorite-bands />
  </div>
</template>

<script>
import FavoriteBands from "@/components/FavoriteBands";
export default {
  name: "home",
  components: { FavoriteBands },
};
</script>

<style>
.nav {
  margin: -30px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: flex-center;
  align-content: center;
  background: black;
  color: white;
  background-image: url("~@/assets/crowd.png");
  background-position: bottom;
  background-size: cover;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}
</style>