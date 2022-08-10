<template>
  <div class="form">
    <new-band-form></new-band-form>
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
  margin: -30px;
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