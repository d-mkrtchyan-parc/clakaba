;; Здесь хранятся пути

(ns clakaba.routes.home
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
    [clakaba.admin.auth :refer [auth]]
    [clakaba.views.templates :as tpl]))

(defroutes home-static
  (GET "/" [] (tpl/index nil)) ; путь до глагне
  (GET "/login" [] (tpl/auth-form nil))
  (GET "/boards" [] tpl/boards) ;путь до списка досок (статичная страница)
  (GET "/boards/:id" {{id :id} :params} (tpl/board id))
  (GET "/thread/:id" {{id :id} :params} (tpl/thread id))

  (POST "/login" {{user :u_name password :u_password} :params} 
    (if (auth user password)
        (redirect "/")
        (tpl/auth-form "Wrong user@password pair"))))
