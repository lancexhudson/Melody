<template>
  <div id="login" class="text-center">
    <div>
      <img
        src="..\assets\LOGO TRANSPARENCY white.png"
        alt="not found"
        class="image"
      />
    </div>

    <h1 class="h3 mb-3 font-weight-normal">Experience Music Together</h1>
    <form class="form-signin" @submit.prevent="login">
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <ul>
        <li>
          <label for="username" class="sr-only"></label>
          <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
        </li>
        <li>
          <label for="password" class="sr-only"></label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          />
        </li>
        <li>
          <router-link class="link" :to="{ name: 'register' }"
            >Need an account?</router-link
          >
        </li>
        <li>
          <button type="submit">Sign in</button>
        </li>
      </ul>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  background: rgb(0, 0, 0);
  color: white;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}
#login {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

ul {
  display: flex;
  flex-direction: column;
  align-items: center;
  list-style: none;
  padding-left: 0px;
}

li {
  margin-top: 8px;
}

.link {
  color: inherit;
  text-decoration: none;
}

/* need to make image responsive */
img {
  display: flex;
  max-width: 300px;
}
</style>
