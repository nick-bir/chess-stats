FROM node:14-alpine
WORKDIR /chess-stats-client
COPY package.json .
RUN yarn
COPY . .
ENTRYPOINT yarn serve