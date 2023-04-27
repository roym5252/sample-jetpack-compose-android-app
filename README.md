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