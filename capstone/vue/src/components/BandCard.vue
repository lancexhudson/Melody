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
      :disabled="!isActive"
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
  data() {
    return {
      isActive: true,
      favorite: {
        bandId: 0,
      },
    };
  },
  props: ["band"],
  band: Object,
  enableFollow: {
    type: Boolean,
    default: false,
  },
  methods: {
    toggleFavorite(Id) {
      this.favorite.bandId = Id;
      bandService.makeFavorite(this.favorite);
      this.favorite.bandId = 0;
      this.isActive = false;
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
  padding: 4px;
  width: 100%;
  max-width: 250px;
  height: auto;
  background: rgba(0, 0, 0, 0.8);
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
  width: 100%;
  max-width: 250px;
  height: auto;
  max-height: 250px;
}
.submit-button {
  width: 80px;
  font-weight: bold;
}
</style>