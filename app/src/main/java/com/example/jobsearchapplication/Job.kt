package com.example.jobsearchapplication

data class JobResult(val results: List<Job>)
data class Job(val title: String = "", val company: Company = Company(""), val location: Location = Location(""), val description: String = "", val redirect_url: String = "") {
    constructor() : this("", Company(""), Location(""), "", "")
}
data class Company(val display_name: String = "") {
    constructor() : this("")
}
data class Location(val display_name: String = "") {
    constructor() : this("")
}