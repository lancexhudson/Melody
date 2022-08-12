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
    <button type="submit" v-on:click.prevent="addToFollowList(band)">
      Test Button
    </button>
  </div>
</template>

<script>
export default {
  name: "band-card",
  props: ["band"],
  band: Object,
  enableFollow: {
    type: Boolean,
    default: false,
  },
  methods: {
    addToFollowList(band) {
      let followedBand = Object.assign(band);
      this.$store.commit("FOLLOW_BAND", followedBand);
    },
  },
};
</script>

<style>
.card {
  border: solid rgb(255, 255, 255) 2px;
  border-radius: 0.5rem;
  background-color: rgba(53, 53, 53, 0.7);
  padding: 4px;
  width: 300px;
}
.cover {
  border-radius: 0.5rem;
  max-width: 290px;
  max-height: 100px;
}
</style>