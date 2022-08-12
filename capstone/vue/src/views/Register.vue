<template>
  <div id="register" class="register">
    <div>
      <img
        src="..\assets\LOGO TRANSPARENCY white.png"
        alt="not found"
        class="registerlogo"
      />
    </div>
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <ul class="registerUserForm">
        <li>
          <label for="username" class="sr-only"></label>
          <input
            type="email"
            id="username"
            class="form-control"
            placeholder="Email"
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
            pattern=".{8,12}"
            required
          />
        </li>
        <li>
          <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />
        </li>
        <li>
          <router-link class="link" :to="{ name: 'login' }"
            >Have an account?</router-link
          >
        </li>
        <li>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Create Account
          </button>
        </li>
      </ul>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  beforeCreate: function () {
    document.body.className = "register";
  },
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
body.register {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;
  background: black;
  color: white;
  background-image: url("~@/assets/drum.png");
  background-position: bottom;
  background-size: cover;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}
#form-register {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
#register {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.registerUserForm {
  display: flex;
  flex-direction: column;
  align-items: center;
  list-style: none;
  padding-left: 0px;
}

li {
  margin-top: 8px;
}
#login {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.registerlogo {
  margin-top: 130px;
  display: flex;
  max-width: 300px;
}

.link {
  color: inherit;
  text-decoration: none;
}
</style>
