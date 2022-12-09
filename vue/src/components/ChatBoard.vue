<template>
  <v-app id="inspire">
    <sidebarChat />
    <v-main>
      <v-container class="py-8 px-6" fluid>
        <v-row>
          <v-col cols="12">
            <v-card>
              <v-list two-line>
                <template v-for="(chat, id) in chats">
                  <v-list-item :key="id">
                    <v-list-item-avatar color="grey darken-1" size="70">
                      <img
                        :src="
                          require('@/assets/img/userIcon/' +
                            chat.signup.fileName)
                        "
                      />
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-subtitle>
                        {{ chat.message }}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                  <v-divider
                    v-if="n !== 6"
                    :key="`divider-${id}`"
                    inset
                  ></v-divider>
                </template>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <v-textarea
          v-model="body"
          append-icon="mdi-comment"
          class="mx-2"
          label="メッセージを送信する"
          rows="3"
          auto-grow
        ></v-textarea>
        <v-btn
          color="success"
          @click="submit"
          class="mr-4"
          type="submit"
          :disabled="invalid"
        >
          submit
        </v-btn>
        <v-btn @click="clear"> clear </v-btn>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
import sidebarChat from "@/components/layouts/SideBarChat";
export default {
  components: {
    sidebarChat,
  },
  created() {
    //ユーザーアイコンの取得
    axios
      .get("/chats", {
        params: {
          roomId: this.$route.query.room_id,
        },
      })
      .then((response) => {
        const data = response.data;
        for (const room of data) {
          this.chats.push(room);
        }

        console.log(this.chats);
      })
      .catch((e) => {
        // エラーが発生
        console.log(e);
      });
  },
  methods: {
    submit() {
      const data = new FormData();
      data.append("message", this.body);
      data.append("userId", this.userId);
      data.append("roomId", this.roomId);
      axios.post("/save", data).then((this.body = ""));
    },
  },
  data: () => ({
    roomId: "",
    userId: sessionStorage.getItem("id"),
    drawer: null,
    body: "",
    chats: [],
  }),
};
</script>