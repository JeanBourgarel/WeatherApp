
# WeatherApp

This application displays different french cities today's weather, and their respective upcoming weather for the next week.
I used the [https://openweathermap.org/api](https://openweathermap.org/api) with the GET [/onecall](https://api.openweathermap.org/data/2.5/onecall) endpoint.

## Features

The app shows a list of items containing : the city name, the city day temperature in degrees Celcius, a description of the day weather in french and an icon.

Tapping on an item will open a dialog containing the same weather informations for the next week according to the city of the item.

## Cities

Each city displayed in the app is set in MainActivity.kt. If you'd like to add other cities, add another Triple instances and give the city name, latitude and longitude as parameters.

```kotlin
val cities = mutableListOf(
    Triple("Nantes", 47.1667, -1.5833),
    Triple("Quimper", 47.9960325, -4.1024782),
    Triple("Bordeaux", 44.841225, -0.5800364),
    Triple("Lyon", 45.7578137, 4.8320114),
    Triple("Brest", 48.3905283, -4.4860088),
    Triple("Paris", 48.8566969, 2.3514616),
    Triple("Rennes", 48.1113387, -1.6800198),
    Triple("Angers", 47.4739884,-0.5515588),
)
```
