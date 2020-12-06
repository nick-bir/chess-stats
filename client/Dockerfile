FROM node:15-alpine
WORKDIR /chess-stats-client
COPY package.json .
RUN yarn install
COPY . .
ENTRYPOINT yarn serve