(defproject my-project "0.1.0-SNAPSHOT"
  :description "An example project that demonstrates using Boot as a build tool."
  :url "https://juxt.pro/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main my-project.core
  :profiles {:dev {:source-paths ["dev"]}}
  :repl-options {:init-ns user})
