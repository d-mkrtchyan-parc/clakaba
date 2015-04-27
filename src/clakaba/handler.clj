(ns clakaba.handler
  (:require [compojure.core :refer [defroutes routes]]
						[compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [clakaba.routes.home :refer [home-static]]
            [clakaba.views.static :as static]))

(defroutes app-routes
  (route/resources "/") ; пути для приложения
  (route/not-found static/Page-404)) ; 404-ая страница

; Main application's handler
; главный обработчик запросов compojure (еще не разобрался как работает )
(def app
  (-> (routes home-static app-routes)
      (handler/site)
      (wrap-base-url)))

; этот метод вызывается, когда инициализируется приложение
(defn init []
  (println "Compojure server starting"))

; этот метод вызывается, когда приложение закрывается
(defn destroy []
	;(log app) 
	(println "App is shuting down"))