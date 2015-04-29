;;;; Здесь лежат все главные шаблоны
(ns clakaba.views.templates 
	(:require 
		[hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.utils.types :refer [parse-int]]
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
(defn auth-form[error] (layout/page 
  (html
    (column common/header)
    (auth-layout [:div.b-form.b-form--auth 
      
      [:form {:action "login" :method "post" :id "auth"}
        [:div.b-input
          [:input {:type "text" :placeholder "User" :name "u_name"} ]]
        [:div.b-input
          [:input {:type "password" :placeholder "Password" :name "u_password"}]]
        [:div.b-input
          [:button.btn.b-button--primary "Submit"]]] 
      (if error [:div.b-error (str "Error: " error)])]))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Шаблоны списка досок
(def ^{:private true} board-column (layout/one-column {:block true :bid "g-board"}))
(def ^{:private true} thread-column (layout/one-column {:block true :bid "g-thread"}))
(defn ^{:private true} board-header [bid thread]
  (html 
    (column (html 
      [:div.b-boards-list--top.hidden-sm.hidden-xs boards/boards-list]
      [:div.b-boards-list--mobile.hidden-lg.hidden-md 
        [:i.b-icon.b-icon--boards_list {:js "publish:menu:show"}]
        [:div.g-hidden boards/boards-list]]))
    (column [:div.b-board-meta 
      [:ul 
        [:li [:a {:href (str "/new?from=" bid)} "New thread"]]
        (if thread [:li [:a {:href "javascript: void 0;" :js "publish:fave:"} "Add to favorite"]])
      ]])))


(def boards (layout/page 
    (html
      (column common/header)
      (column [:div.b-boards-list--full boards/boards-list]))))


;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; Шаблоны доски
(defn board[id]
    (layout/page (html
      common/header
      (board-header id nil)
      (column [:h1 (boards/get-board-title id)])
      (board-column (boards/get-board-content id)))))

(defn thread[id]
  (layout/page (html
    common/header
    (board-header id true)
    (thread-column (boards/get-thread-content id)))))

(defn gui[] "Test")

(def ^{:private true} new-thread-layout (layout/one-column {:block true :bid "g-new-thread"}))
(defn new-thread[from error]
  (let [board (:domain (nth (DB/get-threads nil) (dec (parse-int from))))]
    (layout/page
      (html 
        (column common/header)
        (new-thread-layout [:div.b-form.b-form--new-thread
          [:form {:action "post" :method "post" :id "new-thread"}
            [:div.b-input
              [:input {:type "text" :placeholder "Title" :name "title"} ]]
            [:div.b-input
              [:input {:type "password" :placeholder "Password" :name "delete_pwd"}]]
            [:div.b-input
              [:textarea {:type "text" :placeholder "Text" :name "text"}]]
            [:div.b-input
              [:input {:type "file" :placeholder "File" :name "file"} "Attachment"]]
            [:div.b-input
              [:button.btn.b-button--primary "Submit"]]] 
          (if error [:div.b-error (str "Error: " error)])
        ])
      )
    )
  )
)