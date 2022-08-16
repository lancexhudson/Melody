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
      <li class="form-row">
        <input
          class="band-name-update"
          type="text"
          placeholder="  Band Name"
          v-model="newBand.bandName"
          required
        />
      </li>
      <li class="form-row">
        <textarea
          class="band-description-input"
          type="textarea"
          placeholder=" Your Band's Description"
          v-model="newBand.description"
          required
        >
        </textarea>
      </li>
      <li class="form-row">
        <select
          class="genre-selector-update"
          name="genre-selector"
          id="genre-select"
          v-model="newBand.genre"
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
      <li class="form-row">
        <textarea
          class="image-link-update"
          placeholder=" Image Link"
          v-model="newBand.imageLink"
          required
        >
        </textarea>
      </li>

      <li class="form-row">
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
        genre: [],
        imageLink: "",
      },
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
        genre: [],
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
  },
  computed: {
    isMyManager() {
      return authHelp.canModify(this.$store.state.user.id, this.myManager);
    },
  },
  created() {
    this.listGenres();
    this.setMyManager();
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
  margin-left: 54.6rem;
  margin-top: 1rem;
  min-width: auto;
  width: 350px;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 0.5rem;
}
.form {
  justify-content: center;
  align-items: center;
}
.band-updating {
  display: flex;
  flex-direction: column;
  padding: 0;
  list-style: none;
}

.band-name-update {
  width: 330px;
  padding: 0;
  margin: 5px;
  border: 0;
  height: 25px;
}
.band-description-input {
  height: 50px;
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.genre-selector-update {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.image-link-update {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 5px;
}
.updateBandButton {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
  height: 30px;
}
.updateBand {
  font-size: 15pt;
  font-weight: 900;
  text-decoration: underline;
}
</style>