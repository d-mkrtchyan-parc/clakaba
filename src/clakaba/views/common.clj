(ns clakaba.views.common
  (:require 
    [hiccup.page :refer [xhtml html5 include-css include-js]]
    [hiccup.core :refer [html]]))

(def head (html
  [:head
    [:title "Clakaba"]
      (include-css "/css/screen.css")
      (include-css "/css/bootstrap.css")
      (include-css "/css/main.css")
      ;(include-js "/js/main.js")
      ]))


(def footer (html 
  [:footer 
    [:div.container
      [:div.row 
        [:div.col-md-12.center "Footer"]]]]))

(def header (html [:header [:h1 [:a {:href "/"} "Clakaba"]]]))