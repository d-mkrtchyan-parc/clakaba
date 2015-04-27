;; Здесь хранятся пути

(ns clakaba.routes.home
  (:require [compojure.core :refer :all]
    [clakaba.views.layout :as layout]
    [clakaba.views.templates :refer [index boards]]))



(defroutes home-static
  (GET "/" [] (index nil)) ; путь до глагне
  (GET "/boards" [] boards) ;путь до списка досок (статичная страница)
  (GET "/boards/:id" {{id :id} :params} (index id)))
