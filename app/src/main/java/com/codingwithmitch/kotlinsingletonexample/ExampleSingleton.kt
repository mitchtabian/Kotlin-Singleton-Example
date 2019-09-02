package com.codingwithmitch.kotlinsingletonexample

import com.codingwithmitch.kotlinsingletonexample.model.User

object ExampleSingleton {

    val singletonUser: User by lazy{
        User("mitchelltabian@gmail.com", "mitch", "some_image_url.png")
    }
}



