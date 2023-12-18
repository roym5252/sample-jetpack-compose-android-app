# JLP Android Engineer Test

## Brief

Your task is to create an Android App that will allow customers to see the range of dishwashers on sale at John Lewis. This app should be simple to use and work on both phones and tablets.

You should build the application in Kotlin.

You are expected to provide adequate unit tests.

Please fork this repo into your own GitLab namespace. (You will need a GitLab account.)

## Product Grid

On initial app launch the customer should be presented with a grid of dishwashers available for purchase at John Lewis.

## Product Page

When a dishwasher is tapped on the product grid a new screen is displayed that displays the details about that particular product.

## Data

You can call these APIs:
`https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword?q=dishwasher&key=AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI`

`https://api.johnlewis.com/mobile-apps/api/v1/products/{productId}`

or use the mock data provided in `/mockdata`

## Designs

In the `/designs` folder you can find example designs of how the pages might look:

- product-page-compact.png
- product-page-regular.png
- product-grid.png

## What we are looking for

- A TDD approach to your work were appropriate.
- Appropriate levels of testing.
- Works across phone and tablet.
- Any assumptions, notes, instructions or improvement suggestions added to your README
- We assess coding choices, how you've approaced the task and factors like accessibility, performance and security.


## Getting Started

Once you have a user setup in GitLab, please request access to the codebase by clicking the **“Request Access”** link at the top of the screen - under the project name.  Then you're free to fork the repo and have some fun with it.  Please however make sure your repo is private to keep it fair for the other candidates.

When complete, please grant developer access to the jlp-test-review/candidate GitLab group and please let me know via email. One of our senior engineers will review your submission, and I’ll let you know the outcome.

## Architecture

Modern Android App Architecture is used because it clearly separates the app into three layers: UI, Domain, and Data. This makes it easy to write unit tests for the Domain layer, and to keep all UI elements in the UI layer.

A core module is created to keep all common code, and separate modules are created for each screen/feature. This structure allows new modules to be added based on features in the future, and keeps module-specific UI elements and tests within the related module. All common code is kept under the core module.

Benefits:

- Future scalability
- Code maintainability
- Separation of layers
- Module re-usability

Link: https://developer.android.com/topic/architecture

## Technologies:

- Jetpack Compose

- Coroutines

- Flow

- Hilt

- ViewModel

- Coroutines

- Retrofit

- Gson

- OkHttp

- HttpInterceptor

- Coil (Image loading)

- EncryptedSharedPreferences

- Robolectric

- AndroidX Test

- Mockito

- MockWebServer

- Timber

- Leak Canary

- EncryptedSharedPreference

- Macrobenchmark


## TDD

TDD approach was consistently employed throughout the app development process. Initially, failing unit tests were crafted, followed by the implementation of basic code to make the tests pass. Subsequently, the code underwent refactoring to adhere to the specified requirements. The Red-Green-Refactor pattern was adhered to as much as possible.


## Levels of testing

- Unit tests are written with Robolectric, which enables efficient UI testing without the need for instrumentation tests.

- Unit tests cover both screen components and domain layer use cases. Mockito is used for mocking objects, and MockWebServer is employed for simulating server responses.

- A macrobenchmark test is incorporated to ensure the app's startup performance remains optimized.


## Accessibility

- Minimum font size of 12 sp is used across the app.

- Click labels are added.

- 'mergeDescendants = true' is used wherever applicable.

- 'contentDescription' is added in appropriate areas.

- Clickable areas are kept large.

- Headings are marked as 'Modifier.semantics { heading() }'

## Security

- API key is secured inside local.properties file and it is added to gitignore. For CI, the file is uploaded to Gitlab's secured file location and CI process access this file from the secured location. This protects the API key from getting exposed.

- During app launch, if build is release version, app will check if the device is an emulator or any rooted device. If yes, app will stop execution.

- API key is loaded into EncryptedSharedPreference from local.properties. Although it can be directly accessed without loading to EncryptedSharedPreference, this is done to demonstrate knowledge in securing sensitive information within app using EncryptedSharedPreference.

- Proguard is enabled for release variant.


## Performance

- Macrobenchmarking is done to ensure the performance of app start up. This will help to compare the app performance in future with previous start up time to determine if the changes done affects the app's performance.

StartUp Test results (Samsung S23):

timeToInitialDisplayMs   min 344.3,   median 372.6,   max 612.4

Additional details: https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview

- Resource shrinking is enabled for release variant. This will ensure smaller APK, which will eventually result in better app performance.

- Leak canary library is added to capture all sorts of memory leaks. This is to ensure that the memory leaks are identified on time and will not affect the performance of the app.

- Hilt is used for dependency injection of objects, which will help to avoid unnecessary creation of objects.

## Notes

- Mock server data is used.

- Appropriate modifications are done to the UI to make it much for compactable for mobile devices.

- Offline support not included.

- When matched using product ID Some of the product's details are not available in second JSON file. As a result the error screen will be displayed if the product details are missing in the provided mock response.

- Dark mode theme implementation not included. 

## Future enhancements

- Offline support: Integrate Room library to enable offline data access and UI updates.

- Load data: Utilize Room to seamlessly load remote data into the local database.

- Data management: Connect the local database to the UI for real-time updates.

- Data security: Enhance data security by employing SQLCipher on top of Room.
