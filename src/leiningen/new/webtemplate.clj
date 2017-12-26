(ns leiningen.new.webtemplate
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "webtemplate"))

(defn webtemplate
  "Create a new template for clojure webapp using compojure + mount"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' webtemplate project.")
    (->files data
             [".gitignore"  (render "gitignore")]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["dev/resources/config.yml" (render "config.yml" data)]
             ["src/{{sanitized}}/config.clj" (render "config.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/handlers.clj" (render "handlers.clj" data)]
             ["dev/src/user.clj" (render "user.clj" data)]
             )))
