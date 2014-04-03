(ns myblog.views
  (:require [hiccup.core :refer (html)]
            [myblog.posts :as posts]))

(defn layout [title & content]
  (html
   [:head [:title title]]
   [:body content]))

(defn main-page []
  (layout "My Blog"
          [:h1 "My Blog"]
          [:p "Whazzuuuuup"]))

; Post is a map corresponding to a record from the database
(defn post-summary [post]
  (let [id (:id post)
        title (:title post)
        body (:body post)
        created-at (:created-at post)]
    [:section
     [:h3 title]
     [:h4 created-at]
     [:section body]
     [:section.actions
      [:a {:href (str "/admin/" id "/edit")} "Edit"] " / "
      [:a {:href (str "/admin/" id "/delete")} "Delete"]]]))

(defn admin-blog-page []
  (layout "My Blog - Administrator's Blog"
          [:h1 "Administrator's Blog"]
          [:h2 "All my posts"]
          [:a {:href "admin/add"} "Add"]
          (map #(post-summary %) (posts/all))))
