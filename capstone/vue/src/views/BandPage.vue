<template>
  <div class="main-div">
    <div class="melodyLogo">
      <router-link v-bind:to="{ name: 'home' }">
        <img
          src="..\assets\LOGO TRANSPARENCY white.png"
          alt="Melody Logo"
          class="homelogo"
        />
      </router-link>
      <br />
      <br />
      <div>
        <new-message-form></new-message-form>
        <new-event-form></new-event-form>
        <update-band-form class="update-your-band"></update-band-form>
      </div>
      <band-details v-bind:band="band"></band-details>
    </div>
  </div>
</template>

<script>
import bandDetails from "@/components/BandDetails";
import bandService from "@/services/BandService";
import newMessageForm from "@/components/NewMessageForm";
import updateBandForm from "@/components/UpdateBandForm";
import newEventForm from "@/components/NewEventForm";
export default {
  beforeCreate: function () {
    document.body.className = "bandDetailsView";
  },
  data() {
    return {
      band: {},
    };
  },
  components: { bandDetails, newMessageForm, updateBandForm, newEventForm },
  created() {
    const selectedBandId = this.$route.params.bandId;
    bandService.getBandById(selectedBandId).then((response) => {
      if (response.status === 200) {
        this.band = response.data;
      }
    });
  },
  methods: {
    getBandById() {
      const selectedBandId = this.$route.params.bandId;
      return this.$store.state.bands.find((b) => b.bandId === selectedBandId);
    },
  },
};
</script>

<style>
body.bandDetailsView {
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
.main-div {
  display: flex;
  margin-right: -70px;
}
</style>