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
  background-image: url("https://lh3.googleusercontent.com/OxeSuIiaRjzZrbyTWytTEr-C31I4sRARl7S3Q2WCCCUUHwPljTx9-eYAEsGb8SRSmYVi9ZZIU9xg5npWTKF4YYAJH8gv3DI9ECpZHi40S_HJ3LnNpq7x7YNIL7f0xNO92dwAD7F9NqpgB3b2q_QSfsh8dPAsh31gedOf0PQ5Zv9Srt1iY4MncdQaH3IjU_tcwrndcHIsG9UHgMNCgf8O5HwnEJ9hBE9-64vjCBJlc8jjgog6BO2DUyszbEmHjdzczw9vKFkFZ4v9bNCzPvZ7uPTvF4ZHI13ofFuh1LOnko1qTtaNF19j2JeprTWRdCsHbvJOepB2sZ1Q6txg5x-DIF4d0UwruZoZluazgWT0cJy8OI0a03aMbO3pQpfB8sNISxTh3HSZsoDsTsIs5l4DvirDRRkgDOIDQ-6Fg3CH0g2TiIuQx8hTTCK79RKg6Vhn5Wl5F2S6iGF8jD3tyiTtxV-IAOT7LSB45_Y-nBrDK8tHGYQpMs4m0wfZTtmN6-9YiJYtYCCz0wXacO47r26wfUL75IQru8FG_Lj0tciRZLRRglo86GQR4s0MSxFMYzjO62j_YHc34HbHHUyLGXMI5BJ8N6wyAr5Nxu9n0aDv9Uo0LFA8fB6nE4ShrhXswKGGHCwXsjZTDmoZDoNwiAcafMj6hXEraozOvBkAOJslfqeHS1HdOmcd0QzjgWtg-1Qf0BDYGM27twCpMzLKV4OvL1MEf2N-01xPPFbHqkzFn-QCoH5qCtp7gdAnqE0vzg=w1318-h880-no?authuser=0");
  background-position: center;
  background-size: cover;
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
.image {
  display: flex;
  max-width: 300px;
}
</style>
