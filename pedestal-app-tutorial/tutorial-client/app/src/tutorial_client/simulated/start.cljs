(ns tutorial-client.simulated.start
  (:require [io.pedestal.app.render.push.handlers.automatic :as d]
            [tutorial-client.start :as start]
            [tutorial-client.rendering :as rendering]
            [goog.Uri]
            ;; This needs to be included somewhere in order for the
            ;; tools to work.
            [io.pedestal.app-tools.tooling :as tooling]))

(defn param [name]
  (let [uri (goog.Uri. (.toString  (.-location js/document)))]
    (.getParameterValue uri name)))

(defn ^:export main []
  (start/create-app (if (= "auto" (param "renderer"))
                      d/data-renderer-config
                      (rendering/render-config))))
