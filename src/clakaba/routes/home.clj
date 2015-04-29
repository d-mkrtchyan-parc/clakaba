;; Здесь хранятся пути для обычных пользователей
(ns clakaba.routes.home
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
    [clakaba.views.templates :as tpl]))

(defroutes home-routes
  (GET "/" [] (tpl/index nil))  ; путь до глагне
  (GET "/boards" [] tpl/boards) ;путь до списка досок (статичная страница)
  (GET "/boards/:id" {{id :id} :params} (tpl/board id)) ;путь до конкратной доски
  (GET "/thread/:id" {{id :id} :params} (tpl/thread id)) ;путь до конкретного треда
  (GET "/new" {{from :from} :params} (tpl/new-thread from nil)))