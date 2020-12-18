package com.manipal.demo.exception


class BookNotFoundException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
}
