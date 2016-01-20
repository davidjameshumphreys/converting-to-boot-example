# Converting to Boot

An example Clojure project that demonstrates how to migrate to the Boot build tool.

See the blog [post](http://blog.juxt.pro/posts/boot-parity.html) for more discussion.

## Usage

Once boot is installed, you should be able to run the commands:
* `boot repl` to get a similar REPL to Leiningen. It will have the `dev/user.clj` namespace as its starting point.
* `boot test` to run all the tests.
* `boot build` equivalent to `lein jar` to generate a JAR with the main class defined.
* `boot build-uber` equivalent to `lein uberjar` to generate a standalone uberjar with the main class defined.

There is some commentary in the `build.boot` file to help.

## License

Copyright © 2016 David Humphreys

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
