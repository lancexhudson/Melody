<template>
  <div class="add-your-band-form">
    <h4 class="addYourBand">ADD YOUR BAND</h4>
    <form class="new-band-form" v-on:submit.prevent="saveBand">
      <ul class="band-creation">
        <li>
          <input
            class="band-name-box"
            type="text"
            placeholder="  Band Name"
            v-model="newBand.bandName"
            required
          />
        </li>
        <li>
          <textarea
            class="band-description-box"
            type="textarea"
            placeholder=" Your Band's Description"
            v-model="newBand.description"
            required
          >
          </textarea>
        </li>
        <li>
          <select
            class="genre-selector-box"
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
        <li>
          <textarea
            class="image-link-box"
            placeholder=" Image Link"
            v-model="newBand.imageLink"
            required
          >
          </textarea>
        </li>

        <li>
          <button class="saveBandButton">Save Band</button>
        </li>
      </ul>
    </form>
  </div>
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
.add-your-band-form {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.8);
  border-radius: 0.5rem;
  width: 100%;
  margin-bottom: 20px;
  max-width: 700px;
}

.new-band-form {
  max-height: 360px;
}

.band-creation,
.band-name-input,
option,
button {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.form {
  justify-content: center;
  align-items: center;
}
.band-creation {
  display: flex;
  flex-direction: column;
  margin-bottom: 300px;
  margin-right: 40px;
  list-style: none;
}

.band-name-box {
  width: 330px;
  height: 30px;
  padding: 0;
  margin: 0.5rem;
  border: 0;
  height: 25px;
  border-radius: 0.2rem;
}

.band-description-box {
  height: 40px;
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0.5rem;
  border-radius: 0.2rem;
}
.genre-selector-box {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0.5rem;
  border-radius: 0.2rem;
}

.image-link-box {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0.5rem;
  border-radius: 0.2rem;
}
.saveBandButton {
  width: auto;
  min-width: 300px;
  height: auto;
  min-height: 25px;
  padding: 0;
  border: 3px;
  margin: 0;
  border-radius: 0.2rem;
}
.addYourBand {
  font-size: 15pt;
  font-weight: 900;
  text-decoration: underline;
  font-size: 2rem;
  margin-bottom: 1rem;
}

.form-row {
  border: white solid;
  border-radius: 1rem;
  background-color: 255, 255, 255, 0.8;

  backdrop-filter: blur(10px);
  margin: 1rem;
  margin-right: auto;
  max-width: 50rem;
  margin-left: 9px;
  border-radius: 0.5rem;
}
</style>