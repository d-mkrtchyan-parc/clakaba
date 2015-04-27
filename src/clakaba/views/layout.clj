(ns clakaba.views.layout
  (:require 
			[clakaba.views.static :as static]
			[hiccup.page :refer [xhtml html5 include-css include-js]]
			[hiccup.core :refer [html]]
			[clakaba.dsl.database :as DB]))


(def boards-list (DB/get-boards))

(defn menu [url]
	(map 
		(fn[item] 
			(let [curl (:url item)]
				(vec 
					[:li 
						[:a 
							{:href (str url "/" curl)} 
							(:name item) ]]))) boards-list))

(defn get-board-content [id] 
	(if (= id 0)
			(html5
				[:p "Boards list" ])
			(html
				[:p (str "Board: " id)])))

(def boards
  (html5 
		[:ul.nav {:id "menu"}
		 	(menu "/boards") ]))


(defn page [content]
  (html5
    [:head
     [:title "CLAKABA"]
     (include-css "/css/screen.css")
     (include-css "/css/bootstrap.css")
     (include-js "/js/main.js")]
    [:body 
      [:div content]
    ]))

(defn index [board]
  (html5 
		[:div.container 
		 [:div.row
			[:div.col-md-12.col-lg-12.col-sm-12.b-header 
			 [:h1.center "Index dude"]]]
		 [:div.row
			[:div.col-md-3.col-lg-3.hidden-sm.hidden-xs.b-boards
			 [:h1.center "Boards"]
			 [:div.b-boards-list boards]] 
			[:div.col-md-9.col-lg-9.col-sm-12.col-xs-12.b-main-content
			 [:h1 (if board (get-board-content board) "Content")]]]]))
