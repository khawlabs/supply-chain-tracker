# Stage 1: Build the Angular application
FROM node:20 AS build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json from the frontend project
COPY ../../front-end/package*.json ./

# Install dependencies
RUN npm install

# Copy the application source code
COPY ../../front-end .

# Build the Angular application for production
#RUN npm run build -- --configuration production
RUN npm run build-prod

# Stage 2: Serve the application with nginx
FROM nginx:alpine

# Copy the built Angular app from the previous stage to nginx html folder
COPY --from=build /app/dist/angular-frontend/browser /usr/share/nginx/html

# Copy the custom Nginx configuration file into the container
COPY docker/supply-chain-frontend/nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 to the outside world
EXPOSE 80

# Start nginx server
CMD ["nginx", "-g", "daemon off;"]
