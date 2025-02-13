# Fetch Rewards Coding Exercise - Software Engineering (Mobile)

This project is a native Android application built using Kotlin that retrieves data from the following endpoint:


The app processes and displays the list of items based on these requirements:
- **Display all items grouped by `listId`**
- **Sort the results first by `listId` then by `name`** when displaying.
- **Filter out any items** where `name` is blank or null.
- The final result is shown in an easy-to-read list.

## Features

- **Data Retrieval:**  
  Uses Retrofit to fetch data from the provided endpoint.

- **Data Processing:**  
  - Filters out items with blank or null names.
  - Groups items by `listId`.
  - Sorts groups in ascending order by `listId` and sorts items within each group by `name`.

- **User Interface:**  
  Built with Jetpack Compose, the app displays the processed data in a single `LazyColumn` with group headers.

## Project Structure

- **app/src/main/java/com/example/fetchapptest/**
  - **di/**: Contains Hilt modules for dependency injection.
  - **models/**: Contains data model classes (e.g., `FetchItem`, `FetchItemsList`).
  - **network/**: Contains the Retrofit API interfaces, network client, and response models.
  - **ui/**: Contains Jetpack Compose UI components and screens.
  - **viewmodel/**: Contains ViewModel classes that manage UI state and data fetching.

## Requirements

- Android Studio (latest stable version)
- Android SDK (targeting the current stable Android release)
- Kotlin

## How to Build & Run

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/SaidShatila/FetchAppInterviewTest.git
