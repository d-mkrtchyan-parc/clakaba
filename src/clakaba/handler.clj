;; неймспейс для обработки входящих HTTP запросов 
(ns clakaba.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [clakaba.routes.admin :refer [admin-routes]]
            [clakaba.routes.home :refer [home-routes]]
            [clakaba.views.static :as static]
            [cemerick.friend :as friend]
            (cemerick.friend  [workflows :as workflows]
                              [credentials :as creds])))

(defroutes app-routes
  (route/resources "/") ; пути для приложения
  (route/not-found static/Page-404)) ; 404-ая страница

; Mock-список юзеров
(def users {"admin" {:username "admin"
                    :password (creds/hash-bcrypt "admin")
                    :roles #{::admin}}
            "moder" {:username "moder"
                    :password (creds/hash-bcrypt "moder")
                    :roles #{::moderator}}})


; Main application's handler
; главный обработчик запросов compojure (еще не разобрался как работает )
(def app
  (-> (routes home-routes admin-routes app-routes)
      (handler/site)
      (wrap-base-url)))


(def secured-app
  (-> app
    (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn users)
                          :workflows [(workflows/interactive-form)]})
    ; ...required Ring middlewares ...
    ))

; этот метод вызывается, когда инициализируется приложение
(defn init []
  (println "Compojure server starting"))

; этот метод вызывается, когда приложение закрывается
(defn destroy []
	;(log app) 
	(println "App is shuting down"))