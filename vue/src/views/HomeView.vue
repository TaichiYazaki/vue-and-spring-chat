<template>
  <v-app id="inspire">
    <v-app-bar app shrink-on-scroll>
      <v-toolbar-title>ルーム一覧</v-toolbar-title>
    </v-app-bar>
    <v-navigation-drawer v-model="drawer" app>
      <v-sheet color="grey lighten-4" class="pa-4">
        <label>
          <v-avatar class="mb-4" color="grey darken-1" size="64">
            <img :src="require('@/assets/img/userIcon/' + this.imgName.name)" />
            <input
              type="file"
              ref="fileInput"
              accept="image/jpeg, image/jpg, image/png"
              style="display: none"
              name="files"
              @change="updateIcon"
            />
          </v-avatar>
        </label>
        <div>
          {{ email }}
        </div>
      </v-sheet>
      <v-list-item @click="createRoom">
        <v-list-item-icon>
          <v-icon color="green"> mdi-sprout-outline</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <rooms />
        </v-list-item-content>
      </v-list-item>
      <v-list-item @click="logout">
        <v-list-item-icon>
          <v-icon color="red"> mdi-logout </v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>Logout</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-navigation-drawer>

    <v-main>
      <v-container>
        <v-row>
          <v-col v-for="n in 6" :key="n" cols="4">
            <v-avatar color="teal" size="128"></v-avatar>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
import rooms from "@/components/modal/RoomsView";
export default {
  components: {
    rooms,
  },
  mounted() {
    axios
      .get("/findById", {
        params: {
          id: this.id,
        },
      })
      .then((response) => {
        //IDに基づいたアイコン画像名の取得
        const data = response.data;
        const name = data.imgFile;
        this.$set(this.imgName, "name", name);
      });
  },
  data: () => ({
    cards: ["Today", "Yesterday"],
    drawer: null,
    links: [["mdi-logout", "logout"]],
    id: sessionStorage.getItem("id"),
    email: sessionStorage.getItem("email"),
    imgName: {
      name: "",
    },
  }),
  methods: {
    updateIcon() {
      //ファイルの情報を取得
      const file = this.$refs.fileInput.files[0];
      /**
       * 処理結果で得られたJSONが、すでにJavascriptのオブジェクトに変換された状態のため、
       * 表示用にJSONに変換する。
       */
      const formData = new FormData();
      formData.append("file", file);
      formData.append("id", this.id);

      axios.post("/insertImg", formData).then((response) => {
        const data = response.data;
        const name = data.imgFile;
        console.log("name", name);
        this.$set(this.imgName, "name", name);
      });

      //JavaScriptメモ(ファイル名だけを取り出す方法)
      //const name = file.name
    },
    createRoom() {
      this.$router.push("/createRoom");
    },
    logout() {
      this.$router.push("/login");
    },
  },
};
</script>
<style>
.createRoomBtn {
  padding-right: 100px;
}
</style>