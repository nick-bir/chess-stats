FROM node:14-alpine
RUN yarn global add @quasar/cli
WORKDIR /chess-stats-client
RUN yarn install
EXPOSE 8080
ENTRYPOINT quasar dev