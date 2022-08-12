<template>
  <div class="card">
    <router-link :to="{ name: 'bandDetails', params: { bandId: band.bandId } }">
      <h2 class="band-name">{{ band.bandName }}</h2>
      <img class="cover" :src="band.imageLink" alt="band-image" />
    </router-link>
    <!-- <div class="button-container" v-if="!enableFollow">
      <button v-if="enableFollow" v-on:click.prevent="addToFollowList(band)">
        Follow Band
      </button>
    </div> -->
    <button
      type="submit"
      class="submit-button"
      v-on:click.prevent="toggleFavorite(band.bandId)"
    >
      Favorite?
    </button>
  </div>
</template>

<script>
import bandService from "@/services/BandService.js";
export default {
  name: "band-card",
  props: ["band"],
  band: Object,
  enableFollow: {
    type: Boolean,
    default: false,
  },
  methods: {
    toggleFavorite(bandId) {
      this.$store.commit("FOLLOW_BAND", bandId);
      bandService.makeFavorite(this.$store.state.favorite);
    },
  },
};
</script>

<style>
.card {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  border: solid rgb(255, 255, 255) 1px;
  border-radius: 0.5rem;
  background-color: rgba(53, 53, 53, 0.7);
  padding: 4px;
  width: 300px;
  height: 300px;
}
a:link {
  text-decoration: none;
}
.band-name {
  color: white;
  text-decoration: none;
}
.cover {
  border-radius: 0.5rem;
  max-width: 250px;
  max-height: 250px;
}
.submit-button {
  width: 80px;
  font-weight: bold;
}
</style>