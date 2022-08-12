<template>
  <form class="new-band-form" v-on:submit.prevent="saveBand">
    <ul class="band-creation">
      <li>
        <input
          class="band-name-input"
          type="text"
          placeholder="Band Name"
          v-model="newBand.bandName"
        />
      </li>
      <li>
        <textarea
          class="band-description-input"
          type="textarea"
          placeholder="Your Band's Description"
          v-model="newBand.description"
        >
        </textarea>
      </li>
      <li>
        <select
          class="genre-selector"
          name="genre-selector"
          id="genre-select"
          v-model="newBand.genre"
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
          <!-- <option value="Pop">Pop</option>
          <option value="Rock">Rock</option>
          <option value="HipHop">HipHop</option>
          <option value="Classical">Classical</option>
          <option value="RnB">{{ "R & B" }}</option>
          <option value="Electronic">Electronic</option>
          <option value="Country">Country</option>
          <option value="Blues">Blues</option>
          <option value="Jazz">Jazz</option>
          <option value="Metal">Metal</option>
          <option value="Reggae">Reggae</option>
          <option value="Folk">Folk</option>
          <option value="Latin">Latin</option>
          <option value="A capella">A capella</option>
          <option value="Funk">Funk</option>
          <option value="Worship">Worship</option>
          <option value="Instrumental">Instrumental</option>
          <option value="Punk">Punk</option>
          <option value="Singer/Songwriter">Singer/Songwriter</option>
          <option value="World">World</option> -->
        </select>
      </li>
      <li>
        <textarea
          class="image-link"
          placeholder="Image Link"
          v-model="newBand.imageLink"
        >
        </textarea>
      </li>
      <button class="saveBandButton">Save Band</button>
    </ul>
  </form>
</template>

<script>
import bandService from "@/services/BandService.js";
import genreService from "@/services/GenreService.js";

export default {
  name: "band-form",
  data() {
    return {
      newBand: {
        bandName: "",
        description: "",
        genre: [],
        imageLink: "",
      },
    };
  },
  methods: {
    saveBand() {
      bandService.createBand(this.newBand).then((response) => {
        if (response.status == 201) {
          this.$router.push({ name: "home" });
        }
      });
      this.newBand = {
        bandName: "",
        description: "",
        genre: [],
        imageLink: "",
      };
    },
    listGenres() {
      genreService.listGenres().then((response) => {
        this.$store.commit("SET_GENRES", response.data);
      });
    },
  },
  created() {
    this.listGenres();
  },
};
</script>

<style>
/* .button {
  font-size: 200px;
} */
.band-creation,
.band-name-input,
option,
button {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.band-creation {
  display: flex;
  width: 20%;
  flex-direction: column;
  align-items: center;
  list-style: none;
}
.band-name-input {
  width: 20rem;
}
.band-description-input {
  width: 20rem;
}
.genre-selector {
  width: 20rem;
}
.image-link {
  width: 20rem;
}
</style>