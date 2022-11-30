<template>
  <v-app>
    <v-card elevation="9" outlined class="room">
      <v-card-title>ルーム作成</v-card-title>
      <v-btn text color="light-blue" to="/home">ルーム一覧へ戻る</v-btn>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="name"
                label="Room Name*"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-file-input
                v-model="file"
                accept="image/jpeg, image/jpg, image/png"
                label="File input"
              ></v-file-input>
            </v-col>
          </v-row>
          <v-btn color="success" class="btn">作成</v-btn>
          <v-btn color="error" class="btn">クリア</v-btn>
        </v-container>
      </v-card-text>
    </v-card>
  </v-app>
</template>

<style>
.room {
  margin: 400px;
  margin-top: 100px;
}
.btn {
  margin: 10px;
}
</style>
