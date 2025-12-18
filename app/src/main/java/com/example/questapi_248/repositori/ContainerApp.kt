package com.example.questapi_248.repositori




interface ContainerApp {
    val repositoryDataSiswa : RepositoryDataSiswa
}

class DefaultContainerApp : ContainerApp{
    private val baseUrl = "http://10.0.2.2/QuestApi_248/"
}

class