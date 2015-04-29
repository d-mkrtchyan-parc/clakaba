;; Здесь хранятся логика авторизации
(ns clakaba.admin.auth
  (:require [clakaba.variables :refer [admin-conf]]))

(defn auth[user password]
  (if
    (and (= user (:user admin-conf)) (= password (:password admin-conf)))
    ["admin"]
    false))
