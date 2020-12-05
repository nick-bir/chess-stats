FROM node:15-alpine
WORKDIR /chess-stats-client
COPY package.json .
RUN yarn
ENTRYPOINT yarn serve