<template>
  <v-app>
    <div class="login-box">
      <v-form ref="form" v-model="valid" lazy-validation class="login-form">
        <v-card-title class="login-title">Sign Up</v-card-title>
        <v-card-subtitle class="subtitle"
          >ユーザー情報を入力してください</v-card-subtitle
        >
        <v-btn text color="light-blue" to="login">ログインはこちら</v-btn>
        <v-text-field
          v-model="name"
          :counter="10"
          :rules="nameRules"
          label="名前"
          required
        ></v-text-field>

        <v-text-field
          v-model="email"
          :rules="emailRules"
          label="メールアドレス"
          required
        ></v-text-field>

        <v-text-field
          v-model="password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="パスワード"
          hint="8文字以上OK"
          counter
          @click:append="show1 = !show1"
        ></v-text-field>
        <v-btn
          :disabled="!valid"
          color="success"
          class="login-btn"
          @click="submit"
        >
          SIGN UP
        </v-btn>

        <v-btn color="error" @click="reset"> CREAR </v-btn>
        <v-alert dense outlined type="error" v-if="errorMessage">
          {{ errorMessage }}
        </v-alert>
      </v-form>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    valid: true,
    name: "",
    nameRules: [
      (v) => !!v || "名前は必須です",
      (v) => (v && v.length <= 10) || "10文字以内で入力してください",
    ],
    email: "",
    emailRules: [
      (v) => !!v || "E-mailは必須です",
      (v) => /.+@.+\..+/.test(v) || "E-mailの形式で入力してください",
    ],

    show1: false,
    show2: true,
    show3: false,
    show4: false,
    password: "",
    rules: {
      required: (value) => !!value || "パスワードは必須です",
      min: (v) => v.length >= 8 || "8文字未満です",
      emailMatch: () => `The email and password you entered don't match`,
    },
    errorMessage: "",
  }),

  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    submit() {
      axios
        .post("/signUp", {
          name: this.name,
          email: this.email,
          password: this.password,
        })
        .then((result) => {
          console.log("success", result);
          this.$router.push("/login");
        })
        .catch((error) => {
          console.log("fail", error);
          this.errorMessage = "ユーザー登録に失敗しました";
        });
    },
  },
};
</script>

<style>
.login-form {
  margin: 150px;
  padding: 30px;
}
.login-box {
  width: 60%;
  margin: 0px auto;
}
.login-title {
  display: inline-block;
}
.login-btn {
  margin: 20px;
}
</style>