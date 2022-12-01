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
      <v-list-item>
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
          <v-col v-for="room in rooms" :key="room">
            <v-avatar color="primary" size="70">
              <img :src="require('@/assets/img/roomIcon/' + room.file)" />
            </v-avatar>
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
    Promise.resolve()
      // 1個目の通信 ログインしたユーザーのidを取得する処理
      .then(() =>
        axios.get("/findById", {
          params: {
            id: this.id,
          },
        })
      )
      .then((response) => {
        // 1個目の通信 成功
        const data = response.data;
        const name = data.imgFile;
        this.$set(this.imgName, "name", name);
        // 2個目の通信 ルーム一覧を取得する処理
        return axios.get("/showRooms");
      })
      .then((response) => {
        // 2個目の通信 成功
        const rooms = response.data;
        for (const room of rooms) {
          this.rooms.push(room);
        }
      })
      .catch((e) => {
        // エラーが発生
        console.log(e);
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
    rooms: [],
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
        this.$set(this.imgName, "name", name);
      });

      //JavaScriptメモ(ファイル名だけを取り出す方法)
      //const name = file.name
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