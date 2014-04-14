(ns ^:shared tutorial-client.behavior
    (:require [clojure.string :as string]
              [io.pedestal.app.messages :as msg]
              [io.pedestal.app :as app]
              [io.pedestal.app.protocols :as p]
              [tutorial-client.simulated.services :as services]))

(defn init-main [_]
  [[:transform-enable [:main :my-counter] :inc [{msg/topic [:my-counter]}]]])

(defn inc-transform [old-value _]
  ((fnil inc 0) old-value))

(defn swap-transform [_ message]
  (:value message))

(def example-app
  {:version 2
   :transform [[:inc  [:my-counter] inc-transform]
               [:swap [:**]         swap-transform]]
   :emit [{:init init-main}
          [#{[:my-counter]} (app/default-emitter [:main])]]})

(defn ^:export main []
  (let [app (start/create-app d/data-renderer-config)
        services (services/->MockServices (:app app))]
    (p/start services)
    app))
