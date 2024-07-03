package com.example.jobsearchapplication

data class JobResult(val results: List<Job>)
data class Job(val title: String, val company: Company, val location: Location, val description: String, val redirect_url: String)
data class Company(val display_name: String)
data class Location(val display_name: String)