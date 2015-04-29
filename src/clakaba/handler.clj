;; неймспейс для обработки входящих HTTP запросов 
(ns clakaba.handler
  (:use ring.util.response)

  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]

            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [clakaba.routes.admin :refer [admin-routes]]
            [clakaba.routes.home :refer [home-routes]]
            [clakaba.views.static :as static]
            [clakaba.dsl.users :as users :refer (users)]
            [cemerick.friend :as friend]
            (cemerick.friend  [workflows :as workflows]
                              [credentials :as creds])))

(defroutes app-routes
  (route/resources "/") ; пути для приложения
  (route/not-found static/Page-404)) ; 404-ая страница

; Main application's handler
; главный обработчик запросов compojure (еще не разобрался как работает )
(def app
  (-> (routes home-routes admin-routes app-routes)
      (friend/authenticate 
        { :allow-anon? true
          ; :login-uri "/login"
          ; :default-landing-uri "/" 
          :unauthorized-handler #(-> (str "You do not have sufficient privileges to access " (:uri %)) response (status 401))
          :credential-fn #(creds/bcrypt-credential-fn @users %)
          :workflows [(workflows/interactive-form)]})
      (handler/site)
      (wrap-base-url)))


; этот метод вызывается, когда инициализируется приложение
(defn init []
  (println "Compojure server starting"))

; этот метод вызывается, когда приложение закрывается
(defn destroy []
	;(log app) 
	(println "App is shuting down"))