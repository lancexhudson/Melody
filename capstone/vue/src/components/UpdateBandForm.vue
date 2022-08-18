<template>
  <form
    v-if="isMyManager"
    class="update-band-form"
    v-on:submit.prevent="updateBand"
  >
    <div>
      <p class="updateBand">UPDATE YOUR BAND</p>
    </div>
    <ul class="band-updating">
      <li>
        <input
          class="band-name-update"
          type="text"
          placeholder="  Band Name"
          v-model="newBand.bandName"
          required
        />
      </li>
      <li>
        <textarea
          class="band-description-input"
          type="textarea"
          placeholder=" Your Band's Description"
          v-model="newBand.description"
          required
        >
        </textarea>
      </li>
      <li>
        <select
          class="genre-selector-update"
          name="genre-selector"
          id="genre-select"
          v-model="newBand.genres"
          required
          multiple
        >
          <option value="" disabled selected>Select Genre</option>
          <option
            v-for="genre in this.$store.state.genres"
            v-bind:key="genre.genreId"
            :value="genre.genreId"
          >
            {{ genre.genreName }}
          </option>
        </select>
      </li>
      <li>
        <textarea
          class="image-link-update"
          placeholder=" Image Link"
          v-model="newBand.imageLink"
          required
        >
        </textarea>
      </li>

      <li>
        <button class="updateBandButton">Update Band</button>
      </li>
    </ul>
  </form>
</template>

<script>
import bandService from "@/services/BandService.js";
import genreService from "@/services/GenreService.js";
import authHelp from "@/services/AuthHelpService.js";
export default {
  name: "band-form",

  props: ["band"],

  data() {
    return {
      newBand: {
        bandName: "",
        description: "",
        genres: [],
        imageLink: "",
      },
      bandInfo: {},
      theBandId: this.$route.params.bandId,
      myManager: 0,
    };
  },
  methods: {
    updateBand() {
      bandService.updateBand(this.theBandId, this.newBand).then((response) => {
        if (response.status == 200) {
          this.$router.go();
        }
      });
      this.newBand = {
        bandName: "",
        description: "",
        genres: [],
        imageLink: "",
      };
    },
    setMyManager() {
      bandService.getMyManager(this.theBandId).then((response) => {
        this.myManager = response.data;
      });
    },
    listGenres() {
      genreService.listGenres().then((response) => {
        this.$store.commit("SET_GENRES", response.data);
      });
    },
    setMyBandInfo() {
      bandService.getBandById(this.theBandId).then((response) => {
        this.newBand = response.data;
      });
    },
  },
  computed: {
    isMyManager() {
      return authHelp.canModify(this.$store.state.user.id, this.myManager);
    },
  },
  created() {
    this.listGenres();
    this.setMyManager();
    this.setMyBandInfo();
  },
};
</script>

<style>
.band-updating,
.band-name-update,
option,
button {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.update-band-form {
  display: block;
  flex-direction: column;
  align-content: center;
  margin-left: 50rem;
  margin-top: 1rem;
  min-width: auto;
  width: 360px;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 0.5rem;
}

.band-updating {
  display: flex;
  flex-direction: column;
  padding: 0;
  list-style: none;
}

.band-name-update {
  display: flex;
  justify-content: center;
  width: 320px;
  max-width: auto;
  padding: 0;
  margin-left: 1rem;
  /* border: 0; */
  height: 25px;
}
.band-description-input {
  display: flex;
  justify-items: center;
  height: 50px;
  width: 320px;
  border-radius: 0rem;
  margin-left: 1rem;
}
.genre-selector-update {
  width: 320px;
  padding: 0;
  margin-right: 13px;

  margin-left: 0.4rem;
}
.image-link-update {
  display: flex;
  justify-content: center;
  width: 320px;
  padding: 0;

  margin: 0.8rem;
}
.updateBandButton {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 320px;
  padding: 0;
  border: 3px;
  margin-left: 0.82rem;
  height: 30px;
}
.updateBand {
  font-size: 15pt;
  font-weight: 900;
  text-decoration: underline;
}
</style>