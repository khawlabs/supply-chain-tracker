# Stage 1: Build the Angular application
FROM node:18 AS build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the application source code to the container
COPY . .

# Build the Angular application for production
RUN npm run build-prod

# Stage 2: Serve the application with nginx
FROM nginx:alpine

# Copy the built Angular app from the previous stage to nginx html folder
COPY --from=build /app/dist/angular-frontend/browser /usr/share/nginx/html

# Copy the custom Nginx configuration file into the container's default configuration directory.
# This configuration ensures Angular routes are handled correctly, and API requests are proxied to the backend.
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 to the outside world
EXPOSE 80

# Start nginx server
CMD ["nginx", "-g", "daemon off;"]
