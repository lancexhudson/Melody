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
      <br />
      <br />
      <band-details v-bind:band="band"></band-details>
    </div>
  </div>
</template>

<script>
import bandDetails from "@/components/BandDetails";
import bandService from "@/services/BandService";
export default {
  data() {
    return {
      band: {},
    };
  },
  components: { bandDetails },
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
</style>