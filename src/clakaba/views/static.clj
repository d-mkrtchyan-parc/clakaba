;; Здесь хранятся статичные шаблоны

(ns clakaba.views.static
  (:require 
    [hiccup.page :refer [html5 include-css include-js]]
    [hiccup.core :refer [html]]
    [clakaba.views.layout :refer [page]]))


(def ^{:private true} not-found (html5
  [:div.container [:h1 "Not Found"]]))

(def Page-404 (page {
  :content not-found
  :cclass "b-not-found" }))

(def Footer (html
  [:footer]))