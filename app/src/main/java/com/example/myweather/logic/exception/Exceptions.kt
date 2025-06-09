package com.example.myweather.logic.exception

open class DomainExceptions(message: String) : Exception(message)

class NoLocationFoundException : DomainExceptions("No location found")

class NoWeatherFoundException : DomainExceptions("No weather found")


class InvalidCityNameException : DomainExceptions("Invalid city name")


class NoHourlyTemperatureFound : DomainExceptions("no hourly temperature found")

class UnKnownWeatherConditionException : DomainExceptions("unknown weather code found")

class MissingTemperaturesException : DomainExceptions("No temperatures found in this list")

class NoMatchingClothesFoundException : DomainExceptions("No Clothes found in this list")
class InvalidDataFoundException : DomainExceptions("Invalid data found")
