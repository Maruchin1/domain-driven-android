# Domain Driven Android

[![CI](https://github.com/Maruchin1/domain-driven-android/actions/workflows/ci-workflow.yml/badge.svg)](https://github.com/Maruchin1/domain-driven-android/actions/workflows/ci-workflow.yml)

# Building a Model which makes sense

During countless discussions regarding the best Android architecture we often forget about principles of an object-oriented programming and proper domain modeling.

This repository contains an example of a Domain Driven Design in the Android application. You can find more details and an in-depth explanation in the Medium article.

# Structure of the app

For a sake of simplicity the app is built in a single `app` module. It's struture can be scaled up and divided into multiple modules if needed.

Application follows an official [guide to app architecture](https://developer.android.com/topic/architecture) and is split into 4 major architectural layers:

- `ui` - Screens, ViewModels, Theme
- `domain` - Use Cases and corresponding data structures
- `data` - The main Model of the applicaton with corresponding Repositories
- `core` - Setup for [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html), [Data Store](https://developer.android.com/topic/libraries/architecture/datastore) and [Retrofit](https://square.github.io/retrofit/)

# Tech stack

- [Jetpack Compose + View Model](https://developer.android.com/jetpack/compose?gclid=CjwKCAjw6IiiBhAOEiwALNqncXeI1D4qospRfSBTQylLhzj6cN2u7US96zsQ9fULwqPqb3mDQHajzxoCGVgQAvD_BwE&gclsrc=aw.ds) for the UI
- [Coroutines + Flow](https://kotlinlang.org/docs/coroutines-overview.html#tutorials) for an asynchronous processing
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for a Dependency Injection
- [Retrofit](https://square.github.io/retrofit/) for an HTTP communication
- [Data Store](https://developer.android.com/topic/libraries/architecture/datastore) for a simple key-value persistance

# What does this app do?

It is a loyality application for the fast-food type of restaurant, inspired by the McDonalds app, where user can collect points and exchange them for the coupons.

![Projekt bez tytułu (3)](https://user-images.githubusercontent.com/46427781/233632707-fc1953f0-2dbb-4c99-a825-91f8ef43dad8.png)

## Browsing available coupons

User can browse the list of available coupons which are ordered from the cheapest to the most valueable one. 

Each coupon can be clicked and then the full screen preview of it is displayed.

## Exchanging collected points for the coupons

For buying the food user collects the points. Each coupon in the app has a value expreseed in points. 

User can use collected points and exchange them for the coupons.

## Activation of the coupon

When user exchanges points for the coupon this coupon becomes active for 60 seconds. 

During this time an activation code is displayed on the screen and user can use it in the restaurant to receive a free meal.

## Creating an account

To collect the points user needs an account. Account can be created directly in the application.

User needs to enter an email address and accept Terms & Conditions notes.
