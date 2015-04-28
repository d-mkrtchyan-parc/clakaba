;;;; Здесь лежат шаблоны доски

(ns clakaba.views.boards.board
  (:require 
    [hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.views.boards.posts :refer [draw-post]]
    [clakaba.dsl.database :as DB]
    [clakaba.views.layout :as layout]))

(defn ^{:private true} drawer-full[thread]
  (let [{:keys [id domain author hashnum posts]} thread]
    [:div.b-thread--full {:data-domain domain}
      [:div.b-thread-op_post (draw-post (first posts))]
      [:hr.b-thread-hr]
      [:div.b-thread-posts
        (map draw-post (rest posts))
      ]
    ]))

(defn ^{:private true} drawer-truncated[thread]
  (let [{:keys [id domain author hashnum posts]} thread]
    [:div.b-thread--truncated {:data-domain domain}
      [:div.b-thread-op_post (draw-post (first posts))]
      [:hr.b-thread-hr]
      [:div.b-thread-posts
        (map draw-post (rest posts))
      ]
    ]))

(defn draw-board[threads truncated?]
  (html (map (if truncated? drawer-truncated drawer-full) threads)))