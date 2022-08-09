<template>
  <div class="home">
    <!-- <h1>Home</h1>
    <p>You must be authenticated to see this</p> -->
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style>
.home {
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