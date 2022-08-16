<template>
  <div>
    <div class="searchBands">
      <h1 v-if="isFilteredEmpty">NO MATCHING RESULTS</h1>
      <table id="tableBands">
        <td>
          <input
            class="searchbox"
            type="text"
            id="bandNameFilter"
            v-model="filter.bandName"
            placeholder="Search by Band Name"
          />
        </td>

        <td>
          <input
            class="searchbox"
            type="text"
            id="genreFilter"
            v-model="filter.genreFilter"
            placeholder="Search by Genre"
          />
        </td>
      </table>
    </div>
    <div class="band-container">
      <p v-if="isEmpty">NO MATCHING RESULTS</p>
      <band-card
        v-bind:band="band"
        v-for="band in filteredBandList"
        v-bind:key="band.id"
        v-bind:filteredBandList="testArray"
      >
      </band-card>
    </div>
    <div>
      <band-card
        v-for="favorite in this.$store.state.myBands"
        v-bind:key="favorite.bandId"
      ></band-card>
    </div>
  </div>
</template>

<script>
import bandCard from "@/components/BandCard.vue";
import bandService from "@/services/BandService.js";
export default {
  name: "band-list",
  components: {
    bandCard,
  },
  data() {
    return {
      filter: {
        bandName: "",
        genreFilter: "",
      },
      isEmpty: this.isFilteredEmpty,
      testArray: [],
      bandByGenreArray: [],
    };
  },
  computed: {
    filteredBandList() {
      let filteredBands = this.$store.state.bands;
      if (this.filter.bandName != "") {
        filteredBands = filteredBands.filter((band) =>
          band.bandName
            .toLowerCase()
            .includes(this.filter.bandName.toLowerCase())
        );
      }
      if (this.filter.genreFilter != "") {
        filteredBands = filteredBands.filter((band) =>
          band.genres.some((genre) =>
            genre.genreName
              .toLowerCase()
              .includes(this.filter.genreFilter.toLowerCase())
          )
        );
      }
      return filteredBands;
    },
    isFilteredEmpty() {
      if (this.filteredBandList.length == 0) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    listBands() {
      bandService.listBands().then((response) => {
        this.$store.commit("SET_BANDS", response.data);
      });
    },
    updateGenreArray() {
      bandService.getBandsByGenre(this.filter.genreFilter).then((response) => {
        this.bandByGenreArray = response.data;
      });
    },
  },
  created() {
    this.listBands();
  },
};
</script>

<style>
.band-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  margin-bottom: 100px;
}

#tableBands {
  display: flex;
  justify-content: space-evenly;
  margin-top: 55px;
  margin-bottom: 55px;
}
.searchbox {
  width: 240px;
}
</style>