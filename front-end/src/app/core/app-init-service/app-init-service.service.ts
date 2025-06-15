import { Injectable } from '@angular/core';

/**
 * **AppInitService**
 *
 * This service is responsible for performing **initialization tasks**
 * before the application starts. It is commonly used with `APP_INITIALIZER`
 * in Angular to ensure important configurations are loaded before bootstrapping
 * the main app module.
 *
 * ### Why Do We Need `AppInitService`?
 * - **Preloads essential app configurations** (e.g., API URLs, feature flags).
 * - **Ensures user authentication state is available** before the app loads.
 * - **Initializes global services** that are required before the UI is rendered.
 * - **Prevents race conditions** by ensuring dependencies are ready before components use them.
 *
 * @export
 * @class AppInitService
 */
@Injectable()
export class AppInitService {
  constructor() {}

  /**
   * **initApp()**
   *
   * This function is called before the app starts.
   * It can be extended to fetch app-wide settings, check authentication, etc.
   *
   * @returns {Promise<void>} A promise that resolves when initialization is complete.
   */
  initApp(): Promise<void> {
    return new Promise((resolve) => {
      console.log('App Initialization Started...');

      // Simulating an async setup process (e.g., fetching config)
      setTimeout(() => {
        console.log('App Initialization Completed!');
        resolve();
      }, 100);
    });
  }
}
