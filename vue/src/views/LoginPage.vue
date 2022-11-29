<template>
  <v-app>
    <div class="login-box">
      <v-form ref="form" v-model="valid" lazy-validation class="login-form">
        <v-card-title class="login-title">Login</v-card-title>
        <v-card-subtitle class="subtitle"
          >ユーザー情報を入力してください</v-card-subtitle
        >
        <v-btn text color="light-blue" to="/">新規登録はこちら</v-btn>
        <v-text-field
          v-model="items.email"
          :rules="items.emailRules"
          label="メールアドレス"
          required
        ></v-text-field>

        <v-text-field
          v-model="items.password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[items.rules.required, items.rules.min]"
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
          LOGIN
        </v-btn>
        <v-btn color="error" @click="reset"> CREAR </v-btn>
      </v-form>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    valid: true,
    items: {
      email: "",
      emailRules: [
        (v) => !!v || "E-mailは必須です",
        (v) => /.+@.+\..+/.test(v) || "E-mailの形式で入力してください",
      ],
      password: "",
      rules: {
        required: (value) => !!value || "パスワードは必須です",
        min: (v) => v.length >= 8 || "8文字未満です",
        emailMatch: () => `The email and password you entered don't match`,
      },
    },
    revItems: { email: "", password: "" },
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
      axios.post("/login", this.items).then((response) => {
        this.revItems = response.data;
        if (
          (this.revItems.email && this.revItems.password) ==
            "NotFoundEmailAndPassword" ||
          (this.revItems.email && this.revItems.password) == null
        ) {
          return;
        }else{
          sessionStorage.setItem("email", this.revItems.email);
          sessionStorage.setItem("id",this.revItems.id);
        this.$router.push("/home");
        }
        
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