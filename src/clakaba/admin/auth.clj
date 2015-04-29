;; Здесь хранятся логика авторизации
(ns clakaba.admin.auth
  (:require 
    [clakaba.variables :refer [admin-conf]]
    [cemerick.friend :as friend]
    (cemerick.friend  [workflows :as workflows]
                      [credentials :as creds])))

; mock
(defn admin[x](println x))

(defn user-group[user] "admin")

(defn auth[user password]
  (let [group (user-group user)]
    (friend/authorize #{::admin}
      "mallah"
      "allah!")))