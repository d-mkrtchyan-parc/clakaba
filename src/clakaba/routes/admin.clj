;; Здесь хранятся пути для админов и прочее бобро
(ns clakaba.routes.admin
  (:use ring.util.response)
  
  (:require 
    [compojure.core :refer :all]
    [clakaba.admin.auth :refer [auth]]
    [clakaba.views.templates :as tpl]))

(defroutes admin-routes
  (GET "/login" [] (tpl/auth-form nil))
  (GET "/admin" [] (tpl/gui))

  (POST "/login" {{user :u_name password :u_password} :params} 
    (if (auth user password)
        (redirect "/admin")
        (tpl/auth-form "Wrong user@password pair"))))
