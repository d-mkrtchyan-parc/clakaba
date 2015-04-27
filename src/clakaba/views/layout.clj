(ns clakaba.views.layout
  (:require 
    [clakaba.utils.validate :as validate]
    [clakaba.views.common :refer [head footer]]
    [hiccup.page :refer [xhtml html5 include-css include-js]]
    [hiccup.core :refer [html]]))

; принимает аргумент data и на основе его создает страницу
(defn page[data]
  (if (map? data)
      (let [{:keys [content bclass cclass id]} data] 
        (html5 head [:body {:class bclass :id id} [:div {:class (str cclass " g-wrapper")} content]] footer ))
      (html5 head [:body [:div.g-wrapper data]] footer)))

;; Создает одно-колоночный шаблон
(defn one-column[descriptor]
  (let [{ :keys [block bid bclass col-class]
          :or {block false col-class "col-md-12 col-sm-12 col-xs-12 col-lg-12"}} descriptor]
    (fn[data]
      (let [content [:div.container [:div.row [:div {:class col-class} data]]]]
        (if block [:div {:role "block" :id bid :class bclass} content] content)))))

;; Создает двух-колоночный шаблон
(defn two-column[descriptor]
  (let [{ :keys [block bid bclass left-col right-col left-col-desc right-col-desc] 
          :or {block false left-col "col-md-3 col-lg-3 col-sm-12 col-xs-12" right-col "col-md-0 col-lg-9 col-sm-12 col-xs-12"}} descriptor]
    (fn[left right]
      (let [content 
        [:div.container 
          [:div.row 
            [:div (validate/conj-val {:class left-col} left-col-desc) left] 
            [:div (validate/conj-val {:class right-col} right-col-desc) right]]]]
        (if block [:div {:role "block" :id bid :class bclass} content] content)))))