;; Здесь хранятся логика авторизации
(ns clakaba.admin.auth)   

(defn auth[user password]
  (if
    (and (= user "admin") (= password "admin"))
    ["admin"]
    false))
