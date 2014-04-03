(ns myblog.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [myblog.views :as views]))

(defroutes app-routes
  public-routes
  protected-routes
  (route/not-found "Not Found"))

(defroutes public-routes
  (GET "/" [] (views/main-page))
  (route/resources "/"))

(defroutes protected-routes
  (GET "/admin" [] (views/admin-blog-page)))

(def app
  (handler/site app-routes))
