FROM node:20

WORKDIR /angular-app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

EXPOSE 4200
CMD ["npx", "http-server", "dist/artisanat-frontend", "-p", "4200", "-a", "0.0.0.0"]
