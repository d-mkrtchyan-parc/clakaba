;;;; Здесь лежат шаблоны доски

(ns clakaba.views.boards.posts
  (:require 
    [hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.views.layout :as layout]))

(defn draw-post[post]
 (let [{:keys [id domain title email hashnum content image]} post]
    [:div.b-post {:id id}
      [:div.b-post-meta
        [:div.b-post-title title]
        [:div.b-post-email email]
        [:span.b-post-id (str ">>" id)]
      ]
      [:div.b-post-content
        [:img {:src (:src image) :href (:src image) :alt (:alt image)}]
        [:div.b-post-text (html content)]
      ]
    ]))