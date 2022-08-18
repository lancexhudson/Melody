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
      <img
        v-if="!amIFollowing(band.bandId)"
        class="heart-button"
        src="..\assets\love.png"
        alt="heart"
      />
      <img
        v-if="amIFollowing(band.bandId)"
        class="heart-button"
        src="..\assets\red-love.png"
        alt="red-heart"
      />
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
      hasBeenClicked: false,
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
      if (this.$store.state.myBands.some((b) => b.bandId === Id)) {
        bandService.UNfollow(this.$store.state.user.id, Id);
        this.$router.go();
      } else {
        this.favorite.bandId = Id;
        bandService.makeFavorite(this.favorite);
        this.favorite.bandId = 0;
        this.isActive = false;
        this.$router.go();
      }
    },
    amIFollowing(bandId) {
      return this.$store.state.myBands.some((b) => b.bandId === bandId);
    },
    setMyBands() {
      bandService
        .favoriteBandsByUser(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_MY_BANDS", response.data);
        });
    },
  },
  created() {
    this.setMyBands();
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
  margin-bottom: 20px;
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
  width: 14rem;
  max-width: 250px;
  height: 150px;
  max-height: 250px;
  margin-bottom: 19px;
}
.submit-button {
  width: 80px;
  font-weight: light;
  margin-bottom: 19px;
  color: grey;
  background-color: transparent;
  border-color: white;
  border-radius: 0.2rem;
}

.submit-button:hover {
  background-color: rgb(37, 35, 33);
}

.heart-button {
  padding-top: 2px;
  max-width: 20px;
  /* background-color:; */
}
</style>