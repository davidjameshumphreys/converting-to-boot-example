;; An example setup showing how to move a simple library from Leiningen to Boot.

(set-env!

 :source-paths #{"src" "test"}
 ;; If resource-paths is not set then the clj files will not appear in
 ;; the JAR or uberjar
 :resource-paths #{"src"}

 ;; these values must be set to use the pom task.
 :project 'my-project
 :version "0.1.0-SNAPSHOT"

 ;; beware the initial quote on the vector!
 :dependencies '[[org.clojure/clojure "1.7.0"]
                 ;; Add your other dependencies as normal in here


                 ;; we must include this boot task in order to run
                 ;; boot test. It is scoped for the test life-cycle
                 ;; only
                 [adzerk/boot-test "1.1.0" :scope "test"]]
 )

(require '[adzerk.boot-test :refer :all])
;; or :refer [test] if you prefer to specify.


;; We set the default values for task options. We may override them
;; from the command line or if we call tasks.
(task-options!
 pom {
      ;; needed to write the pom.xml file.
      :project (get-env :project)
      :version (get-env :version)

      ;; How to add in your project license
      :license {"Eclipse Public License"
                "http://www.eclipse.org/legal/epl-v10.html"}

      ;; And url.
      :url "https://juxt.pro/"}

 ;; beware the initial quote here too.
 ;; you could use :all true instead
 aot {:namespace '#{my-project.core}}
 jar {:main 'my-project.core}

 ;; we have our own dev/user.clj file that we wish to load.  We
 ;; skip-init so that we don't clash with Boot's user ns.
 repl {:init-ns 'user
       :skip-init true}
 )

;; We want to change the behaviour of the repl task to include our own
;; namespace.
(replace-task!
 [r repl] (fn [& xs]
            ;; we only want to have "dev" included for the REPL task
            (merge-env! :source-paths #{"dev"})
            (apply r xs)))

(deftask build
  "Build the JAR file"
  [] ;; we have no options for this task.

  ;; compose the tasks.
  (comp
   (aot)
   (pom)
   (jar)))

(deftask build-uber
  "Build the uberjar file"
  []
  (comp
   (aot)
   (pom)
   (uber)
   ;; In this case, we want the jar to be named in a way that mirrors
   ;; the Leiningen way.
   (jar
    :file (format "%s-%s-standalone.jar"
                  (get-env :project)
                  (get-env :version)))))
