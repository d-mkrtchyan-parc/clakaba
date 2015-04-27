;;;; Здесь лежат шаблоны досок

(ns clakaba.views.boards
  (:require 
    [hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.dsl.database :as DB]
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

(def boards-list (html [:ul (menu "/boards")]))

(defn get-board-title[id]
  (if (= id 0)
      (html [:p "Boards" ])
      (html [:p (str "Board: " id)])))