FROM node:21

RUN npm install -g @angular/cli@17

WORKDIR /
RUN mkdir angular-app
WORKDIR /angular-app

ENV APP_NAME 'my-app'
ENV ROUTING 'true'
ENV STANDALONE 'true'
ENV STRICT 'true'
ENV STYLE 'scss'

CMD ng new $APP_NAME --routing=$ROUTING --standalone=$STANDALONE --strict=$STRICT --style=$STYLE \
    && mv $APP_NAME/* . \
    && rm -rf $APP_NAME \
    && ng serve --host 0.0.0.0 --port 4200

EXPOSE 4200


FROM node:21

WORKDIR /angular-app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

EXPOSE 4200
CMD ["npx", "http-server", "dist/artisanat-frontend", "-p", "4200", "-a", "0.0.0.0"]
