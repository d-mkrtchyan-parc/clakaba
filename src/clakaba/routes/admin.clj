;; Здесь хранятся пути для админов и прочее бобро
(ns clakaba.routes.admin
  (:use ring.util.response)
  
  (:require 
    [compojure.core :refer :all]
    [clakaba.admin.auth :refer [auth]]
    [clakaba.views.common :as common]
    [clakaba.views.templates :as tpl]
    [clakaba.dsl.users :as users :refer (users)]

    [cemerick.friend :as friend]
    
    (cemerick.friend  [workflows :as workflows]
                      [credentials :as creds])))

(defroutes admin-routes
  (GET "/login" {{error? :login_failed} :params} (tpl/auth-form (if (= error? "Y") (common/auth-error :params) nil)))
  
  (GET "/logout" req
    (friend/logout* (redirect (str (:context req) "/"))))
  ; (POST "/login" req (friend/authenticated "Thanks for authenticating!")))
  
  (GET "/admin" req
    (friend/authorize #{::users/admin} (tpl/gui)))
  (GET "/moder" req
    (friend/authorize #{::users/moder} (tpl/gui)))

  (POST "/login" req
    (fn[r]
      (do
        (println (str r "\n azaza"))
        (friend/authorize #{::users/admin} "You're an admin!")))))