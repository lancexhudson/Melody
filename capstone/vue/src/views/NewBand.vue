<template>
  <div>
    <div class="melodyLogo">
      <router-link v-bind:to="{ name: 'home' }">
        <img
          src="..\assets\LOGO TRANSPARENCY white.png"
          alt="Melody Logo"
          class="homelogo"
        />
      </router-link>
    </div>
    <div class="form">
      <new-band-form></new-band-form>
    </div>
  </div>
</template>

<script>
import NewBandForm from "@/components/NewBandForm";
export default {
  components: {
    NewBandForm,
  },
};
</script>

<style>
.form {
  display: flex;
  text-align: center;
  flex-direction: column;
  justify-content: flex-center;

  background: black;
  color: white;
  background-image: url("~@/assets/crowd.png");
  background-position: bottom;
  background-size: cover;
  background-attachment: fixed;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}
</style>