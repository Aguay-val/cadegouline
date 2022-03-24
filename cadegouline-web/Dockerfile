FROM node:16.13.1

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json /app/package.json

RUN npm install

COPY . /app

EXPOSE 8080

CMD ng serve --host=0.0.0.0
