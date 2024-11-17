
#FROM node:12.7-alpine
FROM docker.repository.esi.adp.com/clientcentral/node:12.7-alpine
WORKDIR /app
COPY package.json .
COPY . .
CMD ["npm", "start"]

EXPOSE 3000