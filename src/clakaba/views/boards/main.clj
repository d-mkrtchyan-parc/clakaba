;;;; Здесь лежат шаблоны досок

(ns clakaba.views.boards.main
  (:require 
    [hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.utils.types :refer [parse-int]]
    [clakaba.dsl.database :as DB]
    [clakaba.views.boards.board :refer [draw-board]]
    [clakaba.views.layout :as layout]))



(defn ^{:private true}menu [url]
  (map 
    (fn[item] 
      (let [curl (:url item)]
        (vec 
          [:li 
            [:a 
              {:href (str url "/" curl)} 
                (:name item) ]]))) (DB/get-boards)))

(def boards-list (html [:ul.b-boards-menu (menu "/boards")]))

(defn get-board-title[id]
  (if (= id 0)
      (html [:p "Boards" ])
      (html [:p (str "Board: " id)])))

(defn get-board-content[id]
  (let [threads (DB/get-threads id)]
    (draw-board threads true)))

(defn get-thread-content[id]
  (let [thread [(nth (DB/get-threads id) (dec (parse-int id)))]]
    (draw-board thread false)))