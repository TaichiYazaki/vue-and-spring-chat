<template>
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
</template>