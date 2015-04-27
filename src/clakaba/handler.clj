(ns clakaba.handler
  (:require [compojure.core :refer [defroutes routes]]
						[compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [clakaba.routes.home :refer [home-static]]))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not found"))

; Main application's handler
(def app
  (-> (routes home-static app-routes)
      (handler/site)
      (wrap-base-url)))

; Init handler
(defn init []
  (println "Compojure server starting"))

; Destroy handler
(defn destroy []
	;(log `app)
	(println "App is shuting down"))