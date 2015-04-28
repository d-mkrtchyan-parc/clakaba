;;;; Здесь лежат все главные шаблоны
(ns clakaba.views.templates 
	(:require 
		[hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.views.boards.main :as boards]
    [clakaba.dsl.database :as DB]
		[clakaba.views.layout :as layout]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Шаблоны главной страницы

;; Лейатуы для главной страницы
(def ^{:private true} header-layout (layout/one-column {:block true :bid "g-header" :bclass ""}))
(def ^{:private true} index-layout (layout/two-column 
  { :block true 
    :bid "g-index-boards" 
    :left-col "col-sm-6 col-lg-3 col-md-3 hidden-xs"
    :right-col "col-sm-6 col-lg-9 col-md-9 col-xs-12"
    :left-col-desc {:id "g-boards-list"}}))

;;Разметка контента главной страницы
; Список борд
(def index-boards-list 
  [:div.b-boards 
    [:h1.center "Boards"] 
    [:div.b-boards--menu boards/boards-list]])
; Главный контент
(defn index-main-content[b]
    [:div.b-main-content 
      [:h1 (if b (boards/get-board-title b) "Index content")]
    ])

; главная страница
(defn index[board]
  (let [index-page-content (vector :div
    (header-layout [:h1.center "Index dude"]) ; шапка сайта    
    (index-layout index-boards-list (index-main-content board)))]

    (html5 (layout/page index-page-content))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Шаблоны списка досок
(def ^{:private true} boards-index-page (layout/one-column {:block false}))
(def ^{:private true} board-column (layout/one-column {:block true :bid "g-board"}))

(def boards (layout/page (boards-index-page 
  (html
    [:div.b-boards--description
      [:p "The list of boards"]]
    [:div.b-boards-list--full boards/boards-list]))))


;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; Шаблоны доски
(defn board[id]
    (layout/page (html
      (boards-index-page [:div.b-boards-list--top boards/boards-list])
      ((layout/one-column {:block false}) [:h1 (boards/get-board-title id)])
      (board-column (boards/get-board-content id)))))