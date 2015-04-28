;; Здесь хранятся пути

(ns clakaba.routes.home
  (:require [compojure.core :refer :all]
    [clakaba.views.templates :as tpl]))

(defroutes home-static
  (GET "/" [] (tpl/index nil)) ; путь до глагне
  (GET "/login" [] tpl/auth-form)
  (GET "/boards" [] tpl/boards) ;путь до списка досок (статичная страница)
  (GET "/boards/:id" {{id :id} :params} (tpl/board id))
  (GET "/thread/:id" {{id :id} :params} (tpl/thread id)))
