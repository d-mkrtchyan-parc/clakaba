(ns clakaba.dsl.users
  (:require 
  	[cemerick.friend.credentials :refer (hash-bcrypt)]))

(def users 
  (atom {"moder"  {:username "moder"
                  :password (hash-bcrypt "moder")
                  :roles #{::moder}}

        "admin" {:username "admin"
                 :password (hash-bcrypt "admin")
                 :roles #{::admin}}}))

(derive ::admin ::moder)