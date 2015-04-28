;;;; Здесь лежат все главные шаблоны
(ns clakaba.views.templates 
	(:require 
		[hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.views.boards.main :as boards]
    [clakaba.views.common :as common]
    [clakaba.dsl.database :as DB]
		[clakaba.views.layout :as layout]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Лейауты
(def ^{:private true} column (layout/one-column {:block false}))

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
    [:h1.center [:a {:href "/boards"} "Boards"]]
    [:div.b-boards--menu boards/boards-list]])
; Главный контент
(defn index-main-content[b]
    [:div.b-main-content 
      [:h1 (if b (boards/get-board-title b) "Index content")]
    ])

; главная страница
(defn index[board]
  (let [index-page-content (vector :div
    (header-layout [:h1.center "clakaba: imageboard on clojure"]) ; шапка сайта    
    (index-layout index-boards-list (index-main-content board)))]

    (html5 (layout/page index-page-content))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Шаблоны формы авторизации
(def ^{:private true} auth-layout (layout/one-column {:block true :bid "g-auth"}))
(def auth-form (layout/page 
  (html
    (column common/header)
    (auth-layout [:div.b-form.b-form--auth 
      [:form {:action "login" :type "POST" :id "auth"}
        [:div.b-input
          [:input {:type "text" :placeholder "User" :name "u_name"} ]]
        [:div.b-input
          [:input {:type "password" :placeholder "Password" :name "u_password"}]]
        [:div.b-input
          [:button.btn.b-button--primary "Submit"]]]]))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Шаблоны списка досок
(def ^{:private true} board-column (layout/one-column {:block true :bid "g-board"}))
(def ^{:private true} thread-column (layout/one-column {:block true :bid "g-thread"}))
(def ^{:private true} board-header 
  (column (html 
    [:div.b-boards-list--top.hidden-sm.hidden-xs boards/boards-list]
    [:div.b-boards-list--mobile.hidden-lg.hidden-md 
      [:i.b-icon.b-icon--boards_list {:js "publish:menu:show"}]
      [:div.g-hidden boards/boards-list]])))


(def boards (layout/page 
    (html
      (column common/header)
      (column [:div.b-boards-list--full boards/boards-list]))))


;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; Шаблоны доски
(defn board[id]
    (layout/page (html
      common/header
      board-header
      (column [:h1 (boards/get-board-title id)])
      (board-column (boards/get-board-content id)))))

(defn thread[id]
  (layout/page (html
    common/header
    board-header
    (thread-column (boards/get-thread-content id)))))